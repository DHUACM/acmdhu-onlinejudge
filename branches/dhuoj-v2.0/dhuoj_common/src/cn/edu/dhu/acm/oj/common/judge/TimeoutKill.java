package cn.edu.dhu.acm.oj.common.judge;

class TimeoutKill extends Thread {

    public TimeoutKill(long t) {
        rp = null;
        timelimited = t * (long) 1000;
        tle = false;
    }

    public void setTestThread(Run r) {
        rp = r;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(timelimited);
            if (rp.isAlive()) {
                tle = true;
                rp.destroy();
                rp.stop();
            }
        } catch (InterruptedException IEX) {
            System.out.println(IEX.toString());
        }
    }

    public boolean isTLE() {
        return tle;
    }
    private Thread rp;
    private long timelimited;
    private boolean tle;
}
