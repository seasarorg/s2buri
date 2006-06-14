/*
 * çÏê¨ì˙: 2006/06/06
 *
 */
package org.seasar.buri.dao.util.impl;

import java.util.List;

import org.seasar.buri.dao.BuriPathDataUserDao;
import org.seasar.buri.dao.util.BuriDataUtil;
import org.seasar.buri.engine.BuriSystemContext;

public class BuriDataUserUtilImpl extends BuriDataUtilImpl implements BuriDataUtil{
    private BuriPathDataUserDao pathDataDao;
    
    protected long countByPathKeys(String className,List longList,List strList,String pathName,BuriSystemContext sysContext) {
        long count = pathDataDao.getCountByPathKeysAndUser(className,longList,strList,pathName,sysContext.getCallPath().getPathType(),sysContext.getUserID());
        return count;
    }

    protected List getDataInfoListFromPathName(String pathName,BuriSystemContext sysContext) {
        String className = sysContext.getTgtClass().getName();
        Long pathType = sysContext.getCallPath().getPathType();
        List infoList = pathDataDao.getListByPathNameAndUser(className,pathName,pathType,sysContext.getUserID());
        return infoList;
    }
    
}
