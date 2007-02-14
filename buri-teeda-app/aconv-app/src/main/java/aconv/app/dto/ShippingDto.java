package aconv.app.dto;

import java.util.Date;

public class ShippingDto {
	public static final String TABLE = "Shipping";

	public static final String shippingID_ID = "sequence, sequenceName=shippingID";

	private long shippingID;

	private Date shippingDate = new Date(8070, 11, 31, 23, 59, 59);

	private long orderTitleID;

	private long customerID;

	public long getShippingID() {
		return shippingID;
	}

	public void setShippingID(long shippingID) {
		this.shippingID = shippingID;
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public long getOrderTitleID() {
		return orderTitleID;
	}

	public void setOrderTitleID(long orderTitleID) {
		this.orderTitleID = orderTitleID;
	}

	public long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}

	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/shippingID=").append(shippingID);
		buff.append("/shippingDate=").append(shippingDate);
		buff.append("/orderTitleID=").append(orderTitleID);
		buff.append("/customerID=").append(customerID);
		buff.append("]");
		return buff.toString();
	}

}
