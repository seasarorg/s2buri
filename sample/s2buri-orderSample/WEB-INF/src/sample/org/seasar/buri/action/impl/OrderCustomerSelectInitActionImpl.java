package sample.org.seasar.buri.action.impl;

import sample.org.seasar.buri.action.OrderCustomerSelectInitAction;
import sample.org.seasar.buri.dto.CustomerListDto;
import sample.org.seasar.buri.logic.OrderLogic;

public class OrderCustomerSelectInitActionImpl implements OrderCustomerSelectInitAction {

	private OrderLogic logic;
	public void setOrderLogic(OrderLogic logic) {
		this.logic = logic;
	}

	private CustomerListDto dto;
	public void setCustomerListDto(CustomerListDto dto) {
		this.dto = dto;
	}
	
	public String inithialize() {
		dto.setCustomerList(logic.getAllCustomer());
		return null;
	}
	
}
