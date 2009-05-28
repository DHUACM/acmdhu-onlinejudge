package cn.edu.dhu.acm.oj.client.thread;

import cn.edu.dhu.acm.oj.client.*;
import cn.edu.dhu.acm.oj.common.config.Const;

public class RunQueryMessageStatus implements Runnable {

    Integer mID;

    public RunQueryMessageStatus(Integer i) {
        mID = i;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(Const.CLIENTQUESTIONTIME);
                if (Control.QueryQuestion(mID)) {
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
}