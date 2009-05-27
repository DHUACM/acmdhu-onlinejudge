/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.edu.dhu.acm.oj.webservice;

import cn.edu.dhu.acm.oj.common.form.SubmitCodeForm;
import cn.edu.dhu.acm.oj.persistence.beans.SolutionBean;
import cn.edu.dhu.acm.oj.persistence.beans.ContestBean;
import cn.edu.dhu.acm.oj.logic.facade.ContestFacade;
import cn.edu.dhu.acm.oj.exception.*;
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
            int cid = iter.next().getContestId();
            if (cid != 5 && cid != 6) {
                iter.remove();
            }
        }
        return clist;
        //return ContestFacade.getContests(firstResult, maxResults);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getMyContestList")
    public java.util.List<ContestBean> getMyContestList(@WebParam(name = "userID")
    String userID) {
        return ContestFacade.getMyReservedContest(userID);
    }

}
