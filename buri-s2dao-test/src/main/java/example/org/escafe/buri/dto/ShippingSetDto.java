/*
 * 作成日: 2005/11/29
 *
 */
package example.org.escafe.buri.dto;

import java.util.ArrayList;
import java.util.List;

public class ShippingSetDto extends ShippingDto {
	private List<ShippingItemDto> shippingItemList =
	    new ArrayList<ShippingItemDto>();

	public List<ShippingItemDto> getShippingItemList() {
		return shippingItemList;
	}

	public void setShippingItemList(List<ShippingItemDto> shippingItemList) {
		this.shippingItemList = shippingItemList;
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer("{");
		buff.append(super.toString());
		buff.append("/items=").append(shippingItemList);
		buff.append("}");
		return buff.toString();
	}
}
