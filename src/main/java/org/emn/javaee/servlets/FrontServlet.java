package org.emn.javaee.servlets;

import org.emn.javaee.models.User;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet(urlPatterns = {"/test/*"})
public class FrontServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ActionDispatcher<User> userAction = new UserAction();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] paths = request.getPathInfo().split("/");
		if(paths.length < 2) {
			response.sendError(404);
			return;
		}

		switch(paths[1]) {
			case "users":
				userAction.handleGet(request, response);
				break;
			case "resources":
			case "typeResources":
				request.getRequestDispatcher("/" + paths[0]).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] paths = request.getPathInfo().split("/");
		if(paths.length < 2) {
			response.sendError(404);
			return;
		}

		switch(paths[1]) {
			case "users":
				userAction.handlePost(request, response);
				break;
			default:
				this.doGet(request, response);
		}
	}

}
