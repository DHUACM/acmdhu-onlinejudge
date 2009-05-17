package judge;

/**
 * Compiler执行编译器时的线程。
 * @author liheyuan
 */
public class Watcher implements Runnable {

    /**执行编译器的进程句柄*/
    private Process process = null;
    /**编译器字符串（编译器进程命令行）*/
    private String compStr;

    /***
     * 
     * @param p
     * @param s
     */
    public Watcher(String s) {
        compStr = s;
    }

    /***
     * 
     */
    public void run() {
        try {
            process = Runtime.getRuntime().exec(compStr);
            process.waitFor();
        } catch (Exception e) {
        }
    }

    /***
     * getter
     * @return 返回执行编译进程的句柄
     */
    public Process getProcess() {
        return process;
    }
}
