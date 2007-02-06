/*
 * 作成日: 2006/06/06
 *
 */
package org.seasar.buri.dao.util.impl;

import java.util.ArrayList;
import java.util.List;

import org.seasar.buri.dao.BuriStateUserDao;
import org.seasar.buri.dao.util.BuriStateUtil;
import org.seasar.buri.dao.util.BuriUserUtil;
import org.seasar.buri.dto.BuriStateUserEntityDto;
import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.engine.RoleInfo;
import org.seasar.buri.util.packages.BranchWalker;
import org.seasar.coffee.dataaccess.DataAccessFactory;

public class BuriStateUserUtilImpl extends BuriStateUtilImpl implements BuriStateUtil {

    private BuriUserUtil userUtil;
    private BuriStateUserDao stateUserDao;

    @Override
    public long saveStatus(DataAccessFactory factory, BuriSystemContext sysContext,
            BranchWalker walker) {
        long stateID = super.saveStatus(factory, sysContext, walker);
        List<RoleInfo> users = userUtil.getTargetRoleInfos(factory, sysContext, walker);
        List<Long> userIds = userUtil.convertBuriUserIDsFromRoleInfos(users);
        List<BuriStateUserEntityDto> entities = getEntityList(new Long(stateID), userIds);
        for (BuriStateUserEntityDto dto : entities) {
            stateUserDao.insert(dto);
        }
        return stateID;
    }

    protected List<BuriStateUserEntityDto> getEntityList(Long stateID, List<Long> userIds) {
        List<BuriStateUserEntityDto> entities = new ArrayList<BuriStateUserEntityDto>();
        for (Long userID : userIds) {
            BuriStateUserEntityDto dto = new BuriStateUserEntityDto();
            dto.setBuriUserID(userID);
            dto.setStateID(stateID);
            entities.add(dto);
        }
        return entities;
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
