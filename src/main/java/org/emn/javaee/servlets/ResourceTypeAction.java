package org.emn.javaee.servlets;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.emn.javaee.crud.ResourceTypeCrud;
import org.emn.javaee.models.ResourceType;
import org.emn.javaee.tools.ValueParameter;

public class ResourceTypeAction extends ActionDispatcher<ResourceType> {

	public static final String FIELD_NAME = "name";

	public ResourceTypeAction() {
		super(new ResourceTypeCrud());
	}

	@Override
	protected String getEntityPageTitle() {
		return "Types de ressources";
	}

	@Override
	protected String getEntityFolderName() {
		return "resourceTypes";
	}

	@Override
	protected Map<String, Object> getFilters(HttpServletRequest req) {
		Map<String, Object> filters = new HashMap<>(1);

		String name = req.getParameter(FIELD_NAME);
		if(name != null && !name.trim().isEmpty()){
			filters.put(FIELD_NAME, new ValueParameter(name));
		}

		return filters;
	}

	@Override
	protected ResourceType createEntityModel() {
		return new ResourceType();
	}

	@Override
	protected ResourceType mapEntity(ResourceType model, HttpServletRequest req) {
		model.setName(req.getParameter(FIELD_NAME));
		
		return model;
	}

	@Override
	protected void validateFields(HttpServletRequest req) throws BeanValidationError {
		String name = req.getParameter(FIELD_NAME);
		int id;
		try
		{
			id = getId(req);
		}
		catch(Exception e)
		{
			id = -1;
		}
		if(!isFieldValid(name)) {
			throw new BeanValidationError("Libellé invalide.");
		}
		else if(new ResourceTypeCrud().nameAlreadyExist(name, id))
		{
			throw new BeanValidationError("Un type de ressource portant le même libellé existe déjà.");
		}
	}

}
