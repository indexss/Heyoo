package me.indexss.Client.view;

import com.formdev.flatlaf.intellijthemes.FlatDraculaIJTheme;
import me.indexss.Client.Service.ClientConnectServerThread;
import me.indexss.Client.Service.MessageClientService;
import me.indexss.Client.Service.UserClientService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LoginFrame extends JFrame {
    public UserClientService userClientService = new UserClientService(); //用于登陆服务器
    public MessageClientService messageClientService = new MessageClientService();
    public LoginFrame() {
        this.setSize(450, 300);
        setLocationRelativeTo(null);
        Container loginPane = this.getContentPane();
        loginPane.setLayout(null);


        ImageIcon backgroundImage = new ImageIcon("/Users/shilinli/Desktop/Projects/javaProjects/chatqq/src/main/java/me/indexss/Client/view/backpic.jpeg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(-200,-95,700,200);
        loginPane.add(backgroundLabel);

        // 创建标题标签并设置字体样式
        JLabel titleLabel = new JLabel("登录窗口");
        titleLabel.setFont(new Font("宋体", Font.BOLD, 30));
        titleLabel.setBounds(160, 35, 200, 30);

        // 创建用户名标签和输入框
        JLabel usernameLabel = new JLabel("用户名：");
        usernameLabel.setFont(new Font("仿宋", NORMAL, 20));
        JTextField usernameField = new JTextField();
        usernameLabel.setBounds(50, 120, 100, 30);
        usernameField.setBounds(130, 120, 250, 35);

        // 创建密码标签和密码框
        JLabel passwordLabel = new JLabel("密码：");
        passwordLabel.setFont(new Font("", NORMAL, 20));
        JPasswordField passwordField = new JPasswordField();
        passwordLabel.setBounds(50, 170, 100, 30);
        passwordField.setBounds(130, 170, 250, 35);

        // 创建登录按钮
        JButton loginButton = new JButton("登录");
        loginButton.setFont(new Font("仿宋", Font.BOLD, 20));
        loginButton.setBounds(170, 220, 100, 40);

        // 添加登录按钮的监听器
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取用户名和密码
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // 清空用户名和密码框
                usernameField.setText("");
                passwordField.setText("");    // 打印用户名和密码

                System.out.println("用户名：" + username);
                System.out.println("密码：" + password);
                if(userClientService.checkUser(username, password)) {
                    System.out.println("登陆成功");
                    userClientService.onlineFriendList();
                    JOptionPane.showMessageDialog(LoginFrame.this, "登录成功");
                    new ListFrame(username, userClientService);

                    setVisible(false);
                } else {
                    System.out.println("登陆失败");
                }



            }
        });

        // 添加键盘监听器到用户名框和密码框
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // 模拟点击登录按钮
                    loginButton.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        };

        usernameField.addKeyListener(keyListener);
        passwordField.addKeyListener(keyListener);

        // 将组件添加到登录面板
        loginPane.add(titleLabel);
        loginPane.add(usernameLabel);
        loginPane.add(usernameField);
        loginPane.add(passwordLabel);
        loginPane.add(passwordField);
        loginPane.add(loginButton);
        loginPane.add(backgroundLabel);
//        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}
