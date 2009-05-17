/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package judge;

import judge.bean.*;
import config.*;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Administrator
 */
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
        String lan = config.Const.LANGUAGE[runbean.getLanguage()];
        String filename = Const.COMPILENAME + envbean.getFormerSuffix(lan);
        File file = new File(filename);
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(runbean.getCode());
            out.close();

            String commandline = envbean.getCompileCom(lan, Const.COMPILENAME, Const.COMPILENAME);
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
                    throw new Exception();
                } else {
                    compileinfo = "Compile successfully\n";
                    compileresult = true;
                }
            } catch (Exception e) {
                compileinfo = getError(p.getErrorStream());
                compileresult = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            compileresult = false;
        } finally {
            file.delete();
        }
        return compileresult;
    }

    public String getCompileinfo() {
        return compileinfo;
    }
    private RunBean runbean;
    private EnvironmentBean envbean;
    private String compileinfo;
}
