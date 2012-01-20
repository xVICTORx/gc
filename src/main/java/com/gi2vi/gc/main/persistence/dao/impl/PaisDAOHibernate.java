package com.gi2vi.gc.main.persistence.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gi2vi.gc.main.persistence.dao.PaisDAO;
import com.gi2vi.gc.main.persistence.model.Pais;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class PaisDAOHibernate extends AbstractDAOHibernate<Integer, Pais>
		implements PaisDAO {

}