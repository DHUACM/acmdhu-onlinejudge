package cn.edu.dhu.acm.oj.client;

import cn.edu.dhu.acm.oj.common.config.*;
import cn.edu.dhu.acm.oj.common.judge.*;
import cn.edu.dhu.acm.oj.common.bean.*;
import cn.edu.dhu.acm.oj.common.paper.PaperBean;
import cn.edu.dhu.acm.oj.client.thread.*;
import cn.edu.dhu.acm.oj.client.panel.*;
import java.net.*;
import java.io.*;

public class Control {

    //public:
    public Control() {
    }

    public static void init() {
        java.io.File f;
        String sep = System.getProperty("file.separator");
        dhuojhomepath = System.getProperty("user.home") + sep + "dhuoj" + sep;
        tmppath = System.getProperty("java.io.tmpdir");
        workpath = dhuojhomepath + "yourcode" + sep;
        f = new java.io.File(dhuojhomepath);
        f.mkdir();
        f = new java.io.File(workpath);
        f.mkdir();
        envbean = new EnvironmentBean();
        envbean.setSourceCodeTemp(workpath);
        envbean.setSourceTarget(tmppath, tmppath);
        language = "CPP";
        allcodecnt = 0;
        localqid = 0;
        contestid = 0;
        lastSubmitTime = 0;
    }

    public static void setMainFrame(MainFrame f) {
        frame = f;
    }

    public static MainFrame getMainFrame() {
        return frame;
    }

    public static void setLoginFrame(LoginFrame l) {
        loginframe = l;
    }

    public static LoginFrame getLoginframe() {
        return loginframe;
    }

    public static void setModel(String str) {
        model = str;
    }

    public static String getModel() {
        return model;
    }

    public static long getLastSubmitTime() {
        return lastSubmitTime;
    }

    public static void setLastSubmitTime(long t) {
        lastSubmitTime = t;
    }

    public static void setNowpapernum(int x) {
        nowPaperNum = x;
    }

    public static int getNowpapernum() {
        return nowPaperNum;
    }

    public static void setServer(String ip) {
        try {
            UserAccountServiceURL = new java.net.URL(cn.edu.dhu.acm.oj.webservice.UserAccountServiceService.class.getResource("."), "http://" + ip + ":80/dhuoj/UserAccountService?wsdl");
            UserAccountServiceQName = new javax.xml.namespace.QName("http://webservice.oj.acm.dhu.edu.cn/", "UserAccountServiceService");
            ContestServiceURL = new java.net.URL(cn.edu.dhu.acm.oj.webservice.UserAccountServiceService.class.getResource("."), "http://" + ip + ":80/dhuoj/ContestService?wsdl");
            ContestServiceQName = new javax.xml.namespace.QName("http://webservice.oj.acm.dhu.edu.cn/", "ContestServiceService");
            MessageServiceURL = new java.net.URL(cn.edu.dhu.acm.oj.webservice.MessageServiceService.class.getResource("."), "http://" + ip + ":80/dhuoj/MessageService?wsdl");
            MessageServiceQName = new javax.xml.namespace.QName("http://webservice.oj.acm.dhu.edu.cn/", "MessageServiceService");
        } catch (Exception e) {
            frame.smallDialog("ServerIP Error!", "Error", 0);
        }
    }

    public static void downloadPaper(int i, String pwd) {
        String paperpath = "";
        try {

            cn.edu.dhu.acm.oj.webservice.ContestServiceService service = new cn.edu.dhu.acm.oj.webservice.ContestServiceService();
            cn.edu.dhu.acm.oj.webservice.ContestService port = service.getContestServicePort();
            cn.edu.dhu.acm.oj.webservice.ContestBean cb = port.getContestDetail(id, paperlist[i]);
            String key = cb.getPaperKey();
            if (key == null) {
                key = "";
            }
            paperpath = cb.getPaperPath();

            int bytesum = 0;
            int byteread = 0;
            if (!pwd.equals(key)) {
                frame.smallDialog("PaperPassword Error!", "Error", 0);
                return;
            }
            java.net.URL url = new java.net.URL(paperpath);
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            String filename;
            if (paperlist[i] >= Const.PROBLEMSTART) {
                filename = dhuojhomepath + Const.PROBLEMPREFIX + paperlist[i] + Const.CLIENTPAPERSUFFIX;
            } else {
                filename = tmppath + Const.CONTESTPREFIX + paperlist[i] + Const.CLIENTPAPERSUFFIX;
            }
            FileOutputStream fs = new FileOutputStream(filename);

            byte[] buffer = new byte[1024];

            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;
                fs.write(buffer, 0, byteread);
            }
            contestid = paperlist[i];
            setPaper(filename);
        } catch (Exception e) {
            frame.smallDialog("DownloadPaper Error!\n" + e.getMessage(), "Error", 0);
            System.out.println(paperpath);
        }
    }

    public static void setPaper(String filename) {
        try {
            paperName = filename;
            String tmp = "";
            int i;
            for (i = filename.length() - 1; i >= 0; i--) {
                if (filename.charAt(i) >= '0' && filename.charAt(i) <= '9') {
                    break;
                }
            }
            while (filename.charAt(i) >= '0' && filename.charAt(i) <= '9') {
                tmp = filename.charAt(i) + tmp;
                i--;
                if (i < 0) {
                    break;
                }
            }
            int num = Integer.parseInt(tmp);
            if (num >= Const.PROBLEMSTART) {
                frame.setTitle(Const.PROBLEMPREFIX + num);
            } else {
                frame.setTitle(Const.CONTESTPREFIX + num);
                contestid = num;
                frame.setURL("http://acm.dhu.edu.cn/dhuoj/rank/contest" + contestid + ".html");
            }

            paperbean = new PaperBean();
            if (num == 0) {
                paperbean.unmarshal();
            } else {
                paperbean.unmarshal(paperName);
            }
            nowPaperNum = 0;
            frame.setPaper();
        } catch (Exception E) {
            frame.smallDialog("Paper get error!", "Error", 0);
        }
    }

    public static PaperBean getPaperBean() {
        return paperbean;
    }

    public static void removePaper() {
        paperName = null;
        paperbean = null;
        nowPaperNum = 0;
    }

    public static void setLanguage(String str) {
        language = str;
    }

    public static String getLanguage() {
        return language;
    }

    public static void setPaperpanel(PaperPanel p) {
        paperpanel = p;
    }

    public static void setCode(String co) {
        code = co;
    }

    public static void setWorkpath(String dir) {
        workpath = dir;
        envbean.setSourceCodeTemp(workpath);
    }

    public static String getWorkpath() {
        return workpath;
    }

    public static String getTmppath() {
        return tmppath;
    }

    public static void setTmppath(String tpath) {
        tmppath = tpath;
        envbean.setSourceTarget(tmppath, tmppath);
    }

    public static int getAllcodecnt() {
        allcodecnt++;
        return allcodecnt - 1;
    }

    public static PaperPanel getPaperpanel() {
        return paperpanel;
    }

    public static String getCompileOut() {
        return compileOut;
    }

    public static String getTestOut() {
        return testOut;
    }

    public static String getPaperName() {
        return paperName;
    }

    public static String getMessage() {
        return message;
    }

    public static int[] getStatuslist() {
        return statuslist;
    }

    public static void minusAllcodecnt() {
        if (allcodecnt > 0) {
            allcodecnt--;
        }
    }

    public static EnvironmentBean getEnvBean() {
        return envbean;
    }

    public static String getDhuojhomepath() {
        return dhuojhomepath;
    }

    //public Main step:
    public static void CheckTmppath() {
        boolean ans = true;
        String str = "";
        if (tmppath.indexOf(" ") != -1) {
            str = "Tmp path can't include white space \" \"!";
            ans = false;
        } else {
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter(tmppath + "tmp.txt"));
                out.write("Hello world!");
                out.close();
            } catch (Exception e) {
                str = "No permission on Tmp path!";
                ans = false;
            }
        }
        if (!ans) {
            frame.showEnvPanel();
            frame.smallDialog(str, "Error", 0);
        } else {
            System.out.println("Tmp path check ok!");
        }
    }

    public static boolean Register(String nick, String uid, String pwd, String sch, String email) {
        boolean ans = false;
        try {
            cn.edu.dhu.acm.oj.webservice.UserAccountServiceService service = new cn.edu.dhu.acm.oj.webservice.UserAccountServiceService(UserAccountServiceURL, UserAccountServiceQName);
            cn.edu.dhu.acm.oj.webservice.UserAccountService port = service.getUserAccountServicePort();
            cn.edu.dhu.acm.oj.webservice.RegisterForm r = new cn.edu.dhu.acm.oj.webservice.RegisterForm();
            r.setEmail(email);
            r.setNickName(nick);
            r.setPassword(pwd);
            r.setSchool(sch);
            r.setUserID(uid);
            ans = port.register(r);
            if (ans) {
                message = "Register OK!";
            } else {
                message = "Register Failed!";
            }
        } catch (Exception ex) {
            ans = false;
            message = ex.getMessage();
        }
        return ans;
    }

    public static boolean login(String username, String password) {
        boolean islogined = false;
        try {
            cn.edu.dhu.acm.oj.webservice.UserAccountServiceService service = new cn.edu.dhu.acm.oj.webservice.UserAccountServiceService(UserAccountServiceURL, UserAccountServiceQName);
            cn.edu.dhu.acm.oj.webservice.UserAccountService port = service.getUserAccountServicePort();
            cn.edu.dhu.acm.oj.webservice.LoginForm userForm = new cn.edu.dhu.acm.oj.webservice.LoginForm();
            userForm.setUsername(username);
            userForm.setPassword(password);
            java.lang.Boolean loginresult = port.login(userForm);
            if (loginresult) {
                islogined = true;
                message = "Login Success!";
                id = username;
                psw = password;
            } else {
                islogined = false;
                message = "Login Failed!";
            }
        } catch (Exception ex) {
            islogined = false;
            message = "Server can not found!";
        }
        return islogined;
    }

    public static String[] getContest() {
        String[] contest = null;

        try {
            cn.edu.dhu.acm.oj.webservice.ContestServiceService service = new cn.edu.dhu.acm.oj.webservice.ContestServiceService(ContestServiceURL, ContestServiceQName);
            cn.edu.dhu.acm.oj.webservice.ContestService port = service.getContestServicePort();

            int firstResult = 0;
            int maxResults = Integer.MAX_VALUE;

            java.util.List<cn.edu.dhu.acm.oj.webservice.ContestBean> result = port.getContestList(firstResult, maxResults);
            contest = new String[result.size()];
            paperlist = new int[result.size()];
            statuslist = new int[result.size()];
            for (int i = 0; i < result.size(); i++) {
                cn.edu.dhu.acm.oj.webservice.ContestBean cb = result.get(i);
                contest[i] = cb.getTitle();
                paperlist[i] = cb.getContestId();
                statuslist[i] = cb.getStatus();
            }
        } catch (Exception ex) {
            frame.smallDialog("GetContest Failed!\n" + ex.getMessage(), "Error", 0);
        }
        return contest;
    }

    public static boolean Compile(boolean showdialog) {
        runbean = new RunBean();
        runbean.setSourceCode(code);
        runbean.setLanguage(Const.getLanguageByte(language));
        judger = new Judger(runbean, envbean);
        compiled = judger.Compile();
        compileOut = judger.getCompileinfo();
        if (compileOut.startsWith("Unknow")) {
            compileOut = "Compile Error!\nPlease confirm you compile path!\n" +
                    "Please confirm you have a permission on tmp path\nYou can change these at the menuitem Tool!";
        }
        if (!compiled && showdialog) {
            frame.smallDialog("Compile Error!", "Error", 0);
        }
        return compiled;
    }

    public static short Query(Integer qid) {
        short ans = -1;
        try {
            cn.edu.dhu.acm.oj.webservice.ContestServiceService service = new cn.edu.dhu.acm.oj.webservice.ContestServiceService(ContestServiceURL, ContestServiceQName);
            cn.edu.dhu.acm.oj.webservice.ContestService port = service.getContestServicePort();
            java.lang.Integer solutionId = qid;
            cn.edu.dhu.acm.oj.webservice.SolutionBean bean = port.querySubmitStatus(solutionId);
            Object[] row = new Object[]{
                qid, new Integer(bean.getContestId()),
                null, Const.VERDICT[bean.getResult()],
                null, new Integer(bean.getRuntime()),
                bean.getSubmitDate().toString()
            };
            frame.updateStatusRow(row);
            ans = bean.getResult();
            if (ans != Const.WAIT && ans != Const.QUEUE) {
                showResult(qid);
            }
        } catch (Exception ex) {
            System.out.println("Query failed!\n" + ex.getMessage());
            ans = -1;
        }
        return ans;
    }

    public static void WsSubmit(int problemNo, String problemName) {
        int isOK = 0;
        LocalJudge(problemNo, problemName);
        if (model.indexOf("Local") != -1) {
            showResult(localqid - 1);
        } else {
            try {
                frame.deleteStatusQid(localqid - 1);
                cn.edu.dhu.acm.oj.webservice.ContestServiceService service = new cn.edu.dhu.acm.oj.webservice.ContestServiceService(ContestServiceURL, ContestServiceQName);
                cn.edu.dhu.acm.oj.webservice.ContestService port = service.getContestServicePort();
                cn.edu.dhu.acm.oj.webservice.SubmitCodeForm submitForm = new cn.edu.dhu.acm.oj.webservice.SubmitCodeForm();
                byte lan = Const.getLanguageByte(language);
                submitForm.setContestID(contestid);
                submitForm.setLanguage(lan);
                submitForm.setProblemID(problemNo + 1);
                submitForm.setPassword(psw);
                submitForm.setSource(code);
                submitForm.setUserID(id);
                submitForm.setLocalJudgeResult(runbean.getResult());
                java.lang.Integer submitresult = port.submitCode(submitForm);
                RunQuerySumbmitStatus query = new RunQuerySumbmitStatus(submitresult);
                Object[] row = new Object[]{
                    submitresult, null,
                    problemName, Const.VERDICT[0],
                    Const.LANGUAGE[lan], null,
                    null
                };
                frame.updateStatusRow(row);
                Thread thread = new Thread(query);
                thread.start();
                isOK = 1;
                message = "Submit OK!";
                if (contestid == 0) {
                    if (runbean.getResult() == Const.AC) {
                        paperpanel.showGetpaper();
                    }
                }
            } catch (Exception ex) {
                isOK = 0;
                message = "Submit failed!\n" + ex.getMessage();
            }
            frame.smallDialog(message, "Submit", isOK);

        }

    }

    public static void RunTest(String test, long tl) {
        if (Compile(true)) {
            runbean.setInput(test);
            runbean.setTimeLimit(tl);
            judger.Run();
            short r = runbean.getResult();
            if (r == Const.QUEUE) {
                testOut = runbean.getOutput();
            } else {
                testOut = "Error: " + Const.VERDICT[r] + "\n" + runbean.getOutput();
            }
        }
    }

    public static void PostQuestion(String question) {
        try {
            cn.edu.dhu.acm.oj.webservice.MessageServiceService service = new cn.edu.dhu.acm.oj.webservice.MessageServiceService(MessageServiceURL, MessageServiceQName);
            cn.edu.dhu.acm.oj.webservice.MessageService port = service.getMessageServicePort();
            cn.edu.dhu.acm.oj.webservice.MessageForm msgForm = new cn.edu.dhu.acm.oj.webservice.MessageForm();
            msgForm.setUserID(id);
            msgForm.setQuestion(question);
            int mid = port.postMessage(msgForm);
            RunQueryMessageStatus r = new RunQueryMessageStatus(mid);
            Object[] row = new Object[]{
                mid, question, null
            };
            frame.updateQuestionRow(row);
            Thread thread = new Thread(r);
            thread.start();
        } catch (Exception ex) {
            frame.smallDialog("PostQuestion Error!\n" + ex.getMessage(), "Error", 0);
        }
    }

    public static boolean QueryQuestion(Integer mid) {
        boolean ans = false;
        try {
            cn.edu.dhu.acm.oj.webservice.MessageServiceService service = new cn.edu.dhu.acm.oj.webservice.MessageServiceService();
            cn.edu.dhu.acm.oj.webservice.MessageService port = service.getMessageServicePort();
            cn.edu.dhu.acm.oj.webservice.MessageBean result = port.queryMessageStatus(mid);
            String response = result.getResponse();
            System.out.println("QueryQuestion : " + mid + " " + response);
            if (response != null) {
                ans = true;
                Object[] row = new Object[]{
                    mid, null, result.getResponse()
                };
                frame.updateQuestionRow(row);
                String r = "Question : \n\n\t" + result.getQuestion() + "\n" + "Response : \n\n\t" + result.getResponse();
                frame.smallDialog(r, "Question", 1);
            }
        } catch (Exception ex) {
            frame.smallDialog("QueryQuestion Error!\n" + ex.getMessage(), "Error", 0);
        }

        return ans;
    }

    //private:
    private static void LocalJudge(int problemNo, String problemName) {
        if (Compile(false)) {
            runbean.setInput(paperbean.getProblemAt(problemNo).getTestData().getTestInput());
            runbean.setStdAns(paperbean.getProblemAt(problemNo).getTestData().getTestOutput());
            runbean.setTimeLimit(paperbean.getProblemAt(problemNo).getTestData().getTimeLimit());
            judger.Run();
            judger.Check();
        }
        Object[] row = new Object[]{
            localqid, null,
            problemName, Const.VERDICT[runbean.getResult()],
            language, runbean.getTimeUsed(),
            null
        };
        frame.updateStatusRow(row);
        localqid++;
    }

    private static void showResult(Integer qid) {
        String r = frame.getStatusRowString(qid);
        frame.smallDialog(r, "Result", 1);
    }
    private static MainFrame frame;
    private static LoginFrame loginframe;
    private static EnvironmentBean envbean;
    private static PaperBean paperbean;
    private static PaperPanel paperpanel;
    private static Judger judger;
    private static RunBean runbean;
    private static int nowPaperNum = 0;
    private static int allcodecnt;
    private static int localqid;
    private static int contestid;
    private static int[] paperlist;
    private static int[] statuslist;
    private static long lastSubmitTime;
    private static String model;
    private static String language;
    private static String paperName;
    private static String compileOut;
    private static String id;
    private static String psw;
    private static String code;
    private static String testOut;
    private static String message;
    private static String workpath;
    private static String tmppath;
    private static String dhuojhomepath;
    private static java.net.URL UserAccountServiceURL;
    private static java.net.URL ContestServiceURL;
    private static java.net.URL MessageServiceURL;
    private static javax.xml.namespace.QName UserAccountServiceQName;
    private static javax.xml.namespace.QName ContestServiceQName;
    private static javax.xml.namespace.QName MessageServiceQName;
    private static boolean compiled;
}