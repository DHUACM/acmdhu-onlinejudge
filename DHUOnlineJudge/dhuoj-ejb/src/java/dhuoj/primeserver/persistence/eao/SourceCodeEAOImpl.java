package dhuoj.primeserver.persistence.eao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import dhuoj.primeserver.persistence.entity.SourceCode;

public class SourceCodeEAOImpl implements SourceCodeEAO {

    private static String EM_NAME = EAOFactory.ENTITY_MANAGER_NAME;
    private EntityManager em;

    public SourceCodeEAOImpl() {
        em = getEntityManager();
    }
    
    public void addSourceCode(SourceCode src) {
        em.persist(src);
    }

    public SourceCode findSourceCode(int slnID) {
        return em.find(SourceCode.class, slnID);
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