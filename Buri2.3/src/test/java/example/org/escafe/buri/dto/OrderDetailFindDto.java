package example.org.escafe.buri.dto;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.escafe.buri.common.util.ScriptProcessor;

public class OrderDetailFindDto {
	public static final String TABLE = "OrderDetail";
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
	private Long itemID = null;
	private Long itemID_not = null;
	private Long itemID_large = null;
	private Long itemID_moreLarge = null;
	private Long itemID_from = null;
	private Long itemID_to = null;
	private Long itemID_moreSmall = null;
	private Long itemID_small = null;
	private List itemID_in = null;
	private Boolean itemID_isNull = null;
	private Boolean itemID_isNotNull = null;
	private boolean itemID_isASC = true;
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
	public Long getItemID() {
		return itemID;
	}

	public void setItemID(Long itemID) {
		this.itemID = itemID;
	}
	public Long getItemID_not() {
		return itemID_not;
	}

	public void setItemID_not(Long itemID_not) {
		this.itemID_not = itemID_not;
	}
	public Long getItemID_large() {
		return itemID_large;
	}

	public void setItemID_large(Long itemID_large) {
		this.itemID_large = itemID_large;
	}
	public Long getItemID_moreLarge() {
		return itemID_moreLarge;
	}

	public void setItemID_moreLarge(Long itemID_moreLarge) {
		this.itemID_moreLarge = itemID_moreLarge;
	}
	public Long getItemID_from() {
		return itemID_from;
	}

	public void setItemID_from(Long itemID_from) {
		this.itemID_from = itemID_from;
	}
	public Long getItemID_to() {
		return itemID_to;
	}

	public void setItemID_to(Long itemID_to) {
		this.itemID_to = itemID_to;
	}
	public Long getItemID_moreSmall() {
		return itemID_moreSmall;
	}

	public void setItemID_moreSmall(Long itemID_moreSmall) {
		this.itemID_moreSmall = itemID_moreSmall;
	}
	public Long getItemID_small() {
		return itemID_small;
	}

	public void setItemID_small(Long itemID_small) {
		this.itemID_small = itemID_small;
	}
	public List getItemID_in() {
		return itemID_in;
	}

	public void setItemID_in(List itemID_in) {
		this.itemID_in = itemID_in;
	}
	public Boolean getItemID_isNull() {
		return itemID_isNull;
	}

	public void setItemID_isNull(Boolean itemID_isNull) {
		this.itemID_isNull = itemID_isNull;
	}
	public Boolean getItemID_isNotNull() {
		return itemID_isNotNull;
	}

	public void setItemID_isNotNull(Boolean itemID_isNotNull) {
		this.itemID_isNotNull = itemID_isNotNull;
	}
	public boolean getItemID_isASC() {
		return itemID_isASC;
	}

	public void setItemID_isASC(boolean itemID_isASC) {
		this.itemID_isASC = itemID_isASC;
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
		buff.append("/itemID=").append(itemID);
		buff.append("/itemID_not=").append(itemID_not);
		buff.append("/itemID_large=").append(itemID_large);
		buff.append("/itemID_moreLarge=").append(itemID_moreLarge);
		buff.append("/itemID_from=").append(itemID_from);
		buff.append("/itemID_to=").append(itemID_to);
		buff.append("/itemID_moreSmall=").append(itemID_moreSmall);
		buff.append("/itemID_small=").append(itemID_small);
		buff.append("/itemID_in=").append(itemID_in);
		buff.append("/itemID_isNull=").append(itemID_isNull);
		buff.append("/itemID_isNotNull=").append(itemID_isNotNull);
		buff.append("/itemID_isASC=").append(itemID_isASC);
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
		buff.append("]");
		return buff.toString();
	}
	
}
