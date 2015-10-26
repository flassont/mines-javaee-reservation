package org.emn.javaee.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.emn.javaee.crud.ReservationCrud;
import org.emn.javaee.crud.ResourceCrud;
import org.emn.javaee.crud.UserCrud;
import org.emn.javaee.models.User;
import org.emn.javaee.tools.ValueParameter;

/**
 * User web action dispatcher
 */
public class UserAction extends ActionDispatcher<User> {

    public static final String BOOL_ON_STATUS = "on";

    public static final String FIELD_FIRST_NAME = "firstName";
    public static final String FIELD_LAST_NAME = "lastName";
    public static final String FIELD_ADMINISTRATOR = "isAdmin";
    public static final String FIELD_LOGIN = "login";
    public static final String FIELD_PASSWORD = "password";
    public static final String FIELD_PHONE = "phone";
    public static final String FIELD_MAIL = "mail";

    private ReservationCrud reservationCrud;
    private ResourceCrud resourceCrud;
    
    public UserAction() {
        super(new UserCrud());
        this.reservationCrud = new ReservationCrud();
        this.resourceCrud = new ResourceCrud();
    }

    @Override
    protected String getEntityPageTitle() {
        return "Utilisateurs";
    }

    @Override
    protected String getEntityFolderName() {
        return "users";
    }

	@Override
	protected void deleteEntity(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		User user = this.crud.find(getId(req));
		
		if (this.reservationCrud.isReserver(user)) {
			req.getSession().setAttribute(SESSION_ATTR_ERROR_NAME,
					"Impossible de supprimer l'utilisateur. Il a des réservations en cours.");
			return;
		} 
		
		if(this.resourceCrud.isResponsible(user)) {
			req.getSession().setAttribute(SESSION_ATTR_ERROR_NAME,
					"Impossible de supprimer l'utilisateur. Il est responsable de ressources.");
			return;
		}
		
		super.deleteEntity(req, resp);
	}

    @Override
    protected Map<String, Object> getFilters(HttpServletRequest req) {
        Map<String, Object> filters = new HashMap<>(3);

        String firstName = req.getParameter(FIELD_FIRST_NAME);
        if (firstName != null && !firstName.trim().isEmpty()) {
            filters.put(FIELD_FIRST_NAME, new ValueParameter(firstName));
        }

        String lastName = req.getParameter(FIELD_LAST_NAME);
        if (lastName != null && !lastName.trim().isEmpty()) {
            filters.put(FIELD_LAST_NAME, new ValueParameter(lastName));
        }

        Boolean administrator = BOOL_ON_STATUS.equals(req.getParameter(FIELD_ADMINISTRATOR));
        // There's no null status
        filters.put(FIELD_ADMINISTRATOR, new ValueParameter(administrator));

        return filters;
    }

    @Override
    protected User createEntityModel() {
        return new User();
    }

    @Override
    protected User mapEntity(User model, HttpServletRequest req) {
        model.setFirstName(req.getParameter(FIELD_FIRST_NAME));
        model.setLastName(req.getParameter(FIELD_LAST_NAME));
        model.setLogin(req.getParameter(FIELD_LOGIN));
        model.setPassword(req.getParameter(FIELD_PASSWORD));
        model.setPhone(req.getParameter(FIELD_PHONE));
        model.setMail(req.getParameter(FIELD_MAIL));
        model.setIsAdmin(BOOL_ON_STATUS.equals(req.getParameter(FIELD_ADMINISTRATOR)));

        return model;
    }

	@Override
	protected void validateFields(HttpServletRequest req) throws BeanValidationError {
		String login = req.getParameter(FIELD_LOGIN);
		if(!isFieldValid(login)) {
			throw new BeanValidationError("Login invalide.");
		}
		
		String password = req.getParameter(FIELD_PASSWORD);
		if(!isFieldValid(password)) {
			throw new BeanValidationError("Mot de passe invalide.");
		}

		String lastName = req.getParameter(FIELD_LAST_NAME);
		if(!isFieldValid(lastName)) {
			throw new BeanValidationError("Nom invalide.");
		}

		String firstName = req.getParameter(FIELD_FIRST_NAME);
		if(!isFieldValid(firstName)) {
			throw new BeanValidationError("Prénom invalide.");
		}

		String mail = req.getParameter(FIELD_MAIL);
		if(!isFieldValid(mail)) {
			throw new BeanValidationError("email invalide.");
		}

		String phone = req.getParameter(FIELD_PHONE);
		if(!isFieldValid(phone)) {
			throw new BeanValidationError("Téléphone invalide.");
		}
	}
}
