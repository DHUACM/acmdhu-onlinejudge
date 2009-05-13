package config;

/**
 * 定义程序使用的各种常量
 * @author
 */
public class Const {
    /**VERDICT: WAIT=0,QUEUE=1,AC=2,WA=3,TLE=4,MLE=5,PE=6,RE=7,CE=8*/
    public static final String[] VERDICT = {"WAIT", "QUEUE", "AC", "WA", "TLE", "MLE", "PE", "RE", "CE"};
    /**判题结果：等待*/
    public static final String WAIT = "WAIT";
    /**判题结果：队列，待判*/
    public static final String QUEUE = "QUEUE";
    /**判题结果：通过*/
    public static final String AC = "AC";
    /**判题结果：答案错误*/
    public static final String WA = "WA";
    /**判题结果：超时*/
    public static final String TLE = "TLE";
    /**判题结果：超内存*/
    public static final String MLE = "MLE";
    /**判题结果：运行时错误*/
    public static final String PE = "PE";
    /**判题结果：运行时错误*/
    public static final String RE = "RE";
    /**判题结果：编译错误*/
    public static final String CE = "CE";
    /**LANGUAGE: ""=0,C=1,CPP=2,JAVA=3*/
    public static final String[] LANGUAGE ={"","C","CPP","JAVA"};
    /**语言类型，C语言*/
    public static final byte C = 1;
    /**语言类型，C++*/
    public static final byte CPP = 2;
    /**语言类型，Java*/
    public static final byte JAVA = 3;
    /**编译超时，毫秒*/
    public static final int COMPILE_TIME = 2000;
    /**临时存放源文件的位置*/
    public static final String SRC_PATH = "d:\\dhuoj\\work\\src\\";
    /**临时存放二进制文件的位置*/
    public static final String BIN_PATH = "d:\\dhuoj\\work\\bin\\";
    /**gcc编译器的位置*/
    public static final String GCC_PATH = "D:\\dhuoj\\compiler\\mingw\\bin\\gcc.exe";
    /**g++编译器的位置*/
    public static final String CPP_PATH = "D:\\dhuoj\\compiler\\mingw\\bin\\g++.exe";
    /**Java编译器*/
    public static final String JAVA_PATH = "D:\\dhuoj\\compiler\\jdk\\bin\\javac.exe";
    /**测试数据文件夹*/
    public static final String DATA_PATH = "D:\\dhuoj\\data\\";
    /**Judge内核*/
    public static final String JUDGE_CORE = "D:\\dhuoj\\bin\\core.exe";
    /**JAVA放宽限制倍数*/
    public static final int JAVA_LIMIT = 3;
    /**Java虚拟机位置*/
    public static final String JVM = "D:\\dhuoj\\compiler\\jdk\\jre\\bin\\java-rmi.exe";
    /**运行内核的windows限制帐号的用户名*/
    public static final String USER = "administrator";
    /**运行内核的windows限制帐号的用户名*/
    public static final String PASS = "sunci777";
    /**服务器端的验证TOKEN，用于与Judge通信时的验证*/
    public static final String TOKEN_SERVER = "9c6300202e877f4f7444f51aba6143d6";
    /**Judge端的TOKEN，将其用md5加密后发送给Server，由Server进行比对判断，从而验证合法身份*/
    public static final String TOKEN_CLIENT = "李赫元";
    /**Server的IP*/
    public static final String SERVER_IP = "127.0.0.1";
    /**Server的端口*/
    public static final int SERVER_RCV_SOCKET = 58001;
    /**Client的端口*/
    public static final int CLIENT_RCV_SOCKET = 58002;
    /**数据库服务器url*/
    public static final String DB_URL = "jdbc:mysql://acm.dhu.edu.cn:3306/dhuoj?useUnicode=true&characterEncoding=utf8&autoReconnect=true";
    /**数据库用户名*/
    public static final String DB_USER = "root";
    /**数据库密码*/
    public static final String DB_PASS = "123456";
    /**客户端字体*/
    public static final java.awt.Font font = new java.awt.Font("DialogInput", 0, 13);
    /**客户端提交结果刷新间隔*/
    public static final long SLEEPTIME = 10000;
    /**Output File Size*/
    public static final long FILEMAXSIZE = 512L;
}
