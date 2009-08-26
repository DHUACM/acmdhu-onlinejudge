package dhuoj.primeserver.persistence.eao;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import dhuoj.primeserver.persistence.entity.Solution;

public class SolutionEAOImpl implements SolutionEAO {

    private static String EM_NAME = EAOFactory.ENTITY_MANAGER_NAME;
    private EntityManager em;

    public SolutionEAOImpl() {
        em = getEntityManager();
    }

    public void addSolution(Solution sln) {
        em.persist(sln);
    }

    public void updateSolution(Solution sln) {
        em.merge(sln);
    }

    public void removeSolution(Solution sln) {
        em.remove(em.merge(sln));
    }

    public Solution findSolution(int slnID) {
        return em.find(Solution.class, slnID);
    }

    public List<Solution> findSolutionsInRange(int first, int max) {
        Query q = em.createNamedQuery("Solution.findAll");
        q.setFirstResult(first);
        q.setMaxResults(max);
        return q.getResultList();
    }

    public List<Solution> findContestSolutionsInRange(int contestID, int first, int max) {
        Query q = em.createNamedQuery("Solution.findByContestId");
        q.setParameter("contestId", contestID);
        q.setFirstResult(first);
        q.setMaxResults(max);
        return q.getResultList();
    }

    public List<Solution> findContestLoginStatus(int contestID) {
        Query q = em.createNamedQuery("Solution.findContestLoginStatus");
        q.setParameter("contestId", contestID);
        return q.getResultList();
    }

    public List<Solution> findUnjudgedSolutionsInRange(int first, int max) {
        Query q = em.createNamedQuery("Solution.findUnjudgedSolution");
        q.setFirstResult(first);
        q.setMaxResults(max);
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