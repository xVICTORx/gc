package com.gi2vi.projectgc.test.common;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clase de configuracion para los test, carga el contexto de spring, definido
 * en file:src/main/webapp/WEB-INF/context/context.xml, heredar esta clase para
 * cada test. Correr con JUnit.
 * 
 * @author Victor Huerta
 * @since 04-11-2011
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/context/context.xml")
public class ConfigurationTest {

	@Autowired
	protected SessionFactory sessionFactory;

	@Before
	public void onSetUp() throws Exception {
	}

	@After
	public void onTearDown() throws Exception {
	}
	
	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	public void testComun() {
		System.out.println("Test");
	}

}
