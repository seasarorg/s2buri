package org.seasar.buri.examples.gas.bao;

import java.util.List;


import org.seasar.buri.bao.BuriConvert;
import org.seasar.buri.examples.gas.entity.MeterCheck;

public interface GasRateBao {
    public static String PROCESS = "GasRate.GasRateProcess";
    
    public static BuriConvert CONVERTER[] = new BuriConvert[]{
        new BuriConvert(Long.class,"MeterCheckDao.getMeterCheckById(#data)")
    };

    public static String getEndBillList_ACTIVITY = "éxï•ë“Çø";
    public List getEndBillList();
    
    public static String findEndBillListByCriteria_ACTIVITY = "éxï•ë“Çø";
    public List findEndBillListByCriteria(MeterCheck findDto);
    
    public static String getApprovedPaymentList_ACTIVITY = "éxï•èàóùäÆóπçœÇ›";
    public List getApprovedPaymentList();
    
    public static String getRemindedPaymentList_ACTIVITY = "ì¬ë£çœÇ›";
    public List getRemindedPaymentList();
    
    
    public void enterMeterCheck(MeterCheck data);
    public void proceed(MeterCheck data);
    public void proceed(Long meterCheckId);
}
