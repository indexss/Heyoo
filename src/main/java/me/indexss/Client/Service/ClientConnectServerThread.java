package me.indexss.Client.Service;

import me.indexss.messageinfo.Message;
import me.indexss.messageinfo.MessageType;

import java.io.*;
import java.net.Socket;

public class ClientConnectServerThread extends Thread{
    //线程需要持有socket
    private Socket socket;

    public ClientConnectServerThread(Socket socket){
        this.socket = socket;
    }

    public String getListContent() {
        return listContent;
    }

    public void setListContent(String listContent) {
        this.listContent = listContent;
    }

    public String listContent = null;


    @Override
    public void run(){
        while(true) {
//            System.out.println("客户端线程等待读取服务器消息：");
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();
                //判断message类型 然后做处理
                if(message.getMesType().equals(MessageType.retFriends)) {
                    setListContent(message.getContent());
                    String[] onlineUsers = message.getContent().split(" ");
//                    System.out.println("\n===当前在线用户列表===");
                    String path = "/Users/shilinli/Desktop/Projects/javaProjects/chatqq/src/main/java/ListPipe.txt";
                    String content = message.getContent();
                    File file = new File(path);
                    try {
                        if (!file.exists()) {
                            // 如果文件不存在，则创建文件
                            file.createNewFile();
                        }
                        FileWriter writer = new FileWriter(file, false);
                        writer.write(content + "\n"); // 写入一行文字
                        writer.close();
//                        System.out.println("写入成功！");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    for(int i = 0; i < onlineUsers.length; i++){
//                        System.out.println("用户：" + onlineUsers[i]);
                    }
                } else if(message.getMesType().equals(MessageType.oneMsg)) {
                    String path = "/Users/shilinli/Desktop/Projects/javaProjects/chatqq/src/main/java/me/indexss/Pipe/OneChat.txt";
                    String content = message.getContent();
                    File file = new File(path);
                    try {
                        if (!file.exists()) {
                            // 如果文件不存在，则创建文件
                            file.createNewFile();
                        }
                        FileWriter writer = new FileWriter(file, false);
                        writer.write(message.getContent() + "\n"); // 写入一行文字
                        writer.close();
//                        System.out.println("写入成功！");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (message.getMesType().equals(MessageType.allMsg)){
                    String path = "/Users/shilinli/Desktop/Projects/javaProjects/chatqq/src/main/java/me/indexss/Pipe/AllChat.txt";
                    String content = message.getContent();
                    File file = new File(path);
                    try {
                        if (!file.exists()) {
                            // 如果文件不存在，则创建文件
                            file.createNewFile();
                        }
                        FileWriter writer = new FileWriter(file, false);
                        writer.write(message.getContent() + "\n"); // 写入一行文字
                        writer.close();
//                        System.out.println("写入成功！");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (message.getMesType().equals(MessageType.fileMsg)) {
                    FileOutputStream fileOutputStream = new FileOutputStream(message.getDest());
                    fileOutputStream.write(message.getFileBytes());
                    fileOutputStream.close();
                } else if(message.getMesType().equals(MessageType.groupMsg)){
                    String path = "/Users/shilinli/Desktop/Projects/javaProjects/chatqq/src/main/java/me/indexss/Pipe/AllChat.txt";
                    String content = message.getContent();
                    File file = new File(path);
                    try {
                        if (!file.exists()) {
                            // 如果文件不存在，则创建文件
                            file.createNewFile();
                        }
                        FileWriter writer = new FileWriter(file, false);
                        writer.write(message.getContent() + "\n"); // 写入一行文字
                        writer.close();
//                        System.out.println("写入成功！");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    System.out.println("其他类型");
                }
            } catch (Exception e) {
//                e.printStackTrace();
            }

        }
    }

    public Socket getSocket(){
        return socket;
    }


}
