package org.emn.javaee.servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.emn.javaee.crud.ReservationCrud;
import org.emn.javaee.crud.ResourceCrud;
import org.emn.javaee.crud.ResourceTypeCrud;
import org.emn.javaee.crud.UserCrud;
import org.emn.javaee.models.Reservation;
import org.emn.javaee.models.Resource;

/**
 * Servlet implementation class ReservationServlet
 */
@WebServlet("/reservations/*")
public class ReservationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ReservationCrud reservationCrud;
	private ResourceTypeCrud typeCrud;
	private ResourceCrud resourceCrud;
	private UserCrud userCrud;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationServlet() {
        super();
        this.reservationCrud = new ReservationCrud();
        this.typeCrud = new ResourceTypeCrud();
        this.resourceCrud = new ResourceCrud();
        this.userCrud = new UserCrud();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();
		
		if(path != null) {
	        // remove reservation
			if(path.startsWith("/delete")) {
	            handleDeletion(request, response);
	            return;
			}
			else if(path.startsWith("/edit")){
				int id = Integer.valueOf(request.getParameter("id"));
				Reservation reservation = this.reservationCrud.find(id);
				// add the current user to fill the input values in the jsp reservation form
				request.setAttribute("reservation", reservation);
				request.setAttribute("creationMode", true);
				request.setAttribute("resources", resourceCrud.findAll());
			}
			else if("/new".equals(path)){
				String type = request.getParameter("resourceType");
				
				if(type != null) {
					request.setAttribute("authenticatedUser", request.getSession().getAttribute("authenticatedUser"));
					request.setAttribute("resources", resourceCrud.findAll());
					request.setAttribute("resourceType", typeCrud.find(Integer.parseInt(type)));
				} else {
					request.setAttribute("resourceTypes", typeCrud.findAll());
				}
				
				request.setAttribute("creationMode", true);
			}
		} else {
			handleFullOrFilteredList(request);
		}
		
        request.setAttribute("page", "template/manager.jsp");
        request.setAttribute("entity", "reservations");
        request.setAttribute("title", "reservations");
        request.getRequestDispatcher("/pages/main.jsp").forward(request, response);
	}

    /**
     * Retrieves the full list of reservations  or the filtered list based on parameters past as GET
     * @param request
     */
    private void handleFullOrFilteredList(HttpServletRequest request) {
    	request.setAttribute("reservations", reservationCrud.findAll());
    }

	/**
	 * Persist new entity on POST
	 * @param request
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleForm(request);
		this.redirectToResources(request, response);
	}

    /**
     * Persist new entity on POST
     * @param request
     */
    private void handleForm(HttpServletRequest request) {
        Reservation reservation;

        // Create or edit
        String idParameter = request.getParameter("id");
        if (idParameter != null) {
            try {
                reservation = this.reservationCrud.find(Integer.valueOf(idParameter));
            } catch (Exception e) {
                return;
            }
        } else {	
            reservation = new Reservation();
        }
        	
        int resourceId = Integer.parseInt(request.getParameter("reserved"));
        int userId = Integer.parseInt(request.getParameter("reserver"));
        reservation.setReserved(resourceCrud.find(resourceId));
        reservation.setReserver(userCrud.find(userId));
        reservation.setBegin(Date.valueOf(request.getParameter("begin")));
        reservation.setEnd(Date.valueOf(request.getParameter("end")));
        

        this.reservationCrud.create(reservation);
    }
    
    /**
	 * Deletes a reservation
	 * @param request
	 * @throws IOException 
	 */
	private void handleDeletion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			int id = Integer.valueOf(request.getParameter("id"));
			this.reservationCrud.remove(id);
		}
		catch(Exception e){}
		finally{
			this.redirectToResources(request, response);
		}
	}
	
	/**
	 * Redirect to the list of reservations
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void redirectToResources(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		response.sendRedirect(getServletContext().getContextPath() + request.getServletPath());
	}

}
