package judge.bean;

import config.*;

public class RunBean {

    private String result = Const.QUEUE;
    private String code = null;
    private long timeused = 0;
    private long memused = 0;
    private byte language;
    private String sub_time = null;
    private long TIME_LIMIT = 0;
    private long MEM_LIMIT = 0;
    private boolean isspecial = false;
    /**STDin*/
    private String in;
    /**The output of the run of code*/
    private String out;
    /**STDans*/
    private String ans;
    private int percent;

    public long getMEM_LIMIT() {
        return MEM_LIMIT;
    }

    public void setMEM_LIMIT(long MEM_LIMIT) {
        this.MEM_LIMIT = MEM_LIMIT;
    }

    public long getTIME_LIMIT() {
        return TIME_LIMIT;
    }

    public void setTIME_LIMIT(long TIME_LIMIT) {
        this.TIME_LIMIT = TIME_LIMIT;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIn() {
        return in;
    }

    public void setIn(String in) {
        this.in = in;
    }

    public boolean isIsspecial() {
        return isspecial;
    }

    public void setIsspecial(boolean isspecial) {
        this.isspecial = isspecial;
    }

    public byte getLanguage() {
        return language;
    }

    public void setLanguage(byte language) {
        this.language = language;
    }

    public long getMemused() {
        return memused;
    }

    public void setMemused(long memused) {
        this.memused = memused;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSub_time() {
        return sub_time;
    }

    public void setSub_time(String sub_time) {
        this.sub_time = sub_time;
    }

    public long getTimeused() {
        return timeused;
    }

    public void setTimeused(long timeused) {
        this.timeused = timeused;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}
