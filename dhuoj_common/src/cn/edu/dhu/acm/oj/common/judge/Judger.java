package cn.edu.dhu.acm.oj.common.judge;

import cn.edu.dhu.acm.oj.common.bean.*;
import cn.edu.dhu.acm.oj.common.config.*;

public class Judger {

    public Judger(RunBean rb, EnvironmentBean eb) {
        rbean = rb;
        envbean = eb;
    }

    public void Run() {
        Run rr = new Run(rbean, envbean);
        rr.start();
        try {
            rr.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Check() {
        if (rbean.getResult().equals(Const.QUEUE)) {
            CheckAnswer ca = new CheckAnswer();
            ca.setAnswer(rbean.getOut());
            ca.setStandarAnswer(rbean.getAns());
            ca.AnswerCheck();
            rbean.setResult(ca.getCheckInfo());
            rbean.setPercent(ca.getPercent());
        }
    }

    public boolean Compile() {
        Compile cc = new Compile(rbean, envbean);
        compile = cc.doit();
        compileinfo = cc.getCompileinfo();
        return compile;
    }

    public String getCompileinfo() {
        return compileinfo;
    }
    private RunBean rbean;
    private EnvironmentBean envbean;
    private boolean compile;
    private String compileinfo;
}
