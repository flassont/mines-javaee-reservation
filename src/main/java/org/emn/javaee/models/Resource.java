package org.emn.javaee.models;

import javax.persistence.*;
import java.util.List;

/**
 * Resource instance
 */
@Entity
public class Resource {
    @Id @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String name;

    private String location;

    private String description;

    /**
     * User in charge of this Resource
     */
    @ManyToOne
    private User responsible;

    /**
     * This Resource's general type
     */
    @ManyToOne
    private ResourceType type;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }
}
