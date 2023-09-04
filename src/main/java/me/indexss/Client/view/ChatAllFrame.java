package me.indexss.Client.view;

import me.indexss.Client.Service.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.Socket;

public class ChatAllFrame extends JFrame{
    public static String sendString;
    public static String getString;
    public static String setterId;
    public static String getterId;

    public static JTextArea jta = null;

    public ChatAllFrame(String setterId){
        this.setTitle(setterId + " 的群聊窗口");
        this.setterId = setterId;
        this.setBounds(500,500,500,400);
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        // 创建一个 JScrollPane 并将 JTextArea 放置其中
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 480, 280);

        jta = new JTextArea();
        jta.setEditable(false);
        scrollPane.setViewportView(jta); // 将 jta 放置在滚动面板中
        Font font = new Font("仿宋", Font.PLAIN, 15);
        jta.setFont(font);

        JTextField jf = new JTextField();
        jf.setBounds(8, 323, 420, 40);

        JButton emojiButton = new JButton("😊");
        emojiButton.setBounds(10, 290, 35, 35);
        emojiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedEmoji = selectEmoji();
                if (selectedEmoji != null) {
                    jf.setText(jf.getText() + selectedEmoji);
                }
            }
        });

        JButton sendJB = new JButton("⇦");
        sendJB.setBounds(430, 323, 60,40);
        sendJB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendString = jf.getText();
                jf.setText("");
                AllMessageService allMessageService = new AllMessageService();
                allMessageService.sendToAll(setterId, sendString, false);
//                String[] onlineUserId = new String[5];
//                for(int i = 0; i < 5; i++){
//                    if(ListFrame.onlineUser[0] == 1){
//                        onlineUserId[i] = "大雄";
//                    }
//                    if(ListFrame.onlineUser[1] == 1){
//                        onlineUserId[i] = "小夫";
//                    }
//                    if(ListFrame.onlineUser[2] == 1){
//                        onlineUserId[i] = "胖虎";
//                    }
//                    if(ListFrame.onlineUser[3] == 1){
//                        onlineUserId[i] = "静香";
//                    }
//                    if(ListFrame.onlineUser[4] == 1){
//                        onlineUserId[i] = "机器猫";
//                    }
//
//                }
//                MessageClientService messageClientService = new MessageClientService();
//                messageClientService.sendMessageToGroup(setterId, onlineUserId, sendString);
            }
        });

        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendJB.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        };

        jf.addKeyListener(keyListener);

        contentPane.add(scrollPane); // 添加滚动面板而不是 jta
        contentPane.add(jf);
        contentPane.add(emojiButton);
        contentPane.add(sendJB);

        RefreshContentThread2 refreshContentThread2 = new RefreshContentThread2();
        Thread thread = new Thread(refreshContentThread2);
        thread.start();

        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new ChatAllFrame("静香");
    }

    private String selectEmoji() {
        String[] emojis = {"😀", "😃", "😄", "😁", "😆", "😅", "😂", "🤣", "😊", "😇", "🙂", "🙃", "😉", "😌", "😍", "🥰", "😘", "😗", "😙", "😚"};
        return (String) JOptionPane.showInputDialog(
                ChatAllFrame.this,
                "选择一个表情",
                "表情",
                JOptionPane.PLAIN_MESSAGE,
                null,
                emojis,
                emojis[0]);
    }
}

class RefreshContentThread2 implements Runnable{

    @Override
    public void run() {
        while(true){
//            ClientConnectServerThread clientConnectServerThread = new ClientConnectServerThread(new Socket());
//            clientConnectServerThread.run();
            GetAllMesageService getAllMesageService = new GetAllMesageService();
            ChatAllFrame.getString = getAllMesageService.textAreaContent(false);
            System.out.println("shoudao " + ChatAllFrame.getString);
            ChatAllFrame.jta.setText(ChatAllFrame.getString);
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        }
    }
}