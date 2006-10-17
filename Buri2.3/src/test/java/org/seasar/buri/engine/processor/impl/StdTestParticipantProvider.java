/*
 * �쐬��: 2006/06/14
 *
 */
package org.seasar.buri.engine.processor.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.seasar.buri.dao.BuriTestUserDao;
import org.seasar.buri.dto.BuriTestUserDto;
import org.seasar.buri.engine.BuriParticipantContext;
import org.seasar.buri.engine.ParticipantContext;
import org.seasar.buri.engine.ParticipantProvider;
import org.seasar.buri.engine.RoleInfo;

public class StdTestParticipantProvider implements ParticipantProvider {
    private BuriTestUserDao userDao;

    public boolean isUserInRole(Object userData, String participantName, String participantType) {
        if(userData==null && participantName.equalsIgnoreCase("���֌W")) {
            return true;
        }
        BuriTestUserDto dto = (BuriTestUserDto)userData;
        if(dto.getRoleName().indexOf(participantName) >= 0) {
            return true;
        }
        return false;
    }

    public Object getUserData(Long userIDNum, String userIDString) {
        BuriTestUserDto dto = userDao.getBuriTestUser(userIDNum.intValue());
        return dto;
    }

    public String getUserIDString(Object userData, String participantType) {
        if(userData==null) {
            return "���֌W";
        }
        return null;
    }

    public Long getUserIDNum(Object userData, String participantType) {
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
        // TODO �����������ꂽ���\�b�h�E�X�^�u
        return null;
    }

    public List getUser(ParticipantContext context) {
        BuriParticipantContext buriContext = (BuriParticipantContext)context;
        Long idNum = context.getActionUserIdNum();
        String roleName = context.getParticipantName();
        // ��̕���T��
        List roleList = userDao.getUserListByParentUserID(idNum,roleName);
        if(! roleName.equals("���֌W")) {
            if(roleList.size()==0) {
                //��i����T��
                roleList = userDao.getUserListByUserID(idNum,roleName);
            }
            if(roleList.size()==0 && roleName.equals("�����[") && buriContext.getStartRoleName().equals("��ԏ�")) {
                //��ԏ�Ɖ����[���Ώۂ̏ꍇ������i����T��
                List mannaka = userDao.getUserListByParentUserID(idNum,"�^��");
                Iterator ite = mannaka.iterator();
                while(ite.hasNext()) {
                    BuriTestUserDto dto = (BuriTestUserDto)ite.next();
                    List tmpList = userDao.getUserListByParentUserID(new Long(dto.getUserID()),roleName);
                    roleList.addAll(tmpList);
                }
            }
            if(roleList.size()==0 && roleName.equals("�^��") && buriContext.getStartRoleName().equals("�����[")) {
                List selfData = userDao.getUserListByUserID(idNum,"�����[");
                Iterator ite = selfData.iterator();
                while(ite.hasNext()) {
                    BuriTestUserDto dto = (BuriTestUserDto)ite.next();
                    List tmpList = userDao.getUserListByUserID(new Long(dto.getParentUserID()),roleName);
                    roleList.addAll(tmpList);
                }
                
            }
            if(roleList.size()==0 && roleName.equals("��ԏ�") && buriContext.getStartRoleName().equals("�^��")) {
                List selfData = userDao.getUserListByUserID(idNum,"�^��");
                Iterator ite = selfData.iterator();
                while(ite.hasNext()) {
                    BuriTestUserDto dto = (BuriTestUserDto)ite.next();
                    List tmpList = userDao.getUserListByUserID(new Long(dto.getParentUserID()),roleName);
                    roleList.addAll(tmpList);
                }
                
            }
            
        }
        List result = new ArrayList();
        Iterator ite = roleList.iterator();
        while(ite.hasNext()) {
            BuriTestUserDto dto = (BuriTestUserDto)ite.next();
            RoleInfo roleInfo = new RoleInfo();
            roleInfo.setIdNum(this.getUserIDNum(dto,context.getParticipantType()));
            roleInfo.setIdVar(this.getUserIDString(dto,context.getParticipantType()));
            result.add(roleInfo);
        }
        if(result.size()==0) {
            RoleInfo roleInfo = new RoleInfo();
            roleInfo.setIdNum(null);
            roleInfo.setIdVar("���֌W");
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
