package org.seasar.buri.dto;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.seasar.buri.common.util.ScriptProcessor;

public class BuriTestUserFindDto {
	public static final String TABLE = "BuriTestUser";
    private ArrayList orderList = new ArrayList();
	
	private Long userID = null;
	private Long userID_not = null;
	private Long userID_large = null;
	private Long userID_moreLarge = null;
	private Long userID_from = null;
	private Long userID_to = null;
	private Long userID_moreSmall = null;
	private Long userID_small = null;
	private List userID_in = null;
	private Boolean userID_isNull = null;
	private Boolean userID_isNotNull = null;
	private boolean userID_isASC = true;
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
	private Integer parentUserID = null;
	private Integer parentUserID_not = null;
	private Integer parentUserID_large = null;
	private Integer parentUserID_moreLarge = null;
	private Integer parentUserID_from = null;
	private Integer parentUserID_to = null;
	private Integer parentUserID_moreSmall = null;
	private Integer parentUserID_small = null;
	private List parentUserID_in = null;
	private Boolean parentUserID_isNull = null;
	private Boolean parentUserID_isNotNull = null;
	private boolean parentUserID_isASC = true;

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public Long getUserID_not() {
		return userID_not;
	}

	public void setUserID_not(Long userID_not) {
		this.userID_not = userID_not;
	}
	public Long getUserID_large() {
		return userID_large;
	}

	public void setUserID_large(Long userID_large) {
		this.userID_large = userID_large;
	}
	public Long getUserID_moreLarge() {
		return userID_moreLarge;
	}

	public void setUserID_moreLarge(Long userID_moreLarge) {
		this.userID_moreLarge = userID_moreLarge;
	}
	public Long getUserID_from() {
		return userID_from;
	}

	public void setUserID_from(Long userID_from) {
		this.userID_from = userID_from;
	}
	public Long getUserID_to() {
		return userID_to;
	}

	public void setUserID_to(Long userID_to) {
		this.userID_to = userID_to;
	}
	public Long getUserID_moreSmall() {
		return userID_moreSmall;
	}

	public void setUserID_moreSmall(Long userID_moreSmall) {
		this.userID_moreSmall = userID_moreSmall;
	}
	public Long getUserID_small() {
		return userID_small;
	}

	public void setUserID_small(Long userID_small) {
		this.userID_small = userID_small;
	}
	public List getUserID_in() {
		return userID_in;
	}

	public void setUserID_in(List userID_in) {
		this.userID_in = userID_in;
	}
	public Boolean getUserID_isNull() {
		return userID_isNull;
	}

	public void setUserID_isNull(Boolean userID_isNull) {
		this.userID_isNull = userID_isNull;
	}
	public Boolean getUserID_isNotNull() {
		return userID_isNotNull;
	}

	public void setUserID_isNotNull(Boolean userID_isNotNull) {
		this.userID_isNotNull = userID_isNotNull;
	}
	public boolean getUserID_isASC() {
		return userID_isASC;
	}

	public void setUserID_isASC(boolean userID_isASC) {
		this.userID_isASC = userID_isASC;
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
		if(userName_matchFull==null) {
			return null;
		}
		return "%"+userName_matchFull+"%";
	}

	public void setUserName_matchFull(String userName_matchFull) {
		this.userName_matchFull = userName_matchFull;
	}
	public String getUserName_matchFront() {
		if(userName_matchFront==null) {
			return null;
		}
		return userName_matchFront+"%";
	}

	public void setUserName_matchFront(String userName_matchFront) {
		this.userName_matchFront = userName_matchFront;
	}
	public String getUserName_matchBack() {
		if(userName_matchBack==null) {
			return null;
		}
		return "%"+userName_matchBack;
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
		if(roleName_matchFull==null) {
			return null;
		}
		return "%"+roleName_matchFull+"%";
	}

	public void setRoleName_matchFull(String roleName_matchFull) {
		this.roleName_matchFull = roleName_matchFull;
	}
	public String getRoleName_matchFront() {
		if(roleName_matchFront==null) {
			return null;
		}
		return roleName_matchFront+"%";
	}

	public void setRoleName_matchFront(String roleName_matchFront) {
		this.roleName_matchFront = roleName_matchFront;
	}
	public String getRoleName_matchBack() {
		if(roleName_matchBack==null) {
			return null;
		}
		return "%"+roleName_matchBack;
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
	public Integer getParentUserID() {
		return parentUserID;
	}

	public void setParentUserID(Integer parentUserID) {
		this.parentUserID = parentUserID;
	}
	public Integer getParentUserID_not() {
		return parentUserID_not;
	}

	public void setParentUserID_not(Integer parentUserID_not) {
		this.parentUserID_not = parentUserID_not;
	}
	public Integer getParentUserID_large() {
		return parentUserID_large;
	}

	public void setParentUserID_large(Integer parentUserID_large) {
		this.parentUserID_large = parentUserID_large;
	}
	public Integer getParentUserID_moreLarge() {
		return parentUserID_moreLarge;
	}

	public void setParentUserID_moreLarge(Integer parentUserID_moreLarge) {
		this.parentUserID_moreLarge = parentUserID_moreLarge;
	}
	public Integer getParentUserID_from() {
		return parentUserID_from;
	}

	public void setParentUserID_from(Integer parentUserID_from) {
		this.parentUserID_from = parentUserID_from;
	}
	public Integer getParentUserID_to() {
		return parentUserID_to;
	}

	public void setParentUserID_to(Integer parentUserID_to) {
		this.parentUserID_to = parentUserID_to;
	}
	public Integer getParentUserID_moreSmall() {
		return parentUserID_moreSmall;
	}

	public void setParentUserID_moreSmall(Integer parentUserID_moreSmall) {
		this.parentUserID_moreSmall = parentUserID_moreSmall;
	}
	public Integer getParentUserID_small() {
		return parentUserID_small;
	}

	public void setParentUserID_small(Integer parentUserID_small) {
		this.parentUserID_small = parentUserID_small;
	}
	public List getParentUserID_in() {
		return parentUserID_in;
	}

	public void setParentUserID_in(List parentUserID_in) {
		this.parentUserID_in = parentUserID_in;
	}
	public Boolean getParentUserID_isNull() {
		return parentUserID_isNull;
	}

	public void setParentUserID_isNull(Boolean parentUserID_isNull) {
		this.parentUserID_isNull = parentUserID_isNull;
	}
	public Boolean getParentUserID_isNotNull() {
		return parentUserID_isNotNull;
	}

	public void setParentUserID_isNotNull(Boolean parentUserID_isNotNull) {
		this.parentUserID_isNotNull = parentUserID_isNotNull;
	}
	public boolean getParentUserID_isASC() {
		return parentUserID_isASC;
	}

	public void setParentUserID_isASC(boolean parentUserID_isASC) {
		this.parentUserID_isASC = parentUserID_isASC;
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
		buff.append("/userID=").append(userID);
		buff.append("/userID_not=").append(userID_not);
		buff.append("/userID_large=").append(userID_large);
		buff.append("/userID_moreLarge=").append(userID_moreLarge);
		buff.append("/userID_from=").append(userID_from);
		buff.append("/userID_to=").append(userID_to);
		buff.append("/userID_moreSmall=").append(userID_moreSmall);
		buff.append("/userID_small=").append(userID_small);
		buff.append("/userID_in=").append(userID_in);
		buff.append("/userID_isNull=").append(userID_isNull);
		buff.append("/userID_isNotNull=").append(userID_isNotNull);
		buff.append("/userID_isASC=").append(userID_isASC);
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
		buff.append("/parentUserID=").append(parentUserID);
		buff.append("/parentUserID_not=").append(parentUserID_not);
		buff.append("/parentUserID_large=").append(parentUserID_large);
		buff.append("/parentUserID_moreLarge=").append(parentUserID_moreLarge);
		buff.append("/parentUserID_from=").append(parentUserID_from);
		buff.append("/parentUserID_to=").append(parentUserID_to);
		buff.append("/parentUserID_moreSmall=").append(parentUserID_moreSmall);
		buff.append("/parentUserID_small=").append(parentUserID_small);
		buff.append("/parentUserID_in=").append(parentUserID_in);
		buff.append("/parentUserID_isNull=").append(parentUserID_isNull);
		buff.append("/parentUserID_isNotNull=").append(parentUserID_isNotNull);
		buff.append("/parentUserID_isASC=").append(parentUserID_isASC);
		buff.append("]");
		return buff.toString();
	}
	
}
