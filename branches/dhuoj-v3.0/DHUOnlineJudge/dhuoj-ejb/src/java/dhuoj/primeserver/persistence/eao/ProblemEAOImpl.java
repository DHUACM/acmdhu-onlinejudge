package dhuoj.primeserver.persistence.eao;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import dhuoj.primeserver.persistence.entity.Problem;

public class ProblemEAOImpl implements ProblemEAO {

    private static String EM_NAME = EAOFactory.ENTITY_MANAGER_NAME;
    private EntityManager em;

    public ProblemEAOImpl() {
        em = getEntityManager();
    }

    public void addProblem(Problem problem) {
        em.persist(problem);
    }

    public void updateProblem(Problem problem) {
        em.merge(problem);
    }

    public void removeProblem(Problem problem) {
        em.remove(em.merge(problem));
    }

    public Problem findProblem(int problemID) {
        return em.find(Problem.class, problemID);
    }

    public List<Problem> findProblemInRange(int first, int max) {
        Query q = em.createNamedQuery("Problem.findAll");
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