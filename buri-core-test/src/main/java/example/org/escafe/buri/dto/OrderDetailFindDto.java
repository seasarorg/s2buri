package example.org.escafe.buri.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.escafe.buri.common.util.ScriptProcessor;

public class OrderDetailFindDto {
	private final ArrayList orderList = new ArrayList();

	private Long orderDetailId = null;

	private Long orderDetailId_not = null;

	private Long orderDetailId_large = null;

	private Long orderDetailId_moreLarge = null;

	private Long orderDetailId_from = null;

	private Long orderDetailId_to = null;

	private Long orderDetailId_moreSmall = null;

	private Long orderDetailId_small = null;

	private List orderDetailId_in = null;

	private Boolean orderDetailId_isNull = null;

	private Boolean orderDetailId_isNotNull = null;

	private boolean orderDetailId_isASC = true;

	private Integer orderCount = null;

	private Integer orderCount_not = null;

	private Integer orderCount_large = null;

	private Integer orderCount_moreLarge = null;

	private Integer orderCount_from = null;

	private Integer orderCount_to = null;

	private Integer orderCount_moreSmall = null;

	private Integer orderCount_small = null;

	private List orderCount_in = null;

	private Boolean orderCount_isNull = null;

	private Boolean orderCount_isNotNull = null;

	private boolean orderCount_isASC = true;

	private Long itemId = null;

	private Long itemId_not = null;

	private Long itemId_large = null;

	private Long itemId_moreLarge = null;

	private Long itemId_from = null;

	private Long itemId_to = null;

	private Long itemId_moreSmall = null;

	private Long itemId_small = null;

	private List itemId_in = null;

	private Boolean itemId_isNull = null;

	private Boolean itemId_isNotNull = null;

	private boolean itemId_isASC = true;

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

	public List getOrderDetailId_in() {
		return orderDetailId_in;
	}

	public void setOrderDetailId_in(List orderDetailId_in) {
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

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public Integer getOrderCount_not() {
		return orderCount_not;
	}

	public void setOrderCount_not(Integer orderCount_not) {
		this.orderCount_not = orderCount_not;
	}

	public Integer getOrderCount_large() {
		return orderCount_large;
	}

	public void setOrderCount_large(Integer orderCount_large) {
		this.orderCount_large = orderCount_large;
	}

	public Integer getOrderCount_moreLarge() {
		return orderCount_moreLarge;
	}

	public void setOrderCount_moreLarge(Integer orderCount_moreLarge) {
		this.orderCount_moreLarge = orderCount_moreLarge;
	}

	public Integer getOrderCount_from() {
		return orderCount_from;
	}

	public void setOrderCount_from(Integer orderCount_from) {
		this.orderCount_from = orderCount_from;
	}

	public Integer getOrderCount_to() {
		return orderCount_to;
	}

	public void setOrderCount_to(Integer orderCount_to) {
		this.orderCount_to = orderCount_to;
	}

	public Integer getOrderCount_moreSmall() {
		return orderCount_moreSmall;
	}

	public void setOrderCount_moreSmall(Integer orderCount_moreSmall) {
		this.orderCount_moreSmall = orderCount_moreSmall;
	}

	public Integer getOrderCount_small() {
		return orderCount_small;
	}

	public void setOrderCount_small(Integer orderCount_small) {
		this.orderCount_small = orderCount_small;
	}

	public List getOrderCount_in() {
		return orderCount_in;
	}

	public void setOrderCount_in(List orderCount_in) {
		this.orderCount_in = orderCount_in;
	}

	public Boolean getOrderCount_isNull() {
		return orderCount_isNull;
	}

	public void setOrderCount_isNull(Boolean orderCount_isNull) {
		this.orderCount_isNull = orderCount_isNull;
	}

	public Boolean getOrderCount_isNotNull() {
		return orderCount_isNotNull;
	}

	public void setOrderCount_isNotNull(Boolean orderCount_isNotNull) {
		this.orderCount_isNotNull = orderCount_isNotNull;
	}

	public boolean getOrderCount_isASC() {
		return orderCount_isASC;
	}

	public void setOrderCount_isASC(boolean orderCount_isASC) {
		this.orderCount_isASC = orderCount_isASC;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getItemId_not() {
		return itemId_not;
	}

	public void setItemId_not(Long itemId_not) {
		this.itemId_not = itemId_not;
	}

	public Long getitemId_large() {
		return itemId_large;
	}

	public void setItemId_large(Long itemId_large) {
		this.itemId_large = itemId_large;
	}

	public Long getItemId_moreLarge() {
		return itemId_moreLarge;
	}

	public void setItemId_moreLarge(Long itemId_moreLarge) {
		this.itemId_moreLarge = itemId_moreLarge;
	}

	public Long getItemId_from() {
		return itemId_from;
	}

	public void setItemId_from(Long itemId_from) {
		this.itemId_from = itemId_from;
	}

	public Long getItemId_to() {
		return itemId_to;
	}

	public void setItemId_to(Long itemId_to) {
		this.itemId_to = itemId_to;
	}

	public Long getItemId_moreSmall() {
		return itemId_moreSmall;
	}

	public void setItemId_moreSmall(Long itemId_moreSmall) {
		this.itemId_moreSmall = itemId_moreSmall;
	}

	public Long getItemId_small() {
		return itemId_small;
	}

	public void setItemId_small(Long itemId_small) {
		this.itemId_small = itemId_small;
	}

	public List getItemId_in() {
		return itemId_in;
	}

	public void setItemId_in(List itemId_in) {
		this.itemId_in = itemId_in;
	}

	public Boolean getItemId_isNull() {
		return itemId_isNull;
	}

	public void setItemId_isNull(Boolean itemId_isNull) {
		this.itemId_isNull = itemId_isNull;
	}

	public Boolean getItemId_isNotNull() {
		return itemId_isNotNull;
	}

	public void setItemId_isNotNull(Boolean itemId_isNotNull) {
		this.itemId_isNotNull = itemId_isNotNull;
	}

	public boolean getItemId_isASC() {
		return itemId_isASC;
	}

	public void setItemId_isASC(boolean itemId_isASC) {
		this.itemId_isASC = itemId_isASC;
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

	public void setorderTitleId_large(Long orderTitleId_large) {
		this.orderTitleId_large = orderTitleId_large;
	}

	public Long getOrderTitleId_moreLarge() {
		return orderTitleId_moreLarge;
	}

	public void setOrderTitleId_moreLarge(Long orderTitleId_moreLarge) {
		this.orderTitleId_moreLarge = orderTitleId_moreLarge;
	}

	public Long getorderTitleId_from() {
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

	public Long getorderTitleId_moreSmall() {
		return orderTitleId_moreSmall;
	}

	public void setOrderTitleId_moreSmall(Long orderTitleId_moreSmall) {
		this.orderTitleId_moreSmall = orderTitleId_moreSmall;
	}

	public Long getorderTitleId_small() {
		return orderTitleId_small;
	}

	public void setOrderTitleId_small(Long orderTitleId_small) {
		this.orderTitleId_small = orderTitleId_small;
	}

	public List getorderTitleId_in() {
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
		buff.append("/orderCount=").append(orderCount);
		buff.append("/orderCount_not=").append(orderCount_not);
		buff.append("/orderCount_large=").append(orderCount_large);
		buff.append("/orderCount_moreLarge=").append(orderCount_moreLarge);
		buff.append("/orderCount_from=").append(orderCount_from);
		buff.append("/orderCount_to=").append(orderCount_to);
		buff.append("/orderCount_moreSmall=").append(orderCount_moreSmall);
		buff.append("/orderCount_small=").append(orderCount_small);
		buff.append("/orderCount_in=").append(orderCount_in);
		buff.append("/orderCount_isNull=").append(orderCount_isNull);
		buff.append("/orderCount_isNotNull=").append(orderCount_isNotNull);
		buff.append("/orderCount_isASC=").append(orderCount_isASC);
		buff.append("/itemId=").append(itemId);
		buff.append("/itemId_not=").append(itemId_not);
		buff.append("/itemId_large=").append(itemId_large);
		buff.append("/itemId_moreLarge=").append(itemId_moreLarge);
		buff.append("/itemId_from=").append(itemId_from);
		buff.append("/itemId_to=").append(itemId_to);
		buff.append("/itemId_moreSmall=").append(itemId_moreSmall);
		buff.append("/itemId_small=").append(itemId_small);
		buff.append("/itemId_in=").append(itemId_in);
		buff.append("/itemId_isNull=").append(itemId_isNull);
		buff.append("/itemId_isNotNull=").append(itemId_isNotNull);
		buff.append("/itemId_isASC=").append(itemId_isASC);
		buff.append("/OrderTitleId=").append(orderTitleId);
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
		buff.append("]");
		return buff.toString();
	}
}
