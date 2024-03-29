/*
 * 作成日: 2006/01/03
 *
 */
package org.escafe.buri.s2dao.bao;

import java.util.List;

import org.escafe.buri.bao.BuriConvert;

import example.org.escafe.buri.dto.OrderInfoDto;

public interface OrderBao {
	public static Class TARGETDTO = OrderInfoDto.class;

	public static String PROCESS = "注文管理.注文";

	public static BuriConvert CONVERTER[] =
	    new BuriConvert[] { new BuriConvert(
	        Long.class,
	        "orderInfoDao.getOrderInfo(#data)") };

	public static String getUnderWork_ACTIVITY = "出荷作業中";

	public List getUnderWork();

	public static String getEndShipping_ACTIVITY = "出荷終了";

	public List getEndShipping();

	public static String getOrderEnd_ACTIVITY = "終了";

	public List getOrderEnd();

	public static String getOrderCancelEnd_ACTIVITY = "キャンセル終了";

	public List getOrderCancelEnd();

	public static String order_ACTIVITY = "注文";

	public void order(OrderInfoDto dto);

	public static String endShipping_ACTIVITY = "出荷作業中";

	public void endShipping(Long orderId);

	public static String endBill_ACTIVITY = "出荷終了";

	public void endBill(Long orderId);

	public static String cancel_ACTIVITY = "出荷作業中,出荷終了";

	public static String cancel_ACTION = "cancel";

	public static String cancel_RESULT = "#cancelStatus";

	public String cancel(Long orderId);
}
