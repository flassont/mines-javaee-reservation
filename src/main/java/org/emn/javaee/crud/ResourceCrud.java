package org.emn.javaee.crud;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.emn.javaee.models.Resource;
import org.emn.javaee.models.ResourceType;
import org.emn.javaee.models.User;
import org.emn.javaee.servlets.BeanValidationError;
import org.emn.javaee.tools.ValueParameter;


/**
 * Resource CRUD
 */
public class ResourceCrud extends GenericCrud<Resource> {
    
	/**
     * Find Resources containing nameFragment in their name
     * @param nameFragment String contained within the name
     * @return Resources having nameFragment in their name
     * @throws BeanValidationError 
     */
    public List<Resource> findByNameContaining(String nameFragment){
        Map<String, Object> nameFilter= new HashMap<>();
        nameFilter.put("name", new ValueParameter("%" + nameFragment + "%"));
        return this.filter(nameFilter);
    }
    
    public List<Resource> findByType(ResourceType type) {
    	Map<String, Object> typeFilter = new HashMap<>(1);
    	
    	typeFilter.put("type", new ValueParameter(type));
    	
    	return this.filter(typeFilter);
    }
    
    public boolean isResponsible(User user) {
    	HashMap<String, Object> filters = new HashMap<>();
    	
    	filters.put("responsible", new ValueParameter(user));
    	
    	return !this.filter(filters).isEmpty();
    }
    
    /*
     * Search resources by name, location and its responsible firstName and/or lastName
     */
    @SuppressWarnings("unchecked")
	public List<Resource> findByNameAndLocationAndResponsible(String name, String location, String responsible) {
    	String queryString = "SELECT r FROM Resource r WHERE 1=1 ";
		if(name != null)
		{
			queryString += " AND name like :name ";
		}
		if(location != null)
		{
			queryString += " AND location like :location ";
		}
		if(responsible != null)
		{
			queryString += " AND (r.responsible.firstName like :responsible OR r.responsible.lastName like :responsible OR concat(r.responsible.firstName,r.responsible.lastName) like :responsible)";
		}
		Query query = this.em.createQuery(queryString, Resource.class);
		if(name != null)
		{
			query.setParameter("name", "%" + name + "%");
		}
		if(location != null)
		{
			query.setParameter("location", "%" + location + "%");
		}
		if(responsible != null)
		{
			query.setParameter("responsible", "%" + responsible.replace(" ", "") + "%");
		}
		return query.getResultList();
    }
}
