package example.org.escafe.buri.dto;

import java.util.List;

public class OrderDataDto {

	private long customerId;

	private long itemId;

	private List itemIds;

	public long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public long getItemId() {
		return this.itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public List getItemIds() {
		return this.itemIds;
	}

	public void setItemIds(List itemIds) {
		this.itemIds = itemIds;
	}

	public void addItemIds(long itemId) {
		itemIds.add(itemId);
	}

}
