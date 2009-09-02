package cn.edu.dhu.acm.oj.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import cn.edu.dhu.acm.oj.wsclient.WSMessageFacadeClient;
import cn.edu.dhu.acm.oj.buslogic.facade.message.*;
import cn.edu.dhu.acm.oj.config.ServerConfig;

@WebService()
public class MessageService {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "postMessage")
    public int postMessage(@WebParam(name = "msgForm")
    MessageForm msgForm) throws Exception {
        WSMessageFacadeClient client = new WSMessageFacadeClient(ServerConfig.PRIME_SERVER_URL);
        return client.submitMessage(msgForm);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "queryMessageStatus")
    public MessageBean queryMessageStatus(@WebParam(name = "msgID")
    int msgID) throws Exception {
        WSMessageFacadeClient client = new WSMessageFacadeClient(ServerConfig.PRIME_SERVER_URL);
        return client.queryMessageStatus(msgID);
    }

}
