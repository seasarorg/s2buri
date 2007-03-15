package example.org.seasar.buri.dto;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.seasar.buri.common.util.ScriptProcessor;

public class ShippingItemFindDto {
	public static final String TABLE = "ShippingItem";
    private ArrayList orderList = new ArrayList();
	
	private Long orderDetailID = null;
	private Long orderDetailID_not = null;
	private Long orderDetailID_large = null;
	private Long orderDetailID_moreLarge = null;
	private Long orderDetailID_from = null;
	private Long orderDetailID_to = null;
	private Long orderDetailID_moreSmall = null;
	private Long orderDetailID_small = null;
	private List orderDetailID_in = null;
	private Boolean orderDetailID_isNull = null;
	private Boolean orderDetailID_isNotNull = null;
	private boolean orderDetailID_isASC = true;
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
	private Long shippingItemID = null;
	private Long shippingItemID_not = null;
	private Long shippingItemID_large = null;
	private Long shippingItemID_moreLarge = null;
	private Long shippingItemID_from = null;
	private Long shippingItemID_to = null;
	private Long shippingItemID_moreSmall = null;
	private Long shippingItemID_small = null;
	private List shippingItemID_in = null;
	private Boolean shippingItemID_isNull = null;
	private Boolean shippingItemID_isNotNull = null;
	private boolean shippingItemID_isASC = true;

	public Long getOrderDetailID() {
		return orderDetailID;
	}

	public void setOrderDetailID(Long orderDetailID) {
		this.orderDetailID = orderDetailID;
	}
	public Long getOrderDetailID_not() {
		return orderDetailID_not;
	}

	public void setOrderDetailID_not(Long orderDetailID_not) {
		this.orderDetailID_not = orderDetailID_not;
	}
	public Long getOrderDetailID_large() {
		return orderDetailID_large;
	}

	public void setOrderDetailID_large(Long orderDetailID_large) {
		this.orderDetailID_large = orderDetailID_large;
	}
	public Long getOrderDetailID_moreLarge() {
		return orderDetailID_moreLarge;
	}

	public void setOrderDetailID_moreLarge(Long orderDetailID_moreLarge) {
		this.orderDetailID_moreLarge = orderDetailID_moreLarge;
	}
	public Long getOrderDetailID_from() {
		return orderDetailID_from;
	}

	public void setOrderDetailID_from(Long orderDetailID_from) {
		this.orderDetailID_from = orderDetailID_from;
	}
	public Long getOrderDetailID_to() {
		return orderDetailID_to;
	}

	public void setOrderDetailID_to(Long orderDetailID_to) {
		this.orderDetailID_to = orderDetailID_to;
	}
	public Long getOrderDetailID_moreSmall() {
		return orderDetailID_moreSmall;
	}

	public void setOrderDetailID_moreSmall(Long orderDetailID_moreSmall) {
		this.orderDetailID_moreSmall = orderDetailID_moreSmall;
	}
	public Long getOrderDetailID_small() {
		return orderDetailID_small;
	}

	public void setOrderDetailID_small(Long orderDetailID_small) {
		this.orderDetailID_small = orderDetailID_small;
	}
	public List getOrderDetailID_in() {
		return orderDetailID_in;
	}

	public void setOrderDetailID_in(List orderDetailID_in) {
		this.orderDetailID_in = orderDetailID_in;
	}
	public Boolean getOrderDetailID_isNull() {
		return orderDetailID_isNull;
	}

	public void setOrderDetailID_isNull(Boolean orderDetailID_isNull) {
		this.orderDetailID_isNull = orderDetailID_isNull;
	}
	public Boolean getOrderDetailID_isNotNull() {
		return orderDetailID_isNotNull;
	}

	public void setOrderDetailID_isNotNull(Boolean orderDetailID_isNotNull) {
		this.orderDetailID_isNotNull = orderDetailID_isNotNull;
	}
	public boolean getOrderDetailID_isASC() {
		return orderDetailID_isASC;
	}

	public void setOrderDetailID_isASC(boolean orderDetailID_isASC) {
		this.orderDetailID_isASC = orderDetailID_isASC;
	}
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
	public Long getShippingItemID() {
		return shippingItemID;
	}

	public void setShippingItemID(Long shippingItemID) {
		this.shippingItemID = shippingItemID;
	}
	public Long getShippingItemID_not() {
		return shippingItemID_not;
	}

	public void setShippingItemID_not(Long shippingItemID_not) {
		this.shippingItemID_not = shippingItemID_not;
	}
	public Long getShippingItemID_large() {
		return shippingItemID_large;
	}

	public void setShippingItemID_large(Long shippingItemID_large) {
		this.shippingItemID_large = shippingItemID_large;
	}
	public Long getShippingItemID_moreLarge() {
		return shippingItemID_moreLarge;
	}

	public void setShippingItemID_moreLarge(Long shippingItemID_moreLarge) {
		this.shippingItemID_moreLarge = shippingItemID_moreLarge;
	}
	public Long getShippingItemID_from() {
		return shippingItemID_from;
	}

	public void setShippingItemID_from(Long shippingItemID_from) {
		this.shippingItemID_from = shippingItemID_from;
	}
	public Long getShippingItemID_to() {
		return shippingItemID_to;
	}

	public void setShippingItemID_to(Long shippingItemID_to) {
		this.shippingItemID_to = shippingItemID_to;
	}
	public Long getShippingItemID_moreSmall() {
		return shippingItemID_moreSmall;
	}

	public void setShippingItemID_moreSmall(Long shippingItemID_moreSmall) {
		this.shippingItemID_moreSmall = shippingItemID_moreSmall;
	}
	public Long getShippingItemID_small() {
		return shippingItemID_small;
	}

	public void setShippingItemID_small(Long shippingItemID_small) {
		this.shippingItemID_small = shippingItemID_small;
	}
	public List getShippingItemID_in() {
		return shippingItemID_in;
	}

	public void setShippingItemID_in(List shippingItemID_in) {
		this.shippingItemID_in = shippingItemID_in;
	}
	public Boolean getShippingItemID_isNull() {
		return shippingItemID_isNull;
	}

	public void setShippingItemID_isNull(Boolean shippingItemID_isNull) {
		this.shippingItemID_isNull = shippingItemID_isNull;
	}
	public Boolean getShippingItemID_isNotNull() {
		return shippingItemID_isNotNull;
	}

	public void setShippingItemID_isNotNull(Boolean shippingItemID_isNotNull) {
		this.shippingItemID_isNotNull = shippingItemID_isNotNull;
	}
	public boolean getShippingItemID_isASC() {
		return shippingItemID_isASC;
	}

	public void setShippingItemID_isASC(boolean shippingItemID_isASC) {
		this.shippingItemID_isASC = shippingItemID_isASC;
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
		buff.append("/orderDetailID=").append(orderDetailID);
		buff.append("/orderDetailID_not=").append(orderDetailID_not);
		buff.append("/orderDetailID_large=").append(orderDetailID_large);
		buff.append("/orderDetailID_moreLarge=").append(orderDetailID_moreLarge);
		buff.append("/orderDetailID_from=").append(orderDetailID_from);
		buff.append("/orderDetailID_to=").append(orderDetailID_to);
		buff.append("/orderDetailID_moreSmall=").append(orderDetailID_moreSmall);
		buff.append("/orderDetailID_small=").append(orderDetailID_small);
		buff.append("/orderDetailID_in=").append(orderDetailID_in);
		buff.append("/orderDetailID_isNull=").append(orderDetailID_isNull);
		buff.append("/orderDetailID_isNotNull=").append(orderDetailID_isNotNull);
		buff.append("/orderDetailID_isASC=").append(orderDetailID_isASC);
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
		buff.append("/shippingItemID=").append(shippingItemID);
		buff.append("/shippingItemID_not=").append(shippingItemID_not);
		buff.append("/shippingItemID_large=").append(shippingItemID_large);
		buff.append("/shippingItemID_moreLarge=").append(shippingItemID_moreLarge);
		buff.append("/shippingItemID_from=").append(shippingItemID_from);
		buff.append("/shippingItemID_to=").append(shippingItemID_to);
		buff.append("/shippingItemID_moreSmall=").append(shippingItemID_moreSmall);
		buff.append("/shippingItemID_small=").append(shippingItemID_small);
		buff.append("/shippingItemID_in=").append(shippingItemID_in);
		buff.append("/shippingItemID_isNull=").append(shippingItemID_isNull);
		buff.append("/shippingItemID_isNotNull=").append(shippingItemID_isNotNull);
		buff.append("/shippingItemID_isASC=").append(shippingItemID_isASC);
		buff.append("]");
		return buff.toString();
	}
	
}
