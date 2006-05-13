package org.seasar.buri.dao;

import java.util.List;

import org.seasar.buri.dto.BuriDataEntityDto;


public interface BuriDataDao {
	public Class BEAN = BuriDataEntityDto.class;

	public List getAllBuriData();

	public String getBuriData_ARGS = "DataID";
	public BuriDataEntityDto getBuriData(long DataID);
    
    public String getBuridataFromDto_ARGS = "dto";
    public BuriDataEntityDto getBuridataFromDto(BuriDataEntityDto dto);
	
	public void insert(BuriDataEntityDto dto);
	
	public void update(BuriDataEntityDto dto);
	
	public void delete(BuriDataEntityDto dto);

}
