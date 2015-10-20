package org.emn.javaee.crud;

public interface Crud<Entity> {
	/**
	 * Creates the entity
	 * @param entity
	 * @return the newly created entity
	 */
	public Entity create(Entity entity);
	
	/**
	 * Deletes the entity
	 * @param entity
	 * @return true if the entity has been deleted, else false
	 */
	public boolean remove(Entity entity);
	
	/**
	 * Removes the entity found by id
	 * @param id 
	 * @return true if the entity has been deleted, false if not found or not deleted
	 */
	public boolean remove(int id);
	
	/**
	 * Retrieve the entity by its id
	 */
	public Entity find(int id);
	
	/**
	 * Updates the entity
	 * @param entity
	 * @return the updated entity
	 */
	public Entity update(Entity entity);
}
