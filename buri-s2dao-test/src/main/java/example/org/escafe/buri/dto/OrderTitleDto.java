package example.org.escafe.buri.dto;

import java.util.Date;

public class OrderTitleDto {
	public static final String TABLE = "OrderTitle";

	public static final String orderTitleID_ID = "sequence, sequenceName=orderTitleID";
	private long orderTitleID;
	private Date orderDate;
	private long customerID;
	private Integer status;
	
	public long getOrderTitleID() {
		return orderTitleID;
	}

	public void setOrderTitleID(long orderTitleID) {
		this.orderTitleID = orderTitleID;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
    public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/orderTitleID=").append(orderTitleID);
		buff.append("/orderDate=").append(orderDate);
		buff.append("/customerID=").append(customerID);
		buff.append("/status=").append(status);
		buff.append("]");
		return buff.toString();
	}
	
}
