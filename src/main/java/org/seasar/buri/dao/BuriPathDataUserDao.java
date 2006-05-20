/*
 * çÏê¨ì˙: 2006/05/18
 *
 */
package org.seasar.buri.dao;

import java.util.List;

import org.seasar.buri.dto.BuriPathDataUserEntityDto;

public interface BuriPathDataUserDao {
    public Class BEAN = BuriPathDataUserEntityDto.class;


    public String getListByPathNameAndUser_ARGS = "className,pathName,pathType,userID";
    public List getListByPathNameAndUser(String className,String pathName,Long pathType,Long userID);
}
