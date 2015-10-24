package org.emn.javeee.services;

import java.util.List;

public abstract class AbstractBusinessService<E> implements BusinessService<E> {

	protected static boolean isFieldValid(String value) {
		boolean ret = false;

		if (value != null && !value.trim().isEmpty()) {
			ret = true;
		}

		return ret;
	}

	@Override
	public void create(E entity) throws BusinessException {
		validEntity(entity);
		getCrud().create(entity);
	}

	@Override
	public boolean exist(int key) {
		boolean ret = false;

		if (getByKey(key) != null) {
			ret = true;
		}

		return ret;
	}

	@Override
	public boolean delete(int key) {
		return getCrud().remove(key);
	}

	@Override
	public boolean delete(E entity) {
		return getCrud().remove(entity);
	}

	@Override
	public void update(E entity) throws BusinessException {
		validEntity(entity);
		getCrud().create(entity);
	}

	@Override
	public E getByKey(int key) {
		return getCrud().find(key);
	}

	@Override
	public List<E> findAll() {
		return getCrud().findAll();
	}
}
