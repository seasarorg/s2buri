package aconv.app.web.order;

import aconv.app.service.OrderService;

public class OrderPage {

	private OrderService orderService;

	public void setOrderService(OrderService orderService) {
		System.out.println("*-------------------------OrderService Setter called");
		this.orderService = orderService;
	}

	public String doOrder() {
		System.out.println("*-------------------------doOrder called");
		orderService.doOrder();
		return "aaa/bbb";
	}

	public String initialize() {
		System.out.println("*-------------------------inithialize called");
		return null;
	}

	public String prerender() {
		System.out.println("*-------------------------prerender called");
		return null;
	}

}
