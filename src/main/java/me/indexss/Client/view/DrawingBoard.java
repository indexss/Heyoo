package me.indexss.Client.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class DrawingBoard extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private BufferedImage canvas;
    private Graphics2D g2d;

    public DrawingBoard() {
        canvas = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g2d = canvas.createGraphics();

        setTitle("画板");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton saveButton = new JButton("发送");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String savePath = "/Users/shilinli/Desktop/Projects/javaProjects/chatqq/src/main/java/me/indexss/Pipe/pic.png";

                File file = new File(savePath);
                try {
                    ImageIO.write(canvas, "png", file);
                    JOptionPane.showMessageDialog(DrawingBoard.this, "图画保存成功!");
                    setVisible(false);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(DrawingBoard.this, "保存失败！ " + ex.getMessage());
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(saveButton);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(new DrawingPanel(), BorderLayout.CENTER);
        contentPane.add(panel, BorderLayout.SOUTH);
    }

    private class DrawingPanel extends JPanel {
        private int startX;
        private int startY;

        public DrawingPanel() {
            setBackground(Color.BLUE);

            addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    startX = evt.getX();
                    startY = evt.getY();
                }
            });

            addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                public void mouseDragged(java.awt.event.MouseEvent evt) {
                    int endX = evt.getX();
                    int endY = evt.getY();
                    g2d.drawLine(startX, startY, endX, endY);
                    startX = endX;
                    startY = endY;
                    repaint();
                }
            });
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(canvas, 0, 0, null);
        }
    }

    public static void draw(){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DrawingBoard().setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DrawingBoard().setVisible(true);
            }
        });
    }
}
