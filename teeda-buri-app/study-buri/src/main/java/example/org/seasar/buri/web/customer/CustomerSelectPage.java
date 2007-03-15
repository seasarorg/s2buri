package example.org.seasar.buri.web.customer;

import java.util.List;

import example.org.seasar.buri.dao.CustomerDao;
import example.org.seasar.buri.dto.OrderDataDto;
import example.org.seasar.buri.web.item.ItemSelectPage;

public class CustomerSelectPage {

	private OrderDataDto orderDataDto;

	private long clickCustomerId;

	private long customerId;

	private int customerIndex;

	private List customerItems;

	private String customerName;

	/**
	 * 
	 */
	private CustomerDao customerDao;

	public long getClickCustomerId() {
		return this.clickCustomerId;
	}

	public void setClickCustomerId(long clickCustomerId) {
		this.clickCustomerId = clickCustomerId;
	}

	public long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public int getCustomerIndex() {
		return this.customerIndex;
	}

	public void setCustomerIndex(int customerIndex) {
		this.customerIndex = customerIndex;
	}

	public List getCustomerItems() {
		return this.customerItems;
	}

	public void setCustomerItems(List customerItems) {
		this.customerItems = customerItems;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Class doCustomerSelect() {
		System.out.println("選択した顧客ID:=" + clickCustomerId);
		// Sessionに選択した顧客IDを保管する。
		orderDataDto.setCustomerId(clickCustomerId);
		return ItemSelectPage.class;
	}

	public String initialize() {
		return null;
	}

	/**
	 * @return the customerDao
	 */
	public CustomerDao getCustomerDao() {
		return this.customerDao;
	}

	/**
	 * @param customerDao
	 *            the customerDao to set
	 */
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public String prerender() {
		this.setCustomerItems(getCustomerDao().getAllCustomer());
		return null;
	}

	public OrderDataDto getOrderDataDto() {
		return this.orderDataDto;
	}

	public void setOrderDataDto(OrderDataDto orderDataDto) {
		this.orderDataDto = orderDataDto;
	}

}
