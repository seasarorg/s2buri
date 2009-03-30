package org.escafe.buri.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.escafe.buri.common.util.ScriptProcessor;

public class BuriTestINTFindDto {
	public static final String TABLE = "BuriTestINT";

	private final ArrayList orderList = new ArrayList();

	private Long testId = null;

	private Long testId_not = null;

	private Long testId_large = null;

	private Long testId_moreLarge = null;

	private Long testId_from = null;

	private Long testId_to = null;

	private Long testId_moreSmall = null;

	private Long testId_small = null;

	private List testId_in = null;

	private Boolean testId_isNull = null;

	private Boolean testId_isNotNull = null;

	private boolean testId_isASC = true;

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

	private Long versionNo = null;

	private Long versionNo_not = null;

	private Long versionNo_large = null;

	private Long versionNo_moreLarge = null;

	private Long versionNo_from = null;

	private Long versionNo_to = null;

	private Long versionNo_moreSmall = null;

	private Long versionNo_small = null;

	private List versionNo_in = null;

	private Boolean versionNo_isNull = null;

	private Boolean versionNo_isNotNull = null;

	private boolean versionNo_isASC = true;

	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}

	public Long getTestId_not() {
		return testId_not;
	}

	public void setTestId_not(Long testId_not) {
		this.testId_not = testId_not;
	}

	public Long getTestId_large() {
		return testId_large;
	}

	public void setTestId_large(Long testId_large) {
		this.testId_large = testId_large;
	}

	public Long getTestId_moreLarge() {
		return testId_moreLarge;
	}

	public void setTestId_moreLarge(Long testId_moreLarge) {
		this.testId_moreLarge = testId_moreLarge;
	}

	public Long getTestId_from() {
		return testId_from;
	}

	public void setTestId_from(Long testId_from) {
		this.testId_from = testId_from;
	}

	public Long getTestId_to() {
		return testId_to;
	}

	public void setTestId_to(Long testId_to) {
		this.testId_to = testId_to;
	}

	public Long getTestId_moreSmall() {
		return testId_moreSmall;
	}

	public void setTestId_moreSmall(Long testId_moreSmall) {
		this.testId_moreSmall = testId_moreSmall;
	}

	public Long getTestId_small() {
		return testId_small;
	}

	public void setTestId_small(Long testId_small) {
		this.testId_small = testId_small;
	}

	public List getTestId_in() {
		return testId_in;
	}

	public void setTestId_in(List testId_in) {
		this.testId_in = testId_in;
	}

	public Boolean getTestId_isNull() {
		return testId_isNull;
	}

	public void setTestId_isNull(Boolean testId_isNull) {
		this.testId_isNull = testId_isNull;
	}

	public Boolean getTestId_isNotNull() {
		return testId_isNotNull;
	}

	public void setTestId_isNotNull(Boolean testId_isNotNull) {
		this.testId_isNotNull = testId_isNotNull;
	}

	public boolean getTestId_isASC() {
		return testId_isASC;
	}

	public void setTestId_isASC(boolean testId_isASC) {
		this.testId_isASC = testId_isASC;
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
		if (value_matchFull == null) {
			return null;
		}
		return "%" + value_matchFull + "%";
	}

	public void setValue_matchFull(String value_matchFull) {
		this.value_matchFull = value_matchFull;
	}

	public String getValue_matchFront() {
		if (value_matchFront == null) {
			return null;
		}
		return value_matchFront + "%";
	}

	public void setValue_matchFront(String value_matchFront) {
		this.value_matchFront = value_matchFront;
	}

	public String getValue_matchBack() {
		if (value_matchBack == null) {
			return null;
		}
		return "%" + value_matchBack;
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

	public Long getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Long versionNo) {
		this.versionNo = versionNo;
	}

	public Long getVersionNo_not() {
		return versionNo_not;
	}

	public void setVersionNo_not(Long versionNo_not) {
		this.versionNo_not = versionNo_not;
	}

	public Long getVersionNo_large() {
		return versionNo_large;
	}

	public void setVersionNo_large(Long versionNo_large) {
		this.versionNo_large = versionNo_large;
	}

	public Long getVersionNo_moreLarge() {
		return versionNo_moreLarge;
	}

	public void setVersionNo_moreLarge(Long versionNo_moreLarge) {
		this.versionNo_moreLarge = versionNo_moreLarge;
	}

	public Long getVersionNo_from() {
		return versionNo_from;
	}

	public void setVersionNo_from(Long versionNo_from) {
		this.versionNo_from = versionNo_from;
	}

	public Long getVersionNo_to() {
		return versionNo_to;
	}

	public void setVersionNo_to(Long versionNo_to) {
		this.versionNo_to = versionNo_to;
	}

	public Long getVersionNo_moreSmall() {
		return versionNo_moreSmall;
	}

	public void setVersionNo_moreSmall(Long versionNo_moreSmall) {
		this.versionNo_moreSmall = versionNo_moreSmall;
	}

	public Long getVersionNo_small() {
		return versionNo_small;
	}

	public void setVersionNo_small(Long versionNo_small) {
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
		buff.append("/testId=").append(testId);
		buff.append("/testId_not=").append(testId_not);
		buff.append("/testId_large=").append(testId_large);
		buff.append("/testId_moreLarge=").append(testId_moreLarge);
		buff.append("/testId_from=").append(testId_from);
		buff.append("/testId_to=").append(testId_to);
		buff.append("/testId_moreSmall=").append(testId_moreSmall);
		buff.append("/testId_small=").append(testId_small);
		buff.append("/testId_in=").append(testId_in);
		buff.append("/testId_isNull=").append(testId_isNull);
		buff.append("/testId_isNotNull=").append(testId_isNotNull);
		buff.append("/testId_isASC=").append(testId_isASC);
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
