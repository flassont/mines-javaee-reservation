package org.emn.javaee.crud;

import java.lang.reflect.ParameterizedType;
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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;

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
			// No filter, reach next attribute
			if(!keys.contains(attributeName)) {
				continue;
			}

			// Exists, check whether use like (for String)
			// or equal (other types)
			Object expectedValue = ((ValueParameter) filters.get(attributeName)).getValue();
			// handle the relations between the entities
			if(attribute.isAssociation() && "autofilter".equals(((ValueParameter)filters.get(attributeName)).getParameter()))
			{
				condition = handleAssociationFilter(cb, root, condition, attribute, expectedValue);
			}
			else if(attribute.getJavaType() == String.class) {
				condition = handleStringFilter(cb, root, condition, attributeName, expectedValue);
			} else if (attribute.getJavaType() == Boolean.class) {
				condition = handleBooleanFilter(cb, root, condition, attributeName, expectedValue);
			} 
			else if (attribute.getJavaType() == Date.class)
			{
				condition = handleDateFilter(filters, cb, root, condition, attributeName, expectedValue);
			}
			else {

				condition = cb.and(condition, cb.equal(root.get(attributeName), expectedValue));
			}
		}

		return this.em.createQuery(query.where(condition)).getResultList();
	}

	/**
	 * Handle the filtering on an entity attribute
	 * @param cb
	 * @param root
	 * @param condition
	 * @param attribute
	 * @param expectedValue
	 * @return condition
	 */
	private Expression<Boolean> handleAssociationFilter(CriteriaBuilder cb, Root<Entity> root,
			Expression<Boolean> condition, Attribute<? super Entity, ?> attribute, Object expectedValue) {
		// join the mapped entity
		Join<Object, org.emn.javaee.models.AbstractModel> association = root.join(attribute.getName());
		// the filters of the joined entity
		String[] associationFilters;
		try {
			// get the filters of the joined entity separated by ","
			associationFilters = association.getJavaType().newInstance().getFilterBy().split(",");
			// array of predicate
			Predicate[] predicates = new Predicate[associationFilters.length];
			int i = 0;
			// loop through the array of filters
			for(String s:associationFilters)
			{
				// add the predicate according to the current filter
				predicates[i]=cb.like(cb.lower(association.get(s).as(String.class)), "%" + ((String) expectedValue).toLowerCase() + "%");
				i++;
			}
			// finally, make a or condition on all the filters
			condition = cb.and(condition, cb.or(predicates));
		} catch(Exception e)
		{
			// todo
			e.printStackTrace();
		}
		return condition;
	}

	/**
	 * Handle filtering on String class attributes
	 * @param cb
	 * @param root
	 * @param condition
	 * @param attributeName
	 * @param expectedValue
	 * @return condition
	 */
	private Expression<Boolean> handleStringFilter(CriteriaBuilder cb, Root<Entity> root, Expression<Boolean> condition,
			String attributeName, Object expectedValue) {
		condition = cb.and(condition, cb.like(cb.lower(root.get(attributeName).as(String.class)), "%" + ((String) expectedValue).toLowerCase() + "%"));
		return condition;
	}

	/**
	 * Handle filtering on boolean Class attributes
	 * @param cb
	 * @param root
	 * @param condition
	 * @param attributeName
	 * @param expectedValue
	 * @return condition
	 */
	private Expression<Boolean> handleBooleanFilter(CriteriaBuilder cb, Root<Entity> root,
			Expression<Boolean> condition, String attributeName, Object expectedValue) {
		Expression<Boolean> expectedExpression;
		if ((Boolean) expectedValue) {
			expectedExpression = cb.isTrue(root.get(attributeName).as(Boolean.class));
		} else {
			expectedExpression = cb.isFalse(root.get(attributeName).as(Boolean.class));
		}
		condition = cb.and(condition, expectedExpression);
		return condition;
	}

	/**
	 * Handle filtering on Date class attribute
	 * @param filters
	 * @param cb
	 * @param root
	 * @param condition
	 * @param attributeName
	 * @param expectedValue
	 * @return condition
	 */
	private Expression<Boolean> handleDateFilter(Map<String, Object> filters, CriteriaBuilder cb, Root<Entity> root,
			Expression<Boolean> condition, String attributeName, Object expectedValue) {
		
		// switch on the parameter as we know it's a DateSearch enum
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
		return condition;
	}
}
