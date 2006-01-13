/*
 * �쐬��: 2006/01/04
 *
 */
package org.seasar.buri.bao;

import java.util.List;

import org.seasar.buri.dto.FurnitureItemDto;

public interface PetitionUserBao {
    public static String PROCESS = "���Y�Ǘ�.�w���\��";

    public static BuriConvert CONVERTER[] = new BuriConvert[]{
        new BuriConvert(Long.class,"FurnitureItemDao.getFurnitureItem(#data)")
    };
    
    public static String USERINFO = "user";
    
    public static String getWaitApprove_ACTIVITY = "���F�҂�";
    public List getWaitApprove();
    
    public static String getEndApprove_ACTIVITY = "���F�ς�";
    public List getEndApprove();
    
    public static String getNowPetition_ACTIVITY = "�\����";
    public List getNowPetition();
    
    public static String getReturning_ACTIVITY = "�����߂��ς�";
    public List getReturning();
    
    public void petition(FurnitureItemDto dto);
    
    public static String approve_ACTION = "approve";
    public void approve(FurnitureItemDto dto);

    public static String returning_ACTION = "returning";
    public void returning(FurnitureItemDto dto);

}
