/*
 * çÏê¨ì˙: 2005/11/29
 *
 */
package example.org.seasar.buri.dao.util;

import java.util.Iterator;

import example.org.seasar.buri.dao.ShippingDao;
import example.org.seasar.buri.dao.ShippingItemDao;
import example.org.seasar.buri.dto.OrderDetailDto;
import example.org.seasar.buri.dto.OrderInfoDto;
import example.org.seasar.buri.dto.ShippingDto;
import example.org.seasar.buri.dto.ShippingItemDto;
import example.org.seasar.buri.dto.ShippingSetDto;

public class ShippingSetDao {
    private ShippingDao shippingDao;
    private ShippingItemDao itemDao;
    private OrderInfoDao orderInfoDao;
    
    public void insert(ShippingSetDto setDto) {
        shippingDao.insert(setDto);
        Iterator ite = setDto.getItems().iterator();
        while(ite.hasNext()) {
            ShippingItemDto itemDto = (ShippingItemDto)ite.next();
            itemDto.setShippingID(setDto.getShippingID());
            itemDao.insert(itemDto);
        }
    }
    
    public void update(ShippingSetDto setDto) {
        shippingDao.update(setDto);
        Iterator ite = setDto.getItems().iterator();
        while(ite.hasNext()) {
            ShippingItemDto itemDto = (ShippingItemDto)ite.next();
            itemDto.setShippingID(setDto.getShippingID());
            if(itemDto.getShippingItemID() != 0) {
                itemDao.update(itemDto);
            } else {
                itemDao.insert(itemDto);
            }
        }
    }
    
    public ShippingSetDto getShippingSetDto(long shippingID) {
        ShippingSetDto setDto = new ShippingSetDto();
        ShippingDto dto = shippingDao.getShipping(shippingID);
        if(dto == null) {
            return setDto;
        }
        setDto.setShippingID(dto.getShippingID());
        setDto.setCustomerID(dto.getCustomerID());
        setDto.setOrderTitleID(dto.getOrderTitleID());
        setDto.setShippingDate(dto.getShippingDate());
        setDto.setShippingID(dto.getShippingID());
        setDto.setItems(itemDao.getShippingItemByShippingID(shippingID));
        return setDto;
    }
    
    public ShippingSetDto getDtoByOrderTitleID(OrderInfoDto orderInfo) {
        ShippingSetDto setDto = new ShippingSetDto();
        ShippingDto dto = shippingDao.getShippingByOrderTitleID(orderInfo.getOrderTitleID());
        if(dto == null) {
            setDto.setOrderTitleID(orderInfo.getOrderTitleID());
            Iterator ite = orderInfo.getOrderDetail().iterator();
            while(ite.hasNext()) {
                OrderDetailDto detailDto = (OrderDetailDto)ite.next();
                ShippingItemDto shippingItemDto = new ShippingItemDto();
                shippingItemDto.setOrderDetailID(detailDto.getOrderDetailID());
                setDto.getItems().add(shippingItemDto);
            }
            return setDto;
        }
        setDto = getShippingSetDto(dto.getShippingID());
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
