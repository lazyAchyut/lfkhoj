package com.lftechnology.lfkhoj;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *  This class is the central injection point for all Dependency Injection
 *
 */
public class Resources {
	/*
	 * The JPA Entity Manager created from persistence.xml
	 */
	@PersistenceContext
	@Produces
	private EntityManager manager;
}
