package sample.org.seasar.buri.action.impl;

import java.util.List;

import sample.org.seasar.buri.action.OrderCustomerSelectInitAction;
import sample.org.seasar.buri.logic.OrderLogic;

public class OrderCustomerSelectInitActionImpl implements OrderCustomerSelectInitAction {

	private OrderLogic logic;
	public void setOrderLogic(OrderLogic logic) {
		this.logic = logic;
	}

//	private CustomerListDto dto;
//	public void setCustomerListDto(CustomerListDto dto) {
//		this.dto = dto;
//	}

	private List customerList;
	public List getCustomerList() {
		return customerList;
	}
	
	public String inithialize() {
		//dto.setCustomerList(logic.getAllCustomer());
		customerList = logic.getAllCustomer();
		return null;
	}
	
}
