package cn.edu.dhu.acm.oj.persistence.dao;

import org.hibernate.Session;


public class BaseHibernateDAO implements IBaseHibernateDAO {
	
	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}
	
}