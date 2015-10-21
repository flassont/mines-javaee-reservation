package org.emn.javaee.crud;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.emn.javaee.models.ResourceType;

/**
 * ResourceType CRUD
 */
public class ResourceTypeCrud extends GenericCrud<ResourceType>{

	public List<ResourceType> findBy(String name)
	{
		Map<String, Object> filters = new HashMap<>(1);
		if(!name.isEmpty()) {
			filters.put("name", "%" + name + "%");
		}
		return this.filter(filters);
	}
	
	public boolean nameAlreadyExist(String name, int id)
	{
		String queryString = "SELECT * FROM RESOURCETYPE WHERE NAME = :name ";
		if(id != -1)
		{
			queryString += " AND ID != :id";
		}
		Query query = this.em.createNativeQuery(queryString, ResourceType.class);
		query.setParameter("name", name);
		if(id != -1)
		{
			query.setParameter("id", id);
		}
		return query.getResultList().size() != 0;
	}

}
