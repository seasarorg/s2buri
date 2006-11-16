package org.seasar.buri.dto;

import java.util.Date;

public class FurnitureItemDto {
	public static final String TABLE = "FurnitureItem";

	public static final String furnitureID_ID = "sequence, sequenceName=FurnitureItemID";
	private long furnitureID;
	private String type = "";
	private String name = "";
	private Date acquisition;
	private int acquisitionType;
	private int versionNo;
	
	public long getFurnitureID() {
		return furnitureID;
	}

	public void setFurnitureID(long furnitureID) {
		this.furnitureID = furnitureID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getAcquisition() {
		return acquisition;
	}

	public void setAcquisition(Date acquisition) {
		this.acquisition = acquisition;
	}

	public int getAcquisitionType() {
		return acquisitionType;
	}

	public void setAcquisitionType(int acquisitionType) {
		this.acquisitionType = acquisitionType;
	}

	public int getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}
    
    public void setAcquisitionTypeBuy() {
        acquisitionType = 0;
    }
    public void setAcquisitionTypeLease() {
        acquisitionType = 1;
    }
    public boolean isBuying() {
        return acquisitionType == 0;
    }
    public boolean isLease() {
        return acquisitionType == 1;
    }

	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/furnitureID=").append(furnitureID);
		buff.append("/type=").append(type);
		buff.append("/name=").append(name);
		buff.append("/acquisition=").append(acquisition);
		buff.append("/acquisitionType=").append(acquisitionType);
		buff.append("/versionNo=").append(versionNo);
		buff.append("]");
		return buff.toString();
	}
	
}
