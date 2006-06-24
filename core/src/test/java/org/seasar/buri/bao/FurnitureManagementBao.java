/*
 * �쐬��: 2006/01/04
 *
 */
package org.seasar.buri.bao;

import java.util.List;

import org.seasar.buri.bao.BuriConvert;
import org.seasar.buri.dto.FurnitureItemDto;

public interface FurnitureManagementBao {
    public static String PROCESS = "���Y�Ǘ�.���i�Ǘ�";

    public static BuriConvert CONVERTER[] = new BuriConvert[]{
        new BuriConvert(Long.class,"FurnitureItemDao.getFurnitureItem(#data)")
    };
    
    public static String getNowUse_ACTIVITY = "���p��";
    public List getNowUse();
    
    public static String findNowUse_ACTIVITY = "���p��";
    public List findNowUse(FurnitureItemDto findDto);
    
    public static String getEndDepreciation_ACTIVITY = "���p���ԏI��";
    public List getEndDepreciation();
    
    public static String getEndLease_ACTIVITY = "���[�X�I��";
    public List getEndLease();
    
    public void enterItem(FurnitureItemDto dto);

    public void timeorverItem(long itemID);
}
