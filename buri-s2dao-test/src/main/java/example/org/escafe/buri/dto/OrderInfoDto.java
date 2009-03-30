/*
 * 作成日: 2005/11/29
 *
 */
package example.org.escafe.buri.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderInfoDto extends OrderTitleDto {
	private List<OrderDetailDto> orderDetailList =
	    new ArrayList<OrderDetailDto>();

	public List<OrderDetailDto> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetailDto> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer("{");
		buff.append(super.toString());
		buff.append("/orderDetailList=").append(orderDetailList);
		buff.append("}");
		return buff.toString();
	}
}
