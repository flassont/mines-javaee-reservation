package org.emn.javaee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.emn.javaee.crud.ResourceTypeCrud;
import org.emn.javaee.models.ResourceType;
import org.emn.javaee.models.User;

/**
 * Servlet implementation class ResourceTypeServlet
 */
@WebServlet("/resourceTypes/*")
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
			handleDeletion(request, response);
		}
		else 
		{
			// edit / see resourceType
			if (path != null && path.startsWith("/edit"))
			{
				try {
					int id = Integer.valueOf(request.getParameter("id"));
					ResourceType resourceType = this.crud.find(id);
					// add the current resource type to fill the input values in the jsp resource type form
					request.setAttribute("resourceType", resourceType);
				}
				catch(Exception e){};

			}
			// list resourceTypes
			System.out.println("marqueur");
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
		// create resourceType
		handleFormResourceType(request, response);
		if(request.getAttribute("error") == null)
		{
			System.out.println("error est null");
			this.redirectToResourceTypes(request, response);
		}
		else
		{
			doGet(request, response);
		}
		
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
			request.setAttribute("resourceTypes", crud.findBy(name));
		}
		else
		{
			request.setAttribute("resourceTypes", crud.findAll());
		}
	}

	/**
	 * Redirect to the list of resourceTypes
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void redirectToResourceTypes(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		response.sendRedirect(getServletContext().getContextPath() + request.getServletPath());
	}

	/**
	 * If a form has been submitted to create or update a resourceType, we handle it here
	 * @param request
	 * @throws IOException 
	 */
	private void handleFormResourceType(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameterValues("name")[0];
		if(name != null)
		{
			ResourceType resourceType;
			int id;
			// we update a resourceType as its id has been past
			if(request.getParameter("id") != null)
			{
				try{
					id = Integer.valueOf(request.getParameter("id"));
					resourceType = this.crud.find(id);
				}
				catch(Exception e)
				{
					// After that return, the user will be redirected to the list of resourceType
					return;
				}
			}
			else // new resourceType
			{
				id = -1;
				resourceType = new ResourceType();
			}
			if(this.crud.nameAlreadyExist(name, id))
			{
				System.out.println("ERREUR ! le nom existe déjà !");
				request.setAttribute("error", "Erreur, ce type de ressource existe déjà.");
				return;
			}
			resourceType.setName(name);
			// Remember : this function updates a resourceType or creates it if not found in the context
			this.crud.create(resourceType);
		}

	}

	/**
	 * Deletes a resourceType
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
			this.redirectToResourceTypes(request, response);
		};
	}
}
