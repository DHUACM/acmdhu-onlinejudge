/*
 * RunSubmit.java
 * 
 * Created on 2007-6-5, 9:30:30
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package client.thread;

import client.*;

/**
 *
 * @author 孙辞�? */
public class RunSubmit implements Runnable {

    String paperNo;
    int problemNo;
    String problemName;

    public RunSubmit(String paperNo, int problemNo, String name) {
        this.paperNo = paperNo;
        this.problemNo = problemNo;
        this.problemName = name;
    }

    public void run() {
        Control.WsSubmit(paperNo, problemNo,problemName);
    }
}

