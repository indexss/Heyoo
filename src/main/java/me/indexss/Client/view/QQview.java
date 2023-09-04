package me.indexss.Client.view;

import me.indexss.Client.Service.FileClientService;
import me.indexss.Client.Service.MessageClientService;
import me.indexss.Client.Service.UserClientService;
import me.indexss.Client.utils.Utility;

public class QQview {
    private boolean loop = true;
    private String key = "";
    private UserClientService userClientService = new UserClientService(); //用于登陆服务器
    private MessageClientService messageClientService = new MessageClientService();
    private FileClientService fileClientService = new FileClientService();

    private void mainMenu() {
        while (loop) {
            System.out.println("welcome");
            System.out.println("1 login");
            System.out.println("9 exit");


            System.out.print("please send your choice: ");
            key = Utility.readString(1);
            switch (key) {
                case "1":
                    System.out.print("please imput id: ");
                    String userID = Utility.readString(50);
                    System.out.print("please input pwd: ");
                    String pwd = Utility.readString(50);

                    // to be verify

                    if (userClientService.checkUser(userID, pwd)) {
                        System.out.println("welcome" + userID);
                        while (loop) {
                            System.out.println("网络通信系统二级菜单 用户：" + userID);
                            System.out.println("1 拉取在线客户列表");
                            System.out.println("2 群发");
                            System.out.println("3 私聊");
                            System.out.println("4 文件");
                            System.out.println("9 退出");
                            System.out.print("请输入你的选择： ");
                            key = Utility.readString(1);
                            switch (key) {
                                case "1":
//                                    System.out.println("1 拉取在线客户列表");
                                    userClientService.onlineFriendList();
                                    break;
                                case "2":
                                    System.out.println("2 群发");
                                    System.out.print("请输入想对大家说的话：");
                                    String s = Utility.readString(100);
                                    messageClientService.sendMessageToAll(s, userID);
                                    break;
                                case "3":
                                    System.out.println("3 私聊");
                                    System.out.print("请输入想聊天的客户号： ");
                                    String getterId = Utility.readString(50);
                                    System.out.print("请输入想说的话： ");
                                    String content = Utility.readString(100);
                                    messageClientService.sendMessageToOne(content, userID, getterId);
                                    break;
                                case "4":
                                    System.out.println("4 文件");
                                    System.out.print("请输入你要发的对象id: ");
                                    String getterId1 = Utility.readString(50);
                                    System.out.print("请输入发送文件的路径：");
                                    String src = Utility.readString(100);
                                    System.out.print("请输入对方发送路径：");
                                    String dest = Utility.readString(100);
                                    fileClientService.sendFileToOne(src, dest, userID, getterId1);

                                    break;
                                case "9":
                                    System.out.println("退出");
                                    //调用方法 给服务器发送退出信息
                                    System.out.println("break");
                                    userClientService.logout();
                                    loop = false;
                                    break;
                            }
                        }
                    } else {
                        System.out.println("登陆失败");
                    }
                    break;

                case "9":
                    System.out.println("break");
                    userClientService.logout();
                    loop = false;
                    break;
            }

        }
    }

    public static void main(String[] args) {
        new QQview().mainMenu();
        System.out.println("客户端退出");
    }

}
