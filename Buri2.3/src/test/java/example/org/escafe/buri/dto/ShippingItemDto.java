package example.org.escafe.buri.dto;


public class ShippingItemDto {
	public static final String TABLE = "ShippingItem";

	public static final String shippingItemID_ID = "sequence, sequenceName=shippingItemID";
	private long shippingItemID;
	private long orderDetailID;
	private long shippingID;
	
	public long getShippingItemID() {
		return shippingItemID;
	}

	public void setShippingItemID(long shippingItemID) {
		this.shippingItemID = shippingItemID;
	}

	public long getOrderDetailID() {
		return orderDetailID;
	}

	public void setOrderDetailID(long orderDetailID) {
		this.orderDetailID = orderDetailID;
	}

	public long getShippingID() {
		return shippingID;
	}

	public void setShippingID(long shippingID) {
		this.shippingID = shippingID;
	}

	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/shippingItemID=").append(shippingItemID);
		buff.append("/orderDetailID=").append(orderDetailID);
		buff.append("/shippingID=").append(shippingID);
		buff.append("]");
		return buff.toString();
	}
	
}
