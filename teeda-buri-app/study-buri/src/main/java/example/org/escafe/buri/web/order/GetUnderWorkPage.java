package example.org.escafe.buri.web.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import example.org.escafe.buri.bao.OrderBao;
import example.org.escafe.buri.dao.CustomerDao;
import example.org.escafe.buri.dao.ItemDao;
import example.org.escafe.buri.dto.OrderDetailDto;
import example.org.escafe.buri.dto.OrderInfoDto;

public class GetUnderWorkPage {

	private OrderBao orderBao;

	private CustomerDao customerDao;

	private ItemDao itemDao;

	private String customerName;

	private String itemName;

	private Date orderDate;

	private int orderInfoIndex;

	private List orderInfoItems;

	private long orderTitleId;

	private long price;

	private Integer status;

	/**
	 * 
	 */
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

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderInfoIndex() {
		return this.orderInfoIndex;
	}

	public void setOrderInfoIndex(int orderInfoIndex) {
		this.orderInfoIndex = orderInfoIndex;
	}

	public List getOrderInfoItems() {
		return this.orderInfoItems;
	}

	public void setOrderInfoItems(List orderInfoItems) {
		this.orderInfoItems = orderInfoItems;
	}

	public long getOrderTitleId() {
		return this.orderTitleId;
	}

	public void setOrderTitleId(long orderTitleId) {
		this.orderTitleId = orderTitleId;
	}

	public long getPrice() {
		return this.price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String initialize() {
		return null;
	}

	public String prerender() {
		orderInfoItems = new ArrayList();
		List results = orderBao.getUnderWork();
		for (int i = 0; i < results.size(); i++) {
			OrderInfoDto dto = (OrderInfoDto) results.get(i);
			//
			OrderPageListDto listDto = new OrderPageListDto();
			listDto.setOrderTitleId(dto.getOrderTitleID());
			listDto.setOrderDate(dto.getOrderDate());
			listDto.setCustomerName(customerDao.getCustomer(dto.getCustomerID()).getCustomerName());
			listDto.setStatus(dto.getStatus());
			//
			orderInfoItems.add(listDto);
		}
		return null;
	}

	public OrderBao getOrderBao() {
		return this.orderBao;
	}

	public void setOrderBao(OrderBao orderBao) {
		this.orderBao = orderBao;
	}

	public CustomerDao getCustomerDao() {
		return this.customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public ItemDao getItemDao() {
		return this.itemDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

}
