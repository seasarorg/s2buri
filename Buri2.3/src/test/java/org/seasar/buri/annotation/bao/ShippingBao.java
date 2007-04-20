/*
 * 作成日: 2006/01/03
 *
 */
package org.seasar.buri.annotation.bao;

import java.util.List;

import org.seasar.buri.annotation.Buri;
import org.seasar.buri.annotation.BuriAction;
import org.seasar.buri.annotation.BuriActivity;
import org.seasar.buri.annotation.BuriConversionRule;
import org.seasar.buri.annotation.BuriConverter;

import example.org.seasar.buri.dto.OrderInfoDto;
import example.org.seasar.buri.dto.ShippingSetDto;

@Buri(process = "注文管理.出荷", dtoClass = ShippingSetDto.class)
@BuriConverter( {
		@BuriConversionRule(convertClass = Long.class, ognl = "ShippingSetDao.getShippingSetDto(#data)"),
		@BuriConversionRule(convertClass = OrderInfoDto.class, ognl = "ShippingSetDao.getDtoByOrderTitleID(#data)") })
public interface ShippingBao {

	@BuriActivity("商品待ち")
	public List getNowWaiting();

	@BuriActivity("出荷済み")
	public List getEndShipping();

	@BuriActivity("キャンセル済み")
	public List getShippingCancel();

	@BuriActivity("出荷依頼")
	public void shipping(OrderInfoDto dto);

	@BuriActivity("商品待ち")
	public void checkEdnShipping(long shippingID);

	@BuriActivity("商品待ち")
	@BuriAction("cancel")
	public void cancel(OrderInfoDto dto);

}
