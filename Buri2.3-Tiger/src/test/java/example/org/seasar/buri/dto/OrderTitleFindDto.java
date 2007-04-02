package example.org.seasar.buri.dto;

import java.util.GregorianCalendar;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.seasar.buri.common.util.ScriptProcessor;

public class OrderTitleFindDto {
	public static final String TABLE = "OrderTitle";
    private ArrayList orderList = new ArrayList();
	
	private Long orderTitleID = null;
	private Long orderTitleID_not = null;
	private Long orderTitleID_large = null;
	private Long orderTitleID_moreLarge = null;
	private Long orderTitleID_from = null;
	private Long orderTitleID_to = null;
	private Long orderTitleID_moreSmall = null;
	private Long orderTitleID_small = null;
	private List orderTitleID_in = null;
	private Boolean orderTitleID_isNull = null;
	private Boolean orderTitleID_isNotNull = null;
	private boolean orderTitleID_isASC = true;
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
	private Long customerID = null;
	private Long customerID_not = null;
	private Long customerID_large = null;
	private Long customerID_moreLarge = null;
	private Long customerID_from = null;
	private Long customerID_to = null;
	private Long customerID_moreSmall = null;
	private Long customerID_small = null;
	private List customerID_in = null;
	private Boolean customerID_isNull = null;
	private Boolean customerID_isNotNull = null;
	private boolean customerID_isASC = true;
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

	public Long getOrderTitleID() {
		return orderTitleID;
	}

	public void setOrderTitleID(Long orderTitleID) {
		this.orderTitleID = orderTitleID;
	}
	public Long getOrderTitleID_not() {
		return orderTitleID_not;
	}

	public void setOrderTitleID_not(Long orderTitleID_not) {
		this.orderTitleID_not = orderTitleID_not;
	}
	public Long getOrderTitleID_large() {
		return orderTitleID_large;
	}

	public void setOrderTitleID_large(Long orderTitleID_large) {
		this.orderTitleID_large = orderTitleID_large;
	}
	public Long getOrderTitleID_moreLarge() {
		return orderTitleID_moreLarge;
	}

	public void setOrderTitleID_moreLarge(Long orderTitleID_moreLarge) {
		this.orderTitleID_moreLarge = orderTitleID_moreLarge;
	}
	public Long getOrderTitleID_from() {
		return orderTitleID_from;
	}

	public void setOrderTitleID_from(Long orderTitleID_from) {
		this.orderTitleID_from = orderTitleID_from;
	}
	public Long getOrderTitleID_to() {
		return orderTitleID_to;
	}

	public void setOrderTitleID_to(Long orderTitleID_to) {
		this.orderTitleID_to = orderTitleID_to;
	}
	public Long getOrderTitleID_moreSmall() {
		return orderTitleID_moreSmall;
	}

	public void setOrderTitleID_moreSmall(Long orderTitleID_moreSmall) {
		this.orderTitleID_moreSmall = orderTitleID_moreSmall;
	}
	public Long getOrderTitleID_small() {
		return orderTitleID_small;
	}

	public void setOrderTitleID_small(Long orderTitleID_small) {
		this.orderTitleID_small = orderTitleID_small;
	}
	public List getOrderTitleID_in() {
		return orderTitleID_in;
	}

	public void setOrderTitleID_in(List orderTitleID_in) {
		this.orderTitleID_in = orderTitleID_in;
	}
	public Boolean getOrderTitleID_isNull() {
		return orderTitleID_isNull;
	}

	public void setOrderTitleID_isNull(Boolean orderTitleID_isNull) {
		this.orderTitleID_isNull = orderTitleID_isNull;
	}
	public Boolean getOrderTitleID_isNotNull() {
		return orderTitleID_isNotNull;
	}

	public void setOrderTitleID_isNotNull(Boolean orderTitleID_isNotNull) {
		this.orderTitleID_isNotNull = orderTitleID_isNotNull;
	}
	public boolean getOrderTitleID_isASC() {
		return orderTitleID_isASC;
	}

	public void setOrderTitleID_isASC(boolean orderTitleID_isASC) {
		this.orderTitleID_isASC = orderTitleID_isASC;
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
	public Long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}
	public Long getCustomerID_not() {
		return customerID_not;
	}

	public void setCustomerID_not(Long customerID_not) {
		this.customerID_not = customerID_not;
	}
	public Long getCustomerID_large() {
		return customerID_large;
	}

	public void setCustomerID_large(Long customerID_large) {
		this.customerID_large = customerID_large;
	}
	public Long getCustomerID_moreLarge() {
		return customerID_moreLarge;
	}

	public void setCustomerID_moreLarge(Long customerID_moreLarge) {
		this.customerID_moreLarge = customerID_moreLarge;
	}
	public Long getCustomerID_from() {
		return customerID_from;
	}

	public void setCustomerID_from(Long customerID_from) {
		this.customerID_from = customerID_from;
	}
	public Long getCustomerID_to() {
		return customerID_to;
	}

	public void setCustomerID_to(Long customerID_to) {
		this.customerID_to = customerID_to;
	}
	public Long getCustomerID_moreSmall() {
		return customerID_moreSmall;
	}

	public void setCustomerID_moreSmall(Long customerID_moreSmall) {
		this.customerID_moreSmall = customerID_moreSmall;
	}
	public Long getCustomerID_small() {
		return customerID_small;
	}

	public void setCustomerID_small(Long customerID_small) {
		this.customerID_small = customerID_small;
	}
	public List getCustomerID_in() {
		return customerID_in;
	}

	public void setCustomerID_in(List customerID_in) {
		this.customerID_in = customerID_in;
	}
	public Boolean getCustomerID_isNull() {
		return customerID_isNull;
	}

	public void setCustomerID_isNull(Boolean customerID_isNull) {
		this.customerID_isNull = customerID_isNull;
	}
	public Boolean getCustomerID_isNotNull() {
		return customerID_isNotNull;
	}

	public void setCustomerID_isNotNull(Boolean customerID_isNotNull) {
		this.customerID_isNotNull = customerID_isNotNull;
	}
	public boolean getCustomerID_isASC() {
		return customerID_isASC;
	}

	public void setCustomerID_isASC(boolean customerID_isASC) {
		this.customerID_isASC = customerID_isASC;
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

    public void addOrderList(String order,boolean isAsc) {
        orderList.add(order);
        ScriptProcessor processor = new ScriptProcessor();
        processor.setValue(order.replace('.','_') + "_isASC",this,new Boolean(isAsc));
    }
    
    public String getOrderList() {
        String order = "";
        String ORDER = "ORDER BY ";
        Iterator ite = orderList.iterator();
        ScriptProcessor processor = new ScriptProcessor();
        while(ite.hasNext()) {
            String orderTgt = (String)ite.next();
            order = ORDER + order + orderTgt.replace('_','.') + " ";
            Boolean var = (Boolean)processor.getValue(orderTgt + "_isASC", this);
            if( ! var.booleanValue()) {
                order = order + "DESC ";
            }
            ORDER = "";
        }
        return order;
    }

	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/orderTitleID=").append(orderTitleID);
		buff.append("/orderTitleID_not=").append(orderTitleID_not);
		buff.append("/orderTitleID_large=").append(orderTitleID_large);
		buff.append("/orderTitleID_moreLarge=").append(orderTitleID_moreLarge);
		buff.append("/orderTitleID_from=").append(orderTitleID_from);
		buff.append("/orderTitleID_to=").append(orderTitleID_to);
		buff.append("/orderTitleID_moreSmall=").append(orderTitleID_moreSmall);
		buff.append("/orderTitleID_small=").append(orderTitleID_small);
		buff.append("/orderTitleID_in=").append(orderTitleID_in);
		buff.append("/orderTitleID_isNull=").append(orderTitleID_isNull);
		buff.append("/orderTitleID_isNotNull=").append(orderTitleID_isNotNull);
		buff.append("/orderTitleID_isASC=").append(orderTitleID_isASC);
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
		buff.append("/customerID=").append(customerID);
		buff.append("/customerID_not=").append(customerID_not);
		buff.append("/customerID_large=").append(customerID_large);
		buff.append("/customerID_moreLarge=").append(customerID_moreLarge);
		buff.append("/customerID_from=").append(customerID_from);
		buff.append("/customerID_to=").append(customerID_to);
		buff.append("/customerID_moreSmall=").append(customerID_moreSmall);
		buff.append("/customerID_small=").append(customerID_small);
		buff.append("/customerID_in=").append(customerID_in);
		buff.append("/customerID_isNull=").append(customerID_isNull);
		buff.append("/customerID_isNotNull=").append(customerID_isNotNull);
		buff.append("/customerID_isASC=").append(customerID_isASC);
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
