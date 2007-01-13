/*
 * 作成日: 2006/01/03
 *
 */
package org.seasar.buri.bao;

import java.util.List;

import org.seasar.buri.bao.BuriConvert;

import example.org.seasar.buri.dto.OrderInfoDto;
import example.org.seasar.buri.dto.ShippingSetDto;

public interface ShippingBao {
    public static String PROCESS = "注文管理.出荷";
    public static Class TARGETDTO = ShippingSetDto.class;

    public static BuriConvert CONVERTER[] = new BuriConvert[]{
        new BuriConvert(Long.class,"ShippingSetDao.getShippingSetDto(#data)")
        ,new BuriConvert(OrderInfoDto.class,"ShippingSetDao.getDtoByOrderTitleID(#data)")
    };
    
    public static String getNowWaiting_ACTIVITY = "商品待ち";
    public List getNowWaiting();
    
    public static String getEndShipping_ACTIVITY = "出荷済み";
    public List getEndShipping();
    
    public static String getShippingCancel_ACTIVITY = "キャンセル済み";
    public List getShippingCancel();
    
    public static String shipping_ACTIVITY = "出荷依頼";
    public void shipping(OrderInfoDto dto);

    public static String checkEdnShipping_ACTIVITY = "商品待ち";
    public void checkEdnShipping(long shippingID);

    public static String cancel_ACTIVITY = "商品待ち";
    public static String cancel_ACTION = "cancel";
    public void cancel(OrderInfoDto dto);
    
}
