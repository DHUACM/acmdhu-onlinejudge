package dhuoj.primeserver.persistence.eao;

import java.util.List;
import dhuoj.primeserver.persistence.entity.Message;

public interface MessageEAO {

    public void addMessage(Message msg);

    public void updateMessage(Message msg);

    public void removeMessage(Message msg);

    public Message findMessage(int msgID);

    public List<Message> findNewMessagesInRange(int first, int max);
}
