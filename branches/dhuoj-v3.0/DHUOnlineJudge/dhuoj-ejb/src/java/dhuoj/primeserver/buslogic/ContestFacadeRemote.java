package dhuoj.primeserver.buslogic;

import dhuoj.primeserver.common.form.SubmitCodeForm;
import dhuoj.primeserver.exception.ContestException;
import dhuoj.primeserver.persistence.entity.*;
import java.util.List;
import java.util.TreeMap;
import javax.ejb.Remote;

@Remote
public interface ContestFacadeRemote {

    Contest getContest(int contestID);

    Contest getContestDetail(String userID, int contestID) throws ContestException;

    TreeMap<String, User> getContestReservation(int contestID);

    List<Contest> getContests(int first, int max);

    TreeMap<Integer, ContestProblem> getProblemsByContest(int contestID);

    TreeMap<String, User> getUsersByRank(List<Solution> runs);

    Solution querySubmitStatus(int slnID);

    List<Solution> querySubmitStatusByContest(int contestID);

    int submitCode(SubmitCodeForm scf) throws ContestException;
    
}
