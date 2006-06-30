/*
 * ì¬“ú: 2006/01/03
 *
 */
package org.seasar.buri.bao;

import java.util.List;

import example.org.seasar.buri.dto.BillDto;
import example.org.seasar.buri.dto.ShippingSetDto;

public interface BillBao {
    public static Class TARGETDTO = BillDto.class;
    public static String PROCESS = "’•¶ŠÇ—.¿‹";
    
    public static String getBillWaiting_ACTIVITY = "¿‹ì‹Æ";
    public List getBillWaiting();
    
    public static String getReBill_ACTIVITY = "Ä¿‹€”õ";
    public List getReBill();
    
    public static String getEndBill_ACTIVITY = "¿‹I—¹";
    public List getEndBill();
    
    public static String enterBill_ACTIVITY = "¿‹ˆË—Š";
    public void enterBill(ShippingSetDto dto);
    
    public static String bill_ACTIVITY = "¿‹ì‹Æ";
    public void bill(BillDto dto);
    
    public static String checkPayment_ACTIVITY = "Ä¿‹€”õ";
    public void checkPayment(BillDto dto);

}
