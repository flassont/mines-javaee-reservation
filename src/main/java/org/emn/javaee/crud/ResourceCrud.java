package org.emn.javaee.crud;

import org.emn.javaee.models.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Resource CRUD
 */
public class ResourceCrud extends GenericCrud<Resource> {
    /**
     * Find Resources containing nameFragment in their name
     * @param nameFragment String contained within the name
     * @return Resources having nameFragment in their name
     */
    public List<Resource> findByNameContaining(String nameFragment) {
        Map<String, Object> nameFilter= new HashMap<>();
        nameFilter.put("name", "%" + nameFragment + "%");
        return this.filter(nameFilter);
    }
    
    public List<Resource> findByNameAndLocationAndResponsible(String name, String location, String responsible) {
        // todo
    	return null;
    }
}
