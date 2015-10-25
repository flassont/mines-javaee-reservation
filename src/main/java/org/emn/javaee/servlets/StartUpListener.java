package org.emn.javaee.servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.emn.javaee.crud.UserCrud;
import org.emn.javaee.models.User;

@WebListener
public class StartUpListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		UserCrud crud = new UserCrud();
		User admin = crud.findByLoginAndPassword("ADMIN", "azerty");
		
		if(admin == null) {
			admin = new User();
			
			admin.setLogin("ADMIN");
			admin.setPassword("azerty");
			admin.setFirstName("admin");
			admin.setLastName("admin");
			admin.setMail("admin@admin.fr");
			admin.setPhone("0606060609");
			admin.setIsAdmin(true);
			
			crud.create(admin);
		}
	} 

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
