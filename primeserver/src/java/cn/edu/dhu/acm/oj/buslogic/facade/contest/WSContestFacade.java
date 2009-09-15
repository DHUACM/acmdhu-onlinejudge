package cn.edu.dhu.acm.oj.buslogic.facade.contest;

import java.util.List;
import java.util.TreeMap;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import cn.edu.dhu.acm.oj.common.config.Const;
import cn.edu.dhu.acm.oj.common.util.Util;
import cn.edu.dhu.acm.oj.common.form.SubmitCodeForm;
import cn.edu.dhu.acm.oj.exception.*;
import cn.edu.dhu.acm.oj.persistence.beans.*;
import cn.edu.dhu.acm.oj.persistence.dao.*;

@WebService()
public class WSContestFacade {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "submitCode")
    public int submitCode(@WebParam(name = "scf") SubmitCodeForm scf) throws SubmitFailException {
        SolutionBean sbean = new SolutionBean(scf.getUserID(), scf.getContestID(),
                scf.getProblemID(), 0, 0, Util.getTime(), Const.WAIT, scf.getLanguage(), scf.getLocalJudgeResult());

        String userID = scf.getUserID();
        String password = scf.getPassword();
        /*
        UserDAO udao = new UserDAO();
        UserBean ubean = udao.chkLogin(userID, password);
        // userId not match with its password
        if (ubean == null) throw new SubmitFailException("User " + userID + " not match with its password. Submit Failed.");
         */
        int cid = scf.getContestID();
        int seq = scf.getProblemID();

        ContestDAO cdao = new ContestDAO();
        ContestBean cbean = cdao.findContest(cid);
        if (cbean == null) {
            throw new SubmitFailException("Contest " + cid + " not exist, submit code failed.");
        }

        /*
        // user cannot submit code to private contest which he/she has not registered.
        if (cbean.getPrivate_() != 0) {
        if (!getContestReservation(cid).containsKey(userID))
        throw new SubmitFailException("Fail to submit to private contest without reservation.");
        }*/

        if (seq == 0) {
            // this is a+b problem
            sbean.setProblemId(seq);
        } else {
            int cmpStartTime = Util.getTime().compareTo(cbean.getStartTime());
            int cmpEndTime = Util.getTime().compareTo(cbean.getEndTime());
            // contest not start.
            if (cmpStartTime < 0) {
                throw new SubmitFailException("Contest " + cid + " not start, submit code failed.");
            }
            // contest has closed
            if (cmpEndTime > 0) {
                throw new SubmitFailException("Contest" + cid + " has closed, submit code failed.");
            }
            TreeMap<Integer, ContestProblemBean> contestProblems = getProblemsByContest(cid);
            sbean.setProblemId(contestProblems.get(seq).getProblemId());
        }

        SolutionDAO sdao = new SolutionDAO();
        sdao.addSolution(sbean);

        SourceCodeBean scbean = new SourceCodeBean(sbean.getSolutionId(), scf.getSource());
        SourceCodeDAO scdao = new SourceCodeDAO();
        scdao.addSourceCode(scbean);

        return sbean.getSolutionId();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "querySubmitStatus")
    public SolutionBean querySubmitStatus(@WebParam(name = "solutionID") int solutionID) {
        SolutionDAO sdao = new SolutionDAO();
        return sdao.findSolution(solutionID);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "queryContestLoginStatus")
    public List<SolutionBean> queryContestLoginStatus(@WebParam(name = "contestID") int contestID) {
        SolutionDAO sdao = new SolutionDAO();
        return sdao.findContestLoginStatus(contestID);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "querySubmitStatusByContest")
    public List<SolutionBean> querySubmitStatusByContest(@WebParam(name = "contestID") int contestID) {
        SolutionDAO sdao = new SolutionDAO();
        return sdao.findContestSolutionsInRange(contestID, 0, Integer.MAX_VALUE);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getProblemsByContest")
    public TreeMap<Integer, ContestProblemBean> getProblemsByContest(@WebParam(name = "contestID") int contestID) {
        ContestProblemDAO cpdao = new ContestProblemDAO();
        return cpdao.findProblemTreeMapByContest(contestID);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getContest")
    public ContestBean getContest(@WebParam(name = "contestID") int contestID) {
        ContestDAO cdao = new ContestDAO();
        return cdao.findContest(contestID);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getContests")
    public List<ContestBean> getContests(@WebParam(name = "first") int first, @WebParam(name = "max") int max) {
        ContestDAO cdao = new ContestDAO();
        return cdao.findContestInRange(first, max);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getUsersByRank")
    public TreeMap<String, UserBean> getUsersByRank(@WebParam(name = "runs") List<SolutionBean> runs) {
        TreeMap<String, UserBean> userMap = new TreeMap();
        UserDAO udao = new UserDAO();
        for (SolutionBean sb : runs) {
            String uid = sb.getUserId();
            UserBean ubean = udao.findUser(uid);
            userMap.put(uid, ubean);
        }
        return userMap;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getContestReservation")
    public TreeMap<String, UserBean> getContestReservation(@WebParam(name = "contestID") int contestID) {
        TreeMap<String, UserBean> revMap = new TreeMap();
        ContestReservationDAO revdao = new ContestReservationDAO();
        List<ContestReservationBean> revList = revdao.findContestReservationList(contestID);
        UserDAO udao = new UserDAO();
        for (ContestReservationBean crb : revList) {
            String uid = crb.getUserId();
            UserBean ubean = udao.findUser(uid);
            revMap.put(uid, ubean);
        }
        return revMap;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getContestDetail")
    public ContestBean getContestDetail(@WebParam(name = "userID") String userID, @WebParam(name = "contestID") int contestID) throws ContestException {
        ContestDAO cdao = new ContestDAO();
        ContestBean cbean = cdao.findContest(contestID);
        if (cbean == null) {
            throw new ContestException("Fail to get detail information about contest" + contestID);
        }
        if (cbean.getPrivate_() != 0) {
            ContestReservationDAO crdao = new ContestReservationDAO();
            boolean match = crdao.checkUserReservedContest(userID, contestID);
            if (match) {
                return cbean;
            } else {
                throw new ContestException("This is a private contest, please contact the administrator.");
            }
        } else {
            return cbean;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getContestByStatus")
    public List<ContestBean> getContestByStatus(@WebParam(name = "status") int status) {
        ContestDAO cdao = new ContestDAO();
        return cdao.findContestByStatus(status);
    }
}
