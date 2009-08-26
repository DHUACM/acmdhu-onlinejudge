package dhuoj.primeserver.persistence.eao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import dhuoj.primeserver.persistence.entity.Compileinfo;

public class CompileinfoEAOImpl implements CompileinfoEAO {

    private static String EM_NAME = EAOFactory.ENTITY_MANAGER_NAME;
    private EntityManager em;

    public CompileinfoEAOImpl() {
        em = getEntityManager();
    }

    public void addCompileInfo(Compileinfo compileinfo) {
        em.persist(compileinfo);
    }

    public Compileinfo findCompileInfo(int slnID) {
        return em.find(Compileinfo.class, slnID);
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
