package dhuoj.primeserver.buslogic;

import dhuoj.primeserver.common.form.MessageForm;
import dhuoj.primeserver.persistence.entity.Message;
import javax.ejb.Remote;

@Remote
public interface MessageFacadeRemote {

    Integer submitMessage(MessageForm msgForm);

    Message queryMessageStatus(int msgID);
    
}
