package org.emn.javaee.servlets;

public class User {
	private String label;
	private long id;

	public User() {
	}

	public User(String label, long id) {
		this.label = label;
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
