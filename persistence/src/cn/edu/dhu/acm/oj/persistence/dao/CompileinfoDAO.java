package cn.edu.dhu.acm.oj.persistence.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

import cn.edu.dhu.acm.oj.persistence.beans.CompileinfoBean;

public class CompileinfoDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(SourceCodeDAO.class);
    public static final String COMPILEINFO_BEAN = "cn.edu.dhu.acm.oj.persistence.beans.CompileinfoBean";

    public void addCompileInfo(CompileinfoBean cibean) {
        log.debug("saving CompileinfoBean instance");
		try {
            Session session = getSession();
            Transaction tx = session.beginTransaction();
            session.save(cibean);
            tx.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
    }

    public CompileinfoBean findCompileInfo(int sid) {
		log.debug("getting CompileInfoBean instance with id: " + sid);
		try {
			CompileinfoBean cibean = (CompileinfoBean) getSession().get(
                    COMPILEINFO_BEAN, sid);
			return cibean;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

    public static void main(String[] args) {
    }
}
