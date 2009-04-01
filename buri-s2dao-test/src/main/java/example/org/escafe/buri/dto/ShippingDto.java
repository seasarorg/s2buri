package example.org.escafe.buri.dto;

import java.util.Date;

public class ShippingDto {
	public static final String TABLE = "SHIPPING";

	public static final String shippingId_ID =
	    "sequence, sequenceName=SHIPPING_SEQ";

	private Long shippingId;

	private Date shippingDate = new Date(8070, 11, 31, 23, 59, 59);

	private Long orderTitleId;

	private Long customerId;

	public Long getShippingId() {
		return shippingId;
	}

	public void setShippingId(Long shippingId) {
		this.shippingId = shippingId;
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public Long getOrderTitleId() {
		return orderTitleId;
	}

	public void setOrderTitleId(Long orderTitleId) {
		this.orderTitleId = orderTitleId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/shippingId=").append(shippingId);
		buff.append("/shippingDate=").append(shippingDate);
		buff.append("/orderTitleId=").append(orderTitleId);
		buff.append("/customerId=").append(customerId);
		buff.append("]");
		return buff.toString();
	}
}
