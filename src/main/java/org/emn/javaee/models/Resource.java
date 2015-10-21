package org.emn.javaee.models;

import javax.persistence.*;
import java.util.List;

/**
 * Resource instance
 */
@Entity
@Table (name = "RESOURCE")
public class Resource {

    @Id
    @GeneratedValue
    @Column (name = "ID")
    private int id;

    @Column (name = "NAME", unique = true, nullable = false)
    private String name;

    @Column (name = "LOCATION")
    private String location;

    @Column (name = "DESCRIPTION")
    private String description;

    /**
     * User in charge of this Resource
     */
    @ManyToOne
    @JoinColumn (name = "RESPONSIBLE_ID")
    private User responsible;

    /**
     * This Resource's general type
     */
    @ManyToOne
    @JoinColumn (name = "TYPE_ID")
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
