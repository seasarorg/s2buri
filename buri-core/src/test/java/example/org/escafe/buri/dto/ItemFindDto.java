package example.org.escafe.buri.dto;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.escafe.buri.common.util.ScriptProcessor;

public class ItemFindDto {
	public static final String TABLE = "Item";
    private ArrayList orderList = new ArrayList();
	
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
	private String itemName = null;
	private String itemName_not = null;
	private String itemName_large = null;
	private String itemName_moreLarge = null;
	private String itemName_from = null;
	private String itemName_to = null;
	private String itemName_moreSmall = null;
	private String itemName_small = null;
	private String itemName_matchFull = null;
	private String itemName_matchFront = null;
	private String itemName_matchBack = null;
	private List itemName_in = null;
	private Boolean itemName_isNull = null;
	private Boolean itemName_isNotNull = null;
	private boolean itemName_isASC = true;
	private Long price = null;
	private Long price_not = null;
	private Long price_large = null;
	private Long price_moreLarge = null;
	private Long price_from = null;
	private Long price_to = null;
	private Long price_moreSmall = null;
	private Long price_small = null;
	private List price_in = null;
	private Boolean price_isNull = null;
	private Boolean price_isNotNull = null;
	private boolean price_isASC = true;

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
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemName_not() {
		return itemName_not;
	}

	public void setItemName_not(String itemName_not) {
		this.itemName_not = itemName_not;
	}
	public String getItemName_large() {
		return itemName_large;
	}

	public void setItemName_large(String itemName_large) {
		this.itemName_large = itemName_large;
	}
	public String getItemName_moreLarge() {
		return itemName_moreLarge;
	}

	public void setItemName_moreLarge(String itemName_moreLarge) {
		this.itemName_moreLarge = itemName_moreLarge;
	}
	public String getItemName_from() {
		return itemName_from;
	}

	public void setItemName_from(String itemName_from) {
		this.itemName_from = itemName_from;
	}
	public String getItemName_to() {
		return itemName_to;
	}

	public void setItemName_to(String itemName_to) {
		this.itemName_to = itemName_to;
	}
	public String getItemName_moreSmall() {
		return itemName_moreSmall;
	}

	public void setItemName_moreSmall(String itemName_moreSmall) {
		this.itemName_moreSmall = itemName_moreSmall;
	}
	public String getItemName_small() {
		return itemName_small;
	}

	public void setItemName_small(String itemName_small) {
		this.itemName_small = itemName_small;
	}
	public String getItemName_matchFull() {
		if(itemName_matchFull==null) {
			return null;
		}
		return "%"+itemName_matchFull+"%";
	}

	public void setItemName_matchFull(String itemName_matchFull) {
		this.itemName_matchFull = itemName_matchFull;
	}
	public String getItemName_matchFront() {
		if(itemName_matchFront==null) {
			return null;
		}
		return itemName_matchFront+"%";
	}

	public void setItemName_matchFront(String itemName_matchFront) {
		this.itemName_matchFront = itemName_matchFront;
	}
	public String getItemName_matchBack() {
		if(itemName_matchBack==null) {
			return null;
		}
		return "%"+itemName_matchBack;
	}

	public void setItemName_matchBack(String itemName_matchBack) {
		this.itemName_matchBack = itemName_matchBack;
	}
	public List getItemName_in() {
		return itemName_in;
	}

	public void setItemName_in(List itemName_in) {
		this.itemName_in = itemName_in;
	}
	public Boolean getItemName_isNull() {
		return itemName_isNull;
	}

	public void setItemName_isNull(Boolean itemName_isNull) {
		this.itemName_isNull = itemName_isNull;
	}
	public Boolean getItemName_isNotNull() {
		return itemName_isNotNull;
	}

	public void setItemName_isNotNull(Boolean itemName_isNotNull) {
		this.itemName_isNotNull = itemName_isNotNull;
	}
	public boolean getItemName_isASC() {
		return itemName_isASC;
	}

	public void setItemName_isASC(boolean itemName_isASC) {
		this.itemName_isASC = itemName_isASC;
	}
	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}
	public Long getPrice_not() {
		return price_not;
	}

	public void setPrice_not(Long price_not) {
		this.price_not = price_not;
	}
	public Long getPrice_large() {
		return price_large;
	}

	public void setPrice_large(Long price_large) {
		this.price_large = price_large;
	}
	public Long getPrice_moreLarge() {
		return price_moreLarge;
	}

	public void setPrice_moreLarge(Long price_moreLarge) {
		this.price_moreLarge = price_moreLarge;
	}
	public Long getPrice_from() {
		return price_from;
	}

	public void setPrice_from(Long price_from) {
		this.price_from = price_from;
	}
	public Long getPrice_to() {
		return price_to;
	}

	public void setPrice_to(Long price_to) {
		this.price_to = price_to;
	}
	public Long getPrice_moreSmall() {
		return price_moreSmall;
	}

	public void setPrice_moreSmall(Long price_moreSmall) {
		this.price_moreSmall = price_moreSmall;
	}
	public Long getPrice_small() {
		return price_small;
	}

	public void setPrice_small(Long price_small) {
		this.price_small = price_small;
	}
	public List getPrice_in() {
		return price_in;
	}

	public void setPrice_in(List price_in) {
		this.price_in = price_in;
	}
	public Boolean getPrice_isNull() {
		return price_isNull;
	}

	public void setPrice_isNull(Boolean price_isNull) {
		this.price_isNull = price_isNull;
	}
	public Boolean getPrice_isNotNull() {
		return price_isNotNull;
	}

	public void setPrice_isNotNull(Boolean price_isNotNull) {
		this.price_isNotNull = price_isNotNull;
	}
	public boolean getPrice_isASC() {
		return price_isASC;
	}

	public void setPrice_isASC(boolean price_isASC) {
		this.price_isASC = price_isASC;
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
		buff.append("/itemName=").append(itemName);
		buff.append("/itemName_not=").append(itemName_not);
		buff.append("/itemName_large=").append(itemName_large);
		buff.append("/itemName_moreLarge=").append(itemName_moreLarge);
		buff.append("/itemName_from=").append(itemName_from);
		buff.append("/itemName_to=").append(itemName_to);
		buff.append("/itemName_moreSmall=").append(itemName_moreSmall);
		buff.append("/itemName_small=").append(itemName_small);
		buff.append("/itemName_in=").append(itemName_in);
		buff.append("/itemName_isNull=").append(itemName_isNull);
		buff.append("/itemName_isNotNull=").append(itemName_isNotNull);
		buff.append("/itemName_isASC=").append(itemName_isASC);
		buff.append("/price=").append(price);
		buff.append("/price_not=").append(price_not);
		buff.append("/price_large=").append(price_large);
		buff.append("/price_moreLarge=").append(price_moreLarge);
		buff.append("/price_from=").append(price_from);
		buff.append("/price_to=").append(price_to);
		buff.append("/price_moreSmall=").append(price_moreSmall);
		buff.append("/price_small=").append(price_small);
		buff.append("/price_in=").append(price_in);
		buff.append("/price_isNull=").append(price_isNull);
		buff.append("/price_isNotNull=").append(price_isNotNull);
		buff.append("/price_isASC=").append(price_isASC);
		buff.append("]");
		return buff.toString();
	}
	
}
