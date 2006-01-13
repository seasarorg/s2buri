package example.org.seasar.buri.dao;

import java.util.List;

import example.org.seasar.buri.dto.CustomerDto;

public interface CustomerDao {
    public Class BEAN = CustomerDto.class;

    public List getAllCustomer();

    public String getCustomer_ARGS = "customerID";
    public CustomerDto getCustomer(long customerID);

    public String getCustomerByIds_ARGS = "customerIDs";
    public String getCustomerByIds_QUERY = "customerID in /*customerIDs*/(1)";
    public List getCustomerByIds(List customerIDs);
    
    public void insert(CustomerDto dto);
    
    public void update(CustomerDto dto);
    
    public void delete(CustomerDto dto);
}

