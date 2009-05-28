package cn.edu.dhu.acm.oj.logic.facade;

import cn.edu.dhu.acm.oj.common.form.MessageForm;
import cn.edu.dhu.acm.oj.persistence.beans.MessageBean;
import cn.edu.dhu.acm.oj.persistence.dao.MessageDAO;
import cn.edu.dhu.acm.oj.exception.*;

public class MessageFacade {

    public static int submitMessage(MessageForm msgForm) throws MessageException {
        MessageDAO mdao = new MessageDAO();
        MessageBean mbean = new MessageBean(msgForm.getUserID(), msgForm.getQuestion(), null);
        try {
            mdao.addMessage(mbean);
            return mbean.getMessageId();
        } catch(Exception e) {
            throw new MessageException("Error occur when adding a message.");
        }
    }

    public static MessageBean queryMessageStatus(int mid) throws MessageException {
        MessageDAO mdao = new MessageDAO();
        try {
            return mdao.findMessage(mid);
        } catch(Exception e) {
            throw new MessageException("Error occur when query message status.");
        }
    }

}
