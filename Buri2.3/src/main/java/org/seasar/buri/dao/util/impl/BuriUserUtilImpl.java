/*
 * 作成日: 2006/06/09
 *
 */
package org.seasar.buri.dao.util.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.buri.dao.BuriDataDao;
import org.seasar.buri.dao.BuriUserDao;
import org.seasar.buri.dao.util.BuriUserUtil;
import org.seasar.buri.dto.BuriDataEntityDto;
import org.seasar.buri.dto.BuriUserEntityDto;
import org.seasar.buri.engine.BuriParticipantContext;
import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.engine.IdentityInfo;
import org.seasar.buri.engine.ParticipantProvider;
import org.seasar.buri.oouo.internal.structure.BuriActivityType;
import org.seasar.buri.util.packages.BranchWalker;
import org.seasar.buri.util.packages.BuriExePackages;
import org.seasar.buri.util.packages.BuriExecProcess;
import org.seasar.coffee.dataaccess.DataAccessFactory;

public class BuriUserUtilImpl implements BuriUserUtil {

    private BuriDataDao dataDao;
    private BuriUserDao userDao;
    private Map<String, Long> buriUserIDCache = new HashMap<String, Long>();

    public void dispose() {
        buriUserIDCache.clear();
    }

    public long convertBuriUserId(IdentityInfo appUserId) {
        String userIDKey = appUserId.getIdNumber() + "/" + appUserId.getIdString();
        if (buriUserIDCache.containsKey(userIDKey)) {
            Long buriUserID = buriUserIDCache.get(userIDKey);
            return buriUserID.longValue();
        }
        BuriUserEntityDto dto = convertBuriUserEntityDto(appUserId.getIdNumber(), appUserId
            .getIdString());
        buriUserIDCache.put(userIDKey, new Long(dto.getBuriUserID()));
        return dto.getBuriUserID();
    }

    private BuriUserEntityDto convertBuriUserEntityDto(Long appUserIDNumber, String appUserIDString) {
        BuriUserEntityDto dto = userDao.getBuriUserFromIds(appUserIDNumber, appUserIDString);
        if (dto == null) {
            dto = new BuriUserEntityDto();
            dto.setUserIDNum(appUserIDNumber);
            dto.setUserIDVal(appUserIDString);
            userDao.insert(dto);
        }
        return dto;
    }

    public List<Long> convertBuriUserIds(List<IdentityInfo> appUserIds) {
        List<Long> resutlt = new ArrayList<Long>();
        for (IdentityInfo appUserId : appUserIds) {
            long buriUserID = convertBuriUserId(appUserId);
            resutlt.add(new Long(buriUserID));
        }
        return resutlt;
    }

    public Object getUserData(DataAccessFactory factory, long buriUserID, IdentityInfo appUserId) {
        BuriExePackages packages = getBuriExePackages(factory);
        ParticipantProvider provider = packages.getParticipantProvider();
        if (appUserId.getIdNumber() == null && appUserId.getIdString() == null) {
            BuriUserEntityDto userDto = userDao.getBuriUser(buriUserID);
            appUserId.setIdNumber(userDto.getUserIDNum());
            appUserId.setIdString(userDto.getUserIDVal());
        }
        return provider.getUserData(appUserId);
    }

    public List<IdentityInfo> getUserIds(DataAccessFactory factory, BuriSystemContext sysContext,
            BranchWalker walker) {
        BuriExePackages packages = getBuriExePackages(factory);
        ParticipantProvider provider = packages.getParticipantProvider();
        String appUserIdName = getRoleName(factory, walker);
        String appUserIdType = getRoleName(factory, walker);
        Object argData = sysContext.getUserContext().getData();

        BuriParticipantContext pc = new BuriParticipantContext();
        pc.setParticipantName(appUserIdName);
        pc.setParticipantType(appUserIdType);
        pc.setStartRoleName(sysContext.getStartRoleName());
        pc.setSystemContext(sysContext);
        pc.setUserContext(sysContext.getUserContext());
        pc.setWalker(walker);
        pc.setData(argData);
        pc.setProcess((BuriExecProcess) factory);
        updateBuriParticipantContextUser(pc, sysContext, provider);

        List<IdentityInfo> users = provider.getAuthorizedUserIds(pc);
        return users;
    }

    private void updateBuriParticipantContextUser(BuriParticipantContext pc,
            BuriSystemContext sysContext, ParticipantProvider provider) {
        Long insertUserID = getInsertUesrID(sysContext);
        Object userData = sysContext.getUserContext().getUserData();
        if (insertUserID != null) {
            BuriUserEntityDto userDto = userDao.getBuriUser(insertUserID.longValue());
            IdentityInfo insertUserId = new IdentityInfo();
            insertUserId.setIdNumber(userDto.getUserIDNum());
            insertUserId.setIdString(userDto.getUserIDVal());
            pc.setStartUserId(insertUserId);
        }
        IdentityInfo appUserId = provider.getUserId(userData);
        pc.setCurrentUserId(appUserId);
    }

    /**
     * {@code BuriExePackages}を取得します。
     * @param factory
     * @return
     */
    private BuriExePackages getBuriExePackages(DataAccessFactory factory) {
        BuriExecProcess process = (BuriExecProcess) factory;
        BuriExePackages packages = process.getBuriExePackages();
        return packages;
    }

    /**
     * データが現在留まっているアクティビティに対応するロール名を返します。
     * @param factory
     * @param walker
     * @return
     */
    private String getRoleName(DataAccessFactory factory, BranchWalker walker) {
        BuriExecProcess process = (BuriExecProcess) factory;
        String actId = (String) walker.getNowPath().getActivityId().get(0);
        BuriActivityType actType = process.getBuriWorkflowProcessType().getActivityById(actId);
        String appUserIdName = actType.getRoleName();
        return appUserIdName;
    }

    /**
     * 最初にデータを追加したユーザIDを返します。
     * @param sysContext
     * @return
     */
    private Long getInsertUesrID(BuriSystemContext sysContext) {
        BuriDataEntityDto dataEntityDto = dataDao.getBuriData(sysContext.getDataID().longValue());
        Long insertUserID = dataEntityDto.getInsertUserID();
        return insertUserID;
    }

    public BuriDataDao getDataDao() {
        return dataDao;
    }

    public void setDataDao(BuriDataDao dataDao) {
        this.dataDao = dataDao;
    }

    public BuriUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(BuriUserDao userDao) {
        this.userDao = userDao;
    }

    public Map<String, Long> getBuriUserIDCache() {
        return buriUserIDCache;
    }
}
