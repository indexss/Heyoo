package me.indexss.Client.Service;

import me.indexss.Client.Service.GetMessageService;
import me.indexss.Client.utils.AudioRecorder;
import me.indexss.Client.view.DrawingBoard;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class ChatFrame extends JFrame {

    public static String sendString;
    public static String getString;
    public static String setterId;
    public static String getterId;

    public static JTextArea jta = null;

    public ChatFrame(String setterId, String getterId){
        this.setTitle(setterId + " 对 " + getterId + " 的聊天窗口");
        this.setterId = setterId;
        this.getterId = getterId;
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

        JButton fileButton = new JButton("📁文件");
        fileButton.setBounds(75,290,60,35);
        contentPane.add(fileButton);



        fileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc=new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
                jfc.showDialog(new JLabel(), "选择");
                File file=jfc.getSelectedFile();
                if(file.isDirectory()){
                    System.out.println("文件夹:"+file.getAbsolutePath());
                }else if(file.isFile()){
                    System.out.println("文件:"+file.getAbsolutePath());
                }
//                System.out.println(jfc.getSelectedFile().getName());
                System.out.println(file.getAbsolutePath());
                String srcPath = file.getAbsolutePath();
                String dstPath = "/Users/shilinli/Desktop/Projects/javaProjects/chatqq/src/main/java/me/indexss/FileRecv";
                FileClientService fileClientService = new FileClientService();
                GetInfo.printInfo(srcPath, dstPath);
                fileClientService.sendFileToOne(srcPath, dstPath, setterId, getterId);
                OneMessageService oneMessageService = new OneMessageService();
                oneMessageService.sendToOne(setterId, getterId, "📁 我向你发送了一个文件");
            }
        });

        JButton audioButton = new JButton("🎤语音");
        audioButton.setBounds(205,290,60,35);


        contentPane.add(audioButton);

        JButton openDirJB = new JButton("📃收件");
        openDirJB.setBounds(140,290,60,35);

        openDirJB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Runtime.getRuntime().exec("open /Users/shilinli/Desktop/Projects/javaProjects/chatqq/src/main/java/me/indexss/FileRecv");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        contentPane.add(openDirJB);

        JButton playJB = new JButton("▶收音");
        playJB.setBounds(270,290,60,35);
        playJB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String srcPath = "/Users/shilinli/Desktop/Projects/javaProjects/chatqq/src/main/java/me/indexss/Client/Audio/recording.wav";
                    String dstPath = "/Users/shilinli/Desktop/Projects/javaProjects/chatqq/src/main/java/me/indexss/FileRecv";
                    GetInfo.printInfo(srcPath, dstPath);
                    Runtime.getRuntime().exec("afplay /Users/shilinli/Desktop/Projects/javaProjects/chatqq/src/main/java/me/indexss/FileRecv/recording.wav");
                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
                }
            }
        });
        contentPane.add(playJB);


        JButton drawJB = new JButton("\uD83C\uDFA8画板");
        drawJB.setBounds(335,290,60,35);
        drawJB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DrawingBoard.draw();
                String srcPath = "/Users/shilinli/Desktop/Projects/javaProjects/chatqq/src/main/java/me/indexss/Pipe/pic.png";
                String dstPath = "/Users/shilinli/Desktop/Projects/javaProjects/chatqq/src/main/java/me/indexss/FileRecv";
                FileClientService fileClientService = new FileClientService();
                GetInfo.printInfo(srcPath, dstPath);
                fileClientService.sendFileToOne(srcPath, dstPath, setterId, getterId);
                OneMessageService oneMessageService = new OneMessageService();
                oneMessageService.sendToOne(setterId, getterId, "\uD83D\uDDBC 我向你发送了一幅图画");
            }
        });

        JButton watchJB = new JButton("\uD83D\uDDBC图画");
        watchJB.setBounds(400,290,60,35);
        watchJB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String srcPath = "/Users/shilinli/Desktop/Projects/javaProjects/chatqq/src/main/java/me/indexss/Pipe/pic.png";
                    String dstPath = "/Users/shilinli/Desktop/Projects/javaProjects/chatqq/src/main/java/me/indexss/FileRecv";
                    GetInfo.printInfo(srcPath, dstPath);
                    Runtime.getRuntime().exec("open /Users/shilinli/Desktop/Projects/javaProjects/chatqq/src/main/java/me/indexss/FileRecv/pic.png");
                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
                }
            }
        });
        contentPane.add(watchJB);
        contentPane.add(drawJB);




        JButton emojiButton = new JButton("😊表情");
        emojiButton.setBounds(10, 290, 60, 35);
        emojiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedEmoji = selectEmoji();
                if (selectedEmoji != null) {
                    jf.setText(jf.getText() + selectedEmoji);
                }
            }
        });

        audioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AudioRecorder audioRecorder = new AudioRecorder();
                String srcPath = "/Users/shilinli/Desktop/Projects/javaProjects/chatqq/src/main/java/me/indexss/Client/Audio/recording.wav";
                String dstPath = "/Users/shilinli/Desktop/Projects/javaProjects/chatqq/src/main/java/me/indexss/FileRecv";
                System.out.println(audioRecorder.recordfinish);
                FileClientService fileClientService = new FileClientService();
                GetInfo.printInfo(srcPath, dstPath);
                fileClientService.sendFileToOne(srcPath, dstPath, setterId, getterId);
                OneMessageService oneMessageService = new OneMessageService();
                if(audioRecorder.recordfinish == true) {
                    oneMessageService.sendToOne(setterId, getterId, "🔊 我向你发送了一条语音");
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
                OneMessageService oneMessageService = new OneMessageService();
                oneMessageService.sendToOne(setterId, getterId, sendString);
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

        RefreshContentThread refreshContentThread = new RefreshContentThread();
        Thread thread = new Thread(refreshContentThread);
        thread.start();

        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new ChatFrame("大雄", "小夫");
    }

    private String selectEmoji() {
        String[] emojis = {"😀", "😃", "😄", "😁", "😆", "😅", "😂", "🤣", "😊", "😇", "🙂", "🙃", "😉", "😌", "😍", "🥰", "😘", "😗", "😙", "😚"};
        return (String) JOptionPane.showInputDialog(
                ChatFrame.this,
                "选择一个表情",
                "表情",
                JOptionPane.PLAIN_MESSAGE,
                null,
                emojis,
                emojis[0]);
    }
}

class RefreshContentThread implements Runnable{

    @Override
    public void run() {
        while(true){
            GetMessageService getMessageService = new GetMessageService();
            ChatFrame.getString = getMessageService.textAreaContent(ChatFrame.setterId, ChatFrame.getterId);
//            System.out.println(ChatFrame.getString);
            ChatFrame.jta.setText(ChatFrame.getString);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
