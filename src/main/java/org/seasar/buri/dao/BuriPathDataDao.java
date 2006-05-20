/*
 * çÏê¨ì˙: 2006/05/18
 *
 */
package org.seasar.buri.dao;

import java.util.List;

import org.seasar.buri.dto.BuriPathDataEntityDto;

public interface BuriPathDataDao {
    public Class BEAN = BuriPathDataEntityDto.class;
    
    public String getListByPathName_ARGS = "className,pathName,pathType";
    public List getListByPathName(String className,String pathName,Long pathType);

}
