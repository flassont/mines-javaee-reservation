package org.emn.javaee.tools;

public class ValueParameter {
	Object value;
	Object parameter;
	
	public Object getValue() {
		return value;
	}

	public Object getParameter() {
		return parameter;
	}

	public ValueParameter(Object value, Object parameter)
	{
		this.value = value;
		this.parameter = parameter;
	}
	
	public ValueParameter(Object value)
	{
		this.value = value;
		this.parameter = null;
	}
}
