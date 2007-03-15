package example.org.seasar.buri.web.shipping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import example.org.seasar.buri.bao.ShippingBao;
import example.org.seasar.buri.dao.CustomerDao;
import example.org.seasar.buri.dao.ItemDao;
import example.org.seasar.buri.dao.OrderInfoDao;
import example.org.seasar.buri.dao.ShippingSetDao;
import example.org.seasar.buri.dto.ItemDto;
import example.org.seasar.buri.dto.OrderDetailDto;
import example.org.seasar.buri.dto.OrderInfoDto;
import example.org.seasar.buri.dto.ShippingSetDto;

public class GetNowWatingPage {

	private ShippingBao shippingBao;

	private CustomerDao customerDao;

	private String customerName;

	private Date shippingDate;

	private long shippingId;

	private long orderTitleId;

	private int shippingSetIndex;

	private List shippingSetItems;

	private Date orderDate;

	private String itemName;

	private OrderInfoDao orderInfoDao;

	private ItemDao itemDao;

	/**
	 * 
	 */
	private ShippingSetDao shippingSetDao;

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getShippingDate() {
		return this.shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public long getShippingId() {
		return this.shippingId;
	}

	public void setShippingId(long shippingId) {
		this.shippingId = shippingId;
	}

	public int getShippingSetIndex() {
		return this.shippingSetIndex;
	}

	public void setShippingSetIndex(int shippingSetIndex) {
		this.shippingSetIndex = shippingSetIndex;
	}

	public List getShippingSetItems() {
		return this.shippingSetItems;
	}

	public void setShippingSetItems(List shippingSetItems) {
		this.shippingSetItems = shippingSetItems;
	}

	public String initialize() {
		return null;
	}

	/**
	 * @return the shippingSetDao
	 */
	public ShippingSetDao getShippingSetDao() {
		return this.shippingSetDao;
	}

	/**
	 * @param shippingSetDao
	 *            the shippingSetDao to set
	 */
	public void setShippingSetDao(ShippingSetDao shippingSetDao) {
		this.shippingSetDao = shippingSetDao;
	}

	public String prerender() {
		shippingSetItems = new ArrayList();
		List results = shippingBao.getNowWaiting();
		for (int i = 0; i < results.size(); i++) {
			ShippingSetDto dto = (ShippingSetDto) results.get(i);
			OrderInfoDto orderInfoDto = orderInfoDao.getOrderInfo(dto.getOrderTitleID());
			for (int j = 0; j < orderInfoDto.getOrderDetail().size(); j++) {
				ShippingPageListDto listDto = new ShippingPageListDto();
				//
				listDto.setShippingId(dto.getShippingID());
				listDto.setShippingDate(dto.getShippingDate());
				listDto.setCustomerName(customerDao.getCustomer(dto.getCustomerID()).getCustomerName());
				listDto.setOrderTitleId(dto.getOrderTitleID());
				listDto.setOrderDate(orderInfoDto.getOrderDate());
				//
				OrderDetailDto orderDetailDto = (OrderDetailDto) orderInfoDto.getOrderDetail().get(j);
				ItemDto itemDto = itemDao.getItem(orderDetailDto.getItemID());
				listDto.setItemName(itemDto.getItemName());
				this.shippingSetItems.add(listDto);
			}
		}
		return null;
	}

	public ShippingBao getShippingBao() {
		return this.shippingBao;
	}

	public void setShippingBao(ShippingBao shippingBao) {
		this.shippingBao = shippingBao;
	}

	public CustomerDao getCustomerDao() {
		return this.customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public long getOrderTitleId() {
		return this.orderTitleId;
	}

	public void setOrderTitleId(long orderTitleId) {
		this.orderTitleId = orderTitleId;
	}

	public ItemDao getItemDao() {
		return this.itemDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public OrderInfoDao getOrderInfoDao() {
		return this.orderInfoDao;
	}

	public void setOrderInfoDao(OrderInfoDao orderInfoDao) {
		this.orderInfoDao = orderInfoDao;
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

}
