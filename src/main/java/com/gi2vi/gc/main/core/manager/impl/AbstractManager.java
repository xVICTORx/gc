package com.gi2vi.gc.main.core.manager.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gi2vi.gc.main.core.manager.Manager;
import com.gi2vi.gc.main.persistence.dao.DAO;
import com.gi2vi.gc.main.persistence.dao.DAO.Ord;

/**
 * Manager generico con las operaciones definidas en Manager todos los managers
 * deben extender de este e implementar el metodo para recuperar el dao extiende
 * de LazyDataModel para las respuestas Lazy de las datatables de primefaces
 * 
 * @author Victor Huerta
 * 
 * @param <PK>
 *            Llave primaria de la entidad
 * @param <T>
 *            Tipo de la entidad
 * @param <TDAO>
 *            Tipo del dao para la entidad
 */
@Transactional(propagation = Propagation.REQUIRED)
public abstract class AbstractManager<PK extends Serializable, T, TDAO extends DAO<PK, T>>
		implements Manager<PK, T, TDAO> {

	/**
	 * Debe retornar el dao para las operaciones
	 * 
	 * @return
	 */
	public abstract TDAO getDAO();

	public Boolean save(T entity) {
		return getDAO().save(entity);
	}

	public Boolean delete(T entity) {
		return getDAO().delete(entity);
	}

	public List<T> getAll() {
		return getDAO().getAll();
	}

	public T getById(PK id) {
		return getDAO().getById(id);
	}

	public List<T> searchByExample(T entity) {
		return getDAO().searchByExample(entity);
	}

	public List<T> searchByExamplePages(T entity, String sortBy, Ord ord,
			Integer limit, Integer start) {
		return getDAO().searchByExamplePages(entity, sortBy, ord, limit, start);
	}

	public java.util.List<T> searchByExamplePages(T entity, String sortBy,
			DAO.Ord ord, Integer limit, Integer start,
			java.util.Map<String, Object> associations) {
		return getDAO().searchByExamplePages(entity, sortBy, ord, limit, start,
				associations);
	};

	public Integer countAll() {
		return getDAO().countAll();
	}

	public Integer countByExample(T entity) {
		return getDAO().countByExample(entity);
	}

	public Integer countByExamplePages(T entity, Integer limit) {
		return getDAO().countByExamplePages(entity, limit);
	}

}
