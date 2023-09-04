package me.indexss.Server.Service;

import me.indexss.messageinfo.Message;
import me.indexss.messageinfo.MessageType;
import me.indexss.messageinfo.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class chatServer extends JFrame {
    public static void main(String[] args) {
        new chatServer();
    }

    private ServerSocket ss = null;
    //创建账号密码数据库
    private static ConcurrentHashMap<String, User> validUsers = new ConcurrentHashMap<>();


    static {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myqq", "root", "123213123123");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from qquser");
            while (rs.next()) {
                String userId = rs.getString("UserId");
                String pwd = rs.getString("pwd");
//                System.out.println(userId + " - " + pwd);
                validUsers.put(userId, new User(userId, pwd));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && conn.isClosed() == false) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    //验证用户是否有效
    private boolean checkUser(String userId, String passwd) {
        User user = validUsers.get(userId);
        if (user == null) {
            return false;
        }
        if (!user.getPasswd().equals(passwd)) {
            return false;
        }
        return true;
    }

    public static JLabel numberJlb = null;
    public static JComboBox<String> comboBox = null;

    public static ArrayList<String> options = new ArrayList<>();
    public chatServer() {
        this.setBounds(1500,30,300,250);
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);
        JLabel serverJlb = new JLabel("服务器");
        JLabel portJlb = new JLabel("监听端口: 9999");
        JButton jButton = new JButton("🦶 强制下线");

        numberJlb = new JLabel("当前在线人数: 0人");
        comboBox = new JComboBox<>();

        serverJlb.setFont(new Font("仿宋", NORMAL, 30 ));
        portJlb.setFont(new Font("仿宋", NORMAL, 15));
        numberJlb.setFont(new Font("仿宋", NORMAL, 15));
        jButton.setFont(new Font("仿宋", NORMAL, 15));

        numberJlb.setForeground(Color.BLUE);
        portJlb.setForeground(Color.red.darker());

        serverJlb.setBounds(100,10,200,50);
        portJlb.setBounds(93,42,200,50);
        numberJlb.setBounds(85, 65,200,50);
        comboBox.setBounds(55,100, 200,50);
        jButton.setBounds(90,150,120,60);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) comboBox.getSelectedItem();
                System.out.println("--==--===" + selectedOption);
                ManageClientThreads.removeServerConnectClientThread(selectedOption);
            }
        });

        contentPane.add(serverJlb);
        contentPane.add(portJlb);
        contentPane.add(numberJlb);
        contentPane.add(comboBox);
        contentPane.add(jButton);


        this.setTitle("Chat Server");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        System.out.println("服务端在9999监听");
        try {
            ss = new ServerSocket(9999);
            new Thread(new BroadCastService()).start();
            while (true) {
                Socket socket = ss.accept();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                User u = (User) ois.readObject();
                Message message = new Message();
                if (checkUser(u.getUserID(), u.getPasswd()) && ManageClientThreads.getServerConnectClientThread(u.getUserID()) == null) { //单点登录保证
                    message.setMesType(MessageType.success);
                    oos.writeObject(message);
                    //创建线程与客户端保持通信
                    ManageClientsAndMessageService serverConnectClientThread = new ManageClientsAndMessageService(socket, u.getUserID());
                    serverConnectClientThread.start();
                    //把线程对象放入集合
                    ManageClientThreads.addClientThread(u.getUserID(), serverConnectClientThread);
                    onlineCheck onlineCheck = new onlineCheck();
                    Thread thread = new Thread(onlineCheck);
                    thread.start();
                } else {
                    message.setMesType(MessageType.fail);
                    System.out.println("用户" + u.getUserID() + "输入密码为" + u.getPasswd() + "验证失败");
                    oos.writeObject(message);
                    socket.close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ss.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class onlineCheck implements Runnable{
    @Override
    public void run() {

        HashMap<String, ManageClientsAndMessageService> hm = ManageClientThreads.getHm();

        while (true) {
            Iterator<String> iterator = hm.keySet().iterator();
            ArrayList<String> onlineUsers = new ArrayList<>();
            int i = 0;

            while (iterator.hasNext()) {
                try {
                    String getterId = iterator.next().toString();
                    i++;
                    System.out.println(getterId + " : " + hm.get(getterId));
                    onlineUsers.add(getterId);
                }catch(Exception e){}

            }

            // 更新在线人数标签
            chatServer.numberJlb.setText("当前在线人数: " + i + "人");

            // 更新ComboBox选项
            for (int k = chatServer.comboBox.getItemCount() - 1; k >= 0; k--) {
                String user = (String) chatServer.comboBox.getItemAt(k);
                if (!onlineUsers.contains(user)) {
                    chatServer.comboBox.removeItemAt(k);
                }
            }
            for (String user : onlineUsers) {
                if (!chatServer.options.contains(user)) {
                    chatServer.options.add(user);
                    chatServer.comboBox.addItem(user);
                }
            }
        }
    }

}
