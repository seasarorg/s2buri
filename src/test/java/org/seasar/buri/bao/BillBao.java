/*
 * �쐬��: 2006/01/03
 *
 */
package org.seasar.buri.bao;

import java.util.List;

import example.org.seasar.buri.dto.BillDto;
import example.org.seasar.buri.dto.ShippingSetDto;

public interface BillBao {
    public static Class TARGETDTO = BillDto.class;
    public static String PROCESS = "�����Ǘ�.����";
    
    public static String getBillWaiting_ACTIVITY = "�������";
    public List getBillWaiting();
    
    public static String getReBill_ACTIVITY = "�Đ�������";
    public List getReBill();
    
    public static String getEndBill_ACTIVITY = "�����I��";
    public List getEndBill();
    
    public static String enterBill_ACTIVITY = "�����˗�";
    public void enterBill(ShippingSetDto dto);
    
    public static String bill_ACTIVITY = "�������";
    public void bill(BillDto dto);
    
    public static String checkPayment_ACTIVITY = "�Đ�������";
    public void checkPayment(BillDto dto);

}
