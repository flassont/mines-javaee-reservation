package org.emn.javaee.servlets.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.emn.javaee.models.User;
import org.emn.javaee.servlets.ServletAction;
import org.emn.javeee.services.user.UserBusinessService;

public class EditAction implements ServletAction {

	private UserBusinessService userBS;

	public EditAction() {
		this.userBS = UserBusinessService.getInstance();
	}

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			User user = this.userBS.getByKey(Integer.valueOf(request.getParameter("id")));
			request.setAttribute("user", user);

		} catch (NumberFormatException e) {

		}
		
		String path = request.getPathInfo();
		
		request.setAttribute("page", "template/manager.jsp");
		request.setAttribute("entity", "users");
		request.setAttribute("title", "Utilisateurs");
		request.setAttribute("creationMode", "/new".equals(path) || (path != null && path.startsWith("/edit")));
		
		String error = (String) request.getSession().getAttribute("error");
		if(error != null){
			request.setAttribute("error", error);
			request.getSession().removeAttribute("error");
		}
		
		request.getRequestDispatcher("/pages/main.jsp").forward(request, response);
	}
}
