package cn.edu.dhu.acm.oj.judge;



import java.io.*;
import java.util.*;

public class LogoWriter {

    /** Creates a new instance of LogoWriter */
    public LogoWriter() {
    }

    public static void judgeLogo(String app) {
        try {
            FileWriter fw = new FileWriter("Judge.log", true);
            Calendar cal = Calendar.getInstance();
            String head = "Real world time " + cal.getTime();
            fw.write("---------------------------" + head + "\n"  + app + "\n");
            fw.close();
        } catch (Exception ep) {
            errorLogo("write log file err  " + ep.toString());
        }
    }

    public static void errorLogo(String app) {
        try {
            FileWriter fw = new FileWriter("error.log", true);
            Calendar cal = Calendar.getInstance();
            String head = "Real world time " + cal.getTime();
            fw.write("---------------------------" + head + "\n"  + app + "\n");
            fw.close();
        } catch (Exception ep) {
        }
    }
}
