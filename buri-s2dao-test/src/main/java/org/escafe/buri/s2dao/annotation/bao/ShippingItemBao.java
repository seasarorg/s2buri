/*
 * 作成日: 2006/01/03
 *
 */
package org.escafe.buri.s2dao.annotation.bao;

import java.util.List;

import org.escafe.buri.annotation.Buri;
import org.escafe.buri.annotation.BuriAction;
import org.escafe.buri.annotation.BuriActivity;
import org.escafe.buri.annotation.BuriArgs;

import example.org.escafe.buri.dto.ShippingItemDto;

@Buri(process = "注文管理.出荷詳細", dtoClass = ShippingItemDto.class)
public interface ShippingItemBao {
	@BuriActivity("商品準備中")
	public List<ShippingItemDto> getItemWaiting();

	@BuriActivity("商品準備完了")
	public List<ShippingItemDto> getEndShipping();

	@BuriActivity("cancel済み")
	public List<ShippingItemDto> getCancel();

	@BuriActivity("商品準備完了")
	@BuriArgs("datas")
	public Long getEndShippingCount(List datas);

	@BuriActivity("商品準備開始")
	public void startShipping(ShippingItemDto dto);

	@BuriActivity("商品準備開始")
	public void startShipping(List<ShippingItemDto> dtos);

	@BuriActivity("商品準備中")
	public void endShipping(ShippingItemDto dto);

	@BuriActivity("商品準備中")
	@BuriAction("cancel")
	public void cancel(ShippingItemDto dto);

	@BuriActivity("商品準備中")
	@BuriAction("cancel")
	public void cancel(List<ShippingItemDto> dtos);
}
