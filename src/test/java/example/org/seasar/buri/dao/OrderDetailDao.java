package example.org.seasar.buri.dao;

import java.util.List;

import example.org.seasar.buri.dto.OrderDetailDto;

public interface OrderDetailDao {
    public Class BEAN = OrderDetailDto.class;

    public List getAllOrderDetail();

    public String getOrderDetail_ARGS = "orderDetailID";
    public OrderDetailDto getOrderDetail(long orderDetailID);

    public String getOrderDetailByIds_ARGS = "orderDetailIDs";
    public String getOrderDetailByIds_QUERY = "orderDetailID in /*orderDetailIDs*/(1)";
    public List getOrderDetailByIds(List orderDetailIDs);
    
    public String getOrderDetailByTitleID_ARGS = "orderTitleID";
    public String getOrderDetailByTitleID_QUERY = "orderTitleID = /*orderTitleID*/0";
    public List getOrderDetailByTitleID(long orderTitleID);
    
    public void insert(OrderDetailDto dto);
    
    public void update(OrderDetailDto dto);
    
    public void delete(OrderDetailDto dto);
}

