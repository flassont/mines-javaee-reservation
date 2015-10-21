package org.emn.javaee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.emn.javaee.crud.ResourceTypeCrud;
import org.emn.javaee.models.User;

/**
 * Servlet implementation class ResourceTypeServlet
 */
@WebServlet("/ResourceTypeServlet")
public class ResourceTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ResourceTypeCrud crud;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResourceTypeServlet() {
        super();
        this.crud = new ResourceTypeCrud();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();
		// remove user
		if(path != null && path.startsWith("/delete"))
		{
			//handleDeletion(request, response);
		}
		else 
		{
			// edit / see resourceType
			if (path != null && path.startsWith("/edit"))
			{
				/*try {
					int id = Integer.valueOf(request.getParameter("id"));
					User user = this.crud.find(id);
					// add the current user to fill the input values in the jsp user form
					request.setAttribute("user", user);
				}
				catch(Exception e){};*/

			}
			// list resourceTypes
			handleFullOrFilteredList(request);
			request.setAttribute("page", "template/manager.jsp");
			request.setAttribute("entity", "resourceTypes");
			request.setAttribute("title", "Types de ressource");
			request.setAttribute("creationMode", "/new".equals(path) || (path != null && path.startsWith("/edit")));
			request.getRequestDispatcher("/pages/main.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/**
	 * Retrieve the full list of resource types or the filtered list based on parameters past as GET
	 * @param request
	 */
	private void handleFullOrFilteredList(HttpServletRequest request) {
		String name = request.getParameter("name");
		if( name != null )
		{
			request.setAttribute("name", name);
			/*Map<String, Object> filters = new HashMap<String, Object>();
			filters.put("lastName", lastName);
			filters.put("firstName", firstName);
			filters.put("isAdmin", administrator);
			request.setAttribute("users", crud.filter(filters));*/
			request.setAttribute("resourceTypes", crud.findBy(name));
		}
		else
		{
			request.setAttribute("resourceTypes", crud.findAll());
		}
	}

}
