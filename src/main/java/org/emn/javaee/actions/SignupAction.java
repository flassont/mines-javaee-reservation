package org.emn.javaee.actions;

import javax.servlet.http.HttpServletRequest;

import org.emn.javaee.crud.UserCrud;
import org.emn.javaee.models.User;

public class SignupAction extends Action{

	public SignupAction()
	{
		this.pattern = "/signup";
	}

	public String treat(HttpServletRequest request) {
		if(request.getParameterValues("firstname") != null)
		{
			User user = new User();
			user.setFirstName(request.getParameterValues("firstname")[0]);
			user.setLastName(request.getParameterValues("lastname")[0]);
			user.setPhone(request.getParameterValues("phone")[0]);
			user.setMail(request.getParameterValues("email")[0]);
			user.setLogin(request.getParameterValues("login")[0]);
			user.setPassword(request.getParameterValues("password")[0]);
			user.setIsAdmin(false);
			UserCrud crud = new UserCrud();
			crud.create(user);
			return "/users";
		}
		return "/signup";
	}

}
