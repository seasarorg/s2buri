package org.seasar.buri.dao;

import java.util.List;

import org.seasar.buri.dto.BuriPathEntityDto;


public interface BuriPathDao {
	public Class BEAN = BuriPathEntityDto.class;

	public List getAllBuriPath();

	public String getBuriPath_ARGS = "PathID";
	public BuriPathEntityDto getBuriPath(long PathID);

    public String getBuriPathFromPath_ARGS = "pathName,pathType";
    public List getBuriPathFromPath(String path,Long pathType);

    public String getBuriPathFromRealPath_ARGS = "realPathName";
    public BuriPathEntityDto getBuriPathFromRealPath(String realPath);
    
    public String getPathListByPathAndData_ARGS = "path,dataID,pathType";
    public List getPathListByPathAndData(String path,long dataID,Long pathType);
    
//    public String getBuriPathByDataID_ARGS = "dataID";
//    public List getBuriPathByDataID(long dataID);
    
//    public String getBuriPathFromDto_ARGS = "dto";
//    public List getBuriPathFromDto(BuriPathEntityDto dto);
	
	public void insert(BuriPathEntityDto dto);
	
	public void update(BuriPathEntityDto dto);
	
	public void delete(BuriPathEntityDto dto);

}
