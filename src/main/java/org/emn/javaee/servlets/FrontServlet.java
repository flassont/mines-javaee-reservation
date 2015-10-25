package org.emn.javaee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.emn.javaee.models.Reservation;
import org.emn.javaee.models.Resource;
import org.emn.javaee.models.ResourceType;
import org.emn.javaee.models.User;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet(urlPatterns = {"/app/*"})
public class FrontServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private ActionDispatcher<User> userAction = new UserAction();
	private ActionDispatcher<ResourceType> typeAction = new ResourceTypeAction();
	private ActionDispatcher<Resource> resourceAction = new ResourceAction();
	private ActionDispatcher<Reservation> reservationAction = new ReservationAction();

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
			case "resourceTypes":
				typeAction.handleGet(request, response);
				break;
			case "resources":
				resourceAction.handleGet(request, response);
				break;
			case "reservations":
				reservationAction.handleGet(request, response);
				break;
			case "logout":
				request.getRequestDispatcher("/logout").forward(request, response);
				break;
			default:
				request.getRequestDispatcher("/" + paths[0]).forward(request, response);
				break;
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
			case "resourceTypes":
				typeAction.handlePost(request, response);
				break;
			case "resources":
				resourceAction.handlePost(request, response);
				break;
			case "reservations":
				reservationAction.handlePost(request, response);
				break;
			default:
				this.doGet(request, response);
				break;
		}
	}

}
