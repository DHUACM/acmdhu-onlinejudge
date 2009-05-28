package cn.edu.dhu.acm.oj.client.thread;

import cn.edu.dhu.acm.oj.client.*;
import cn.edu.dhu.acm.oj.common.config.Const;

public class RunQuerySumbmitStatus implements Runnable {

    Integer queryID;

    public RunQuerySumbmitStatus(Integer qid) {
        queryID = qid;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(Const.CLIENTSTATUSTIME);
                int ans = Control.Query(queryID);
                System.out.println(queryID + " : " + Const.VERDICT[ans]);
                if (ans != Const.WAIT && ans != Const.QUEUE) {
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
}