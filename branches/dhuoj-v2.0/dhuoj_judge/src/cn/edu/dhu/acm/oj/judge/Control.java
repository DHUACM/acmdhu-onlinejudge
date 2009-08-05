package cn.edu.dhu.acm.oj.judge;

import cn.edu.dhu.acm.oj.common.config.*;
import cn.edu.dhu.acm.oj.common.judge.*;
import cn.edu.dhu.acm.oj.common.bean.RunBean;
import cn.edu.dhu.acm.oj.common.config.Const;
import cn.edu.dhu.acm.oj.common.paper.PaperBean;
import cn.edu.dhu.acm.oj.persistence.beans.SolutionBean;
import cn.edu.dhu.acm.oj.persistence.beans.MessageBean;
import java.io.*;
import java.net.*;
import java.util.*;

public class Control {

    //public:
    public Control() {
    }

    public static void init(MainFrame f) {

        java.io.File file = new java.io.File("./Environment.xml");
        if (file.exists()) {
            System.out.println("Exist Environment.xml");
            envbean = new EnvironmentBean("./Environment.xml");
        } else {
            System.out.println("No Environment.xml");
            envbean = new EnvironmentBean();
        }
        solutionbean = null;
        mainframe = f;
        isauto = false;
        isAcceptLocaljudge = true;
        codeframe = new CodeInfFrame();
        problemframe = new ProblemLookFrame();
        try {
            server = new ServerSocket(Const.CLIENT_RCV_SOCKET);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isIsAcceptLocaljudge() {
        return isAcceptLocaljudge;
    }

    public static void setInfo(String str) {
        codeframe.setInfo(str);
    }

    public static void setCode(String str) {
        codeframe.setCode(str);
        codeframe.setVisible(true);
    }

    public static void setProblem() {
        problemframe.setProblem(paperbean, 0);
        problemframe.setVisible(true);
    }

    public static void setIsAcceptLocaljudge(boolean isAcceptLocaljudge) {
        Control.isAcceptLocaljudge = isAcceptLocaljudge;
    }

    public static void setIP(String SIP) {
        ServerIP = SIP;
    }

    public static void setIsauto(boolean t) {
        isauto = t;
    }

    //for Receiver
    public static void Receive() {
        ObjectInputStream ois = null;
        try {
            Socket socket = server.accept();
            ois = new ObjectInputStream(socket.getInputStream());
            Object obj = ois.readObject();
            if (obj instanceof SolutionBean) {
                synchronized (solutionqueue) {
                    solutionqueue.add((SolutionBean) obj);
                    mainframe.setSolutionQueue(solutionqueue.size());
                    mainframe.setVisible(true);
                    System.out.println("Received a SolutionBean");
                    socket.getOutputStream().write("OK\r\n".getBytes());
                }
            } else if (obj instanceof MessageBean) {
                synchronized (messagequeue) {
                    messagequeue.add((MessageBean) obj);
                    mainframe.setMessageQueue(messagequeue.size());
                    mainframe.setVisible(true);
                    System.out.println("Received a MessageBean");
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

    //Judge Step 1
    public static boolean GetSubmit() {
        boolean ans = false;
        synchronized (solutionqueue) {
            if (solutionqueue.isEmpty()) {
                solutionbean = null;
            } else {
                solutionbean = solutionqueue.removeFirst();
                ans = true;
                mainframe.setSolutionGotten();
                mainframe.setSolutionQueue(solutionqueue.size());
            }
        }
        return ans;
    }

    //Judge Step 2
    public static void Judge() {
        if (solutionbean != null) {

            Solution2Run();
            if (isAcceptLocaljudge) {
                short localresult = solutionbean.getLocalJudgeResult();
                if (localresult != Const.AC && localresult != Const.CE) {
                    runbean.setResult(localresult);
                    return;
                }
            }

            judger = new Judger(runbean, envbean);
            if (judger.Compile()) {
                judger.Run();
                judger.Check();
            }
            System.out.println(runbean.getResult());
        }
    }

    //Judge Step 3
    public static void SendResult() {
        if (solutionbean != null) {
            Run2Solution();
            synchronized (sendqueue) {
                sendqueue.add(solutionbean);
            }
        }
    }

    //Message Step 1
    public static boolean GetMessage() {
        boolean ans = false;
        synchronized (messagequeue) {
            if (messagequeue.isEmpty()) {
                messagebean = null;
            } else {
                messagebean = messagequeue.removeFirst();
                ans = true;
                mainframe.setMessageQueue(messagequeue.size());
            }
        }
        return ans;
    }

    //Message Step 2
    public static void SendMessage() {
        if (messagebean != null) {
            synchronized (sendqueue) {
                sendqueue.add(messagebean);
            }
        }
    }

    //for Sender
    public static void Send() {
        try {
            synchronized (sendqueue) {
                if (!sendqueue.isEmpty()) {
                    if (sendBean(sendqueue.getFirst())) {
                        sendqueue.removeFirst();
                        System.out.println("send one");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.gc();
        }

    }

    public static int getSolutionQueueNum() {
        synchronized (solutionqueue) {
            return solutionqueue.size();
        }
    }

    public static RunBean getRunbean() {
        return runbean;
    }

    public static SolutionBean getSolutionbean() {
        return solutionbean;
    }

    public static MessageBean getMessagebean() {
        return messagebean;
    }

    public static PaperBean getPaperbean() {
        return paperbean;
    }

    public static boolean getIsauto() {
        return isauto;
    }

    //private:
    private static void Solution2Run() {
        runbean = new RunBean();
        runbean.setLanguage(solutionbean.getLanguage());
        runbean.setSourceCode(solutionbean.getSourceCode().getSource());

        String name = solutionbean.getProblemId() + ".xml";
        if (!name.equals(paperName)) {
            setPaper(name);
        }
        runbean.setInput(tmpIn);
        runbean.setStdAns(tmpAns);
        runbean.setTimeLimit(tmpTimelim);
    }

    private static void Run2Solution() {
        //solutionbean.getCompileInfo().setError(runbean.getCompileInfo());
        solutionbean.setRuntime((int) runbean.getTimeUsed());
        solutionbean.setResult(runbean.getResult());
    }

    private static void setPaper(String x) {
        try {
            paperName = x;
            paperbean = new PaperBean();
            paperbean.unmarshal("./paper/" + paperName);
            tmpIn = paperbean.getProblemAt(0).getTestData().getTestInput();
            tmpAns = paperbean.getProblemAt(0).getTestData().getTestOutput();
            tmpTimelim = paperbean.getProblemAt(0).getTestData().getTimeLimit();
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    private static boolean sendBean(Object b) {
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
                ex.printStackTrace();
            }
        }
    }
    private static String ServerIP;
    private static Judger judger;
    private static EnvironmentBean envbean;
    private static ServerSocket server = null;
    private static final LinkedList<SolutionBean> solutionqueue = new LinkedList();
    private static final LinkedList<MessageBean> messagequeue = new LinkedList();
    private static final LinkedList<Object> sendqueue = new LinkedList();
    private static PaperBean paperbean;
    private static String paperName = Const.INITPAPER;
    private static String tmpIn,  tmpAns;
    private static long tmpTimelim;
    private static boolean isauto;
    private static boolean isAcceptLocaljudge;
    private static MainFrame mainframe;
    private static SolutionBean solutionbean;
    private static MessageBean messagebean;
    private static RunBean runbean;
    private static CodeInfFrame codeframe;
    private static ProblemLookFrame problemframe;
}