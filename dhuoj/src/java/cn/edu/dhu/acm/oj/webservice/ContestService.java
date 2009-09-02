package cn.edu.dhu.acm.oj.webservice;

import java.util.List;
import java.util.Iterator;
import java.util.Date;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import cn.edu.dhu.acm.oj.wsclient.WSContestFacadeClient;
import cn.edu.dhu.acm.oj.buslogic.facade.contest.*;
import cn.edu.dhu.acm.oj.config.ServerConfig;

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
    public Integer submitCode(@WebParam(name = "submitForm") SubmitCodeForm submitForm) throws Exception {

        WSContestFacadeClient client = new WSContestFacadeClient(ServerConfig.PRIME_SERVER_URL);
        int solutionID = client.submitCode(submitForm);
        System.out.println("user " + submitForm.getUserID() + " submit problem " + submitForm.getProblemID() + " successfully!");
        return solutionID;

    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "querySubmitStatus")
    public SolutionBean querySubmitStatus(@WebParam(name = "solutionId") Integer solutionId) throws Exception {

        WSContestFacadeClient client = new WSContestFacadeClient(ServerConfig.PRIME_SERVER_URL);
        return client.querySubmitStatus(solutionId);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getContestList")
    public java.util.List<ContestBean> getContestList(@WebParam(name = "firstResult") int firstResult, @WebParam(name = "maxResults") int maxResults) throws Exception {

        WSContestFacadeClient client = new WSContestFacadeClient(ServerConfig.PRIME_SERVER_URL);
        List<ContestBean> list = client.getContestByStatus(1);
        list.addAll(client.getContestByStatus(0));
        return list;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getContestDetail")
    public ContestBean getContestDetail(@WebParam(name = "userID") String userID, @WebParam(name = "contestID") int contestID) throws Exception {

        WSContestFacadeClient client = new WSContestFacadeClient(ServerConfig.PRIME_SERVER_URL);
        return client.getContestDetail(userID, contestID);
    }
}
