/*
 * 作成日: 2005/11/29
 *
 */
package example.org.escafe.buri.test;

import java.util.Date;
import java.util.List;

import org.escafe.buri.bao.BillBao;
import org.escafe.buri.bao.OrderBao;
import org.escafe.buri.bao.ShippingBao;
import org.escafe.buri.bao.ShippingItemBao;
import org.escafe.buri.engine.BuriEngine;
import org.escafe.buri.service.BuriPathEntityService;
import org.escafe.buri.service.BuriStateEntityService;
import org.seasar.extension.unit.S2TestCase;

import example.org.escafe.buri.entity.Bill;
import example.org.escafe.buri.entity.Customer;
import example.org.escafe.buri.entity.Item;
import example.org.escafe.buri.entity.OrderDetail;
import example.org.escafe.buri.entity.OrderTitle;
import example.org.escafe.buri.entity.ShippingItem;
import example.org.escafe.buri.service.CustomerService;
import example.org.escafe.buri.service.ItemService;

public class BaoTest extends S2TestCase {
	private ItemService itemService;

	private CustomerService customerService;

	private BuriStateEntityService buriStateEntityService;

	private BuriPathEntityService buriPathEntityService;

	private BillBao billBao;

	private OrderBao orderBao;

	private ShippingBao shippingBao;

	private ShippingItemBao shippingItemBao;

	private Customer 客1;

	private Customer 客2;

	private Item 商品1;

	private Item 商品2;

	private Item 商品3;

	private Item 商品4;

	private Item 商品5;

	public BaoTest(String arg0) {
		super(arg0);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		include("baotest.dicon");
	}

	public void testNomalOrderTx() {
		// 本当はburi2.diconに書くもの
		BuriEngine buriEngine = (BuriEngine) getComponent("BuriSimpleEngine");
		buriEngine.readWorkFlowFromResource(
		    "wakanagoxpdl/orderBao.xpdl",
		    "注文管理");
		customerSetup();
		itemSetup();
		List<?> datas = null;
		OrderTitle orderInfoDto1 = orderSetup1();
		orderBao.order(orderInfoDto1);
		datas = orderBao.getUnderWork();
		assertEquals(datas.size(), 1);
		System.out.println(datas);
		datas = shippingBao.getNowWaiting();
		assertEquals(datas.size(), 1);
		System.out.println(datas);
		datas = shippingItemBao.getItemWaiting();
		assertEquals(datas.size(), 2);
		System.out.println(datas);
		ShippingItem shippingItem = (ShippingItem) datas.get(0);
		// endShipping
		shippingItemBao.endShipping(shippingItem);
		datas = orderBao.getUnderWork();
		assertEquals(datas.size(), 1);
		datas = shippingItemBao.getItemWaiting();
		System.out.println(datas);
		assertEquals(datas.size(), 1);
		shippingItem = (ShippingItem) datas.get(0);
		datas = shippingItemBao.getEndShipping();
		System.out.println(datas);
		assertEquals(datas.size(), 1);
		datas = shippingBao.getNowWaiting();
		System.out.println(datas);
		assertEquals(datas.size(), 1);
		datas = shippingBao.getEndShipping();
		System.out.println(datas);
		assertEquals(datas.size(), 0);
		shippingItemBao.endShipping(shippingItem);
		buriPathEntityService.getAllBuriPath();
		buriStateEntityService.getAllBuriState();
		datas = shippingItemBao.getItemWaiting();
		assertEquals(datas.size(), 0);
		datas = shippingItemBao.getEndShipping();
		assertEquals(datas.size(), 2);
		datas = shippingBao.getNowWaiting();
		assertEquals(datas.size(), 0);
		datas = shippingBao.getEndShipping();
		assertEquals(datas.size(), 1);
		datas = orderBao.getUnderWork();
		assertEquals(datas.size(), 0);
		datas = orderBao.getEndShipping();
		assertEquals(datas.size(), 1);
		datas = billBao.getBillWaiting();
		assertEquals(datas.size(), 1);
		Bill billDto = (Bill) datas.get(0);
		datas = billBao.getReBill();
		assertEquals(datas.size(), 0);
		datas = billBao.getEndBill();
		assertEquals(datas.size(), 0);
		billBao.bill(billDto);
		datas = billBao.getBillWaiting();
		assertEquals(datas.size(), 0);
		datas = billBao.getReBill();
		assertEquals(datas.size(), 1);
		billDto = (Bill) datas.get(0);
		datas = billBao.getEndBill();
		assertEquals(datas.size(), 0);
		billBao.checkPayment(billDto);
		datas = orderBao.getEndShipping();
		assertEquals(datas.size(), 0);
		datas = orderBao.getOrderEnd();
		assertEquals(datas.size(), 1);
		datas = billBao.getBillWaiting();
		assertEquals(datas.size(), 0);
		datas = billBao.getEndBill();
		assertEquals(datas.size(), 1);
	}

	// public void testCancelSuccessOrderTx() {
	// // 本当はburi2.diconに書くもの
	// BuriEngine buriEngine = (BuriEngine) getComponent("BuriSimpleEngine");
	// buriEngine.readWorkFlowFromResource(
	// "wakanagoxpdl/orderBao.xpdl",
	// "注文管理");
	// customerSetup();
	// itemSetup();
	// List<?> datas = null;
	// OrderTitle orderInfoDto1 = orderSetup1();
	// orderBao.order(orderInfoDto1);
	// datas = orderBao.getUnderWork();
	// assertEquals(datas.size(), 1);
	// System.out.println(datas);
	// datas = shippingBao.getNowWaiting();
	// assertEquals(datas.size(), 1);
	// System.out.println(datas);
	// datas = shippingItemBao.getItemWaiting();
	// assertEquals(datas.size(), 2);
	// System.out.println(datas);
	// Object status = orderBao.cancel(orderInfoDto1.orderTitleId);
	// assertEquals(status.toString(), "success");
	// datas = orderBao.getOrderCancelEnd();
	// assertEquals(datas.size(), 1);
	// datas = orderBao.getUnderWork();
	// assertEquals(datas.size(), 0);
	// datas = orderBao.getEndShipping();
	// assertEquals(datas.size(), 0);
	// datas = shippingItemBao.getCancel();
	// System.out.println(datas);
	// assertEquals(datas.size(), 2);
	// datas = shippingItemBao.getItemWaiting();
	// System.out.println(datas);
	// assertEquals(datas.size(), 0);
	// datas = shippingItemBao.getEndShipping();
	// System.out.println(datas);
	// assertEquals(datas.size(), 0);
	// datas = shippingBao.getShippingCancel();
	// System.out.println(datas);
	// assertEquals(datas.size(), 1);
	// datas = shippingBao.getNowWaiting();
	// System.out.println(datas);
	// assertEquals(datas.size(), 0);
	// datas = shippingBao.getEndShipping();
	// System.out.println(datas);
	// assertEquals(datas.size(), 0);
	// }
	//
	// public void testInvokeExceptionOrderTx() {
	// // 本当はburi2.diconに書くもの
	// BuriEngine buriEngine = (BuriEngine) getComponent("BuriSimpleEngine");
	// buriEngine.readWorkFlowFromResource(
	// "wakanagoxpdl/orderBao.xpdl",
	// "注文管理");
	// customerSetup();
	// itemSetup();
	// List<?> datas = null;
	// OrderTitle orderInfoDto1 = orderSetup1();
	// orderBao.order(orderInfoDto1);
	// try {
	// orderBao.order(orderInfoDto1);
	// fail();
	// } catch (Exception ex) {
	// }
	// datas = orderBao.getUnderWork();
	// assertEquals(datas.size(), 1);
	// System.out.println(datas);
	// datas = shippingBao.getNowWaiting();
	// assertEquals(datas.size(), 1);
	// System.out.println(datas);
	// datas = shippingItemBao.getItemWaiting();
	// assertEquals(datas.size(), 2);
	// System.out.println(datas);
	// Object status = orderBao.cancel(orderInfoDto1.orderTitleId);
	// assertEquals(status.toString(), "success");
	// datas = orderBao.getOrderCancelEnd();
	// assertEquals(datas.size(), 1);
	// datas = orderBao.getUnderWork();
	// assertEquals(datas.size(), 0);
	// datas = orderBao.getEndShipping();
	// assertEquals(datas.size(), 0);
	// datas = shippingItemBao.getCancel();
	// System.out.println(datas);
	// assertEquals(datas.size(), 2);
	// datas = shippingItemBao.getItemWaiting();
	// System.out.println(datas);
	// assertEquals(datas.size(), 0);
	// datas = shippingItemBao.getEndShipping();
	// System.out.println(datas);
	// assertEquals(datas.size(), 0);
	// datas = shippingBao.getShippingCancel();
	// System.out.println(datas);
	// assertEquals(datas.size(), 1);
	// datas = shippingBao.getNowWaiting();
	// System.out.println(datas);
	// assertEquals(datas.size(), 0);
	// datas = shippingBao.getEndShipping();
	// System.out.println(datas);
	// assertEquals(datas.size(), 0);
	// }
	protected OrderTitle orderSetup1() {
		OrderTitle dto = new OrderTitle();
		dto.customerId = 客1.customerId;
		dto.orderDate = new Date();
		dto.status = new Integer(0);
		OrderDetail detailDto = new OrderDetail();
		detailDto.itemId = 商品2.itemId;
		detailDto.orderCount = 1;
		dto.orderDetailList.add(detailDto);
		detailDto = new OrderDetail();
		detailDto.itemId = 商品3.itemId;
		detailDto.orderCount = 2;
		dto.orderDetailList.add(detailDto);
		return dto;
	}

	protected OrderTitle orderSetup2() {
		OrderTitle dto = new OrderTitle();
		dto.customerId = 客1.customerId;
		dto.orderDate = new Date();
		dto.status = new Integer(0);
		OrderDetail detailDto = new OrderDetail();
		detailDto.itemId = 商品4.itemId;
		detailDto.orderCount = 2;
		dto.orderDetailList.add(detailDto);
		detailDto = new OrderDetail();
		detailDto.itemId = 商品5.itemId;
		detailDto.orderCount = 1;
		dto.orderDetailList.add(detailDto);
		return dto;
	}

	protected OrderTitle orderSetup3() {
		OrderTitle dto = new OrderTitle();
		dto.customerId = 客2.customerId;
		dto.orderDate = new Date();
		dto.status = new Integer(0);
		OrderDetail detailDto = new OrderDetail();
		detailDto.itemId = 商品1.itemId;
		detailDto.orderCount = 5;
		dto.orderDetailList.add(detailDto);
		return dto;
	}

	protected void itemSetup() {
		商品1 = new Item();
		商品1.itemName = "PS1";
		商品1.price = 19800L;
		itemService.insert(商品1);
		商品2 = new Item();
		商品2.itemName = "PS2";
		商品2.price = 19800L;
		itemService.insert(商品2);
		商品3 = new Item();
		商品3.itemName = "PS3";
		商品3.price = 19800L;
		itemService.insert(商品3);
		商品4 = new Item();
		商品4.itemName = "PS4";
		商品4.price = 19800L;
		itemService.insert(商品4);
		商品5 = new Item();
		商品5.itemName = "PS5";
		商品5.price = 19800L;
		itemService.insert(商品5);
	}

	protected void customerSetup() {
		客1 = new Customer();
		客1.customerName = "客１だよ";
		customerService.insert(客1);
		客2 = new Customer();
		客2.customerName = "客２じゃ";
		customerService.insert(客2);
	}
}
