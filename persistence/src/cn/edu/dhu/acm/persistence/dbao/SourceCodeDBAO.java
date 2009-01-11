/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.edu.dhu.acm.persistence.dbao;

import cn.edu.dhu.acm.persistence.dbao.exceptions.NonexistentEntityException;
import cn.edu.dhu.acm.persistence.dbao.exceptions.PreexistingEntityException;
import cn.edu.dhu.acm.persistence.entity.SourceCode;
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
public class SourceCodeDBAO {

    public SourceCodeDBAO() {
        emf = Persistence.createEntityManagerFactory("dhuojPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SourceCode sourceCode) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(sourceCode);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSourceCode(sourceCode.getSolutionId()) != null) {
                throw new PreexistingEntityException("SourceCode " + sourceCode + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SourceCode sourceCode) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            sourceCode = em.merge(sourceCode);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sourceCode.getSolutionId();
                if (findSourceCode(id) == null) {
                    throw new NonexistentEntityException("The sourceCode with id " + id + " no longer exists.");
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
            SourceCode sourceCode;
            try {
                sourceCode = em.getReference(SourceCode.class, id);
                sourceCode.getSolutionId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sourceCode with id " + id + " no longer exists.", enfe);
            }
            em.remove(sourceCode);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SourceCode> findSourceCodeEntities() {
        return findSourceCodeEntities(true, -1, -1);
    }

    public List<SourceCode> findSourceCodeEntities(int maxResults, int firstResult) {
        return findSourceCodeEntities(false, maxResults, firstResult);
    }

    private List<SourceCode> findSourceCodeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from SourceCode as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public SourceCode findSourceCode(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SourceCode.class, id);
        } finally {
            em.close();
        }
    }

    public int getSourceCodeCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from SourceCode as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
