/*
 * 作成日: 2006/06/06
 *
 */
package org.escafe.buri.dao.util.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.escafe.buri.common.util.ClassDefUtil;
import org.escafe.buri.dao.BuriPathDataUserDao;
import org.escafe.buri.dao.util.BuriDataUtil;
import org.escafe.buri.dto.BuriPathDataUserEntityDto;
import org.escafe.buri.engine.BuriSystemContext;
import org.seasar.coffee.dataaccess.DataAccessUtilLongKey;

public class BuriDataUserUtilImpl extends BuriDataUtilImpl implements BuriDataUtil {
    private BuriPathDataUserDao pathDataUserDao;
    private ClassDefUtil classDefUtil;

    @Override
    protected long countByPathKeys(String className, List longList, List strList, String pathName, BuriSystemContext sysContext) {
        long count = pathDataUserDao.getCountByPathKeysAndUser(className, longList, strList, pathName, sysContext.getCallPath().getPathType(),
            sysContext.getBuriUserID());
        return count;
    }

    @Override
    protected List getDataInfoListFromPathName(String pathName, BuriSystemContext sysContext) {
        String className = null;
        if (sysContext.getTargetDtoClass() != null) {
            className = classDefUtil.getClassName(sysContext.getTargetDtoClass());
        }
        Long pathType = sysContext.getCallPath().getPathType();
        List infoList = pathDataUserDao.getListByPathNameAndUser(className, pathName, pathType, sysContext.getBuriUserID());
        return infoList;
    }

    @Override
    protected List getDataDtoList(String pathName, DataAccessUtilLongKey dataUtil, BuriSystemContext sysContext) {
        List infoList = getDataInfoListFromPathName(pathName, sysContext);
        Iterator ite = infoList.iterator();
        List<Long> result = new ArrayList<Long>();
        while (ite.hasNext()) {
            BuriPathDataUserEntityDto dto = (BuriPathDataUserEntityDto) ite.next();
            result.add(dto.getPkeyNum());
        }
        return result;
    }

    public BuriPathDataUserDao getPathDataUserDao() {
        return pathDataUserDao;
    }

    public void setPathDataUserDao(BuriPathDataUserDao pathDataUserDao) {
        this.pathDataUserDao = pathDataUserDao;
    }

    public ClassDefUtil getClassDefUtil() {
        return classDefUtil;
    }

    public void setClassDefUtil(ClassDefUtil classDefUtil) {
        this.classDefUtil = classDefUtil;
    }

}
