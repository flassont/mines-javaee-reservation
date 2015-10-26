package org.emn.javaee.servlets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.emn.javaee.crud.ReservationCrud;
import org.emn.javaee.crud.ResourceCrud;
import org.emn.javaee.crud.ResourceTypeCrud;
import org.emn.javaee.crud.UserCrud;
import org.emn.javaee.models.Reservation;
import org.emn.javaee.models.Resource;
import org.emn.javaee.models.ResourceType;

public class ReservationAction extends ActionDispatcher<Reservation> {

	public static final String FIELD_BEGIN_NAME = "begin";
	public static final String FIELD_END_NAME = "end";
	public static final String FIELD_RESERVED_NAME = "reserved";
	public static final String FIELD_RESERVER_NAME = "reserver";
	public static final String FIELD_RESERVED_TYPE_NAME = "resourceType";

	private static final String REQUEST_ATTR_RESOURCETYPES_NAME = "resourceTypes";
	private static final String REQUEST_ATTR_SELECTED_RESOURCETYPE_NAME = "resourceType";
	private static final String REQUEST_ATTR_RESOURCES_NAME = "resources";

	private static final String DATE_EXPECTED_FORMAT = "dd/MM/yyyy";
	private final SimpleDateFormat formatter = new SimpleDateFormat(DATE_EXPECTED_FORMAT);

	private ResourceTypeCrud typeCrud;
	private ResourceCrud resourceCrud;
	private UserCrud userCrud;
	private ReservationCrud reservationCrud;

	public ReservationAction() {
		super(new ReservationCrud());

		this.typeCrud = new ResourceTypeCrud();
		this.resourceCrud = new ResourceCrud();
		this.userCrud = new UserCrud();
		this.reservationCrud = new ReservationCrud();
	}

	@Override
	protected String getEntityPageTitle() {
		return "Réservations";
	}

	@Override
	protected String getEntityFolderName() {
		return "reservations";
	}

	@Override
	protected void createEntity(HttpServletRequest req, HttpServletResponse resp) {
		super.createEntity(req, resp);

		String typeId = req.getParameter(FIELD_RESERVED_TYPE_NAME);
		if (typeId == null) {
			createEntityStepOne(req, resp);
		} else {
			creatEntityStepTwo(req, resp);
		}
	}

	@Override
	protected Map<String, Object> getFilters(HttpServletRequest req) {
		Map<String, Object> filters = new HashMap<>(4);

		String reserved = req.getParameter(FIELD_RESERVED_NAME);
		if (reserved != null && !reserved.trim().isEmpty()) {
			filters.put(FIELD_RESERVED_NAME, reserved);
		}

		String reserver = req.getParameter(FIELD_RESERVER_NAME);
		if (reserved != null && !reserver.trim().isEmpty()) {
			filters.put(FIELD_RESERVER_NAME, reserver);
		}

		String begin = req.getParameter(FIELD_BEGIN_NAME);
		if (begin != null) {
			filters.put(FIELD_BEGIN_NAME, begin);
		}

		String end = req.getParameter(FIELD_END_NAME);
		if (end != null) {
			filters.put(FIELD_END_NAME, end);
		}

		return filters;
	}

	@Override
	protected Reservation createEntityModel() {
		return new Reservation();
	}

	@Override
	protected Reservation mapEntity(Reservation model, HttpServletRequest req) {
		String begin = req.getParameter(FIELD_BEGIN_NAME);
		String end = req.getParameter(FIELD_END_NAME);
		int resourceId = Integer.parseInt(req.getParameter(FIELD_RESERVED_NAME));
		int userId = Integer.parseInt(req.getParameter(FIELD_RESERVER_NAME));

		try {
			model.setBegin(readDate(begin));
			model.setEnd(readDate(end));
		} catch (ParseException e) {
			Date now = new Date();
			model.setBegin(now);
			model.setBegin(now);
		}
		model.setReserved(this.resourceCrud.find(resourceId));
		model.setReserver(this.userCrud.find(userId));

		return model;
	}

	@Override
	protected void validateFields(HttpServletRequest req) throws BeanValidationError {
		int reserverId = Integer.parseInt(req.getParameter(FIELD_RESERVER_NAME));
		if (userCrud.find(reserverId) == null) {
			throw new BeanValidationError("Utilisateur invalide");
		}

		int reservedId = Integer.parseInt(req.getParameter(FIELD_RESERVED_NAME));
		Resource resource = resourceCrud.find(reservedId);
		if (resource == null) {
			throw new BeanValidationError("Ressource invalide");
		}

		String begin = req.getParameter(FIELD_BEGIN_NAME);
		if (!isFieldValid(begin)) {
			throw new BeanValidationError("Date de début invalide");
		}

		String end = req.getParameter(FIELD_END_NAME);
		if (!isFieldValid(end)) {
			throw new BeanValidationError("Date de fin invalide");
		}

		try {
			Date endDate = formatter.parse(end);
			Date beginDate = formatter.parse(begin);

			if (beginDate.compareTo(endDate) > 0) {
				throw new BeanValidationError("La date de fin est antérieure à la date de début");
			}

//			if (this.reservationCrud.isResourceReservedBetween(resource, beginDate, endDate)) {
//				throw new BeanValidationError("La ressource n'est pas disponible sur cette période");
//			}
		} catch (ParseException e) {
			throw new BeanValidationError("Erreur de convertion des dates.");
		}
	}

	private void createEntityStepOne(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute(REQUEST_ATTR_RESOURCETYPES_NAME, this.typeCrud.findAll());
	}

	private void creatEntityStepTwo(HttpServletRequest req, HttpServletResponse resp) {
		int typeId = Integer.parseInt(req.getParameter(FIELD_RESERVED_TYPE_NAME));
		ResourceType type = this.typeCrud.find(typeId);

		req.setAttribute(REQUEST_ATTR_RESOURCES_NAME, this.resourceCrud.findByType(type));
		req.setAttribute(REQUEST_ATTR_SELECTED_RESOURCETYPE_NAME, type);
	}

	private Date readDate(String date) throws ParseException {
		date = date.trim();
		return formatter.parse(date);
	}
}
