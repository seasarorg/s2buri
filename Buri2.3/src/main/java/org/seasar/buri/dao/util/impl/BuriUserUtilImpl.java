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
import org.seasar.buri.engine.ParticipantProvider;
import org.seasar.buri.engine.RoleInfo;
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

    public long convertBuriUserID(Long appUserIDNumber, String appUserIDString) {
        String userIDKey = "" + appUserIDNumber + "/" + appUserIDString;
        if (buriUserIDCache.containsKey(userIDKey)) {
            Long buriUserID = buriUserIDCache.get(userIDKey);
            return buriUserID.longValue();
        }
        BuriUserEntityDto dto = convertBuriUserEntityDto(appUserIDNumber, appUserIDString);
        buriUserIDCache.put(userIDKey, new Long(dto.getBuriUserID()));
        return dto.getBuriUserID();
    }

    protected BuriUserEntityDto convertBuriUserEntityDto(Long appUserIDNumber,
            String appUserIDString) {
        BuriUserEntityDto dto = userDao.getBuriUserFromIds(appUserIDNumber, appUserIDString);
        if (dto == null) {
            dto = new BuriUserEntityDto();
            dto.setUserIDNum(appUserIDNumber);
            dto.setUserIDVal(appUserIDString);
            userDao.insert(dto);
        }
        return dto;
    }

    public List<Long> convertBuriUserIDsFromRoleInfos(List<RoleInfo> roleInfos) {
        List<Long> resutlt = new ArrayList<Long>();
        for (RoleInfo role : roleInfos) {
            long buriUserID = convertBuriUserID(role.getIdNum(), role.getIdVar());
            resutlt.add(new Long(buriUserID));
        }
        return resutlt;
    }

    public Object getUserData(DataAccessFactory factory, long buriUserID, Long appUserIDNumber,
            String appUserIDString) {
        if (appUserIDNumber == null && appUserIDString == null) {
            BuriUserEntityDto userDto = userDao.getBuriUser(buriUserID);
            appUserIDNumber = userDto.getUserIDNum();
            appUserIDString = userDto.getUserIDVal();
        }
        BuriExePackages packages = getBuriExePackages(factory);
        ParticipantProvider provider = packages.getParticipantProvider();
        Object result = provider.getUserData(appUserIDNumber, appUserIDString);
        return result;
    }

    public List<RoleInfo> getTargetRoleInfos(DataAccessFactory factory,
            BuriSystemContext sysContext, BranchWalker walker) {
        BuriExePackages packages = getBuriExePackages(factory);
        ParticipantProvider provider = packages.getParticipantProvider();
        String roleName = getRoleName(factory, walker);
        String roleType = getRoleName(factory, walker);
        Object argData = sysContext.getUserContext().getData();

        BuriParticipantContext pc = new BuriParticipantContext();
        pc.setParticipantName(roleName);
        pc.setParticipantType(roleType);
        pc.setStartRoleName(sysContext.getStartRoleName());
        pc.setSystemContext(sysContext);
        pc.setUserContext(sysContext.getUserContext());
        pc.setWalker(walker);
        pc.setData(argData);
        pc.setProcess((BuriExecProcess) factory);
        updateBuriParticipantContextUser(pc, sysContext, provider);

        List<RoleInfo> users = provider.getUser(pc);
        return users;
    }

    protected void updateBuriParticipantContextUser(BuriParticipantContext pc,
            BuriSystemContext sysContext, ParticipantProvider provider) {
        Long insertUserID = getInsertUesrID(sysContext);
        Object userData = sysContext.getUserContext().getUserData();
        if (insertUserID != null) {
            BuriUserEntityDto userDto = userDao.getBuriUser(insertUserID.longValue());
            pc.setInsertUserIdNum(userDto.getUserIDNum());
            pc.setInsertUserIdVal(userDto.getUserIDVal());
        }
        pc.setActionUserIdNum(provider.getUserIDNum(userData, pc.getParticipantType()));
        pc.setActionUserIdVal(provider.getUserIDString(userData, pc.getParticipantType()));
    }

    /**
     * {@code BuriExePackages}を取得します。
     * @param factory
     * @return
     */
    protected BuriExePackages getBuriExePackages(DataAccessFactory factory) {
        BuriExecProcess process = (BuriExecProcess) factory;
        BuriExePackages packages = process.getBuriExePackages();
        return packages;
    }

    /**
     * データが現在留まっているアクティビティに対応するロール種別を返します。
     * @param factory
     * @param walker
     * @return
     */
    protected String getRoleType(DataAccessFactory factory, BranchWalker walker) {
        BuriExecProcess process = (BuriExecProcess) factory;
        String actId = (String) walker.getNowPath().getActivityId().get(0);
        BuriActivityType actType = process.getBuriWorkflowProcessType().getActivityById(actId);
        String roleType = actType.getRoleType();
        return roleType;
    }

    /**
     * データが現在留まっているアクティビティに対応するロール名を返します。
     * @param factory
     * @param walker
     * @return
     */
    protected String getRoleName(DataAccessFactory factory, BranchWalker walker) {
        BuriExecProcess process = (BuriExecProcess) factory;
        String actId = (String) walker.getNowPath().getActivityId().get(0);
        BuriActivityType actType = process.getBuriWorkflowProcessType().getActivityById(actId);
        String roleName = actType.getRoleName();
        return roleName;
    }

    /**
     * 最初にデータを追加したユーザIDを返します。
     * @param sysContext
     * @return
     */
    protected Long getInsertUesrID(BuriSystemContext sysContext) {
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
