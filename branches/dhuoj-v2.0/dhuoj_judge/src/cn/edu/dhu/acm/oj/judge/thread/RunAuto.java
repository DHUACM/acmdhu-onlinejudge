package cn.edu.dhu.acm.oj.judge.thread;

import cn.edu.dhu.acm.oj.judge.*;

public class RunAuto implements Runnable {

    public void run() {
        while (true) {
            try {
                if(!Control.getIsauto()){
                    Thread.sleep(3000);
                    continue;
                }
                int cnt = Control.getQueueNum();
                int time = 3000;
                if (cnt > 2) {
                    time = 0;
                }
                if (cnt >= 1) {
                    try {
                        Control.GetSubmit();
                        Control.Judge();
                        Control.SendResult();
                    } catch (Exception e) {
                        System.out.println("autoJudge_exception out");
                    }
                }
                Thread.sleep(time);
            } catch (Exception e) {
                System.out.println("RunAuto_stop");
            }
        }
    }
}
