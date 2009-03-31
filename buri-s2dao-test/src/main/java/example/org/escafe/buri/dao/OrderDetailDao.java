package example.org.escafe.buri.dao;

import java.util.List;

import example.org.escafe.buri.dto.OrderDetailDto;
import example.org.escafe.buri.dto.OrderDetailFindDto;

public interface OrderDetailDao {
	public Class<?> BEAN = OrderDetailDto.class;

	public List<OrderDetailDto> getAllOrderDetail();

	public String getOrderDetail_QUERY = "ORDER_DETAIL_ID = ?";

	public OrderDetailDto getOrderDetail(Long orderDetailId);

	public String find_ARGS = "dto,paths";

	public List<OrderDetailDto> find(OrderDetailFindDto dto, List<String> paths);

	public String findAndUser_ARGS = "dto,paths,userIds";

	public List<OrderDetailDto> findAndUser(OrderDetailFindDto dto,
	        List<String> paths, List<Long> userIds);

	public String getOrderDetailByTitleID_ARGS = "orderTitleId";

	public String getOrderDetailByTitleID_QUERY =
	    "ORDER_TITLE_ID = /*orderTitleId*/0";

	public List<OrderDetailDto> getOrderDetailByTitleId(Long orderTitleId);

	public void insert(OrderDetailDto entity);

	public void update(OrderDetailDto entity);

	public void delete(OrderDetailDto entity);
}
