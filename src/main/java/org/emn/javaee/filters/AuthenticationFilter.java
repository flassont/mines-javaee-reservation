package org.emn.javaee.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.emn.javaee.crud.UserCrud;
import org.emn.javaee.models.User;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
	protected ServletContext servletContext;

	public void init(FilterConfig filterConfig) {
		servletContext = filterConfig.getServletContext();
	}
	
	public void doFilter(
			ServletRequest request, ServletResponse response, FilterChain chain)
					throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpSession session = ((HttpServletRequest) request).getSession();

		String login = req.getParameter("loginAuth");
		String password = req.getParameter("passwordAuth");
		if(login != null && password != null)
		{
			UserCrud crud = new UserCrud();
			User user = crud.findByLoginAndPassword(login, password);
			if(user != null)
			{
				session.setAttribute("authenticatedUser", user);
				if(user.getIsAdmin()) 
				{
					resp.sendRedirect(req.getRequestURI());
				}
				else
				{
					resp.sendRedirect(request.getServletContext().getContextPath() + "/app/reservations");
				}
				return;
			}
			else
			{
				req.setAttribute("error", "Erreur de connexion, l'utilisateur " + login + " / " + password + " n'existe pas.");
			}
		}

		if (session.getAttribute("authenticatedUser") == null) {
			request.setAttribute("page", "template/connection/login.jsp");
			request.setAttribute("title", "Connexion");
			request.getRequestDispatcher("/WEB-INF/pages/main.jsp").forward(request, response);
			return; //break filter chain, requested JSP/servlet will not be executed
		}

		//propagate to next element in the filter chain, ultimately JSP/ servlet gets executed
		chain.doFilter(request, response);        
	}

	@Override
	public void destroy() {
	}
}