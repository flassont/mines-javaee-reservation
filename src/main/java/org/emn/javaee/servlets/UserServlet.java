package org.emn.javaee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.emn.javaee.crud.UserCrud;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/users/*")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserCrud crud;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        this.crud = new UserCrud();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("servlet user");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		Boolean administrator = "on".equals(request.getParameter("administrator"));
		if( firstName != null || lastName != null || administrator)
		{
			request.setAttribute("users", crud.findMT(lastName, firstName, administrator));
		}
		else
		{
			request.setAttribute("users", crud.findAll());
		}
		request.setAttribute("page", "template/manager.jsp");
		request.setAttribute("entity", "users");
		request.setAttribute("title", "Utilisateurs");
		String path = request.getPathInfo();
		System.out.println(request.getServletPath());
		System.out.println(request.getRequestURL());
		System.out.println(request.getPathInfo());
		request.setAttribute("creationMode", "/new".equals(path));
		request.getRequestDispatcher("/pages/main.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
