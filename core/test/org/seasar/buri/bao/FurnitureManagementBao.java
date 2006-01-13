/*
 * 作成日: 2006/01/04
 *
 */
package org.seasar.buri.bao;

import java.util.List;

import org.seasar.buri.bao.BuriConvert;
import org.seasar.buri.dto.FurnitureItemDto;

public interface FurnitureManagementBao {
    public static String PROCESS = "資産管理.備品管理";

    public static BuriConvert CONVERTER[] = new BuriConvert[]{
        new BuriConvert(Long.class,"FurnitureItemDao.getFurnitureItem(#data)")
    };
    
    public static String getNowUse_ACTIVITY = "利用中";
    public List getNowUse();
    
    public static String findNowUse_ACTIVITY = "利用中";
    public List findNowUse(FurnitureItemDto findDto);
    
    public static String getEndDepreciation_ACTIVITY = "償却期間終了";
    public List getEndDepreciation();
    
    public static String getEndLease_ACTIVITY = "リース終了";
    public List getEndLease();
    
    public void enterItem(FurnitureItemDto dto);

    public void timeorverItem(long itemID);
}
