package example.org.escafe.buri.dto;

import java.util.Date;

public class BillDto {
	public static final String TABLE = "BILL";

	public static final String billId_ID = "sequence, sequenceName=BILL_SEQ";

	private Long billId;

	private Date billDate;

	private Long shippingId;

	private Long orderTitleId;

	private Long customerId;

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public Long getShippingId() {
		return shippingId;
	}

	public void setShippingId(Long shippingId) {
		this.shippingId = shippingId;
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
		buff.append("/billId=").append(billId);
		buff.append("/billDate=").append(billDate);
		buff.append("/shippingId=").append(shippingId);
		buff.append("/orderTitleId=").append(orderTitleId);
		buff.append("/customerId=").append(customerId);
		buff.append("]");
		return buff.toString();
	}
}
