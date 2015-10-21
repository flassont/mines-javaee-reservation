package org.emn.javaee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.emn.javaee.crud.UserCrud;
import org.emn.javaee.models.User;

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
		request.setAttribute("creationMode", "/new".equals(path));
		request.getRequestDispatcher("/pages/main.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Post");
		if(request.getParameterValues("firstName") != null)
		{
			System.out.println("form envoyé");
			User user = new User();
			user.setFirstName(request.getParameterValues("firstName")[0]);
			user.setLastName(request.getParameterValues("lastName")[0]);
			user.setPhone(request.getParameterValues("phone")[0]);
			user.setMail(request.getParameterValues("mail")[0]);
			user.setLogin(request.getParameterValues("login")[0]);
			user.setPassword(request.getParameterValues("password")[0]);
			user.setIsAdmin(false);
			this.crud.create(user);
		}
		this.doGet(request, response);
	}

}
