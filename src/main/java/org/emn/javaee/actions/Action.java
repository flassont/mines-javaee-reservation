package org.emn.javaee.actions;

import javax.servlet.http.HttpServletRequest;

public abstract class Action {
    protected String pattern;
    protected String page;
    protected String title;
    
	public  void handle(HttpServletRequest request){
		request.setAttribute("page", this.page);
		request.setAttribute("title", this.title);
	};
	
	public String getPattern()
	{
		return this.pattern;
	}

}
