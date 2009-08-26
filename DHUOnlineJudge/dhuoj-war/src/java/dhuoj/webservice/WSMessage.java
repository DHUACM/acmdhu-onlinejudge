package dhuoj.webservice;

import dhuoj.primeserver.buslogic.MessageFacadeRemote;
import dhuoj.primeserver.common.form.MessageForm;
import dhuoj.primeserver.persistence.entity.Message;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService()
public class WSMessage {
    @EJB
    private MessageFacadeRemote messageFacadeBean;

    @WebMethod(operationName = "submitMessage")
    public Integer submitMessage(@WebParam(name = "msgForm")
    MessageForm msgForm) {
        return messageFacadeBean.submitMessage(msgForm);
    }

    @WebMethod(operationName = "queryMessageStatus")
    public Message queryMessageStatus(@WebParam(name = "msgID")
    int msgID) {
        return messageFacadeBean.queryMessageStatus(msgID);
    }
}
