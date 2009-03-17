/*
 * 作成日: 2006/01/03
 *
 */
package org.escafe.buri.annotation.bao;

import java.util.List;

import org.escafe.buri.bao.BuriConvert;

import example.org.escafe.buri.entity.OrderTitle;
import example.org.escafe.buri.entity.Shipping;

public interface ShippingBao {
	public static String PROCESS = "注文管理.出荷";

	public static Class<?> TARGETDTO = Shipping.class;

	public static BuriConvert CONVERTER[] =
	    new BuriConvert[] {
	        new BuriConvert(Long.class, "shippingService.convertOrNew(#data)"),
	        new BuriConvert(
	            OrderTitle.class,
	            "shippingService.convertOrNew(#data)") };

	public static String getNowWaiting_ACTIVITY = "商品待ち";

	public List<Shipping> getNowWaiting();

	public static String getEndShipping_ACTIVITY = "出荷済み";

	public List<Shipping> getEndShipping();

	public static String getShippingCancel_ACTIVITY = "キャンセル済み";

	public List<Shipping> getShippingCancel();

	public static String shipping_ACTIVITY = "出荷依頼";

	public void shipping(OrderTitle dto);

	public static String checkEdnShipping_ACTIVITY = "商品待ち";

	public void checkEdnShipping(Long shippingId);

	public static String cancel_ACTIVITY = "商品待ち";

	public static String cancel_ACTION = "cancel";

	public void cancel(OrderTitle dto);
}
