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

import cn.edu.dhu.acm.oj.persistence.beans.ProblemBean;

/**
 * A data access object (DAO) providing persistence and search support for
 * Problem entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see dao.Problem
 * @author MyEclipse Persistence Tools
 */

public class ProblemDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ProblemDAO.class);
    public static final String PROBLEM_BEAN = "cn.edu.dhu.acm.oj.persistence.beans.ProblemBean";

    public void addProblem(ProblemBean pbean) {
        log.debug("add ProblemBean instance");
        try {
            Session session = getSession();
            Transaction tx = session.beginTransaction();
            session.save(pbean);
            tx.commit();
        } catch (RuntimeException re) {
            log.error("add ProblemBean failed.", re);
            throw re;
        }
    }

    public void editProblem(ProblemBean pbean) {
        log.debug("edit ProblemBean instance");
        try {
            Session session = getSession();
            Transaction tx = session.beginTransaction();
            session.persist(pbean);
            tx.commit();
        } catch (RuntimeException re) {
            log.error("edit ProblemBean failed.", re);
            throw re;
        }
    }

    public ProblemBean findProblem(int pid) {
		log.debug("getting ProblemBean instance with id: " + pid);
		try {
			ProblemBean pbean = (ProblemBean) getSession().get(PROBLEM_BEAN, pid);
			return pbean;
		} catch (RuntimeException re) {
			log.error("get ProblemBean failed", re);
			throw re;
		}
	}

    public List<ProblemBean> findProblemInRange(int first, int max){
        try {
            Session session=getSession();
            Transaction tx = session.beginTransaction();
            Query query=session.createQuery("from ProblemBean order by problem_id asc");
            query.setFirstResult(first);
            query.setMaxResults(max);
            List<ProblemBean> rs=query.list();
            tx.commit();
            return rs;
        } catch(Exception e) {
            log.error("find problems in range failed", e);
            return null;
        }
    }

    public static void main(String[] args) {
        ProblemDAO pdao = new ProblemDAO();

        // get all problems
        //List<ProblemBean> list = pdao.findProblemInRange(0, 1000);
        //System.out.println(list.size());

        // get one problem
        ProblemBean pb = pdao.findProblem(1001);
        System.out.println(pb.getTitle());

        // update problem
        pb.setTitle("EASY PROBLEM 1");
        pdao.editProblem(pb);
    }

    /*
	public void save(Problem transientInstance) {
		log.debug("saving Problem instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Problem persistentInstance) {
		log.debug("deleting Problem instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Problem findById(java.lang.Integer id) {
		log.debug("getting Problem instance with id: " + id);
		try {
			Problem instance = (Problem) getSession().get("dao.Problem", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Problem instance) {
		log.debug("finding Problem instance by example");
		try {
			List results = getSession().createCriteria("dao.Problem").add(
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
		log.debug("finding Problem instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Problem as model where model."
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

	public List findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List findByProblemPath(Object problemPath) {
		return findByProperty(PROBLEM_PATH, problemPath);
	}

	public List findByInputPath(Object inputPath) {
		return findByProperty(INPUT_PATH, inputPath);
	}

	public List findByOutputPath(Object outputPath) {
		return findByProperty(OUTPUT_PATH, outputPath);
	}

	public List findByStdcodePath(Object stdcodePath) {
		return findByProperty(STDCODE_PATH, stdcodePath);
	}

	public List findByTimeLimit(Object timeLimit) {
		return findByProperty(TIME_LIMIT, timeLimit);
	}

	public List findByCaseTimeLimit(Object caseTimeLimit) {
		return findByProperty(CASE_TIME_LIMIT, caseTimeLimit);
	}

	public List findByMemoryLimit(Object memoryLimit) {
		return findByProperty(MEMORY_LIMIT, memoryLimit);
	}

	public List findByDefunct(Object defunct) {
		return findByProperty(DEFUNCT, defunct);
	}

	public List findByContestId(Object contestId) {
		return findByProperty(CONTEST_ID, contestId);
	}

	public List findByAccepted(Object accepted) {
		return findByProperty(ACCEPTED, accepted);
	}

	public List findBySubmit(Object submit) {
		return findByProperty(SUBMIT, submit);
	}

	public List findByError(Object error) {
		return findByProperty(ERROR, error);
	}

	public List findByDifficulty(Object difficulty) {
		return findByProperty(DIFFICULTY, difficulty);
	}

	public List findBySubmitUser(Object submitUser) {
		return findByProperty(SUBMIT_USER, submitUser);
	}

	public List findBySolvedUser(Object solvedUser) {
		return findByProperty(SOLVED_USER, solvedUser);
	}

	public List findAll() {
		log.debug("finding all Problem instances");
		try {
			String queryString = "from Problem";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findCurrentPage(int pageSize,int currentPageNo){
        if(currentPageNo<1)currentPageNo=1;
        Session session=getSession();
        session.beginTransaction();
        Query query=session.createQuery("from Problem");
        query.setFirstResult(pageSize*(currentPageNo-1));
        query.setMaxResults(pageSize);
        List rs=query.list();
        session.getTransaction().commit();
        return rs;
    }
	
	public Problem merge(Problem detachedInstance) {
		log.debug("merging Problem instance");
		try {
			Problem result = (Problem) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Problem instance) {
		log.debug("attaching dirty Problem instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Problem instance) {
		log.debug("attaching clean Problem instance");
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