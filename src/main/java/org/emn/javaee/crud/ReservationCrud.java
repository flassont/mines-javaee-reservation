package org.emn.javaee.crud;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;

import org.emn.javaee.models.Reservation;
import org.emn.javaee.models.Resource;
import org.emn.javaee.models.User;
import org.emn.javaee.tools.DateSearch;
import org.emn.javaee.tools.ValueParameter;

/**
 * Reservation CRUD
 */
public class ReservationCrud extends GenericCrud<Reservation> {
	
	public boolean isReserver(User user) {
		HashMap<String, Object> filters = new HashMap<>();
		
		filters.put("reserver", new ValueParameter(user));
		
		return !filter(filters).isEmpty();
	}
	
	/**
	 * Return true if there is no other reservation made for these dates for the given resource, else false
	 * @param beginDate
	 * @param endDate
	 * @param resource
	 * @return boolean
	 */
	public boolean isFree(Date beginDate, Date endDate, Resource resource)
	{
		String queryString = "SELECT r from Reservation r where (:begin >= r.begin or :end <= r.end) or (:begin <= r.begin and :end >= r.end) and r.reserved = :resource";
		Query query = this.em.createQuery(queryString, Reservation.class);
		query.setParameter("begin", beginDate);
		query.setParameter("end", endDate);
		query.setParameter("resource", resource);
		return query.getResultList().isEmpty();
	}
}
