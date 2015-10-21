package org.emn.javaee.servlets;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getPathInfo();
		if(path.startsWith("/users")){
			request.getRequestDispatcher("/users").forward(request, response);
		}
		request.getServletContext().
		else if(path.startsWith("/resources")) {
			request.getRequestDispatcher("resources").forward(request, response);
		}
		else if(path.startsWith("/typeResources"))
		{
			request.getRequestDispatcher("/typeResources").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
