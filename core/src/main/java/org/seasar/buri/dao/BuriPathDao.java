package org.seasar.buri.dao;

import java.util.List;

import org.seasar.buri.dto.BuriPathEntityDto;


public interface BuriPathDao {
	public Class BEAN = BuriPathEntityDto.class;

	public List getAllBuriPath();

	public String getBuriPath_ARGS = "PathID";
	public BuriPathEntityDto getBuriPath(long PathID);

    public String getBuriPathFromPath_ARGS = "pathName";
    public BuriPathEntityDto getBuriPathFromPath(String path);

    public String getBuriPathFromRealPath_ARGS = "realPathName";
    public BuriPathEntityDto getBuriPathFromRealPath(String realPath);
    
    public String getBuriPathByDataID_ARGS = "dataID";
    public List getBuriPathByDataID(long dataID);
	
	public void insert(BuriPathEntityDto dto);
	
	public void update(BuriPathEntityDto dto);
	
	public void delete(BuriPathEntityDto dto);

}
