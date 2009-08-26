package dhuoj.primeserver.buslogic;

import dhuoj.primeserver.common.form.MessageForm;
import dhuoj.primeserver.persistence.entity.Message;
import dhuoj.primeserver.persistence.eao.MessageEAO;
import dhuoj.primeserver.persistence.eao.EAOFactory;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;

@PersistenceContext(unitName="dhuojPU",name="dhuojPU")
@Stateless
public class MessageFacadeBean implements MessageFacadeRemote {

    public Integer submitMessage(MessageForm msgForm) {
        MessageEAO meao = EAOFactory.getMessageEAO();
        Message msg = new Message(msgForm.getUserID(), msgForm.getQuestion(), null);
        meao.addMessage(msg);
        return msg.getMessageId();
    }

    public Message queryMessageStatus(int msgID) {
        MessageEAO meao = EAOFactory.getMessageEAO();
        return meao.findMessage(msgID);
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")
 
}
