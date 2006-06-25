/*
 * ì¬“ú: 2006/05/18
 *
 */
package org.seasar.buri.dao;

import java.util.List;

import org.seasar.buri.dto.BuriPathDataUserEntityDto;

public interface BuriPathDataUserDao {
    public Class BEAN = BuriPathDataUserEntityDto.class;


    public String getTimeOrverByState_QUERY = "autoRunTime < CURRENT_TIMESTAMP and stateID = /*stateID*/1";
    public String getTimeOrverByState_ARGS = "stateID";
    public List getTimeOrverByState(long stateID);

    public String getCountByPathKeysAndUser_ARGS = "dataType,pathName,pathType,buriUserID";
    public long getCountByPathKeysAndUser(String className,List longList,List strList,String pathName,Long pathType,Long userID);

    public String getListByPathNameAndUser_ARGS = "dataType,pathName,pathType,buriUserID";
    public List getListByPathNameAndUser(String className,String pathName,Long pathType,Long userID);
}
