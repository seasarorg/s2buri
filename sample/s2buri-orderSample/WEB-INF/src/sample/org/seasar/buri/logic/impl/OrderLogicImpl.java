package sample.org.seasar.buri.logic.impl;

import java.util.List;

import sample.org.seasar.buri.logic.OrderLogic;
import example.org.seasar.buri.dao.CustomerDao;

public class OrderLogicImpl implements OrderLogic {

	private CustomerDao dao;
	public void setCustomerDao(CustomerDao dao) {
		this.dao = dao;
	}
	
	public List getAllCustomer() {
		return dao.getAllCustomer();
	}
	
}
