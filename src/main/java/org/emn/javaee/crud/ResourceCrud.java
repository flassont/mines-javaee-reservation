package org.emn.javaee.crud;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.emn.javaee.models.Resource;
import org.emn.javaee.models.ResourceType;
import org.emn.javaee.models.User;
import org.emn.javaee.tools.ValueParameter;

/**
 * Resource CRUD
 */
public class ResourceCrud extends GenericCrud<Resource> {

	/**
	 * Find Resources containing nameFragment in their name.
	 * 
	 * @param nameFragment String contained within the name.
	 * @return Resources having nameFragment in their name.
	 */
	public List<Resource> findByNameContaining(String nameFragment) {
		Map<String, Object> nameFilter = new HashMap<>();
		nameFilter.put("name", new ValueParameter("%" + nameFragment + "%"));
		return this.filter(nameFilter);
	}

	/**
	 * List the resources of a type.
	 * 
	 * @param type The type of resource.
	 * @return The list.
	 */
	public List<Resource> findByType(ResourceType type) {
		Map<String, Object> typeFilter = new HashMap<>(1);

		typeFilter.put("type", new ValueParameter(type));

		return this.filter(typeFilter);
	}

	/**
	 * This method indicates if the specified user is responsible of resources.
	 * 
	 * @param user The user.
	 * @return True if the user is responsible of at least one resource.
	 */
	public boolean isResponsible(User user) {
		HashMap<String, Object> filters = new HashMap<>();

		filters.put("responsible", new ValueParameter(user));

		return !this.filter(filters).isEmpty();
	}
}
