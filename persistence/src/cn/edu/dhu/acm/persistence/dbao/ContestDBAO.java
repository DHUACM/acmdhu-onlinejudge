/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.edu.dhu.acm.persistence.dbao;

import cn.edu.dhu.acm.persistence.dbao.exceptions.NonexistentEntityException;
import cn.edu.dhu.acm.persistence.dbao.exceptions.PreexistingEntityException;
import cn.edu.dhu.acm.persistence.entity.Contest;
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
public class ContestDBAO {

    public ContestDBAO() {
        emf = Persistence.createEntityManagerFactory("dhuojPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Contest contest) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(contest);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findContest(contest.getContestId()) != null) {
                throw new PreexistingEntityException("Contest " + contest + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Contest contest) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            contest = em.merge(contest);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = contest.getContestId();
                if (findContest(id) == null) {
                    throw new NonexistentEntityException("The contest with id " + id + " no longer exists.");
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
            Contest contest;
            try {
                contest = em.getReference(Contest.class, id);
                contest.getContestId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contest with id " + id + " no longer exists.", enfe);
            }
            em.remove(contest);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Contest> findContestEntities() {
        return findContestEntities(true, -1, -1);
    }

    public List<Contest> findContestEntities(int maxResults, int firstResult) {
        return findContestEntities(false, maxResults, firstResult);
    }

    private List<Contest> findContestEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Contest as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Contest findContest(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Contest.class, id);
        } finally {
            em.close();
        }
    }

    public int getContestCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Contest as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
