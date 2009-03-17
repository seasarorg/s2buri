package org.escafe.buri.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.escafe.buri.common.util.ScriptProcessor;

public class BuriTestUserFindDto {
	public static final String TABLE = "BuriTestUser";

	private final ArrayList orderList = new ArrayList();

	private Long userId = null;

	private Long userId_not = null;

	private Long userId_large = null;

	private Long userId_moreLarge = null;

	private Long userId_from = null;

	private Long userId_to = null;

	private Long userId_moreSmall = null;

	private Long userId_small = null;

	private List<Long> userId_in = null;

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

	private List<String> userName_in = null;

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

	private List<String> roleName_in = null;

	private Boolean roleName_isNull = null;

	private Boolean roleName_isNotNull = null;

	private boolean roleName_isASC = true;

	private Integer parentuserId = null;

	private Integer parentuserId_not = null;

	private Integer parentuserId_large = null;

	private Integer parentuserId_moreLarge = null;

	private Integer parentuserId_from = null;

	private Integer parentuserId_to = null;

	private Integer parentuserId_moreSmall = null;

	private Integer parentuserId_small = null;

	private List<Long> parentuserId_in = null;

	private Boolean parentuserId_isNull = null;

	private Boolean parentuserId_isNotNull = null;

	private boolean parentuserId_isASC = true;

	public Long getuserId() {
		return userId;
	}

	public void setuserId(Long userId) {
		this.userId = userId;
	}

	public Long getuserId_not() {
		return userId_not;
	}

	public void setuserId_not(Long userId_not) {
		this.userId_not = userId_not;
	}

	public Long getuserId_large() {
		return userId_large;
	}

	public void setuserId_large(Long userId_large) {
		this.userId_large = userId_large;
	}

	public Long getuserId_moreLarge() {
		return userId_moreLarge;
	}

	public void setuserId_moreLarge(Long userId_moreLarge) {
		this.userId_moreLarge = userId_moreLarge;
	}

	public Long getuserId_from() {
		return userId_from;
	}

	public void setuserId_from(Long userId_from) {
		this.userId_from = userId_from;
	}

	public Long getuserId_to() {
		return userId_to;
	}

	public void setuserId_to(Long userId_to) {
		this.userId_to = userId_to;
	}

	public Long getuserId_moreSmall() {
		return userId_moreSmall;
	}

	public void setuserId_moreSmall(Long userId_moreSmall) {
		this.userId_moreSmall = userId_moreSmall;
	}

	public Long getuserId_small() {
		return userId_small;
	}

	public void setuserId_small(Long userId_small) {
		this.userId_small = userId_small;
	}

	public List getuserId_in() {
		return userId_in;
	}

	public void setuserId_in(List userId_in) {
		this.userId_in = userId_in;
	}

	public Boolean getuserId_isNull() {
		return userId_isNull;
	}

	public void setuserId_isNull(Boolean userId_isNull) {
		this.userId_isNull = userId_isNull;
	}

	public Boolean getuserId_isNotNull() {
		return userId_isNotNull;
	}

	public void setuserId_isNotNull(Boolean userId_isNotNull) {
		this.userId_isNotNull = userId_isNotNull;
	}

	public boolean getuserId_isASC() {
		return userId_isASC;
	}

	public void setuserId_isASC(boolean userId_isASC) {
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

	public Integer getParentuserId() {
		return parentuserId;
	}

	public void setParentuserId(Integer parentuserId) {
		this.parentuserId = parentuserId;
	}

	public Integer getParentuserId_not() {
		return parentuserId_not;
	}

	public void setParentuserId_not(Integer parentuserId_not) {
		this.parentuserId_not = parentuserId_not;
	}

	public Integer getParentuserId_large() {
		return parentuserId_large;
	}

	public void setParentuserId_large(Integer parentuserId_large) {
		this.parentuserId_large = parentuserId_large;
	}

	public Integer getParentuserId_moreLarge() {
		return parentuserId_moreLarge;
	}

	public void setParentuserId_moreLarge(Integer parentuserId_moreLarge) {
		this.parentuserId_moreLarge = parentuserId_moreLarge;
	}

	public Integer getParentuserId_from() {
		return parentuserId_from;
	}

	public void setParentuserId_from(Integer parentuserId_from) {
		this.parentuserId_from = parentuserId_from;
	}

	public Integer getParentuserId_to() {
		return parentuserId_to;
	}

	public void setParentuserId_to(Integer parentuserId_to) {
		this.parentuserId_to = parentuserId_to;
	}

	public Integer getParentuserId_moreSmall() {
		return parentuserId_moreSmall;
	}

	public void setParentuserId_moreSmall(Integer parentuserId_moreSmall) {
		this.parentuserId_moreSmall = parentuserId_moreSmall;
	}

	public Integer getParentuserId_small() {
		return parentuserId_small;
	}

	public void setParentuserId_small(Integer parentuserId_small) {
		this.parentuserId_small = parentuserId_small;
	}

	public List getParentuserId_in() {
		return parentuserId_in;
	}

	public void setParentuserId_in(List parentuserId_in) {
		this.parentuserId_in = parentuserId_in;
	}

	public Boolean getParentuserId_isNull() {
		return parentuserId_isNull;
	}

	public void setParentuserId_isNull(Boolean parentuserId_isNull) {
		this.parentuserId_isNull = parentuserId_isNull;
	}

	public Boolean getParentuserId_isNotNull() {
		return parentuserId_isNotNull;
	}

	public void setParentuserId_isNotNull(Boolean parentuserId_isNotNull) {
		this.parentuserId_isNotNull = parentuserId_isNotNull;
	}

	public boolean getParentuserId_isASC() {
		return parentuserId_isASC;
	}

	public void setParentuserId_isASC(boolean parentuserId_isASC) {
		this.parentuserId_isASC = parentuserId_isASC;
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
		buff.append("/parentuserId=").append(parentuserId);
		buff.append("/parentuserId_not=").append(parentuserId_not);
		buff.append("/parentuserId_large=").append(parentuserId_large);
		buff.append("/parentuserId_moreLarge=").append(parentuserId_moreLarge);
		buff.append("/parentuserId_from=").append(parentuserId_from);
		buff.append("/parentuserId_to=").append(parentuserId_to);
		buff.append("/parentuserId_moreSmall=").append(parentuserId_moreSmall);
		buff.append("/parentuserId_small=").append(parentuserId_small);
		buff.append("/parentuserId_in=").append(parentuserId_in);
		buff.append("/parentuserId_isNull=").append(parentuserId_isNull);
		buff.append("/parentuserId_isNotNull=").append(parentuserId_isNotNull);
		buff.append("/parentuserId_isASC=").append(parentuserId_isASC);
		buff.append("]");
		return buff.toString();
	}
}
