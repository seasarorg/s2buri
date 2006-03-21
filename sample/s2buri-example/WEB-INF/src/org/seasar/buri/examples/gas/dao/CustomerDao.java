package org.seasar.buri.examples.gas.dao;

import java.util.List;

import org.seasar.buri.examples.gas.entity.Customer;


public interface CustomerDao {
    public Class BEAN = Customer.class;
    public List getAllCustomer();

    public String getCustomerByCustomerCode_ARGS = "customerCode";
    public Customer getCustomerByCustomerCode(String customerCode);

    public String getCustomerById_ARGS = "customerId";
    public Customer getCustomerById(long customerId);

    public String getCustomerListByIds_ARGS = "customerIds";
    public String getCustomerListByIds_QUERY = "customerId in /*customer*/(1)";
    public List getCustomerListByIds(List customerIds);

    public void insert(Customer customer);
    public void update(Customer customer);
    public void delete(Customer customer);
}
