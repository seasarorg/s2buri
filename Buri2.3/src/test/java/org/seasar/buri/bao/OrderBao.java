/*
 * �쐬��: 2006/01/03
 *
 */
package org.seasar.buri.bao;

import java.util.List;

import org.seasar.buri.bao.BuriConvert;

import example.org.seasar.buri.dto.OrderInfoDto;

public interface OrderBao {
    public static Class TARGETDTO = OrderInfoDto.class;
    public static String PROCESS = "�����Ǘ�.����";

    public static BuriConvert CONVERTER[] = new BuriConvert[]{
        new BuriConvert(Long.class,"OrderInfoDao.getOrderInfo(#data)")
    };

    public static String getUnderWork_ACTIVITY = "�o�׍�ƒ�";
    public List getUnderWork();
    
    public static String getEndShipping_ACTIVITY = "�o�׏I��";
    public List getEndShipping();

    public static String getOrderEnd_ACTIVITY = "�I��";
    public List getOrderEnd();

    public static String getOrderCancelEnd_ACTIVITY = "�L�����Z���I��";
    public List getOrderCancelEnd();
    
    public static String order_ACTIVITY = "����";
    public void order(OrderInfoDto dto);

    public static String endShipping_ACTIVITY = "�o�׍�ƒ�";
    public void endShipping(long orderID);
    
    public static String endBill_ACTIVITY = "�o�׏I��";
    public void endBill(long orderID);
    
    public static String cancel_ACTIVITY = "�o�׍�ƒ�,�o�׏I��";
    public static String cancel_ACTION = "cancel";
    public static String cancel_RESULT = "#cancelStatus";
    public String cancel(long orderID);
    
}