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
import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.engine.IdentityInfo;
import org.seasar.buri.engine.ParticipantContext;
import org.seasar.buri.engine.ParticipantProvider;
import org.seasar.buri.oouo.internal.structure.BuriActivityType;
import org.seasar.buri.util.packages.BranchWalker;
import org.seasar.buri.util.packages.BuriExePackages;
import org.seasar.buri.util.packages.BuriExecProcess;
import org.seasar.coffee.dataaccess.DataAccessFactory;

/**
 * ユーザ情報に関するユーティリティの実装クラスです。
 * 
 * @author $Author$
 * @version $Revision$
 */
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
        BuriUserEntityDto dto = convertBuriUserEntityDto(appUserId.getIdNumber(), appUserId.getIdString());
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

    public Object getUserData(DataAccessFactory factory, long buriUserId, IdentityInfo appUserId) {
        BuriExePackages packages = getBuriExePackages(factory);
        ParticipantProvider provider = packages.getParticipantProvider();
        if (appUserId.getIdNumber() == null && appUserId.getIdString() == null) {
            appUserId = convertAppUserId(buriUserId);
        }
        return provider.getUserData(appUserId);
    }

    private IdentityInfo convertAppUserId(long buriUserId) {
        BuriUserEntityDto userDto = userDao.getBuriUser(buriUserId);
        IdentityInfo appUserId = new IdentityInfo();
        appUserId.setIdNumber(userDto.getUserIDNum());
        appUserId.setIdString(userDto.getUserIDVal());
        return appUserId;
    }

    public List<IdentityInfo> getUserIds(DataAccessFactory factory, BuriSystemContext sysContext, BranchWalker walker) {
        BuriExePackages packages = getBuriExePackages(factory);
        ParticipantProvider provider = packages.getParticipantProvider();

        ParticipantContext pc = new ParticipantContext();
        pc.setInsertUserId(getInsertUserId(sysContext));
        Object userData = sysContext.getUserContext().getUserData();
        pc.setCurrentUserData(userData);
        pc.setCurrentUserId(provider.getUserId(userData));
        pc.setStartRoleName(sysContext.getStartRoleName());
        pc.setData(sysContext.getUserContext().getData());
        pc.setProcess((BuriExecProcess) factory);
        pc.setUserContext(sysContext.getUserContext());
        updateParticipantInfo(pc, walker, (BuriExecProcess) factory); // TODO:キャスト関係が複雑...

        List<IdentityInfo> users = provider.getAuthorizedUserIds(pc);
        return users;
    }

    private BuriExePackages getBuriExePackages(DataAccessFactory factory) {
        BuriExecProcess process = (BuriExecProcess) factory;
        BuriExePackages packages = process.getBuriExePackages();
        return packages;
    }

    /**
     * データが現在留まっているアクティビティに対応するロール名・種別をParticipantContextに設定します。
     */
    private void updateParticipantInfo(ParticipantContext pc, BranchWalker walker, BuriExecProcess process) {
        String actId = walker.getNowPath().getActivityId().get(0);
        BuriActivityType actType = process.getBuriWorkflowProcessType().getActivityById(actId);
        pc.setParticipantName(actType.getRoleName());
        pc.setParticipantType(actType.getRoleType());
    }

    public IdentityInfo getInsertUserId(BuriSystemContext sysContext) {
        BuriDataEntityDto dataEntityDto = dataDao.getBuriData(sysContext.getDataID().longValue());
        Long insertBuriUserId = dataEntityDto.getInsertUserID();
        if (insertBuriUserId == null) {
            return null;
        }
        return convertAppUserId(insertBuriUserId.longValue());
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
