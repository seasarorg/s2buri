package example.org.escafe.buri.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.escafe.buri.common.util.ScriptProcessor;

public class CustomerFindDto {
	public static final String TABLE = "CUSTOMER";
	private final ArrayList orderList = new ArrayList();

	private Long customerId = null;
	private Long customerId_not = null;
	private Long customerId_large = null;
	private Long customerId_moreLarge = null;
	private Long customerId_from = null;
	private Long customerId_to = null;
	private Long customerId_moreSmall = null;
	private Long customerId_small = null;
	private List customerId_in = null;
	private Boolean customerId_isNull = null;
	private Boolean customerId_isNotNull = null;
	private boolean customerId_isASC = true;
	private String customerName = null;
	private String customerName_not = null;
	private String customerName_large = null;
	private String customerName_moreLarge = null;
	private String customerName_from = null;
	private String customerName_to = null;
	private String customerName_moreSmall = null;
	private String customerName_small = null;
	private String customerName_matchFull = null;
	private String customerName_matchFront = null;
	private String customerName_matchBack = null;
	private List customerName_in = null;
	private Boolean customerName_isNull = null;
	private Boolean customerName_isNotNull = null;
	private boolean customerName_isASC = true;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getCustomerId_not() {
		return customerId_not;
	}

	public void setCustomerId_not(Long customerId_not) {
		this.customerId_not = customerId_not;
	}

	public Long getCustomerId_large() {
		return customerId_large;
	}

	public void setCustomerId_large(Long customerId_large) {
		this.customerId_large = customerId_large;
	}

	public Long getCustomerId_moreLarge() {
		return customerId_moreLarge;
	}

	public void setCustomerId_moreLarge(Long customerId_moreLarge) {
		this.customerId_moreLarge = customerId_moreLarge;
	}

	public Long getCustomerId_from() {
		return customerId_from;
	}

	public void setCustomerId_from(Long customerId_from) {
		this.customerId_from = customerId_from;
	}

	public Long getCustomerId_to() {
		return customerId_to;
	}

	public void setCustomerId_to(Long customerId_to) {
		this.customerId_to = customerId_to;
	}

	public Long getCustomerId_moreSmall() {
		return customerId_moreSmall;
	}

	public void setCustomerId_moreSmall(Long customerId_moreSmall) {
		this.customerId_moreSmall = customerId_moreSmall;
	}

	public Long getCustomerId_small() {
		return customerId_small;
	}

	public void setCustomerId_small(Long customerId_small) {
		this.customerId_small = customerId_small;
	}

	public List getCustomerId_in() {
		return customerId_in;
	}

	public void setCustomerId_in(List customerId_in) {
		this.customerId_in = customerId_in;
	}

	public Boolean getCustomerId_isNull() {
		return customerId_isNull;
	}

	public void setCustomerId_isNull(Boolean customerId_isNull) {
		this.customerId_isNull = customerId_isNull;
	}

	public Boolean getCustomerId_isNotNull() {
		return customerId_isNotNull;
	}

	public void setCustomerId_isNotNull(Boolean customerId_isNotNull) {
		this.customerId_isNotNull = customerId_isNotNull;
	}

	public boolean getCustomerId_isASC() {
		return customerId_isASC;
	}

	public void setCustomerId_isASC(boolean customerId_isASC) {
		this.customerId_isASC = customerId_isASC;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerName_not() {
		return customerName_not;
	}

	public void setCustomerName_not(String customerName_not) {
		this.customerName_not = customerName_not;
	}

	public String getCustomerName_large() {
		return customerName_large;
	}

	public void setCustomerName_large(String customerName_large) {
		this.customerName_large = customerName_large;
	}

	public String getCustomerName_moreLarge() {
		return customerName_moreLarge;
	}

	public void setCustomerName_moreLarge(String customerName_moreLarge) {
		this.customerName_moreLarge = customerName_moreLarge;
	}

	public String getCustomerName_from() {
		return customerName_from;
	}

	public void setCustomerName_from(String customerName_from) {
		this.customerName_from = customerName_from;
	}

	public String getCustomerName_to() {
		return customerName_to;
	}

	public void setCustomerName_to(String customerName_to) {
		this.customerName_to = customerName_to;
	}

	public String getCustomerName_moreSmall() {
		return customerName_moreSmall;
	}

	public void setCustomerName_moreSmall(String customerName_moreSmall) {
		this.customerName_moreSmall = customerName_moreSmall;
	}

	public String getCustomerName_small() {
		return customerName_small;
	}

	public void setCustomerName_small(String customerName_small) {
		this.customerName_small = customerName_small;
	}

	public String getCustomerName_matchFull() {
		if (customerName_matchFull == null) {
			return null;
		}
		return "%" + customerName_matchFull + "%";
	}

	public void setCustomerName_matchFull(String customerName_matchFull) {
		this.customerName_matchFull = customerName_matchFull;
	}

	public String getCustomerName_matchFront() {
		if (customerName_matchFront == null) {
			return null;
		}
		return customerName_matchFront + "%";
	}

	public void setCustomerName_matchFront(String customerName_matchFront) {
		this.customerName_matchFront = customerName_matchFront;
	}

	public String getCustomerName_matchBack() {
		if (customerName_matchBack == null) {
			return null;
		}
		return "%" + customerName_matchBack;
	}

	public void setCustomerName_matchBack(String customerName_matchBack) {
		this.customerName_matchBack = customerName_matchBack;
	}

	public List getCustomerName_in() {
		return customerName_in;
	}

	public void setCustomerName_in(List customerName_in) {
		this.customerName_in = customerName_in;
	}

	public Boolean getCustomerName_isNull() {
		return customerName_isNull;
	}

	public void setCustomerName_isNull(Boolean customerName_isNull) {
		this.customerName_isNull = customerName_isNull;
	}

	public Boolean getCustomerName_isNotNull() {
		return customerName_isNotNull;
	}

	public void setCustomerName_isNotNull(Boolean customerName_isNotNull) {
		this.customerName_isNotNull = customerName_isNotNull;
	}

	public boolean getCustomerName_isASC() {
		return customerName_isASC;
	}

	public void setCustomerName_isASC(boolean customerName_isASC) {
		this.customerName_isASC = customerName_isASC;
	}

	public void addOrderList(String order) {
		orderList.add(order);
	}

	public void addOrderList(String order, boolean isAsc) {
		orderList.add(order);
		ScriptProcessor processor = new ScriptProcessor();
		processor.setValue(order.replace('.', '_') + "_isASC", this,
				new Boolean(isAsc));
	}

	public String getOrderList() {
		String order = "";
		String ORDER = "ORDER BY ";
		Iterator ite = orderList.iterator();
		ScriptProcessor processor = new ScriptProcessor();
		while (ite.hasNext()) {
			String orderTgt = (String) ite.next();
			order = ORDER + order + orderTgt.replace('_', '.') + " ";
			Boolean var = (Boolean) processor.getValue(orderTgt + "_isASC",
					this);
			if (!var.booleanValue()) {
				order = order + "DESC ";
			}
			ORDER = "";
		}
		return order;
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/customerId=").append(customerId);
		buff.append("/customerId_not=").append(customerId_not);
		buff.append("/customerId_large=").append(customerId_large);
		buff.append("/customerId_moreLarge=").append(customerId_moreLarge);
		buff.append("/customerId_from=").append(customerId_from);
		buff.append("/customerId_to=").append(customerId_to);
		buff.append("/customerId_moreSmall=").append(customerId_moreSmall);
		buff.append("/customerId_small=").append(customerId_small);
		buff.append("/customerId_in=").append(customerId_in);
		buff.append("/customerId_isNull=").append(customerId_isNull);
		buff.append("/customerId_isNotNull=").append(customerId_isNotNull);
		buff.append("/customerId_isASC=").append(customerId_isASC);
		buff.append("/customerName=").append(customerName);
		buff.append("/customerName_not=").append(customerName_not);
		buff.append("/customerName_large=").append(customerName_large);
		buff.append("/customerName_moreLarge=").append(customerName_moreLarge);
		buff.append("/customerName_from=").append(customerName_from);
		buff.append("/customerName_to=").append(customerName_to);
		buff.append("/customerName_moreSmall=").append(customerName_moreSmall);
		buff.append("/customerName_small=").append(customerName_small);
		buff.append("/customerName_in=").append(customerName_in);
		buff.append("/customerName_isNull=").append(customerName_isNull);
		buff.append("/customerName_isNotNull=").append(customerName_isNotNull);
		buff.append("/customerName_isASC=").append(customerName_isASC);
		buff.append("]");
		return buff.toString();
	}

}
