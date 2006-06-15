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
import org.seasar.buri.engine.ParticipantContext;
import org.seasar.buri.engine.ParticipantProvider;
import org.seasar.buri.engine.RoleInfo;

public class StdTestParticipantProvider implements ParticipantProvider {
    private BuriTestUserDao userDao;

    public boolean isUserInRole(Object userData, String participantName) {
        if(userData==null && participantName.equalsIgnoreCase("無関係")) {
            return true;
        }
        BuriTestUserDto dto = (BuriTestUserDto)userData;
        if(dto.getRoleName().indexOf(participantName) >= 0) {
            return true;
        }
        return false;
    }

    public String getUserIDString(Object userData) {
        if(userData==null) {
            return "無関係";
        }
        return null;
    }

    public Long getUserIDNum(Object userData) {
        if(userData==null) {
            return null;
        }
        BuriTestUserDto dto = (BuriTestUserDto)userData;
        return new Long(dto.getUserID());
    }

    public boolean hasRoleUser(ParticipantContext context) {
        List roleList = userDao.getUserListByParentUserID(context.getActionUserIdNum(),context.getParticipantName());
        if(roleList.size()>0) {
            return true;
        } else {
            return false;
        }
    }

    public RoleInfo getSingleUser(ParticipantContext context) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    public List getUser(ParticipantContext context) {
        Long idNum = context.getActionUserIdNum();
        String roleName = context.getParticipantName();
        // 上の方を探す
        List roleList = userDao.getUserListByParentUserID(idNum,roleName);
        if(! roleName.equals("無関係")) {
            if(roleList.size()==0) {
                //一段下を探す
                roleList = userDao.getUserListByUserID(idNum,roleName);
            }
            if(roleList.size()==0 && roleName.equals("下っ端")) {
                //もう一段下を探す
                List mannaka = userDao.getUserListByParentUserID(idNum,"真ん中");
                Iterator ite = mannaka.iterator();
                while(ite.hasNext()) {
                    BuriTestUserDto dto = (BuriTestUserDto)ite.next();
                    List tmpList = userDao.getUserListByParentUserID(new Long(dto.getUserID()),roleName);
                    roleList.addAll(tmpList);
                }
            }
        }
        List result = new ArrayList();
        Iterator ite = roleList.iterator();
        while(ite.hasNext()) {
            BuriTestUserDto dto = (BuriTestUserDto)ite.next();
            RoleInfo roleInfo = new RoleInfo();
            roleInfo.setIdNum(this.getUserIDNum(dto));
            roleInfo.setIdVar(this.getUserIDString(dto));
            result.add(roleInfo);
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
