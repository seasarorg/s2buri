/*
 * 作成日: 2006/01/03
 *
 */
package org.seasar.buri.annotation.bao;

import java.util.List;

import org.seasar.buri.annotation.Buri;
import org.seasar.buri.annotation.BuriAction;
import org.seasar.buri.annotation.BuriActivity;
import org.seasar.buri.annotation.BuriArgs;

import example.org.seasar.buri.dto.ShippingItemDto;

@Buri(process = "注文管理.出荷詳細", dtoClass = ShippingItemDto.class)
public interface ShippingItemBao {

	@BuriActivity("商品準備中")
	public List getItemWaiting();

	@BuriActivity("商品準備完了")
	public List getEndShipping();

	@BuriActivity("cancel済み")
	public List getCancel();

	@BuriActivity("商品準備完了")
	@BuriArgs("datas")
	public long getEndShippingCount(List datas);

	@BuriActivity("商品準備開始")
	public void startShipping(ShippingItemDto dto);

	@BuriActivity("商品準備開始")
	public void startShipping(List dtos);

	@BuriActivity("商品準備中")
	public void endShipping(ShippingItemDto dto);

	@BuriActivity("商品準備中")
	@BuriAction("cancel")
	public void cancel(ShippingItemDto dto);

	@BuriActivity("商品準備中")
	@BuriAction("cancel")
	public void cancel(List dtos);

}
