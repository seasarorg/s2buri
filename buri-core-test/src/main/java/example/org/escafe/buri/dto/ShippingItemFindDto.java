package example.org.escafe.buri.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.escafe.buri.common.util.ScriptProcessor;

public class ShippingItemFindDto {
	private final List<String> orderList = new ArrayList<String>();

	private Long orderDetailId = null;

	private Long orderDetailId_not = null;

	private Long orderDetailId_large = null;

	private Long orderDetailId_moreLarge = null;

	private Long orderDetailId_from = null;

	private Long orderDetailId_to = null;

	private Long orderDetailId_moreSmall = null;

	private Long orderDetailId_small = null;

	private List<Long> orderDetailId_in = null;

	private Boolean orderDetailId_isNull = null;

	private Boolean orderDetailId_isNotNull = null;

	private boolean orderDetailId_isASC = true;

	private Long shippingId = null;

	private Long shippingId_not = null;

	private Long shippingId_large = null;

	private Long shippingId_moreLarge = null;

	private Long shippingId_from = null;

	private Long shippingId_to = null;

	private Long shippingId_moreSmall = null;

	private Long shippingId_small = null;

	private List<Long> shippingId_in = null;

	private Boolean shippingId_isNull = null;

	private Boolean shippingId_isNotNull = null;

	private boolean shippingId_isASC = true;

	private Long shippingItemId = null;

	private Long shippingItemId_not = null;

	private Long shippingItemId_large = null;

	private Long shippingItemId_moreLarge = null;

	private Long shippingItemId_from = null;

	private Long shippingItemId_to = null;

	private Long shippingItemId_moreSmall = null;

	private Long shippingItemId_small = null;

	private List<Long> shippingItemId_in = null;

	private Boolean shippingItemId_isNull = null;

	private Boolean shippingItemId_isNotNull = null;

	private boolean shippingItemId_isASC = true;

	public Long getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public Long getOrderDetailId_not() {
		return orderDetailId_not;
	}

	public void setOrderDetailId_not(Long orderDetailId_not) {
		this.orderDetailId_not = orderDetailId_not;
	}

	public Long getOrderDetailId_large() {
		return orderDetailId_large;
	}

	public void setOrderDetailId_large(Long orderDetailId_large) {
		this.orderDetailId_large = orderDetailId_large;
	}

	public Long getOrderDetailId_moreLarge() {
		return orderDetailId_moreLarge;
	}

	public void setOrderDetailId_moreLarge(Long orderDetailId_moreLarge) {
		this.orderDetailId_moreLarge = orderDetailId_moreLarge;
	}

	public Long getOrderDetailId_from() {
		return orderDetailId_from;
	}

	public void setOrderDetailId_from(Long orderDetailId_from) {
		this.orderDetailId_from = orderDetailId_from;
	}

	public Long getOrderDetailId_to() {
		return orderDetailId_to;
	}

	public void setOrderDetailId_to(Long orderDetailId_to) {
		this.orderDetailId_to = orderDetailId_to;
	}

	public Long getOrderDetailId_moreSmall() {
		return orderDetailId_moreSmall;
	}

	public void setOrderDetailId_moreSmall(Long orderDetailId_moreSmall) {
		this.orderDetailId_moreSmall = orderDetailId_moreSmall;
	}

	public Long getOrderDetailId_small() {
		return orderDetailId_small;
	}

	public void setOrderDetailId_small(Long orderDetailId_small) {
		this.orderDetailId_small = orderDetailId_small;
	}

	public List<Long> getOrderDetailId_in() {
		return orderDetailId_in;
	}

	public void setOrderDetailId_in(List<Long> orderDetailId_in) {
		this.orderDetailId_in = orderDetailId_in;
	}

	public Boolean getOrderDetailId_isNull() {
		return orderDetailId_isNull;
	}

	public void setOrderDetailId_isNull(Boolean orderDetailId_isNull) {
		this.orderDetailId_isNull = orderDetailId_isNull;
	}

	public Boolean getOrderDetailId_isNotNull() {
		return orderDetailId_isNotNull;
	}

	public void setOrderDetailId_isNotNull(Boolean orderDetailId_isNotNull) {
		this.orderDetailId_isNotNull = orderDetailId_isNotNull;
	}

	public boolean getOrderDetailId_isASC() {
		return orderDetailId_isASC;
	}

	public void setOrderDetailId_isASC(boolean orderDetailId_isASC) {
		this.orderDetailId_isASC = orderDetailId_isASC;
	}

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

	public List<Long> getShippingId_in() {
		return shippingId_in;
	}

	public void setShippingId_in(List<Long> shippingId_in) {
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

	public Long getShippingItemId() {
		return shippingItemId;
	}

	public void setShippingItemId(Long shippingItemId) {
		this.shippingItemId = shippingItemId;
	}

	public Long getShippingItemId_not() {
		return shippingItemId_not;
	}

	public void setShippingItemId_not(Long shippingItemId_not) {
		this.shippingItemId_not = shippingItemId_not;
	}

	public Long getShippingItemId_large() {
		return shippingItemId_large;
	}

	public void setShippingItemId_large(Long shippingItemId_large) {
		this.shippingItemId_large = shippingItemId_large;
	}

	public Long getShippingItemId_moreLarge() {
		return shippingItemId_moreLarge;
	}

	public void setShippingItemId_moreLarge(Long shippingItemId_moreLarge) {
		this.shippingItemId_moreLarge = shippingItemId_moreLarge;
	}

	public Long getShippingItemId_from() {
		return shippingItemId_from;
	}

	public void setShippingItemId_from(Long shippingItemId_from) {
		this.shippingItemId_from = shippingItemId_from;
	}

	public Long getShippingItemId_to() {
		return shippingItemId_to;
	}

	public void setShippingItemId_to(Long shippingItemId_to) {
		this.shippingItemId_to = shippingItemId_to;
	}

	public Long getShippingItemId_moreSmall() {
		return shippingItemId_moreSmall;
	}

	public void setShippingItemId_moreSmall(Long shippingItemId_moreSmall) {
		this.shippingItemId_moreSmall = shippingItemId_moreSmall;
	}

	public Long getShippingItemId_small() {
		return shippingItemId_small;
	}

	public void setShippingItemId_small(Long shippingItemId_small) {
		this.shippingItemId_small = shippingItemId_small;
	}

	public List<Long> getShippingItemId_in() {
		return shippingItemId_in;
	}

	public void setShippingItemId_in(List<Long> shippingItemId_in) {
		this.shippingItemId_in = shippingItemId_in;
	}

	public Boolean getShippingItemId_isNull() {
		return shippingItemId_isNull;
	}

	public void setShippingItemId_isNull(Boolean shippingItemId_isNull) {
		this.shippingItemId_isNull = shippingItemId_isNull;
	}

	public Boolean getShippingItemId_isNotNull() {
		return shippingItemId_isNotNull;
	}

	public void setShippingItemId_isNotNull(Boolean shippingItemId_isNotNull) {
		this.shippingItemId_isNotNull = shippingItemId_isNotNull;
	}

	public boolean getShippingItemId_isASC() {
		return shippingItemId_isASC;
	}

	public void setShippingItemId_isASC(boolean shippingItemId_isASC) {
		this.shippingItemId_isASC = shippingItemId_isASC;
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
		Iterator<String> ite = orderList.iterator();
		ScriptProcessor processor = new ScriptProcessor();
		while (ite.hasNext()) {
			String orderTgt = ite.next();
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
		buff.append("/orderDetailId=").append(orderDetailId);
		buff.append("/orderDetailId_not=").append(orderDetailId_not);
		buff.append("/orderDetailId_large=").append(orderDetailId_large);
		buff
		    .append("/orderDetailId_moreLarge=")
		    .append(orderDetailId_moreLarge);
		buff.append("/orderDetailId_from=").append(orderDetailId_from);
		buff.append("/orderDetailId_to=").append(orderDetailId_to);
		buff
		    .append("/orderDetailId_moreSmall=")
		    .append(orderDetailId_moreSmall);
		buff.append("/orderDetailId_small=").append(orderDetailId_small);
		buff.append("/orderDetailId_in=").append(orderDetailId_in);
		buff.append("/orderDetailId_isNull=").append(orderDetailId_isNull);
		buff
		    .append("/orderDetailId_isNotNull=")
		    .append(orderDetailId_isNotNull);
		buff.append("/orderDetailId_isASC=").append(orderDetailId_isASC);
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
		buff.append("/shippingItemId=").append(shippingItemId);
		buff.append("/shippingItemId_not=").append(shippingItemId_not);
		buff.append("/shippingItemId_large=").append(shippingItemId_large);
		buff.append("/shippingItemId_moreLarge=").append(
		    shippingItemId_moreLarge);
		buff.append("/shippingItemId_from=").append(shippingItemId_from);
		buff.append("/shippingItemId_to=").append(shippingItemId_to);
		buff.append("/shippingItemId_moreSmall=").append(
		    shippingItemId_moreSmall);
		buff.append("/shippingItemId_small=").append(shippingItemId_small);
		buff.append("/shippingItemId_in=").append(shippingItemId_in);
		buff.append("/shippingItemId_isNull=").append(shippingItemId_isNull);
		buff.append("/shippingItemId_isNotNull=").append(
		    shippingItemId_isNotNull);
		buff.append("/shippingItemId_isASC=").append(shippingItemId_isASC);
		buff.append("]");
		return buff.toString();
	}
}
