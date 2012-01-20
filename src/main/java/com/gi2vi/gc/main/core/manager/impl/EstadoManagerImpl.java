package com.gi2vi.gc.main.core.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gi2vi.gc.main.core.manager.EstadoManager;
import com.gi2vi.gc.main.persistence.dao.EstadoDAO;
import com.gi2vi.gc.main.persistence.model.Estado;

@Component("estadoManager")
@Transactional(propagation = Propagation.REQUIRED)
public class EstadoManagerImpl extends
		AbstractManager<Integer, Estado, EstadoDAO> implements EstadoManager {

	@Autowired
	private EstadoDAO dao;

	@Override
	public EstadoDAO getDAO() {
		return dao;
	}

}