package example.org.escafe.buri.dao;

import java.util.List;

import example.org.escafe.buri.dto.CustomerDto;
import example.org.escafe.buri.dto.CustomerFindDto;

public interface CustomerDao {
	public Class<?> BEAN = CustomerDto.class;

	public List<CustomerDto> getAllCustomer();

	public String getCustomer_QUERY = "CUSTOMER_ID = ?";

	public CustomerDto getCustomer(Long customerId);

	public String getCustomerByIds_ARGS = "customerIds";

	public String getCustomerByIds_QUERY = "CUSTOMER_ID in /*customerIds*/(1)";

	public List<CustomerDto> getCustomerByIds(List<Long> customerIds);

	public String find_ARGS = "dto,paths";

	public List<CustomerDto> find(CustomerFindDto dto, List<String> paths);

	public String findAndUser_ARGS = "dto,paths,userIds";

	public List<CustomerDto> findAndUser(CustomerFindDto dto,
	        List<String> paths, List<Long> userIds);

	public void insert(CustomerDto entity);

	public void update(CustomerDto entity);

	public void delete(CustomerDto entity);
}
