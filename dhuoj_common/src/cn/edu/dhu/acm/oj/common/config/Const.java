package cn.edu.dhu.acm.oj.common.config;

public class Const {

    public static final short WAIT = 0;
    public static final short QUEUE = 1;
    public static final short AC = 2;
    public static final short WA = 3;
    public static final short TLE = 4;
    public static final short MLE = 5;
    public static final short PE = 6;
    public static final short RE = 7;
    public static final short CE = 8;
    
    /**VERDICT: WAIT=0,QUEUE=1,AC=2,WA=3,TLE=4,MLE=5,PE=6,RE=7,CE=8*/
    public static final String[] VERDICT = {"WAIT", "QUEUE", "Accepted", "Wrong Answer",
    "Time Limit Exceeded", "Memory Limit Exceeded", "Presentation Error", "Runtime Error", "Compile Error"};
    /**LANGUAGE: ""=0,C=1,CPP=2,JAVA=3*/
    public static final String[] LANGUAGE = {"PASCAL", "C", "CPP", "JAVA"};

    /**Language to byte*/
    public static final byte getLanguageByte(String l) {
        byte ans = 0;
        for (byte i = 0; i < LANGUAGE.length; i++) {
            if (l.equalsIgnoreCase(LANGUAGE[i])) {
                ans = i;
                break;
            }
        }
        return ans;
    }
    public static final byte C = 1;
    public static final byte CPP = 2;
    public static final byte JAVA = 3;
    public static final int COMPILE_TIME = 2000;
    /**The Time for JAVA run*/
    public static final int JAVA_LIMIT = 3;
    public static final String JVM = "D:\\dhuoj\\compiler\\jdk\\jre\\bin\\java-rmi.exe";
    public static final String USER = "administrator";
    public static final String PASS = "sunci777";
    public static final String TOKEN_SERVER = "9c6300202e877f4f7444f51aba6143d6";
    public static final String TOKEN_CLIENT = "���Ԫ";
    public static final String SERVER_IP = "127.0.0.1";
    public static final int SERVER_RCV_SOCKET = 58001;
    public static final int CLIENT_RCV_SOCKET = 58002;
    public static final String DB_URL = "jdbc:mysql://acm.dhu.edu.cn:3306/dhuoj?useUnicode=true&characterEncoding=utf8&autoReconnect=true";
    public static final String DB_USER = "root";
    public static final String DB_PASS = "123456";
    /**The code Font*/
    public static final java.awt.Font font = new java.awt.Font("DialogInput", 0, 13);
    /**The Time status reflash*/
    public static final long SLEEPTIME = 10000;
    /**Output File Size*/
    public static final long FILEMAXSIZE = 512L;
    /**Compile Name*/
    public static final String COMPILENAME = "Main";
}
