package me.indexss.Client.view;

import me.indexss.Client.Service.*;
import me.indexss.Client.utils.Utility;
import me.indexss.messageinfo.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;

import static java.lang.Thread.sleep;

//TODO:
// 1. 写留言按钮 离线才留言
// 2. 写铃铛按钮 铃铛获取留言
// 3. 写聊天窗口


public class ListFrame extends JFrame {
    public static Container contentPane = null;

    public static int[] onlineUser = new int[5];
    public static MessageClientService messageClientService = new MessageClientService();

    public static boolean stat;
    public static Thread thread = null;

    public static JTextArea rcvRadioJta = null;
    public static UserClientService userClientService = null;
    public static String id = null;
    public static JButton radioJB = null;
    public static JButton logoutJB = null;
    public static JButton daxiongJB = null;
    public static JButton xiaofuJB = null;
    public static JButton panghuJB = null;
    public static JButton jingxiangJB = null;
    public static JButton jiqimaoJB = null;
    public static JButton groupJB = null;
    public static JButton daxiongSLJB = null;
    public static JButton xiaofuSLJB = null;
    public static JButton panghuSLJB = null;
    public static JButton jingxiangSLJB = null;
    public static JButton jiqimaoSLJB = null;
//    public static JButton groupJB = null;
    public JLabel daxiongPicLabel = null;
    public JLabel xiaofuPicLabel = null;
    public JLabel jingxiangPicLabel = null;
    public JLabel panghuPicLabel = null;
    public JLabel jiqimaoPicLabel = null;
    public JLabel groupPicLabel = null;
    public JLabel guangboPicLabel = null;
    public ImageIcon daxiongImage = null;
    public ImageIcon xiaofuImage = null;
    public ImageIcon jingxiangImage = null;
    public ImageIcon panghuImage = null;
    public ImageIcon jiqimaoImage = null;
    public ImageIcon groupImage = null;

    public ImageIcon guangboImage = null;
    public JLabel daxiongText = new JLabel();
    public JLabel xiaofuText = null;
    public JLabel jingxiangText = null;
    public JLabel panghuText = null;
    public JLabel jiqimaoText = null;
    public JLabel groupText = null;
    public static JLabel daxiongolText = null;
    public static JLabel xiaofuolText = null;
    public static JLabel panghuolText = null;
    public static JLabel jingxiangolText = null;
    public static JLabel jiqimaoolText = null;
    public static JLabel groupolText = null;
    public static JTextArea guangboTextArea= null;

    public ListFrame(String userId, UserClientService userClientService){
        stat = true;
        id = userId;
        this.userClientService = userClientService;
        this.setTitle(userId + " 的聊天列表");
        this.setBounds(200,200,600,420);
        contentPane = getContentPane();
        contentPane.setLayout(null);

        daxiongImage = new ImageIcon("/Users/shilinli/Desktop/Projects/javaProjects/chatqq/src/main/java/me/indexss/avatar/daxiong.png");
        xiaofuImage = new ImageIcon("/Users/shilinli/Desktop/Projects/javaProjects/chatqq/src/main/java/me/indexss/avatar/xiaofu.png");
        jingxiangImage = new ImageIcon("/Users/shilinli/Desktop/Projects/javaProjects/chatqq/src/main/java/me/indexss/avatar/jingxiang.png");
        panghuImage = new ImageIcon("/Users/shilinli/Desktop/Projects/javaProjects/chatqq/src/main/java/me/indexss/avatar/panghu.png");
        jiqimaoImage = new ImageIcon("/Users/shilinli/Desktop/Projects/javaProjects/chatqq/src/main/java/me/indexss/avatar/jiqimao.png");
        groupImage = new ImageIcon("/Users/shilinli/Desktop/Projects/javaProjects/chatqq/src/main/java/me/indexss/avatar/qunliao.png");
        guangboImage = new ImageIcon("/Users/shilinli/Desktop/Projects/javaProjects/chatqq/src/main/java/me/indexss/avatar/guangbo.png");

        daxiongPicLabel = new JLabel();
        xiaofuPicLabel = new JLabel();
        jingxiangPicLabel = new JLabel();
        panghuPicLabel = new JLabel();
        jiqimaoPicLabel = new JLabel();
        groupPicLabel = new JLabel();
        guangboPicLabel = new JLabel();

        daxiongImage.setImage(daxiongImage.getImage().getScaledInstance(80, 80,Image.SCALE_DEFAULT ));
        xiaofuImage.setImage(xiaofuImage.getImage().getScaledInstance(80, 80,Image.SCALE_DEFAULT ));
        panghuImage.setImage(panghuImage.getImage().getScaledInstance(80, 80,Image.SCALE_DEFAULT ));
        jingxiangImage.setImage(jingxiangImage.getImage().getScaledInstance(80, 80,Image.SCALE_DEFAULT ));
        jiqimaoImage.setImage(jiqimaoImage.getImage().getScaledInstance(80, 80,Image.SCALE_DEFAULT ));
        groupImage.setImage(groupImage.getImage().getScaledInstance(80, 80,Image.SCALE_DEFAULT ));
        guangboImage.setImage(guangboImage.getImage().getScaledInstance(80, 80,Image.SCALE_DEFAULT ));

        daxiongPicLabel.setBounds(20,10,100,100);
        xiaofuPicLabel.setBounds(20,110,100,100);
        panghuPicLabel.setBounds(20,210,100,100);
        jingxiangPicLabel.setBounds(300,10,100,100);
        jiqimaoPicLabel.setBounds(300,110,100,100);
        groupPicLabel.setBounds(300,210,100,100);
        guangboPicLabel.setBounds(20,300,85,85);

        daxiongPicLabel.setIcon(daxiongImage);
        xiaofuPicLabel.setIcon(xiaofuImage);
        panghuPicLabel.setIcon(panghuImage);
        jingxiangPicLabel.setIcon(jingxiangImage);
        jiqimaoPicLabel.setIcon(jiqimaoImage);
        groupPicLabel.setIcon(groupImage);
        guangboPicLabel.setIcon(guangboImage);

        daxiongText = new JLabel("大雄");
        xiaofuText = new JLabel("小夫");
        jingxiangText = new JLabel("静香");
        panghuText = new JLabel("胖虎");
        jiqimaoText = new JLabel("哆啦A梦");
        groupText = new JLabel("群聊");

        daxiongolText = new JLabel();
        xiaofuolText = new JLabel();
        jingxiangolText = new JLabel();
        panghuolText = new JLabel();
        jiqimaoolText = new JLabel();
        groupolText = new JLabel();

        daxiongJB = new JButton("私聊");
        xiaofuJB = new JButton("私聊");
        panghuJB = new JButton("私聊");
        jingxiangJB = new JButton("私聊");
        jiqimaoJB = new JButton("私聊");
        groupJB = new JButton("加入群聊");

        daxiongSLJB = new JButton("留言");
        xiaofuSLJB = new JButton("留言");
        panghuSLJB = new JButton("留言");
        jingxiangSLJB = new JButton("留言");
        jiqimaoSLJB = new JButton("留言");
        radioJB = new JButton("发送广播");

        logoutJB = new JButton("登出");

        daxiongolText.setText("◉ 离线");
        xiaofuolText.setText("◉ 离线");
        jingxiangolText.setText("◉ 离线");
        panghuolText.setText("◉ 离线");
        jiqimaoolText.setText("◉ 离线");
        groupolText.setText("◉ 0人在线");

        daxiongolText.setForeground(Color.red.darker());
        xiaofuolText.setForeground(Color.red.darker());
        panghuolText.setForeground(Color.red.darker());
        jingxiangolText.setForeground(Color.red.darker());
        jiqimaoolText.setForeground(Color.red.darker());
//        groupolText.setForeground(Color.red.darker());

        daxiongolText.setBounds(120,8,100,100);
        xiaofuolText.setBounds(120,108,100,100);
        panghuolText.setBounds(120,208,100,100);
        jingxiangolText.setBounds(400,8,100,100);
        jiqimaoolText.setBounds(400,108,100,100);
        groupolText.setBounds(400,208,100,100);

        daxiongText.setBounds(120, 5, 50,50);
        xiaofuText.setBounds(120, 105, 50,50);
        panghuText.setBounds(120, 205, 50,50);
        jingxiangText.setBounds(400, 5, 50,50);
        jiqimaoText.setBounds(400, 105, 100,50);
        groupText.setBounds(400, 205, 50,50);

        daxiongJB.setBounds(110,70,80,30);
        xiaofuJB.setBounds(110,170,80,30);
        panghuJB.setBounds(110,270,80,30);
        jingxiangJB.setBounds(390,70,80,30);
        jiqimaoJB.setBounds(390,170,80,30);
        groupJB.setBounds(390,270,80,30);

        daxiongSLJB.setBounds(190,70,80,30);
        xiaofuSLJB.setBounds(190,170,80,30);
        panghuSLJB.setBounds(190,270,80,30);
        jingxiangSLJB.setBounds(470,70,80,30);
        jiqimaoSLJB.setBounds(470,170,80,30);



        Font font = new Font("仿宋", Font.PLAIN, 20);

        daxiongText.setFont(font);
        xiaofuText.setFont(font);
        jingxiangText.setFont(font);
        panghuText.setFont(font);
        jiqimaoText.setFont(font);
        groupText.setFont(font);

        guangboTextArea = new JTextArea();
        guangboTextArea.setBounds(100,355,410,25);
        rcvRadioJta = new JTextArea();
        rcvRadioJta.setBounds(100,320,410,25);



        logoutJB.setBounds(515,350,80,35);
        radioJB.setBounds(515,315,80,35);

        radioJB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AllMessageService allMessageService = new AllMessageService();
                String content = rcvRadioJta.getText();
                rcvRadioJta.setText("");
                allMessageService.sendToAll(id, content, true);
            }
        });

        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    radioJB.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        };

        rcvRadioJta.addKeyListener(keyListener);

        logoutJB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userClientService.logout();
                stat = false;
                setVisible(false);
            }
        });

        xiaofuJB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChatFrame(id, "小夫");
            }
        });

        daxiongJB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChatFrame(id, "大雄");
            }
        });
        panghuJB.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new ChatFrame(id, "胖虎");
                    }
                });
        jingxiangJB.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new ChatFrame(id, "静香");
                    }
                });
        jiqimaoJB.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            new ChatFrame(id, "机器猫");
                    }
                });

        groupJB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChatAllFrame(id);
            }
        });



        RefreshOnlineStat refreshOnlineStat = new RefreshOnlineStat();
        thread = new Thread(refreshOnlineStat);
        thread.start();
        contentPane.add(guangboTextArea);

        contentPane.add(logoutJB);

        contentPane.add(radioJB);
        contentPane.add(daxiongPicLabel);
        contentPane.add(xiaofuPicLabel);
        contentPane.add(jingxiangPicLabel);
        contentPane.add(jiqimaoPicLabel);
        contentPane.add(panghuPicLabel);
        contentPane.add(groupPicLabel);
        contentPane.add(guangboPicLabel);

        contentPane.add(daxiongText);
        contentPane.add(xiaofuText);
        contentPane.add(jingxiangText);
        contentPane.add(panghuText);
        contentPane.add(jiqimaoText);
        contentPane.add(groupText);

        contentPane.add(daxiongolText);
        contentPane.add(xiaofuolText);
        contentPane.add(jingxiangolText);
        contentPane.add(panghuolText);
        contentPane.add(jiqimaoolText);
        contentPane.add(groupolText);

        contentPane.add(daxiongJB);
        contentPane.add(xiaofuJB);
        contentPane.add(panghuJB);
        contentPane.add(jingxiangJB);
        contentPane.add(jiqimaoJB);
        contentPane.add(groupJB);

//        contentPane.add(daxiongSLJB);
//        contentPane.add(xiaofuSLJB);
//        contentPane.add(panghuSLJB);
//        contentPane.add(jingxiangSLJB);
//        contentPane.add(jiqimaoSLJB);
        contentPane.add(rcvRadioJta);

        if(id.equals("大雄")){
            contentPane.remove(daxiongJB);
            contentPane.remove(daxiongSLJB);
        }
        if(id.equals("小夫")){
            contentPane.remove(xiaofuJB);
            contentPane.remove(xiaofuSLJB);
        }
        if(id.equals("胖虎")){
            contentPane.remove(panghuJB);
            contentPane.remove(panghuSLJB);
        }
        if(id.equals("静香")){
            contentPane.remove(jingxiangJB);
            contentPane.remove(jingxiangSLJB);
        }
        if(id.equals("机器猫")){
            contentPane.remove(jiqimaoJB);
            contentPane.remove(jiqimaoSLJB);
        }

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
//        System.out.println(ListFrame.class.getResource("../../../../avatar/daxiong.png"));
//        System.out.println(ListFrame.class.getResource("/"));
//        new ListFrame("大雄");
    }

}

class RefreshOnlineStat implements Runnable{



    @Override
    public void run() {
        String line = null;
        while(ListFrame.stat){
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String path = "/Users/shilinli/Desktop/Projects/javaProjects/chatqq/src/main/java/ListPipe.txt";
//            ListFrame.userClientService.onlineFriendList();
            try {
                ListFrame.userClientService.onlineFriendList();
                BufferedReader reader = new BufferedReader(new FileReader(path));
                line = reader.readLine();
//                System.out.println(line);
                reader.close();
                GetAllMesageService getAllMesageService = new GetAllMesageService();
                String radioContent = getAllMesageService.textAreaContent(true);
                ListFrame.guangboTextArea.setText(radioContent);
                String[] s = line.split(" ");
//                System.out.println(line);
                int[] onlineUser = new int[6];
                onlineUser[0] = 0;
                onlineUser[1] = 0;
                onlineUser[2] = 0;
                onlineUser[3] = 0;
                onlineUser[4] = 0;
                onlineUser[5] = 0;
                for(String str : s){
//                    System.out.println(str);

                    if(str == null) {
                        System.out.println("null");
                    }
                    if(str.equals("大雄")){
                        onlineUser[0] = 1;
                    }
                    if(str.equals("小夫")){
                        onlineUser[1] = 1;
                    }
                    if(str.equals("胖虎")){
                        onlineUser[2] = 1;
                    }
                    if(str.equals("静香")){
                        onlineUser[3] = 1;
                    }
                    if(str.equals("机器猫")){
                        onlineUser[4] = 1;
                    }
                }
                for(int i = 0; i < 5; i++){
                    ListFrame.onlineUser[i] = onlineUser[i];
                }
                if(ListFrame.id.equals("大雄") && onlineUser[0] == 0){
                    JOptionPane.showMessageDialog(null,"您被服务器踢下线！","下线提示",JOptionPane.WARNING_MESSAGE);	//消息对话框
                    ListFrame.logoutJB.doClick();
                }
                if(ListFrame.id.equals("小夫") && onlineUser[1] == 0){
                    JOptionPane.showMessageDialog(null,"您被服务器踢下线！","下线提示",JOptionPane.WARNING_MESSAGE);	//消息对话框
                    ListFrame.logoutJB.doClick();
                }
                if(ListFrame.id.equals("胖虎") && onlineUser[2] == 0){
                    JOptionPane.showMessageDialog(null,"您被服务器踢下线！","下线提示",JOptionPane.WARNING_MESSAGE);	//消息对话框
                    ListFrame.logoutJB.doClick();
                }
                if(ListFrame.id.equals("静香") && onlineUser[3] == 0){
                    JOptionPane.showMessageDialog(null,"您被服务器踢下线！","下线提示",JOptionPane.WARNING_MESSAGE);	//消息对话框
                    ListFrame.logoutJB.doClick();
                }
                if(ListFrame.id.equals("机器猫") && onlineUser[4] == 0){
                    JOptionPane.showMessageDialog(null,"您被服务器踢下线！","下线提示",JOptionPane.WARNING_MESSAGE);	//消息对话框
                    ListFrame.logoutJB.doClick();
                }

                if(onlineUser[0] == 0){
                    ListFrame.daxiongolText.setText("◉ 离线");
                    ListFrame.daxiongolText.setForeground(Color.red.darker());
//                    ListFrame.contentPane.remove(ListFrame.daxiongJB);
                    ListFrame.daxiongJB.setEnabled(false);
                    ListFrame.daxiongSLJB.setEnabled(true);
                }
                if(onlineUser[0] == 1){
                    ListFrame.daxiongolText.setText("◉ 在线");
                    ListFrame.daxiongolText.setForeground(Color.green.darker());
//                    ListFrame.contentPane.add(ListFrame.daxiongJB);
                    ListFrame.daxiongJB.setEnabled(true);
                    ListFrame.daxiongSLJB.setEnabled(false);
                }if(onlineUser[1] == 0){
                    ListFrame.xiaofuolText.setText("◉ 离线");
                    ListFrame.xiaofuolText.setForeground(Color.red.darker());
//                    ListFrame.contentPane.remove(ListFrame.xiaofuJB);
                    ListFrame.xiaofuJB.setEnabled(false);
                    ListFrame.xiaofuSLJB.setEnabled(true);
                }
                if(onlineUser[1] == 1){
                    ListFrame.xiaofuolText.setText("◉ 在线");
                    ListFrame.xiaofuolText.setForeground(Color.green.darker());
//                    ListFrame.contentPane.add(ListFrame.xiaofuJB);
                    ListFrame.xiaofuJB.setEnabled(true);
                    ListFrame.xiaofuSLJB.setEnabled(false);
                }if(onlineUser[2] == 0){
                    ListFrame.panghuolText.setText("◉ 离线");
                    ListFrame.panghuolText.setForeground(Color.red.darker());
//                    ListFrame.contentPane.remove(ListFrame.panghuJB);
                    ListFrame.panghuJB.setEnabled(false);
                    ListFrame.panghuSLJB.setEnabled(true);
                }
                if(onlineUser[2] == 1){
                    ListFrame.panghuolText.setText("◉ 在线");
                    ListFrame.panghuolText.setForeground(Color.green.darker());
//                    ListFrame.contentPane.add(ListFrame.panghuJB);
                    ListFrame.panghuJB.setEnabled(true);
                    ListFrame.panghuSLJB.setEnabled(false);
                }if(onlineUser[3] == 0){
                    ListFrame.jingxiangolText.setText("◉ 离线");
                    ListFrame.jingxiangolText.setForeground(Color.red.darker());
//                    ListFrame.contentPane.remove(ListFrame.jingxiangJB);
                    ListFrame.jingxiangJB.setEnabled(false);
                    ListFrame.jingxiangSLJB.setEnabled(true);
                }
                if(onlineUser[3] == 1){
                    ListFrame.jingxiangolText.setText("◉ 在线");
                    ListFrame.jingxiangolText.setForeground(Color.green.darker());
//                    ListFrame.contentPane.add(ListFrame.jingxiangJB);
                    ListFrame.jingxiangJB.setEnabled(true);
                    ListFrame.jingxiangSLJB.setEnabled(false);
                }if(onlineUser[4] == 0){
                    ListFrame.jiqimaoolText.setText("◉ 离线");
                    ListFrame.jiqimaoolText.setForeground(Color.red.darker());
//                    ListFrame.contentPane.remove(ListFrame.jiqimaoJB);
                    ListFrame.jiqimaoJB.setEnabled(false);
                    ListFrame.jiqimaoSLJB.setEnabled(true);
                }
                if(onlineUser[4] == 1){
                    ListFrame.jiqimaoolText.setText("◉ 在线");
                    ListFrame.jiqimaoolText.setForeground(Color.green.darker());
//                    ListFrame.contentPane.add(ListFrame.jiqimaoJB);
                    ListFrame.jiqimaoJB.setEnabled(true);
                    ListFrame.jiqimaoSLJB.setEnabled(false);
                }

                int count = 0;
                for(int i = 0; i<5; i++){
                    if(onlineUser[i] == 1){
                        count ++;
                    }
                }
                ListFrame.groupolText.setForeground(Color.BLUE);
                ListFrame.groupolText.setText("◉ " + count + "人在线");

            } catch (Exception e) {
//                e.printStackTrace();
            }
        }
    }
}


