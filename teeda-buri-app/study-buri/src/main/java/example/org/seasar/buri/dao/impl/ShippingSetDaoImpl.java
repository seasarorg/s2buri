/*
 * 作成日: 2005/11/29
 *
 */
package example.org.seasar.buri.dao.impl;

import java.util.Iterator;

import example.org.seasar.buri.dao.OrderInfoDao;
import example.org.seasar.buri.dao.ShippingDao;
import example.org.seasar.buri.dao.ShippingItemDao;
import example.org.seasar.buri.dao.ShippingSetDao;
import example.org.seasar.buri.dto.OrderDetailDto;
import example.org.seasar.buri.dto.OrderInfoDto;
import example.org.seasar.buri.dto.ShippingDto;
import example.org.seasar.buri.dto.ShippingItemDto;
import example.org.seasar.buri.dto.ShippingSetDto;

public class ShippingSetDaoImpl implements ShippingSetDao {
    private ShippingDao shippingDao;
    private ShippingItemDao itemDao;
    private OrderInfoDao orderInfoDao;
    
    /* (non-Javadoc)
	 * @see example.org.seasar.buri.dao.impl.ShippingSetDao#insert(example.org.seasar.buri.dto.ShippingSetDto)
	 */
    public void insert(ShippingSetDto setDto) {
        shippingDao.insert(setDto);
        Iterator ite = setDto.getItems().iterator();
        while(ite.hasNext()) {
            ShippingItemDto itemDto = (ShippingItemDto)ite.next();
            itemDto.setShippingID(setDto.getShippingID());
            itemDao.insert(itemDto);
        }
    }
    
    /* (non-Javadoc)
	 * @see example.org.seasar.buri.dao.impl.ShippingSetDao#update(example.org.seasar.buri.dto.ShippingSetDto)
	 */
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
    
    /* (non-Javadoc)
	 * @see example.org.seasar.buri.dao.impl.ShippingSetDao#getShippingSetDto(long)
	 */
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
    
    /* (non-Javadoc)
	 * @see example.org.seasar.buri.dao.impl.ShippingSetDao#getDtoByOrderTitleID(example.org.seasar.buri.dto.OrderInfoDto)
	 */
    public ShippingSetDto getDtoByOrderTitleID(OrderInfoDto orderInfo) {
        ShippingSetDto setDto = new ShippingSetDto();
        ShippingDto dto = shippingDao.getShippingByOrderTitleID(orderInfo.getOrderTitleID());
        if(dto == null) {
            setDto.setCustomerID(orderInfo.getCustomerID());
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

    /* (non-Javadoc)
	 * @see example.org.seasar.buri.dao.impl.ShippingSetDao#getItemDao()
	 */
    public ShippingItemDao getItemDao() {
        return itemDao;
    }

    /* (non-Javadoc)
	 * @see example.org.seasar.buri.dao.impl.ShippingSetDao#setItemDao(example.org.seasar.buri.dao.ShippingItemDao)
	 */
    public void setItemDao(ShippingItemDao itemDao) {
        this.itemDao = itemDao;
    }

    /* (non-Javadoc)
	 * @see example.org.seasar.buri.dao.impl.ShippingSetDao#getOrderInfoDao()
	 */
    public OrderInfoDao getOrderInfoDao() {
        return orderInfoDao;
    }

    /* (non-Javadoc)
	 * @see example.org.seasar.buri.dao.impl.ShippingSetDao#setOrderInfoDao(example.org.seasar.buri.dao.OrderInfoDao)
	 */
    public void setOrderInfoDao(OrderInfoDao orderInfoDao) {
        this.orderInfoDao = orderInfoDao;
    }

    /* (non-Javadoc)
	 * @see example.org.seasar.buri.dao.impl.ShippingSetDao#getShippingDao()
	 */
    public ShippingDao getShippingDao() {
        return shippingDao;
    }

    /* (non-Javadoc)
	 * @see example.org.seasar.buri.dao.impl.ShippingSetDao#setShippingDao(example.org.seasar.buri.dao.ShippingDao)
	 */
    public void setShippingDao(ShippingDao shippingDao) {
        this.shippingDao = shippingDao;
    }
    
}
