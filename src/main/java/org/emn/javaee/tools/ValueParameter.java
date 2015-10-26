package org.emn.javaee.tools;

/**
 * This class is used as the second argument of a map representing filters Allow
 * to add a parameter for a given value (ex: the dates)
 */
public class ValueParameter {
	
	/**
	 * The value.
	 */
	private Object value;
	
	/**
	 * The parameter.
	 */
	private Object parameter;

	public Object getValue() {
		return value;
	}

	public Object getParameter() {
		return parameter;
	}

	public ValueParameter(Object value, Object parameter) {
		this.value = value;
		this.parameter = parameter;
	}

	public ValueParameter(Object value) {
		this.value = value;
		this.parameter = null;
	}
}
