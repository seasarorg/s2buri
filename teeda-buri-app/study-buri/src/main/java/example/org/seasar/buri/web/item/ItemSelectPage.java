package example.org.seasar.buri.web.item;

import java.util.ArrayList;
import java.util.List;

import example.org.seasar.buri.dao.ItemDao;
import example.org.seasar.buri.dto.ItemDto;
import example.org.seasar.buri.dto.OrderDataDto;
import example.org.seasar.buri.web.order.OrderPage;

public class ItemSelectPage {

	private OrderDataDto orderDataDto;

	private boolean checks;

	private long itemId;

	private int itemIndex;

	private ItemPageListDto[] itemItems;

	private String itemName;

	private long price;

	/**
	 * 
	 */
	private ItemDao itemDao;

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

	public int getItemIndex() {
		return this.itemIndex;
	}

	public void setItemIndex(int itemIndex) {
		this.itemIndex = itemIndex;
	}

	public ItemPageListDto[] getItemItems() {
		return this.itemItems;
	}

	public void setItemItems(ItemPageListDto[] itemItems) {
		this.itemItems = itemItems;
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

	public Class doItemSelect() {
		System.out.println("配列:=" + itemItems.length);
		List itemIds = new ArrayList();
		for (int i = 0; i < itemItems.length; i++) {
			System.out.println(i + "回目:=" + itemItems[i].isChecks());
			if (itemItems[i].isChecks()) {
				itemIds.add(itemItems[i].getItemId());
			}
		}
		orderDataDto.setItemIds(itemIds);
		return OrderPage.class;
	}

	public String initialize() {
		return null;
	}

	/**
	 * @return the itemDao
	 */
	public ItemDao getItemDao() {
		return this.itemDao;
	}

	/**
	 * @param itemDao
	 *            the itemDao to set
	 */
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public String prerender() {
		if (itemItems == null) {
			List results = getItemDao().getAllItem();
			itemItems = new ItemPageListDto[results.size()];
			for (int i = 0; i < results.size(); i++) {
				ItemDto dto = (ItemDto) results.get(i);
				ItemPageListDto listDto = new ItemPageListDto();
				listDto.setChecks(false);
				listDto.setItemId(dto.getItemID());
				listDto.setItemName(dto.getItemName());
				listDto.setPrice(dto.getPrice());
				this.itemItems[i] = listDto;
			}
		}
		return null;
	}

	public OrderDataDto getOrderDataDto() {
		return this.orderDataDto;
	}

	public void setOrderDataDto(OrderDataDto orderDataDto) {
		this.orderDataDto = orderDataDto;
	}

}
