package com.dzmiter.musix.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Hibernate crud dao interface implementation
 * 
 * @author harchevnikov_m Date: 18.09.11 Time: 21:20
 */
@Repository
@Transactional
public class CrudDAOBean implements CrudDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Default constructor
	 */
	public CrudDAOBean() {}

	@SuppressWarnings("unchecked")
	public <T> T merge(T t) {
		return (T) currentSession().merge(t);
	}

	@SuppressWarnings("unchecked")
	public <T, PK extends Serializable> T find(Class<T> type, PK id) {			
		return (T) currentSession().get(type, id);
	}

	public <T, PK extends Serializable> void delete(Class<T> type, PK id) {
		Session currentSession = currentSession();
		Object object = currentSession.get(type, id);
		currentSession.delete(object);
	}

	@SuppressWarnings({ "unchecked" })
	public <T> List<T> list(Class<T> type) {
		return currentSession().createCriteria(type).list();			
	}
	
	private Session currentSession() {
		Session currentSession = sessionFactory.getCurrentSession();		
		return currentSession;
	}
	
	@SuppressWarnings({ "unchecked" })
	public <T> List<T> topList(Class<T> type, int amount, String field) {		
		return currentSession().createCriteria(type).
			addOrder(Order.desc(field)).setMaxResults(amount).list();
	}
	
}