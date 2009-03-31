package example.org.escafe.buri.dao;

import java.util.List;

import example.org.escafe.buri.dto.ItemDto;
import example.org.escafe.buri.dto.ItemFindDto;

public interface ItemDao {
	public Class<?> BEAN = ItemDto.class;

	public List<ItemDto> getAllItem();

	public String getItem_QUERY = "ITEM_ID = ?";

	public ItemDto getItem(Long itemId);

	public String getItemByIds_ARGS = "itemIds";

	public String getItemByIds_QUERY = "ITEM_ID IN/*itemIds*/(1)";

	public List<ItemDto> getItemByIds(List<Long> itemIds);

	public String find_ARGS = "dto,paths";

	public List<ItemDto> find(ItemFindDto dto, List<String> paths);

	public String findAndUser_ARGS = "dto,paths,userIds";

	public List<ItemDto> findAndUser(ItemFindDto dto, List<String> paths,
	        List<Long> userIds);

	public void insert(ItemDto entity);

	public void update(ItemDto entity);

	public void delete(ItemDto entity);
}
