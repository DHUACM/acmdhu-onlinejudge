package dhuoj.primeserver.persistence.eao;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import dhuoj.primeserver.persistence.entity.User;

public class UserEAOImpl implements UserEAO {

    private static String EM_NAME = EAOFactory.ENTITY_MANAGER_NAME;
    private EntityManager em;

    public UserEAOImpl() {
        em = getEntityManager();
    }

	public void addUser(User user) {
        em.persist(user);
    }

    public void updateUser(User user) {
        em.merge(user);
    }

    public void removeUser(User user) {
        em.remove(em.merge(user));
    }

	public User findUser(String uid) {
        return em.find(User.class, uid);
    }

    public User chkLogin(String userId, String password) {
        Query q = em.createNamedQuery("User.findByUserIdAndPassword");
        q.setParameter("userId", userId);
        q.setParameter("password", password);
        List<User> l = q.getResultList();
        return l.size() == 0 ? null : l.get(0);
    }

    public List<User> findUsersInRange(int first, int max) {
        Query q = em.createNamedQuery("User.findAll");
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