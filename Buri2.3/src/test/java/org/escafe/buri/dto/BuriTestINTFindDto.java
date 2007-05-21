package org.escafe.buri.dto;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.escafe.buri.common.util.ScriptProcessor;

public class BuriTestINTFindDto {
	public static final String TABLE = "BuriTestINT";
    private ArrayList orderList = new ArrayList();
	
	private Integer testID = null;
	private Integer testID_not = null;
	private Integer testID_large = null;
	private Integer testID_moreLarge = null;
	private Integer testID_from = null;
	private Integer testID_to = null;
	private Integer testID_moreSmall = null;
	private Integer testID_small = null;
	private List testID_in = null;
	private Boolean testID_isNull = null;
	private Boolean testID_isNotNull = null;
	private boolean testID_isASC = true;
	private String value = null;
	private String value_not = null;
	private String value_large = null;
	private String value_moreLarge = null;
	private String value_from = null;
	private String value_to = null;
	private String value_moreSmall = null;
	private String value_small = null;
	private String value_matchFull = null;
	private String value_matchFront = null;
	private String value_matchBack = null;
	private List value_in = null;
	private Boolean value_isNull = null;
	private Boolean value_isNotNull = null;
	private boolean value_isASC = true;
	private Integer versionNo = null;
	private Integer versionNo_not = null;
	private Integer versionNo_large = null;
	private Integer versionNo_moreLarge = null;
	private Integer versionNo_from = null;
	private Integer versionNo_to = null;
	private Integer versionNo_moreSmall = null;
	private Integer versionNo_small = null;
	private List versionNo_in = null;
	private Boolean versionNo_isNull = null;
	private Boolean versionNo_isNotNull = null;
	private boolean versionNo_isASC = true;

	public Integer getTestID() {
		return testID;
	}

	public void setTestID(Integer testID) {
		this.testID = testID;
	}
	public Integer getTestID_not() {
		return testID_not;
	}

	public void setTestID_not(Integer testID_not) {
		this.testID_not = testID_not;
	}
	public Integer getTestID_large() {
		return testID_large;
	}

	public void setTestID_large(Integer testID_large) {
		this.testID_large = testID_large;
	}
	public Integer getTestID_moreLarge() {
		return testID_moreLarge;
	}

	public void setTestID_moreLarge(Integer testID_moreLarge) {
		this.testID_moreLarge = testID_moreLarge;
	}
	public Integer getTestID_from() {
		return testID_from;
	}

	public void setTestID_from(Integer testID_from) {
		this.testID_from = testID_from;
	}
	public Integer getTestID_to() {
		return testID_to;
	}

	public void setTestID_to(Integer testID_to) {
		this.testID_to = testID_to;
	}
	public Integer getTestID_moreSmall() {
		return testID_moreSmall;
	}

	public void setTestID_moreSmall(Integer testID_moreSmall) {
		this.testID_moreSmall = testID_moreSmall;
	}
	public Integer getTestID_small() {
		return testID_small;
	}

	public void setTestID_small(Integer testID_small) {
		this.testID_small = testID_small;
	}
	public List getTestID_in() {
		return testID_in;
	}

	public void setTestID_in(List testID_in) {
		this.testID_in = testID_in;
	}
	public Boolean getTestID_isNull() {
		return testID_isNull;
	}

	public void setTestID_isNull(Boolean testID_isNull) {
		this.testID_isNull = testID_isNull;
	}
	public Boolean getTestID_isNotNull() {
		return testID_isNotNull;
	}

	public void setTestID_isNotNull(Boolean testID_isNotNull) {
		this.testID_isNotNull = testID_isNotNull;
	}
	public boolean getTestID_isASC() {
		return testID_isASC;
	}

	public void setTestID_isASC(boolean testID_isASC) {
		this.testID_isASC = testID_isASC;
	}
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	public String getValue_not() {
		return value_not;
	}

	public void setValue_not(String value_not) {
		this.value_not = value_not;
	}
	public String getValue_large() {
		return value_large;
	}

	public void setValue_large(String value_large) {
		this.value_large = value_large;
	}
	public String getValue_moreLarge() {
		return value_moreLarge;
	}

	public void setValue_moreLarge(String value_moreLarge) {
		this.value_moreLarge = value_moreLarge;
	}
	public String getValue_from() {
		return value_from;
	}

	public void setValue_from(String value_from) {
		this.value_from = value_from;
	}
	public String getValue_to() {
		return value_to;
	}

	public void setValue_to(String value_to) {
		this.value_to = value_to;
	}
	public String getValue_moreSmall() {
		return value_moreSmall;
	}

	public void setValue_moreSmall(String value_moreSmall) {
		this.value_moreSmall = value_moreSmall;
	}
	public String getValue_small() {
		return value_small;
	}

	public void setValue_small(String value_small) {
		this.value_small = value_small;
	}
	public String getValue_matchFull() {
		if(value_matchFull==null) {
			return null;
		}
		return "%"+value_matchFull+"%";
	}

	public void setValue_matchFull(String value_matchFull) {
		this.value_matchFull = value_matchFull;
	}
	public String getValue_matchFront() {
		if(value_matchFront==null) {
			return null;
		}
		return value_matchFront+"%";
	}

	public void setValue_matchFront(String value_matchFront) {
		this.value_matchFront = value_matchFront;
	}
	public String getValue_matchBack() {
		if(value_matchBack==null) {
			return null;
		}
		return "%"+value_matchBack;
	}

	public void setValue_matchBack(String value_matchBack) {
		this.value_matchBack = value_matchBack;
	}
	public List getValue_in() {
		return value_in;
	}

	public void setValue_in(List value_in) {
		this.value_in = value_in;
	}
	public Boolean getValue_isNull() {
		return value_isNull;
	}

	public void setValue_isNull(Boolean value_isNull) {
		this.value_isNull = value_isNull;
	}
	public Boolean getValue_isNotNull() {
		return value_isNotNull;
	}

	public void setValue_isNotNull(Boolean value_isNotNull) {
		this.value_isNotNull = value_isNotNull;
	}
	public boolean getValue_isASC() {
		return value_isASC;
	}

	public void setValue_isASC(boolean value_isASC) {
		this.value_isASC = value_isASC;
	}
	public Integer getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Integer versionNo) {
		this.versionNo = versionNo;
	}
	public Integer getVersionNo_not() {
		return versionNo_not;
	}

	public void setVersionNo_not(Integer versionNo_not) {
		this.versionNo_not = versionNo_not;
	}
	public Integer getVersionNo_large() {
		return versionNo_large;
	}

	public void setVersionNo_large(Integer versionNo_large) {
		this.versionNo_large = versionNo_large;
	}
	public Integer getVersionNo_moreLarge() {
		return versionNo_moreLarge;
	}

	public void setVersionNo_moreLarge(Integer versionNo_moreLarge) {
		this.versionNo_moreLarge = versionNo_moreLarge;
	}
	public Integer getVersionNo_from() {
		return versionNo_from;
	}

	public void setVersionNo_from(Integer versionNo_from) {
		this.versionNo_from = versionNo_from;
	}
	public Integer getVersionNo_to() {
		return versionNo_to;
	}

	public void setVersionNo_to(Integer versionNo_to) {
		this.versionNo_to = versionNo_to;
	}
	public Integer getVersionNo_moreSmall() {
		return versionNo_moreSmall;
	}

	public void setVersionNo_moreSmall(Integer versionNo_moreSmall) {
		this.versionNo_moreSmall = versionNo_moreSmall;
	}
	public Integer getVersionNo_small() {
		return versionNo_small;
	}

	public void setVersionNo_small(Integer versionNo_small) {
		this.versionNo_small = versionNo_small;
	}
	public List getVersionNo_in() {
		return versionNo_in;
	}

	public void setVersionNo_in(List versionNo_in) {
		this.versionNo_in = versionNo_in;
	}
	public Boolean getVersionNo_isNull() {
		return versionNo_isNull;
	}

	public void setVersionNo_isNull(Boolean versionNo_isNull) {
		this.versionNo_isNull = versionNo_isNull;
	}
	public Boolean getVersionNo_isNotNull() {
		return versionNo_isNotNull;
	}

	public void setVersionNo_isNotNull(Boolean versionNo_isNotNull) {
		this.versionNo_isNotNull = versionNo_isNotNull;
	}
	public boolean getVersionNo_isASC() {
		return versionNo_isASC;
	}

	public void setVersionNo_isASC(boolean versionNo_isASC) {
		this.versionNo_isASC = versionNo_isASC;
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
		buff.append("/testID=").append(testID);
		buff.append("/testID_not=").append(testID_not);
		buff.append("/testID_large=").append(testID_large);
		buff.append("/testID_moreLarge=").append(testID_moreLarge);
		buff.append("/testID_from=").append(testID_from);
		buff.append("/testID_to=").append(testID_to);
		buff.append("/testID_moreSmall=").append(testID_moreSmall);
		buff.append("/testID_small=").append(testID_small);
		buff.append("/testID_in=").append(testID_in);
		buff.append("/testID_isNull=").append(testID_isNull);
		buff.append("/testID_isNotNull=").append(testID_isNotNull);
		buff.append("/testID_isASC=").append(testID_isASC);
		buff.append("/value=").append(value);
		buff.append("/value_not=").append(value_not);
		buff.append("/value_large=").append(value_large);
		buff.append("/value_moreLarge=").append(value_moreLarge);
		buff.append("/value_from=").append(value_from);
		buff.append("/value_to=").append(value_to);
		buff.append("/value_moreSmall=").append(value_moreSmall);
		buff.append("/value_small=").append(value_small);
		buff.append("/value_in=").append(value_in);
		buff.append("/value_isNull=").append(value_isNull);
		buff.append("/value_isNotNull=").append(value_isNotNull);
		buff.append("/value_isASC=").append(value_isASC);
		buff.append("/versionNo=").append(versionNo);
		buff.append("/versionNo_not=").append(versionNo_not);
		buff.append("/versionNo_large=").append(versionNo_large);
		buff.append("/versionNo_moreLarge=").append(versionNo_moreLarge);
		buff.append("/versionNo_from=").append(versionNo_from);
		buff.append("/versionNo_to=").append(versionNo_to);
		buff.append("/versionNo_moreSmall=").append(versionNo_moreSmall);
		buff.append("/versionNo_small=").append(versionNo_small);
		buff.append("/versionNo_in=").append(versionNo_in);
		buff.append("/versionNo_isNull=").append(versionNo_isNull);
		buff.append("/versionNo_isNotNull=").append(versionNo_isNotNull);
		buff.append("/versionNo_isASC=").append(versionNo_isASC);
		buff.append("]");
		return buff.toString();
	}
	
}
