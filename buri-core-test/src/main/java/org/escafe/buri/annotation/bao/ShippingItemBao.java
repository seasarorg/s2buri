/*
 * 作成日: 2006/01/03
 *
 */
package org.escafe.buri.annotation.bao;

import java.util.List;

import example.org.escafe.buri.entity.ShippingItem;

public interface ShippingItemBao {
	public static String PROCESS = "注文管理.出荷詳細";

	public static Class<?> TARGETDTO = ShippingItem.class;

	public static String getItemWaiting_ACTIVITY = "商品準備中";

	public List<ShippingItem> getItemWaiting();

	public static String getEndShipping_ACTIVITY = "商品準備完了";

	public List<ShippingItem> getEndShipping();

	public static String getCancel_ACTIVITY = "cancel済み";

	public List<ShippingItem> getCancel();

	public static String getEndShippingCount_ACTIVITY = "商品準備完了";

	public static String getEndShippingCount_ARGS = "datas";

	public Long getEndShippingCount(List<ShippingItem> datas);

	public static String startShipping_ACTIVITY = "商品準備開始";

	public void startShipping(ShippingItem dto);

	public void startShipping(List<ShippingItem> dtos);

	public static String endShipping_ACTIVITY = "商品準備中";

	public void endShipping(ShippingItem dto);

	public static String cancel_ACTIVITY = "商品準備中";

	public static String cancel_ACTION = "cancel";

	public void cancel(ShippingItem dto);

	public void cancel(List<ShippingItem> dtos);
}
