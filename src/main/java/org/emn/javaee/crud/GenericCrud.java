package org.emn.javaee.crud;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;

import org.emn.javaee.models.AbstractModel;
import org.emn.javaee.tools.DateSearch;
import org.emn.javaee.tools.Em;
import org.emn.javaee.tools.ValueParameter;

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
	@SuppressWarnings("unchecked")
	public GenericCrud(){
		/**
		 * Get the current class at runtime
		 */
		entityClass = ((Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
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
	@SuppressWarnings("unchecked")
	public List<Entity> findAll()
	{
		Query query = this.em.createNamedQuery(this.entityClass.getSimpleName() + ".findAll");
		return query.getResultList();
	}

	public List<Entity> filter(Map<String, Object> filters) {
		Set<String> keys =  filters.keySet();
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<Entity> query = cb.createQuery(this.entityClass);

		Root<Entity> root = query.from(this.entityClass);
		EntityType<Entity> entityType = root.getModel();
		Expression<Boolean> condition = cb.conjunction();
		for (Attribute<? super Entity,?> attribute: entityType.getAttributes()){
			String attributeName = attribute.getName();
			System.out.println("generic crud attribut : " +attributeName);
			// No filter, reach next attribute
			if(!keys.contains(attributeName)) {
				System.out.println("next");
				continue;
			}

			// Exists, check whether use like (for String)
			// or equal (other types)
			Object expectedValue = ((ValueParameter) filters.get(attributeName)).getValue();
			// handle the relations between the entities
			if(attribute.isAssociation())
			{
				System.out.println("Filtre sur "+attribute.getName());
				// join the mapped entity
				Join<Object, org.emn.javaee.models.AbstractModel> association = root.join(attribute.getName());
				// the filters of the joined entity
				String[] associationFilters;
				try {
					// get the filters of the joined entity separated by ","
					System.out.println("JAVATYPE => " +association.getJavaType());
					associationFilters = association.getJavaType().newInstance().getFilterBy().split(",");
					System.out.println("Filtres : "+associationFilters.toString());
					// array of predicate
					Predicate[] predicates = new Predicate[associationFilters.length];
					int i = 0;
					// loop through the array of filters
					for(String s:associationFilters)
					{
						// add the predicate according to the current filter
						predicates[i]=cb.like(association.get(s).as(String.class), "%" + (String) expectedValue + "%");
						i++;
					}
					// finally, make a or condition on all the filters
					condition = cb.and(condition, cb.or(predicates));
				} catch(Exception e)
				{
					// todo
					System.out.println("!!!!!!!!!!!!!!!!!!!EXCEPTION");
					e.printStackTrace();
				}
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
			} 
			 else if (attribute.getJavaType() == Date.class)
				{
					switch((DateSearch)((ValueParameter) filters.get(attributeName)).getParameter())
					{
					case FROM:
						condition = cb.and(condition, cb.greaterThanOrEqualTo(root.get(attributeName).as(Date.class), (Date) expectedValue));
					break;
					case TO:
						condition = cb.and(condition, cb.lessThanOrEqualTo(root.get(attributeName).as(Date.class), (Date) expectedValue));
					break;
					default:
						condition = cb.and(condition, cb.equal(root.get(attributeName).as(Date.class), (Date) expectedValue));
						break;
					}
				}
			else {
				System.out.println("ni string ni boolean");
				condition = cb.and(condition, cb.equal(root.get(attributeName), expectedValue));
			}
		}

		return this.em.createQuery(query.where(condition)).getResultList();
	}
}
