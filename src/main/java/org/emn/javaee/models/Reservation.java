package org.emn.javaee.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Reservation of a Resource by a User
 */
@Entity
@NamedQueries({
	@NamedQuery( name = "Reservation.findAll",
			query = "SELECT R FROM Reservation AS R" )
})
public class Reservation {
	@Id @GeneratedValue
	private int id;

	/**
	 * Reservation beginning date
	 */
	private Date begin;

	/**
	 * Reservation end date
	 */
	private Date end;

	/**
	 * Resource reserved
	 */
	@ManyToOne
	private Resource reserved;

	/**
	 * User reserving the Resource
	 */
	@ManyToOne
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
