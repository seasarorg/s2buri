/*
 * çÏê¨ì˙: 2005/08/18
 *
 */
package org.seasar.buri.dao;

import java.util.List;

import org.seasar.buri.dto.FurnitureItemDto;


public interface FurnitureItemDao {
    public Class BEAN = FurnitureItemDto.class;
    
    public List getAllFurnitureItem();

    public String getFurnitureItem_ARGS = "FurnitureID";
    public FurnitureItemDto getFurnitureItem(String furnitureID);

    public String getByIdAndDto_ARGS = "testIDs,dto";
    public String getByIdAndDto_QUERY = "FurnitureID in /*testIds*/(1) /*IF dto.Type != null*/and Type = /*dto.Type*/'hoge' /*END*/ /*IF dto.Name != null*/and Name = /*dto.Name*/'hoge' /*END*/ /*IF dto.acquisitionType != 0*/and acquisitionType = /*dto.acquisitionType*/1 /*END*/";
    public List getByIdAndDto(List testIds,FurnitureItemDto dto);
    
    public void insert(FurnitureItemDto dto);
    
    public void update(FurnitureItemDto dto);
    
    public void delete(FurnitureItemDto dto);
    
    
}
