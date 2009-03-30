/*
 * 作成日: 2005/11/29
 *
 */
package example.org.escafe.buri.dao.util;

import java.util.Iterator;
import java.util.List;

import example.org.escafe.buri.dao.OrderDetailDao;
import example.org.escafe.buri.dao.OrderTitleDao;
import example.org.escafe.buri.dto.OrderDetailDto;
import example.org.escafe.buri.dto.OrderInfoDto;
import example.org.escafe.buri.dto.OrderTitleDto;

public class OrderInfoDao {
	private OrderTitleDao titleDao;

	private OrderDetailDao detailDao;

	public void insert(OrderInfoDto orderInfo) {
		titleDao.insert(orderInfo);
		Iterator ite = orderInfo.getOrderDetailList().iterator();
		while (ite.hasNext()) {
			OrderDetailDto detailDto = (OrderDetailDto) ite.next();
			detailDto.setOrderTitleId(orderInfo.getOrderTitleId());
			detailDao.insert(detailDto);
		}
	}

	public void update(OrderInfoDto orderInfo) {
		titleDao.update(orderInfo);
		Iterator ite = orderInfo.getOrderDetailList().iterator();
		while (ite.hasNext()) {
			OrderDetailDto detailDto = (OrderDetailDto) ite.next();
			detailDto.setOrderTitleId(orderInfo.getOrderTitleId());
			if (detailDto.getOrderDetailId() != 0) {
				detailDao.update(detailDto);
			} else {
				detailDao.insert(detailDto);
			}
		}
	}

	public OrderInfoDto getOrderInfo(Long orderTitleId) {
		OrderTitleDto titleDto = titleDao.getOrderTitle(orderTitleId);
		if (titleDto == null) {
			return null;
		}
		OrderInfoDto info = new OrderInfoDto();
		info.setOrderTitleId(titleDto.getOrderTitleId());
		info.setCustomerId(titleDto.getCustomerId());
		info.setOrderDate(titleDto.getOrderDate());
		info.setStatus(titleDto.getStatus());
		List details = detailDao.getOrderDetailByTitleId(orderTitleId);
		info.setOrderDetailList(details);
		return info;
	}

	public OrderDetailDao getDetailDao() {
		return detailDao;
	}

	public void setDetailDao(OrderDetailDao detailDao) {
		this.detailDao = detailDao;
	}

	public OrderTitleDao getTitleDao() {
		return titleDao;
	}

	public void setTitleDao(OrderTitleDao titleDao) {
		this.titleDao = titleDao;
	}
}
