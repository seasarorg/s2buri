/*
 * 作成日: 2005/07/02
 *
 */
package org.escafe.buri.dao;

import java.util.List;

import org.escafe.buri.dto.BuriTestManyDto;

public interface BuriTestManyDao {
	public Class BEAN = BuriTestManyDto.class;

	public List<BuriTestManyDto> getAllBuriTestMany();

	public String getBuriTestMany_ARGS = "testId01,testId02";

	public BuriTestManyDto getBuriTestMany(Long testId01, Long testId02);

	public String insert_ARGS = "dto";

	public void insert(BuriTestManyDto entity);

	public void update(BuriTestManyDto entity);

	public void delete(BuriTestManyDto entity);
}
