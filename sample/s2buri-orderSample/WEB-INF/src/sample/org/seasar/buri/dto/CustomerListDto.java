package sample.org.seasar.buri.dto;

import java.io.Serializable;
import java.util.List;

public class CustomerListDto implements Serializable {

	public List customerList;

	public List getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List customerList) {
		this.customerList = customerList;
	}
	
}
