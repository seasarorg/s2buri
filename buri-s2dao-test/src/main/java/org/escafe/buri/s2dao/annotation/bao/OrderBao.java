/*
 * 作成日: 2006/01/03
 *
 */
package org.escafe.buri.s2dao.annotation.bao;

import java.util.List;

import org.escafe.buri.annotation.Buri;
import org.escafe.buri.annotation.BuriAction;
import org.escafe.buri.annotation.BuriActivity;
import org.escafe.buri.annotation.BuriConversionRule;
import org.escafe.buri.annotation.BuriConverter;
import org.escafe.buri.annotation.BuriResult;

import example.org.escafe.buri.dto.OrderInfoDto;

@Buri(process = "注文管理.注文", dtoClass = OrderInfoDto.class)
@BuriConverter( { @BuriConversionRule(convertClass = Long.class, ognl = "orderInfoDao.getOrderInfo(#data)") })
public interface OrderBao {
	@BuriActivity("出荷作業中")
	public List<OrderInfoDto> getUnderWork();

	@BuriActivity("出荷終了")
	public List<OrderInfoDto> getEndShipping();

	@BuriActivity("終了")
	public List<OrderInfoDto> getOrderEnd();

	@BuriActivity("キャンセル終了")
	public List<OrderInfoDto> getOrderCancelEnd();

	@BuriActivity("注文")
	public void order(OrderInfoDto dto);

	@BuriActivity("出荷作業中")
	public void endShipping(Long orderId);

	@BuriActivity("出荷終了")
	public void endBill(Long orderId);

	@BuriActivity( { "出荷作業中", "出荷終了" })
	@BuriAction("cancel")
	@BuriResult("#cancelStatus")
	public String cancel(Long orderId);
}
