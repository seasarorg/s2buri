package example.org.escafe.buri.dto;

import java.util.Date;

public class OrderTitleDto {
	public static final String TABLE = "ORDER_TITLE";

	public static final String orderTitleId_ID =
	    "sequence, sequenceName=ORDER_TITLE_SEQ";

	private Long orderTitleId;

	private Date orderDate;

	private Long customerId;

	private Integer status;

	public Long getOrderTitleId() {
		return orderTitleId;
	}

	public void setOrderTitleId(Long orderTitleId) {
		this.orderTitleId = orderTitleId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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
		buff.append("/orderTitleId=").append(orderTitleId);
		buff.append("/orderDate=").append(orderDate);
		buff.append("/customerId=").append(customerId);
		buff.append("/status=").append(status);
		buff.append("]");
		return buff.toString();
	}
}
