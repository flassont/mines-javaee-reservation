package org.emn.javaee.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.emn.javaee.crud.UserCrud;
import org.emn.javaee.models.User;

import org.emn.javaee.actions.*;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet(urlPatterns = {"/test/*"})
public class FrontServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
//	private ArrayList<Action> actions;

//	public void init(ServletConfig config) throws ServletException {
//		super.init(config);
//		this.actions = new ArrayList<Action>();
//		actions.add(new SignupAction());
//	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getPathInfo();
		if(path.startsWith("/users")){
			request.getRequestDispatcher("/users").forward(request, response);
		}
//		Action a = this.findAction(path);
//		if(a == null)
//		{
//			System.out.println("action est null");
//		}
//		else 
//		{
//			a.handle(request);
//			request.getRequestDispatcher("/pages/main.jsp").forward(request, response);
//		}
		// response.getWriter().write(request.getContextPath());
		/*if (request.getServletPath().equals("/pages/login")) {
			request.setAttribute("page", "template/connection/login.jspf");
			request.setAttribute("title", "Connection");
			request.getRequestDispatcher("/pages/main.jsp").forward(request, response);
		}*/
		/*if (request.getServletPath().equals("/pages/signup")){
			request.setAttribute("page", "template/connection/signup.jspf");
			request.setAttribute("title", "Inscription");
			request.getRequestDispatcher("/pages/main.jsp").forward(request, response);
		}*/
		/*if(request.getServletPath().equals("/pages/manager")){
			request.setAttribute("page", "template/manager.jsp");
			request.setAttribute("title", "Manager");*/
		//request.setAttribute("types", Arrays.asList(new User("Type 1", 1), new User("Type 2", 2), new User("Type 3", 3)));
		/*request.getRequestDispatcher("/pages/main.jsp").forward(request, response);
		}*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	/**
	 * Find a action by its pattern
	 * @param path
	 * @return action if found else null
	 */
	/*private Action findAction(String path)
	{
		for(Action a: this.actions)
		{
			System.out.println(a.getPattern() + " vs " + path);
			if(a.getPattern().equals(path))
			{
				System.out.println("test");
				return a;
			}
		}
		return null;
	}*/
}
