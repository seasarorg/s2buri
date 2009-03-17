package example.org.escafe.buri.dto;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.escafe.buri.common.util.ScriptProcessor;

public class BillFindDto {
	public static final String TABLE = "Bill";
    private ArrayList orderList = new ArrayList();
	
	private Long billId = null;
	private Long billId_not = null;
	private Long billId_large = null;
	private Long billId_moreLarge = null;
	private Long billId_from = null;
	private Long billId_to = null;
	private Long billId_moreSmall = null;
	private Long billId_small = null;
	private List billId_in = null;
	private Boolean billId_isNull = null;
	private Boolean billId_isNotNull = null;
	private boolean billId_isASC = true;
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

	public Long getbillId() {
		return billId;
	}

	public void setbillId(Long billId) {
		this.billId = billId;
	}
	public Long getbillId_not() {
		return billId_not;
	}

	public void setbillId_not(Long billId_not) {
		this.billId_not = billId_not;
	}
	public Long getbillId_large() {
		return billId_large;
	}

	public void setbillId_large(Long billId_large) {
		this.billId_large = billId_large;
	}
	public Long getbillId_moreLarge() {
		return billId_moreLarge;
	}

	public void setbillId_moreLarge(Long billId_moreLarge) {
		this.billId_moreLarge = billId_moreLarge;
	}
	public Long getbillId_from() {
		return billId_from;
	}

	public void setbillId_from(Long billId_from) {
		this.billId_from = billId_from;
	}
	public Long getbillId_to() {
		return billId_to;
	}

	public void setbillId_to(Long billId_to) {
		this.billId_to = billId_to;
	}
	public Long getbillId_moreSmall() {
		return billId_moreSmall;
	}

	public void setbillId_moreSmall(Long billId_moreSmall) {
		this.billId_moreSmall = billId_moreSmall;
	}
	public Long getbillId_small() {
		return billId_small;
	}

	public void setbillId_small(Long billId_small) {
		this.billId_small = billId_small;
	}
	public List getbillId_in() {
		return billId_in;
	}

	public void setbillId_in(List billId_in) {
		this.billId_in = billId_in;
	}
	public Boolean getbillId_isNull() {
		return billId_isNull;
	}

	public void setbillId_isNull(Boolean billId_isNull) {
		this.billId_isNull = billId_isNull;
	}
	public Boolean getbillId_isNotNull() {
		return billId_isNotNull;
	}

	public void setbillId_isNotNull(Boolean billId_isNotNull) {
		this.billId_isNotNull = billId_isNotNull;
	}
	public boolean getbillId_isASC() {
		return billId_isASC;
	}

	public void setbillId_isASC(boolean billId_isASC) {
		this.billId_isASC = billId_isASC;
	}
	public Long getshippingId() {
		return shippingId;
	}

	public void setshippingId(Long shippingId) {
		this.shippingId = shippingId;
	}
	public Long getshippingId_not() {
		return shippingId_not;
	}

	public void setshippingId_not(Long shippingId_not) {
		this.shippingId_not = shippingId_not;
	}
	public Long getshippingId_large() {
		return shippingId_large;
	}

	public void setshippingId_large(Long shippingId_large) {
		this.shippingId_large = shippingId_large;
	}
	public Long getshippingId_moreLarge() {
		return shippingId_moreLarge;
	}

	public void setshippingId_moreLarge(Long shippingId_moreLarge) {
		this.shippingId_moreLarge = shippingId_moreLarge;
	}
	public Long getshippingId_from() {
		return shippingId_from;
	}

	public void setshippingId_from(Long shippingId_from) {
		this.shippingId_from = shippingId_from;
	}
	public Long getshippingId_to() {
		return shippingId_to;
	}

	public void setshippingId_to(Long shippingId_to) {
		this.shippingId_to = shippingId_to;
	}
	public Long getshippingId_moreSmall() {
		return shippingId_moreSmall;
	}

	public void setshippingId_moreSmall(Long shippingId_moreSmall) {
		this.shippingId_moreSmall = shippingId_moreSmall;
	}
	public Long getshippingId_small() {
		return shippingId_small;
	}

	public void setshippingId_small(Long shippingId_small) {
		this.shippingId_small = shippingId_small;
	}
	public List getshippingId_in() {
		return shippingId_in;
	}

	public void setshippingId_in(List shippingId_in) {
		this.shippingId_in = shippingId_in;
	}
	public Boolean getshippingId_isNull() {
		return shippingId_isNull;
	}

	public void setshippingId_isNull(Boolean shippingId_isNull) {
		this.shippingId_isNull = shippingId_isNull;
	}
	public Boolean getshippingId_isNotNull() {
		return shippingId_isNotNull;
	}

	public void setshippingId_isNotNull(Boolean shippingId_isNotNull) {
		this.shippingId_isNotNull = shippingId_isNotNull;
	}
	public boolean getshippingId_isASC() {
		return shippingId_isASC;
	}

	public void setshippingId_isASC(boolean shippingId_isASC) {
		this.shippingId_isASC = shippingId_isASC;
	}
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

	@Override
    public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/billId=").append(billId);
		buff.append("/billId_not=").append(billId_not);
		buff.append("/billId_large=").append(billId_large);
		buff.append("/billId_moreLarge=").append(billId_moreLarge);
		buff.append("/billId_from=").append(billId_from);
		buff.append("/billId_to=").append(billId_to);
		buff.append("/billId_moreSmall=").append(billId_moreSmall);
		buff.append("/billId_small=").append(billId_small);
		buff.append("/billId_in=").append(billId_in);
		buff.append("/billId_isNull=").append(billId_isNull);
		buff.append("/billId_isNotNull=").append(billId_isNotNull);
		buff.append("/billId_isASC=").append(billId_isASC);
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
