package me.indexss.Client.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OneMessageService {
    public String getSenderId() {
        return SenderId;
    }

    public void setSenderId(String senderId) {
        SenderId = senderId;
    }

    public String getGetterId() {
        return getterId;
    }

    public void setGetterId(String getterId) {
        this.getterId = getterId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String SenderId;
    private String getterId;
    private String content;

    public void sendToOne(String senderId, String getterId, String content) {
        String path = "/Users/shilinli/Desktop/Projects/javaProjects/chatqq/src/main/java/me/indexss/Pipe/OneChat.txt";

        File file = new File(path);

        try {
            if (!file.exists()) {
                // 如果文件不存在，则创建文件
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file, true); // 设置为true，表示以追加的方式写入文件
            writer.write(senderId + "," + getterId + "," + content + "\n"); // 写入一行文字
            writer.close();

            System.out.println("写入成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
