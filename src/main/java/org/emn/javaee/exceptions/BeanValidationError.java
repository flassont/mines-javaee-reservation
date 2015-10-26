package org.emn.javaee.exceptions;

/**
 * This exception is used to notify an error during the entities validation process.
 */
public class BeanValidationError extends Exception {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * 
	 * @param message The error's description.
	 */
	public BeanValidationError(String message) {
		super(message);
	}
}
