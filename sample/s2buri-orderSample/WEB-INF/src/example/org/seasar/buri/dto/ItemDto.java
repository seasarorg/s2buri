package example.org.seasar.buri.dto;


public class ItemDto {
	public static final String TABLE = "Item";

	public static final String itemID_ID = "sequence, sequenceName=itemID";
	private long itemID;
	private String itemName = "";
	private long price;
	
	public long getItemID() {
		return itemID;
	}

	public void setItemID(long itemID) {
		this.itemID = itemID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/itemID=").append(itemID);
		buff.append("/itemName=").append(itemName);
		buff.append("/price=").append(price);
		buff.append("]");
		return buff.toString();
	}
	
}
