/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.edu.dhu.acm.oj.webservice;

import java.util.Date;
import cn.edu.dhu.acm.oj.common.form.SubmitCodeForm;
import cn.edu.dhu.acm.oj.persistence.beans.SolutionBean;
import cn.edu.dhu.acm.oj.persistence.beans.ContestBean;
import cn.edu.dhu.acm.oj.logic.facade.ContestFacade;
import cn.edu.dhu.acm.oj.exception.*;
import cn.edu.dhu.acm.oj.common.config.Const;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author yhu
 */
@WebService()
public class ContestService {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "submitCode")
    public Integer submitCode(@WebParam(name = "submitForm")
    SubmitCodeForm submitForm) throws SubmitFailException {
        try {
            return ContestFacade.submitCode(submitForm);
        } catch(SubmitFailException sfe) {
            throw sfe;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "querySubmitStatus")
    public SolutionBean querySubmitStatus(@WebParam(name = "solutionId")
    Integer solutionId) {
        return ContestFacade.querySubmitStatus(solutionId);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getContestList")
    public java.util.List<ContestBean> getContestList(@WebParam(name = "firstResult")
    int firstResult, @WebParam(name = "maxResults")
    int maxResults) {
        // TODO: this function will be modified later.
        java.util.List<ContestBean> clist = ContestFacade.getContests(0, 100);
        java.util.Iterator<ContestBean> iter = clist.listIterator();
        while (iter.hasNext()) {
            ContestBean cbean = iter.next();
            if (cbean.getContestId() == 0) {
                // cid = 0 is for problem archive.
                iter.remove();
            } else {
                // calculate contest status
                Date startTime = cbean.getStartTime();
                Date endTime = cbean.getEndTime();
                Date now = java.util.Calendar.getInstance().getTime();
                if (startTime.after(now)) cbean.setStatus(Const.CONTEST_PENDING);
                else if (endTime.before(now)) cbean.setStatus(Const.CONTEST_ENDED);
                else cbean.setStatus(Const.CONTEST_RUNNING);
                // TODO: set paper path and key to null.
            }
        }
        return clist;
        //return ContestFacade.getContests(firstResult, maxResults);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getContestDetail")
    public ContestBean getContestDetail(@WebParam(name = "userID")
    String userID, @WebParam(name = "contestID")
    int contestID) throws cn.edu.dhu.acm.oj.exception.ContestException {
        try {
            return ContestFacade.getContestDetail(userID, contestID);
        } catch(ContestException ce) {
            throw ce;
        }
    }

}
