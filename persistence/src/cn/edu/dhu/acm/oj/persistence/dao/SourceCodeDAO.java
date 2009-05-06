package cn.edu.dhu.acm.oj.persistence.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

import cn.edu.dhu.acm.oj.persistence.beans.SourceCodeBean;

public class SourceCodeDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(SourceCodeDAO.class);
    public static final String SOURCE_CODE_BEAN = "cn.edu.dhu.acm.oj.persistence.beans.SourceCodeBean";

    public void addSourceCode(SourceCodeBean scbean) {
        log.debug("saving SourceCodeBean instance");
		try {
            Session session = getSession();
            Transaction tx = session.beginTransaction();
            session.save(scbean);
            tx.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
    }

    public SourceCodeBean findSourceCode(int sid) {
		log.debug("getting SourceCodeBean instance with id: " + sid);
		try {
			SourceCodeBean scbean = (SourceCodeBean) getSession().get(
                    SOURCE_CODE_BEAN, sid);
			return scbean;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

    public static void main(String[] args) {
    }
    /*
	public void save(SourceCodeBean transientInstance) {
		log.debug("saving SourceCode instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SourceCode persistentInstance) {
		log.debug("deleting SourceCode instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SourceCode findById(java.lang.Integer id) {
		log.debug("getting SourceCode instance with id: " + id);
		try {
			SourceCode instance = (SourceCode) getSession().get(
					"dao.SourceCode", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SourceCode instance) {
		log.debug("finding SourceCode instance by example");
		try {
			List results = getSession().createCriteria("dao.SourceCode").add(
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
		log.debug("finding SourceCode instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SourceCode as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySource(Object source) {
		return findByProperty(SOURCE, source);
	}

	public List findAll() {
		log.debug("finding all SourceCode instances");
		try {
			String queryString = "from SourceCode";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SourceCode merge(SourceCode detachedInstance) {
		log.debug("merging SourceCode instance");
		try {
			SourceCode result = (SourceCode) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SourceCode instance) {
		log.debug("attaching dirty SourceCode instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SourceCode instance) {
		log.debug("attaching clean SourceCode instance");
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