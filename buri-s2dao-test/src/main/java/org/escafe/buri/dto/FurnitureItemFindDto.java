package org.escafe.buri.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.escafe.buri.common.util.ScriptProcessor;

public class FurnitureItemFindDto {
	public static final String TABLE = "FurnitureItem";

	private final ArrayList orderList = new ArrayList();

	private Integer furnitureId = null;

	private Integer furnitureId_not = null;

	private Integer furnitureId_large = null;

	private Integer furnitureId_moreLarge = null;

	private Integer furnitureId_from = null;

	private Integer furnitureId_to = null;

	private Integer furnitureId_moreSmall = null;

	private Integer furnitureId_small = null;

	private List furnitureId_in = null;

	private Boolean furnitureId_isNull = null;

	private Boolean furnitureId_isNotNull = null;

	private boolean furnitureId_isASC = true;

	private String type = null;

	private String type_not = null;

	private String type_large = null;

	private String type_moreLarge = null;

	private String type_from = null;

	private String type_to = null;

	private String type_moreSmall = null;

	private String type_small = null;

	private String type_matchFull = null;

	private String type_matchFront = null;

	private String type_matchBack = null;

	private List type_in = null;

	private Boolean type_isNull = null;

	private Boolean type_isNotNull = null;

	private boolean type_isASC = true;

	private String name = null;

	private String name_not = null;

	private String name_large = null;

	private String name_moreLarge = null;

	private String name_from = null;

	private String name_to = null;

	private String name_moreSmall = null;

	private String name_small = null;

	private String name_matchFull = null;

	private String name_matchFront = null;

	private String name_matchBack = null;

	private List name_in = null;

	private Boolean name_isNull = null;

	private Boolean name_isNotNull = null;

	private boolean name_isASC = true;

	private Date acquisition = null;

	private Date acquisition_not = null;

	private Date acquisition_large = null;

	private Date acquisition_moreLarge = null;

	private Date acquisition_from = null;

	private Date acquisition_to = null;

	private Date acquisition_moreSmall = null;

	private Date acquisition_small = null;

	private List acquisition_in = null;

	private Boolean acquisition_isNull = null;

	private Boolean acquisition_isNotNull = null;

	private boolean acquisition_isASC = true;

	private Integer acquisitionType = null;

	private Integer acquisitionType_not = null;

	private Integer acquisitionType_large = null;

	private Integer acquisitionType_moreLarge = null;

	private Integer acquisitionType_from = null;

	private Integer acquisitionType_to = null;

	private Integer acquisitionType_moreSmall = null;

	private Integer acquisitionType_small = null;

	private List acquisitionType_in = null;

	private Boolean acquisitionType_isNull = null;

	private Boolean acquisitionType_isNotNull = null;

	private boolean acquisitionType_isASC = true;

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

	public Integer getFurnitureId() {
		return furnitureId;
	}

	public void setFurnitureId(Integer furnitureId) {
		this.furnitureId = furnitureId;
	}

	public Integer getFurnitureId_not() {
		return furnitureId_not;
	}

	public void setFurnitureId_not(Integer furnitureId_not) {
		this.furnitureId_not = furnitureId_not;
	}

	public Integer getFurnitureId_large() {
		return furnitureId_large;
	}

	public void setFurnitureId_large(Integer furnitureId_large) {
		this.furnitureId_large = furnitureId_large;
	}

	public Integer getFurnitureId_moreLarge() {
		return furnitureId_moreLarge;
	}

	public void setFurnitureId_moreLarge(Integer furnitureId_moreLarge) {
		this.furnitureId_moreLarge = furnitureId_moreLarge;
	}

	public Integer getFurnitureId_from() {
		return furnitureId_from;
	}

	public void setFurnitureId_from(Integer furnitureId_from) {
		this.furnitureId_from = furnitureId_from;
	}

	public Integer getFurnitureId_to() {
		return furnitureId_to;
	}

	public void setFurnitureId_to(Integer furnitureId_to) {
		this.furnitureId_to = furnitureId_to;
	}

	public Integer getFurnitureId_moreSmall() {
		return furnitureId_moreSmall;
	}

	public void setFurnitureId_moreSmall(Integer furnitureId_moreSmall) {
		this.furnitureId_moreSmall = furnitureId_moreSmall;
	}

	public Integer getFurnitureId_small() {
		return furnitureId_small;
	}

	public void setFurnitureId_small(Integer furnitureId_small) {
		this.furnitureId_small = furnitureId_small;
	}

	public List getFurnitureId_in() {
		return furnitureId_in;
	}

	public void setFurnitureId_in(List furnitureId_in) {
		this.furnitureId_in = furnitureId_in;
	}

	public Boolean getFurnitureId_isNull() {
		return furnitureId_isNull;
	}

	public void setFurnitureId_isNull(Boolean furnitureId_isNull) {
		this.furnitureId_isNull = furnitureId_isNull;
	}

	public Boolean getFurnitureId_isNotNull() {
		return furnitureId_isNotNull;
	}

	public void setFurnitureId_isNotNull(Boolean furnitureId_isNotNull) {
		this.furnitureId_isNotNull = furnitureId_isNotNull;
	}

	public boolean getFurnitureId_isASC() {
		return furnitureId_isASC;
	}

	public void setFurnitureId_isASC(boolean furnitureId_isASC) {
		this.furnitureId_isASC = furnitureId_isASC;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType_not() {
		return type_not;
	}

	public void setType_not(String type_not) {
		this.type_not = type_not;
	}

	public String getType_large() {
		return type_large;
	}

	public void setType_large(String type_large) {
		this.type_large = type_large;
	}

	public String getType_moreLarge() {
		return type_moreLarge;
	}

	public void setType_moreLarge(String type_moreLarge) {
		this.type_moreLarge = type_moreLarge;
	}

	public String getType_from() {
		return type_from;
	}

	public void setType_from(String type_from) {
		this.type_from = type_from;
	}

	public String getType_to() {
		return type_to;
	}

	public void setType_to(String type_to) {
		this.type_to = type_to;
	}

	public String getType_moreSmall() {
		return type_moreSmall;
	}

	public void setType_moreSmall(String type_moreSmall) {
		this.type_moreSmall = type_moreSmall;
	}

	public String getType_small() {
		return type_small;
	}

	public void setType_small(String type_small) {
		this.type_small = type_small;
	}

	public String getType_matchFull() {
		if (type_matchFull == null) {
			return null;
		}
		return "%" + type_matchFull + "%";
	}

	public void setType_matchFull(String type_matchFull) {
		this.type_matchFull = type_matchFull;
	}

	public String getType_matchFront() {
		if (type_matchFront == null) {
			return null;
		}
		return type_matchFront + "%";
	}

	public void setType_matchFront(String type_matchFront) {
		this.type_matchFront = type_matchFront;
	}

	public String getType_matchBack() {
		if (type_matchBack == null) {
			return null;
		}
		return "%" + type_matchBack;
	}

	public void setType_matchBack(String type_matchBack) {
		this.type_matchBack = type_matchBack;
	}

	public List getType_in() {
		return type_in;
	}

	public void setType_in(List type_in) {
		this.type_in = type_in;
	}

	public Boolean getType_isNull() {
		return type_isNull;
	}

	public void setType_isNull(Boolean type_isNull) {
		this.type_isNull = type_isNull;
	}

	public Boolean getType_isNotNull() {
		return type_isNotNull;
	}

	public void setType_isNotNull(Boolean type_isNotNull) {
		this.type_isNotNull = type_isNotNull;
	}

	public boolean getType_isASC() {
		return type_isASC;
	}

	public void setType_isASC(boolean type_isASC) {
		this.type_isASC = type_isASC;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName_not() {
		return name_not;
	}

	public void setName_not(String name_not) {
		this.name_not = name_not;
	}

	public String getName_large() {
		return name_large;
	}

	public void setName_large(String name_large) {
		this.name_large = name_large;
	}

	public String getName_moreLarge() {
		return name_moreLarge;
	}

	public void setName_moreLarge(String name_moreLarge) {
		this.name_moreLarge = name_moreLarge;
	}

	public String getName_from() {
		return name_from;
	}

	public void setName_from(String name_from) {
		this.name_from = name_from;
	}

	public String getName_to() {
		return name_to;
	}

	public void setName_to(String name_to) {
		this.name_to = name_to;
	}

	public String getName_moreSmall() {
		return name_moreSmall;
	}

	public void setName_moreSmall(String name_moreSmall) {
		this.name_moreSmall = name_moreSmall;
	}

	public String getName_small() {
		return name_small;
	}

	public void setName_small(String name_small) {
		this.name_small = name_small;
	}

	public String getName_matchFull() {
		if (name_matchFull == null) {
			return null;
		}
		return "%" + name_matchFull + "%";
	}

	public void setName_matchFull(String name_matchFull) {
		this.name_matchFull = name_matchFull;
	}

	public String getName_matchFront() {
		if (name_matchFront == null) {
			return null;
		}
		return name_matchFront + "%";
	}

	public void setName_matchFront(String name_matchFront) {
		this.name_matchFront = name_matchFront;
	}

	public String getName_matchBack() {
		if (name_matchBack == null) {
			return null;
		}
		return "%" + name_matchBack;
	}

	public void setName_matchBack(String name_matchBack) {
		this.name_matchBack = name_matchBack;
	}

	public List getName_in() {
		return name_in;
	}

	public void setName_in(List name_in) {
		this.name_in = name_in;
	}

	public Boolean getName_isNull() {
		return name_isNull;
	}

	public void setName_isNull(Boolean name_isNull) {
		this.name_isNull = name_isNull;
	}

	public Boolean getName_isNotNull() {
		return name_isNotNull;
	}

	public void setName_isNotNull(Boolean name_isNotNull) {
		this.name_isNotNull = name_isNotNull;
	}

	public boolean getName_isASC() {
		return name_isASC;
	}

	public void setName_isASC(boolean name_isASC) {
		this.name_isASC = name_isASC;
	}

	public Date getAcquisition() {
		return acquisition;
	}

	public void setAcquisition(Date acquisition) {
		this.acquisition = acquisition;
	}

	public Date getAcquisition_not() {
		return acquisition_not;
	}

	public void setAcquisition_not(Date acquisition_not) {
		this.acquisition_not = acquisition_not;
	}

	public Date getAcquisition_large() {
		return acquisition_large;
	}

	public void setAcquisition_large(Date acquisition_large) {
		this.acquisition_large = acquisition_large;
	}

	public Date getAcquisition_moreLarge() {
		return acquisition_moreLarge;
	}

	public void setAcquisition_moreLarge(Date acquisition_moreLarge) {
		this.acquisition_moreLarge = acquisition_moreLarge;
	}

	public Date getAcquisition_from() {
		return acquisition_from;
	}

	public void setAcquisition_from(Date acquisition_from) {
		this.acquisition_from = acquisition_from;
	}

	public Date getAcquisition_to() {
		return acquisition_to;
	}

	public void setAcquisition_to(Date acquisition_to) {
		this.acquisition_to = acquisition_to;
	}

	public Date getAcquisition_moreSmall() {
		return acquisition_moreSmall;
	}

	public void setAcquisition_moreSmall(Date acquisition_moreSmall) {
		this.acquisition_moreSmall = acquisition_moreSmall;
	}

	public Date getAcquisition_small() {
		return acquisition_small;
	}

	public void setAcquisition_small(Date acquisition_small) {
		this.acquisition_small = acquisition_small;
	}

	public List getAcquisition_in() {
		return acquisition_in;
	}

	public void setAcquisition_in(List acquisition_in) {
		this.acquisition_in = acquisition_in;
	}

	public Boolean getAcquisition_isNull() {
		return acquisition_isNull;
	}

	public void setAcquisition_isNull(Boolean acquisition_isNull) {
		this.acquisition_isNull = acquisition_isNull;
	}

	public Boolean getAcquisition_isNotNull() {
		return acquisition_isNotNull;
	}

	public void setAcquisition_isNotNull(Boolean acquisition_isNotNull) {
		this.acquisition_isNotNull = acquisition_isNotNull;
	}

	public boolean getAcquisition_isASC() {
		return acquisition_isASC;
	}

	public void setAcquisition_isASC(boolean acquisition_isASC) {
		this.acquisition_isASC = acquisition_isASC;
	}

	public Integer getAcquisitionType() {
		return acquisitionType;
	}

	public void setAcquisitionType(Integer acquisitionType) {
		this.acquisitionType = acquisitionType;
	}

	public Integer getAcquisitionType_not() {
		return acquisitionType_not;
	}

	public void setAcquisitionType_not(Integer acquisitionType_not) {
		this.acquisitionType_not = acquisitionType_not;
	}

	public Integer getAcquisitionType_large() {
		return acquisitionType_large;
	}

	public void setAcquisitionType_large(Integer acquisitionType_large) {
		this.acquisitionType_large = acquisitionType_large;
	}

	public Integer getAcquisitionType_moreLarge() {
		return acquisitionType_moreLarge;
	}

	public void setAcquisitionType_moreLarge(Integer acquisitionType_moreLarge) {
		this.acquisitionType_moreLarge = acquisitionType_moreLarge;
	}

	public Integer getAcquisitionType_from() {
		return acquisitionType_from;
	}

	public void setAcquisitionType_from(Integer acquisitionType_from) {
		this.acquisitionType_from = acquisitionType_from;
	}

	public Integer getAcquisitionType_to() {
		return acquisitionType_to;
	}

	public void setAcquisitionType_to(Integer acquisitionType_to) {
		this.acquisitionType_to = acquisitionType_to;
	}

	public Integer getAcquisitionType_moreSmall() {
		return acquisitionType_moreSmall;
	}

	public void setAcquisitionType_moreSmall(Integer acquisitionType_moreSmall) {
		this.acquisitionType_moreSmall = acquisitionType_moreSmall;
	}

	public Integer getAcquisitionType_small() {
		return acquisitionType_small;
	}

	public void setAcquisitionType_small(Integer acquisitionType_small) {
		this.acquisitionType_small = acquisitionType_small;
	}

	public List getAcquisitionType_in() {
		return acquisitionType_in;
	}

	public void setAcquisitionType_in(List acquisitionType_in) {
		this.acquisitionType_in = acquisitionType_in;
	}

	public Boolean getAcquisitionType_isNull() {
		return acquisitionType_isNull;
	}

	public void setAcquisitionType_isNull(Boolean acquisitionType_isNull) {
		this.acquisitionType_isNull = acquisitionType_isNull;
	}

	public Boolean getAcquisitionType_isNotNull() {
		return acquisitionType_isNotNull;
	}

	public void setAcquisitionType_isNotNull(Boolean acquisitionType_isNotNull) {
		this.acquisitionType_isNotNull = acquisitionType_isNotNull;
	}

	public boolean getAcquisitionType_isASC() {
		return acquisitionType_isASC;
	}

	public void setAcquisitionType_isASC(boolean acquisitionType_isASC) {
		this.acquisitionType_isASC = acquisitionType_isASC;
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
		buff.append("/furnitureId=").append(furnitureId);
		buff.append("/furnitureId_not=").append(furnitureId_not);
		buff.append("/furnitureId_large=").append(furnitureId_large);
		buff.append("/furnitureId_moreLarge=").append(furnitureId_moreLarge);
		buff.append("/furnitureId_from=").append(furnitureId_from);
		buff.append("/furnitureId_to=").append(furnitureId_to);
		buff.append("/furnitureId_moreSmall=").append(furnitureId_moreSmall);
		buff.append("/furnitureId_small=").append(furnitureId_small);
		buff.append("/furnitureId_in=").append(furnitureId_in);
		buff.append("/furnitureId_isNull=").append(furnitureId_isNull);
		buff.append("/furnitureId_isNotNull=").append(furnitureId_isNotNull);
		buff.append("/furnitureId_isASC=").append(furnitureId_isASC);
		buff.append("/type=").append(type);
		buff.append("/type_not=").append(type_not);
		buff.append("/type_large=").append(type_large);
		buff.append("/type_moreLarge=").append(type_moreLarge);
		buff.append("/type_from=").append(type_from);
		buff.append("/type_to=").append(type_to);
		buff.append("/type_moreSmall=").append(type_moreSmall);
		buff.append("/type_small=").append(type_small);
		buff.append("/type_in=").append(type_in);
		buff.append("/type_isNull=").append(type_isNull);
		buff.append("/type_isNotNull=").append(type_isNotNull);
		buff.append("/type_isASC=").append(type_isASC);
		buff.append("/name=").append(name);
		buff.append("/name_not=").append(name_not);
		buff.append("/name_large=").append(name_large);
		buff.append("/name_moreLarge=").append(name_moreLarge);
		buff.append("/name_from=").append(name_from);
		buff.append("/name_to=").append(name_to);
		buff.append("/name_moreSmall=").append(name_moreSmall);
		buff.append("/name_small=").append(name_small);
		buff.append("/name_in=").append(name_in);
		buff.append("/name_isNull=").append(name_isNull);
		buff.append("/name_isNotNull=").append(name_isNotNull);
		buff.append("/name_isASC=").append(name_isASC);
		buff.append("/acquisition=").append(acquisition);
		buff.append("/acquisition_not=").append(acquisition_not);
		buff.append("/acquisition_large=").append(acquisition_large);
		buff.append("/acquisition_moreLarge=").append(acquisition_moreLarge);
		buff.append("/acquisition_from=").append(acquisition_from);
		buff.append("/acquisition_to=").append(acquisition_to);
		buff.append("/acquisition_moreSmall=").append(acquisition_moreSmall);
		buff.append("/acquisition_small=").append(acquisition_small);
		buff.append("/acquisition_in=").append(acquisition_in);
		buff.append("/acquisition_isNull=").append(acquisition_isNull);
		buff.append("/acquisition_isNotNull=").append(acquisition_isNotNull);
		buff.append("/acquisition_isASC=").append(acquisition_isASC);
		buff.append("/acquisitionType=").append(acquisitionType);
		buff.append("/acquisitionType_not=").append(acquisitionType_not);
		buff.append("/acquisitionType_large=").append(acquisitionType_large);
		buff.append("/acquisitionType_moreLarge=").append(
		    acquisitionType_moreLarge);
		buff.append("/acquisitionType_from=").append(acquisitionType_from);
		buff.append("/acquisitionType_to=").append(acquisitionType_to);
		buff.append("/acquisitionType_moreSmall=").append(
		    acquisitionType_moreSmall);
		buff.append("/acquisitionType_small=").append(acquisitionType_small);
		buff.append("/acquisitionType_in=").append(acquisitionType_in);
		buff.append("/acquisitionType_isNull=").append(acquisitionType_isNull);
		buff.append("/acquisitionType_isNotNull=").append(
		    acquisitionType_isNotNull);
		buff.append("/acquisitionType_isASC=").append(acquisitionType_isASC);
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
