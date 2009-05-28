package cn.edu.dhu.acm.oj.logic.facade;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.LinkedList;
import java.util.TreeMap;
import cn.edu.dhu.acm.oj.common.config.Const;
import cn.edu.dhu.acm.oj.common.form.SubmitCodeForm;
import cn.edu.dhu.acm.oj.common.util.Util;
import cn.edu.dhu.acm.oj.persistence.beans.*;
import cn.edu.dhu.acm.oj.persistence.dao.*;
import cn.edu.dhu.acm.oj.exception.*;
import java.util.Set;


public class ContestFacade {

    public static int submitCode(SubmitCodeForm scf) throws SubmitFailException {
        SolutionBean sbean = new SolutionBean(scf.getUserID(), scf.getContestID(),
                scf.getProblemID(), 0, 0, Util.getTime(), Const.WAIT, scf.getLanguage(), scf.getLocalJudgeResult());

        String userID = scf.getUserID();
        String password = scf.getPassword();

        UserDAO udao = new UserDAO();
        UserBean ubean = udao.chkLogin(userID, password);
        // userId not match with its password
        if (ubean == null) throw new SubmitFailException("User " + userID + " not match with its password. Submit Failed.");

        int cid = scf.getContestID();
        int seq = scf.getProblemID();

        ContestDAO cdao = new ContestDAO();
        ContestBean cbean = cdao.findContest(cid);

        int cmpStartTime = Calendar.getInstance().getTime().compareTo(cbean.getStartTime());
        int cmpEndTime = Calendar.getInstance().getTime().compareTo(cbean.getEndTime());
        // contest not start.
        if (cmpStartTime < 0) throw new SubmitFailException("Contest " + cid + " not start, submit code failed.");
        // contest has closed
        if (cmpEndTime > 0) throw new SubmitFailException("Contest" + cid + " has closed, submit code failed.");

        // user cannot submit code to private contest which he/she has not registered.
        if (cbean.getPrivate_() != 0) {
            if (!getContestReservation(cid).containsKey(userID))
                throw new SubmitFailException("Fail to submit to private contest without reservation.");
        }

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

    public static List<ContestBean> getContests(int first, int max) {
        ContestDAO cdao = new ContestDAO();
        return cdao.findContestInRange(first, max);
    }

    public static TreeMap<String, UserBean> getUsersByRank(List<SolutionBean> runs) {
        TreeMap<String, UserBean> userMap = new TreeMap();
        UserDAO udao = new UserDAO();
        for (SolutionBean sb : runs) {
            String uid = sb.getUserId();
            UserBean ubean = udao.findUser(uid);
            userMap.put(uid, ubean);
        }
        return userMap;
    }

    public static TreeMap<String, UserBean> getContestReservation(int cid) {
        TreeMap<String, UserBean> revMap = new TreeMap();
        ContestReservationDAO revdao = new ContestReservationDAO();
        List<ContestReservationBean> revList = revdao.findContestReservationList(cid);
        UserDAO udao = new UserDAO();
        for (ContestReservationBean crb : revList) {
            String uid = crb.getUserId();
            UserBean ubean = udao.findUser(uid);
            revMap.put(uid, ubean);
        }
        return revMap;
    }

    public static ContestBean getContestDetail(String uid, int cid) throws ContestException {
        ContestDAO cdao = new ContestDAO();
        ContestBean cbean = cdao.findContest(cid);
        if (cbean == null) throw new ContestException("Fail to get detail information about contest" + cid);
        if (cbean.getPrivate_() != 0) {
            ContestReservationDAO crdao = new ContestReservationDAO();
            List<ContestReservationBean> my_contests = crdao.findUserReservedContest(uid);
            boolean match = false;
            for (ContestReservationBean crb : my_contests) {
                if (crb.getContestId() == cid) match = true;
            }
            if (match) return cbean;
            else throw new ContestException("This is a private contest, register it first or contact the administrator.");
        } else {
            return cbean;
        }
    }

    /*
    public static void main(String[] args) throws Exception {
        getContestDetail("intest01", 3);
    }
     */
}
