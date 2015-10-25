package org.emn.javaee.models;

import javax.persistence.*;
import java.util.Date;

/**
 * Reservation of a Resource by a User
 */
@Entity
@Table (name = "RESERVATION")
@NamedQueries({
	@NamedQuery( name = "Reservation.findAll",
			query = "SELECT R FROM Reservation AS R" )
})
public class Reservation {

    @Id
    @GeneratedValue
    @Column (name = "ID")
    private int id;

    /**
     * Reservation beginning date
     */
    @Column (name = "BEGIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date begin;

    /**
     * Reservation end date
     */
    @Column (name = "END" )
    @Temporal(TemporalType.TIMESTAMP)
    private Date end;

    /**
     * Resource reserved
     */
    @ManyToOne
    @JoinColumn (name = "RESERVED_ID")
    private Resource reserved;

    /**
     * User reserving the Resource
     */
    @ManyToOne
    @JoinColumn (name = "RESERVER_ID")
    private User reserver;

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

    public Resource getReserved() {
        return reserved;
    }

    public void setReserved(Resource reserved) {
        this.reserved = reserved;
    }

    public User getReserver() {
        return reserver;
    }

    public void setReserver(User reserver) {
        this.reserver = reserver;
    }
}
