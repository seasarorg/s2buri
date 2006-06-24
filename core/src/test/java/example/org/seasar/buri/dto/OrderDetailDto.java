package example.org.seasar.buri.dto;


public class OrderDetailDto {
	public static final String TABLE = "OrderDetail";

	public static final String orderDetailID_ID = "sequence, sequenceName=orderDetailID";
	private long orderDetailID;
	private int orderCount;
	private long itemID;
	private long orderTitleID;
	
	public long getOrderDetailID() {
		return orderDetailID;
	}

	public void setOrderDetailID(long orderDetailID) {
		this.orderDetailID = orderDetailID;
	}

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	public long getItemID() {
		return itemID;
	}

	public void setItemID(long itemID) {
		this.itemID = itemID;
	}

	public long getOrderTitleID() {
		return orderTitleID;
	}

	public void setOrderTitleID(long orderTitleID) {
		this.orderTitleID = orderTitleID;
	}

	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/orderDetailID=").append(orderDetailID);
		buff.append("/orderCount=").append(orderCount);
		buff.append("/itemID=").append(itemID);
		buff.append("/orderTitleID=").append(orderTitleID);
		buff.append("]");
		return buff.toString();
	}
	
}
