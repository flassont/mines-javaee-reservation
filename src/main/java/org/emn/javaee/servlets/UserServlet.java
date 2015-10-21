package org.emn.javaee.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
		String path = request.getPathInfo();
		// remove user
		if(path != null && path.startsWith("/delete"))
		{
			handleDeletion(request, response);
		}
		else 
		{
			// edit / see user
			if (path != null && path.startsWith("/edit"))
			{
				try {
					int id = Integer.valueOf(request.getParameter("id"));
					User user = this.crud.find(id);
					// add the current user to fill the input values in the jsp user form
					request.setAttribute("user", user);
				}
				catch(Exception e){};

			}
			// list users
			handleFullOrFilteredList(request);
			request.setAttribute("page", "template/manager.jsp");
			request.setAttribute("entity", "users");
			request.setAttribute("title", "Utilisateurs");
			request.setAttribute("creationMode", "/new".equals(path) || (path != null && path.startsWith("/edit")));
			request.getRequestDispatcher("/pages/main.jsp").forward(request, response);
		}
	}

	/**
	 * Deletes a user
	 * @param request
	 * @throws IOException 
	 */
	private void handleDeletion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			int id = Integer.valueOf(request.getParameter("id"));
			this.crud.remove(id);
		}
		catch(Exception e){}
		finally{
			this.redirectToUsers(request, response);
		};
	}

	/**
	 * Retrieve the full list of users or the filtered list of users based on parameters past as GET
	 * @param request
	 */
	private void handleFullOrFilteredList(HttpServletRequest request) {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		Boolean administrator = "on".equals(request.getParameter("administrator"));
		if( firstName != null || lastName != null || administrator)
		{
			request.setAttribute("lastName", lastName);
			request.setAttribute("firstName", firstName);
			request.setAttribute("administrator", administrator);
			request.setAttribute("users", crud.findBy(lastName, firstName, administrator));
		}
		else
		{
			request.setAttribute("users", crud.findAll());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// create user
		handleFormUser(request, response);
		this.redirectToUsers(request, response);
	}

	/**
	 * Redirect to the list of users
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void redirectToUsers(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		response.sendRedirect(getServletContext().getContextPath() + request.getServletPath());
	}

	/**
	 * If a form has been submitted to create or update a user, we handle it here
	 * @param request
	 * @throws IOException 
	 */
	private void handleFormUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(request.getParameterValues("firstName") != null)
		{
			User user;
			// we update a user as its id has been past
			if(request.getParameter("id") != null)
			{
				try{
					int id = Integer.valueOf(request.getParameter("id"));
					user = this.crud.find(id);
				}
				catch(Exception e)
				{
					// After that return, the user will be redirected to the list of users
					return;
				}
			}
			else // new user
			{
				user = new User();
			}
			user.setFirstName(request.getParameterValues("firstName")[0]);
			user.setLastName(request.getParameterValues("lastName")[0]);
			user.setPhone(request.getParameterValues("phone")[0]);
			user.setMail(request.getParameterValues("mail")[0]);
			user.setLogin(request.getParameterValues("login")[0]);
			user.setPassword(request.getParameterValues("password")[0]);
			user.setIsAdmin(false);
			// Remember : this function updates a user or creates it if not found in the context
			this.crud.create(user);
		}
	}

}
