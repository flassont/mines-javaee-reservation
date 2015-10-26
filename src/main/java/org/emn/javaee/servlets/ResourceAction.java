package org.emn.javaee.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.emn.javaee.crud.ReservationCrud;
import org.emn.javaee.crud.ResourceCrud;
import org.emn.javaee.crud.ResourceTypeCrud;
import org.emn.javaee.crud.UserCrud;
import org.emn.javaee.models.Resource;
import org.emn.javaee.tools.ValueParameter;

public class ResourceAction extends ActionDispatcher<Resource> {

	public static final String FIELD_NAME = "name";
	public static final String FIELD_TYPE = "type";
	public static final String FIELD_RESPONSIBLE = "responsible";
	public static final String FIELD_DESCRIPTION = "description";
	public static final String FIELD_LOCATION = "location";

	private static final String REQUEST_ATTR_RESPONSIBLES_NAME = "responsibles";
	private static final String REQUEST_ATTR_RESOURCETYPES_NAME = "resourceTypes";

	private ResourceTypeCrud typeCrud;
	private UserCrud userCrud;
	private ReservationCrud reservationCrud;

	public ResourceAction() {
		super(new ResourceCrud());
		
		this.typeCrud = new ResourceTypeCrud();
		this.userCrud = new UserCrud();
		this.reservationCrud = new ReservationCrud();
	}

	@Override
	protected void createEntity(HttpServletRequest req, HttpServletResponse resp) {
		super.createEntity(req, resp);
		
		req.setAttribute(REQUEST_ATTR_RESOURCETYPES_NAME, typeCrud.findAll());
		req.setAttribute(REQUEST_ATTR_RESPONSIBLES_NAME, userCrud.findAll());
	}
	
	@Override
	protected void editEntity(HttpServletRequest req, HttpServletResponse resp) {
		super.editEntity(req, resp);
		
		req.setAttribute(REQUEST_ATTR_RESOURCETYPES_NAME, typeCrud.findAll());
		req.setAttribute(REQUEST_ATTR_RESPONSIBLES_NAME, userCrud.findAll());
	}

	@Override
	protected String getEntityPageTitle() {
		return "Resources";
	}

	@Override
	protected String getEntityFolderName() {
		return "resources";
	}

	@Override
	protected Map<String, Object> getFilters(HttpServletRequest req) {
		Map<String, Object> filters = new HashMap<>();

		String name = req.getParameter(FIELD_NAME);
		if (name != null) {
			filters.put(FIELD_NAME, new ValueParameter(name));
		}

		String responsible = req.getParameter(FIELD_RESPONSIBLE);
		if (responsible != null && !responsible.trim().isEmpty()) {
			filters.put(FIELD_RESPONSIBLE, new ValueParameter(responsible, AUTO_FILTERING));
		}

		String type = req.getParameter(FIELD_TYPE);
		if (type != null && !type.trim().isEmpty()) {
			filters.put(FIELD_TYPE, new ValueParameter(type, AUTO_FILTERING));
		}

		return filters;
	}

	@Override
	protected Resource createEntityModel() {
		return new Resource();
	}

	@Override
	protected Resource mapEntity(Resource model, HttpServletRequest req) {
		model.setName(req.getParameter(FIELD_NAME));
		model.setDescription(req.getParameter(FIELD_DESCRIPTION));
		model.setLocation(req.getParameter(FIELD_LOCATION));

		int typeId = Integer.parseInt(req.getParameter(FIELD_TYPE));
		
		model.setType(typeCrud.find(typeId));
		
		int userId = Integer.parseInt(req.getParameter(FIELD_RESPONSIBLE));
		model.setResponsible(userCrud.find(userId));

		return model;
	}

	@Override
	protected void validateFields(HttpServletRequest req) throws BeanValidationError {
		String name = req.getParameter(FIELD_NAME);
		if(!isFieldValid(name)) {
			throw new BeanValidationError("Libellé invalide.");
		}
		
		int typeId = Integer.parseInt(req.getParameter(FIELD_TYPE));
		if(typeCrud.find(typeId) == null) {
			throw new BeanValidationError("Type de ressource inexistant");
		}
		
		int responsibleId = Integer.parseInt(req.getParameter(FIELD_RESPONSIBLE));
		if(userCrud.find(responsibleId) == null) {
			throw new BeanValidationError("Responsable inexistant");
		}
		
		String description = req.getParameter(FIELD_DESCRIPTION);
		if(!isFieldValid(description)) {
			throw new BeanValidationError("Description invalide.");
		}
		
		String location = req.getParameter(FIELD_DESCRIPTION);
		if(!isFieldValid(location)) {
			throw new BeanValidationError("Localisation invalide.");
		}
	}
	
	@Override
	protected void deleteEntity(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Resource resource = this.crud.find(getId(req));
		
		if (this.reservationCrud.existWithResource(resource)) {
			req.getSession().setAttribute(SESSION_ATTR_ERROR_NAME,
					"Impossible de supprimer la ressource " + resource.getName()+". Des réservations y sont liées.");
			return;
		} 
		
		super.deleteEntity(req, resp);
	}
}
