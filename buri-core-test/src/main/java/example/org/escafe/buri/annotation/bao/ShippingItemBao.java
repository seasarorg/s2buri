/*
 * 作成日: 2006/01/03
 *
 */
package example.org.escafe.buri.annotation.bao;

import java.util.List;

import org.escafe.buri.annotation.Buri;
import org.escafe.buri.annotation.BuriAction;
import org.escafe.buri.annotation.BuriActivity;
import org.escafe.buri.annotation.BuriArgs;

import example.org.escafe.buri.entity.ShippingItem;

@Buri(process = "注文管理.出荷詳細", dtoClass = ShippingItem.class)
public interface ShippingItemBao {
	@BuriActivity("商品準備中")
	public List<ShippingItem> getItemWaiting();

	@BuriActivity("商品準備完了")
	public List<ShippingItem> getEndShipping();

	@BuriActivity("cancel済み")
	public List<ShippingItem> getCancel();

	@BuriActivity("商品準備完了")
	@BuriArgs("datas")
	public Long getEndShippingCount(List datas);

	@BuriActivity("商品準備開始")
	public void startShipping(ShippingItem dto);

	@BuriActivity("商品準備開始")
	public void startShipping(List dtos);

	@BuriActivity("商品準備中")
	public void endShipping(ShippingItem dto);

	@BuriActivity("商品準備中")
	@BuriAction("cancel")
	public void cancel(ShippingItem dto);

	@BuriActivity("商品準備中")
	@BuriAction("cancel")
	public void cancel(List dtos);
}
