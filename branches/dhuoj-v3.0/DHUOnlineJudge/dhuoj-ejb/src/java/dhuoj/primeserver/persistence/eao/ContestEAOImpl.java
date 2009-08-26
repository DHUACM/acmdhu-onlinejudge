package dhuoj.primeserver.persistence.eao;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import dhuoj.primeserver.persistence.entity.Contest;

public class ContestEAOImpl implements ContestEAO {

    private static String EM_NAME = EAOFactory.ENTITY_MANAGER_NAME;
    private EntityManager em;

    public ContestEAOImpl() {
        em = getEntityManager();
    }

	public void addContest(Contest contest) {
        em.persist(contest);
    }

    public void updateContest(Contest contest) {
        em.merge(contest);
    }

    public void removeContest(Contest contest) {
        em.remove(em.merge(contest));
    }

    public Contest findContest(int contestID) {
        return em.find(Contest.class, contestID);
    }

    public List<Contest> findContestInRange(int first, int max) {
        if (max > 50000000) max = 50000000;
        Query q = em.createNamedQuery("Contest.findAll");
        q.setFirstResult(first);
        q.setMaxResults(max);
        return q.getResultList();
    }

    public List<Contest> findContestByStatus(int status) {
        Query q = em.createNamedQuery("Contest.findByStatus");
        q.setParameter("status", new Integer(status));
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