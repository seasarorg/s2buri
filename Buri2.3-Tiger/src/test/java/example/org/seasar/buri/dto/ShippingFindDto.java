package example.org.seasar.buri.dto;

import java.util.GregorianCalendar;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.seasar.buri.common.util.ScriptProcessor;

public class ShippingFindDto {
	public static final String TABLE = "Shipping";
    private ArrayList orderList = new ArrayList();
	
	private Long shippingID = null;
	private Long shippingID_not = null;
	private Long shippingID_large = null;
	private Long shippingID_moreLarge = null;
	private Long shippingID_from = null;
	private Long shippingID_to = null;
	private Long shippingID_moreSmall = null;
	private Long shippingID_small = null;
	private List shippingID_in = null;
	private Boolean shippingID_isNull = null;
	private Boolean shippingID_isNotNull = null;
	private boolean shippingID_isASC = true;
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

	public Long getShippingID() {
		return shippingID;
	}

	public void setShippingID(Long shippingID) {
		this.shippingID = shippingID;
	}
	public Long getShippingID_not() {
		return shippingID_not;
	}

	public void setShippingID_not(Long shippingID_not) {
		this.shippingID_not = shippingID_not;
	}
	public Long getShippingID_large() {
		return shippingID_large;
	}

	public void setShippingID_large(Long shippingID_large) {
		this.shippingID_large = shippingID_large;
	}
	public Long getShippingID_moreLarge() {
		return shippingID_moreLarge;
	}

	public void setShippingID_moreLarge(Long shippingID_moreLarge) {
		this.shippingID_moreLarge = shippingID_moreLarge;
	}
	public Long getShippingID_from() {
		return shippingID_from;
	}

	public void setShippingID_from(Long shippingID_from) {
		this.shippingID_from = shippingID_from;
	}
	public Long getShippingID_to() {
		return shippingID_to;
	}

	public void setShippingID_to(Long shippingID_to) {
		this.shippingID_to = shippingID_to;
	}
	public Long getShippingID_moreSmall() {
		return shippingID_moreSmall;
	}

	public void setShippingID_moreSmall(Long shippingID_moreSmall) {
		this.shippingID_moreSmall = shippingID_moreSmall;
	}
	public Long getShippingID_small() {
		return shippingID_small;
	}

	public void setShippingID_small(Long shippingID_small) {
		this.shippingID_small = shippingID_small;
	}
	public List getShippingID_in() {
		return shippingID_in;
	}

	public void setShippingID_in(List shippingID_in) {
		this.shippingID_in = shippingID_in;
	}
	public Boolean getShippingID_isNull() {
		return shippingID_isNull;
	}

	public void setShippingID_isNull(Boolean shippingID_isNull) {
		this.shippingID_isNull = shippingID_isNull;
	}
	public Boolean getShippingID_isNotNull() {
		return shippingID_isNotNull;
	}

	public void setShippingID_isNotNull(Boolean shippingID_isNotNull) {
		this.shippingID_isNotNull = shippingID_isNotNull;
	}
	public boolean getShippingID_isASC() {
		return shippingID_isASC;
	}

	public void setShippingID_isASC(boolean shippingID_isASC) {
		this.shippingID_isASC = shippingID_isASC;
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
		buff.append("/shippingID=").append(shippingID);
		buff.append("/shippingID_not=").append(shippingID_not);
		buff.append("/shippingID_large=").append(shippingID_large);
		buff.append("/shippingID_moreLarge=").append(shippingID_moreLarge);
		buff.append("/shippingID_from=").append(shippingID_from);
		buff.append("/shippingID_to=").append(shippingID_to);
		buff.append("/shippingID_moreSmall=").append(shippingID_moreSmall);
		buff.append("/shippingID_small=").append(shippingID_small);
		buff.append("/shippingID_in=").append(shippingID_in);
		buff.append("/shippingID_isNull=").append(shippingID_isNull);
		buff.append("/shippingID_isNotNull=").append(shippingID_isNotNull);
		buff.append("/shippingID_isASC=").append(shippingID_isASC);
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
		buff.append("]");
		return buff.toString();
	}
	
}
