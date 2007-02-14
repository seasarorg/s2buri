package aconv.app.dao;

import java.util.List;

import aconv.app.dto.ShippingItemDto;
import aconv.app.dto.ShippingItemFindDto;

public interface ShippingItemDao {
	public Class BEAN = ShippingItemDto.class;

	public List getAllShippingItem();

	public String getShippingItem_QUERY = "shippingItemID = ?";

	public ShippingItemDto getShippingItem(long shippingItemID);

	public String getShippingItemByIds_ARGS = "shippingItemIDs";

	public String getShippingItemByIds_QUERY = "shippingItemID in /*shippingItemIDs*/(1)";

	public List getShippingItemByIds(List shippingItemIDs);

	public String find_ARGS = "dto,paths";

	public List find(ShippingItemFindDto dto, List paths);

	public String findAndUser_ARGS = "dto,paths,userIDs";

	public List findAndUser(ShippingItemFindDto dto, List paths, List userIDs);

	public String getShippingItemByShippingID_ARGS = "shippingID";

	public String getShippingItemByShippingID_QUERY = "shippingID = /*shippingID*/0";

	public List getShippingItemByShippingID(long shippingID);

	// public String soleMatch_ARGS = "dto";
	// public ShippingItemDto soleMatch(ShippingItemFindDto dto);

	public void insert(ShippingItemDto dto);

	public void update(ShippingItemDto dto);

	public void delete(ShippingItemDto dto);
}
