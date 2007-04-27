package org.seasar.buri.dao;

import java.util.List;

import org.seasar.buri.dto.BuriTestUserDto;
import org.seasar.buri.dto.BuriTestUserFindDto;

public interface BuriTestUserDao {
    public Class BEAN = BuriTestUserDto.class;

    public List<BuriTestUserDto> getAllBuriTestUser();

    public String getBuriTestUser_QUERY = "userID = ?";
    public BuriTestUserDto getBuriTestUser(long userID);

    public String getBuriTestUserByIds_ARGS = "userIDs";
    public String getBuriTestUserByIds_QUERY = "userID in /*userIDs*/(1)";
    public List<BuriTestUserDto> getBuriTestUserByIds(List userIDs);
    
    public String find_ARGS = "dto,paths";
    public List<BuriTestUserDto> find(BuriTestUserFindDto dto,List paths);
    
    public String findAndUser_ARGS = "dto,paths,userIDs";
    public List<BuriTestUserDto> findAndUser(BuriTestUserFindDto dto,List paths,List userIDs);

    public String getUserListByParentUserID_ARGS = "parentUserID,roleName";
    public List<BuriTestUserDto> getUserListByParentUserID(Long userID,String roleName);

    public String getUserListByUserID_ARGS = "userID,roleName";
    public List<BuriTestUserDto> getUserListByUserID(Long userID,String roleName);

    public void insert(BuriTestUserDto dto);
    
    public void update(BuriTestUserDto dto);
    
    public void delete(BuriTestUserDto dto);
}

