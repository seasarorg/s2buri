/*
 * �쐬��: 2006/01/03
 *
 */
package example.org.seasar.buri.bao;

import java.util.List;

import org.seasar.buri.bao.BuriConvert;

import example.org.seasar.buri.dto.OrderInfoDto;

public interface ShippingBao {
    public static String PROCESS = "�����Ǘ�.�o��";

    public static BuriConvert CONVERTER[] = new BuriConvert[]{
        new BuriConvert(Long.class,"ShippingSetDao.getShippingSetDto(#data)")
        ,new BuriConvert(OrderInfoDto.class,"ShippingSetDao.getDtoByOrderTitleID(#data)")
    };
    
    public static String getNowWaiting_ACTIVITY = "���i�҂�";
    public List getNowWaiting();
    
    public static String getEndShipping_ACTIVITY = "�o�׍ς�";
    public List getEndShipping();
    
    public static String getShippingCancel_ACTIVITY = "�L�����Z���ς�";
    public List getShippingCancel();
    
    public void shipping(OrderInfoDto dto);

    public void checkEdnShipping(long shippingID);

    public static String cancel_ACTION = "cancel";
    public void cancel(OrderInfoDto dto);
    
}
