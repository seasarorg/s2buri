/*
 * 作成日: 2006/01/03
 *
 */
package org.escafe.buri.bao;

import java.util.List;

import example.org.escafe.buri.dto.BillDto;
import example.org.escafe.buri.dto.ShippingSetDto;

public interface BillBao {
    public static Class TARGETDTO = BillDto.class;
    public static String PROCESS = "注文管理.請求";
    
    public static String getBillWaiting_ACTIVITY = "請求作業";
    public List getBillWaiting();
    
    public static String getReBill_ACTIVITY = "再請求準備";
    public List getReBill();
    
    public static String getEndBill_ACTIVITY = "請求終了";
    public List getEndBill();
    
    public static String enterBill_ACTIVITY = "請求依頼";
    public void enterBill(ShippingSetDto dto);
    
    public static String bill_ACTIVITY = "請求作業";
    public void bill(BillDto dto);
    
    public static String checkPayment_ACTIVITY = "再請求準備";
    public void checkPayment(BillDto dto);

}
