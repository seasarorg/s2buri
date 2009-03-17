/*
 * 作成日: 2006/01/03
 *
 */
package org.escafe.buri.bao;

import java.util.List;

import example.org.escafe.buri.entity.Bill;
import example.org.escafe.buri.entity.Shipping;

public interface BillBao {
	public static Class<?> TARGETDTO = Bill.class;

	public static String PROCESS = "注文管理.請求";

	public static String getBillWaiting_ACTIVITY = "請求作業";

	public List<Bill> getBillWaiting();

	public static String getReBill_ACTIVITY = "再請求準備";

	public List<Bill> getReBill();

	public static String getEndBill_ACTIVITY = "請求終了";

	public List<Bill> getEndBill();

	public static String enterBill_ACTIVITY = "請求依頼";

	public void enterBill(Shipping dto);

	public static String bill_ACTIVITY = "請求作業";

	public void bill(Bill dto);

	public static String checkPayment_ACTIVITY = "再請求準備";

	public void checkPayment(Bill dto);
}
