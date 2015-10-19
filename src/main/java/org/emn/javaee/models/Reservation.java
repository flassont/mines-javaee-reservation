package org.emn.javaee.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Reservation of a Resource by a User
 */
@Entity
public class Reservation {
    @Id
    private int id;

    /**
     * Reservation beginning date
     */
    private Date begin;

    /**
     * Reservation end date
     */
    private Date end;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
