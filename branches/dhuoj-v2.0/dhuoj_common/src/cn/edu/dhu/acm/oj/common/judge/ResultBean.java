// Decompiled by Jad v1.5.7f. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3)
// Source File Name:   ResultBean.java
package cn.edu.dhu.acm.oj.common.judge;

import java.util.ArrayList;

public class ResultBean {

    public ResultBean() {
        compileresult = false;
        compileinfo = "";
        compileerrorplace = 0;
        runresult = false;
        runinfo = "Runtime Error";
        runoutputdata = "";
        runoutputerror = "Runtime Error";
        checkresult = false;
        checkinfo = "Runtime Error";
        checkpercent = 0;
        accept = new ArrayList();
        firstwrongplace = 0;
    }

    public boolean getCompileResult() {
        return compileresult;
    }

    public int getFirstCompileErrorPlace() {
        return compileerrorplace;
    }

    public String getCompileInfo() {
        return compileinfo;
    }

    public boolean getRunResult() {
        return runresult;
    }

    public String getRunInfo() {
        return runinfo;
    }

    public String getRunOutputData() {
        return runoutputdata;
    }

    public String getRunOutputError() {
        return runoutputerror;
    }

    public boolean getCheckResult() {
        return checkresult;
    }

    public String getCheckInfo() {
        return checkinfo;
    }

    public int getCheckPercent() {
        return checkpercent;
    }

    public ArrayList getCheckCaseAccept() {
        return accept;
    }

    public int getFirstWrongplace() {
        return firstwrongplace;
    }

    public void setCompileResult(boolean result) {
        compileresult = result;
    }

    public void setCompileInfo(String info) {
        compileinfo = info;
    }

    public void setFirstCompileErrorPlace(int place) {
        compileerrorplace = place;
    }

    public void setRunResult(boolean result) {
        runresult = result;
    }

    public void setRunInfo(String info) {
        runinfo = info;
    }

    public void setRunOutputData(String out) {
        runoutputdata = out;
    }

    public void setRunOutputError(String err) {
        runoutputerror = err;
    }

    public void setCheckResult(boolean result) {
        checkresult = result;
    }

    public void setCheckInfo(String info) {
        checkinfo = info;
    }

    public void setCheckPercent(int per) {
        checkpercent = per;
    }

    public void setCheckCaseAccept(ArrayList a) {
        accept = a;
    }

    public void setFirstWrongplace(int place) {
        firstwrongplace = place;
    }
    private boolean compileresult;
    private String compileinfo;
    private int compileerrorplace;
    private boolean runresult;
    private String runinfo;
    private String runoutputdata;
    private String runoutputerror;
    private boolean checkresult;
    private String checkinfo;
    private int checkpercent;
    private ArrayList accept;
    private int firstwrongplace;
}
