package cn.edu.dhu.acm.oj.client;

import cn.edu.dhu.acm.oj.common.config.*;
import cn.edu.dhu.acm.oj.common.judge.*;
import cn.edu.dhu.acm.oj.common.bean.*;
import cn.edu.dhu.acm.oj.common.paper.PaperBean;
import cn.edu.dhu.acm.oj.common.paperdistribute.PaperDecryptor;
import cn.edu.dhu.acm.oj.client.thread.*;
import cn.edu.dhu.acm.oj.client.panel.*;
import cn.edu.dhu.acm.oj.webservice.client.*;
import cn.edu.dhu.acm.oj.webservice.*;
import java.io.*;
import java.util.*;

public class Control {

	//public:
	public Control() {
	}

	public static void init() {
		java.io.File f;
		String sep = System.getProperty("file.separator");
		dhuojhomepath = System.getProperty("user.home") + sep + "dhuoj" + sep;
		tmppath = System.getProperty("java.io.tmpdir");
		if (!tmppath.endsWith(sep)) {
			tmppath += sep;
		}
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
		paperbean = new PaperBean();
		code = "#include <iostream>\nusing namespace std;\nint main(){\n" +
				"int a, b;\nwhile (cin >> a >> b){\ncout << a + b << endl;\n" +
				"}\nreturn 0;\n}";
	}

	public static void setMainFrame(MainFrame f) {
		frame = f;
	}

	public static String getUserid() {
		return userid;
	}

	public static void setUserid(String userid) {
		Control.userid = userid;
	}

	public static String getUserpassword() {
		return userpassword;
	}

	public static void setUserpassword(String userpassword) {
		Control.userpassword = userpassword;
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

	public static String getUserID() {
		return userid;
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

	public static void setNowProblemNum(int x) {
		nowProblemNum = x;
	}

	public static int getNowProblemNum() {
		return nowProblemNum;
	}

	public static ArrayList<String> getNetList() {
		return netList;
	}

	public static void setNetList(ArrayList<String> l) {
		netList = l;
	}

	public static PaperBean getPaperBean() {
		return paperbean;
	}

	public static void removePaper() {
		paperName = null;
		paperbean = null;
		nowProblemNum = 0;
	}

	public static void setLanguage(String str) {
		language = str;
	}

	public static String getLanguage() {
		return language;
	}

	public static int getContestid() {
		return contestid;
	}

	public static void setPaperpanel(PaperPanel p) {
		paperpanel = p;
	}

	public static void setCode(String co) {
		code = co;
	}

	public static String getCode() {
		return code;
	}

	public static String getContestTitle() {
		return contestTitle;
	}

	public static void setContestTitle(String title) {
		contestTitle = title;
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

	public static String getContestPaperName() {
		return contestPaperName;
	}

	public static void setContestPaperName(String contestPaper) {
		contestPaperName = contestPaper;
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

	public static String getPaperpath() {
		return paperpath;
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

	public static String getHostIP() {
		return hostIP;
	}

	public static void setServer(String ip) {
		try {
			hostIP = ip;
			netList.set(0,ip);
			wsContest = new WSContestClient();
			wsMessage = new WSMessageClient();
			wsUserAccount = new WSUserAccountClient();
		} catch (Exception e) {
			frame.smallDialog("ServerIP Error!", "Error", 0);
		}
	}

	public static boolean SetContest(int i) {
		boolean ans = false;
		paperpath = "";
		try {
			contestid = paperlist[i];
			ContestBean cb = wsContest.getContestDetail(userid, contestid);
			paperpath = cb.getPaperPath();
			ans = true;
			//frame.setURL("http://"+hostIP+"/dhuoj/contestrank?cid=" + contestid);
			frame.setURL("http://" + hostIP + "/dhuoj/rank/contest" + contestid + ".html");
		} catch (Exception e) {
			frame.smallDialog("GetContest Error!\n" + e.getMessage(), "Error", 0);
		}
		return ans;
	}

	public static void setPaper(String filename, String paperpwd) {
		try {
			if (filename.startsWith("CONTEST")) {
				paperName = contestPaperName;
			} else {
				paperName = filename;
			}
			nowPaperNum = contestid;
			paperbean = new PaperBean();
			if (paperName.endsWith(Const.ENCRYPTPAPERSUFFIX)) {
				paperbean = PaperDecryptor.decryptPaper(new java.io.File(paperName),
						paperpwd);
			} else {
				paperbean.unmarshal(paperName);
			}
			nowProblemNum = 0;
			frame.setPaper();
		} catch (Exception E) {
			E.printStackTrace();
			frame.smallDialog("Paper open error!\nMay your password not correct!\nOr the paper format error!", "Error", 0);
		}
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
			RegisterForm r = new RegisterForm();
			r.setEmail(email);
			r.setNickName(nick);
			r.setPassword(pwd);
			r.setSchool(sch);
			r.setUserID(uid);
			ans = wsUserAccount.register(r);
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
			LoginForm userForm = new LoginForm();
			userForm.setUsername(username);
			userForm.setPassword(password);
			java.lang.Boolean loginresult = wsUserAccount.login(userForm);
			if (loginresult) {
				islogined = true;
				message = "Login Successfully!\nYou can download paper by clicking the 'Download' button now.\n" +
						"When the contest start, you can click 'openPaper' button to open the contest paper.\n" +
						"The paper password is available at http://acm.dhu.edu.cn/dhuoj when contest start.\n\n" +
						"This software will automatically submit 'A+B' problem for test!\n" +
						"If it submits OK and receives judged result, congratulations! You successfully connect to the server.\n" +
						"If it fails, please check the network connection or contact the admin.\n\n" +
						"You can test your compiler at Tool-menu's Test-Compiler or press ALT+T\n" +
						"If it fails to find C++ Compiler for example, just set your g++ path to your system enviroment path\n" +
						"This means you can just run g++ at your cmd or terminal at any directory.";
				userid = username;
				userpassword = password;
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
			int firstResult = 0;
			int maxResults = Integer.MAX_VALUE;
			List<ContestBean> result = wsContest.getContestList(firstResult, maxResults);
			contest = new String[result.size()];
			paperlist = new int[result.size()];
			statuslist = new int[result.size()];
			for (int i = 0; i < result.size(); i++) {
				ContestBean cb = result.get(i);
				contest[i] = cb.getTitle() + " => " +
						cb.getStartTime().toString().substring(0, 16) + "~" +
						cb.getEndTime().toString().substring(0, 16);
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
			compileOut = "Please confirm you compile path!\n" +
					"Please confirm you have a permission on tmp path\n" +
					"You can change these at the menuitem Tool!\nCompile Error!";
		}
		if (!compiled && showdialog) {
			frame.smallDialog("Compile Error!", "Error", 0);
		}
		return compiled;
	}

	public static short Query(Integer qid) {
		short ans = -1;
		try {
			SolutionBean bean = wsContest.querySubmitStatus(qid);
			//getRuntime - > null, new Integer(bean.getRuntime())
			Object[] row = new Object[]{
				qid, new Integer(bean.getContestId()),
				null, Const.VERDICT[bean.getResult()],
				null, null,
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
		try {
			LocalJudge(problemNo, problemName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (model.startsWith("Local")) {
			showResult(localqid - 1);
		} else {
			try {
				frame.deleteStatusQid(localqid - 1);
				SubmitCodeForm submitForm = new SubmitCodeForm();
				byte lan = Const.getLanguageByte(language);
				//TODO: change nowPaperNum to contestid
				//submitForm.setContestID(nowPaperNum);
				submitForm.setContestID(contestid);
				submitForm.setLanguage(lan);
				//new
				int delta = 1;
				if (nowPaperNum == 0) {
					delta = 0;
				}
				submitForm.setProblemID(problemNo + delta);
				submitForm.setPassword(userpassword);
				submitForm.setSource(code);
				submitForm.setUserID(userid);
				submitForm.setLocalJudgeResult(runbean.getResult());
				java.lang.Integer submitresult = wsContest.submitCode(submitForm);
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
//				paperpanel.showOpenPaper();
//				if (nowPaperNum == 0) {
//					frame.smallDialog("You can Click GetPaper Now!", "Success", 1);
//				}
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
			MessageForm msgForm = new MessageForm();
			msgForm.setUserID(userid);
			msgForm.setQuestion(question);
			int mid = wsMessage.submitMessage(msgForm);
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
			MessageBean result = wsMessage.queryMessageStatus(mid);
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
	private static int nowProblemNum = 0;
	private static int allcodecnt;
	private static int localqid;
	private static int contestid;
	private static int nowPaperNum = 0;
	private static int[] paperlist;
	private static int[] statuslist;
	private static long lastSubmitTime;
	private static String model;
	private static String language;
	private static String paperName;
	private static String compileOut;
	private static String userid = "Test";
	private static String userpassword;
	private static String code;
	private static String testOut;
	private static String message;
	private static String workpath;
	private static String tmppath;
	private static String dhuojhomepath;
	private static String paperpath;
	private static String hostIP;
	private static String contestTitle = "Training";
	private static String contestPaperName;
	private static WSContestClient wsContest;
	private static WSMessageClient wsMessage;
	private static WSUserAccountClient wsUserAccount;
	private static ArrayList<String> netList;
	private static boolean compiled;
}