package cn.edu.dhu.acm.oj.common.bean;

import cn.edu.dhu.acm.oj.common.config.Const;

public class RunBean {

    private short result;
    private String sourceCode;
    private String compileInfo;
    private long timeUsed;
    private long memoryUsed;
    private byte language;
    private long timeLimit;
    private long memoryLimit;
    private long caseTimeLimit;
    private String input;
    private String output;
    private String stdAns;
    private boolean isSpecial;
    private String specialCode;
    private int percent;

    public RunBean() {
        result = Const.QUEUE;
        isSpecial = false;
    }

    public long getCaseTimeLimit() {
        return caseTimeLimit;
    }

    public void setCaseTimeLimit(long caseTimeLimit) {
        this.caseTimeLimit = caseTimeLimit;
    }

    public String getCompileInfo() {
        return compileInfo;
    }

    public void setCompileInfo(String compileInfo) {
        this.compileInfo = compileInfo;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public boolean isIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(boolean isSpecial) {
        this.isSpecial = isSpecial;
    }

    public byte getLanguage() {
        return language;
    }

    public void setLanguage(byte language) {
        this.language = language;
    }

    public long getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(long memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public long getMemoryUsed() {
        return memoryUsed;
    }

    public void setMemoryUsed(long memoryUsed) {
        this.memoryUsed = memoryUsed;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public short getResult() {
        return result;
    }

    public void setResult(short result) {
        this.result = result;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getSpecialCode() {
        return specialCode;
    }

    public void setSpecialCode(String specialCode) {
        this.specialCode = specialCode;
    }

    public String getStdAns() {
        return stdAns;
    }

    public void setStdAns(String stdAns) {
        this.stdAns = stdAns;
    }

    public long getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(long timeLimit) {
        this.timeLimit = timeLimit;
    }

    public long getTimeUsed() {
        return timeUsed;
    }

    public void setTimeUsed(long timeUsed) {
        this.timeUsed = timeUsed;
    }
}
