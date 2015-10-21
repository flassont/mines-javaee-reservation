package org.emn.javaee.actions;

import javax.servlet.http.HttpServletRequest;

public class LoginAction extends Action{

	public LoginAction()
	{
		this.pattern = "/pages/login";
	}
	
	@Override
	public void handle(HttpServletRequest request) {

	}

}
