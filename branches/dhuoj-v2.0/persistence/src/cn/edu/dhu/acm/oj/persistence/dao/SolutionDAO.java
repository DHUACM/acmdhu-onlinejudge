package cn.edu.dhu.acm.oj.persistence.dao;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import cn.edu.dhu.acm.oj.persistence.beans.SolutionBean;

public class SolutionDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(SolutionDAO.class);
    public static final String SOLUTION_BEAN = "cn.edu.dhu.acm.oj.persistence.beans.SolutionBean";

    public void addSolution(SolutionBean sbean) {
        log.debug("saving SolutionBean instance");
		try {
            Session session = getSession();
            Transaction tx = session.beginTransaction();
            session.save(sbean);
            tx.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
    }

    public void updateSolution(SolutionBean sbean) {
		log.debug("updateing SolutionBean instance");
		try {
			Session session = getSession();
            Transaction tx = session.beginTransaction();
            session.update(sbean);
            tx.commit();
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

    public SolutionBean findSolution(int sid) {
		log.debug("getting SolutionBean instance with id: " + sid);
		try {
            Session session = getSession();
            session.flush();
			SolutionBean sbean = (SolutionBean) session.get(SOLUTION_BEAN, sid);
            session.close();
			return sbean;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

    public List<SolutionBean> findSolutionsInRange(int first, int max){
        try {
            Session session=getSession();
            Transaction tx = session.beginTransaction();
            session.flush();
            Query query=session.createQuery("from SolutionBean order by solutionId desc");
            query.setFirstResult(first);
            query.setMaxResults(max);
            List<SolutionBean> rs=query.list();
            tx.commit();
            session.close();
            return rs;
        } catch(Exception e) {
            log.error("find solutions in range failed", e);
            return null;
        }
    }

    public List<SolutionBean> findContestSolutionsInRange(int cid, int first, int max){
        try {
            Session session=getSession();
            Transaction tx = session.beginTransaction();
            session.flush();
            Query query=session.createQuery("from SolutionBean where contestId= " + cid + " order by solutionId asc");
            query.setFirstResult(first);
            query.setMaxResults(max);
            List<SolutionBean> rs=query.list();
            tx.commit();
            session.close();
            return rs;
        } catch(Exception e) {
            log.error("find contest solutions in range failed", e);
            return null;
        }
    }

    public List<SolutionBean> findContestLoginStatus(int cid){
        try {
            Session session=getSession();
            Transaction tx = session.beginTransaction();
            session.flush();
            Query query=session.createQuery("from SolutionBean where contestId= " + cid + " and problemId='1000' order by solutionId asc");
            List<SolutionBean> rs=query.list();
            tx.commit();
            session.close();
            return rs;
        } catch(Exception e) {
            log.error("find contest login status in range failed", e);
            return null;
        }
    }

    public List<SolutionBean> findUnjudgedSolutionsInRange(int first, int max){
        try {
            Session session=getSession();
            Transaction tx = session.beginTransaction();
            session.flush();
            Query query=session.createQuery("from SolutionBean where result= 0 order by solutionId asc");
            query.setFirstResult(first);
            query.setMaxResults(max);
            List<SolutionBean> rs=query.list();
            tx.commit();
            session.close();
            return rs;
        } catch(Exception e) {
            log.error("find contest solutions in range failed", e);
            return null;
        }
    }

    public static void main(String[] args) {
        SolutionDAO sdao = new SolutionDAO();
        SolutionBean sbean = sdao.findSolution(125);
        sbean.setResult((short)2);
        sdao.updateSolution(sbean);

        /*
        // update solution
        SolutionBean s = sdao.findSolution(92);
        SourceCodeBean sc = s.getSourceCode();
        sc.setSource("abc");
        System.out.println(s.getSolutionId() + " " + s.getSourceCode().getSource());
        sdao.editSolution(s);
         * */
    }
    /*
	public void save(Solution transientInstance) {
		log.debug("saving Solution instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Solution persistentInstance) {
		log.debug("deleting Solution instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Solution findById(java.lang.Integer id) {
		log.debug("getting Solution instance with id: " + id);
		try {
			Solution instance = (Solution) getSession().get("dao.Solution", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Solution instance) {
		log.debug("finding Solution instance by example");
		try {
			List results = getSession().createCriteria("dao.Solution").add(
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
		log.debug("finding Solution instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Solution as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findCurrentPage(int pageSize,int currentPageNo){
        if(currentPageNo<1)currentPageNo=1;
        Session session=getSession();
        session.beginTransaction();
        session.flush();
        Query query=session.createQuery("from Solution order by solutionId desc");
        query.setFirstResult(pageSize*(currentPageNo-1));
        query.setMaxResults(pageSize);
        List rs=query.list();
        session.getTransaction().commit();
        session.close();
        return rs;
    }
	
	public List findByProblemId(Object problemId) {
		return findByProperty(PROBLEM_ID, problemId);
	}

	public List findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List findByRuntime(Object runtime) {
		return findByProperty(RUNTIME, runtime);
	}

	public List findByMemory(Object memory) {
		return findByProperty(MEMORY, memory);
	}

	public List findByResult(Object result) {
		return findByProperty(RESULT, result);
	}

	public List findByLanguage(Object language) {
		return findByProperty(LANGUAGE, language);
	}

	public List findByContestId(Object contestId) {
		return findByProperty(CONTEST_ID, contestId);
	}

	public List findByValid(Object valid) {
		return findByProperty(VALID, valid);
	}

	public List findByCodeLength(Object codeLength) {
		return findByProperty(CODE_LENGTH, codeLength);
	}

	public List findAll() {
		log.debug("finding all Solution instances");
		try {
			String queryString = "from Solution";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Solution merge(Solution detachedInstance) {
		log.debug("merging Solution instance");
		try {
			Solution result = (Solution) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Solution instance) {
		log.debug("attaching dirty Solution instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Solution instance) {
		log.debug("attaching clean Solution instance");
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