package com.gi2vi.gc.main.core.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gi2vi.gc.main.core.manager.PaisManager;
import com.gi2vi.gc.main.persistence.dao.PaisDAO;
import com.gi2vi.gc.main.persistence.model.Pais;

@Component("paisManager")
@Transactional(propagation = Propagation.REQUIRED)
public class PaisManagerImpl extends AbstractManager<Integer, Pais, PaisDAO>
		implements PaisManager {

	@Autowired
	private PaisDAO dao;

	@Override
	public PaisDAO getDAO() {
		return dao;
	}

}
