/*
 * 作成日: 2006/01/03
 *
 */
package org.escafe.buri.bao;

import java.util.List;

import example.org.escafe.buri.entity.OrderTitle;

public interface OrderBao {
	public static Class<?> TARGETDTO = OrderTitle.class;

	public static String PROCESS = "注文管理.注文";

	public static BuriConvert CONVERTER[] =
	    new BuriConvert[] { new BuriConvert(
	        Long.class,
	        "orderTitleService.findByIdWithOrderDetail(#data)") };

	public static String getUnderWork_ACTIVITY = "出荷作業中";

	public List<OrderTitle> getUnderWork();

	public static String getEndShipping_ACTIVITY = "出荷終了";

	public List<OrderTitle> getEndShipping();

	public static String getOrderEnd_ACTIVITY = "終了";

	public List<OrderTitle> getOrderEnd();

	public static String getOrderCancelEnd_ACTIVITY = "キャンセル終了";

	public List<OrderTitle> getOrderCancelEnd();

	public static String order_ACTIVITY = "注文";

	public void order(OrderTitle dto);

	public static String endShipping_ACTIVITY = "出荷作業中";

	public void endShipping(long orderId);

	public static String endBill_ACTIVITY = "出荷終了";

	public void endBill(long orderId);

	public static String cancel_ACTIVITY = "出荷作業中,出荷終了";

	public static String cancel_ACTION = "cancel";

	public static String cancel_RESULT = "#cancelStatus";

	public String cancel(long orderId);
}
