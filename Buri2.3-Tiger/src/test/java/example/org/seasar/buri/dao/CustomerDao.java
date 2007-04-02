package example.org.seasar.buri.dao;

import java.util.List;

import example.org.seasar.buri.dto.CustomerDto;
import example.org.seasar.buri.dto.CustomerFindDto;

public interface CustomerDao {
    public Class BEAN = CustomerDto.class;

    public List getAllCustomer();

    public String getCustomer_QUERY = "customerID = ?";
    public CustomerDto getCustomer(long customerID);

    public String getCustomerByIds_ARGS = "customerIDs";
    public String getCustomerByIds_QUERY = "customerID in /*customerIDs*/(1)";
    public List getCustomerByIds(List customerIDs);
    
    public String find_ARGS = "dto,paths";
    public List find(CustomerFindDto dto,List paths);
    
    public String findAndUser_ARGS = "dto,paths,userIDs";
    public List findAndUser(CustomerFindDto dto,List paths,List userIDs);

    //public String soleMatch_ARGS = "dto";
    //public CustomerDto soleMatch(CustomerFindDto dto);
    
    public void insert(CustomerDto dto);
    
    public void update(CustomerDto dto);
    
    public void delete(CustomerDto dto);
}

