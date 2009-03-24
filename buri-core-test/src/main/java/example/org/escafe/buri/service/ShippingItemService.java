package example.org.escafe.buri.service;

import static example.org.escafe.buri.names.ShippingItemNames.shippingId;
import static example.org.escafe.buri.names.ShippingItemNames.shippingItemId;
import static org.seasar.extension.jdbc.operation.Operations.eq;
import static org.seasar.extension.jdbc.operation.Operations.in;

import java.util.List;

import example.org.escafe.buri.entity.ShippingItem;

/**
 * {@link ShippingItem}のサービスクラスです。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public class ShippingItemService extends AbstractService<ShippingItem> {
	public ShippingItem getShippingItem(Long shippingItemId) {
		ShippingItem result = select().id(shippingItemId).getSingleResult();
		return result;
	}

	public List<ShippingItem> getShippingItemByIds(List<Long> shippingItemIds) {
		List<ShippingItem> result =
		    select()
		        .where(in(shippingItemId(), shippingItemIds))
		        .getResultList();
		return result;
	}

	public List<ShippingItem> getShippingItemByShippingId(Long shippingId) {
		List<ShippingItem> result =
		    select().where(eq(shippingId(), shippingId)).getResultList();
		return result;
	}
}
