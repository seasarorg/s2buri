/*
 * ì¬ú: 2006/01/03
 *
 */
package example.org.seasar.buri.bao;

import java.util.List;

import example.org.seasar.buri.dto.ShippingItemDto;

public interface ShippingItemBao {
    public static String PROCESS = "¶Ç.o×Ú×";
    
    
    public static String getItemWaiting_ACTIVITY = "¤iõ";
    public List getItemWaiting();
    
    public static String getEndShipping_ACTIVITY = "¤iõ®¹";
    public List getEndShipping();
    
    public static String getCancel_ACTIVITY = "cancelÏÝ";
    public List getCancel();
    
    public static String getEndShippingCount_ACTIVITY = "¤iõ®¹";
    public static String getEndShippingCount_ARGS = "datas";
    public long getEndShippingCount(List datas);
    
    public void startShipping(ShippingItemDto dto);
    public void startShipping(List dtos);

    public void endShipping(ShippingItemDto dto);

    public static String cancel_ACTION = "cancel";
    public void cancel(ShippingItemDto dto);
    public void cancel(List dtos);
    
}
