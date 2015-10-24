package org.emn.javaee.servlets.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/users/*")
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private DeleteAction deleteAction;
	private EditAction editAction;
	private ListAction listAction;
	private PostAction postAction;

	public UserServlet() {
		this.deleteAction = new DeleteAction();
		this.editAction = new EditAction();
		this.listAction = new ListAction();
		this.postAction = new PostAction();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getPathInfo();

		// remove user
		if (path != null && path.startsWith("/delete")) {
			deleteAction.process(request, response);

		}
		// edit user
		else if (path != null && path.startsWith("/edit")) {
			editAction.process(request, response);
		}
		// List
		else {
			listAction.process(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			postAction.process(request, response);
	}

}
