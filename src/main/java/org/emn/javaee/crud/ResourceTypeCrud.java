package org.emn.javaee.crud;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.emn.javaee.models.ResourceType;
import org.emn.javaee.models.User;

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

}
