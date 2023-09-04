package me.indexss.Client.Service;

import me.indexss.messageinfo.Message;
import me.indexss.messageinfo.MessageType;

import java.io.*;
import java.util.Date;

public class FileClientService {
    public void sendFileToOne(String src, String dest, String senderId, String getterId) {
        try {
            Message message = new Message();
            message.setMesType(MessageType.fileMsg);
            message.setSender(senderId);
            message.setGetter(getterId);
            message.setSrc(src);
            message.setDest(dest);
            message.setSendTime(new Date().toString());

            FileInputStream fileInputStream = null;
            byte[] fileBytes = new byte[(int) new File(src).length()];
            try {
                fileInputStream = new FileInputStream(src);
                fileInputStream.read(fileBytes);
                message.setFileBytes(fileBytes);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("\n" + senderId + " 给 " + getterId + " 发送文件 " + src + " 到对方的 " + dest);
            try {
                ObjectOutputStream oos = new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream());
                oos.writeObject(message);
            } catch (IOException e) {
//                throw new RuntimeException(e);
            }
        } catch(Exception e){}
    }
}
