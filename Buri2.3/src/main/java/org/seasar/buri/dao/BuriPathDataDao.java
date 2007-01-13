/*
 * 作成日: 2006/05/18
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
    
    public String getCountByPathKeys_ARGS = "dataType,pkeyNums,pkeyVals,pathName,pathType";
    public long getCountByPathKeys(String className,List pkeyNums,List pkeyVals,String pathName,Long pathType);
    
    public String getTimeOrverState_QUERY = "autoRunTime < CURRENT_TIMESTAMP";
    public List getTimeOrverState();
    
}
