package me.indexss.Client.Service;

import java.util.HashMap;

public class ManageClientConnectServerThread {


    private static HashMap<String, ClientConnectServerThread> hm = new HashMap<>();

    public static HashMap<String, ClientConnectServerThread> getHm() {
        return hm;
    }
    public static void addManageClientConnectServerThread(String userId, ClientConnectServerThread clientConnectServerThread){
        hm.put(userId, clientConnectServerThread);
    }

    public static ClientConnectServerThread getClientConnectServerThread(String userId){
        return hm.get(userId);
    }
}
