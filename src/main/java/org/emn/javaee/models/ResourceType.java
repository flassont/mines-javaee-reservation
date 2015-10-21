package org.emn.javaee.models;

import javax.persistence.*;
import java.util.List;

/**
 * Resources supertype
 */
@Entity
@Table (name = "RESOURCETYPE")
@NamedQueries({
	@NamedQuery( name = "ResourceType.findAll",
			query = "SELECT R FROM ResourceType AS R" )
})
public class ResourceType {

    @Id
    @GeneratedValue
    @Column (name = "ID")
    private int id;

    @Column (name = "NAME", unique = true, nullable = false)
    private String name;

    /**
     * Resource instances depending of this ResourceType
     */
    @OneToMany (mappedBy = "type")
    private List<Resource> resources;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}
