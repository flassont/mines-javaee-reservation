package org.emn.javeee.services;

import java.util.List;

import org.emn.javaee.crud.GenericCrud;

public interface BusinessService<E> {

	public GenericCrud<E> getCrud();

	public List<E> findAll();

	public void create(E entity) throws BusinessException;

	public boolean delete(int key) throws BusinessException;

	public boolean delete(E entity);

	public void update(E entity) throws BusinessException;

	public E getByKey(int key);

	public void validEntity(E entity) throws BusinessException;

	public boolean exist(int key);
}
