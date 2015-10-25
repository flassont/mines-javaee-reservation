package org.emn.javaee.servlets;

import org.emn.javaee.crud.UserCrud;
import org.emn.javaee.models.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * User web action dispatcher
 */
public class UserAction extends ActionDispatcher<User> {

    public static final String BOOL_ON_STATUS = "on";

    public static final String FIELD_FIRST_NAME = "firstName";
    public static final String FIELD_LAST_NAME = "lastName";
    public static final String FIELD_ADMINISTRATOR = "administrator";
    public static final String FIELD_LOGIN = "login";
    public static final String FIELD_PASSWORD = "password";
    public static final String FIELD_PHONE = "phone";
    public static final String FIELD_MAIL = "mail";

    public UserAction() {
        super(new UserCrud());
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
    protected Map<String, Object> getFilters(HttpServletRequest req) {
        Map<String, Object> filters = new HashMap<>(3);

        String firstName = req.getParameter(FIELD_FIRST_NAME);
        if (firstName != null && !firstName.trim().isEmpty()) {
            filters.put(FIELD_FIRST_NAME, firstName);
        }

        String lastName = req.getParameter(FIELD_LAST_NAME);
        if (lastName != null && !lastName.trim().isEmpty()) {
            filters.put(FIELD_LAST_NAME, lastName);
        }

        Boolean administrator = BOOL_ON_STATUS.equals(req.getParameter(FIELD_ADMINISTRATOR));
        // There's no null status
        filters.put(FIELD_ADMINISTRATOR, administrator);

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
}
