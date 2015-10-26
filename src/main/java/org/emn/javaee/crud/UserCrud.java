package org.emn.javaee.crud;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.emn.javaee.models.User;
import org.emn.javaee.tools.ValueParameter;

/**
 * User crud
 */
public class UserCrud extends GenericCrud<User>{

	public List<User> findBy(String lastName, String firstName, boolean isAdmin)
	{
		Map<String, Object> filters = new HashMap<>(3);
		if(!lastName.isEmpty()) {
			filters.put("lastName", new ValueParameter("%" + lastName + "%"));
		}
		if(!firstName.isEmpty()) {
			filters.put("firstName", new ValueParameter("%" + firstName + "%"));
		}
		filters.put("isAdmin", isAdmin);

		return this.filter(filters);
	}
	
	public User findByLoginAndPassword(String login, String password)
	{
		try
		{
			if(login == null && password == null) return null;
			String queryString = "SELECT u from User u where u.login = :login and u.password = :password";
			Query query = this.em.createQuery(queryString, User.class);
			query.setParameter("login", login);
			query.setParameter("password", password);
			return (User) query.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}
}
