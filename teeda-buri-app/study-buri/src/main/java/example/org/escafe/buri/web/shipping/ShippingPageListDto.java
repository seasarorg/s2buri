package example.org.escafe.buri.web.shipping;

import java.util.Date;

public class ShippingPageListDto {

	private long shippingId;

	private Date shippingDate;

	private String customerName;

	private long orderTitleId;

	private Date orderDate;

	private String itemName;

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public long getOrderTitleId() {
		return this.orderTitleId;
	}

	public void setOrderTitleId(long orderTitleId) {
		this.orderTitleId = orderTitleId;
	}

	public Date getShippingDate() {
		return this.shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public long getShippingId() {
		return this.shippingId;
	}

	public void setShippingId(long shippingId) {
		this.shippingId = shippingId;
	}
}
