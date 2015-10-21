package org.emn.javaee.servlets;

/**
 * Created by florian on 21/10/15.
 */

import org.emn.javaee.crud.GenericCrud;
import org.emn.javaee.crud.ResourceCrud;
import org.emn.javaee.crud.ResourceTypeCrud;
import org.emn.javaee.crud.UserCrud;
import org.emn.javaee.models.Resource;
import org.emn.javaee.models.ResourceType;
import org.emn.javaee.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ResourceTypeServlet
 */
@WebServlet("/resources/*")
public class ResourceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ResourceCrud crud;
    private GenericCrud<User> userCrud;
    private GenericCrud<ResourceType> typeCrud;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResourceServlet() {
        super();
        this.crud = new ResourceCrud();
        this.userCrud = new UserCrud();
        this.typeCrud = new ResourceTypeCrud();
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
            request.setAttribute("entity", "resources");
            request.setAttribute("title", "Ressources");
            if("/new".equals(path) || (path != null && path.startsWith("/edit"))) {
                request.setAttribute("creationMode", true);
                request.setAttribute("responsibles", userCrud.findAll());
                request.setAttribute("types", typeCrud.findAll());
            }
            request.getRequestDispatcher("/pages/main.jsp").forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleForm(request);
        doGet(request, response);
    }

    /**
     * Persist new entity on POST
     * @param request
     */
    private void handleForm(HttpServletRequest request) {
        Resource resource;

        // Create or edit
        String idParameter = request.getParameter("id");
        if (idParameter != null) {
            try {
                resource = this.crud.find(Integer.valueOf(idParameter));
            } catch (Exception e) {
                return;
            }
        } else {
            resource = new Resource();
        }

        resource.setName(request.getParameter("name"));
        resource.setDescription(request.getParameter("description"));
        resource.setLocation(request.getParameter("location"));
        resource.setType(typeCrud.find(Integer.valueOf(request.getParameter("type"))));
        resource.setResponsible(userCrud.find(Integer.valueOf(request.getParameter("responsible"))));

        this.crud.create(resource);
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
            request.setAttribute("resources", crud.findByNameContaining(name));
        }
        else
        {
            request.setAttribute("resources", crud.findAll());
        }
    }

}
