package example.org.escafe.buri.dao;

import example.org.escafe.buri.dto.OrderInfoDto;

public interface OrderInfoDao {

	public abstract void insert(OrderInfoDto orderInfo);

	public abstract void update(OrderInfoDto orderInfo);

	public abstract OrderInfoDto getOrderInfo(long orderTitleID);

	public abstract OrderDetailDao getDetailDao();

	public abstract void setDetailDao(OrderDetailDao detailDao);

	public abstract OrderTitleDao getTitleDao();

	public abstract void setTitleDao(OrderTitleDao titleDao);

}