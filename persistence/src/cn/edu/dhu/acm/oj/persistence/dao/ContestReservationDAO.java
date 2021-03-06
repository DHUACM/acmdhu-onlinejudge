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
import cn.edu.dhu.acm.oj.persistence.beans.ContestReservationBean;

public class ContestReservationDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(ContestReservationDAO.class);

    public List<ContestReservationBean> findContestReservationList(int cid){
        try {
            Session session=getSession();
            Transaction tx = session.beginTransaction();
            Query query=session.createQuery("from ContestReservationBean where contestId=" + cid);
            List<ContestReservationBean> rs=query.list();
            tx.commit();
            return rs;
        } catch(Exception e) {
            log.error("find ProblemsByContest fails", e);
            return null;
        }
    }

    public List<ContestReservationBean> findUserReservedContest(String userId){
        try {
            Session session=getSession();
            Transaction tx = session.beginTransaction();
            Query query=session.createQuery("from ContestReservationBean where userId='" + userId + "'");
            List<ContestReservationBean> rs=query.list();
            tx.commit();
            return rs;
        } catch(Exception e) {
            log.error("find user reserved contest fails", e);
            return null;
        }
    }

    public boolean checkUserReservedContest(String userId, int contestId){
        try {
            Session session=getSession();
            Transaction tx = session.beginTransaction();
            Query query=session.createQuery("from ContestReservationBean where userId='" + userId +
                    "' and contestId=" + contestId);
            List<ContestReservationBean> rs=query.list();
            tx.commit();
            return rs.size() == 0 ? false : true;
        } catch(Exception e) {
            log.error("find user reserved contest fails", e);
            return false;
        }
    }
}
