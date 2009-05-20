package cn.edu.dhu.acm.oj.client;

import cn.edu.dhu.acm.oj.common.config.*;
import cn.edu.dhu.acm.oj.common.judge.*;
import cn.edu.dhu.acm.oj.common.bean.*;
import cn.edu.dhu.acm.oj.common.paper.PaperBean;
import cn.edu.dhu.acm.oj.client.thread.*;
import cn.edu.dhu.acm.oj.client.panel.*;

public class Control {

    public Control() {
    }

    public static void init() {
        envbean = new EnvironmentBean("Environment.xml");
        language = "Cpp";
        allcodecnt = 0;
        model = "Trainer-Local";
        islogined = false;
        localqid = 0;
        contestid = 3;
    }

    public static void setMainFrame(MainFrame f) {
        frame = f;
    }

    public static void setLoginFrame(LoginFrame l) {
        loginframe = l;
    }

    public static void setModel(String str) {
        model = str;
    }

    public static String getModel() {
        return model;
    }

    public static void setNowpapernum(int x) {
        nowPaperNum = x;
    }

    public static void setPaper(String x) {
        try {
            paperNo = x;
            paperbean = new PaperBean();
            paperbean.unmarshal((new StringBuilder()).append("./paper/").append(paperNo).toString());
            nowPaperNum = 0;
            frame.setTitle(x);
            frame.setPaper();
        } catch (Exception E) {
            frame.smallDialog("Paper get error!", "Error", 0);
        }
    }

    public static void removePaper() {
        paperNo = null;
        paperbean = null;
        nowPaperNum = 0;
    }

    public static void setLanguage(String str) {
        language = str;
    }

    public static void setPaperpanel(PaperPanel p) {
        paperpanel = p;
    }

    public static PaperPanel getPaperpanel() {
        return paperpanel;
    }

    public static void setCode(String co) {
        code = co;
    }

    public static void getContest() {
        //TODO: getContest
//        try {
//            cn.edu.dhu.acm.oj.webservice.ContestServiceService service = new cn.edu.dhu.acm.oj.webservice.ContestServiceService();
//            cn.edu.dhu.acm.oj.webservice.ContestService port = service.getContestServicePort();
//            int firstResult = 0;
//            int maxResults = 0;
//            java.util.List<cn.edu.dhu.acm.oj.webservice.ContestBean> result = port.getContestList(firstResult, maxResults);
//            for (int i = 0; i < result.size(); i++) {
//                cn.edu.dhu.acm.oj.webservice.ContestBean cb = result.get(i);
//            //cb.getTitle();
//            }
//            System.out.println("Result = " + result);
//        } catch (Exception ex) {
//            // TODO handle custom exceptions here
//        }
    }

    public static boolean login(String username, String password) {

        try {
            cn.edu.dhu.acm.oj.webservice.UserAccountServiceService service = new cn.edu.dhu.acm.oj.webservice.UserAccountServiceService();
            cn.edu.dhu.acm.oj.webservice.UserAccountService port = service.getUserAccountServicePort();
            cn.edu.dhu.acm.oj.webservice.LoginForm userForm = new cn.edu.dhu.acm.oj.webservice.LoginForm();
            userForm.setUsername(username);
            userForm.setPassword(password);
            java.lang.Boolean loginresult = port.login(userForm);
            if (loginresult) {
                islogined = true;
                message = "         Login Success!";
                id = username;
                psw = password;
            } else {
                islogined = false;
                message = "Login Failed!";
            }
        } catch (Exception ex) {
            islogined = false;
            message = "Server can't be found!";
        }
        return islogined;
    }

    public static boolean Register(String nick, String uid, String pwd, String sch, String email) {
        boolean ans = false;
        try {
            cn.edu.dhu.acm.oj.webservice.UserAccountServiceService service = new cn.edu.dhu.acm.oj.webservice.UserAccountServiceService();
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

    public static boolean Compile() {
        runbean = new RunBean();
        runbean.setSourceCode(code);
        runbean.setLanguage(Const.getLanguageByte(language));
        judger = new Judger(runbean, envbean);
        boolean ans = judger.Compile();
        compileOut = judger.getCompileinfo();
        if (!ans) {
            frame.smallDialog("Compile Error!", "Error", 0);
        }
        return ans;
    }

    public static short Query(Integer qid) {
        try {
            cn.edu.dhu.acm.oj.webservice.ContestServiceService service = new cn.edu.dhu.acm.oj.webservice.ContestServiceService();
            cn.edu.dhu.acm.oj.webservice.ContestService port = service.getContestServicePort();
            java.lang.Integer solutionId = qid;
            cn.edu.dhu.acm.oj.webservice.SolutionBean bean = port.querySubmitStatus(solutionId);
            Object[] row = new Object[]{
                qid, new Integer(bean.getContestId()),
                null, Const.VERDICT[bean.getResult()],
                null, new Integer(bean.getRuntime()),
                bean.getSubmitDate().toString()
            };
            frame.updateRow(row);
            if (bean.getResult() > 1) {
                showResult(qid);
            }
            return bean.getResult();
        } catch (Exception ex) {
            System.out.println("Query failed!\n" + ex.getMessage());
            return -1;
        }

    }

    public static void WsSubmit(int problemNo, String problemName) {
        int isOK = 0;
        compiled = true;
        LocalJudge(problemNo, problemName);
        if (!compiled) {
            return;
        }
        if (model.indexOf("Local") != -1) {
            showResult(localqid - 1);
        } else {
            if (problemName.indexOf("A+B") != -1) {
                contestid = 0;
            } else {
                contestid = 3;
            }
            try {
                frame.deleteqid(localqid - 1);
                cn.edu.dhu.acm.oj.webservice.ContestServiceService service = new cn.edu.dhu.acm.oj.webservice.ContestServiceService();
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
                frame.updateRow(row);
                Thread thread = new Thread(query);
                thread.start();
                isOK = 1;
                message = "         Submit OK!";
                if (problemName.indexOf("A+B") != -1) {
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
        if (!Compile()) {
            return;
        }
        runbean.setInput(test);
        runbean.setTimeLimit(tl);
        judger.Run();

        short r = runbean.getResult();
        if (r == Const.QUEUE) {
            testOut = runbean.getOutput();
        } else {
            testOut = "Error: " + r + "\n" + runbean.getOutput();
        }
    }

    private static void LocalJudge(int problemNo, String problemName) {
        if (!Compile()) {
            compiled = false;
            return;
        }
        runbean.setInput(paperbean.getProblemAt(problemNo).getTestData().getTestInput());
        runbean.setStdAns(paperbean.getProblemAt(problemNo).getTestData().getTestOutput());
        runbean.setTimeLimit(paperbean.getProblemAt(problemNo).getTestData().getTimeLimit());
        judger.Run();
        judger.Check();

        Object[] row = new Object[]{
            localqid, null,
            problemName, Const.VERDICT[runbean.getResult()],
            language, runbean.getTimeUsed(),
            null
        };
        frame.updateRow(row);

        localqid++;
    }

    private static void showResult(Integer qid) {
        String r = frame.getRowString(qid);
        frame.smallDialog(r, "Result", 1);
    }

    public static PaperBean getPaperBean() {
        return paperbean;
    }

    public static int getAllcodecnt() {
        allcodecnt++;
        return allcodecnt - 1;
    }

    public static int getNowpapernum() {
        return nowPaperNum;
    }

    public static String getCompileOut() {
        return compileOut;
    }

    public static String getTestOut() {
        return testOut;
    }

    public static MainFrame getMainFrame() {
        return frame;
    }

    public static String getPaperNo() {
        return paperNo;
    }

    public static String getMessage() {
        return message;
    }

    public static void minusAllcodecnt() {
        if (allcodecnt > 0) {
            allcodecnt--;
        }
    }

    public static LoginFrame getLoginframe() {
        return loginframe;
    }

    public static EnvironmentBean getEnvBean() {
        return envbean;
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
    private static String model;
    private static String language;
    private static String paperNo;
    private static String compileOut;
    private static String id;
    private static String psw;
    private static String code;
    private static String testOut;
    private static String message;
    private static boolean islogined;
    private static boolean compiled;
}