package me.indexss.Client.Service;

import me.indexss.messageinfo.Message;
import me.indexss.messageinfo.MessageType;
import me.indexss.messageinfo.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class UserClientService {

    private User u = new User();
    private Socket socket;

    public String getReturnContent() {
        return returnContent;
    }

    public void setReturnContent(String returnContent) {
        this.returnContent = returnContent;
    }


    public String returnContent = null;
    public boolean checkUser(String userID, String pwd){
        boolean b = false;
        u.setUserID(userID);
        u.setPasswd(pwd);
        //连接到服务器发送对象
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //发送user对象
            oos.writeObject(u);

            //读取bool
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message ms = (Message) ois.readObject();

            if (ms.getMesType().equals(MessageType.success)){

                //创建一个和服务器通信的线程 ClientConnectServerThread
                ClientConnectServerThread clientConnectServerThread = new ClientConnectServerThread(socket);
                clientConnectServerThread.start();
                //线程集合管理
                ManageClientConnectServerThread.addManageClientConnectServerThread(userID, clientConnectServerThread);
                b = true;
            } else {
                //如果登陆失败 关闭socket 不创建和服务器通信线程
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return b;
    }

    //向服务器要在线用户列表
    public void onlineFriendList(){
        Message message = new Message();
        message.setMesType(MessageType.getFriends);
        message.setSender(u.getUserID());
        try {
            ClientConnectServerThread clientConnectServerThread = ManageClientConnectServerThread.getClientConnectServerThread(u.getUserID());
            Socket socket = clientConnectServerThread.getSocket();
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logout() {
        Message message = new Message();
        message.setMesType(MessageType.exit);
        message.setSender(u.getUserID());
        ClientConnectServerThread clientConnectServerThread = ManageClientConnectServerThread.getClientConnectServerThread(u.getUserID());
        Socket socket = clientConnectServerThread.getSocket();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
            System.out.println(u.getUserID() + " 退出系统");
//            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
