package aconv.app.dao;

import java.util.List;

import aconv.app.dto.ItemDto;
import aconv.app.dto.ItemFindDto;

public interface ItemDao {
	public Class BEAN = ItemDto.class;

	public List getAllItem();

	public String getItem_QUERY = "itemID = ?";

	public ItemDto getItem(long itemID);

	public String getItemByIds_ARGS = "itemIDs";

	public String getItemByIds_QUERY = "itemID in /*itemIDs*/(1)";

	public List getItemByIds(List itemIDs);

	public String find_ARGS = "dto,paths";

	public List find(ItemFindDto dto, List paths);

	public String findAndUser_ARGS = "dto,paths,userIDs";

	public List findAndUser(ItemFindDto dto, List paths, List userIDs);

	// public String soleMatch_ARGS = "dto";
	// public ItemDto soleMatch(ItemFindDto dto);

	public void insert(ItemDto dto);

	public void update(ItemDto dto);

	public void delete(ItemDto dto);
}
