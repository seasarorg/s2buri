package example.org.escafe.buri.dto;

public class ShippingItemDto {
	public static final String TABLE = "SHIPPING_ITEM";

	public static final String shippingItemId_ID =
	    "sequence, sequenceName=SHIPPING_ITEM_SEQ";

	private Long shippingItemId;

	private Long orderDetailId;

	private Long shippingId;

	public Long getShippingItemId() {
		return shippingItemId;
	}

	public void setShippingItemId(Long shippingItemId) {
		this.shippingItemId = shippingItemId;
	}

	public Long getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public Long getShippingId() {
		return shippingId;
	}

	public void setShippingId(Long shippingId) {
		this.shippingId = shippingId;
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/shippingItemId=").append(shippingItemId);
		buff.append("/orderDetailId=").append(orderDetailId);
		buff.append("/shippingId=").append(shippingId);
		buff.append("]");
		return buff.toString();
	}
}
