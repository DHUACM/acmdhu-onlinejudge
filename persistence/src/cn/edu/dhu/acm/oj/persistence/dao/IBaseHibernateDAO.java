package cn.edu.dhu.acm.oj.persistence.dao;

import org.hibernate.Session;


public interface IBaseHibernateDAO {
	public Session getSession();
}