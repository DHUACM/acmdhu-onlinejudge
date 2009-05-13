/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.thread;

import client.*;
import config.Const;

/**
 *
 * @author Administrator
 */
public class RunQuerySumbmitStatus implements Runnable {

    Integer queryID;

    public RunQuerySumbmitStatus(Integer qid) {
        queryID = qid;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(Const.SLEEPTIME);
                int ans= Control.Query(queryID);
                System.out.println(queryID+" : "+ans);
                if(ans>1) break;
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
}