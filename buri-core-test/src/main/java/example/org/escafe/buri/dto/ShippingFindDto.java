package example.org.escafe.buri.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.escafe.buri.common.util.ScriptProcessor;

public class ShippingFindDto {
	private final ArrayList orderList = new ArrayList();

	private Long shippingId = null;

	private Long shippingId_not = null;

	private Long shippingId_large = null;

	private Long shippingId_moreLarge = null;

	private Long shippingId_from = null;

	private Long shippingId_to = null;

	private Long shippingId_moreSmall = null;

	private Long shippingId_small = null;

	private List shippingId_in = null;

	private Boolean shippingId_isNull = null;

	private Boolean shippingId_isNotNull = null;

	private boolean shippingId_isASC = true;

	private Timestamp shippingDate = null;

	private Timestamp shippingDate_not = null;

	private Timestamp shippingDate_large = null;

	private Timestamp shippingDate_moreLarge = null;

	private Timestamp shippingDate_from = null;

	private Timestamp shippingDate_to = null;

	private Timestamp shippingDate_moreSmall = null;

	private Timestamp shippingDate_small = null;

	private List shippingDate_in = null;

	private Boolean shippingDate_isNull = null;

	private Boolean shippingDate_isNotNull = null;

	private boolean shippingDate_isASC = true;

	private Long orderTitleId = null;

	private Long orderTitleId_not = null;

	private Long orderTitleId_large = null;

	private Long orderTitleId_moreLarge = null;

	private Long orderTitleId_from = null;

	private Long orderTitleId_to = null;

	private Long orderTitleId_moreSmall = null;

	private Long orderTitleId_small = null;

	private List orderTitleId_in = null;

	private Boolean orderTitleId_isNull = null;

	private Boolean orderTitleId_isNotNull = null;

	private boolean orderTitleId_isASC = true;

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

	public Long getShippingId() {
		return shippingId;
	}

	public void setShippingId(Long shippingId) {
		this.shippingId = shippingId;
	}

	public Long getShippingId_not() {
		return shippingId_not;
	}

	public void setShippingId_not(Long shippingId_not) {
		this.shippingId_not = shippingId_not;
	}

	public Long getShippingId_large() {
		return shippingId_large;
	}

	public void setShippingId_large(Long shippingId_large) {
		this.shippingId_large = shippingId_large;
	}

	public Long getShippingId_moreLarge() {
		return shippingId_moreLarge;
	}

	public void setShippingId_moreLarge(Long shippingId_moreLarge) {
		this.shippingId_moreLarge = shippingId_moreLarge;
	}

	public Long getShippingId_from() {
		return shippingId_from;
	}

	public void setShippingId_from(Long shippingId_from) {
		this.shippingId_from = shippingId_from;
	}

	public Long getShippingId_to() {
		return shippingId_to;
	}

	public void setShippingId_to(Long shippingId_to) {
		this.shippingId_to = shippingId_to;
	}

	public Long getShippingId_moreSmall() {
		return shippingId_moreSmall;
	}

	public void setShippingId_moreSmall(Long shippingId_moreSmall) {
		this.shippingId_moreSmall = shippingId_moreSmall;
	}

	public Long getShippingId_small() {
		return shippingId_small;
	}

	public void setShippingId_small(Long shippingId_small) {
		this.shippingId_small = shippingId_small;
	}

	public List getShippingId_in() {
		return shippingId_in;
	}

	public void setShippingId_in(List shippingId_in) {
		this.shippingId_in = shippingId_in;
	}

	public Boolean getShippingId_isNull() {
		return shippingId_isNull;
	}

	public void setShippingId_isNull(Boolean shippingId_isNull) {
		this.shippingId_isNull = shippingId_isNull;
	}

	public Boolean getShippingId_isNotNull() {
		return shippingId_isNotNull;
	}

	public void setShippingId_isNotNull(Boolean shippingId_isNotNull) {
		this.shippingId_isNotNull = shippingId_isNotNull;
	}

	public boolean getShippingId_isASC() {
		return shippingId_isASC;
	}

	public void setShippingId_isASC(boolean shippingId_isASC) {
		this.shippingId_isASC = shippingId_isASC;
	}

	public Timestamp getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Timestamp shippingDate) {
		this.shippingDate = shippingDate;
	}

	public Timestamp getShippingDate_not() {
		return shippingDate_not;
	}

	public void setShippingDate_not(Timestamp shippingDate_not) {
		this.shippingDate_not = shippingDate_not;
	}

	public Timestamp getShippingDate_large() {
		return shippingDate_large;
	}

	public void setShippingDate_large(Timestamp shippingDate_large) {
		this.shippingDate_large = shippingDate_large;
	}

	public Timestamp getShippingDate_moreLarge() {
		return shippingDate_moreLarge;
	}

	public void setShippingDate_moreLarge(Timestamp shippingDate_moreLarge) {
		this.shippingDate_moreLarge = shippingDate_moreLarge;
	}

	public Timestamp getShippingDate_from() {
		return shippingDate_from;
	}

	public void setShippingDate_from(Timestamp shippingDate_from) {
		this.shippingDate_from = shippingDate_from;
	}

	public Timestamp getShippingDate_to() {
		return shippingDate_to;
	}

	public void setShippingDate_to(Timestamp shippingDate_to) {
		this.shippingDate_to = shippingDate_to;
	}

	public Timestamp getShippingDate_moreSmall() {
		return shippingDate_moreSmall;
	}

	public void setShippingDate_moreSmall(Timestamp shippingDate_moreSmall) {
		this.shippingDate_moreSmall = shippingDate_moreSmall;
	}

	public Timestamp getShippingDate_small() {
		return shippingDate_small;
	}

	public void setShippingDate_small(Timestamp shippingDate_small) {
		this.shippingDate_small = shippingDate_small;
	}

	public List getShippingDate_in() {
		return shippingDate_in;
	}

	public void setShippingDate_in(List shippingDate_in) {
		this.shippingDate_in = shippingDate_in;
	}

	public Boolean getShippingDate_isNull() {
		return shippingDate_isNull;
	}

	public void setShippingDate_isNull(Boolean shippingDate_isNull) {
		this.shippingDate_isNull = shippingDate_isNull;
	}

	public Boolean getShippingDate_isNotNull() {
		return shippingDate_isNotNull;
	}

	public void setShippingDate_isNotNull(Boolean shippingDate_isNotNull) {
		this.shippingDate_isNotNull = shippingDate_isNotNull;
	}

	public boolean getShippingDate_isASC() {
		return shippingDate_isASC;
	}

	public void setShippingDate_isASC(boolean shippingDate_isASC) {
		this.shippingDate_isASC = shippingDate_isASC;
	}

	public Long getOrderTitleId() {
		return orderTitleId;
	}

	public void setOrderTitleId(Long orderTitleId) {
		this.orderTitleId = orderTitleId;
	}

	public Long getOrderTitleId_not() {
		return orderTitleId_not;
	}

	public void setOrderTitleId_not(Long orderTitleId_not) {
		this.orderTitleId_not = orderTitleId_not;
	}

	public Long getOrderTitleId_large() {
		return orderTitleId_large;
	}

	public void setOrderTitleId_large(Long orderTitleId_large) {
		this.orderTitleId_large = orderTitleId_large;
	}

	public Long getOrderTitleId_moreLarge() {
		return orderTitleId_moreLarge;
	}

	public void setOrderTitleId_moreLarge(Long orderTitleId_moreLarge) {
		this.orderTitleId_moreLarge = orderTitleId_moreLarge;
	}

	public Long getOrderTitleId_from() {
		return orderTitleId_from;
	}

	public void setOrderTitleId_from(Long orderTitleId_from) {
		this.orderTitleId_from = orderTitleId_from;
	}

	public Long getOrderTitleId_to() {
		return orderTitleId_to;
	}

	public void setOrderTitleId_to(Long orderTitleId_to) {
		this.orderTitleId_to = orderTitleId_to;
	}

	public Long getOrderTitleId_moreSmall() {
		return orderTitleId_moreSmall;
	}

	public void setOrderTitleId_moreSmall(Long orderTitleId_moreSmall) {
		this.orderTitleId_moreSmall = orderTitleId_moreSmall;
	}

	public Long getOrderTitleId_small() {
		return orderTitleId_small;
	}

	public void setOrderTitleId_small(Long orderTitleId_small) {
		this.orderTitleId_small = orderTitleId_small;
	}

	public List getOrderTitleId_in() {
		return orderTitleId_in;
	}

	public void setOrderTitleId_in(List orderTitleId_in) {
		this.orderTitleId_in = orderTitleId_in;
	}

	public Boolean getOrderTitleId_isNull() {
		return orderTitleId_isNull;
	}

	public void setOrderTitleId_isNull(Boolean orderTitleId_isNull) {
		this.orderTitleId_isNull = orderTitleId_isNull;
	}

	public Boolean getOrderTitleId_isNotNull() {
		return orderTitleId_isNotNull;
	}

	public void setOrderTitleId_isNotNull(Boolean orderTitleId_isNotNull) {
		this.orderTitleId_isNotNull = orderTitleId_isNotNull;
	}

	public boolean getOrderTitleId_isASC() {
		return orderTitleId_isASC;
	}

	public void setOrderTitleId_isASC(boolean orderTitleId_isASC) {
		this.orderTitleId_isASC = orderTitleId_isASC;
	}

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

	public void addOrderList(String order) {
		orderList.add(order);
	}

	public void addOrderList(String order, boolean isAsc) {
		orderList.add(order);
		ScriptProcessor processor = new ScriptProcessor();
		processor.setValue(
		    order.replace('.', '_') + "_isASC",
		    this,
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
			Boolean var =
			    (Boolean) processor.getValue(orderTgt + "_isASC", this);
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
		buff.append("/shippingId=").append(shippingId);
		buff.append("/shippingId_not=").append(shippingId_not);
		buff.append("/shippingId_large=").append(shippingId_large);
		buff.append("/shippingId_moreLarge=").append(shippingId_moreLarge);
		buff.append("/shippingId_from=").append(shippingId_from);
		buff.append("/shippingId_to=").append(shippingId_to);
		buff.append("/shippingId_moreSmall=").append(shippingId_moreSmall);
		buff.append("/shippingId_small=").append(shippingId_small);
		buff.append("/shippingId_in=").append(shippingId_in);
		buff.append("/shippingId_isNull=").append(shippingId_isNull);
		buff.append("/shippingId_isNotNull=").append(shippingId_isNotNull);
		buff.append("/shippingId_isASC=").append(shippingId_isASC);
		buff.append("/shippingDate=").append(shippingDate);
		buff.append("/shippingDate_not=").append(shippingDate_not);
		buff.append("/shippingDate_large=").append(shippingDate_large);
		buff.append("/shippingDate_moreLarge=").append(shippingDate_moreLarge);
		buff.append("/shippingDate_from=").append(shippingDate_from);
		buff.append("/shippingDate_to=").append(shippingDate_to);
		buff.append("/shippingDate_moreSmall=").append(shippingDate_moreSmall);
		buff.append("/shippingDate_small=").append(shippingDate_small);
		buff.append("/shippingDate_in=").append(shippingDate_in);
		buff.append("/shippingDate_isNull=").append(shippingDate_isNull);
		buff.append("/shippingDate_isNotNull=").append(shippingDate_isNotNull);
		buff.append("/shippingDate_isASC=").append(shippingDate_isASC);
		buff.append("/orderTitleId=").append(orderTitleId);
		buff.append("/orderTitleId_not=").append(orderTitleId_not);
		buff.append("/orderTitleId_large=").append(orderTitleId_large);
		buff.append("/orderTitleId_moreLarge=").append(orderTitleId_moreLarge);
		buff.append("/orderTitleId_from=").append(orderTitleId_from);
		buff.append("/orderTitleId_to=").append(orderTitleId_to);
		buff.append("/orderTitleId_moreSmall=").append(orderTitleId_moreSmall);
		buff.append("/orderTitleId_small=").append(orderTitleId_small);
		buff.append("/orderTitleId_in=").append(orderTitleId_in);
		buff.append("/orderTitleId_isNull=").append(orderTitleId_isNull);
		buff.append("/orderTitleId_isNotNull=").append(orderTitleId_isNotNull);
		buff.append("/orderTitleId_isASC=").append(orderTitleId_isASC);
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
		buff.append("]");
		return buff.toString();
	}
}
