package org.emn.javaee.crud;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.emn.javaee.models.User;
import org.emn.javaee.tools.Em;

public class UserCrud implements Crud<User>{
	/**
	 * EntityManager
	 */
	private EntityManager em = Em.getInstance().getEm();

	/**
	 * Constructor
	 */
	public UserCrud(){};

	/**
	 * Creates a user
	 * @param user
	 * @return the newly created user
	 */
	public User create(User user)
	{
		EntityTransaction t = this.em.getTransaction();
		t.begin();
		this.em.persist(user);
		t.commit();
		return user;
	}

	/**
	 * Deletes a user
	 * @param user
	 * @return true if the user has been deleted, else false
	 */
	public boolean remove(User user)
	{
		try 
		{
			EntityTransaction t = this.em.getTransaction();
			t.begin();
			this.em.remove(user);
			t.commit();
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	/**
	 * Removes a user found by id
	 * @param id 
	 * @return true if the user has been deleted, false if not found or not deleted
	 */
	public boolean remove(int id) {
		User user = this.find(id);
		if(user == null) return false;
		try 
		{
			EntityTransaction t = this.em.getTransaction();
			t.begin();
			this.em.remove(user);
			t.commit();
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	/**
	 * Retrieve a user by its id
	 */
	public User find(int id) {
		return this.em.find(User.class, id);
	}

	/**
	 * Updates a user
	 * @param user
	 * @return the updated user
	 */
	public User update(User user) {
		EntityTransaction t = this.em.getTransaction();
		t.begin();
		User u = this.em.merge(user);
		t.commit();
		return u;
	}
}
