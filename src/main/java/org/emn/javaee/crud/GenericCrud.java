package org.emn.javaee.crud;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;

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
	 * Creates or updates the entity
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
		Query query = this.em.createNamedQuery(this.entityClass.getSimpleName() + ".findAll");
		return query.getResultList();
	}

	public List<Entity> filter(Map<String, Object> filters) {
		Set<String> keys = filters.keySet();
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<Entity> query = cb.createQuery(this.entityClass);

		Root<Entity> root = query.from(this.entityClass);
		EntityType<Entity> entityType = root.getModel();
		Expression<Boolean> condition = cb.conjunction();
		for (Attribute<? super Entity,?> attribute: entityType.getAttributes()){
			
			String attributeName = attribute.getName();
			System.out.println("generic crud attribut : " +attributeName+" => " + attribute.isAssociation()+ " | "+attribute.getJavaType());
			// No filter, reach next attribute
			if(!keys.contains(attributeName)) {
				System.out.println("next");
				continue;
			}

			// Exists, check whether use like (for String)
			// or equal (other types)
			Object expectedValue = filters.get(attributeName);
			if(attribute.isAssociation() && attribute.getName().equals("responsible"))
			{
				System.out.println("*******************JOIN*******************");
				Join<Object, org.emn.javaee.models.AbstractModel> association = root.join(attribute.getName());
				condition = cb.and(condition, cb.equal(association.get("firstName"),(String) expectedValue));
				//condition = cb.and(condition, cb.like(association.get("firstName"),(String) expectedValue));

			}
			else if(attribute.getJavaType() == String.class) {
				System.out.println("C'est un string");
				condition = cb.and(condition, cb.like(root.get(attributeName).as(String.class), "%" + (String) expectedValue + "%"));
			} else if (attribute.getJavaType() == Boolean.class) {
				System.out.println("c'est un boolean");
				Expression<Boolean> expectedExpression;
				if ((Boolean) expectedValue) {
					System.out.println("la valeur est vraie");
					expectedExpression = cb.isTrue(root.get(attributeName).as(Boolean.class));
				} else {
					System.out.println("la valeur est fausse");
					expectedExpression = cb.isFalse(root.get(attributeName).as(Boolean.class));
				}
				condition = cb.and(condition, expectedExpression);
			} /*else {
				System.out.println("ni string ni boolean");
				condition = cb.and(condition, cb.equal(root.get(attributeName), expectedValue));
			}*/
		}

		return this.em.createQuery(query.where(condition)).getResultList();
	}
}
