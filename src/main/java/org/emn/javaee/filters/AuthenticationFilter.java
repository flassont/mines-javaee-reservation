package org.emn.javaee.filters;

import java.io.IOException;

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
public class AuthenticationFilter implements javax.servlet.Filter {
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

		String login = req.getParameter("login");
		String password = req.getParameter("password");
		if(login != null && password != null)
		{
			UserCrud crud = new UserCrud();
			User user = crud.findByLoginAndPassword(login, password);
			if(user != null)
			{
				System.out.println("user n'est pas null");
				session.setAttribute("user", user);	
			}
			else
			{
				System.out.println("user est null");
				req.setAttribute("error", "Erreur de connexion, l'utilisateur " + login + " / " + password + " n'existe pas.");
			}
		}

		if (session.getAttribute("user") == null) {
			request.setAttribute("page", "template/connection/login.jsp");
			request.setAttribute("title", "Connection");
			request.getRequestDispatcher("/pages/main.jsp").forward(request, response);
			return; //break filter chain, requested JSP/servlet will not be executed
		}

		//propagate to next element in the filter chain, ultimately JSP/ servlet gets executed
		chain.doFilter(request, response);        
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}