package example.org.escafe.buri.dto;

public class OrderDetailDto {
	public static final String TABLE = "ORDER_DETAIL";

	public static final String orderDetailId_ID = "sequence, sequenceName=ORDER_DETAIL_SEQ";
	private Long orderDetailId;
	private Integer orderCount;
	private Long itemId;
	private Long orderTitleId;

	public long getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(long orderDetailID) {
		this.orderDetailId = orderDetailID;
	}

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemID(Long itemId) {
		this.itemId = itemId;
	}

	public long getOrderTitleId() {
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
