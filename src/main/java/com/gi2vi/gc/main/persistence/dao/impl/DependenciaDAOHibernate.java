package com.gi2vi.gc.main.persistence.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gi2vi.gc.main.persistence.dao.DependenciaDAO;
import com.gi2vi.gc.main.persistence.model.Dependencia;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class DependenciaDAOHibernate extends
		AbstractDAOHibernate<Integer, Dependencia> implements DependenciaDAO {

}