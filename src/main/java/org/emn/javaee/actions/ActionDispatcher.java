package org.emn.javaee.actions;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.emn.javaee.crud.GenericCrud;
import org.emn.javaee.exceptions.BeanValidationError;
import org.emn.javaee.models.User;
import org.emn.javaee.tools.ValueParameter;

/**
 * Created by florian on 22/10/15.
 */
public abstract class ActionDispatcher<T> {

	public static final String REQUEST_PATH_CREATE = "new";
	public static final String REQUEST_PATH_EDIT = "edit";
	public static final String REQUEST_PATH_SEARCH = "search";
	public static final String REQUEST_PATH_DELETE = "delete";
	public static final String REQUEST_ATTR_MODELLIST_NAME = "models";
	public static final String REQUEST_ATTR_CREATIONMODE_NAME = "creationMode";
	public static final String REQUEST_ATTR_MODEL_NAME = "model";
	public static final String AUTO_FILTERING = "autofilter";

	protected static final String REQUEST_ATTR_TEMPLATE_VALUE = "template/manager.jsp";

	protected static final String REQUEST_ATTR_PAGE_NAME = "page";
	protected static final String REQUEST_ATTR_ENTITY_NAME = "entity";
	protected static final String REQUEST_ATTR_TITLE_NAME = "title";

	protected static final String SESSION_ATTR_ERROR_NAME = "transactionError";
	protected static final String SESSION_ATTR_AUTHENTICATED_USER = "authenticatedUser";

	protected final GenericCrud<T> crud;

	protected ActionDispatcher(GenericCrud<T> crud) {
		this.crud = crud;
	}

	/**
	 * Handle and forward GET requests Throws 404 HTTP status code on unexpected actions.
	 *
	 * @param req Original request.
	 * @param resp Original response.
	 * @throws IOException Status code cannot be set.
	 */
	public void handleGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		putDefaultRequestAttributes(req);

		// Check the expected action, default to getAll
		String[] paths = req.getPathInfo().split("/");
		if (paths.length < 3) {
			getAll(req, resp);
		} else {
			String action = paths[2];
			switch (action) {
			case REQUEST_PATH_CREATE:
				createEntity(req, resp);
				break;
			case REQUEST_PATH_EDIT:
				editEntity(req, resp);
				break;
			case REQUEST_PATH_SEARCH:
				getSearch(req, resp);
				break;
			case REQUEST_PATH_DELETE:
				deleteEntity(req, resp);
				goToList(req, resp);
				return;
			default:
				resp.sendError(404);
				return;
			}
		}
		req.getRequestDispatcher("/WEB-INF/pages/main.jsp").forward(req, resp);
	}

	/**
	 * Handle and forward POST requests Throws 404 HTTP status code on unexpected actions,
	 * ie different from creation, edition or search.
	 *
	 * @param req The request.
	 * @param resp The response.
	 * @throws IOException Status code cannot be set.
	 */
	public void handlePost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		putDefaultRequestAttributes(req);

		String[] paths = req.getPathInfo().split("/");
		if (paths.length < 3) {
			resp.sendError(404);
		} else {
			String action = paths[2];
			switch (action) {
			case REQUEST_PATH_CREATE:
				createEntitySave(req, resp);
				break;
			case REQUEST_PATH_EDIT:
				editEntitySave(req, resp);
				break;
			default:
				resp.sendError(404);
				return;
			}

			if (req.getSession().getAttribute(SESSION_ATTR_ERROR_NAME) == null) {
				goToList(req, resp);

			} else {
				String path = req.getServletContext().getContextPath() + req.getServletPath() + "/"
						+ getEntityFolderName() + "/";

				if (action.equals(REQUEST_PATH_CREATE)) {
					resp.sendRedirect(path + REQUEST_PATH_CREATE);
				} else {
					resp.sendRedirect(path + REQUEST_PATH_EDIT + "?id=" + getId(req));
				}
			}
		}
	}

	/**
	 * Get all entities of type T.
	 *
	 * @param req The request.
	 * @param resp The response.
	 */
	protected void getAll(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute(REQUEST_ATTR_MODELLIST_NAME, this.crud.findAll());
	}

	/**
	 * Get the HTTP page title.
	 *
	 * @return The page title.
	 */
	protected abstract String getEntityPageTitle();

	/**
	 * Get web assets folder name.
	 *
	 * @return The web assets folder name.
	 */
	protected abstract String getEntityFolderName();

	/**
	 * Get entities of type T matching filters defined by getFilters method.
	 *
	 * @param req The request.
	 * @param resp The response.
	 */
	protected void getSearch(HttpServletRequest req, HttpServletResponse resp) {
		Map<String, Object> filters = getFilters(req);
		req.setAttribute(REQUEST_ATTR_MODELLIST_NAME, this.crud.filter(filters));
		for (Entry<String, Object> entry : filters.entrySet())
		{
			req.setAttribute(entry.getKey(), ((ValueParameter) entry.getValue()).getValue());
		}

	}

	/**
	 * Get filters on search.
	 *
	 * @param req The request to get filters from.
	 * @return A map associating T filtering members' name with expected result.
	 */
	protected abstract Map<String, Object> getFilters(HttpServletRequest req);

	/**
	 * Prepare entity creation form.
	 *
	 * @param req The request.
	 * @param resp The response.
	 */
	protected void createEntity(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute(REQUEST_ATTR_CREATIONMODE_NAME, true);
	}

	/**
	 * Save the created entity.
	 *
	 * @param req The request.
	 * @param resp The response.
	 */
	protected void createEntitySave(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			validateFields(req);
			T model = createEntityModel();
			model = mapEntity(model, req);
			crud.create(model);

		} catch (BeanValidationError e) {
			req.getSession().setAttribute(SESSION_ATTR_ERROR_NAME, e.getMessage());
		}
	}

	/**
	 * Return an empty and not persisted entity.
	 *
	 * @return The newly created entity.
	 */
	protected abstract T createEntityModel();

	/**
	 * Prepare entity edition form ; req must have an id.
	 *
	 * @param req The request.
	 * @param resp The response.
	 */
	protected void editEntity(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute(REQUEST_ATTR_CREATIONMODE_NAME, true);
		req.setAttribute(REQUEST_ATTR_MODEL_NAME, this.crud.find(this.getId(req)));
	}

	/**
	 * Save entity modifications.
	 *
	 * @param req The request.
	 * @param resp The response.
	 */
	protected void editEntitySave(HttpServletRequest req, HttpServletResponse resp) {
		try {
			validateFields(req);
			T model = crud.find(getId(req));
			model = mapEntity(model, req);
			crud.create(model);
		} catch (BeanValidationError e) {
			req.getSession().setAttribute(SESSION_ATTR_ERROR_NAME, e.getMessage());
		}
	}

	protected User getAuthenticatedUser(HttpServletRequest req) {
		return (User) req.getSession().getAttribute(SESSION_ATTR_AUTHENTICATED_USER);
	}

	/**
	 * Map the entity from request parameters.
	 *
	 * @param model The model.
	 * @param req The request.
	 * @return The mapped entity.
	 */
	protected abstract T mapEntity(T model, HttpServletRequest req);

	/**
	 * Delete an entity ; request must contains an id.
	 *
	 * @param req The request.
	 * @param resp The response.
	 */
	protected void deleteEntity(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		if (!this.crud.remove(getId(req))) {
			req.getSession().setAttribute(SESSION_ATTR_ERROR_NAME,
					"Impossible de supprimer cette entité, vérifier ses dépendences.");
		}
	}

	/**
	 * Add default request attributes for all pages.
	 *
	 * @param req The request.
	 */
	protected void putDefaultRequestAttributes(HttpServletRequest req) {
		req.setAttribute(REQUEST_ATTR_PAGE_NAME, REQUEST_ATTR_TEMPLATE_VALUE);
		req.setAttribute(REQUEST_ATTR_ENTITY_NAME, getEntityFolderName());
		req.setAttribute(REQUEST_ATTR_TITLE_NAME, getEntityPageTitle());

		String error = (String) req.getSession().getAttribute(SESSION_ATTR_ERROR_NAME);
		if (error != null) {
			req.setAttribute(SESSION_ATTR_ERROR_NAME, error);
			req.getSession().removeAttribute(SESSION_ATTR_ERROR_NAME);
		}
	}

	/**
	 * Get the id parameter from the request.
	 *
	 * @param req The request.
	 * @return The id.
	 */
	protected int getId(HttpServletRequest req) {
		return Integer.parseInt(req.getParameter("id"));
	}

	/**
	 * Return to list screen for the entity.
	 *
	 * @param req The request.
	 * @param resp The response.
	 * @throws IOException.
	 */
	private void goToList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// Action handled, back to list
		resp.sendRedirect(req.getServletContext().getContextPath() + req.getServletPath() + "/" + getEntityFolderName());
	}

	/**
	 * This method is called before create and edit methods to validate the inputs.
	 * 
	 * @param req The request.
	 * @throws BeanValidationError If a field is not valid. Call getMessage() on the exception to get the error message.
	 */
	protected abstract void validateFields(HttpServletRequest req) throws BeanValidationError;

	/**
	 * This class method check if a fiel is valid. A field is valid only if it is not null and if it is not empty.
	 * 
	 * @param value The field to check.
	 * @return True if the fiel is valid, else false.
	 */
	protected static boolean isFieldValid(String value) {
		return (value != null) && (!value.trim().isEmpty());
	}
}
