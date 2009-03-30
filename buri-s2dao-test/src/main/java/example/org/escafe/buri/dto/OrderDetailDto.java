package example.org.escafe.buri.dto;

public class OrderDetailDto {
	public static final String TABLE = "ORDER_DETAIL";

	public static final String orderDetailId_ID =
	    "sequence, sequenceName=ORDER_DETAIL_SEQ";

	private Long orderDetailId;

	private Integer orderCount;

	private Long itemId;

	private Long orderTitleId;

	public Long getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Long orderDetailID) {
		this.orderDetailId = orderDetailID;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemID(Long itemId) {
		this.itemId = itemId;
	}

	public Long getOrderTitleId() {
		return orderTitleId;
	}

	public void setOrderTitleId(Long orderTitleId) {
		this.orderTitleId = orderTitleId;
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/orderDetailId=").append(orderDetailId);
		buff.append("/orderCount=").append(orderCount);
		buff.append("/itemID=").append(itemId);
		buff.append("/orderTitleID=").append(orderTitleId);
		buff.append("]");
		return buff.toString();
	}
}
