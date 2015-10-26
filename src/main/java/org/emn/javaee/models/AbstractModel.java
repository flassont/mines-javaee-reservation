package org.emn.javaee.models;

/**
 * Starter class for application's entities.
 */
public abstract class AbstractModel {

	/**
	 * Gets the fields that which can be filtered. They are separated by ",".
	 * 
	 * @return The fields.
	 */
	public abstract String getFilterBy();
}
