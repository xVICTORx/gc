package com.gi2vi.gc.main.persistence.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gi2vi.gc.main.persistence.dao.EstadoDAO;
import com.gi2vi.gc.main.persistence.model.Estado;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class EstadoDAOHibernate extends AbstractDAOHibernate<Integer, Estado>
		implements EstadoDAO {

}
