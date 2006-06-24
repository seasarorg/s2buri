package example.org.seasar.buri.dao;

import java.util.List;

import example.org.seasar.buri.dto.OrderTitleDto;

public interface OrderTitleDao {
    public Class BEAN = OrderTitleDto.class;

    public List getAllOrderTitle();

    public String getOrderTitle_ARGS = "orderTitleID";
    public OrderTitleDto getOrderTitle(long orderTitleID);

    public String getOrderTitleByIds_ARGS = "orderTitleIDs";
    public String getOrderTitleByIds_QUERY = "orderTitleID in /*orderTitleIDs*/(1)";
    public List getOrderTitleByIds(List orderTitleIDs);

    public void insert(OrderTitleDto dto);
    
    public void update(OrderTitleDto dto);
    
    public void delete(OrderTitleDto dto);
}

