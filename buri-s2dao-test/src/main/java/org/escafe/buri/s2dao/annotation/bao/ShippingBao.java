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

import example.org.escafe.buri.dto.OrderInfoDto;
import example.org.escafe.buri.dto.ShippingSetDto;

@Buri(process = "注文管理.出荷", dtoClass = ShippingSetDto.class)
@BuriConverter( {
    @BuriConversionRule(convertClass = Long.class, ognl = "shippingSetDao.getShippingSetDto(#data)"),
    @BuriConversionRule(convertClass = OrderInfoDto.class, ognl = "shippingSetDao.getDtoByOrderTitleId(#data)") })
public interface ShippingBao {
	@BuriActivity("商品待ち")
	public List<ShippingSetDto> getNowWaiting();

	@BuriActivity("出荷済み")
	public List<ShippingSetDto> getEndShipping();

	@BuriActivity("キャンセル済み")
	public List<ShippingSetDto> getShippingCancel();

	@BuriActivity("出荷依頼")
	public void shipping(OrderInfoDto dto);

	@BuriActivity("商品待ち")
	public void checkEndShipping(Long shippingId);

	@BuriActivity("商品待ち")
	@BuriAction("cancel")
	public void cancel(OrderInfoDto dto);
}
