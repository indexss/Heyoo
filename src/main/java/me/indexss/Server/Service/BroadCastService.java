package me.indexss.Server.Service;

import me.indexss.Client.utils.Utility;
import me.indexss.messageinfo.Message;
import me.indexss.messageinfo.MessageType;

import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public class BroadCastService implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.print("请输入要广播的内容：(exit退出)");
            String s = Utility.readString(100);
            if (s.equals("exit")) {
                break;
            }
            Message message = new Message();
            message.setMesType(MessageType.allMsg);
            message.setSender("广播：");
            message.setContent(s);
            message.setSendTime(new Date().toString());
            System.out.println("广播推送消息给所有人说：" + s);
            HashMap<String, ManageClientsAndMessageService> hm = ManageClientThreads.getHm();
            Iterator<String> iterator = hm.keySet().iterator();
            while (iterator.hasNext()) {
                String onLineUserId = iterator.next().toString();

                try {
                    ObjectOutputStream oos = new ObjectOutputStream(hm.get(onLineUserId).getSocket().getOutputStream());
                    oos.writeObject(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
