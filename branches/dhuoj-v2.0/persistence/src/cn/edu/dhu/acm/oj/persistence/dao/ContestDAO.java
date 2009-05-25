package cn.edu.dhu.acm.oj.persistence.dao;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

import cn.edu.dhu.acm.oj.persistence.beans.ContestBean;

public class ContestDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ContestDAO.class);
    public static final String CONTEST_BEAN = "cn.edu.dhu.acm.oj.persistence.beans.ContestBean";

	public void addContest(ContestBean cbean) {
		log.debug("adding ContestBean instance");
		try {
            Session session = getSession();
            Transaction tx = session.beginTransaction();
            session.save(cbean);
            tx.commit();
			log.debug("add ContestBean successful");
		} catch (RuntimeException re) {
			log.error("add ContestBean failed", re);
			throw re;
		}
	}

    public void updateContest(ContestBean cbean) {
		log.debug("updating ContestBean instance");
		try {
            Session session = getSession();
            Transaction tx = session.beginTransaction();
            session.update(cbean);
            tx.commit();
			log.debug("update ContestBean successful");
		} catch (RuntimeException re) {
			log.error("update ContestBean failed", re);
			throw re;
		}
	}

    public ContestBean findContest(int cid) {
		log.debug("getting ContestBean instance with id: " + cid);
		try {
			ContestBean cbean = (ContestBean) getSession().get(CONTEST_BEAN, cid);
			return cbean;
		} catch (RuntimeException re) {
			log.error("get ContestBean failed", re);
			throw re;
		}
	}

    public List<ContestBean> findContestInRange(int first, int max){
        try {
            Session session=getSession();
            Transaction tx = session.beginTransaction();
            Query query=session.createQuery("from ContestBean order by contest_id desc");
            query.setFirstResult(first);
            query.setMaxResults(max);
            List<ContestBean> rs=query.list();
            tx.commit();
            return rs;
        } catch(Exception e) {
            log.error("find Contest in range failed", e);
            return null;
        }
    }

    public static void main(String[] args) {
        ContestDAO cdao = new ContestDAO();

        // add new contest
        //ContestBean cb = new ContestBean("Test Contest", null, null, "Test", (byte)0, 0);
        //cdao.addContest(cb);
        //System.out.println(cb.getContestId());

        // update contest
        //ContestBean cb = cdao.findContest(1);
        //cb.setTitle("Contest1");
        //java.util.Date now = java.util.Calendar.getInstance().getTime();
        //cb.setStartTime(now);
        //cdao.editContest(cb);

        // find contest list
        //List<ContestBean> list = cdao.findContestInRange(0, 1000);
        //System.out.println(list.size());
    }

    /*
	public void delete(Contest persistentInstance) {
		log.debug("deleting Contest instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public List findByExample(Contest instance) {
		log.debug("finding Contest instance by example");
		try {
			List results = getSession().createCriteria("dao.Contest").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Contest instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Contest as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List findByDefunct(Object defunct) {
		return findByProperty(DEFUNCT, defunct);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByPrivate_(Object private_) {
		return findByProperty(PRIVATE_, private_);
	}

	public List findAll() {
		log.debug("finding all Contest instances");
		try {
			String queryString = "from Contest";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Contest merge(Contest detachedInstance) {
		log.debug("merging Contest instance");
		try {
			Contest result = (Contest) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Contest instance) {
		log.debug("attaching dirty Contest instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Contest instance) {
		log.debug("attaching clean Contest instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
     * */
}