package judge;

import judge.bean.*;
import config.*;
import java.io.*;

public class Run extends Thread {

    public Run(RunBean rb, EnvironmentBean eb) {
        runbean = rb;
        String lan = Const.LANGUAGE[rb.getLanguage()];
        commandline = eb.getRunCmd(lan, Const.COMPILENAME);
    }

    private void InputData(OutputStream os)
            throws IOException {
        os.write(runbean.getIn().getBytes());
        os.close();
    }

    @Override
    public void destroy() {
        try {
            runbean.setResult(Const.TLE);
            tend = System.currentTimeMillis();
            runbean.setTimeused(tend - tbegin);
            pro.destroy();
            pin.close();
            pout.close();
            perr.close();
        } catch (IOException IOE) {
            System.out.println(IOE.toString());
        }
    }

    @Override
    public void run() {
        try {
            tk = new TimeoutKill(runbean.getTIME_LIMIT());
            System.out.println("Run : " + commandline);
            pro = Runtime.getRuntime().exec(commandline);
            tbegin = System.currentTimeMillis();
            pin = pro.getOutputStream();
            pout = pro.getInputStream();
            perr = pro.getErrorStream();
            errris = new ReadInputStream(perr);
            outris = new ReadInputStream(pout);

            tk.setTestThread(this);
            errris.start();
            outris.start();
            tk.start();

            InputData(pin);
            pro.waitFor();
            outris.join();
            errris.join();

            tend = System.currentTimeMillis();
            runbean.setTimeused(tend - tbegin);
            int exitValue = pro.exitValue();
            if (exitValue == 0) {
                tk.stop();
                runbean.setOut(outris.getMessage());
            } else {
                tk.stop();
                runbean.setResult(Const.RE);
                runbean.setOut(outris.getMessage());
            }
            pin.close();
            pro.destroy();
            System.out.println("Run Done!");
        } catch (Exception E) {
            tk.stop();
            runbean.setResult(Const.RE);
            System.out.println(E.toString());
        }
    }

    public RunBean getRunBean() {
        return runbean;
    }
    private RunBean runbean;
    private Process pro;
    private OutputStream pin;
    private InputStream perr;
    private InputStream pout;
    private ReadInputStream errris;
    private ReadInputStream outris;
    private String commandline;
    private TimeoutKill tk;
    private long tbegin;
    private long tend;
}
