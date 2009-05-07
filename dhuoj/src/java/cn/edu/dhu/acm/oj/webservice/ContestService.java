/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.edu.dhu.acm.oj.webservice;

import cn.edu.dhu.acm.oj.common.form.SubmitCodeForm;
import cn.edu.dhu.acm.oj.persistence.beans.SolutionBean;
import cn.edu.dhu.acm.oj.logic.facade.ContestFacade;
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
    SubmitCodeForm submitForm) {
        return ContestFacade.submitCode(submitForm);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "querySubmitStatus")
    public SolutionBean querySubmitStatus(@WebParam(name = "solutionId")
    Integer solutionId) {
        return ContestFacade.querySubmitStatus(solutionId);
    }

}
