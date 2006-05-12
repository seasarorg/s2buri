package org.seasar.buri.dao;

import java.util.List;

import org.seasar.buri.dto.BuriDataPathHistoryEntityDto;


public interface BuriDataPathHistoryDao {
	public Class BEAN = BuriDataPathHistoryEntityDto.class;

	public List getAllBuriDataPathHistory();

	public String getBuriDataPathHistory_ARGS = "historyID";
	public BuriDataPathHistoryEntityDto getBuriDataPathHistory(long historyID);
	
	public void insert(BuriDataPathHistoryEntityDto dto);
	
	public void update(BuriDataPathHistoryEntityDto dto);
	
	public void delete(BuriDataPathHistoryEntityDto dto);

}
