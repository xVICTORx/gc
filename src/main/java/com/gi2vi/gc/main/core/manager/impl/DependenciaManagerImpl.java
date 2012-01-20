package com.gi2vi.gc.main.core.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gi2vi.gc.main.core.manager.DependenciaManager;
import com.gi2vi.gc.main.persistence.dao.DependenciaDAO;
import com.gi2vi.gc.main.persistence.model.Dependencia;

@Component("dependenciaManager")
@Transactional(propagation = Propagation.REQUIRED)
public class DependenciaManagerImpl extends
		AbstractManager<Integer, Dependencia, DependenciaDAO> implements
		DependenciaManager {

	@Autowired
	private DependenciaDAO dao;

	@Override
	public DependenciaDAO getDAO() {
		return dao;
	}

}
