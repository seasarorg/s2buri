/*
 * 作成日: 2006/01/03
 *
 */
package org.seasar.buri.bao;

import java.util.List;

import org.seasar.buri.annotation.BuriAction;
import org.seasar.buri.annotation.BuriActivity;
import org.seasar.buri.annotation.BuriArgs;
import org.seasar.buri.annotation.BuriProcess;
import org.seasar.buri.annotation.BuriTargetDto;

import example.org.seasar.buri.dto.ShippingItemDto;

@BuriProcess(name = "注文管理.出荷詳細")
@BuriTargetDto(dtoClass = ShippingItemDto.class)
public interface ShippingItemBao {
	// public static String PROCESS = "注文管理.出荷詳細";
	// public static Class TARGETDTO = ShippingItemDto.class;

	// public static String getItemWaiting_ACTIVITY = "商品準備中";

	@BuriActivity(name = "商品準備中")
	public List getItemWaiting();

	// public static String getEndShipping_ACTIVITY = "商品準備完了";

	@BuriActivity(name = "商品準備完了")
	public List getEndShipping();

	public static String getCancel_ACTIVITY = "cancel済み";

	@BuriActivity(name = "cancel済み")
	public List getCancel();

	// public static String getEndShippingCount_ACTIVITY = "商品準備完了";

	// public static String getEndShippingCount_ARGS = "datas";

	@BuriActivity(name = "商品準備完了")
	@BuriArgs(name = "datas")
	public long getEndShippingCount(List datas);

	// public static String startShipping_ACTIVITY = "商品準備開始";

	@BuriActivity(name = "商品準備開始")
	public void startShipping(ShippingItemDto dto);

	@BuriActivity(name = "商品準備開始")
	public void startShipping(List dtos);

	// public static String endShipping_ACTIVITY = "商品準備中";

	@BuriActivity(name = "商品準備中")
	public void endShipping(ShippingItemDto dto);

	// public static String cancel_ACTIVITY = "商品準備中";

	// public static String cancel_ACTION = "cancel";

	@BuriActivity(name = "商品準備中")
	@BuriAction(name = "cancel")
	public void cancel(ShippingItemDto dto);

	@BuriActivity(name = "商品準備中")
	@BuriAction(name = "cancel")
	public void cancel(List dtos);

}
