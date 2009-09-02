package cn.edu.dhu.acm.oj.buslogic.facade.message;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import cn.edu.dhu.acm.oj.common.form.MessageForm;
import cn.edu.dhu.acm.oj.exception.MessageException;
import cn.edu.dhu.acm.oj.persistence.beans.MessageBean;
import cn.edu.dhu.acm.oj.persistence.dao.MessageDAO;

@WebService()
public class WSMessageFacade {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "submitMessage")
    public int submitMessage(@WebParam(name = "msgForm")
    MessageForm msgForm) throws MessageException {
        MessageDAO mdao = new MessageDAO();
        MessageBean mbean = new MessageBean(msgForm.getUserID(), msgForm.getQuestion(), null);
        try {
            mdao.addMessage(mbean);
            return mbean.getMessageId();
        } catch(Exception e) {
            throw new MessageException("Error occur when adding a message.");
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "queryMessageStatus")
    public MessageBean queryMessageStatus(@WebParam(name = "msgID")
    int msgID) throws MessageException {
        MessageDAO mdao = new MessageDAO();
        try {
            return mdao.findMessage(msgID);
        } catch(Exception e) {
            throw new MessageException("Error occur when query message status.");
        }
    }

}
