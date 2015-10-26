package org.emn.javaee.crud;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.emn.javaee.models.User;

/**
 * User crud
 */
public class UserCrud extends GenericCrud<User> {

	/**
	 * Gets the user matching with the specified login and password.
	 *  
	 * @param login The login.
	 * @param password The password.
	 * @return The user or null if there is no match.
	 */
	public User findByLoginAndPassword(String login, String password) {
		try {
			if (login == null && password == null)
				return null;
			String queryString = "SELECT u from User u where u.login = :login and u.password = :password";
			Query query = this.em.createQuery(queryString, User.class);
			query.setParameter("login", login);
			query.setParameter("password", password);
			return (User) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
