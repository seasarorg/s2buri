/*
 * çÏê¨ì˙: 2005/07/02
 *
 */
package org.seasar.buri.dao;

import java.util.List;

import org.seasar.buri.dto.BuriTestINTDto;


public interface BuriTestINTDao {
    public Class BEAN = BuriTestINTDto.class;

    public List getAllBuriTestINT();

    public String getBuriTestINT_ARGS = "testID";
    public BuriTestINTDto getBuriTestINT(long testID);

    public String getBuriTestINTByIds_ARGS = "testIDs";
    public String getBuriTestINTByIds_QUERY = "testID in /*testIds*/(1)";
    public List getBuriTestINTByIds(List testIds);

    public String getBuriTestINTByIdAndDto_ARGS = "testIDs,dto";
    public String getBuriTestINTByIdAndDto_QUERY = "testID in /*testIds*/(1) and value = /*dto.value*/'hoge'";
    public List getBuriTestINTByIdAndDto(List testIds,BuriTestINTDto dto);
    
    public void insert(BuriTestINTDto dto);
    
    public void update(BuriTestINTDto dto);
    
    public void delete(BuriTestINTDto dto);
}
