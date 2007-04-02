package org.seasar.buri.dao;

import java.util.List;

import org.seasar.buri.dto.FurnitureItemDto;
import org.seasar.buri.dto.FurnitureItemFindDto;

public interface FurnitureItemDao {
    public Class BEAN = FurnitureItemDto.class;

    public List getAllFurnitureItem();

    public String getFurnitureItem_QUERY = "furnitureID = ?";
    public FurnitureItemDto getFurnitureItem(long furnitureID);

    public String getFurnitureItemByIds_ARGS = "furnitureIDs";
    public String getFurnitureItemByIds_QUERY = "furnitureID in /*furnitureIDs*/(1)";
    public List getFurnitureItemByIds(List furnitureIDs);
    
    public String find_ARGS = "dto,paths";
    public List find(FurnitureItemFindDto dto,List paths);
    
    public String findAndUser_ARGS = "dto,paths,userIDs";
    public List findAndUser(FurnitureItemFindDto dto,List paths,List userIDs);

    //public String soleMatch_ARGS = "dto";
    //public FurnitureItemDto soleMatch(FurnitureItemFindDto dto);
    
    public void insert(FurnitureItemDto dto);
    
    public void update(FurnitureItemDto dto);
    
    public void delete(FurnitureItemDto dto);
}

