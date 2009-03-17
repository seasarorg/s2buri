package org.escafe.buri.service;

import static org.escafe.buri.names.BuriTestManyNames.testId01;
import static org.escafe.buri.names.BuriTestManyNames.testId02;
import static org.seasar.extension.jdbc.operation.Operations.eq;

import org.escafe.buri.entity.BuriTestMany;

public class BuriTestManyService extends AbstractService<BuriTestMany> {
	public BuriTestMany getBuriTestMany(Long testId01, Long testId02) {
		return select().where(
		    eq(testId01(), testId01),
		    eq(testId02(), testId02)).getSingleResult();
	}
}
