package config;

/**
 * �������ʹ�õĸ��ֳ���
 * @author
 */
public class Const {
    /**VERDICT: WAIT=0,QUEUE=1,AC=2,WA=3,TLE=4,MLE=5,PE=6,RE=7,CE=8*/
    public static final String[] VERDICT = {"WAIT", "QUEUE", "AC", "WA", "TLE", "MLE", "PE", "RE", "CE"};
    /**���������ȴ�*/
    public static final String WAIT = "WAIT";
    /**�����������У�����*/
    public static final String QUEUE = "QUEUE";
    /**��������ͨ��*/
    public static final String AC = "AC";
    /**���������𰸴���*/
    public static final String WA = "WA";
    /**����������ʱ*/
    public static final String TLE = "TLE";
    /**�����������ڴ�*/
    public static final String MLE = "MLE";
    /**������������ʱ����*/
    public static final String PE = "PE";
    /**������������ʱ����*/
    public static final String RE = "RE";
    /**���������������*/
    public static final String CE = "CE";
    /**LANGUAGE: ""=0,C=1,CPP=2,JAVA=3*/
    public static final String[] LANGUAGE ={"","C","CPP","JAVA"};
    /**�������ͣ�C����*/
    public static final byte C = 1;
    /**�������ͣ�C++*/
    public static final byte CPP = 2;
    /**�������ͣ�Java*/
    public static final byte JAVA = 3;
    /**���볬ʱ������*/
    public static final int COMPILE_TIME = 2000;
    /**��ʱ���Դ�ļ���λ��*/
    public static final String SRC_PATH = "d:\\dhuoj\\work\\src\\";
    /**��ʱ��Ŷ������ļ���λ��*/
    public static final String BIN_PATH = "d:\\dhuoj\\work\\bin\\";
    /**gcc��������λ��*/
    public static final String GCC_PATH = "D:\\dhuoj\\compiler\\mingw\\bin\\gcc.exe";
    /**g++��������λ��*/
    public static final String CPP_PATH = "D:\\dhuoj\\compiler\\mingw\\bin\\g++.exe";
    /**Java������*/
    public static final String JAVA_PATH = "D:\\dhuoj\\compiler\\jdk\\bin\\javac.exe";
    /**���������ļ���*/
    public static final String DATA_PATH = "D:\\dhuoj\\data\\";
    /**Judge�ں�*/
    public static final String JUDGE_CORE = "D:\\dhuoj\\bin\\core.exe";
    /**JAVA�ſ����Ʊ���*/
    public static final int JAVA_LIMIT = 3;
    /**Java�����λ��*/
    public static final String JVM = "D:\\dhuoj\\compiler\\jdk\\jre\\bin\\java-rmi.exe";
    /**�����ں˵�windows�����ʺŵ��û���*/
    public static final String USER = "administrator";
    /**�����ں˵�windows�����ʺŵ��û���*/
    public static final String PASS = "sunci777";
    /**�������˵���֤TOKEN��������Judgeͨ��ʱ����֤*/
    public static final String TOKEN_SERVER = "9c6300202e877f4f7444f51aba6143d6";
    /**Judge�˵�TOKEN��������md5���ܺ��͸�Server����Server���бȶ��жϣ��Ӷ���֤�Ϸ����*/
    public static final String TOKEN_CLIENT = "���Ԫ";
    /**Server��IP*/
    public static final String SERVER_IP = "127.0.0.1";
    /**Server�Ķ˿�*/
    public static final int SERVER_RCV_SOCKET = 58001;
    /**Client�Ķ˿�*/
    public static final int CLIENT_RCV_SOCKET = 58002;
    /**���ݿ������url*/
    public static final String DB_URL = "jdbc:mysql://acm.dhu.edu.cn:3306/dhuoj?useUnicode=true&characterEncoding=utf8&autoReconnect=true";
    /**���ݿ��û���*/
    public static final String DB_USER = "root";
    /**���ݿ�����*/
    public static final String DB_PASS = "123456";
    /**�ͻ�������*/
    public static final java.awt.Font font = new java.awt.Font("DialogInput", 0, 13);
    /**�ͻ����ύ���ˢ�¼��*/
    public static final long SLEEPTIME = 10000;
    /**Output File Size*/
    public static final long FILEMAXSIZE = 512L;
}
