package dhuoj.primeserver.buslogic;

import dhuoj.primeserver.common.form.SubmitCodeForm;
import dhuoj.primeserver.common.util.*;
import dhuoj.primeserver.exception.ContestException;
import dhuoj.primeserver.persistence.entity.*;
import dhuoj.primeserver.persistence.eao.*;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.TreeMap;

@PersistenceContext(unitName="dhuojPU",name="dhuojPU")
@Stateless
public class ContestFacadeBean implements ContestFacadeRemote {

    public Contest getContest(int contestID) {
        ContestEAO ceao = EAOFactory.getContestEAO();
        return ceao.findContest(contestID);
    }

    public Contest getContestDetail(String userID, int contestID) throws ContestException {
        ContestEAO ceao = EAOFactory.getContestEAO();
        Contest contest = ceao.findContest(contestID);
        if (contest == null) throw new ContestException("Contest" + contestID + " does not exist.");
        if (contest.getPrivate() != 0) {
            ContestReservationEAO creao = EAOFactory.getContestReservationEAO();
            List<ContestReservation> my_contests = creao.findUserReservedContest(userID);
            boolean match = false;
            for (ContestReservation cr : my_contests) {
                if (cr.getContestId() == contestID) match = true;
            }
            if (match) return contest;
            else throw new ContestException("This is a private contest, register it first or contact the administrator.");
        } else {
            return contest;
        }
    }

    public TreeMap<String, User> getContestReservation(int contestID) {
        TreeMap<String, User> revMap = new TreeMap();
        ContestReservationEAO revdao = EAOFactory.getContestReservationEAO();
        List<ContestReservation> revList = revdao.findContestReservationList(contestID);
        UserEAO udao = EAOFactory.getUserEAO();
        for (ContestReservation cr : revList) {
            String uid = cr.getUserId();
            User user = udao.findUser(uid);
            revMap.put(uid, user);
        }
        return revMap;
    }

    public List<Contest> getContests(int first, int max) {
        ContestEAO ceao = EAOFactory.getContestEAO();
        return ceao.findContestInRange(first, max);
    }

    public TreeMap<Integer, ContestProblem> getProblemsByContest(int contestID) {
        ContestProblemEAO cpeao = EAOFactory.getContestProblemEAO();
        return cpeao.findProblemTreeMapByContest(contestID);
    }

    public TreeMap<String, User> getUsersByRank(List<Solution> runs) {
        TreeMap<String, User> userMap = new TreeMap();
        UserEAO ueao = EAOFactory.getUserEAO();
        for (Solution sln : runs) {
            String uid = sln.getUserId();
            User user = ueao.findUser(uid);
            userMap.put(uid, user);
        }
        return userMap;
    }

    public Solution querySubmitStatus(int slnID) {
        SolutionEAO seao = EAOFactory.getSolutionEAO();
        return seao.findSolution(slnID);
    }

    public List<Solution> querySubmitStatusByContest(int contestID) {
        SolutionEAO seao = EAOFactory.getSolutionEAO();
        return seao.findContestSolutionsInRange(contestID, 0, 50000000);
    }

    public int submitCode(SubmitCodeForm scf) throws ContestException {
//        SolutionBean sbean = new SolutionBean(scf.getUserID(), scf.getContestID(),
//                scf.getProblemID(), 0, 0, Util.getTime(), Const.WAIT, scf.getLanguage(), scf.getLocalJudgeResult());
//        public Solution(int problemId, String userId, int runtime, int memory, 
//        Date submitDate, int result, int language, int contestId, int valid, int localJudgeResult, int percentage) {
        Solution sln = new Solution(scf.getProblemID(), scf.getUserID(), 0, 0, 
                Util.getTime(), Const.WAIT, scf.getLanguage(), scf.getContestID(),
                1, scf.getLocalJudgeResult(), 0);

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

        ContestEAO ceao = EAOFactory.getContestEAO();
        Contest contest = ceao.findContest(cid);
        if (contest == null) throw new ContestException("Contest " + cid + " not exist.");

        /*
        // user cannot submit code to private contest which he/she has not registered.
        if (cbean.getPrivate_() != 0) {
            if (!getContestReservation(cid).containsKey(userID))
                throw new SubmitFailException("Fail to submit to private contest without reservation.");
        }*/

        if (seq == 0) {
            // this is a+b problem
            sln.setProblemId(seq);
        } else {
            int cmpStartTime = Util.getTime().compareTo(contest.getStartTime());
            int cmpEndTime = Util.getTime().compareTo(contest.getEndTime());
            // contest not start.
            if (cmpStartTime < 0) throw new ContestException("Contest " + cid + " not start, submit code failed.");
            // contest has closed
            if (cmpEndTime > 0) throw new ContestException("Contest" + cid + " has closed, submit code failed.");

            TreeMap<Integer, ContestProblem> contestProblems = getProblemsByContest(cid);
            sln.setProblemId(contestProblems.get(seq).getProblemId());
        }

        SolutionEAO seao = EAOFactory.getSolutionEAO();
        seao.addSolution(sln);

        SourceCode src = new SourceCode(sln.getSolutionId(), scf.getSource());
        SourceCodeEAO srcedao = EAOFactory.getSourceCodeEAO();
        srcedao.addSourceCode(src);

        return sln.getSolutionId();
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")
 
}
