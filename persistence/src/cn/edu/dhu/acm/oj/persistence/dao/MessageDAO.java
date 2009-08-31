package cn.edu.dhu.acm.oj.persistence.dao;

import java.sql.Timestamp;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import cn.edu.dhu.acm.oj.persistence.beans.MessageBean;

public class MessageDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(MessageDAO.class);
    public static final String MESSAGE_BEAN = "cn.edu.dhu.acm.oj.persistence.beans.MessageBean";

    public void addMessage(MessageBean mbean) {
		log.debug("adding MessageBean instance");
		try {
            Session session = getSession();
            Transaction tx = session.beginTransaction();
            session.save(mbean);
            tx.commit();
			log.debug("add MessageBean successful");
		} catch (RuntimeException re) {
			log.error("add MessageBean failed", re);
			throw re;
		}
	}

    public void updateMessage(MessageBean mbean) {
		log.debug("updating MessageBean instance");
		try {
            Session session = getSession();
            Transaction tx = session.beginTransaction();
            session.update(mbean);
            tx.commit();
			log.debug("update MessageBean successful");
		} catch (RuntimeException re) {
			log.error("update MessageBean failed", re);
			throw re;
		}
	}

    public MessageBean findMessage(int mid) {
		log.debug("getting MessageBean instance with id: " + mid);
		try {
            Session session = getSession();
            session.flush();
			MessageBean mbean = (MessageBean) getSession().get(MESSAGE_BEAN, mid);
            session.close();
			return mbean;
		} catch (RuntimeException re) {
			log.error("get MessageBean failed", re);
			throw re;
		}
	}

    public List<MessageBean> findNewMessagesInRange(int first, int max){
        try {
            Session session=getSession();
            Transaction tx = session.beginTransaction();
            session.flush();
            Query query=session.createQuery("from MessageBean where status=0 order by message_id asc");
            query.setFirstResult(first);
            query.setMaxResults(max);
            List<MessageBean> rs=query.list();
            tx.commit();
            session.close();
            return rs;
        } catch(Exception e) {
            log.error("find new messages in range failed", e);
            return null;
        }
    }
}
