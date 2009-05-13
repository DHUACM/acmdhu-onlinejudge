// Decompiled by Jad v1.5.7f. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3)
// Source File Name:   RunProgram.java
package judge;

import java.io.*;

// Referenced classes of package com.sjn:
//            ReadInputStream, TimeoutKill
public class RunProgram extends Thread {

    public RunProgram() {
        runresult = false;
        runinfo = "Runtime Error";
        commandline = "";
        input = "";
        limitedtime = 0;
        istle = false;
        sourcelanguage = "";
    }

    public void destroyPro() {
        try {
            runresult = false;
            runinfo = "Time Limit Exceeded";
            pro.destroy();
            System.out.println("pro.destroy();");
            pin.close();
            System.out.println("pin.close();");
            pout.close();
            System.out.println("pout.close();");
            perr.close();
            System.out.println("perr.close();");
            System.gc();
        } catch (IOException IOE) {
            System.out.println(IOE.toString());
        }
    }

    public void setLanguage(String l) {
        sourcelanguage = l;
    }

    public void setInputData(String s) {
        input = s;
    }

    public void setRunCommand(String command) {
        commandline = command;
    }

    public void setTimelimited(int time) {
        limitedtime = time;
        if (sourcelanguage.compareToIgnoreCase("Java") == 0) {
            limitedtime *= 5;
        }
    }

    private void InputData(OutputStream os)
            throws IOException {
        os.write(input.getBytes());
        os.close();
    }

    public void run() {
        try {
            pro = Runtime.getRuntime().exec(commandline);
            long tbegin = System.currentTimeMillis();
            pin = pro.getOutputStream();
            pout = pro.getInputStream();
            perr = pro.getErrorStream();
            errris = new ReadInputStream(perr);
            outris = new ReadInputStream(pout);
            tk = new TimeoutKill(limitedtime);
            tk.setTestThread(this);
            errris.start();
            outris.start();
            tk.start();
            InputData(pin);
            try {
                pro.waitFor();
            } catch (ArithmeticException AE) {
                System.out.println(AE.toString());
            }
            long tend = System.currentTimeMillis();
            if (pro.exitValue() == 0) {
                tk.stop();
//                System.out.print("Your program has run ");
//                System.out.print((float)(tend - tbegin) / (float)1000);
//                System.out.println(" seconds.");
                runresult = true;
                runinfo = "Run Successfully";
            } 
            else {
                tk.stop();
                runresult = false;
                runinfo = "Runtime Error";
            }
            pin.close();
            //System.out.println("pin.close();");
            pro.destroy();
            System.out.println("Run Done!");
        } catch (Exception E) {
            System.out.println("Exception!!!!!!!!!!!!!!");
            tk.stop();
            runresult = false;
            runinfo = "Runtime Error";
            stop();
            System.out.println(E.toString());
        }
    }

    public boolean getRunResult() {
        return runresult;
    }

    public String getRunInfo() {
        return runinfo;
    }

    public String getOutputData() {
        outris.stop();
        return outris.getMessage();
    }

    public String getOutputError() {
        errris.stop();
        return errris.getMessage();
    }

    public boolean isTLE() {
        return tk.isTLE();
    }
    private boolean runresult;
    private String runinfo;
    private Process pro;
    private OutputStream pin;
    private InputStream perr;
    private InputStream pout;
    private ReadInputStream errris;
    private ReadInputStream outris;
    private String commandline;
    private String input;
    private int limitedtime;
    private boolean istle;
    private String sourcelanguage;
    private TimeoutKill tk;
}
