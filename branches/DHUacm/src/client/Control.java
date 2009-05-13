package client;

import problem.SolutionBean;

import config.EnvironmentBean;
import judge.*;
import paper.PaperBean;
import java.util.StringTokenizer;
import client.thread.*;
import client.allpanel.*;
import config.Const;

public class Control {

    public Control() {
    }

    public static void init(MainFrame f) {
        frame = f;
        eb = new EnvironmentBean("Environment.xml");
        submittimes = 0;
        //cn = new ClientNet();
        //cn.setInterval(0);
        allAC = 0;
        language = "Cpp";
        allcodecnt = 0;
        model = "Trainer";
        jb = new JudgeBean();
        jb.setEnvironmentBean(eb);
        stdsb = new SolutionBean();
        islogined = false;
        subFrame = new SubmissionFrame();
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
            pb = new PaperBean();
            pb.unmarshal((new StringBuilder()).append("./paper/").append(paperNo).toString());
            int papernum = pb.getProblemCount();
            accepted = new int[papernum];
            stdsb = new SolutionBean();
            nowPaperNum = 0;
            frame.setTitle(x);
        } catch (Exception E) {
            frame.smallDialog("Paper get error!", "Error", 0);
        }
    }

    public static void removePaper() {
        paperNo = null;
        pb = null;
        nowPaperNum = 0;
        stdsb = new SolutionBean();
        allAC = 0;
        frame.getAC(allAC);
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

    public static void setSingleAnswerDoc(String na, String lan, String co) {
        name = na;
        language = lan;
        code = co;
    }

    public static void showSubFrame(){
        subFrame.setVisible(true);
    }

    public static void login(String username, String password) {

        try { // Call Web Service Operation
            cn.edu.dhu.acm.oj.webservice.UserAccountServiceService service = new cn.edu.dhu.acm.oj.webservice.UserAccountServiceService();
            cn.edu.dhu.acm.oj.webservice.UserAccountService port = service.getUserAccountServicePort();
            // TODO initialize WS operation arguments here
            cn.edu.dhu.acm.oj.webservice.LoginForm userForm = new cn.edu.dhu.acm.oj.webservice.LoginForm();
            // TODO process result here
            userForm.setUsername(username);
            userForm.setPassword(password);
            java.lang.Boolean loginresult = port.login(userForm);
            if (loginresult) {
                frame.smallDialog("         Login Success!", "Done", 1);
                frame.getJB_GetPaper().setEnabled(true);
                islogined = true;
                //pname = username;
                id = username;
                psw = password;
            } else {
                frame.smallDialog("Login Failed!", "Error", 0);
            }
        } catch (Exception ex) {
            frame.smallDialog("Server can't be found!", "Error", 0);
        }
    }

    public static void Register(String nick,String uid,String pwd,String sch,String email){
        try { // Call Web Service Operation
            cn.edu.dhu.acm.oj.webservice.UserAccountServiceService service = new cn.edu.dhu.acm.oj.webservice.UserAccountServiceService();
            cn.edu.dhu.acm.oj.webservice.UserAccountService port = service.getUserAccountServicePort();
            // TODO initialize WS operation arguments here
            cn.edu.dhu.acm.oj.webservice.RegisterForm r = new cn.edu.dhu.acm.oj.webservice.RegisterForm();
            // TODO process result here
            r.setEmail(email);
            r.setNickName(nick);
            r.setPassword(pwd);
            r.setSchool(sch);
            r.setUserID(uid);
            java.lang.Boolean ans = port.register(r);
            if(ans){
                frame.smallDialog("Register OK!", "Done", 1);
            }
            else{
                frame.smallDialog("Register Failed!", "Error", 0);
            }
        } catch (Exception ex) {
            frame.smallDialog(ex.getMessage(), "Error", 0);
        }

    }

    public static void SaveReplyMessage(String str) {
        if (str.indexOf("SubmitReply: ") >= 0) {
            submittimes++;
            int subt = 0;
            int problemindex = 0;
            String info = "";
            String status = "";
            StringTokenizer st = new StringTokenizer(str);
            String pNo = st.nextToken();
            st.nextToken();
            subt = Integer.parseInt(st.nextToken());
            problemindex = Integer.parseInt(st.nextToken());
            status = st.nextToken();
            while (st.hasMoreTokens()) {
                info = (new StringBuilder()).append(info).append(st.nextToken()).append(' ').toString();
            }
            if (info.charAt(info.length() - 1) == ' ') {
                info = info.substring(0, info.length() - 1);
            }
            String t = "0";
            t = (new StringBuilder()).append(t).append(Integer.toString(subt / 0x36ee80)).toString();
            subt %= 0x36ee80;
            t = (new StringBuilder()).append(t).append(":").toString();
            if (subt / 60000 < 10) {
                t = (new StringBuilder()).append(t).append("0").toString();
            }
            t = (new StringBuilder()).append(t).append(Integer.toString(subt / 60000)).toString();
            subt %= 60000;
            t = (new StringBuilder()).append(t).append(":").toString();
            if (subt / 1000 < 10) {
                t = (new StringBuilder()).append(t).append("0").toString();
            }
            t = (new StringBuilder()).append(t).append(Integer.toString(subt / 1000)).toString();
            String reply = "";
            reply = (new StringBuilder()).append(reply).append("Submit Time : ").append(t).append("\n").toString();
            reply = (new StringBuilder()).append(reply).append("Paper Name : ").append(pNo).append("\n").toString();
            reply = (new StringBuilder()).append(reply).append("Problem Index: ").append(problemindex).append("\n").toString();
            reply = (new StringBuilder()).append(reply).append("Submit Reply : ").append(status).append(" --- ").append(info).append("%\n").toString();
            Object obj[] = {Integer.toString(submittimes), t, Integer.toString(problemindex), (new StringBuilder()).append(status).append(" --- ").append(info).append("%").toString()};
            frame.smallDialog(reply, "SubmitReply", 1);
            if (reply.indexOf("Yes") >= 0 && 0 == accepted[problemindex]) {
                accepted[problemindex] = 1;
                allAC++;
                frame.getAC(allAC);
            }
        } else if (str.indexOf("FAILED") >= 0) {
            frame.smallDialog(str, "Error", 0);
        } else {
            frame.smallDialog(str, "Information", 1);
        }
    }

    public static boolean Compile() {
        stdsb.setSourceCode(code);
        stdsb.setFilename(name);
        stdsb.setLanguage(language);
        jb.setSolutionBean(stdsb);
        jb.judgeCompile();
        boolean flag = jb.getResultBean().getCompileResult();
        compileOut = jb.getResultBean().getCompileInfo();
        if (!flag) {
            frame.smallDialog("Compile Error!", "Error", 0);
            System.gc();
        }
        return flag;
    }

    public static short Query(Integer qid) {
        try { // Call Web Service Operation
            cn.edu.dhu.acm.oj.webservice.ContestServiceService service = new cn.edu.dhu.acm.oj.webservice.ContestServiceService();
            cn.edu.dhu.acm.oj.webservice.ContestService port = service.getContestServicePort();
            // TODO initialize WS operation arguments here
            java.lang.Integer solutionId = qid;
            // TODO process result here
            cn.edu.dhu.acm.oj.webservice.SolutionBean bean = port.querySubmitStatus(solutionId);
            //System.out.println("Result = "+result);

            Object[] row = new Object[]{
                qid, new Integer(bean.getContestId()),
                null, Const.VERDICT[bean.getResult()],
                null, new Integer(bean.getRuntime()),
                bean.getSubmitDate().toString()
            };
            subFrame.updateRow(row);
            return bean.getResult();
        } catch (Exception ex) {
            //frame.smallDialog("Server can't be found! in query", "Error", 0);
            System.out.println("Query failed!\n"+ex.getMessage());
            return -1;
        }

    }

    public static void WsSubmit(String paperNo, int problemNo,String problemName) {
        if (!model.equals("Net")) {
            LocalSubmit(paperNo,""+problemNo);
            return;
        }
        try { // Call Web Service Operation
            cn.edu.dhu.acm.oj.webservice.ContestServiceService service = new cn.edu.dhu.acm.oj.webservice.ContestServiceService();
            cn.edu.dhu.acm.oj.webservice.ContestService port = service.getContestServicePort();
            // TODO initialize WS operation arguments here
            cn.edu.dhu.acm.oj.webservice.SubmitCodeForm submitForm = new cn.edu.dhu.acm.oj.webservice.SubmitCodeForm();
            // TODO process result here
            submitForm.setContestID(1);
            byte lan = getLanguageByte();
            submitForm.setLanguage(lan);
            //int problemid=Integer.parseInt(problemNo);
            submitForm.setProblemID(problemNo+1);
            submitForm.setPassword(psw);
            submitForm.setSource(code);
            submitForm.setUserID(id);
            java.lang.Integer submitresult = port.submitCode(submitForm);
            short tmp=0;
            Object[] row = new Object[]{
                new Integer(submitresult), null,
                problemName, Const.VERDICT[0],
                Const.LANGUAGE[lan], null,
                null
            };
            subFrame.updateRow(row);
            showSubFrame();
            RunQuerySumbmitStatus query = new RunQuerySumbmitStatus(submitresult);
            Thread thread = new Thread(query);
            thread.start();
            frame.smallDialog("         Submit OK!", "Done", 1);
        //System.out.println("queryid = " + submitresult);
        } catch (Exception ex) {
            frame.smallDialog("Submit failed!\n"+ex.getMessage(), "Error", 0);
        }

    }

    public static void LocalSubmit(String paperNo, String problemNo) {

        if (!Compile()) {
            return;
        }
        if (problemNo.length() < 2) {
            problemNo = "0" + problemNo;
        }
        String send;
        LocalJudge(problemNo);
        send = paperNo + " paperSubmitReply: " + "00000" + " " + problemNo + result;
        if (!model.equals("Net")) {
            SaveReplyMessage(send);
            return;
        }

        System.gc();
        return;
    }

//    public static void Submit(String paperNo, String problemNo) {
//        try {
//            if (!cn.isStarted()) {
//                frame.smallDialog("The Contest is closed!", "Error", 2);
//                return;
//            }
//        } catch (Exception E) {
//            System.out.println(E.toString());
//        }
//        if (!Compile()) {
//            return;
//        }
//        if (problemNo.length() < 2) {
//            problemNo = "0" + problemNo;
//        }
//        String send;
//        LocalJudge(problemNo);
//        send = paperNo + " paperSubmitReply: " + "00000" + " " + problemNo + result;
//        if (!model.equals("Net")) {
//            SaveReplyMessage(send);
//            return;
//        }
//        try {
//            sad = new SingleAnswerDoc();
//            sad.setName(pname);
//            sad.setStdNo(id);
//            sad.setStdClass(psw);
//            sad.setTestID(result);
//            sad.setIPAddress(InetAddress.getLocalHost().getHostAddress());
//            sad.setProblemID((new StringBuilder()).append(paperNo).append(problemNo).toString());
//            sad.setLanguage(language);
//            sad.setFilename(name);
//            sad.setSourceCode(code);
//            try {
//                cn.submit(sad);
//                frame.smallDialog("         Submit OK!", "Done", 1);
//                if (result.indexOf("No") >= 0) {
//                    SaveReplyMessage(send);
//                }
//            } catch (Exception E1) {
//                frame.smallDialog("       Submit Fail!", "Error", 0);
//            }
//        } catch (Exception e) {
//            frame.smallDialog("Some thing wrong at your compile path or InetAddress~", "Error", 0);
//        }
//        System.gc();
//        return;
//    }
    public static boolean Run(String in) {
        jb.setTestDataBean(pb.getProblemAt(nowPaperNum).getTestData());
        jb.setTestInput(in);
        jb.judgeTestRun();
        boolean flag = jb.getResultBean().getRunResult();
        if (flag) {
            frame.smallDialog("Run Successfully!", "Done", 1);
        } else {
            frame.smallDialog(jb.getResultBean().getRunInfo(), "Error", 0);
        }
        testOut = jb.getResultBean().getRunOutputData();
        System.gc();
        return flag;
    }

//    public static ClientNet getClientNet() {
//        return cn;
//    }
    public static PaperBean getPaperBean() {
        return pb;
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

    public static byte getLanguageByte(){
        if(language.equals("C")){
            return Const.C;
        }
        else if(language.equals("Cpp")){
            return Const.CPP;
        }
        else{
            return Const.JAVA;
        }
    }

    public static void minusAllcodecnt() {
        if (allcodecnt > 0) {
            allcodecnt--;
        }
    }

    public static EnvironmentBean getEnvBean() {
        return eb;
    }

    private static void LocalJudge(String pNo) {
        int tempProblemNum = 0;
        try {
            tempProblemNum = Integer.parseInt(pNo);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        judgeBean = new JudgeBean();
        judgeBean.setEnvironmentBean(eb);
        SolutionBean sb = new SolutionBean();
        sb.setFilename(name);
        sb.setLanguage(language);
        sb.setSourceCode(code);
        judgeBean.setSolutionBean(sb);
        judgeBean.setTestDataBean(pb.getProblemAt(tempProblemNum).getTestData());
        judgeBean.setProblemArchive(pb.getProblemAt(tempProblemNum));
        judgeBean.judgeCheck();
        String checkinfo = judgeBean.getResultBean().getCheckInfo() + " " + judgeBean.getResultBean().getCheckPercent();
        result = checkinfo;
        if (result.substring(0, 2).equalsIgnoreCase("AC")) {
            result = (new StringBuilder()).append(" Yes ").append(result).toString();
        } else {
            result = (new StringBuilder()).append(" No ").append(result).toString();
        }
    }
    private static String model;
    private static String language;
    private static EnvironmentBean eb;
    private static SolutionBean stdsb;
    private static PaperBean pb;
    private static JudgeBean jb;
    private static SingleAnswerDoc sad;
    private static JudgeBean judgeBean;
    private static int nowPaperNum = 0;
    private static int accepted[];
    private static int allAC;
    private static int submittimes;
    private static int allcodecnt;
    private static String paperNo;
    private static String compileOut;
    private static String pname;
    private static String id;
    private static String psw;
    private static String sip;
    private static String name;
    private static String code;
    private static String result;
    private static String testOut;
    private static MainFrame frame;
    private static boolean islogined;
    private static PaperPanel paperpanel;
    private static SubmissionFrame subFrame;

}