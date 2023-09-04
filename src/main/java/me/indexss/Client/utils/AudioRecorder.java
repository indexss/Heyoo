package me.indexss.Client.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;

public class AudioRecorder extends JFrame {

    private JButton recordButton;
    private JButton stopButton;

    private AudioFormat audioFormat;
    private TargetDataLine targetDataLine;

    public static boolean recordfinish = false;
    public AudioRecorder() {
        setTitle("语音对话录音机");
//        setSize(300, 200);
        setBounds(400,400,200,70);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        recordButton = new JButton("\uD83D\uDD34 录制");
        recordButton.addActionListener(new RecordButtonListener());
        panel.add(recordButton);

        stopButton = new JButton("⬛ 停止");
        stopButton.addActionListener(new StopButtonListener());
        panel.add(stopButton);

        getContentPane().add(panel);
        setVisible(true);

        setDefaultCloseOperation(HIDE_ON_CLOSE);
        audioFormat = new AudioFormat(8000.0f, 16, 1, true, false);
    }

    private class RecordButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
                targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
                targetDataLine.open(audioFormat);
                targetDataLine.start();

                Thread recordingThread = new Thread(new RecordingThread());
                recordingThread.start();

                recordButton.setEnabled(false);
                stopButton.setEnabled(true);
//                recordfinish = false;
            } catch (LineUnavailableException ex) {
                ex.printStackTrace();
            }
        }
    }

    private class StopButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            targetDataLine.stop();
            targetDataLine.close();

            recordButton.setEnabled(true);
            stopButton.setEnabled(false);
//            recordfinish = true;
        }
    }

    private class RecordingThread implements Runnable {
        @Override
        public void run() {
            AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
            String fileName = "/Users/shilinli/Desktop/Projects/javaProjects/chatqq/src/main/java/me/indexss/Client/Audio/recording.wav";

            try {
                AudioSystem.write(new AudioInputStream(targetDataLine), fileType, new java.io.File(fileName));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new AudioRecorder();
    }
}
