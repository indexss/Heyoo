package me.indexss.Client.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GetMessageService {
    public String textAreaContent(String senderId, String getterId){
        String send = null;
        String get = null;
        String content = null;
        String ret = "";
        String path = "/Users/shilinli/Desktop/Projects/javaProjects/chatqq/src/main/java/me/indexss/Pipe/OneChat.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
                String[] split = line.split(",");
//                for (String s : split){
//                    System.out.println(s);
//                }
                send = split[0];
                get = split[1];
                content = split[2];
                if((send.equals(senderId) && get.equals(getterId)) || (send.equals(getterId) && get.equals(senderId))){
//                    System.out.println(send + " 对 " + get + " 说: " + content);
                    ret += (send + " 对 " + get + " 说: " + content + "\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
}
