/*
 * 作成日: 2006/06/06
 *
 */
package org.seasar.buri.dao.util.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.seasar.buri.dao.BuriStateUserDao;
import org.seasar.buri.dao.util.BuriStateUtil;
import org.seasar.buri.dao.util.BuriUserUtil;
import org.seasar.buri.dto.BuriStateUserEntityDto;
import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.util.packages.BranchWalker;
import org.seasar.coffee.dataaccess.DataAccessFactory;

public class BuriStateUserUtilImpl extends BuriStateUtilImpl implements BuriStateUtil{
    private BuriUserUtil userUtil;
    private BuriStateUserDao stateUserDao;
    
    public long saveStatus(DataAccessFactory factory,BuriSystemContext sysContext,BranchWalker walker) {
        long stateID = super.saveStatus(factory,sysContext,walker);
        List users = userUtil.getTgtRoleInfoList(factory,sysContext,walker);
        List userIds = userUtil.convUserIDListFromRoleInfos(users);
        List entitys = getEntityList(new Long(stateID),userIds);
        Iterator ite = entitys.iterator();
        while(ite.hasNext()) {
        	BuriStateUserEntityDto dto = (BuriStateUserEntityDto)ite.next();
        	stateUserDao.insert(dto);
        }
        return stateID;
    }
    
    protected List getEntityList(Long stateID,List userIds) {
        List entitys = new ArrayList();
        Iterator ite = userIds.iterator();
        while(ite.hasNext()) {
            Long userID = (Long)ite.next();
            BuriStateUserEntityDto dto = new BuriStateUserEntityDto();
            dto.setBuriUserID(userID);
            dto.setStateID(stateID);
            entitys.add(dto);
        }
        return entitys;
    }

    public BuriStateUserDao getStateUserDao() {
        return stateUserDao;
    }

    public void setStateUserDao(BuriStateUserDao stateUserDao) {
        this.stateUserDao = stateUserDao;
    }

    public BuriUserUtil getUserUtil() {
        return userUtil;
    }

    public void setUserUtil(BuriUserUtil userUtil) {
        this.userUtil = userUtil;
    }
    

}
