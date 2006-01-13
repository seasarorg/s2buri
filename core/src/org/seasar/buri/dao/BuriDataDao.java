package org.seasar.buri.dao;

import java.util.List;

import org.seasar.buri.dto.BuriDataEntityDto;


public interface BuriDataDao {
	public Class BEAN = BuriDataEntityDto.class;

	public List getAllBuriData();

	public String getBuriData_ARGS = "DataID";
	public BuriDataEntityDto getBuriData(long DataID);
    
    public String getBuriDataFromBuriPathID_ARGS = "pathID,userIDVal,userIDNum";
    public List getBuriDataFromBuriPathID(long pathID,String userIDVal,Long userIDNum);
    
    public String getCountNumDataFromBuriPathID_ARGS = "pathID,userIDVal,userIDNum";
    public BuriDataEntityDto getCountNumDataFromBuriPathID(long pathID,String userIDVal,Long userIDNum);
    
    public String getDataIDsFromBuriPathID_ARGS = "pathID,userIDVal,userIDNum";
    public List getDataIDsFromBuriPathID(long pathID,String userIDVal,Long userIDNum);
    
    public String getBuridataFromDto_ARGS = "dto";
    public BuriDataEntityDto getBuridataFromDto(BuriDataEntityDto dto);
    
    public long getNewBuriDataID();
	
    public String insert_ARGS = "dto";
	public void insert(BuriDataEntityDto dto);
	
	public void update(BuriDataEntityDto dto);
	
	public void delete(BuriDataEntityDto dto);

}
