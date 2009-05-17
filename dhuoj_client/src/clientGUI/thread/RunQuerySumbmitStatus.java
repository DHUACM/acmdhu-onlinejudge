package clientGUI.thread;

import clientGUI.*;
import config.Const;

public class RunQuerySumbmitStatus implements Runnable {

    Integer queryID;

    public RunQuerySumbmitStatus(Integer qid) {
        queryID = qid;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(Const.SLEEPTIME);
                int ans = Control.Query(queryID);
                System.out.println(queryID + " : " + ans);
                if (ans > 1) {
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
}