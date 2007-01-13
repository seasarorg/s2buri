/*
 * 作成日: 2006/01/03
 *
 */
package org.seasar.buri.bao;

import java.util.List;

import example.org.seasar.buri.dto.ShippingItemDto;

public interface ShippingItemBao {
    public static String PROCESS = "注文管理.出荷詳細";
    public static Class TARGETDTO = ShippingItemDto.class;
    
    
    public static String getItemWaiting_ACTIVITY = "商品準備中";
    public List getItemWaiting();
    
    public static String getEndShipping_ACTIVITY = "商品準備完了";
    public List getEndShipping();
    
    public static String getCancel_ACTIVITY = "cancel済み";
    public List getCancel();
    
    public static String getEndShippingCount_ACTIVITY = "商品準備完了";
    public static String getEndShippingCount_ARGS = "datas";
    public long getEndShippingCount(List datas);
    
    public static String startShipping_ACTIVITY = "商品準備開始";
    public void startShipping(ShippingItemDto dto);
    public void startShipping(List dtos);

    public static String endShipping_ACTIVITY = "商品準備中";
    public void endShipping(ShippingItemDto dto);

    public static String cancel_ACTIVITY = "商品準備中";
    public static String cancel_ACTION = "cancel";
    public void cancel(ShippingItemDto dto);
    public void cancel(List dtos);
    
}
