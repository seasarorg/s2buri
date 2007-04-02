package org.seasar.buri.dao;

import java.util.List;

import org.seasar.buri.dto.BuriTestINTDto;
import org.seasar.buri.dto.BuriTestINTFindDto;

public interface BuriTestINTDao {
    public Class BEAN = BuriTestINTDto.class;

    public List getAllBuriTestINT();

    public String getBuriTestINT_QUERY = "testID = ?";
    public BuriTestINTDto getBuriTestINT(long testID);

    public String getBuriTestINTByIds_ARGS = "testIDs";
    public String getBuriTestINTByIds_QUERY = "testID in /*testIDs*/(1)";
    public List getBuriTestINTByIds(List testIDs);
    
    public String find_ARGS = "dto,paths";
    public List find(BuriTestINTFindDto dto,List paths);
    
    public String findAndUser_ARGS = "dto,paths,userIDs";
    public List findAndUser(BuriTestINTFindDto dto,List paths,List userIDs);

    public String getBuriTestINTByIdAndDto_ARGS = "testIDs,dto";
    public String getBuriTestINTByIdAndDto_QUERY = "testID in /*testIds*/(1) and value = /*dto.value*/'hoge'";
    public List getBuriTestINTByIdAndDto(List testIds,BuriTestINTDto dto);
    
    public void insert(BuriTestINTDto dto);
    
    public void update(BuriTestINTDto dto);
    
    public void delete(BuriTestINTDto dto);
}

