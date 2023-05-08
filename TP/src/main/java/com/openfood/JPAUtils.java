package com.openfood;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtils {
	private final static JPAUtils INSTANCE = new JPAUtils();
	private JPAUtils() {}
	public static JPAUtils getInstance() {
		return INSTANCE;
	}
	
	//JPA
	private final static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("OpenFood");
	private final static EntityManager entityManager = entityManagerFactory.createEntityManager();		
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	//Todo methode de close enity manager
	
			
}
