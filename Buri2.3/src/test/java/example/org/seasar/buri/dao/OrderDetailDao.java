package example.org.seasar.buri.dao;

import java.util.List;

import example.org.seasar.buri.dto.OrderDetailDto;
import example.org.seasar.buri.dto.OrderDetailFindDto;

public interface OrderDetailDao {
    public Class BEAN = OrderDetailDto.class;

    public List getAllOrderDetail();

    public String getOrderDetail_QUERY = "orderDetailID = ?";
    public OrderDetailDto getOrderDetail(long orderDetailID);

    public String getOrderDetailByIds_ARGS = "orderDetailIDs";
    public String getOrderDetailByIds_QUERY = "orderDetailID in /*orderDetailIDs*/(1)";
    public List getOrderDetailByIds(List orderDetailIDs);
    
    public String find_ARGS = "dto,paths";
    public List find(OrderDetailFindDto dto,List paths);
    
    public String findAndUser_ARGS = "dto,paths,userIDs";
    public List findAndUser(OrderDetailFindDto dto,List paths,List userIDs);
    
    public String getOrderDetailByTitleID_ARGS = "orderTitleID";
    public String getOrderDetailByTitleID_QUERY = "orderTitleID = /*orderTitleID*/0";
    public List getOrderDetailByTitleID(long orderTitleID);
    

    //public String soleMatch_ARGS = "dto";
    //public OrderDetailDto soleMatch(OrderDetailFindDto dto);
    
    public void insert(OrderDetailDto dto);
    
    public void update(OrderDetailDto dto);
    
    public void delete(OrderDetailDto dto);
}

