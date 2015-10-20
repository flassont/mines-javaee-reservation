package org.emn.javaee.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Resources supertype
 */
@Entity
public class ResourceType {
    @Id @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String name;

    /**
     * Resource instances depending of this ResourceType
     */
    @OneToMany
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
