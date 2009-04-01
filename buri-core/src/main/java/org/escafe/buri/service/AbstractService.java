package org.escafe.buri.service;

import java.util.List;

import org.seasar.extension.jdbc.service.S2AbstractService;

public abstract class AbstractService<E> extends S2AbstractService<E> {
	public E selectById(Object... ids) {
		return select().id(ids).getSingleResult();
	}

	public void insertBatch(List<E> entities) {
		this.jdbcManager.insertBatch(entities);
	}
}
