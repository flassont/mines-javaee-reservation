package org.emn.javaee.crud;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import java.lang.reflect.*;
import java.util.List;

import org.emn.javaee.tools.Em;

/**
 * Generic CRUD class
 */
public class GenericCrud<Entity> {
	/**
	 * EntityManager
	 */
	protected EntityManager em = Em.getInstance().getEm();
	
	/**
	 * Dynamic class
	 */
	private Class<Entity> entityClass;

	/**
	 * Constructor
	 */
	public GenericCrud(){
		/**
		 * Get the current class at runtime
		 */
		entityClass = ((Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	};

	/**
	 * Creates or update the entity
	 * @param entity
	 * @return the newly created entity
	 */
	public Entity create(Entity entity)
	{
		EntityTransaction t = this.em.getTransaction();
		t.begin();
		this.em.merge(entity);
		t.commit();
		return entity;
	}

	/**
	 * Deletes the entity
	 * @param entity
	 * @return true if the entity has been deleted, else false
	 */
	public boolean remove(Entity entity)
	{
		try 
		{
			EntityTransaction t = this.em.getTransaction();
			t.begin();
			this.em.remove(entity);
			t.commit();
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	/**
	 * Removes the entity found by id
	 * @param id 
	 * @return true if the entity has been deleted, false if not found or not deleted
	 */
	public boolean remove(int id) {
		Entity entity = (Entity) this.find(id);
		if(entity == null) return false;
		try 
		{
			EntityTransaction t = this.em.getTransaction();
			t.begin();
			this.em.remove(entity);
			t.commit();
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	/**
	 * Retrieve the entity by its id
	 */
	public Entity find(int id) {
		return this.em.find(this.entityClass, id);
	}
	
	/**
	 * Find all the entities
	 * @return collection of entities
	 */
	public List<Entity> findAll()
	{
		Query query = this.em.createNamedQuery( this.entityClass.getSimpleName() + ".findAll" );
		return query.getResultList();
	}
}
