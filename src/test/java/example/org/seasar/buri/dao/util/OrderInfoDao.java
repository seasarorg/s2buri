/*
 * çÏê¨ì˙: 2005/11/29
 *
 */
package example.org.seasar.buri.dao.util;

import java.util.Iterator;
import java.util.List;

import example.org.seasar.buri.dao.OrderDetailDao;
import example.org.seasar.buri.dao.OrderTitleDao;
import example.org.seasar.buri.dto.OrderDetailDto;
import example.org.seasar.buri.dto.OrderInfoDto;
import example.org.seasar.buri.dto.OrderTitleDto;

public class OrderInfoDao {
    private OrderTitleDao titleDao;
    private OrderDetailDao detailDao;
    
    public void insert(OrderInfoDto orderInfo) {
        titleDao.insert(orderInfo);
        Iterator ite = orderInfo.getOrderDetail().iterator();
        while(ite.hasNext()) {
            OrderDetailDto detailDto = (OrderDetailDto)ite.next();
            detailDto.setOrderTitleID(orderInfo.getOrderTitleID());
            detailDao.insert(detailDto);
        }
    }
    
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
