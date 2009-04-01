/*
 * 作成日: 2006/01/03
 *
 */
package example.org.escafe.buri.annotation.bao;

import java.util.List;

import org.escafe.buri.annotation.Buri;
import org.escafe.buri.annotation.BuriActivity;

import example.org.escafe.buri.entity.Bill;
import example.org.escafe.buri.entity.Shipping;

@Buri(process = "注文管理.請求", dtoClass = Bill.class)
public interface BillBao {
	@BuriActivity("請求作業")
	public List<Bill> getBillWaiting();

	@BuriActivity("再請求準備")
	public List<Bill> getReBill();

	@BuriActivity("請求終了")
	public List<Bill> getEndBill();

	@BuriActivity("請求依頼")
	public void enterBill(Shipping dto);

	@BuriActivity("請求作業")
	public void bill(Bill dto);

	@BuriActivity("再請求準備")
	public void checkPayment(Bill dto);
}
