/*
 * 作成日: 2006/01/03
 *
 */
package org.seasar.buri.bao;

import java.util.List;

import org.seasar.buri.annotation.BuriAction;
import org.seasar.buri.annotation.BuriActionConvert;
import org.seasar.buri.annotation.BuriActivity;
import org.seasar.buri.annotation.BuriProcess;
import org.seasar.buri.annotation.BuriResult;
import org.seasar.buri.annotation.BuriTargetDto;

import example.org.seasar.buri.dto.OrderInfoDto;

@BuriProcess(name = "注文管理.注文")
@BuriTargetDto(dtoClass = OrderInfoDto.class)
// @BuriConverter(converter = { @BuriConvert(convertClass = Long.class, ognl =
// "OrderInfoDao.getOrderInfo(#data)") })
public interface OrderBao {
	// public static Class TARGETDTO = OrderInfoDto.class;
	// public static String PROCESS = "注文管理.注文";

	// public static BuriConvert CONVERTER[] = new BuriConvert[] { new
	// BuriConvert(
	// Long.class, "OrderInfoDao.getOrderInfo(#data)") };

	// public static String getUnderWork_ACTIVITY = "出荷作業中";

	@BuriActivity(name = "出荷作業中")
	public List getUnderWork();

	// public static String getEndShipping_ACTIVITY = "出荷終了";

	@BuriActivity(name = "出荷終了")
	public List getEndShipping();

	// public static String getOrderEnd_ACTIVITY = "終了";

	@BuriActivity(name = "終了")
	public List getOrderEnd();

	// public static String getOrderCancelEnd_ACTIVITY = "キャンセル終了";

	@BuriActivity(name = "キャンセル終了")
	public List getOrderCancelEnd();

	// public static String order_ACTIVITY = "注文";

	@BuriActivity(name = "注文")
	public void order(OrderInfoDto dto);

	// public static String endShipping_ACTIVITY = "出荷作業中";

	@BuriActivity(name = "出荷作業中")
	@BuriActionConvert(ognl = "OrderInfoDao.getOrderInfo(#data)")
	public void endShipping(long orderID);

	// public static String endBill_ACTIVITY = "出荷終了";

	@BuriActivity(name = "出荷終了")
	@BuriActionConvert(ognl = "OrderInfoDao.getOrderInfo(#data)")
	public void endBill(long orderID);

	// public static String cancel_ACTIVITY = "出荷作業中,出荷終了";
	// public static String cancel_ACTION = "cancel";
	// public static String cancel_RESULT = "#cancelStatus";

	@BuriActivity(name = "出荷作業中,出荷終了")
	@BuriAction(name = "cancel")
	@BuriResult(name = "#cancelStatus")
	@BuriActionConvert(ognl = "OrderInfoDao.getOrderInfo(#data)")
	public String cancel(long orderID);

}
