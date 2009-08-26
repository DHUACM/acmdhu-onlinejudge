package dhuoj.primeserver.persistence.eao;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import dhuoj.primeserver.persistence.entity.ContestReservation;

public class ContestReservationEAOImpl implements ContestReservationEAO {

    private static String EM_NAME = EAOFactory.ENTITY_MANAGER_NAME;
    private EntityManager em;

    public ContestReservationEAOImpl() {
        em = getEntityManager();
    }

    public List<ContestReservation> findContestReservationList(int contestID) {
        Query q = em.createNamedQuery("ContestReservation.findByContestId");
        q.setParameter("contestId", new Integer(contestID));
        return q.getResultList();
    }

    public List<ContestReservation> findUserReservedContest(String userId) {
        Query q = em.createNamedQuery("ContestReservation.findByUserId");
        q.setParameter("userId", userId);
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
