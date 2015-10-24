package org.emn.javaee.servlets.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.emn.javaee.servlets.ServletAction;
import org.emn.javeee.services.user.UserBusinessService;

public class ListAction implements ServletAction {

	private UserBusinessService userBS;

	public ListAction() {
		this.userBS = UserBusinessService.getInstance();
	}

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		Boolean administrator = "on".equals(request.getParameter("administrator"));
		
		if (firstName != null || lastName != null || administrator) {
			request.setAttribute("lastName", lastName);
			request.setAttribute("firstName", firstName);
			request.setAttribute("administrator", administrator);
			request.setAttribute("users", userBS.findWithFilter(lastName, firstName, administrator));
		} else {
			request.setAttribute("users", userBS.findAll());
		}
		
		String path = request.getPathInfo();
		
		request.setAttribute("page", "template/manager.jsp");
		request.setAttribute("entity", "users");
		request.setAttribute("title", "Utilisateurs");
		request.setAttribute("creationMode", "/new".equals(path) || (path != null && path.startsWith("/edit")));
		request.getRequestDispatcher("/pages/main.jsp").forward(request, response);
	}

}
