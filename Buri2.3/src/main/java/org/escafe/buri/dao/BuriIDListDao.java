/*
 * 作成日: 2006/05/19
 *
 */
package org.escafe.buri.dao;

import java.util.List;

public interface BuriIDListDao {
    public Class BEAN = Long.TYPE;
    
    public String getIDListByPathName_ARGS = "className,pathName,pathType";
    public List getIDListByPathName(String className,String pathName,Long pathType);
    
    public String getIDListByPathNameAndUser_ARGS = "className,pathName,pathType,userID";
    public List getIDListByPathNameAndUser(String className,String pathName,Long pathType,Long userID);
}
