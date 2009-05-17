package cn.edu.dhu.acm.oj.common.judge;

/**
 * Compilerִ�б�����ʱ���̡߳�
 * @author liheyuan
 */
public class Watcher implements Runnable {

    /**ִ�б������Ľ�̾��*/
    private Process process = null;
    /**�������ַ���������������У�*/
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
     * @return ����ִ�б����̵ľ��
     */
    public Process getProcess() {
        return process;
    }
}
