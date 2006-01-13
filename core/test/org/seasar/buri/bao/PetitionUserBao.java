/*
 * 作成日: 2006/01/04
 *
 */
package org.seasar.buri.bao;

import java.util.List;

import org.seasar.buri.dto.FurnitureItemDto;

public interface PetitionUserBao {
    public static String PROCESS = "資産管理.購入申請";

    public static BuriConvert CONVERTER[] = new BuriConvert[]{
        new BuriConvert(Long.class,"FurnitureItemDao.getFurnitureItem(#data)")
    };
    
    public static String USERINFO = "user";
    
    public static String getWaitApprove_ACTIVITY = "承認待ち";
    public List getWaitApprove();
    
    public static String getEndApprove_ACTIVITY = "承認済み";
    public List getEndApprove();
    
    public static String getNowPetition_ACTIVITY = "申請中";
    public List getNowPetition();
    
    public static String getReturning_ACTIVITY = "差し戻し済み";
    public List getReturning();
    
    public void petition(FurnitureItemDto dto);
    
    public static String approve_ACTION = "approve";
    public void approve(FurnitureItemDto dto);

    public static String returning_ACTION = "returning";
    public void returning(FurnitureItemDto dto);

}
