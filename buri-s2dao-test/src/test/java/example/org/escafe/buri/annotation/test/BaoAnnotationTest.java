/*
 * 作成日: 2005/11/29
 *
 */
package example.org.escafe.buri.annotation.test;

import java.util.Date;
import java.util.List;

import org.escafe.buri.annotation.bao.BillBao;
import org.escafe.buri.annotation.bao.OrderBao;
import org.escafe.buri.annotation.bao.ShippingBao;
import org.escafe.buri.annotation.bao.ShippingItemBao;
import org.escafe.buri.engine.BuriEngine;
import org.escafe.buri.service.BuriPathEntityService;
import org.escafe.buri.service.BuriStateEntityService;
import org.seasar.extension.unit.S2TestCase;

import example.org.escafe.buri.dao.CustomerDao;
import example.org.escafe.buri.dao.ItemDao;
import example.org.escafe.buri.dto.BillDto;
import example.org.escafe.buri.dto.CustomerDto;
import example.org.escafe.buri.dto.ItemDto;
import example.org.escafe.buri.dto.OrderDetailDto;
import example.org.escafe.buri.dto.OrderInfoDto;
import example.org.escafe.buri.dto.ShippingItemDto;

public class BaoAnnotationTest extends S2TestCase {
	private ItemDao itemDao;

	private CustomerDao customerDao;

	private BuriStateEntityService buriStateEntityService;

	private BuriPathEntityService buriPathEntityService;

	private BillBao billBao;

	private OrderBao orderBao;

	private ShippingBao shippingBao;

	private ShippingItemBao shippingItemBao;

	private CustomerDto 客1;

	private CustomerDto 客2;

	private ItemDto 商品1;

	private ItemDto 商品2;

	private ItemDto 商品3;

	private ItemDto 商品4;

	private ItemDto 商品5;

	public BaoAnnotationTest(String arg0) {
		super(arg0);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		include("baoAnnotationTest.dicon");
	}

	public void testNomalOrderTx() {
		// 本当はburi2.diconに書くもの
		BuriEngine buriEngine = (BuriEngine) getComponent("BuriSimpleEngine");
		buriEngine.readWorkFlowFromResource(
		    "wakanagoxpdl/orderBao.xpdl",
		    "注文管理");
		customerSetup();
		itemSetup();
		List datas = null;
		OrderInfoDto orderInfoDto1 = orderSetup1();
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
		ShippingItemDto shippingItemDto = (ShippingItemDto) datas.get(0);
		shippingItemBao.endShipping(shippingItemDto);
		datas = orderBao.getUnderWork();
		assertEquals(datas.size(), 1);
		datas = shippingItemBao.getItemWaiting();
		System.out.println(datas);
		assertEquals(datas.size(), 1);
		shippingItemDto = (ShippingItemDto) datas.get(0);
		datas = shippingItemBao.getEndShipping();
		System.out.println(datas);
		assertEquals(datas.size(), 1);
		datas = shippingBao.getNowWaiting();
		System.out.println(datas);
		assertEquals(datas.size(), 1);
		datas = shippingBao.getEndShipping();
		System.out.println(datas);
		assertEquals(datas.size(), 0);
		shippingItemBao.endShipping(shippingItemDto);
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
		BillDto billDto = (BillDto) datas.get(0);
		datas = billBao.getReBill();
		assertEquals(datas.size(), 0);
		datas = billBao.getEndBill();
		assertEquals(datas.size(), 0);
		billBao.bill(billDto);
		datas = billBao.getBillWaiting();
		assertEquals(datas.size(), 0);
		datas = billBao.getReBill();
		assertEquals(datas.size(), 1);
		billDto = (BillDto) datas.get(0);
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

	public void testCancelSuccessOrderTx() {
		// 本当はburi2.diconに書くもの
		BuriEngine buriEngine = (BuriEngine) getComponent("BuriSimpleEngine");
		buriEngine.readWorkFlowFromResource(
		    "wakanagoxpdl/orderBao.xpdl",
		    "注文管理");
		customerSetup();
		itemSetup();
		List datas = null;
		OrderInfoDto orderInfoDto1 = orderSetup1();
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
		Object status = orderBao.cancel(orderInfoDto1.getOrderTitleID());
		assertEquals(status.toString(), "success");
		datas = orderBao.getOrderCancelEnd();
		assertEquals(datas.size(), 1);
		datas = orderBao.getUnderWork();
		assertEquals(datas.size(), 0);
		datas = orderBao.getEndShipping();
		assertEquals(datas.size(), 0);
		datas = shippingItemBao.getCancel();
		System.out.println(datas);
		assertEquals(datas.size(), 2);
		datas = shippingItemBao.getItemWaiting();
		System.out.println(datas);
		assertEquals(datas.size(), 0);
		datas = shippingItemBao.getEndShipping();
		System.out.println(datas);
		assertEquals(datas.size(), 0);
		datas = shippingBao.getShippingCancel();
		System.out.println(datas);
		assertEquals(datas.size(), 1);
		datas = shippingBao.getNowWaiting();
		System.out.println(datas);
		assertEquals(datas.size(), 0);
		datas = shippingBao.getEndShipping();
		System.out.println(datas);
		assertEquals(datas.size(), 0);
	}

	public void testInvokeExceptionOrderTx() {
		// 本当はburi2.diconに書くもの
		BuriEngine buriEngine = (BuriEngine) getComponent("BuriSimpleEngine");
		buriEngine.readWorkFlowFromResource(
		    "wakanagoxpdl/orderBao.xpdl",
		    "注文管理");
		customerSetup();
		itemSetup();
		List datas = null;
		OrderInfoDto orderInfoDto1 = orderSetup1();
		orderBao.order(orderInfoDto1);
		try {
			orderBao.order(orderInfoDto1);
			fail();
		} catch (Exception ex) {
		}
		datas = orderBao.getUnderWork();
		assertEquals(datas.size(), 1);
		System.out.println(datas);
		datas = shippingBao.getNowWaiting();
		assertEquals(datas.size(), 1);
		System.out.println(datas);
		datas = shippingItemBao.getItemWaiting();
		assertEquals(datas.size(), 2);
		System.out.println(datas);
		Object status = orderBao.cancel(orderInfoDto1.getOrderTitleID());
		assertEquals(status.toString(), "success");
		datas = orderBao.getOrderCancelEnd();
		assertEquals(datas.size(), 1);
		datas = orderBao.getUnderWork();
		assertEquals(datas.size(), 0);
		datas = orderBao.getEndShipping();
		assertEquals(datas.size(), 0);
		datas = shippingItemBao.getCancel();
		System.out.println(datas);
		assertEquals(datas.size(), 2);
		datas = shippingItemBao.getItemWaiting();
		System.out.println(datas);
		assertEquals(datas.size(), 0);
		datas = shippingItemBao.getEndShipping();
		System.out.println(datas);
		assertEquals(datas.size(), 0);
		datas = shippingBao.getShippingCancel();
		System.out.println(datas);
		assertEquals(datas.size(), 1);
		datas = shippingBao.getNowWaiting();
		System.out.println(datas);
		assertEquals(datas.size(), 0);
		datas = shippingBao.getEndShipping();
		System.out.println(datas);
		assertEquals(datas.size(), 0);
	}

	protected OrderInfoDto orderSetup1() {
		OrderInfoDto dto = new OrderInfoDto();
		dto.setCustomerID(客1.getCustomerId());
		dto.setOrderDate(new Date());
		dto.setStatus(new Integer(0));
		OrderDetailDto detailDto = new OrderDetailDto();
		detailDto.setItemID(商品2.getItemID());
		detailDto.setOrderCount(1);
		dto.getOrderDetail().add(detailDto);
		detailDto = new OrderDetailDto();
		detailDto.setItemID(商品3.getItemID());
		detailDto.setOrderCount(2);
		dto.getOrderDetail().add(detailDto);
		return dto;
	}

	protected OrderInfoDto orderSetup2() {
		OrderInfoDto dto = new OrderInfoDto();
		dto.setCustomerID(客1.getCustomerId());
		dto.setOrderDate(new Date());
		dto.setStatus(new Integer(0));
		OrderDetailDto detailDto = new OrderDetailDto();
		detailDto.setItemID(商品4.getItemID());
		detailDto.setOrderCount(2);
		dto.getOrderDetail().add(detailDto);
		detailDto = new OrderDetailDto();
		detailDto.setItemID(商品5.getItemID());
		detailDto.setOrderCount(1);
		dto.getOrderDetail().add(detailDto);
		return dto;
	}

	protected OrderInfoDto orderSetup3() {
		OrderInfoDto dto = new OrderInfoDto();
		dto.setCustomerID(客2.getCustomerId());
		dto.setOrderDate(new Date());
		dto.setStatus(new Integer(0));
		OrderDetailDto detailDto = new OrderDetailDto();
		detailDto.setItemID(商品1.getItemID());
		detailDto.setOrderCount(5);
		dto.getOrderDetail().add(detailDto);
		return dto;
	}

	protected void itemSetup() {
		商品1 = new ItemDto();
		商品1.setItemName("PS1");
		商品1.setPrice(19800);
		itemDao.insert(商品1);
		商品2 = new ItemDto();
		商品2.setItemName("PS2");
		商品2.setPrice(19800);
		itemDao.insert(商品2);
		商品3 = new ItemDto();
		商品3.setItemName("PS3");
		商品3.setPrice(19800);
		itemDao.insert(商品3);
		商品4 = new ItemDto();
		商品4.setItemName("PS4");
		商品4.setPrice(19800);
		itemDao.insert(商品4);
		商品5 = new ItemDto();
		商品5.setItemName("PS5");
		商品5.setPrice(19800);
		itemDao.insert(商品5);
	}

	protected void customerSetup() {
		客1 = new CustomerDto();
		客1.setCustomerName("客１だよ");
		customerDao.insert(客1);
		客2 = new CustomerDto();
		客2.setCustomerName("客２じゃ");
		customerDao.insert(客2);
	}
}
