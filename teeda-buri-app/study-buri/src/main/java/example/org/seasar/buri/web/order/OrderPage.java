package example.org.seasar.buri.web.order;

import java.util.Date;
import java.util.List;

import example.org.seasar.buri.bao.OrderBao;
import example.org.seasar.buri.dao.CustomerDao;
import example.org.seasar.buri.dao.ItemDao;
import example.org.seasar.buri.dto.ItemDto;
import example.org.seasar.buri.dto.OrderDataDto;
import example.org.seasar.buri.dto.OrderDetailDto;
import example.org.seasar.buri.dto.OrderInfoDto;

public class OrderPage {

	private OrderBao orderBao;

	private OrderDataDto orderDataDto;

	private String customerName;

	private String itemName;

	private int orderIndex;

	private List orderItems;

	private long price;

	private CustomerDao customerDao;

	private ItemDao itemDao;

	public ItemDao getItemDao() {
		return this.itemDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getOrderIndex() {
		return this.orderIndex;
	}

	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}

	public List getOrderItems() {
		return this.orderItems;
	}

	public void setOrderItems(List orderItems) {
		this.orderItems = orderItems;
	}

	public long getPrice() {
		return this.price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	private OrderInfoDto createOrderInfo() {
		OrderInfoDto orderInfoDto = new OrderInfoDto();
		orderInfoDto.setCustomerID(orderDataDto.getCustomerId());
		orderInfoDto.setOrderDate(new Date());
		orderInfoDto.setStatus(new Integer(0));
		List items = itemDao.getItemByIds(orderDataDto.getItemIds());
		for (int i = 0; i < items.size(); i++) {
			OrderDetailDto orderDetailDto = new OrderDetailDto();
			ItemDto itemDto = (ItemDto) items.get(i);
			orderDetailDto.setItemID(itemDto.getItemID());
			orderDetailDto.setOrderCount(i + 1);
			orderInfoDto.getOrderDetail().add(orderDetailDto);
		}
		return orderInfoDto;
	}

	public Class doOrder() {
		orderBao.order(createOrderInfo());
		return OrderSuccessPage.class;
	}

	public String initialize() {
		customerName = customerDao.getCustomer(orderDataDto.getCustomerId()).getCustomerName();
		orderItems = itemDao.getItemByIds(orderDataDto.getItemIds());
		return null;
	}

	public String prerender() {
		return null;
	}

	public OrderDataDto getOrderDataDto() {
		return this.orderDataDto;
	}

	public void setOrderDataDto(OrderDataDto orderDataDto) {
		this.orderDataDto = orderDataDto;
	}

	public CustomerDao getCustomerDao() {
		return this.customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public OrderBao getOrderBao() {
		return this.orderBao;
	}

	public void setOrderBao(OrderBao orderBao) {
		this.orderBao = orderBao;
	}

}
