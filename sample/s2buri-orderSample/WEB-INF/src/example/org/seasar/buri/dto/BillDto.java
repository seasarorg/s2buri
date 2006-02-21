package example.org.seasar.buri.dto;

import java.util.Date;

public class BillDto {
	public static final String TABLE = "Bill";

	public static final String billID_ID = "sequence, sequenceName=billID";
	private long billID;
	private Date billDate;
	private long shippingID;
	private long orderTitleID;
	private long customerID;
	
	public long getBillID() {
		return billID;
	}

	public void setBillID(long billID) {
		this.billID = billID;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public long getShippingID() {
		return shippingID;
	}

	public void setShippingID(long shippingID) {
		this.shippingID = shippingID;
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
		buff.append("/billID=").append(billID);
		buff.append("/billDate=").append(billDate);
		buff.append("/shippingID=").append(shippingID);
		buff.append("/orderTitleID=").append(orderTitleID);
		buff.append("/customerID=").append(customerID);
		buff.append("]");
		return buff.toString();
	}
	
}
