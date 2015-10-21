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
		System.out.println(firstName);
		String queryString = "SELECT * FROM User where ISADMIN = :isAdmin ";
		if(firstName != null && firstName != "")
		{
			System.out.println("test1");
			queryString += "and FIRSTNAME like :firstName";
		}
		if(lastName != null && lastName != "")
		{
			queryString += "and LASTNAME like :lastName";
		}
		Query query = this.em.createNativeQuery(queryString, User.class);
		if(lastName != null && lastName != "")
		{
			query.setParameter("lastName", "%" + lastName + "%");
		}
		if(firstName != null && firstName != "")
		{
			System.out.println("test2");
			query.setParameter("firstName", "%" + firstName + "%");
		}
		query.setParameter("isAdmin", isAdmin);
		return query.getResultList();
	}
}
