package org.escafe.buri.s2dao.dao;

import java.util.List;

import org.escafe.buri.dto.FurnitureItemDto;
import org.escafe.buri.dto.FurnitureItemFindDto;

public interface FurnitureItemDao {
	public Class<?> BEAN = FurnitureItemDto.class;

	public List<FurnitureItemDto> getAllFurnitureItem();

	public String getFurnitureItem_QUERY = "FURNITURE_ID = ?";

	public FurnitureItemDto getFurnitureItem(Long furnitureId);

	public String getFurnitureItemByIds_ARGS = "furnitureIds";

	public String getFurnitureItemByIds_QUERY =
	    "FURNITURE_ID IN /*furnitureIds*/(1)";

	public List<FurnitureItemDto> getFurnitureItemByIds(List<Long> furnitureIds);

	public String find_ARGS = "dto,paths";

	public List<FurnitureItemDto> find(FurnitureItemFindDto dto,
	        List<String> paths);

	public String findAndUser_ARGS = "dto,paths,userIds";

	public List<FurnitureItemDto> findAndUser(FurnitureItemFindDto dto,
	        List<String> paths, List<Long> userIds);

	// public String soleMatch_ARGS = "dto";
	// public FurnitureItemDto soleMatch(FurnitureItemFindDto dto);
	public void insert(FurnitureItemDto entity);

	public void update(FurnitureItemDto entity);

	public void delete(FurnitureItemDto entity);
}
