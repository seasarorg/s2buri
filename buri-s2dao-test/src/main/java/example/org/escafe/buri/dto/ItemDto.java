package example.org.escafe.buri.dto;

public class ItemDto {
	public static final String TABLE = "ITEM";

	public static final String itemId_ID = "sequence, sequenceName=ITEM_SEQ";

	private Long itemId;

	private String itemName = "";

	private Long price;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/itemId=").append(itemId);
		buff.append("/itemName=").append(itemName);
		buff.append("/price=").append(price);
		buff.append("]");
		return buff.toString();
	}
}
