package cn.edu.dhu.acm.oj.webservice;

import cn.edu.dhu.acm.oj.common.form.MessageForm;
import cn.edu.dhu.acm.oj.exception.MessageException;
import cn.edu.dhu.acm.oj.persistence.beans.MessageBean;
import cn.edu.dhu.acm.oj.common.form.MessageForm;
import cn.edu.dhu.acm.oj.logic.facade.MessageFacade;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService()
public class MessageService {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "postMessage")
    public int postMessage(@WebParam(name = "msgForm")
    MessageForm msgForm) throws MessageException {
        return MessageFacade.submitMessage(msgForm);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "queryMessageStatus")
    public MessageBean queryMessageStatus(@WebParam(name = "msgID")
    int msgID) throws MessageException {
        return MessageFacade.queryMessageStatus(msgID);
    }

}
