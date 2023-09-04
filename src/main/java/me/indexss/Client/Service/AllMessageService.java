package me.indexss.Client.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AllMessageService {
    public String getSenderId() {
        return SenderId;
    }
    public void setSenderId(String senderId) {
        SenderId = senderId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    private String SenderId;
    private String content;

    public void sendToAll(String senderIdParam, String contentParam, boolean radio) {
        String path = "/Users/shilinli/Desktop/Projects/javaProjects/chatqq/src/main/java/me/indexss/Pipe/AllChat.txt";
        if(radio == true){
            path = "/Users/shilinli/Desktop/Projects/javaProjects/chatqq/src/main/java/me/indexss/Pipe/RadioPipe.txt";
        }
        File file = new File(path);
        try {
            System.out.println(senderIdParam + "," + contentParam + "\n"); // 写入一行文字
            FileWriter writer = new FileWriter(file, !radio); // 设置为true，表示以追加的方式写入文件
            String tobesend = senderIdParam + "," + contentParam + "\n"; // 使用方法参数
            writer.write(tobesend); // 写入一行文字
            writer.close(); // 关闭文件写入器
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
