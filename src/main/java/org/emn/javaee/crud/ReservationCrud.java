package org.emn.javaee.crud;

import java.util.HashMap;

import org.emn.javaee.models.Reservation;
import org.emn.javaee.models.User;
import org.emn.javaee.tools.ValueParameter;

/**
 * Reservation CRUD
 */
public class ReservationCrud extends GenericCrud<Reservation> {
	
	public boolean isResponsible(User user) {
		HashMap<String, Object> filters = new HashMap<>();
		
		filters.put("reserver", new ValueParameter(user));
		
		return !filter(filters).isEmpty();
	}
}
