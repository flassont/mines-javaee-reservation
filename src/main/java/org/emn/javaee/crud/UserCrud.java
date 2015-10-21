package org.emn.javaee.crud;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.emn.javaee.models.User;

/**
 * User crud
 */
public class UserCrud extends GenericCrud<User>{
	
	public List<User> findBy(String lastName, String firstName, boolean isAdmin)
	{
		Map<String, Object> filters = new HashMap<>(3);
		if(!lastName.isEmpty()) {
			filters.put("lastName", "%" + lastName + "%");
		}
		if(!firstName.isEmpty()) {
			filters.put("firstName", "%" + firstName + "%");
		}
		filters.put("isAdmin", isAdmin);

		return this.filter(filters);
	}
}
