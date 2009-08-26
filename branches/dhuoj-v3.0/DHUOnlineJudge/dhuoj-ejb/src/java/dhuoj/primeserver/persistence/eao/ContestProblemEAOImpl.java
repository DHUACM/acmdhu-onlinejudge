package dhuoj.primeserver.persistence.eao;

import java.util.List;
import java.util.TreeMap;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import dhuoj.primeserver.persistence.entity.ContestProblem;

public class ContestProblemEAOImpl implements ContestProblemEAO {

    private static String EM_NAME = EAOFactory.ENTITY_MANAGER_NAME;
    private EntityManager em;

    public ContestProblemEAOImpl() {
        em = getEntityManager();
    }

    public void addContestProblem(ContestProblem cp) {
        em.persist(cp);
    }

    public void updateContestProblem(ContestProblem cp) {
        em.merge(cp);
    }

    public List<ContestProblem> findProblemListByContest(int contestID) {
        Query q = em.createNamedQuery("ContestProblem.findByContestId");
        q.setParameter("contestId", new Integer(contestID));
        return q.getResultList();
    }

    public TreeMap<Integer, ContestProblem> findProblemTreeMapByContest(int contestID) {
        List<ContestProblem> list = findProblemListByContest(contestID);
        TreeMap<Integer, ContestProblem> tree = new TreeMap();
        for (ContestProblem cp : list) {
            tree.put(cp.getSequence(), cp);
        }
        return tree;
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
