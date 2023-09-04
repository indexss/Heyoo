package me.indexss.Server.Service;

import me.indexss.messageinfo.Message;
import me.indexss.messageinfo.MessageType;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

public class ManageClientsAndMessageService extends Thread{
    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    private Socket socket;
    private String userId; // 客户端的用户id

    public ManageClientsAndMessageService(Socket socket, String userId){
        this.socket = socket;
        this.userId = userId;
    }


    @Override
    public void run() {
        while(true){
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();
                if(message.getMesType().equals(MessageType.getFriends)){
                    String onlineUser = ManageClientThreads.getOnlineUser();
                    Message message2 = new Message();
                    message2.setMesType(MessageType.retFriends);
                    message2.setContent(onlineUser);
                    message2.setGetter(message.getSender());
                    //发回去
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(message2);
                } else if (message.getMesType().equals(MessageType.exit)){
                    System.out.println(message.getSender() + " 要退出系统");
                    //将客户端对应线程从集合中删除
                    ManageClientThreads.removeServerConnectClientThread(message.getSender());
                    break;
                } else if(message.getMesType().equals(MessageType.oneMsg)) {
                    ManageClientsAndMessageService serverConnectClientThread = ManageClientThreads.getServerConnectClientThread(message.getGetter());
                    ObjectOutputStream oos = new ObjectOutputStream(serverConnectClientThread.getSocket().getOutputStream());
                    System.out.println("GET from " + message.getSender() + " to " + message.getGetter() + " content: " + message.getContent());
                    oos.writeObject(message);
                } else if(message.getMesType().equals(MessageType.allMsg)){
//                    System.out.println("收到群发消息");
                    HashMap<String, ManageClientsAndMessageService> hm = ManageClientThreads.getHm();
                    Iterator<String> iterator = hm.keySet().iterator();
                    int i = 0;
                    while(iterator.hasNext()){
//                        System.out.println("进来1");
                        String onLineUserId = iterator.next().toString();
                        System.out.println(i++ + onLineUserId);
                        if (!onLineUserId.equals(message.getSender())){
//                            System.out.println("进来2");
                            ObjectOutputStream oos = new ObjectOutputStream(hm.get(onLineUserId).getSocket().getOutputStream());
                            oos.writeObject(message);
                        }
                    }
                } else if(message.getMesType().equals(MessageType.fileMsg)){
                    ManageClientsAndMessageService serverConnectClientThread = ManageClientThreads.getServerConnectClientThread(message.getGetter());
                    ObjectOutputStream oos = new ObjectOutputStream(serverConnectClientThread.getSocket().getOutputStream());
                    oos.writeObject(message);
                } else if(message.getMesType().equals(MessageType.groupMsg)){
                    HashMap<String, ManageClientsAndMessageService> hm = ManageClientThreads.getHm();
                    Iterator<String> iterator = hm.keySet().iterator();
                    int i = 0;
                    while(iterator.hasNext()){
                        String getterId = iterator.next().toString();
                        if(getterId.equals(message.getGetter())){
                            ObjectOutputStream oos = new ObjectOutputStream(hm.get(getterId).getSocket().getOutputStream());
                            oos.writeObject(message);
                        } else {
                            continue;
                        }
                    }
                } else {
                    System.out.println("待会处理");
                }
            } catch (Exception e) {
//                e.printStackTrace();
            }

        }
    }
}
