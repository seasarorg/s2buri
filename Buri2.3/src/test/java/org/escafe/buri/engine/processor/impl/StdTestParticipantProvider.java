/*
 * 作成日: 2006/06/14
 *
 */
package org.escafe.buri.engine.processor.impl;

import java.util.ArrayList;
import java.util.List;

import org.escafe.buri.dao.BuriTestUserDao;
import org.escafe.buri.dto.BuriTestUserDto;
import org.escafe.buri.engine.IdentityInfo;
import org.escafe.buri.engine.ParticipantContext;
import org.escafe.buri.engine.ParticipantProvider;

public class StdTestParticipantProvider implements ParticipantProvider {
    private BuriTestUserDao userDao;

    public BuriTestUserDto getUserData(IdentityInfo appUserId) {
        if (appUserId == null || appUserId.getIdNumber() == null) {
            return null;
        }
        BuriTestUserDto dto = userDao.getBuriTestUser(appUserId.getIdNumber());
        return dto;
    }

    public IdentityInfo getUserId(Object userData) {
        if (userData == null) {
            return new IdentityInfo(null, "無関係");
        }
        BuriTestUserDto dto = (BuriTestUserDto) userData;
        IdentityInfo appUserId = new IdentityInfo();
        appUserId.setIdNumber(dto.getUserID());
        appUserId.setIdString(dto.getUserName());
        return appUserId;
    }

    public boolean hasAuthority(ParticipantContext context) {
        BuriTestUserDto dto = getUserData(context.getUserId());
        if (dto == null) {
            return context.getParticipantName().equalsIgnoreCase("無関係");
        }
        if (dto.getRoleName().indexOf(context.getParticipantName()) >= 0) {
            return true;
        }
        return false;
    }

    public List<IdentityInfo> getAuthorizedUserIds(ParticipantContext context) {
        Long currentUserIdNumber = context.getUserId().getIdNumber();
        String roleName = context.getParticipantName();
        String startRoleName = context.getStartParticipantName();

        // 上の方を探す
        List<BuriTestUserDto> roleList = userDao.getUserListByParentUserID(currentUserIdNumber, roleName);
        if (!roleName.equals("無関係")) {
            if (roleList.isEmpty()) {
                //一段下を探す
                roleList = userDao.getUserListByUserID(currentUserIdNumber, roleName);
            }
            if (roleList.size() == 0 && roleName.equals("下っ端") && startRoleName.equals("一番上")) {
                //一番上と下っ端が対象の場合もう一段下を探す
                List<BuriTestUserDto> mannaka = userDao.getUserListByParentUserID(currentUserIdNumber, "真ん中");
                for (BuriTestUserDto dto : mannaka) {
                    List<BuriTestUserDto> tmpList = userDao.getUserListByParentUserID(new Long(dto.getUserID()), roleName);
                    roleList.addAll(tmpList);
                }
            }
            if (roleList.size() == 0 && roleName.equals("真ん中") && startRoleName.equals("下っ端")) {
                List<BuriTestUserDto> selfData = userDao.getUserListByUserID(currentUserIdNumber, "下っ端");
                for (BuriTestUserDto dto : selfData) {
                    List<BuriTestUserDto> tmpList = userDao.getUserListByUserID(new Long(dto.getParentUserID()), roleName);
                    roleList.addAll(tmpList);
                }
            }
            if (roleList.size() == 0 && roleName.equals("一番上") && startRoleName.equals("真ん中")) {
                List<BuriTestUserDto> selfData = userDao.getUserListByUserID(currentUserIdNumber, "真ん中");
                for (BuriTestUserDto dto : selfData) {
                    List<BuriTestUserDto> tmpList = userDao.getUserListByUserID(new Long(dto.getParentUserID()), roleName);
                    roleList.addAll(tmpList);
                }
            }
        }
        List<IdentityInfo> result = new ArrayList<IdentityInfo>(roleList.size());
        for (BuriTestUserDto dto : roleList) {
            result.add(getUserId(dto));
        }
        if (result.isEmpty()) {
            result.add(new IdentityInfo(null, "無関係"));
        }
        return result;
    }

    public BuriTestUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(BuriTestUserDao userDao) {
        this.userDao = userDao;
    }

}
