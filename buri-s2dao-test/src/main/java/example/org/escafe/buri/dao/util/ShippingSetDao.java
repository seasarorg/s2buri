/*
 * 作成日: 2005/11/29
 *
 */
package example.org.escafe.buri.dao.util;

import java.util.Iterator;

import example.org.escafe.buri.dao.ShippingDao;
import example.org.escafe.buri.dao.ShippingItemDao;
import example.org.escafe.buri.dto.OrderDetailDto;
import example.org.escafe.buri.dto.OrderInfoDto;
import example.org.escafe.buri.dto.ShippingDto;
import example.org.escafe.buri.dto.ShippingItemDto;
import example.org.escafe.buri.dto.ShippingSetDto;

public class ShippingSetDao {
	private ShippingDao shippingDao;

	private ShippingItemDao itemDao;

	private OrderInfoDao orderInfoDao;

	public void insert(ShippingSetDto setDto) {
		shippingDao.insert(setDto);
		Iterator ite = setDto.getShippingItemList().iterator();
		while (ite.hasNext()) {
			ShippingItemDto itemDto = (ShippingItemDto) ite.next();
			itemDto.setShippingId(setDto.getShippingId());
			itemDao.insert(itemDto);
		}
	}

	public void update(ShippingSetDto setDto) {
		shippingDao.update(setDto);
		Iterator ite = setDto.getShippingItemList().iterator();
		while (ite.hasNext()) {
			ShippingItemDto itemDto = (ShippingItemDto) ite.next();
			itemDto.setShippingId(setDto.getShippingId());
			if (itemDto.getShippingItemId() != 0) {
				itemDao.update(itemDto);
			} else {
				itemDao.insert(itemDto);
			}
		}
	}

	public ShippingSetDto getShippingSetDto(Long shippingId) {
		ShippingSetDto setDto = new ShippingSetDto();
		ShippingDto dto = shippingDao.getShipping(shippingId);
		if (dto == null) {
			return setDto;
		}
		setDto.setShippingId(dto.getShippingId());
		setDto.setCustomerId(dto.getCustomerId());
		setDto.setOrderTitleId(dto.getOrderTitleId());
		setDto.setShippingDate(dto.getShippingDate());
		setDto.setShippingId(dto.getShippingId());
		setDto.setShippingItemList(itemDao
		    .getShippingItemByShippingId(shippingId));
		return setDto;
	}

	public ShippingSetDto getDtoByOrderTitleId(OrderInfoDto orderInfo) {
		ShippingSetDto setDto = new ShippingSetDto();
		ShippingDto dto =
		    shippingDao.getShippingByOrderTitleId(orderInfo.getOrderTitleId());
		if (dto == null) {
			setDto.setCustomerId(orderInfo.getCustomerId());
			setDto.setOrderTitleId(orderInfo.getOrderTitleId());
			Iterator ite = orderInfo.getOrderDetailList().iterator();
			while (ite.hasNext()) {
				OrderDetailDto detailDto = (OrderDetailDto) ite.next();
				ShippingItemDto shippingItemDto = new ShippingItemDto();
				shippingItemDto.setOrderDetailId(detailDto.getOrderDetailId());
				setDto.getShippingItemList().add(shippingItemDto);
			}
			return setDto;
		}
		setDto = getShippingSetDto(dto.getShippingId());
		return setDto;
	}

	public ShippingItemDao getItemDao() {
		return itemDao;
	}

	public void setItemDao(ShippingItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public OrderInfoDao getOrderInfoDao() {
		return orderInfoDao;
	}

	public void setOrderInfoDao(OrderInfoDao orderInfoDao) {
		this.orderInfoDao = orderInfoDao;
	}

	public ShippingDao getShippingDao() {
		return shippingDao;
	}

	public void setShippingDao(ShippingDao shippingDao) {
		this.shippingDao = shippingDao;
	}
}
