package com.gi2vi.gc.main.core.manager;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.gi2vi.gc.main.persistence.dao.DAO;
import com.gi2vi.gc.main.persistence.dao.DAO.Ord;

/**
 * Interfaz Manager con operaciones comunes para los servicios sobre los DAOS
 * 
 * @author Victor Huerta
 * 
 * @param <T>
 *            Tipo de entidad
 * @param <PK>
 *            Llave primaria de la entidad debe extender de Serializable
 */
public interface Manager<PK extends Serializable, T, TDAO extends DAO<PK, T>> {
	/**
	 * Metodo para guardar la entidad
	 * 
	 * @param entity
	 * @return
	 */
	Boolean save(T entity);

	/**
	 * Metodo para eliminar la entidad
	 * 
	 * @param entity
	 *            Entidad a borrar
	 * @return true Si se elimino correctamente false En otro caso
	 */
	Boolean delete(T entity);

	/**
	 * Metodo para recuperar todos las entidades
	 * 
	 * @return lista con todos los objetos de la tabla
	 */
	List<T> getAll();

	/**
	 * Metodo para recuberar una entidad por id
	 * 
	 * @param id
	 *            El id de la entidad que se busca
	 * @return La entidad
	 */
	T getById(PK id);

	/**
	 * Metodo para buscar registros con propiedades similares a la de la entidad
	 * que recibe.
	 * 
	 * @param entity
	 * @return
	 */
	List<T> searchByExample(T entity);

	/**
	 * Metodo para buscar registros con propiedades similares a la de la entidad
	 * que recibe, ordenarlos por el campo que manda con el limite y paginas
	 * dadas
	 * 
	 * @param entity
	 *            Ejemplo para la busqueda
	 * @param sortBy
	 *            Campor por el que se desea ordenar
	 * @param limit
	 *            Numero maximo de registros
	 * @param page
	 *            Numero de pagina
	 * @return
	 */
	List<T> searchByExamplePages(T entity, String sortBy, Ord ord,
			Integer limit, Integer start);
	
	List<T> searchByExamplePages(T entity, String sortBy, Ord ord,
			Integer limit, Integer start, Map<String, Object> associations);

	/**
	 * Cuenta todos los registros de la tabla
	 * 
	 * @return Numero de registros en la tabla
	 */
	Integer countAll();

	/**
	 * Cuenta todos los resultados que coinciden con un ejemplo
	 * 
	 * @param entity
	 *            Entidad de ejemplo para la busqueda
	 * @return Numero de registros encontrados
	 */
	Integer countByExample(T entity);

	/**
	 * Numero de paginas que se obtendran buscando con el ejemplo dado y usando
	 * un limite
	 * 
	 * @param entity
	 *            Entidad de ejemplo para la busqueda
	 * @param limit
	 *            Limite de registros por pagina
	 * @return
	 */
	Integer countByExamplePages(T entity, Integer limit);

}