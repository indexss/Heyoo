package me.indexss.Server.Service;

import java.util.HashMap;
import java.util.Iterator;

public class ManageClientThreads {
    public static HashMap<String, ManageClientsAndMessageService> getHm() {
        return hm;
    }

    private static HashMap<String, ManageClientsAndMessageService> hm = new HashMap<>();

    //将线程对象益处hm集合
    public static void removeServerConnectClientThread(String userId){
        hm.remove(userId);
//        System.out.println("asdffda" + userId);
    }

    //添加线程对象到hm集合
    public static void addClientThread(String userId, ManageClientsAndMessageService serverConnectClientThread) {
        hm.put(userId, serverConnectClientThread);
    }

    public static ManageClientsAndMessageService getServerConnectClientThread(String userId){
        return hm.get(userId);
    }

    //返回在线用户集合
    public static String getOnlineUser(){
        Iterator<String> iterator = hm.keySet().iterator();
        String OnlineUserList = "";
        while(iterator.hasNext()){
            OnlineUserList += iterator.next().toString() + " ";
        }
        return OnlineUserList;
    }


}
