package cn.edu.dhu.acm.oj.judge;

import cn.edu.dhu.acm.oj.common.config.*;
import cn.edu.dhu.acm.oj.common.judge.*;
import cn.edu.dhu.acm.oj.common.bean.RunBean;
import cn.edu.dhu.acm.oj.common.config.Const;
import cn.edu.dhu.acm.oj.common.paper.PaperBean;
import cn.edu.dhu.acm.oj.persistence.beans.SolutionBean;
import java.io.*;
import java.net.*;
import java.util.*;

public class Control {

    //public:
    public Control() {
    }

    public static void init(MainFrame f) {
        envbean = new EnvironmentBean();
        solutionbean = null;
        mainframe = f;
        try {
            server = new ServerSocket(Const.SERVER_RCV_SOCKET);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setIP(String SIP) {
        ServerIP = SIP;
    }

    //for Receiver
    public static void Receive() {
        ObjectInputStream ois = null;
        try {
            Socket socket = server.accept();
            ois = new ObjectInputStream(socket.getInputStream());
            Object obj = ois.readObject();
            if (obj instanceof SolutionBean) {
                synchronized (receivequeue) {
                    receivequeue.add((SolutionBean) obj);
                    mainframe.setQueue(receivequeue.size());
                    System.out.println("Received a SolutionBean");
                    socket.getOutputStream().write("OK\r\n".getBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    //Main Step 1
    public static void GetSubmit() {
        synchronized (receivequeue) {
            if (receivequeue.isEmpty()) {
                solutionbean = null;
            } else {
                solutionbean = receivequeue.removeFirst();
                mainframe.setGotten();
            }
        }
    }

    //Main Step 2
    public static void Judge() {
        if (solutionbean != null) {
            Solution2Run();
            judger = new Judger(runbean, envbean);
            if (judger.Compile()) {
                judger.Run();
                judger.Check();
            }
            //send.push(run);
            System.out.println(runbean.getResult());
        }
    }

    //Main Step 3
    public static void SendResult() {
        if (solutionbean != null) {
            Run2Solution();
            synchronized (sendqueue) {
                sendqueue.add(solutionbean);
            }
        }
    }

    //for Sender
    public static void Send() {
        try {
            synchronized (sendqueue) {
                if (!sendqueue.isEmpty()) {
                    if (sendRunBean(sendqueue.getFirst())) {
                        sendqueue.removeFirst();
                        System.out.println("send one");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getQueueNum() {
        synchronized (receivequeue) {
            return receivequeue.size();
        }
    }

    public static RunBean getRunbean() {
        return runbean;
    }

    public static SolutionBean getSolutionbean() {
        return solutionbean;
    }

    public static PaperBean getPaperbean() {
        return paperbean;
    }

    public static int getProblemID() {
        return problemID;
    }

    //private:
    private static void Solution2Run() {
        runbean = new RunBean();
        runbean.setLanguage(solutionbean.getLanguage());
        runbean.setSourceCode(solutionbean.getSourceCode().getSource());
        String name = "contest" + solutionbean.getContestId() + ".xml";
        if (!name.equals(paperName)) {
            setPaper(name);
        }
        problemID = solutionbean.getProblemId();
        //TODO: problemNo = sb.getProblemID - Contest.firstNo, not 0
        problemID = 0;
        runbean.setInput(paperbean.getProblemAt(problemID).getTestData().getTestInput());
        runbean.setStdAns(paperbean.getProblemAt(problemID).getTestData().getTestOutput());
        runbean.setTimeLimit(paperbean.getProblemAt(problemID).getTestData().getTimeLimit());
    }

    private static void Run2Solution() {
        solutionbean.getCompileInfo().setError(runbean.getCompileInfo());
        solutionbean.setRuntime((int) runbean.getTimeUsed());
        solutionbean.setResult(runbean.getResult());
    }

    private static void setPaper(String x) {
        try {
            paperName = x;
            paperbean = new PaperBean();
            paperbean.unmarshal("./paper/" + paperName);
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    private static boolean sendRunBean(SolutionBean b) {
        Socket socket = null;
        try {
            socket = new Socket(ServerIP, Const.SERVER_RCV_SOCKET);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(b);
            Scanner scan = new Scanner(socket.getInputStream());

            if (scan.nextLine().equals("OK")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                socket.close();
                socket = null;
            } catch (Exception ex) {
            }
        }
    }
    private static String ServerIP;
    private static Judger judger;
    private static EnvironmentBean envbean;
    private static ServerSocket server = null;
    private static final LinkedList<SolutionBean> receivequeue = new LinkedList();
    private static final LinkedList<SolutionBean> sendqueue = new LinkedList();
    private static PaperBean paperbean;
    private static String paperName = Const.INITPAPER;
    private static MainFrame mainframe;
    private static SolutionBean solutionbean;
    private static RunBean runbean;
    private static int problemID;
}