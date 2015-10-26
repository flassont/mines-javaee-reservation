package org.emn.javaee.crud;

import java.util.Date;
import java.util.HashMap;

import javax.persistence.Query;

import org.emn.javaee.models.Reservation;
import org.emn.javaee.models.Resource;
import org.emn.javaee.models.ResourceType;
import org.emn.javaee.models.User;
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
		String queryString = "SELECT r from Reservation r where ((:begin >= r.begin and :begin <= r.end) or (:end <= r.end and :end >= r.begin)) or (:begin <= r.begin and :end >= r.end) and r.reserved = :resource";
		Query query = this.em.createQuery(queryString, Reservation.class);
		query.setParameter("begin", beginDate);
		query.setParameter("end", endDate);
		query.setParameter("resource", resource);
		return query.getResultList().isEmpty();
	}
	
	/**
	 * Return true if at least on reservation has a the given resourceType, else false
	 * @param resourceType
	 * @return boolean
	 */
	public boolean existWithResourceType(ResourceType resourceType)
	{
		String queryString = "SELECT r from Reservation r where r.reserved.type = :resourceType";
		Query query = this.em.createQuery(queryString, Reservation.class);
		query.setParameter("resourceType", resourceType);
		return !query.getResultList().isEmpty();
	}
}
