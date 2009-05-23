package cn.edu.dhu.acm.oj.common.judge;

public class Watcher implements Runnable {

    private Process process = null;
    private String compStr;

    public Watcher(String s) {
        compStr = s;
    }

    public void run() {
        try {
            process = Runtime.getRuntime().exec(compStr);
            process.waitFor();
        } catch (Exception e) {
        }
    }

    public Process getProcess() {
        return process;
    }
}
