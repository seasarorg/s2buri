/*
 * 作成日: 2005/11/29
 *
 */
package example.org.escafe.buri.dto;

import java.util.ArrayList;
import java.util.List;

public class ShippingSetDto extends ShippingDto {
	private List<ShippingItemDto> items = new ArrayList<ShippingItemDto>();

	public List<ShippingItemDto> getItems() {
		return items;
	}

	public void setItems(List<ShippingItemDto> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer("{");
		buff.append(super.toString());
		buff.append("/items=").append(items);
		buff.append("}");
		return buff.toString();
	}
}
