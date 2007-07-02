package example.org.escafe.buri.web.item;

import java.io.Serializable;

public class ItemPageListDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean checks;

	private long itemId;

	private String itemName;

	private long price;

	public boolean isChecks() {
		return this.checks;
	}

	public boolean getChecks() {
		return this.checks;
	}

	public void setChecks(boolean checks) {
		this.checks = checks;
	}

	public long getItemId() {
		return this.itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public long getPrice() {
		return this.price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

}
