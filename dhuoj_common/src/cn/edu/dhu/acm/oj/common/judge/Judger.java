package cn.edu.dhu.acm.oj.common.judge;

import cn.edu.dhu.acm.oj.common.bean.*;
import cn.edu.dhu.acm.oj.common.config.*;

public class Judger {

    public Judger(RunBean rb, EnvironmentBean eb) {
        rbean = rb;
        envbean = eb;
    }

    public String getCompileinfo() {
        return compileinfo;
    }

    public boolean Compile() {
        Compile cc = new Compile(rbean, envbean);
        compile = cc.doit();
        if (compile) {
            CheckMaliciousCode cmc = new CheckMaliciousCode(rbean);
            compile = cmc.checkKeyWord();
        }
        compileinfo = rbean.getCompileInfo();
        return compile;
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
        if (rbean.getResult() == Const.QUEUE) {
            CheckAnswer ca = new CheckAnswer();
            ca.setAnswer(rbean.getOutput());
            ca.setStandarAnswer(rbean.getStdAns());
            ca.AnswerCheck();
            rbean.setResult(ca.getVerdict());
            rbean.setPercent(ca.getPercent());
        }
    }
    private RunBean rbean;
    private EnvironmentBean envbean;
    private boolean compile;
    private String compileinfo;
}
