package org.seasar.buri.dao;

import java.util.List;

import org.seasar.buri.dto.BuriTestUserDto;
import org.seasar.buri.dto.BuriTestUserFindDto;

public interface BuriTestUserDao {
    public Class BEAN = BuriTestUserDto.class;

    public List getAllBuriTestUser();

    public String getBuriTestUser_QUERY = "userID = ?";
    public BuriTestUserDto getBuriTestUser(long userID);

    public String getBuriTestUserByIds_ARGS = "userIDs";
    public String getBuriTestUserByIds_QUERY = "userID in /*userIDs*/(1)";
    public List getBuriTestUserByIds(List userIDs);
    
    public String find_ARGS = "dto,paths";
    public List find(BuriTestUserFindDto dto,List paths);
    
    public String findAndUser_ARGS = "dto,paths,userIDs";
    public List findAndUser(BuriTestUserFindDto dto,List paths,List userIDs);


    public String getUserListByParentUserID_ARGS = "parentUserID,roleName";
    public List getUserListByParentUserID(Long userID,String roleName);

    public String getUserListByUserID_ARGS = "userID,roleName";
    public List getUserListByUserID(Long userID,String roleName);

    //public String soleMatch_ARGS = "dto";
    //public BuriTestUserDto soleMatch(BuriTestUserFindDto dto);
    
    public void insert(BuriTestUserDto dto);
    
    public void update(BuriTestUserDto dto);
    
    public void delete(BuriTestUserDto dto);
}

