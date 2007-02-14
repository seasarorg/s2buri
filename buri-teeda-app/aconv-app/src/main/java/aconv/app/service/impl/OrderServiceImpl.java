package aconv.app.service.impl;

import java.util.Date;

import aconv.app.bao.OrderBao;
import aconv.app.dao.CustomerDao;
import aconv.app.dao.ItemDao;
import aconv.app.dto.CustomerDto;
import aconv.app.dto.ItemDto;
import aconv.app.dto.OrderDetailDto;
import aconv.app.dto.OrderInfoDto;
import aconv.app.service.OrderService;

public class OrderServiceImpl implements OrderService {

	private CustomerDao customerDao;

	private ItemDao itemDao;

	private OrderBao orderBao;

	public void doOrder() {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setCustomerName("客1");
		customerDao.insert(customerDto);
		//
		ItemDto itemDto = new ItemDto();
		itemDto.setItemName("Wii");
		itemDto.setPrice(29800);
		itemDao.insert(itemDto);
		//
		OrderInfoDto orderInfoDto = new OrderInfoDto();
		orderInfoDto.setCustomerID(customerDto.getCustomerID());
		orderInfoDto.setOrderDate(new Date());
		orderInfoDto.setStatus(new Integer(0));
		//
		OrderDetailDto orderDetailDto = new OrderDetailDto();
		orderDetailDto.setItemID(itemDto.getItemID());
		orderDetailDto.setOrderCount(1);
		orderInfoDto.getOrderDetail().add(orderDetailDto);
		//
		orderBao.order(orderInfoDto);
	}

	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public ItemDao getItemDao() {
		return itemDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public OrderBao getOrderBao() {
		return orderBao;
	}

	public void setOrderBao(OrderBao orderBao) {
		this.orderBao = orderBao;
	}

}
