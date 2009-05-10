package cn.edu.dhu.acm.oj.logic.facade;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import cn.edu.dhu.acm.oj.common.form.SubmitCodeForm;
import cn.edu.dhu.acm.oj.common.util.Util;
import cn.edu.dhu.acm.oj.persistence.beans.*;
import cn.edu.dhu.acm.oj.persistence.dao.*;
import cn.edu.dhu.acm.oj.exception.*;


public class ContestFacade {

    public static int submitCode(SubmitCodeForm scf) throws ContestNotStartException, ContestClosedException, UserLoginFailException {
        SolutionBean sbean = new SolutionBean(scf.getUserID(), scf.getContestID(),
                scf.getProblemID(), 0, 0, Util.getTime(), (short)0, scf.getLanguage());

        String userID = scf.getUserID();
        String password = scf.getPassword();

        UserDAO udao = new UserDAO();
        UserBean ubean = udao.chkLogin(userID, password);
        // userId not match with its password
        if (ubean == null) throw new UserLoginFailException("User " + userID + " not match with its password. Submit Failed.");

        int cid = scf.getContestID();
        int seq = scf.getProblemID();

        ContestDAO cdao = new ContestDAO();
        ContestBean cbean = cdao.findContest(cid);

        int cmpStartTime = Calendar.getInstance().getTime().compareTo(cbean.getStartTime());
        int cmpEndTime = Calendar.getInstance().getTime().compareTo(cbean.getEndTime());
        // contest not start.
        if (cmpStartTime < 0) throw new ContestNotStartException("Contest " + cid + " not start, submit code failed.");
        // contest has closed
        if (cmpEndTime > 0) throw new ContestClosedException("Contest" + cid + " has closed, submit code failed.");

        TreeMap<Integer, ContestProblemBean> contestProblems = getProblemsByContest(cid);
        sbean.setProblemId(contestProblems.get(seq).getProblemId());

        SolutionDAO sdao = new SolutionDAO();
        sdao.addSolution(sbean);

        SourceCodeBean scbean = new SourceCodeBean(sbean.getSolutionId(), scf.getSource());
        SourceCodeDAO scdao = new SourceCodeDAO();
        scdao.addSourceCode(scbean);

        return sbean.getSolutionId();
    }

    public static SolutionBean querySubmitStatus(int solutionId) {
        SolutionDAO sdao = new SolutionDAO();
        return sdao.findSolution(solutionId);
    }

    public static List<SolutionBean> querySubmitStatusByContest(int contestId) {
        SolutionDAO sdao = new SolutionDAO();
        return sdao.findContestSolutionsInRange(contestId, 0, Integer.MAX_VALUE);
    }

    public static TreeMap<Integer, ContestProblemBean> getProblemsByContest(int contestId) {
        ContestProblemDAO cpdao = new ContestProblemDAO();
        return cpdao.findProblemTreeMapByContest(contestId);
    }

    public static ContestBean getContest(int cid) {
        ContestDAO cdao = new ContestDAO();
        return cdao.findContest(cid);
    }

    public static TreeMap<String, UserBean> getUsersByRank(List<SolutionBean> runs) {
        TreeMap<String, UserBean> userMap = new TreeMap<String, UserBean>();
        UserDAO udao = new UserDAO();
        for (SolutionBean sb : runs) {
            String uid = sb.getUserId();
            UserBean ubean = udao.findUser(uid);
            userMap.put(uid, ubean);
        }
        return userMap;
    }
/*
    public static void main(String[] args) {
        // public SubmitCodeForm(String uid, int pid, int cid, byte lang, String src)
        SubmitCodeForm scf = new SubmitCodeForm("hyj", 1003, 1, (byte)1, "hello.test");
        int sid = ContestFacade.submitCode(scf);
        System.out.println("sid = " + sid);
    }
 * */
}
