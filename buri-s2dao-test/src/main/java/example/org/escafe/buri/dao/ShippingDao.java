package example.org.escafe.buri.dao;

import java.util.List;

import example.org.escafe.buri.dto.ShippingDto;
import example.org.escafe.buri.dto.ShippingFindDto;

public interface ShippingDao {
	public Class<?> BEAN = ShippingDto.class;

	public List<ShippingDto> getAllShipping();

	public String getShipping_QUERY = "SHIPPING_ID = ?";

	public ShippingDto getShipping(Long shippingId);

	public String getShippingByIds_ARGS = "shippingIds";

	public String getShippingByIds_QUERY = "SHIPPING_ID IN/*shippingIds*/(1)";

	public List<ShippingDto> getShippingByIds(List<Long> shippingIds);

	public String find_ARGS = "dto,paths";

	public List<ShippingDto> find(ShippingFindDto dto, List<String> paths);

	public String findAndUser_ARGS = "dto,paths,userIds";

	public List<ShippingDto> findAndUser(ShippingFindDto dto,
	        List<String> paths, List<Long> userIds);

	public String getShippingByOrderTitleId_ARGS = "orderTitleId";

	public String getShippingByOrderTitleId_QUERY =
	    "ORDER_TITLE_ID = /*orderTitleId*/0";

	public ShippingDto getShippingByOrderTitleId(Long orderTitleId);

	public void insert(ShippingDto entity);

	public void update(ShippingDto entity);

	public void delete(ShippingDto entity);
}
