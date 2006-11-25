/*
 * çÏê¨ì˙: 2006/06/09
 *
 */
package org.seasar.buri.dao.util.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
    private Map userIDCache = new HashMap();
    
    public void dispose() {
    	userIDCache.clear();
    }
    
    public long convertUserID(Long userIDNum,String userIDVal) {
        String userIDKey = ""+userIDNum+"/"+userIDVal;
        if(userIDCache.containsKey(userIDKey)) {
            Long userID = (Long)userIDCache.get(userIDKey);
            return userID.longValue();
        }
        BuriUserEntityDto dto = convertBuriUserEntityDto(userIDNum,userIDVal);
        userIDCache.put(userIDKey,new Long(dto.getBuriUserID()));
        return dto.getBuriUserID();
    }
    
    protected BuriUserEntityDto convertBuriUserEntityDto(Long userIDNum,String userIDVal) {
        BuriUserEntityDto dto = userDao.getBuriUserFromIds(userIDNum,userIDVal);
        if(dto == null) {
            dto = new BuriUserEntityDto();
            dto.setUserIDNum(userIDNum);
            dto.setUserIDVal(userIDVal);
            userDao.insert(dto);
        }
        return dto;
    }

    public List convUserIDListFromRoleInfos(List roleInfos) {
        List resutlt = new ArrayList();
        Iterator ite = roleInfos.iterator();
        while(ite.hasNext()) {
            RoleInfo role = (RoleInfo)ite.next();
            long userID = convertUserID(role.getIdNum(),role.getIdVar());
            resutlt.add(new Long(userID));
        }
        return resutlt;
    }
    
    public Object getUserData(DataAccessFactory factory,long userID,Long userIDNum,String userIDStr) {
        if(userIDNum == null && userIDStr == null) {
            BuriUserEntityDto userDto = userDao.getBuriUser(userID);
            userIDNum = userDto.getUserIDNum();
            userIDStr = userDto.getUserIDVal();
        }
        BuriExePackages packages = getBuriExePackages(factory);
        ParticipantProvider provider = packages.getParticipantProvider();
        Object result = provider.getUserData(userIDNum,userIDStr);
        return result;
    }
    
    public List getTgtRoleInfoList(DataAccessFactory factory,BuriSystemContext sysContext,BranchWalker walker) {
        BuriExePackages packages = getBuriExePackages(factory);
        ParticipantProvider provider = packages.getParticipantProvider();
        String roleName = getRoleName(factory,walker);
        String roleType = getRoleName(factory,walker);
        Object argData = sysContext.getUserContext().getData();
        
        BuriParticipantContext pc = new BuriParticipantContext();
        pc.setParticipantName(roleName);
        pc.setParticipantType(roleType);
        pc.setStartRoleName(sysContext.getStartRoleName());
        pc.setSystemContext(sysContext);
        pc.setUserContext(sysContext.getUserContext());
        pc.setWalker(walker);
        pc.setData(argData);
        pc.setProcess((BuriExecProcess)factory);
        updateBuriParticipantContextUser(pc,sysContext,provider);
        
        List users = provider.getUser(pc);
        return users;
    }
    
    protected void  updateBuriParticipantContextUser(BuriParticipantContext pc,BuriSystemContext sysContext,ParticipantProvider provider) {
        Long insertUserID = getInsertUesrID(sysContext);
        Object userData = sysContext.getUserContext().getUserData();
        if(insertUserID != null) {
            BuriUserEntityDto userDto = userDao.getBuriUser(insertUserID.longValue());
            pc.setInsertUserIdNum(userDto.getUserIDNum());
            pc.setInsertUserIdVal(userDto.getUserIDVal());
        }
        pc.setActionUserIdNum(provider.getUserIDNum(userData,pc.getParticipantType()));
        pc.setActionUserIdVal(provider.getUserIDString(userData,pc.getParticipantType()));
    }
    
    protected BuriExePackages getBuriExePackages(DataAccessFactory factory) {
        BuriExecProcess process = (BuriExecProcess)factory;
        BuriExePackages packages = process.getBuriExePackages();
        return packages;
    }
    
    protected String getRoleType(DataAccessFactory factory,BranchWalker walker) {
        BuriExecProcess process = (BuriExecProcess)factory;
        String actId = (String)walker.getNowPath().getActivityId().get(0);
        BuriActivityType actType =  process.getBuriWorkflowProcessType().getActivityById(actId);
        String roleType = actType.getRoleType();
        return roleType;
    }
    
    protected String getRoleName(DataAccessFactory factory,BranchWalker walker) {
        BuriExecProcess process = (BuriExecProcess)factory;
        String actId = (String)walker.getNowPath().getActivityId().get(0);
        BuriActivityType actType =  process.getBuriWorkflowProcessType().getActivityById(actId);
        String roleName = actType.getRoleName();
        return roleName;
    }
    
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

    public Map getUserIDCache() {
        return userIDCache;
    }
}
