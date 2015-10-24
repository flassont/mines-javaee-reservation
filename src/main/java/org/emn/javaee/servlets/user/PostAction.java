package org.emn.javaee.servlets.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.emn.javaee.models.User;
import org.emn.javaee.servlets.ServletAction;
import org.emn.javeee.services.BusinessException;
import org.emn.javeee.services.user.UserBusinessService;

public class PostAction implements ServletAction {

	private UserBusinessService userBS;

	public PostAction() {
		this.userBS = UserBusinessService.getInstance();
	}

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		User user = id != null ? userBS.getByKey(Integer.parseInt(id)) : null;
		Boolean isNewEntity;

		if (user == null) {
			user = new User();
			isNewEntity = true;
		} else {
			isNewEntity = false;
		}

		user = copy(user);
		user.setFirstName(request.getParameterValues("firstName")[0]);
		user.setLastName(request.getParameterValues("lastName")[0]);
		user.setPhone(request.getParameterValues("phone")[0]);
		user.setMail(request.getParameterValues("mail")[0]);
		user.setLogin(request.getParameterValues("login")[0]);
		user.setPassword(request.getParameterValues("password")[0]);
		user.setIsAdmin(false);

		try {
			if (isNewEntity) {
				userBS.create(user);
			} else {
				userBS.update(user);
			}
		} catch (BusinessException e) {
			request.getSession().setAttribute("error", e.getMessage());
		}
		
		String redirectPath = request.getServletContext().getContextPath() + request.getServletPath();
		if(request.getSession().getAttribute("error") != null) {
			redirectPath += "/edit?id=" + request.getParameter("id");
		}
		
		response.sendRedirect(redirectPath);
	}

	private User copy(User user) {
		User copy = new User();
		
		copy.setId(user.getId());
		copy.setLogin(user.getLogin());
		copy.setPassword(user.getPassword());
		copy.setFirstName(user.getFirstName());
		copy.setLastName(user.getLastName());
		copy.setMail(user.getMail());
		copy.setPhone(user.getPhone());
		copy.setIsAdmin(user.isAdmin());
		
		return copy;
	}
}
