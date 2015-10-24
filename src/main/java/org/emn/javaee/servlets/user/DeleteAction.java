package org.emn.javaee.servlets.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.emn.javaee.servlets.ServletAction;
import org.emn.javeee.services.user.UserBusinessService;

public class DeleteAction implements ServletAction {

	private UserBusinessService userBS;

	public DeleteAction() {
		this.userBS = UserBusinessService.getInstance();
	}

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			boolean isOkey = this.userBS.delete(Integer.valueOf(request.getParameter("id")));

			if (!isOkey) {
				request.setAttribute("error",
						"Impossible de supprimer cet utilisateur. Vérifier les réservation ou les ressources.");
			}
		} catch (NumberFormatException e) {

		}
		
		request.getRequestDispatcher(request.getServletPath()).forward(request, response);
	}

}
