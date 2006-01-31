/*
 * 作成日: 2006/01/03
 *
 */
package example.org.seasar.buri.bao;

import java.util.List;

import org.seasar.buri.bao.BuriConvert;

import example.org.seasar.buri.dto.OrderInfoDto;

public interface OrderBao {
    public static String PROCESS = "注文管理.注文";

    public static BuriConvert CONVERTER[] = new BuriConvert[]{
        new BuriConvert(Long.class,"OrderInfoDao.getOrderInfo(#data)")
    };

    public static String getUnderWork_ACTIVITY = "出荷作業中";
    public List getUnderWork();
    
    public static String getEndShipping_ACTIVITY = "出荷終了";
    public List getEndShipping();

    public static String getOrderEnd_ACTIVITY = "終了";
    public List getOrderEnd();

    public static String getOrderCancelEnd_ACTIVITY = "キャンセル終了";
    public List getOrderCancelEnd();
    
    public static String order_ACTIVITYVALIDATE = "注文";
    public void order(OrderInfoDto dto);

    public static String endShipping_ACTIVITYVALIDATE = "出荷作業中";
    public void endShipping(long orderID);
    
    public static String endBill_ACTIVITYVALIDATE = "出荷終了";
    public void endBill(long orderID);
    
    public static String cancel_ACTIVITYVALIDATE = "出荷作業中,出荷終了";
    public static String cancel_ACTION = "cancel";
    public static String cancel_RESULT = "#cancelStatus";
    public String cancel(long orderID);
    
}
