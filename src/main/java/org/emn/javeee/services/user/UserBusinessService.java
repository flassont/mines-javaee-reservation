package org.emn.javeee.services.user;

import java.util.List;

import org.emn.javaee.crud.UserCrud;
import org.emn.javaee.models.User;
import org.emn.javeee.services.AbstractBusinessService;
import org.emn.javeee.services.BusinessException;

public class UserBusinessService extends AbstractBusinessService<User> {

	private static final UserBusinessService INSTANCE = new UserBusinessService();

	private UserCrud crud;

	private UserBusinessService() {
		this.crud = new UserCrud();
	}

	public static final UserBusinessService getInstance() {
		return INSTANCE;
	}

	@Override
	public UserCrud getCrud() {
		return this.crud;
	}

	@Override
	public void create(User entity) throws BusinessException {
		if (!exist(entity.getId())) {
			super.create(entity);
		} else {
			throw new BusinessException("Cet utilisateur existe déjà.");
		}
	}
	
	public List<User> findWithFilter(String lastName, String firstName, boolean isAdmin) {
		return getCrud().findBy(lastName, firstName, isAdmin);
	}

	@Override
	public void validEntity(User entity) throws BusinessException {
		if (!isFieldValid(entity.getLogin())) {
			throw new BusinessException("Précisez un login.");
		}

		if (!isFieldValid(entity.getPassword())) {
			throw new BusinessException("Précisez un mot de passe.");
		}

		if (!isFieldValid(entity.getLastName())) {
			throw new BusinessException("Précisez un nom.");
		}

		if (!isFieldValid(entity.getFirstName())) {
			throw new BusinessException("Précisez un prénom.");
		}

		if (!isFieldValid(entity.getMail())) {
			throw new BusinessException("Précisez un email.");
		}

		if (!isFieldValid(entity.getPhone())) {
			throw new BusinessException("Précisez un numéro de téléphone.");
		}
	}
}
