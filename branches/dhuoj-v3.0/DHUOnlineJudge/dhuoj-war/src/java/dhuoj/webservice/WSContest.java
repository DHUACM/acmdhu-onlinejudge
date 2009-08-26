package dhuoj.webservice;

import dhuoj.primeserver.buslogic.ContestFacadeRemote;
import dhuoj.primeserver.common.form.SubmitCodeForm;
import dhuoj.primeserver.common.util.Util;
import dhuoj.primeserver.common.util.Const;
import dhuoj.primeserver.exception.ContestException;
import dhuoj.primeserver.persistence.entity.Solution;
import dhuoj.primeserver.persistence.entity.Contest;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;
import java.util.Iterator;
import java.util.Date;

@WebService()
public class WSContest {
    @EJB
    private ContestFacadeRemote contestFacadeBean;

    /**
     * Web service operation
     */
    @WebMethod(operationName = "submitCode")
    public Integer submitCode(@WebParam(name = "submitForm")
    SubmitCodeForm submitForm) throws ContestException {
        try {
            int slnID = contestFacadeBean.submitCode(submitForm);
            System.out.println("user " + submitForm.getUserID() + " submit problem " + submitForm.getProblemID() + " successfully!");
            return slnID;
        } catch(ContestException ce) {
            throw ce;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "querySubmitStatus")
    public Solution querySubmitStatus(@WebParam(name = "solutionID")
    int solutionID) {
        return contestFacadeBean.querySubmitStatus(solutionID);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getContestList")
    public java.util.List<Contest> getContestList(@WebParam(name = "firstResult")
    int firstResult, @WebParam(name = "maxResults")
    int maxResults) {
        List<Contest> contests = contestFacadeBean.getContests(firstResult, maxResults);
        Iterator<Contest> iter = contests.listIterator();
        while (iter.hasNext()) {
            Contest contest = iter.next();
            if (contest.getContestId() == 0) {
                // cid = 0 is for problem archive.
                iter.remove();
            } else {
                // calculate contest status
                Date startTime = contest.getStartTime();
                Date endTime = contest.getEndTime();
                Date now = Util.getTime();
                if (startTime.after(now)) contest.setStatus(Const.CONTEST_PENDING);
                else if (endTime.before(now)) iter.remove();
                else contest.setStatus(Const.CONTEST_RUNNING);
            }
        }
        return contests;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getContestDetail")
    public Contest getContestDetail(@WebParam(name = "userID")
    String userID, @WebParam(name = "contestID")
    int contestID) throws ContestException {
        try {
            return contestFacadeBean.getContestDetail(userID, contestID);
        } catch (ContestException ce) {
            throw ce;
        }
    }
}
