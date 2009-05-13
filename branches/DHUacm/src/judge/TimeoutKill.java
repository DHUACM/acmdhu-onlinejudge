// Decompiled by Jad v1.5.7f. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3)
// Source File Name:   TimeoutKill.java
package judge;

import java.io.PrintStream;

// Referenced classes of package com.sjn:
//            RunProgram
class TimeoutKill extends Thread {

    public TimeoutKill(long t) {
        rp = null;
        timelimited = t * (long) 1000;
        timeoutresult = "";
        timeoutinfo = "";
        tle = false;
    }

    public void setTestThread(RunProgram r) {
        rp = r;
    }

    public void run() {
        try {
            //System.out.println("TLE Test Began !!!\n");
            //System.out.println("sleeping...");
            Thread.sleep(timelimited);
            //System.out.println("wake up...");
            //System.out.println(rp.isAlive());
            if (rp.isAlive()) {
                tle = true;
                //System.out.println("rp.destroyPro");
                rp.destroyPro();
                rp.stop();
                //System.out.println("Time Limit Exceeded\n");
                timeoutresult = "No ... TLE!!!";
                timeoutinfo = "Time Limit Exceeded";
            } else {
                timeoutresult = "TLE Test Yes ... OK!!!";
                timeoutinfo = "Your program have run successfully!\n";
            }
            //System.out.print("tk.isTLE = ");
            //System.out.println(tle);
            //System.out.println("System.gc();");
            System.gc();
        //System.out.println("TLE Test Finished !!!\n");
        } catch (InterruptedException IEX) {
            System.out.println(IEX.toString());
        }
    }

    public boolean isTLE() {
        return tle;
    }

    public String getResult() {
        System.out.println(timeoutresult);
        return timeoutresult;
    }

    public String getInfo() {
        return timeoutinfo;
    }
    private RunProgram rp;
    private long timelimited;
    private String timeoutresult;
    private String timeoutinfo;
    private boolean tle;
}
