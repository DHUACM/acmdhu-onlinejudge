// Decompiled by Jad v1.5.7f. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3)
// Source File Name:   JudgeBean.java
package judge;

import problem.*;
import java.util.List;
import config.*;

import paper.*;
// Referenced classes of package com.sjn:
//            ResultBean, CompileCode, RunProgram, CheckAnswer
public class JudgeBean {

    public JudgeBean() {
        rb = new ResultBean();
        iscompiled = false;
        percent = 0;
        runcommandline = "";
        testinput = "";
    }

    public void setEnvironmentBean(EnvironmentBean e) {
        eb = e;
    }

    public void setSolutionBean(SolutionBean s) {
        sb = s;
    }

    public void setTestDataBean(TestDataBean t) {
        tdb = t;
    }

    public ResultBean getResultBean() {
        return rb;
    }

    public String getJudgeOutput() {
        return tdb.getTestOutput();
    }

    public void setTestInput(String input) {
        testinput = input;
        String temp = "";
        int len = testinput.length();
        if (!System.getProperty("line.separator").equals("\r\n")) {
            for (int i = 0; i < len; i++) {
                if (i >= len - 1 || testinput.charAt(i) != '\r' || testinput.charAt(i + 1) != '\n') {
                    temp = String.valueOf(temp) + String.valueOf(testinput.charAt(i));
                }
            }

        } else {
            for (int i = 0; i < len; i++) {
                if (i > 0 && testinput.charAt(i) == '\n' && testinput.charAt(i - 1) != '\r') {
                    temp = String.valueOf(String.valueOf(temp)).concat("\r\n");
                } else {
                    temp = String.valueOf(temp) + String.valueOf(testinput.charAt(i));
                }
            }
        }
        testinput = temp;
    }

    public void judgeCompile() {
        CompileCode cc = new CompileCode();
        cc.setCode(sb.getSourceCode());
        cc.setSaveFilePath(eb.getSource());
        cc.setFilename(sb.getFilename());
        cc.setSourceFileSuffix(eb.getFormerSuffix(sb.getLanguage()));
        cc.setExeFilePath(eb.getTarget());
        cc.saveCodeFile();
        List l = eb.getUsage(sb.getLanguage());
        String commandline = "";
        for (int i = 1; i < l.size() - 1; i++) {
            if (l.get(i).toString().compareToIgnoreCase("[Element: <Path/>]") == 0) {
                commandline = String.valueOf(commandline) + String.valueOf(eb.getPath(sb.getLanguage()));
                continue;
            }
            if (l.get(i).toString().compareToIgnoreCase("[Element: <Cmd/>]") == 0) {
                commandline = String.valueOf(commandline) + String.valueOf(eb.getCmd(sb.getLanguage()));
                continue;
            }
            if (l.get(i).toString().compareToIgnoreCase("[Element: <Source/>]") == 0) {
                commandline = String.valueOf(commandline) + String.valueOf(eb.getSource());
                continue;
            }
            if (l.get(i).toString().compareToIgnoreCase("[Element: <SName/>]") == 0) {
                commandline = String.valueOf(commandline) + String.valueOf(sb.getFilename());
                commandline = String.valueOf(commandline) + String.valueOf(eb.getFormerSuffix(sb.getLanguage()));
                continue;
            }
            if (l.get(i).toString().compareToIgnoreCase("[Element: <Target/>]") == 0) {
                commandline = String.valueOf(commandline) + String.valueOf(eb.getTarget());
                continue;
            }
            if (l.get(i).toString().compareToIgnoreCase("[Element: <TName/>]") == 0) {
                commandline = String.valueOf(commandline) + String.valueOf(sb.getFilename());
                commandline = String.valueOf(commandline) + String.valueOf(eb.getLatterSuffix(sb.getLanguage()));
            } else {
                String temp = l.get(i).toString();
                int j = temp.length();
                temp = temp.substring(7, j - 1);
                commandline = String.valueOf(commandline) + String.valueOf(temp);
            }
        }

        System.out.println(commandline);
        cc.setErrorType(Integer.parseInt(eb.getErrorType(sb.getLanguage())));
        cc.setCompileCommand(commandline);
        cc.runcompile();
        rb.setCompileResult(cc.getCompileResult());
        rb.setCompileInfo(cc.getCompileInfo());
        iscompiled = true;
        System.gc();
    }

    private String getRuncommand() {
        List l = eb.getCommand(sb.getLanguage());
        runcommandline = "";
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).toString().compareToIgnoreCase("[Element: <Target/>]") == 0) {
                runcommandline = String.valueOf(runcommandline) + String.valueOf(eb.getTarget());
                continue;
            }
            if (l.get(i).toString().compareToIgnoreCase("[Element: <TName/>]") == 0) {
                runcommandline = String.valueOf(runcommandline) + String.valueOf(sb.getFilename());
                continue;
            }
            if (l.get(i).toString().compareToIgnoreCase("[Element: <Path/>]") == 0) {
                runcommandline = String.valueOf(runcommandline) + String.valueOf(eb.getPath(sb.getLanguage()));
            } else {
                String temp = l.get(i).toString();
                int j = temp.length();
                temp = temp.substring(7, j - 1);
                runcommandline = String.valueOf(runcommandline) + String.valueOf(temp);
            }
        }

        System.out.println(runcommandline);
        return runcommandline;
    }

    public void judgeTestRun() {
        if (!iscompiled) {
            judgeCompile();
            if (!rb.getCompileResult()) {
                rb.setRunOutputError("Compile Error");
                rb.setRunResult(false);
                return;
            }
        }
        RunProgram rp = new RunProgram();
        rp.setInputData(testinput);
        rp.setRunCommand(getRuncommand());
        rp.setTimelimited(tdb.getTimeLimit());
        rp.setLanguage(sb.getLanguage());
        rp.start();
        while (rp.isAlive()) {
            Thread t = new Thread();
            try {
                Thread.sleep(100L);
            } catch (Exception E) {
                System.out.println(E.toString());
            }
        }
        try {
            Thread t = new Thread();
            Thread.sleep(100L);
        } catch (Exception E) {
            System.out.println(E.toString());
        }
        if (!rp.getRunResult()) {
            rb.setRunInfo(rp.getRunInfo());
            rb.setRunOutputError(rp.getRunInfo());
            rb.setRunResult(false);
        } else {
            rb.setRunInfo(rp.getRunInfo());
            rb.setRunResult(true);
        }
        String str = rp.getOutputData();
        if (rp.getOutputData().length() > 0x200000) {
            rb.setRunOutputData(str.substring(str.length() - 0x200000, str.length()));
        } else {
            rb.setRunOutputData(str);
        }
        System.gc();
    }

    private void judgeRun() {
        if (!iscompiled) {
            judgeCompile();
            if (!rb.getCompileResult()) {
                rb.setRunOutputError("Compile Error");
                rb.setRunResult(false);
                return;
            }
        }
        RunProgram rp = new RunProgram();
        rp.setInputData(tdb.getTestInput());
        rp.setRunCommand(getRuncommand());
        rp.setTimelimited(tdb.getTimeLimit());
        rp.setLanguage(sb.getLanguage());
        rp.start();
        while (rp.isAlive()) {
            Thread t = new Thread();
            try {
                Thread.sleep(100L);
            } catch (Exception E) {
                System.out.println(E.toString());
            }
        }
        try {
            Thread t = new Thread();
            Thread.sleep(100L);
        } catch (Exception E) {
            System.out.println(E.toString());
        }
        if (!rp.getRunResult()) {
            rb.setRunInfo(rp.getRunInfo());
            rb.setRunOutputError(rp.getRunInfo());
            rb.setRunResult(false);
        } else {
            rb.setRunInfo(rp.getRunInfo());
            rb.setRunResult(true);
        }
        rb.setRunOutputData(rp.getOutputData());
        System.gc();
    }

    public void setProblemArchive(ProblemArchiveBean x) {
        pab = x;
    }

    public void judgeCheck() {
        String temp = pab.getJudgeType();
        if (temp.equals("Single Case")) {
            if (!iscompiled) {
                judgeCompile();
                if (!rb.getCompileResult()) {
                    rb.setRunOutputError("Compile Error");
                    rb.setRunResult(false);
                    return;
                }
            }
            judgeRunandCheckCase();
            if (percent == 100) {
                rb.setCheckInfo("Accepted");
            } else {
                rb.setCheckInfo("Wrong Answer");
            }
        } else {
            judgeRun();
            if (!rb.getCompileResult()) {//compile error
                rb.setCheckResult(false);
                rb.setCheckInfo("Compile Error");
                rb.setCheckPercent(0);
                return;
            }
            if (!rb.getRunResult()) {//runtime error?~
                rb.setCheckResult(false);
                rb.setCheckInfo(rb.getRunOutputError());
                rb.setCheckPercent(0);
                return;
            } else {
                CheckAnswer ca = new CheckAnswer();
                ca.setAnswer(getResultBean().getRunOutputData());
                ca.setStandarAnswer(tdb.getTestOutput());
                ca.AnswerCheck();
                rb.setCheckResult(ca.getCheckResult());
                rb.setCheckInfo(ca.getCheckInfo());
                rb.setFirstWrongplace(ca.getFirstWrongplace());
                if (rb.getCheckInfo().equals("Accepted")) {
                    rb.setCheckPercent(100);
                } else {
                    //rb.setCheckPercent(100);
                    judgeRunandCheckCase();
                }
                return;
            }

        }
    }

    public void judgeRunandCheckCase() {
        int casenum = tdb.getTestCaseCount();
        //ArrayList accept = new ArrayList();
        int sum = 0;
        for (int i = 0; i < casenum; i++) {
            RunProgram rp = new RunProgram();
            try {
                rp.setInputData(tdb.getTestInput(i));
            //System.out.println(tdb.getTestInput(i));
            } catch (Exception E) {
                System.out.println(E.toString());
            }
            rp.setRunCommand(getRuncommand());
            rp.setTimelimited(tdb.getTimeLimit());
            rp.setLanguage(sb.getLanguage());
            rp.start();
            while (rp.isAlive()) {
                Thread t = new Thread();
                try {
                    Thread.sleep(100L);
                } catch (Exception E) {
                    System.out.println(E.toString());
                }
            }
            try {
                Thread t = new Thread();
                Thread.sleep(100L);
            } catch (Exception E) {
                System.out.println(E.toString());
            }

            CheckAnswer ca = new CheckAnswer();
            ca.setAnswer(rp.getOutputData());
            //System.out.println("rp "+rp.getOutputData());
            try {
                ca.setStandarAnswer(tdb.getTestOutput(i));
            //System.out.println("tdb "+tdb.getTestOutput(i));
            } catch (Exception E) {
                System.out.println(E.toString());
            }
            ca.AnswerCheck();
            if (ca.getCheckResult()) {
                sum++;
            //accept.add(Boolean.TRUE);
            //System.out.println("Case ");
            //System.out.print(i);
            //System.out.print("...OK!");
            } else {
                //accept.add(Boolean.FALSE);
                //System.out.println("Case ");
                //System.out.print(i);
                //System.out.print("...NO!");
            }
        }

        percent = (int) ((double) sum / (double) casenum * 100.0);
        rb.setCheckPercent(percent);

        System.out.println("percent = " + percent);
    }

    public static void main() {
        JudgeBean jb = new JudgeBean();
        jb.setEnvironmentBean(new EnvironmentBean("Environment.xml"));
        PaperBean pb = new PaperBean();
        try {
            pb.unmarshal("paper.xml");
        } catch (Exception e) {
            ;
        }
        jb.setSolutionBean(pb.getProblemAt(0).getSolution());
        jb.setTestDataBean(pb.getProblemAt(0).getTestData());
        jb.judgeCheck();

    }
    private SolutionBean sb;
    private TestDataBean tdb;
    private EnvironmentBean eb;
    private ResultBean rb;
    private boolean iscompiled;
    private String runcommandline;
    private int percent;
    private String testinput;
    private ProblemArchiveBean pab;
}
