/*
 * �쐬��: 2006/01/04
 *
 */
package org.seasar.buri.bao;

import java.util.List;

import org.seasar.buri.dto.FurnitureItemDto;

public interface PetitionBao {
    public static String PROCESS = "���Y�Ǘ�.�w���\��";

    public static BuriConvert CONVERTER[] = new BuriConvert[]{
        new BuriConvert(Long.class,"FurnitureItemDao.getFurnitureItem(#data)")
    };
    
    public static String getWaitApprove_ACTIVITY = "���F�҂�";
    public static String getWaitApprove_ARGS = "userData";
    public List getWaitApprove(String userData);
    
    public static String getEndApprove_ACTIVITY = "���F�ς�";
    public static String getEndApprove_ARGS = "userData";
    public List getEndApprove(String userData);
    
    public static String getNowPetition_ACTIVITY = "�\����";
    public static String getNowPetition_ARGS = "userData";
    public List getNowPetition(String userData);
    
    public static String getReturning_ACTIVITY = "�����߂��ς�";
    public static String getReturning_ARGS = "userData";
    public List getReturning(String userData);
    
    public static String petition_ARGS = "data,userData";
    public void petition(FurnitureItemDto dto,String userData);
    
    public static String approve_ACTION = "approve";
    public static String approve_ARGS = "data,userData";
    public void approve(FurnitureItemDto dto,String userData);

    public static String returning_ACTION = "returning";
    public static String returning_ARGS = "data,userData";
    public void returning(FurnitureItemDto dto,String userData);

}
