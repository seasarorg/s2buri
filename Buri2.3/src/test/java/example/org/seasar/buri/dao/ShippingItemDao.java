package example.org.seasar.buri.dao;

import java.util.List;

import example.org.seasar.buri.dto.ShippingItemDto;

public interface ShippingItemDao {
    public Class BEAN = ShippingItemDto.class;

    public List getAllShippingItem();

    public String getShippingItem_ARGS = "shippingItemID";
    public ShippingItemDto getShippingItem(long shippingItemID);

    public String getShippingItemByIds_ARGS = "shippingItemIDs";
    public String getShippingItemByIds_QUERY = "shippingItemID in /*shippingItemIDs*/(1)";
    public List getShippingItemByIds(List shippingItemIDs);
    
    public String getShippingItemByShippingID_ARGS = "shippingID";
    public String getShippingItemByShippingID_QUERY = "shippingID = /*shippingID*/0";
    public List getShippingItemByShippingID(long shippingID);

    public void insert(ShippingItemDto dto);
    
    public void update(ShippingItemDto dto);
    
    public void delete(ShippingItemDto dto);
}

