package com.gi2vi.gc.main.persistence.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

/**
 * Interfaz del dao generico
 * 
 * @author Victor Huerta
 * 
 * @param <T>
 *            Tipo de objeto
 * @param <PK>
 *            Llave primaria que tiene que extender de serializable
 */

public interface DAO<PK extends Serializable, T> {
	/**
	 * Enumerate para saber de que forma ordenar los resultados ASC o DESC
	 * 
	 * @author Victor Huerta
	 * 
	 */
	public enum Ord {
		ASCENDING("ASC"), DESCENDING("DESC"), UNSORTED("");
		private final String ord;

		private Ord(String ord) {
			this.ord = ord;
		}

		public String getOrd() {
			return ord;
		}
	}

	/**
	 * Metodo para recuperar la session actual
	 * 
	 * @return la session actual
	 */
	Session getCurrentSession();

	/**
	 * Metodo para guardar un registro
	 * 
	 * @param entity
	 *            Entidad a guardar
	 * @return true si se guardo correctamente false en otro caso
	 */
	Boolean save(T entity);

	/**
	 * Metodo para eliminar un registro
	 * 
	 * @param entity
	 *            Registro a borrar
	 * @return true si se elimino correctamente false en otro caso
	 */
	Boolean delete(T entity);

	/**
	 * Metodo para recuperar todos los objetos de la tabla
	 * 
	 * @return lista con todos los objetos de la tabla
	 */
	List<T> getAll();

	/**
	 * Metodo para recuberar un objeto por el id, este metodo esta destinado a
	 * los objetos que ya tienen definido el tipo T
	 * 
	 * @param id
	 *            El id el objeto que se busca
	 * @return El objeto encontrado
	 */
	T getById(PK id);

	/**
	 * Con este metodo se pueden buscar registros con propiedades similares a
	 * las del objeto que se pasa, no sirve para buscaquedas por id.
	 * 
	 * @param entity
	 *            Entidad con propiedades que se van a buscar
	 * @return Lista de entidades encontradas
	 */
	List<T> searchByExample(T entity);

	/**
	 * Metodo para buscar registros con propiedades similares a la del objeto
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
			Integer limit, Integer page);
	
	List<T> searchByExamplePages(T entity, String sortBy, Ord ord,
			Integer limit, Integer page, Map<String, Object> associations);

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

	/**
	 * Retorna la clase del tipo generico, la clase de la entidad. Destinada a
	 * solo funcionar cuando se implemente esta interfaz definiendo el tipo T
	 * 
	 * @return Clase del tipo generico
	 */
	Class<?> getEntityClass();

}