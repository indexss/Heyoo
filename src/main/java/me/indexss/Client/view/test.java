package me.indexss.Client.view;

import me.indexss.Client.Service.MessageClientService;

/**
 * @author indexss
 * @date 2023/6/11
 * @apiNote
 */
public class test {
    public static void main(String[] args) {
        MessageClientService messageClientService = new MessageClientService();
        String[] t = new String[100];
        messageClientService.sendMessageToGroup("123",t, "123");
    }
}
