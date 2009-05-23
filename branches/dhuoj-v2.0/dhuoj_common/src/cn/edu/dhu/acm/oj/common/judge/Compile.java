package cn.edu.dhu.acm.oj.common.judge;

import cn.edu.dhu.acm.oj.common.bean.*;
import cn.edu.dhu.acm.oj.common.config.*;
import java.io.*;
import java.util.Scanner;

public class Compile {

    public Compile(RunBean rb, EnvironmentBean eb) {
        runbean = rb;
        envbean = eb;
    }

    private String getError(InputStream err) {
        Scanner scan = new Scanner(err);
        StringBuffer ss = new StringBuffer();

        try {
            for (int i = 0; scan.hasNextLine() && i < 10; i++) {
                ss.append(scan.nextLine());
                ss.append("\n");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                err.close();
                scan.close();
            } catch (Exception e) {
            }
        }
        return ss.toString();
    }

    public boolean doit() {
        boolean compileresult = false;
        String lan = cn.edu.dhu.acm.oj.common.config.Const.LANGUAGE[runbean.getLanguage()];
        String filename = Const.COMPILENAME + envbean.getFormerSuffix(lan);
        File file = new File(filename);
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(runbean.getSourceCode());
            out.close();

            String commandline = envbean.getCompileCmd(lan, Const.COMPILENAME, Const.COMPILENAME);
            System.out.println("Compile : " + commandline);
            Watcher watch = new Watcher(commandline);
            Thread t = new Thread(watch);
            t.start();

            //set compile time
            try {
                if (runbean.getLanguage() == Const.JAVA) {
                    t.join(Const.COMPILE_TIME * Const.JAVA_LIMIT);
                } else {
                    t.join(Const.COMPILE_TIME);
                }
            } catch (Exception e) {
            }

            Process p = watch.getProcess();
            p.destroy();

            try {
                if (p.exitValue() != 0) {
                    runbean.setCompileInfo(getError(p.getErrorStream()));
                    compileresult = false;
                } else {
                    runbean.setCompileInfo("Compile successfully");
                    compileresult = true;
                }
            } catch (Exception e) {
                runbean.setCompileInfo("Compile no exitValue!\nMay your compile TLE!");
                compileresult = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            compileresult = false;
            runbean.setCompileInfo("Compile unknow error");
        } finally {
            file.delete();
        }
        return compileresult;
    }
    private RunBean runbean;
    private EnvironmentBean envbean;
}
