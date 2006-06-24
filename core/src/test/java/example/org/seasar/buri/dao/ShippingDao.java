package example.org.seasar.buri.dao;

import java.util.List;

import example.org.seasar.buri.dto.ShippingDto;

public interface ShippingDao {
    public Class BEAN = ShippingDto.class;

    public List getAllShipping();

    public String getShipping_ARGS = "shippingID";
    public ShippingDto getShipping(long shippingID);

    public String getShippingByIds_ARGS = "shippingIDs";
    public String getShippingByIds_QUERY = "shippingID in /*shippingIDs*/(1)";
    public List getShippingByIds(List shippingIDs);
    
    public String getShippingByOrderTitleID_ARGS = "orderTitleID";
    public String getShippingByOrderTitleID_QUERY = "orderTitleID = /*orderTitleID*/0";
    public ShippingDto getShippingByOrderTitleID(long orderTitleID);

    public void insert(ShippingDto dto);
    
    public void update(ShippingDto dto);
    
    public void delete(ShippingDto dto);
}

