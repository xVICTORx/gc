package com.gi2vi.gc.main.persistence.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.gi2vi.gc.main.persistence.dao.DAO;

/**
 * Clase abstracta que implementa los metodos de la interfaz DAO todos los DAO's
 * debe extender de esta ya que contiene las operaciones comunes definidas
 * 
 * @author Victor Huerta
 * 
 * @param <T>
 *            Tipo de objeto
 * @param <PK>
 *            Llave primaria que implemente serializable
 * 
 * @see DAO
 */
public abstract class AbstractDAOHibernate<PK extends Serializable, T>
		implements DAO<PK, T> {

	@Autowired
	private final SessionFactory sessionFactory = null;

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	};

	public Boolean save(T entity) {
		Boolean estatus = Boolean.TRUE;
		try {
			getCurrentSession().saveOrUpdate(entity);

		} catch (HibernateException e) {
			estatus = Boolean.FALSE;
		}
		return estatus;
	}

	public Boolean delete(T entity) {
		Boolean estatus = Boolean.TRUE;
		try {
			getCurrentSession().delete(entity);

		} catch (HibernateException e) {
			estatus = Boolean.FALSE;
		}
		return estatus;
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return getCurrentSession().createCriteria(getEntityClass()).list();
	}

	@SuppressWarnings("unchecked")
	public T getById(PK id) {
		return (T) getCurrentSession().get(getEntityClass(), id);
	}

	@SuppressWarnings("unchecked")
	public List<T> searchByExample(T entity) {
		Criteria criteria = getCurrentSession()
				.createCriteria(getEntityClass());
		criteria.add(Example.create(entity).enableLike(MatchMode.START));
		return criteria.list();
	}

	public List<T> searchByExamplePages(T entity, String sortBy, Ord ord,
			Integer limit, Integer start) {
		Map<String, Object> associations = new HashMap<String, Object>();
		return searchByExamplePages(entity, sortBy, ord, limit, start, associations);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> searchByExamplePages(T entity, String sortBy, Ord ord,
			Integer limit, Integer start, Map<String, Object> associations) {
		Criteria criteria = getCurrentSession()
				.createCriteria(getEntityClass());
		criteria.add(Example.create(entity).enableLike(MatchMode.START));
		for(Entry<String, Object> entry : associations.entrySet()){
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		if (sortBy != null) {
			switch (ord) {
			case ASCENDING:
				criteria.addOrder(Order.asc(sortBy));
				break;
			case DESCENDING:
				criteria.addOrder(Order.desc(sortBy));
				break;
			}
		}
		criteria.setFirstResult(start);
		criteria.setMaxResults(limit);
		return criteria.list();
	}

	public Integer countAll() {
		return getAll().size();
	}

	public Integer countByExample(T entity) {
		return searchByExample(entity).size();
	}

	public Integer countByExamplePages(T entity, Integer limit) {
		List<T> entities = searchByExample(entity);
		Double count = Math.ceil((double) entities.size() / (double) limit);
		return count.intValue();
	}

	public Class<?> getEntityClass() {
		ParameterizedType parameterizedType = (ParameterizedType) this
				.getClass().getGenericSuperclass();
		return (Class<?>) parameterizedType.getActualTypeArguments()[1];
	}
}
