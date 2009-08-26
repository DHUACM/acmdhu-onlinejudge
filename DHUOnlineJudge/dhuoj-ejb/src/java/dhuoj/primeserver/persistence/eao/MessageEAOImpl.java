package dhuoj.primeserver.persistence.eao;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import dhuoj.primeserver.persistence.entity.Message;

public class MessageEAOImpl implements MessageEAO {

    private static String EM_NAME = EAOFactory.ENTITY_MANAGER_NAME;
    private EntityManager em;

    public MessageEAOImpl() {
        em = getEntityManager();
    }

    public void addMessage(Message msg) {
        em.persist(msg);
    }

    public void updateMessage(Message msg) {
        em.merge(msg);
    }

    public void removeMessage(Message msg) {
        em.remove(em.merge(msg));
    }

    public Message findMessage(int msgID) {
        return em.find(Message.class, msgID);
    }

    public List<Message> findNewMessagesInRange(int first, int max) {
        Query q = em.createNamedQuery("Message.findByStatus");
        q.setParameter("status", 0);
        q.setFirstResult(first);
        q.setMaxResults(max);
        return q.getResultList();
    }

    private EntityManager getEntityManager() {
        try {
            Context ctx = new InitialContext();
            return (EntityManager) ctx.lookup(EM_NAME);
        } catch (Exception e) {
            System.out.println("Unable to get an Entity Manager Instance");
            e.printStackTrace();
            return null;
        }
    }
}
