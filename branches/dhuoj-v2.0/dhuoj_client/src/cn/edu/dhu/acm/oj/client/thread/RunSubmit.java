package cn.edu.dhu.acm.oj.client.thread;

import cn.edu.dhu.acm.oj.client.*;

public class RunSubmit implements Runnable {

    int problemNo;
    String problemName;

    public RunSubmit(int problemNo, String name) {
        this.problemNo = problemNo;
        this.problemName = name;
    }

    public void run() {
        Control.WsSubmit(problemNo, problemName);
    }
}

