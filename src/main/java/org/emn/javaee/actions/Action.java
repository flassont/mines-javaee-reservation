package org.emn.javaee.actions;

import javax.servlet.http.HttpServletRequest;

public abstract class Action {
    protected String pattern;
	public abstract String treat(HttpServletRequest request);
	public String getPattern()
	{
		return this.pattern;
	}
}
