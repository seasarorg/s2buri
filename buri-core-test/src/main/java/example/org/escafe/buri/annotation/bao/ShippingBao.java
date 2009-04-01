/*
 * 作成日: 2006/01/03
 *
 */
package example.org.escafe.buri.annotation.bao;

import java.util.List;

import org.escafe.buri.annotation.Buri;
import org.escafe.buri.annotation.BuriAction;
import org.escafe.buri.annotation.BuriActivity;
import org.escafe.buri.annotation.BuriConversionRule;
import org.escafe.buri.annotation.BuriConverter;

import example.org.escafe.buri.entity.OrderTitle;
import example.org.escafe.buri.entity.Shipping;

@Buri(process = "注文管理.出荷", dtoClass = Shipping.class)
@BuriConverter( {
    @BuriConversionRule(convertClass = Long.class, ognl = "shippingService.convertOrNew(#data)"),
    @BuriConversionRule(convertClass = OrderTitle.class, ognl = "shippingService.convertOrNew(#data)") })
public interface ShippingBao {
	@BuriActivity("商品待ち")
	public List<OrderTitle> getNowWaiting();

	@BuriActivity("出荷済み")
	public List<OrderTitle> getEndShipping();

	@BuriActivity("キャンセル済み")
	public List<OrderTitle> getShippingCancel();

	@BuriActivity("出荷依頼")
	public void shipping(OrderTitle dto);

	@BuriActivity("商品待ち")
	public void checkEndShipping(Long shippingId);

	@BuriActivity("商品待ち")
	@BuriAction("cancel")
	public void cancel(OrderTitle dto);
}
