/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.edu.dhu.acm.persistence.dbao;

import cn.edu.dhu.acm.persistence.dbao.exceptions.NonexistentEntityException;
import cn.edu.dhu.acm.persistence.entity.Solution;
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
public class SolutionDBAO implements java.io.Serializable {

    public SolutionDBAO() {
        emf = Persistence.createEntityManagerFactory("dhuojPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Solution solution) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(solution);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Solution solution) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            solution = em.merge(solution);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = solution.getSolutionId();
                if (findSolution(id) == null) {
                    throw new NonexistentEntityException("The solution with id " + id + " no longer exists.");
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
            Solution solution;
            try {
                solution = em.getReference(Solution.class, id);
                solution.getSolutionId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The solution with id " + id + " no longer exists.", enfe);
            }
            em.remove(solution);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Solution> findSolutionEntities() {
        return findSolutionEntities(true, -1, -1);
    }

    public List<Solution> findSolutionEntities(int maxResults, int firstResult) {
        return findSolutionEntities(false, maxResults, firstResult);
    }

    private List<Solution> findSolutionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Solution as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Solution findSolution(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Solution.class, id);
        } finally {
            em.close();
        }
    }

    public int getSolutionCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Solution as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
