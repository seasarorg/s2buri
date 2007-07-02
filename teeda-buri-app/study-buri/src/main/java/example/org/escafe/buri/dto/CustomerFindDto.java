package example.org.escafe.buri.dto;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.escafe.buri.common.util.ScriptProcessor;

public class CustomerFindDto {
	public static final String TABLE = "Customer";
    private ArrayList orderList = new ArrayList();
	
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
		if(customerName_matchFull==null) {
			return null;
		}
		return "%"+customerName_matchFull+"%";
	}

	public void setCustomerName_matchFull(String customerName_matchFull) {
		this.customerName_matchFull = customerName_matchFull;
	}
	public String getCustomerName_matchFront() {
		if(customerName_matchFront==null) {
			return null;
		}
		return customerName_matchFront+"%";
	}

	public void setCustomerName_matchFront(String customerName_matchFront) {
		this.customerName_matchFront = customerName_matchFront;
	}
	public String getCustomerName_matchBack() {
		if(customerName_matchBack==null) {
			return null;
		}
		return "%"+customerName_matchBack;
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
