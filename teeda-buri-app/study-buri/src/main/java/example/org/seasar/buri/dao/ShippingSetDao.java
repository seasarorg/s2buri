package example.org.seasar.buri.dao;

import example.org.seasar.buri.dto.OrderInfoDto;
import example.org.seasar.buri.dto.ShippingSetDto;

public interface ShippingSetDao {

	public abstract void insert(ShippingSetDto setDto);

	public abstract void update(ShippingSetDto setDto);

	public abstract ShippingSetDto getShippingSetDto(long shippingID);

	public abstract ShippingSetDto getDtoByOrderTitleID(OrderInfoDto orderInfo);

	public abstract ShippingItemDao getItemDao();

	public abstract void setItemDao(ShippingItemDao itemDao);

	public abstract OrderInfoDao getOrderInfoDao();

	public abstract void setOrderInfoDao(OrderInfoDao orderInfoDao);

	public abstract ShippingDao getShippingDao();

	public abstract void setShippingDao(ShippingDao shippingDao);

}