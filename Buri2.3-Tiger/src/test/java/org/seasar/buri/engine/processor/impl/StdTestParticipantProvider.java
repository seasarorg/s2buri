/*
 * 作成日: 2006/06/14
 *
 */
package org.seasar.buri.engine.processor.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.seasar.buri.dao.BuriTestUserDao;
import org.seasar.buri.dto.BuriTestUserDto;
import org.seasar.buri.engine.BuriParticipantContext;
import org.seasar.buri.engine.IdentityInfo;
import org.seasar.buri.engine.ParticipantContext;
import org.seasar.buri.engine.ParticipantProvider;

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
        BuriTestUserDto dto = getUserData(context.getCurrentUserId());
        if (dto == null) {
            return context.getParticipantName().equalsIgnoreCase("無関係");
        }
        if (dto.getRoleName().indexOf(context.getParticipantName()) >= 0) {
            return true;
        }
        return false;
    }

    public List<IdentityInfo> getAuthorizedUserIds(ParticipantContext context) {
        BuriParticipantContext buriContext = (BuriParticipantContext) context;
        Long idNum = context.getCurrentUserId().getIdNumber();
        String roleName = context.getParticipantName();
        // 上の方を探す
        List roleList = userDao.getUserListByParentUserID(idNum, roleName);
        if (!roleName.equals("無関係")) {
            if (roleList.size() == 0) {
                //一段下を探す
                roleList = userDao.getUserListByUserID(idNum, roleName);
            }
            if (roleList.size() == 0 && roleName.equals("下っ端")
                    && buriContext.getStartRoleName().equals("一番上")) {
                //一番上と下っ端が対象の場合もう一段下を探す
                List mannaka = userDao.getUserListByParentUserID(idNum, "真ん中");
                Iterator ite = mannaka.iterator();
                while (ite.hasNext()) {
                    BuriTestUserDto dto = (BuriTestUserDto) ite.next();
                    List tmpList = userDao.getUserListByParentUserID(new Long(dto.getUserID()),
                        roleName);
                    roleList.addAll(tmpList);
                }
            }
            if (roleList.size() == 0 && roleName.equals("真ん中")
                    && buriContext.getStartRoleName().equals("下っ端")) {
                List selfData = userDao.getUserListByUserID(idNum, "下っ端");
                Iterator ite = selfData.iterator();
                while (ite.hasNext()) {
                    BuriTestUserDto dto = (BuriTestUserDto) ite.next();
                    List tmpList = userDao.getUserListByUserID(new Long(dto.getParentUserID()),
                        roleName);
                    roleList.addAll(tmpList);
                }

            }
            if (roleList.size() == 0 && roleName.equals("一番上")
                    && buriContext.getStartRoleName().equals("真ん中")) {
                List selfData = userDao.getUserListByUserID(idNum, "真ん中");
                Iterator ite = selfData.iterator();
                while (ite.hasNext()) {
                    BuriTestUserDto dto = (BuriTestUserDto) ite.next();
                    List tmpList = userDao.getUserListByUserID(new Long(dto.getParentUserID()),
                        roleName);
                    roleList.addAll(tmpList);
                }

            }

        }
        List<IdentityInfo> result = new ArrayList<IdentityInfo>();
        Iterator ite = roleList.iterator();
        while (ite.hasNext()) {
            BuriTestUserDto dto = (BuriTestUserDto) ite.next();
            IdentityInfo appUserId = getUserId(dto);
            result.add(appUserId);
        }
        if (result.size() == 0) {
            IdentityInfo appUserId = new IdentityInfo();
            appUserId.setIdNumber(null);
            appUserId.setIdString("無関係");
            result.add(appUserId);
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
