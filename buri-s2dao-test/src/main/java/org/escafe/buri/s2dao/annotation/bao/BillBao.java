/*
 * 作成日: 2006/01/03
 *
 */
package org.escafe.buri.s2dao.annotation.bao;

import java.util.List;

import org.escafe.buri.annotation.Buri;
import org.escafe.buri.annotation.BuriActivity;

import example.org.escafe.buri.dto.BillDto;
import example.org.escafe.buri.dto.ShippingSetDto;

@Buri(process = "注文管理.請求", dtoClass = BillDto.class)
public interface BillBao {
	@BuriActivity("請求作業")
	public List<BillDto> getBillWaiting();

	@BuriActivity("再請求準備")
	public List<BillDto> getReBill();

	@BuriActivity("請求終了")
	public List<BillDto> getEndBill();

	@BuriActivity("請求依頼")
	public void enterBill(ShippingSetDto dto);

	@BuriActivity("請求作業")
	public void bill(BillDto dto);

	@BuriActivity("再請求準備")
	public void checkPayment(BillDto dto);
}
