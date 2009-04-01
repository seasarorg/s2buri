/*
 * 作成日: 2005/07/02
 *
 */
package org.escafe.buri.dao;

import java.util.List;

import org.escafe.buri.dto.BuriTestCHARDto;

public interface BuriTestCHARDao {
	public Class<?> BEAN = BuriTestCHARDto.class;

	public List<BuriTestCHARDto> getAllBuriTestCHAR();

	public String getBuriTestCHAR_ARGS = "testId";

	public BuriTestCHARDto getBuriTestCHAR(String testId);

	public String insert_ARGS = "dto";

	public void insert(BuriTestCHARDto entity);

	public void update(BuriTestCHARDto entity);

	public void delete(BuriTestCHARDto entity);
}
