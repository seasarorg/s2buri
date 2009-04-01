package example.org.escafe.buri.dao;

import java.util.List;

import example.org.escafe.buri.dto.OrderTitleDto;
import example.org.escafe.buri.dto.OrderTitleFindDto;

public interface OrderTitleDao {
	public Class<?> BEAN = OrderTitleDto.class;

	public List<OrderTitleDto> getAllOrderTitle();

	public String getOrderTitle_QUERY = "ORDER_TITLE_ID = ?";

	public OrderTitleDto getOrderTitle(Long orderTitleId);

	public String getOrderTitleByIds_ARGS = "orderTitleIds";

	public String getOrderTitleByIds_QUERY =
	    "ORDER_TITLE_ID IN/*orderTitleIds*/(1)";

	public List<OrderTitleDto> getOrderTitleByIds(List<Long> orderTitleIds);

	public String find_ARGS = "dto,paths";

	public List<OrderTitleDto> find(OrderTitleFindDto dto, List<String> paths);

	public String findAndUser_ARGS = "dto,paths,userIds";

	public List<OrderTitleDto> findAndUser(OrderTitleFindDto dto,
	        List<String> paths, List<Long> userIds);

	public void insert(OrderTitleDto entity);

	public void update(OrderTitleDto entity);

	public void delete(OrderTitleDto entity);
}
