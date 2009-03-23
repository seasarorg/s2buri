package example.org.escafe.buri.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.escafe.buri.common.util.ScriptProcessor;

public class OrderTitleFindDto {
	public static final String TABLE = "OrderTitle";

	private final ArrayList orderList = new ArrayList();

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

	private Timestamp orderDate = null;

	private Timestamp orderDate_not = null;

	private Timestamp orderDate_large = null;

	private Timestamp orderDate_moreLarge = null;

	private Timestamp orderDate_from = null;

	private Timestamp orderDate_to = null;

	private Timestamp orderDate_moreSmall = null;

	private Timestamp orderDate_small = null;

	private List orderDate_in = null;

	private Boolean orderDate_isNull = null;

	private Boolean orderDate_isNotNull = null;

	private boolean orderDate_isASC = true;

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

	private Integer status = null;

	private Integer status_not = null;

	private Integer status_large = null;

	private Integer status_moreLarge = null;

	private Integer status_from = null;

	private Integer status_to = null;

	private Integer status_moreSmall = null;

	private Integer status_small = null;

	private List status_in = null;

	private Boolean status_isNull = null;

	private Boolean status_isNotNull = null;

	private boolean status_isASC = true;

	public Long getorderTitleId() {
		return orderTitleId;
	}

	public void setorderTitleId(Long orderTitleId) {
		this.orderTitleId = orderTitleId;
	}

	public Long getorderTitleId_not() {
		return orderTitleId_not;
	}

	public void setorderTitleId_not(Long orderTitleId_not) {
		this.orderTitleId_not = orderTitleId_not;
	}

	public Long getorderTitleId_large() {
		return orderTitleId_large;
	}

	public void setorderTitleId_large(Long orderTitleId_large) {
		this.orderTitleId_large = orderTitleId_large;
	}

	public Long getorderTitleId_moreLarge() {
		return orderTitleId_moreLarge;
	}

	public void setorderTitleId_moreLarge(Long orderTitleId_moreLarge) {
		this.orderTitleId_moreLarge = orderTitleId_moreLarge;
	}

	public Long getorderTitleId_from() {
		return orderTitleId_from;
	}

	public void setorderTitleId_from(Long orderTitleId_from) {
		this.orderTitleId_from = orderTitleId_from;
	}

	public Long getorderTitleId_to() {
		return orderTitleId_to;
	}

	public void setorderTitleId_to(Long orderTitleId_to) {
		this.orderTitleId_to = orderTitleId_to;
	}

	public Long getorderTitleId_moreSmall() {
		return orderTitleId_moreSmall;
	}

	public void setorderTitleId_moreSmall(Long orderTitleId_moreSmall) {
		this.orderTitleId_moreSmall = orderTitleId_moreSmall;
	}

	public Long getorderTitleId_small() {
		return orderTitleId_small;
	}

	public void setorderTitleId_small(Long orderTitleId_small) {
		this.orderTitleId_small = orderTitleId_small;
	}

	public List getorderTitleId_in() {
		return orderTitleId_in;
	}

	public void setorderTitleId_in(List orderTitleId_in) {
		this.orderTitleId_in = orderTitleId_in;
	}

	public Boolean getorderTitleId_isNull() {
		return orderTitleId_isNull;
	}

	public void setorderTitleId_isNull(Boolean orderTitleId_isNull) {
		this.orderTitleId_isNull = orderTitleId_isNull;
	}

	public Boolean getorderTitleId_isNotNull() {
		return orderTitleId_isNotNull;
	}

	public void setorderTitleId_isNotNull(Boolean orderTitleId_isNotNull) {
		this.orderTitleId_isNotNull = orderTitleId_isNotNull;
	}

	public boolean getorderTitleId_isASC() {
		return orderTitleId_isASC;
	}

	public void setorderTitleId_isASC(boolean orderTitleId_isASC) {
		this.orderTitleId_isASC = orderTitleId_isASC;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public Timestamp getOrderDate_not() {
		return orderDate_not;
	}

	public void setOrderDate_not(Timestamp orderDate_not) {
		this.orderDate_not = orderDate_not;
	}

	public Timestamp getOrderDate_large() {
		return orderDate_large;
	}

	public void setOrderDate_large(Timestamp orderDate_large) {
		this.orderDate_large = orderDate_large;
	}

	public Timestamp getOrderDate_moreLarge() {
		return orderDate_moreLarge;
	}

	public void setOrderDate_moreLarge(Timestamp orderDate_moreLarge) {
		this.orderDate_moreLarge = orderDate_moreLarge;
	}

	public Timestamp getOrderDate_from() {
		return orderDate_from;
	}

	public void setOrderDate_from(Timestamp orderDate_from) {
		this.orderDate_from = orderDate_from;
	}

	public Timestamp getOrderDate_to() {
		return orderDate_to;
	}

	public void setOrderDate_to(Timestamp orderDate_to) {
		this.orderDate_to = orderDate_to;
	}

	public Timestamp getOrderDate_moreSmall() {
		return orderDate_moreSmall;
	}

	public void setOrderDate_moreSmall(Timestamp orderDate_moreSmall) {
		this.orderDate_moreSmall = orderDate_moreSmall;
	}

	public Timestamp getOrderDate_small() {
		return orderDate_small;
	}

	public void setOrderDate_small(Timestamp orderDate_small) {
		this.orderDate_small = orderDate_small;
	}

	public List getOrderDate_in() {
		return orderDate_in;
	}

	public void setOrderDate_in(List orderDate_in) {
		this.orderDate_in = orderDate_in;
	}

	public Boolean getOrderDate_isNull() {
		return orderDate_isNull;
	}

	public void setOrderDate_isNull(Boolean orderDate_isNull) {
		this.orderDate_isNull = orderDate_isNull;
	}

	public Boolean getOrderDate_isNotNull() {
		return orderDate_isNotNull;
	}

	public void setOrderDate_isNotNull(Boolean orderDate_isNotNull) {
		this.orderDate_isNotNull = orderDate_isNotNull;
	}

	public boolean getOrderDate_isASC() {
		return orderDate_isASC;
	}

	public void setOrderDate_isASC(boolean orderDate_isASC) {
		this.orderDate_isASC = orderDate_isASC;
	}

	public Long getcustomerId() {
		return customerId;
	}

	public void setcustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getcustomerId_not() {
		return customerId_not;
	}

	public void setcustomerId_not(Long customerId_not) {
		this.customerId_not = customerId_not;
	}

	public Long getcustomerId_large() {
		return customerId_large;
	}

	public void setcustomerId_large(Long customerId_large) {
		this.customerId_large = customerId_large;
	}

	public Long getcustomerId_moreLarge() {
		return customerId_moreLarge;
	}

	public void setcustomerId_moreLarge(Long customerId_moreLarge) {
		this.customerId_moreLarge = customerId_moreLarge;
	}

	public Long getcustomerId_from() {
		return customerId_from;
	}

	public void setcustomerId_from(Long customerId_from) {
		this.customerId_from = customerId_from;
	}

	public Long getcustomerId_to() {
		return customerId_to;
	}

	public void setcustomerId_to(Long customerId_to) {
		this.customerId_to = customerId_to;
	}

	public Long getcustomerId_moreSmall() {
		return customerId_moreSmall;
	}

	public void setcustomerId_moreSmall(Long customerId_moreSmall) {
		this.customerId_moreSmall = customerId_moreSmall;
	}

	public Long getcustomerId_small() {
		return customerId_small;
	}

	public void setcustomerId_small(Long customerId_small) {
		this.customerId_small = customerId_small;
	}

	public List getcustomerId_in() {
		return customerId_in;
	}

	public void setcustomerId_in(List customerId_in) {
		this.customerId_in = customerId_in;
	}

	public Boolean getcustomerId_isNull() {
		return customerId_isNull;
	}

	public void setcustomerId_isNull(Boolean customerId_isNull) {
		this.customerId_isNull = customerId_isNull;
	}

	public Boolean getcustomerId_isNotNull() {
		return customerId_isNotNull;
	}

	public void setcustomerId_isNotNull(Boolean customerId_isNotNull) {
		this.customerId_isNotNull = customerId_isNotNull;
	}

	public boolean getcustomerId_isASC() {
		return customerId_isASC;
	}

	public void setcustomerId_isASC(boolean customerId_isASC) {
		this.customerId_isASC = customerId_isASC;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus_not() {
		return status_not;
	}

	public void setStatus_not(Integer status_not) {
		this.status_not = status_not;
	}

	public Integer getStatus_large() {
		return status_large;
	}

	public void setStatus_large(Integer status_large) {
		this.status_large = status_large;
	}

	public Integer getStatus_moreLarge() {
		return status_moreLarge;
	}

	public void setStatus_moreLarge(Integer status_moreLarge) {
		this.status_moreLarge = status_moreLarge;
	}

	public Integer getStatus_from() {
		return status_from;
	}

	public void setStatus_from(Integer status_from) {
		this.status_from = status_from;
	}

	public Integer getStatus_to() {
		return status_to;
	}

	public void setStatus_to(Integer status_to) {
		this.status_to = status_to;
	}

	public Integer getStatus_moreSmall() {
		return status_moreSmall;
	}

	public void setStatus_moreSmall(Integer status_moreSmall) {
		this.status_moreSmall = status_moreSmall;
	}

	public Integer getStatus_small() {
		return status_small;
	}

	public void setStatus_small(Integer status_small) {
		this.status_small = status_small;
	}

	public List getStatus_in() {
		return status_in;
	}

	public void setStatus_in(List status_in) {
		this.status_in = status_in;
	}

	public Boolean getStatus_isNull() {
		return status_isNull;
	}

	public void setStatus_isNull(Boolean status_isNull) {
		this.status_isNull = status_isNull;
	}

	public Boolean getStatus_isNotNull() {
		return status_isNotNull;
	}

	public void setStatus_isNotNull(Boolean status_isNotNull) {
		this.status_isNotNull = status_isNotNull;
	}

	public boolean getStatus_isASC() {
		return status_isASC;
	}

	public void setStatus_isASC(boolean status_isASC) {
		this.status_isASC = status_isASC;
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
		buff.append("/orderDate=").append(orderDate);
		buff.append("/orderDate_not=").append(orderDate_not);
		buff.append("/orderDate_large=").append(orderDate_large);
		buff.append("/orderDate_moreLarge=").append(orderDate_moreLarge);
		buff.append("/orderDate_from=").append(orderDate_from);
		buff.append("/orderDate_to=").append(orderDate_to);
		buff.append("/orderDate_moreSmall=").append(orderDate_moreSmall);
		buff.append("/orderDate_small=").append(orderDate_small);
		buff.append("/orderDate_in=").append(orderDate_in);
		buff.append("/orderDate_isNull=").append(orderDate_isNull);
		buff.append("/orderDate_isNotNull=").append(orderDate_isNotNull);
		buff.append("/orderDate_isASC=").append(orderDate_isASC);
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
		buff.append("/status=").append(status);
		buff.append("/status_not=").append(status_not);
		buff.append("/status_large=").append(status_large);
		buff.append("/status_moreLarge=").append(status_moreLarge);
		buff.append("/status_from=").append(status_from);
		buff.append("/status_to=").append(status_to);
		buff.append("/status_moreSmall=").append(status_moreSmall);
		buff.append("/status_small=").append(status_small);
		buff.append("/status_in=").append(status_in);
		buff.append("/status_isNull=").append(status_isNull);
		buff.append("/status_isNotNull=").append(status_isNotNull);
		buff.append("/status_isASC=").append(status_isASC);
		buff.append("]");
		return buff.toString();
	}
}
