package org.emn.javaee.servlets;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.emn.javaee.crud.ResourceTypeCrud;
import org.emn.javaee.models.ResourceType;

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
		if(name != null){
			filters.put(FIELD_NAME, name);
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

}
