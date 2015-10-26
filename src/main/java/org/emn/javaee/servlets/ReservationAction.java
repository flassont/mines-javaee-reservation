package org.emn.javaee.servlets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import org.emn.javaee.models.User;
import org.emn.javaee.tools.DateSearch;
import org.emn.javaee.tools.ValueParameter;

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

	public ReservationAction() {
		super(new ReservationCrud());

		this.typeCrud = new ResourceTypeCrud();
		this.resourceCrud = new ResourceCrud();
		this.userCrud = new UserCrud();
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
	protected void getAll(HttpServletRequest req, HttpServletResponse resp) {
		super.getAll(req, resp);

		User authenticatedUser = getAuthenticatedUser(req);
		if (!authenticatedUser.getIsAdmin()) {
			@SuppressWarnings("unchecked")
			List<Reservation> reservations = (List<Reservation>) req.getAttribute(REQUEST_ATTR_MODELLIST_NAME);
			int userId = authenticatedUser.getId();

			for (Iterator<Reservation> iterator = reservations.iterator(); iterator.hasNext();) {
				Reservation reservation = iterator.next();
				if (reservation.getReserver().getId() != userId) {
					iterator.remove();
				}
			}
		}
	}

	@Override
	protected Map<String, Object> getFilters(HttpServletRequest req) {
		Map<String, Object> filters = new HashMap<>(4);

		String reserved = req.getParameter(FIELD_RESERVED_NAME);
		if (reserved != null && !reserved.trim().isEmpty()) {
			filters.put(FIELD_RESERVED_NAME, new ValueParameter(reserved, AUTO_FILTERING));
		}

		User authenticated = getAuthenticatedUser(req);
		if (authenticated.getIsAdmin()) {
			String reserver = req.getParameter(FIELD_RESERVER_NAME);
			if (reserved != null && !reserver.trim().isEmpty()) {
				filters.put(FIELD_RESERVER_NAME, new ValueParameter(reserver, AUTO_FILTERING));
			}
		} else {
			filters.put(FIELD_RESERVER_NAME, new ValueParameter(authenticated));
		}

		String begin = req.getParameter(FIELD_BEGIN_NAME);
		if (begin != null && !begin.trim().isEmpty()) {
			try {
				filters.put(FIELD_BEGIN_NAME, new ValueParameter(formatter.parse(begin), DateSearch.FROM));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String end = req.getParameter(FIELD_END_NAME);
		if (end != null && !end.trim().isEmpty()) {
			try {
				filters.put(FIELD_END_NAME, new ValueParameter(formatter.parse(end), DateSearch.TO));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		Date endDate;
		Date beginDate;
		try {
			endDate = formatter.parse(end);
			beginDate = formatter.parse(begin);

			if (beginDate.compareTo(endDate) > 0) {
				throw new BeanValidationError("La date de fin est antérieure à la date de début");
			}
		} catch (ParseException e) {
			throw new BeanValidationError("Erreur de convertion des dates.");
		}
		boolean isFree = ((ReservationCrud) this.crud).isFree(beginDate, endDate, resource);
		if (!isFree) {
			throw new BeanValidationError("La ressource " + resource.getName()
					+ " est déjà réservée pour la période du " + begin + " au " + end + ".");
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