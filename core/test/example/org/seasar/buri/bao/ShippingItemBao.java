/*
 * �쐬��: 2006/01/03
 *
 */
package example.org.seasar.buri.bao;

import java.util.List;

import example.org.seasar.buri.dto.ShippingItemDto;

public interface ShippingItemBao {
    public static String PROCESS = "�����Ǘ�.�o�׏ڍ�";
    
    
    public static String getItemWaiting_ACTIVITY = "���i������";
    public List getItemWaiting();
    
    public static String getEndShipping_ACTIVITY = "���i��������";
    public List getEndShipping();
    
    public static String getCancel_ACTIVITY = "cancel�ς�";
    public List getCancel();
    
    public static String getEndShippingCount_ACTIVITY = "���i��������";
    public static String getEndShippingCount_ARGS = "datas";
    public long getEndShippingCount(List datas);
    
    public void startShipping(ShippingItemDto dto);
    public void startShipping(List dtos);

    public void endShipping(ShippingItemDto dto);

    public static String cancel_ACTION = "cancel";
    public void cancel(ShippingItemDto dto);
    public void cancel(List dtos);
    
}
