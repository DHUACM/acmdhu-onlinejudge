package cn.edu.dhu.acm.oj.common.judge;

import cn.edu.dhu.acm.oj.common.bean.*;
import cn.edu.dhu.acm.oj.common.config.*;
import java.io.*;

public class Compile {

    public Compile(RunBean rb, EnvironmentBean eb) {
        runbean = rb;
        envbean = eb;
        path = envbean.getSource();
    }

    public boolean doit() {
        boolean compileresult = false;
        String lan = cn.edu.dhu.acm.oj.common.config.Const.LANGUAGE[runbean.getLanguage()];
        String filename = Const.COMPILENAME + envbean.getFormerSuffix(lan);
        File file = new File(path + filename);
        try {

            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(runbean.getSourceCode());
            out.close();

            String commandline = envbean.getCompileCmd(lan, Const.COMPILENAME, Const.COMPILENAME);
            System.out.println("Compile : " + commandline);

            Process pro = Runtime.getRuntime().exec(commandline);
            outstream = pro.getInputStream();
            errstream = pro.getErrorStream();
            errris = new ReadInputStream(errstream);
            outris = new ReadInputStream(outstream);
            errris.start();
            outris.start();
            pro.waitFor();
            errris.join();
            outris.join();

            try {
                if (pro.exitValue() != 0) {
                    throw new Exception();
                } else {
                    runbean.setCompileInfo("Compile successfully!");
                    compileresult = true;
                }
            } catch (Exception e) {
                String info = errris.getMessage() + outris.getMessage();
                if (info.equals("")) {
                    info = "Compile no exitValue!";
                }
                runbean.setCompileInfo(info);
                compileresult = false;
            }
            pro.destroy();
        } catch (Exception e) {
            System.out.println(e.toString());
            compileresult = false;
            runbean.setCompileInfo("No Compiler found!\nClick MenuItem Tool to Set your Compile Path!");
        } finally {
            file.delete();
        }
        if (!compileresult) {
            runbean.setResult(Const.CE);
        }
        return compileresult;
    }
    private RunBean runbean;
    private EnvironmentBean envbean;
    private String path;
    private InputStream errstream;
    private InputStream outstream;
    private ReadInputStream errris;
    private ReadInputStream outris;
}
