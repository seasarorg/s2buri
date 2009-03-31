package example.org.escafe.buri.dao;

import java.util.List;

import example.org.escafe.buri.dto.ShippingItemDto;
import example.org.escafe.buri.dto.ShippingItemFindDto;

public interface ShippingItemDao {
	public Class<?> BEAN = ShippingItemDto.class;

	public List<ShippingItemDto> getAllShippingItem();

	public String getShippingItem_QUERY = "SHIPPING_ITEM_ID = ?";

	public ShippingItemDto getShippingItem(Long shippingItemId);

	public String getShippingItemByIds_ARGS = "shippingItemIds";

	public String getShippingItemByIds_QUERY =
	    "SHIPPING_ITEM_ID in /*shippingItemIds*/(1)";

	public List<ShippingItemDto> getShippingItemByIds(List<Long> shippingItemIds);

	public String find_ARGS = "dto,paths";

	public List<ShippingItemDto> find(ShippingItemFindDto dto,
	        List<String> paths);

	public String findAndUser_ARGS = "dto,paths,userIds";

	public List<ShippingItemDto> findAndUser(ShippingItemFindDto dto,
	        List<String> paths, List<Long> userIds);

	public String getShippingItemByShippingId_ARGS = "shippingId";

	public String getShippingItemByShippingId_QUERY =
	    "SHIPPING_ID = /*shippingId*/0";

	public List<ShippingItemDto> getShippingItemByShippingId(Long shippingId);

	public void insert(ShippingItemDto entity);

	public void update(ShippingItemDto entity);

	public void delete(ShippingItemDto entity);
}
