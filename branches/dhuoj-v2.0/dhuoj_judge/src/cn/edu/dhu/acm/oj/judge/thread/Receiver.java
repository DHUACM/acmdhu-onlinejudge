package cn.edu.dhu.acm.oj.judge.thread;

import cn.edu.dhu.acm.oj.judge.Control;
import cn.edu.dhu.acm.oj.common.config.Const;

public class Receiver implements Runnable {

    public void run() {
        while (true) {
            try {
                Control.Receive();
                Thread.sleep(Const.JUDGESLEEP);
            } catch (Exception e) {
            }
        }
    }
}
