package me.indexss.Client.Service;

import java.io.BufferedReader;
import java.io.FileReader;

public class GetAllMesageService {
    public String textAreaContent(boolean radio) {
        String send = null;
        String content = null;
        String ret = "";
        String path = "/Users/shilinli/Desktop/Projects/javaProjects/chatqq/src/main/java/me/indexss/Pipe/AllChat.txt";
        if (radio == true){
            path = "/Users/shilinli/Desktop/Projects/javaProjects/chatqq/src/main/java/me/indexss/Pipe/RadioPipe.txt";
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
                String[] split = line.split(",");
//                for (String s : split){
//                    System.out.println(s);
//                }
                send = split[0];
                System.out.println("send: " + send);
                content = split[1];
                System.out.println("content: " + content);
                if(radio == false) {
                    ret += (send + " 说: " + content + "\n");
                }
                if(radio == true){
                    ret += (send + " 广播: " + content + "\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

}
