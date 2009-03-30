package org.escafe.buri.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.escafe.buri.common.util.ScriptProcessor;

public class BuriTestUserFindDto {
	public static final String TABLE = "BURI_TEST_USER";

	private final ArrayList orderList = new ArrayList();

	private Long userId = null;

	private Long userId_not = null;

	private Long userId_large = null;

	private Long userId_moreLarge = null;

	private Long userId_from = null;

	private Long userId_to = null;

	private Long userId_moreSmall = null;

	private Long userId_small = null;

	private List userId_in = null;

	private Boolean userId_isNull = null;

	private Boolean userId_isNotNull = null;

	private boolean userId_isASC = true;

	private String userName = null;

	private String userName_not = null;

	private String userName_large = null;

	private String userName_moreLarge = null;

	private String userName_from = null;

	private String userName_to = null;

	private String userName_moreSmall = null;

	private String userName_small = null;

	private String userName_matchFull = null;

	private String userName_matchFront = null;

	private String userName_matchBack = null;

	private List userName_in = null;

	private Boolean userName_isNull = null;

	private Boolean userName_isNotNull = null;

	private boolean userName_isASC = true;

	private String roleName = null;

	private String roleName_not = null;

	private String roleName_large = null;

	private String roleName_moreLarge = null;

	private String roleName_from = null;

	private String roleName_to = null;

	private String roleName_moreSmall = null;

	private String roleName_small = null;

	private String roleName_matchFull = null;

	private String roleName_matchFront = null;

	private String roleName_matchBack = null;

	private List roleName_in = null;

	private Boolean roleName_isNull = null;

	private Boolean roleName_isNotNull = null;

	private boolean roleName_isASC = true;

	private Integer parentUserId = null;

	private Integer parentUserId_not = null;

	private Integer parentUserId_large = null;

	private Integer parentUserId_moreLarge = null;

	private Integer parentUserId_from = null;

	private Integer parentUserId_to = null;

	private Integer parentUserId_moreSmall = null;

	private Integer parentUserId_small = null;

	private List parentUserId_in = null;

	private Boolean parentUserId_isNull = null;

	private Boolean parentUserId_isNotNull = null;

	private boolean parentUserId_isASC = true;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId_not() {
		return userId_not;
	}

	public void setUserId_not(Long userId_not) {
		this.userId_not = userId_not;
	}

	public Long getUserId_large() {
		return userId_large;
	}

	public void setUserId_large(Long userId_large) {
		this.userId_large = userId_large;
	}

	public Long getUserId_moreLarge() {
		return userId_moreLarge;
	}

	public void setUserId_moreLarge(Long userId_moreLarge) {
		this.userId_moreLarge = userId_moreLarge;
	}

	public Long getUserId_from() {
		return userId_from;
	}

	public void setUserId_from(Long userId_from) {
		this.userId_from = userId_from;
	}

	public Long getUserId_to() {
		return userId_to;
	}

	public void setUserId_to(Long userId_to) {
		this.userId_to = userId_to;
	}

	public Long getUserId_moreSmall() {
		return userId_moreSmall;
	}

	public void setUserId_moreSmall(Long userId_moreSmall) {
		this.userId_moreSmall = userId_moreSmall;
	}

	public Long getUserId_small() {
		return userId_small;
	}

	public void setUserId_small(Long userId_small) {
		this.userId_small = userId_small;
	}

	public List getUserId_in() {
		return userId_in;
	}

	public void setUserId_in(List userId_in) {
		this.userId_in = userId_in;
	}

	public Boolean getUserId_isNull() {
		return userId_isNull;
	}

	public void setUserId_isNull(Boolean userId_isNull) {
		this.userId_isNull = userId_isNull;
	}

	public Boolean getUserId_isNotNull() {
		return userId_isNotNull;
	}

	public void setUserId_isNotNull(Boolean userId_isNotNull) {
		this.userId_isNotNull = userId_isNotNull;
	}

	public boolean getUserId_isASC() {
		return userId_isASC;
	}

	public void setUserId_isASC(boolean userId_isASC) {
		this.userId_isASC = userId_isASC;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName_not() {
		return userName_not;
	}

	public void setUserName_not(String userName_not) {
		this.userName_not = userName_not;
	}

	public String getUserName_large() {
		return userName_large;
	}

	public void setUserName_large(String userName_large) {
		this.userName_large = userName_large;
	}

	public String getUserName_moreLarge() {
		return userName_moreLarge;
	}

	public void setUserName_moreLarge(String userName_moreLarge) {
		this.userName_moreLarge = userName_moreLarge;
	}

	public String getUserName_from() {
		return userName_from;
	}

	public void setUserName_from(String userName_from) {
		this.userName_from = userName_from;
	}

	public String getUserName_to() {
		return userName_to;
	}

	public void setUserName_to(String userName_to) {
		this.userName_to = userName_to;
	}

	public String getUserName_moreSmall() {
		return userName_moreSmall;
	}

	public void setUserName_moreSmall(String userName_moreSmall) {
		this.userName_moreSmall = userName_moreSmall;
	}

	public String getUserName_small() {
		return userName_small;
	}

	public void setUserName_small(String userName_small) {
		this.userName_small = userName_small;
	}

	public String getUserName_matchFull() {
		if (userName_matchFull == null) {
			return null;
		}
		return "%" + userName_matchFull + "%";
	}

	public void setUserName_matchFull(String userName_matchFull) {
		this.userName_matchFull = userName_matchFull;
	}

	public String getUserName_matchFront() {
		if (userName_matchFront == null) {
			return null;
		}
		return userName_matchFront + "%";
	}

	public void setUserName_matchFront(String userName_matchFront) {
		this.userName_matchFront = userName_matchFront;
	}

	public String getUserName_matchBack() {
		if (userName_matchBack == null) {
			return null;
		}
		return "%" + userName_matchBack;
	}

	public void setUserName_matchBack(String userName_matchBack) {
		this.userName_matchBack = userName_matchBack;
	}

	public List getUserName_in() {
		return userName_in;
	}

	public void setUserName_in(List userName_in) {
		this.userName_in = userName_in;
	}

	public Boolean getUserName_isNull() {
		return userName_isNull;
	}

	public void setUserName_isNull(Boolean userName_isNull) {
		this.userName_isNull = userName_isNull;
	}

	public Boolean getUserName_isNotNull() {
		return userName_isNotNull;
	}

	public void setUserName_isNotNull(Boolean userName_isNotNull) {
		this.userName_isNotNull = userName_isNotNull;
	}

	public boolean getUserName_isASC() {
		return userName_isASC;
	}

	public void setUserName_isASC(boolean userName_isASC) {
		this.userName_isASC = userName_isASC;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleName_not() {
		return roleName_not;
	}

	public void setRoleName_not(String roleName_not) {
		this.roleName_not = roleName_not;
	}

	public String getRoleName_large() {
		return roleName_large;
	}

	public void setRoleName_large(String roleName_large) {
		this.roleName_large = roleName_large;
	}

	public String getRoleName_moreLarge() {
		return roleName_moreLarge;
	}

	public void setRoleName_moreLarge(String roleName_moreLarge) {
		this.roleName_moreLarge = roleName_moreLarge;
	}

	public String getRoleName_from() {
		return roleName_from;
	}

	public void setRoleName_from(String roleName_from) {
		this.roleName_from = roleName_from;
	}

	public String getRoleName_to() {
		return roleName_to;
	}

	public void setRoleName_to(String roleName_to) {
		this.roleName_to = roleName_to;
	}

	public String getRoleName_moreSmall() {
		return roleName_moreSmall;
	}

	public void setRoleName_moreSmall(String roleName_moreSmall) {
		this.roleName_moreSmall = roleName_moreSmall;
	}

	public String getRoleName_small() {
		return roleName_small;
	}

	public void setRoleName_small(String roleName_small) {
		this.roleName_small = roleName_small;
	}

	public String getRoleName_matchFull() {
		if (roleName_matchFull == null) {
			return null;
		}
		return "%" + roleName_matchFull + "%";
	}

	public void setRoleName_matchFull(String roleName_matchFull) {
		this.roleName_matchFull = roleName_matchFull;
	}

	public String getRoleName_matchFront() {
		if (roleName_matchFront == null) {
			return null;
		}
		return roleName_matchFront + "%";
	}

	public void setRoleName_matchFront(String roleName_matchFront) {
		this.roleName_matchFront = roleName_matchFront;
	}

	public String getRoleName_matchBack() {
		if (roleName_matchBack == null) {
			return null;
		}
		return "%" + roleName_matchBack;
	}

	public void setRoleName_matchBack(String roleName_matchBack) {
		this.roleName_matchBack = roleName_matchBack;
	}

	public List getRoleName_in() {
		return roleName_in;
	}

	public void setRoleName_in(List roleName_in) {
		this.roleName_in = roleName_in;
	}

	public Boolean getRoleName_isNull() {
		return roleName_isNull;
	}

	public void setRoleName_isNull(Boolean roleName_isNull) {
		this.roleName_isNull = roleName_isNull;
	}

	public Boolean getRoleName_isNotNull() {
		return roleName_isNotNull;
	}

	public void setRoleName_isNotNull(Boolean roleName_isNotNull) {
		this.roleName_isNotNull = roleName_isNotNull;
	}

	public boolean getRoleName_isASC() {
		return roleName_isASC;
	}

	public void setRoleName_isASC(boolean roleName_isASC) {
		this.roleName_isASC = roleName_isASC;
	}

	public Integer getParentUserId() {
		return parentUserId;
	}

	public void setParentUserId(Integer parentUserId) {
		this.parentUserId = parentUserId;
	}

	public Integer getParentUserId_not() {
		return parentUserId_not;
	}

	public void setParentUserId_not(Integer parentUserId_not) {
		this.parentUserId_not = parentUserId_not;
	}

	public Integer getParentUserId_large() {
		return parentUserId_large;
	}

	public void setParentUserId_large(Integer parentUserId_large) {
		this.parentUserId_large = parentUserId_large;
	}

	public Integer getParentUserId_moreLarge() {
		return parentUserId_moreLarge;
	}

	public void setParentUserId_moreLarge(Integer parentUserId_moreLarge) {
		this.parentUserId_moreLarge = parentUserId_moreLarge;
	}

	public Integer getParentUserId_from() {
		return parentUserId_from;
	}

	public void setParentUserId_from(Integer parentUserId_from) {
		this.parentUserId_from = parentUserId_from;
	}

	public Integer getParentUserId_to() {
		return parentUserId_to;
	}

	public void setParentUserId_to(Integer parentUserId_to) {
		this.parentUserId_to = parentUserId_to;
	}

	public Integer getParentUserId_moreSmall() {
		return parentUserId_moreSmall;
	}

	public void setParentUserId_moreSmall(Integer parentUserId_moreSmall) {
		this.parentUserId_moreSmall = parentUserId_moreSmall;
	}

	public Integer getParentUserId_small() {
		return parentUserId_small;
	}

	public void setParentUserId_small(Integer parentUserId_small) {
		this.parentUserId_small = parentUserId_small;
	}

	public List getParentUserId_in() {
		return parentUserId_in;
	}

	public void setParentUserId_in(List parentUserId_in) {
		this.parentUserId_in = parentUserId_in;
	}

	public Boolean getParentUserId_isNull() {
		return parentUserId_isNull;
	}

	public void setParentUserId_isNull(Boolean parentUserId_isNull) {
		this.parentUserId_isNull = parentUserId_isNull;
	}

	public Boolean getParentUserId_isNotNull() {
		return parentUserId_isNotNull;
	}

	public void setParentUserId_isNotNull(Boolean parentUserId_isNotNull) {
		this.parentUserId_isNotNull = parentUserId_isNotNull;
	}

	public boolean getParentUserId_isASC() {
		return parentUserId_isASC;
	}

	public void setParentUserId_isASC(boolean parentUserId_isASC) {
		this.parentUserId_isASC = parentUserId_isASC;
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
		buff.append("/userId=").append(userId);
		buff.append("/userId_not=").append(userId_not);
		buff.append("/userId_large=").append(userId_large);
		buff.append("/userId_moreLarge=").append(userId_moreLarge);
		buff.append("/userId_from=").append(userId_from);
		buff.append("/userId_to=").append(userId_to);
		buff.append("/userId_moreSmall=").append(userId_moreSmall);
		buff.append("/userId_small=").append(userId_small);
		buff.append("/userId_in=").append(userId_in);
		buff.append("/userId_isNull=").append(userId_isNull);
		buff.append("/userId_isNotNull=").append(userId_isNotNull);
		buff.append("/userId_isASC=").append(userId_isASC);
		buff.append("/userName=").append(userName);
		buff.append("/userName_not=").append(userName_not);
		buff.append("/userName_large=").append(userName_large);
		buff.append("/userName_moreLarge=").append(userName_moreLarge);
		buff.append("/userName_from=").append(userName_from);
		buff.append("/userName_to=").append(userName_to);
		buff.append("/userName_moreSmall=").append(userName_moreSmall);
		buff.append("/userName_small=").append(userName_small);
		buff.append("/userName_in=").append(userName_in);
		buff.append("/userName_isNull=").append(userName_isNull);
		buff.append("/userName_isNotNull=").append(userName_isNotNull);
		buff.append("/userName_isASC=").append(userName_isASC);
		buff.append("/roleName=").append(roleName);
		buff.append("/roleName_not=").append(roleName_not);
		buff.append("/roleName_large=").append(roleName_large);
		buff.append("/roleName_moreLarge=").append(roleName_moreLarge);
		buff.append("/roleName_from=").append(roleName_from);
		buff.append("/roleName_to=").append(roleName_to);
		buff.append("/roleName_moreSmall=").append(roleName_moreSmall);
		buff.append("/roleName_small=").append(roleName_small);
		buff.append("/roleName_in=").append(roleName_in);
		buff.append("/roleName_isNull=").append(roleName_isNull);
		buff.append("/roleName_isNotNull=").append(roleName_isNotNull);
		buff.append("/roleName_isASC=").append(roleName_isASC);
		buff.append("/parentUserId=").append(parentUserId);
		buff.append("/parentUserId_not=").append(parentUserId_not);
		buff.append("/parentUserId_large=").append(parentUserId_large);
		buff.append("/parentUserId_moreLarge=").append(parentUserId_moreLarge);
		buff.append("/parentUserId_from=").append(parentUserId_from);
		buff.append("/parentUserId_to=").append(parentUserId_to);
		buff.append("/parentUserId_moreSmall=").append(parentUserId_moreSmall);
		buff.append("/parentUserId_small=").append(parentUserId_small);
		buff.append("/parentUserId_in=").append(parentUserId_in);
		buff.append("/parentUserId_isNull=").append(parentUserId_isNull);
		buff.append("/parentUserId_isNotNull=").append(parentUserId_isNotNull);
		buff.append("/parentUserId_isASC=").append(parentUserId_isASC);
		buff.append("]");
		return buff.toString();
	}
}
