package cn.edu.dhu.acm.oj.persistence.dao;

import java.util.List;
import java.util.TreeMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

import cn.edu.dhu.acm.oj.persistence.beans.ContestProblemBean;

public class ContestProblemDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(ContestDAO.class);
    public static final String CONTEST_BEAN = "cn.edu.dhu.acm.oj.persistence.beans.ContestProblemBean";

    public void addContestProblem(ContestProblemBean cpbean) {
		log.debug("adding ContestProblemBean instance");
		try {
            Session session = getSession();
            Transaction tx = session.beginTransaction();
            session.save(cpbean);
            tx.commit();
			log.debug("add ContestProblemBean successful");
		} catch (RuntimeException re) {
			log.error("add ContestProblemBean failed", re);
			throw re;
		}
	}

    public void updateContestProblem(ContestProblemBean cpbean) {
		log.debug("updating ContestProblemBean instance");
		try {
            Session session = getSession();
            Transaction tx = session.beginTransaction();
            session.update(cpbean);
            tx.commit();
			log.debug("update ContestProblemBean successful");
		} catch (RuntimeException re) {
			log.error("update ContestProblemBean failed", re);
			throw re;
		}
	}

    public List<ContestProblemBean> findProblemListByContest(int cid){
        try {
            Session session=getSession();
            Transaction tx = session.beginTransaction();
            Query query=session.createQuery("from ContestProblemBean where contestId=" + cid + " order by sequence asc");
            List<ContestProblemBean> rs=query.list();
            tx.commit();
            return rs;
        } catch(Exception e) {
            log.error("find ProblemsByContest fails", e);
            return null;
        }
    }

    public TreeMap<Integer, ContestProblemBean> findProblemTreeMapByContest(int cid){
        List<ContestProblemBean> list = this.findProblemListByContest(cid);
        TreeMap<Integer, ContestProblemBean> tree = new TreeMap<Integer, ContestProblemBean>();
        for (ContestProblemBean cpb : list) {
            tree.put(cpb.getSequence(), cpb);
        }
        return tree;
    }
}
