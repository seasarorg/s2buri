package example.org.seasar.buri.dao;

import java.util.List;

import example.org.seasar.buri.dto.ShippingDto;
import example.org.seasar.buri.dto.ShippingFindDto;

public interface ShippingDao {
    public Class BEAN = ShippingDto.class;

    public List getAllShipping();

    public String getShipping_QUERY = "shippingID = ?";
    public ShippingDto getShipping(long shippingID);

    public String getShippingByIds_ARGS = "shippingIDs";
    public String getShippingByIds_QUERY = "shippingID in /*shippingIDs*/(1)";
    public List getShippingByIds(List shippingIDs);
    
    public String find_ARGS = "dto,paths";
    public List find(ShippingFindDto dto,List paths);
    
    public String findAndUser_ARGS = "dto,paths,userIDs";
    public List findAndUser(ShippingFindDto dto,List paths,List userIDs);
    
    public String getShippingByOrderTitleID_ARGS = "orderTitleID";
    public String getShippingByOrderTitleID_QUERY = "orderTitleID = /*orderTitleID*/0";
    public ShippingDto getShippingByOrderTitleID(long orderTitleID);


    //public String soleMatch_ARGS = "dto";
    //public ShippingDto soleMatch(ShippingFindDto dto);
    
    public void insert(ShippingDto dto);
    
    public void update(ShippingDto dto);
    
    public void delete(ShippingDto dto);
}

