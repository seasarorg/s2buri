/*
 * ì¬ú: 2006/01/03
 *
 */
package org.seasar.buri.bao;

import java.util.List;

import example.org.seasar.buri.dto.ShippingItemDto;

public interface ShippingItemBao {
    public static String PROCESS = "¶Ç.o×Ú×";
    public static Class TARGETDTO = ShippingItemDto.class;
    
    
    public static String getItemWaiting_ACTIVITY = "¤iõ";
    public List getItemWaiting();
    
    public static String getEndShipping_ACTIVITY = "¤iõ®¹";
    public List getEndShipping();
    
    public static String getCancel_ACTIVITY = "cancelÏÝ";
    public List getCancel();
    
    public static String getEndShippingCount_ACTIVITY = "¤iõ®¹";
    public static String getEndShippingCount_ARGS = "datas";
    public long getEndShippingCount(List datas);
    
    public static String startShipping_ACTIVITY = "¤iõJn";
    public void startShipping(ShippingItemDto dto);
    public void startShipping(List dtos);

    public static String endShipping_ACTIVITY = "¤iõ";
    public void endShipping(ShippingItemDto dto);

    public static String cancel_ACTIVITY = "¤iõ";
    public static String cancel_ACTION = "cancel";
    public void cancel(ShippingItemDto dto);
    public void cancel(List dtos);
    
}
