/*
 * 作成日: 2005/11/29
 *
 */
package example.org.escafe.buri.dao.impl;

import java.util.Iterator;
import java.util.List;

import example.org.escafe.buri.dao.OrderDetailDao;
import example.org.escafe.buri.dao.OrderInfoDao;
import example.org.escafe.buri.dao.OrderTitleDao;
import example.org.escafe.buri.dto.OrderDetailDto;
import example.org.escafe.buri.dto.OrderInfoDto;
import example.org.escafe.buri.dto.OrderTitleDto;

public class OrderInfoDaoImpl implements OrderInfoDao {
    private OrderTitleDao titleDao;
    private OrderDetailDao detailDao;
    
    /* (non-Javadoc)
	 * @see example.org.escafe.buri.dao.impl.OrderInfoDao#insert(example.org.escafe.buri.dto.OrderInfoDto)
	 */
    public void insert(OrderInfoDto orderInfo) {
        titleDao.insert(orderInfo);
        Iterator ite = orderInfo.getOrderDetail().iterator();
        while(ite.hasNext()) {
            OrderDetailDto detailDto = (OrderDetailDto)ite.next();
            detailDto.setOrderTitleID(orderInfo.getOrderTitleID());
            detailDao.insert(detailDto);
        }
    }
    
    /* (non-Javadoc)
	 * @see example.org.escafe.buri.dao.impl.OrderInfoDao#update(example.org.escafe.buri.dto.OrderInfoDto)
	 */
    public void update(OrderInfoDto orderInfo) {
        titleDao.update(orderInfo);
        Iterator ite = orderInfo.getOrderDetail().iterator();
        while(ite.hasNext()) {
            OrderDetailDto detailDto = (OrderDetailDto)ite.next();
            detailDto.setOrderTitleID(orderInfo.getOrderTitleID());
            if(detailDto.getOrderDetailID() != 0) {
                detailDao.update(detailDto);
            } else {
                detailDao.insert(detailDto);
            }
        }
    }
    
    /* (non-Javadoc)
	 * @see example.org.escafe.buri.dao.impl.OrderInfoDao#getOrderInfo(long)
	 */
    public OrderInfoDto getOrderInfo(long orderTitleID) {
        OrderTitleDto titleDto = titleDao.getOrderTitle(orderTitleID);
        if(titleDto==null) {
            return null;
        }
        OrderInfoDto info = new OrderInfoDto();
        info.setOrderTitleID(titleDto.getOrderTitleID());
        info.setCustomerID(titleDto.getCustomerID());
        info.setOrderDate(titleDto.getOrderDate());
        info.setStatus(titleDto.getStatus());
        List details = detailDao.getOrderDetailByTitleID(orderTitleID);
        info.setOrderDetail(details);
        return info;
    }

    /* (non-Javadoc)
	 * @see example.org.escafe.buri.dao.impl.OrderInfoDao#getDetailDao()
	 */
    public OrderDetailDao getDetailDao() {
        return detailDao;
    }

    /* (non-Javadoc)
	 * @see example.org.escafe.buri.dao.impl.OrderInfoDao#setDetailDao(example.org.escafe.buri.dao.OrderDetailDao)
	 */
    public void setDetailDao(OrderDetailDao detailDao) {
        this.detailDao = detailDao;
    }

    /* (non-Javadoc)
	 * @see example.org.escafe.buri.dao.impl.OrderInfoDao#getTitleDao()
	 */
    public OrderTitleDao getTitleDao() {
        return titleDao;
    }

    /* (non-Javadoc)
	 * @see example.org.escafe.buri.dao.impl.OrderInfoDao#setTitleDao(example.org.escafe.buri.dao.OrderTitleDao)
	 */
    public void setTitleDao(OrderTitleDao titleDao) {
        this.titleDao = titleDao;
    }
    
}
