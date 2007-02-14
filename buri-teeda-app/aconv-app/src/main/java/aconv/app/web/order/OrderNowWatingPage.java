package aconv.app.web.order;

import java.util.List;

import aconv.app.bao.OrderBao;

public class OrderNowWatingPage {

	private OrderBao orderBao;

	public void setOrderBao(OrderBao orderBao) {
		System.out.println("*-------------------------OrderBao Setter called");
		this.orderBao = orderBao;
	}

	public String doOrderNowWating() {
		List datas = orderBao.getUnderWork();
		System.out.println("NowWating:=" + datas.size());
		return null;
	}

	public String initialize() {
		return null;
	}

	public String prerender() {
		return null;
	}

}
