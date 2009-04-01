package example.org.escafe.buri.dao;

import java.util.List;

import example.org.escafe.buri.dto.OrderTitleDto;
import example.org.escafe.buri.dto.OrderTitleFindDto;

public interface OrderTitleDao {
    public Class BEAN = OrderTitleDto.class;

    public List getAllOrderTitle();

    public String getOrderTitle_QUERY = "orderTitleID = ?";
    public OrderTitleDto getOrderTitle(long orderTitleID);

    public String getOrderTitleByIds_ARGS = "orderTitleIDs";
    public String getOrderTitleByIds_QUERY = "orderTitleID in /*orderTitleIDs*/(1)";
    public List getOrderTitleByIds(List orderTitleIDs);
    
    public String find_ARGS = "dto,paths";
    public List find(OrderTitleFindDto dto,List paths);
    
    public String findAndUser_ARGS = "dto,paths,userIDs";
    public List findAndUser(OrderTitleFindDto dto,List paths,List userIDs);

    //public String soleMatch_ARGS = "dto";
    //public OrderTitleDto soleMatch(OrderTitleFindDto dto);
    
    public void insert(OrderTitleDto dto);
    
    public void update(OrderTitleDto dto);
    
    public void delete(OrderTitleDto dto);
}

