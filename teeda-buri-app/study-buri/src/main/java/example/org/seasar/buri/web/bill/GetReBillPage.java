package example.org.seasar.buri.web.bill;

import java.util.Date;
import java.util.List;

import example.org.seasar.buri.bao.BillBao;
import example.org.seasar.buri.dao.BillDao;

public class GetReBillPage {

	private BillBao billBao;

	private Date billDate;

	private long billId;

	private int billIndex;

	private List billItems;

	private long customerID;

	private long orderTitleID;

	private long shippingID;

	private long clickBillId;

	/**
	 * 
	 */
	private BillDao billDao;

	public Date getBillDate() {
		return this.billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public long getBillId() {
		return this.billId;
	}

	public void setBillId(long billId) {
		this.billId = billId;
	}

	public int getBillIndex() {
		return this.billIndex;
	}

	public void setBillIndex(int billIndex) {
		this.billIndex = billIndex;
	}

	public List getBillItems() {
		return this.billItems;
	}

	public void setBillItems(List billItems) {
		this.billItems = billItems;
	}

	public long getCustomerID() {
		return this.customerID;
	}

	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}

	public long getOrderTitleID() {
		return this.orderTitleID;
	}

	public void setOrderTitleID(long orderTitleID) {
		this.orderTitleID = orderTitleID;
	}

	public long getShippingID() {
		return this.shippingID;
	}

	public void setShippingID(long shippingID) {
		this.shippingID = shippingID;
	}

	public String doCheckPayment() {
		System.out.println("clickBillId:=" + clickBillId);
		 billBao.checkPayment(billDao.getBill(clickBillId));
		return null;
	}

	public String initialize() {
		return null;
	}

	/**
	 * @return the billDao
	 */
	public BillDao getBillDao() {
		return this.billDao;
	}

	/**
	 * @param billDao
	 *            the billDao to set
	 */
	public void setBillDao(BillDao billDao) {
		this.billDao = billDao;
	}

	public String prerender() {
		this.setBillItems(billBao.getReBill());
		return null;
	}

	public BillBao getBillBao() {
		return this.billBao;
	}

	public void setBillBao(BillBao billBao) {
		this.billBao = billBao;
	}

	public long getClickBillId() {
		return this.clickBillId;
	}

	public void setClickBillId(long clickBillId) {
		this.clickBillId = clickBillId;
	}

}
