package example.org.escafe.buri.dto;

import java.util.Date;

public class BillDto {
	public static final String TABLE = "BILL";

	public static final String billId_ID = "sequence, sequenceName=BILL_SEQ";
	private long billId;
	private Date billDate;
	private long shippingId;
	private long orderTitleId;
	private long customerId;

	public long getBillId() {
		return billId;
	}

	public void setBillId(long billId) {
		this.billId = billId;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public long getShippingId() {
		return shippingId;
	}

	public void setShippingId(long shippingId) {
		this.shippingId = shippingId;
	}

	public long getOrderTitleId() {
		return orderTitleId;
	}

	public void setOrderTitleId(long orderTitleId) {
		this.orderTitleId = orderTitleId;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/billId=").append(billId);
		buff.append("/billDate=").append(billDate);
		buff.append("/shippingId=").append(shippingId);
		buff.append("/orderTitleId=").append(orderTitleId);
		buff.append("/customerId=").append(customerId);
		buff.append("]");
		return buff.toString();
	}

}
