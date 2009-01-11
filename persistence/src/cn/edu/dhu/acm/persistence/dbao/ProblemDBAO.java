/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.edu.dhu.acm.persistence.dbao;

import cn.edu.dhu.acm.persistence.dbao.exceptions.NonexistentEntityException;
import cn.edu.dhu.acm.persistence.dbao.exceptions.PreexistingEntityException;
import cn.edu.dhu.acm.persistence.entity.Problem;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author yhu
 */
public class ProblemDBAO {

    public ProblemDBAO() {
        emf = Persistence.createEntityManagerFactory("dhuojPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Problem problem) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(problem);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProblem(problem.getProblemId()) != null) {
                throw new PreexistingEntityException("Problem " + problem + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Problem problem) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            problem = em.merge(problem);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = problem.getProblemId();
                if (findProblem(id) == null) {
                    throw new NonexistentEntityException("The problem with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Problem problem;
            try {
                problem = em.getReference(Problem.class, id);
                problem.getProblemId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The problem with id " + id + " no longer exists.", enfe);
            }
            em.remove(problem);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Problem> findProblemEntities() {
        return findProblemEntities(true, -1, -1);
    }

    public List<Problem> findProblemEntities(int maxResults, int firstResult) {
        return findProblemEntities(false, maxResults, firstResult);
    }

    private List<Problem> findProblemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Problem as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Problem findProblem(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Problem.class, id);
        } finally {
            em.close();
        }
    }

    public int getProblemCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Problem as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
