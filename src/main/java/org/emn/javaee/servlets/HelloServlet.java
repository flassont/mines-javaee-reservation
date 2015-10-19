package org.emn.javaee.servlets;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet(urlPatterns = {"/login", "/signup", "/manager" })
public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().write(request.getContextPath());
		if (request.getServletPath().equals("/login")) {
			request.setAttribute("page", "template/connection/login.jspf");
			request.setAttribute("title", "Connection");
			request.getRequestDispatcher("/pages/main.jsp").forward(request, response);
		}
		if (request.getServletPath().equals("/signup")){
			request.setAttribute("page", "template/connection/signup.jspf");
			request.setAttribute("title", "Inscription");
			request.getRequestDispatcher("/pages/main.jsp").forward(request, response);
		}
		if(request.getServletPath().equals("/manager")){
			request.setAttribute("page", "template/manager.jsp");
			request.setAttribute("title", "Manager");
			request.setAttribute("types", Arrays.asList(new User("Type 1", 1), new User("Type 2", 2), new User("Type 3", 3)));
			request.getRequestDispatcher("/pages/main.jsp").forward(request, response);
		}
	}

}
