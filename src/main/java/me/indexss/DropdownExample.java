package me.indexss;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DropdownExample {
    public static void main(String[] args) {
        // 创建一个 JFrame 实例
        JFrame frame = new JFrame("下拉框示例");

        // 设置窗口关闭时的操作
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建一个面板
        JPanel panel = new JPanel();

        // 创建一个下拉框
        String[] options = {"选项1", "选项2", "选项3"};
        JComboBox<String> comboBox = new JComboBox<>(options);

        // 创建一个提交按钮
        JButton submitButton = new JButton("提交");

        // 添加按钮点击事件监听器
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取选中的选项
                String selectedOption = (String) comboBox.getSelectedItem();
                // 打印选中的选项到控制台
                System.out.println("所选的选项: " + selectedOption);
            }
        });

        // 将下拉框和按钮添加到面板
        panel.add(comboBox);
        panel.add(submitButton);

        // 将面板添加到窗口
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        // 设置窗口大小并显示
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}

