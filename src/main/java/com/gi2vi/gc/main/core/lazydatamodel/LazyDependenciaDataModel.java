package com.gi2vi.gc.main.core.lazydatamodel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gi2vi.gc.main.core.manager.DependenciaManager;
import com.gi2vi.gc.main.core.manager.EstadoManager;
import com.gi2vi.gc.main.persistence.dao.DAO.Ord;
import com.gi2vi.gc.main.persistence.model.Dependencia;
import com.gi2vi.gc.main.persistence.model.Dependencia.DependenciaMapping;
import com.gi2vi.gc.main.persistence.model.Estado;

@Component("lazyDependenciaDataModel")
public class LazyDependenciaDataModel extends LazyDataModel<Dependencia> {

	private static final long serialVersionUID = 281045358427030783L;

	@Autowired
	private DependenciaManager dependenciaManager;
	@Autowired
	private EstadoManager estadoManager;

	@Override
	public List<Dependencia> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		Dependencia dependencia = new Dependencia();
		Map<String, Object> associations = new HashMap<String, Object>();
		for (Entry<String, String> filter : filters.entrySet()) {
			try {
				switch (DependenciaMapping.valueOf(filter.getKey())) {
				case cp:
					dependencia.setCp(filter.getValue());
					break;
				case direccion:
					dependencia.setDireccion(filter.getValue());
					break;
				case estado:
					Estado estado = estadoManager.getById(Integer
							.valueOf(filter.getValue()));
					if(estado != null){
						associations.put(DependenciaMapping.estado.getField(), estado);
					}
					break;
				case municipio:
					dependencia.setMunicipio(filter.getValue());
					break;
				case nombre:
					dependencia.setNombre(filter.getValue());
					break;
				case telefono:
					dependencia.setTelefono(filter.getValue());
					break;
				default:
					break;
				}
			} catch (IllegalArgumentException e) {
				System.out.println("Excepcion: Filtro no hallado");
			}
		}
		this.setRowCount(dependenciaManager.countAll());
		this.setPageSize(pageSize);
		return dependenciaManager.searchByExamplePages(dependencia, sortField,
				Ord.valueOf(sortOrder.name()), pageSize, first, associations);
	}
}
