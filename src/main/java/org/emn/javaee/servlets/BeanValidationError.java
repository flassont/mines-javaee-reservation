package org.emn.javaee.servlets;

public class BeanValidationError extends Exception {
	
	private static final long serialVersionUID = 1L;

	public BeanValidationError(String message) {
		super(message);
	}
}
