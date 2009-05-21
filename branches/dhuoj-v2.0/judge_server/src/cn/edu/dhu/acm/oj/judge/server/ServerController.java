package cn.edu.dhu.acm.oj.judge.server;

import java.io.*;
import java.util.Scanner;


public class ServerController {

    ServerRcvImpl s_rcv = null;
    ServerSendImpl s_send = null;
    DBService dbs = null;

    public ServerController() {
        dbs = new DBService();
        s_rcv = new ServerRcvImpl(dbs);
        s_send = new ServerSendImpl(dbs);
        loadJudges();
        new Thread(s_rcv).start();
        new Thread(s_send).start();
        new Thread(dbs).start();
    }

    public void loadJudges() {
        try {
            Scanner scan = new Scanner(new FileInputStream(new File("judge.ini")));
            String line = null;
            while (scan.hasNextLine()) {
                line = scan.nextLine();
                if (!line.startsWith("#")) {
                    String arr[] = line.split(":");
                    s_send.addJudge(arr[0], Integer.parseInt(arr[1]));
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ServerController s = new ServerController();
    }
}
