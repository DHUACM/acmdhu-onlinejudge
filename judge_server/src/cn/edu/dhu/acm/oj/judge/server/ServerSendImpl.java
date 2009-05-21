package cn.edu.dhu.acm.oj.judge.server;

import cn.edu.dhu.acm.oj.judge.server.bean.JudgeBean;
import cn.edu.dhu.acm.oj.persistence.beans.SolutionBean;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class ServerSendImpl implements Runnable {

    private DBService dbs = null;
    private ArrayList<JudgeBean> listJ = new ArrayList();
    private int curJ = 0;

    public ServerSendImpl(DBService d) {
        dbs = d;
    }

    public void run() {
        service();
    }

    public void service() {
        while (true) {
            try {
                SolutionBean r = dbs.getUnjudgeFirst();
                if (r != null) {
                    if (sendRunBean(r)) {
                        dbs.removeUnjudgeFirst();
                    }
                    TimeUnit.MILLISECONDS.sleep(50);
                } else {
                    TimeUnit.MILLISECONDS.sleep(100);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean sendRunBean(SolutionBean b) {
        Socket socket = null;
        try {
            if (curJ >= listJ.size()) {
                curJ = 0;
            }
            JudgeBean j = listJ.get(curJ);
            socket = new Socket(j.getIp(), j.getPort());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(b);
            Scanner scan = new Scanner(socket.getInputStream());

            if (scan.nextLine().equals("OK")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                socket.close();
            } catch (Exception ex) {
            }
        }
    }

    public void addJudge(String ip, int port) {
        JudgeBean j = new JudgeBean();
        j.setIp(ip);
        j.setPort(port);
        listJ.add(j);
    }
}
