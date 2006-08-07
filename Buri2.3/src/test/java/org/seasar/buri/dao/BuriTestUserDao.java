/*
 * çÏê¨ì˙: 2006/06/14
 *
 */
package org.seasar.buri.dao;

import java.util.List;

import org.seasar.buri.dto.BuriTestUserDto;

public interface BuriTestUserDao {
    public Class BEAN = BuriTestUserDto.class;

    public List getAllBuriTestUser();

    public String getBuriTestUser_ARGS = "userID";
    public BuriTestUserDto getBuriTestUser(long userID);

    public String getUserListByParentUserID_ARGS = "parentUserID,roleName";
    public List getUserListByParentUserID(Long userID,String roleName);

    public String getUserListByUserID_ARGS = "userID,roleName";
    public List getUserListByUserID(Long userID,String roleName);
    
    public void insert(BuriTestUserDto dto);
    
    public void update(BuriTestUserDto dto);
    
    public void delete(BuriTestUserDto dto);

}
