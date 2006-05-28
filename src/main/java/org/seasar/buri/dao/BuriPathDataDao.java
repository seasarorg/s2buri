/*
 * �쐬��: 2006/05/18
 *
 */
package org.seasar.buri.dao;

import java.util.List;

import org.seasar.buri.dto.BuriPathDataEntityDto;

public interface BuriPathDataDao {
    public Class BEAN = BuriPathDataEntityDto.class;
    
    public String getListByPathName_ARGS = "dataType,pathName,pathType";
    public List getListByPathName(String className,String pathName,Long pathType);

    public String getListByPkeyNum_ARGS = "dataType,pkeyNum,pathType";
    public List getListByPkeyNum(String className,Long pkeyNum,Long pathType);

    public String getListByPkeyVal_ARGS = "dataType,PkeyVal,pathType";
    public List getListByPkeyVal(String className,String pkeyVal,Long pathType);
    
    public String getDtoByPathKey_ARGS = "dataType,pkeyNum,pkeyVal,pathName,pathType";
    public BuriPathDataEntityDto getDtoByPathKey(String className,Long pkeyNum,String pkeyVal,String pathName,Long pathType);
}
