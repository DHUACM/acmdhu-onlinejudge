package cn.edu.dhu.acm.oj.judge.server.bean;

public class JudgeBean {

    private String ip;
    private int port;
    private long judges = 0;
    private int retry = 0;

    public void incRetry() {
        retry++;
    }

    public void incJudges() {
        judges++;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public long getJudges() {
        return judges;
    }

    public int getRetry() {
        return retry;
    }
}
