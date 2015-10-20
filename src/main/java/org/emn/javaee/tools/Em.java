package org.emn.javaee.tools;

/*
 * This class allows to get a singleton version of the entity manager
 */
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Em {
	/**
	 * EntityManager em
	 */
	private EntityManager em;
	
	/**
	 * static Singleton instance
	 */
	private static Em instance;

	/**
	 * Private constructor for singleton
	 */
	private Em() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("primary");
		this.em = emf.createEntityManager();
	}

	/**
	 * Static getter method for retrieving the singleton instance
	 */
	public static Em getInstance() {
		if (instance == null) {
			instance = new Em();
		}
		return instance;
	}
	
	/**
	 * Get the EntityManager
	 * @return em
	 */
	public EntityManager getEm()
	{
		return this.em;
	}

}
