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

import cn.edu.dhu.acm.oj.persistence.beans.UserBean;

public class UserDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(UserDAO.class);
	// property constants
    public static final String USERBEAN = "cn.edu.dhu.acm.oj.persistence.beans.UserBean";
	public static final String PASSWORD = "password";
	public static final String EMAIL = "email";
	public static final String NICK = "nick";
	public static final String SCHOOL = "school";
	public static final String SUBMIT = "submit";
	public static final String SOLVED = "solved";
	public static final String DEFUNCT = "defunct";
	public static final String IP = "ip";
	public static final String LANGUAGE = "language";
	public static final String ROLE = "role";

	public void save(UserBean ubean) {
		log.debug("saving UserBean instance");
		try {
			getSession().save(ubean);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

    public void persist(UserBean ubean) {
		log.debug("persisting UserBean instance");
		try {
			Session session = getSession();
            Transaction tx = session.beginTransaction();
            session.persist(ubean);
            tx.commit();
			//getSession().persist(ubean);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void delete(UserBean ubean) {
		log.debug("deleting UserBean instance");
		try {
            getSession().delete(ubean);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

    public UserBean merge(UserBean ubean) {
		log.debug("merging UserBean instance");
		try {
			UserBean result = (UserBean) getSession().merge(ubean);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public UserBean findUser(String uid) {
		log.debug("getting UserBean instance with uid: " + uid);
		try {
			UserBean ubean = (UserBean) getSession().get(USERBEAN, uid);
			return ubean;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

    public static UserBean chkLogin(UserBean ubean){
        try{
            Session session=HibernateSessionFactory.getSession();
            session.beginTransaction();
            String hqlStr=new String("from UserBean where userId='"+ubean.getUserId()+
                        "' and password='"+ubean.getPassword()+"'");
            List list =(List)session.createQuery(hqlStr).list();
            UserBean bean = (UserBean)list.get(0);
            session.getTransaction().commit();
            return bean;

        }catch(Exception e){
            log.error("check login failed", e);
            return null;
        }
    }

    public List findUsersInRange(int size, int start){
        if(start < 0) start = 0;
        Session session=getSession();
        session.beginTransaction();
        Query query=session.createQuery("from UserBean order by solved desc");
        query.setFirstResult(size * start);
        query.setMaxResults(size);
        List rs=query.list();
        session.getTransaction().commit();
        return rs;
    }

    public static void main(String args[]) {
        UserDAO udao = new UserDAO();

        // find user by uid
        UserBean ubean = udao.findUser("admin");
        System.out.println(ubean.getUserId());

        // find user in range
        //List list = udao.findUsersInRange(10, 0);
        //UserBean ubean = (UserBean)list.get(0);
        //System.out.println(ubean.getUserId());
        //System.out.println(list.size());

        // add new user
        //UserBean ubean = new UserBean("gjzhu", "gjzhu", "gjzhu@dhu.com",
        //        "lao zhu", "dhu", "127.0.0.1", java.util.Calendar.getInstance().getTime(),
        //        java.util.Calendar.getInstance().getTime(), 0);
        //udao.save(ubean);

        // update user
        ubean.setEmail("admin@qq.com");
        udao.persist(ubean);
    }

    /*
    public List findByExample(UserBean instance) {
		log.debug("finding UserBean instance by example");
		try {
			List results = getSession().createCriteria(USERBEAN).add(
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
		log.debug("finding UserBean instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from UserBean as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List findByNick(Object nick) {
		return findByProperty(NICK, nick);
	}

	public List findBySchool(Object school) {
		return findByProperty(SCHOOL, school);
	}

	public List findBySubmit(Object submit) {
		return findByProperty(SUBMIT, submit);
	}

	public List findBySolved(Object solved) {
		return findByProperty(SOLVED, solved);
	}

	public List findByDefunct(Object defunct) {
		return findByProperty(DEFUNCT, defunct);
	}

	public List findByIp(Object ip) {
		return findByProperty(IP, ip);
	}

	public List findByLanguage(Object language) {
		return findByProperty(LANGUAGE, language);
	}

	public List findByRole(Object role) {
		return findByProperty(ROLE, role);
	}

	public List findAll() {
		log.debug("finding all UserBean instances");
		try {
			String queryString = "from UserBean";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public UserBean merge(UserBean detachedInstance) {
		log.debug("merging UserBean instance");
		try {
			UserBean result = (UserBean) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UserBean instance) {
		log.debug("attaching dirty UserBean instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserBean instance) {
		log.debug("attaching clean UserBean instance");
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