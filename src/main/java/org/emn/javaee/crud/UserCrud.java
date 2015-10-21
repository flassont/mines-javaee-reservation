package org.emn.javaee.crud;

import java.util.List;

import javax.persistence.Query;

import org.emn.javaee.models.User;

/**
 * User crud
 */
public class UserCrud extends GenericCrud<User>{
	
	public List<User> findMT(String lastName, String firstName, boolean isAdmin)
	{
		String queryString = "SELECT * FROM User where LASTNAME like :lastName and FIRSTNAME like :firstName and ISADMIN = :isAdmin";
		
		Query query = this.em.createNativeQuery(queryString, User.class);
		query.setParameter("lastName", "%" + lastName == null ? "" : lastName + "%");
		query.setParameter("firstName", "%" + firstName == null ? "" : firstName + "%");
		query.setParameter("isAdmin", isAdmin);
		return query.getResultList();
	}
}
