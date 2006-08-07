/*
 * çÏê¨ì˙: 2005/08/18
 *
 */
package org.seasar.buri.dto;

import java.util.Date;

public class FurnitureItemDto {
    public static final String TABLE = "FurnitureItem";

    public static final String furnitureID_ID = "sequence, sequenceName=FurnitureItemID";
    private long FurnitureID;
    private String Type;
    private String Name;
    private Date Acquisition;
    private long acquisitionType;
    private int versionNo;

    public Date getAcquisition() {
        return Acquisition;
    }
    public void setAcquisition(Date acquisition) {
        Acquisition = acquisition;
    }
    public long getFurnitureID() {
        return FurnitureID;
    }
    public void setFurnitureID(long furnitureID) {
        FurnitureID = furnitureID;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getType() {
        return Type;
    }
    public void setType(String type) {
        Type = type;
    }
    public int getVersionNo() {
        return versionNo;
    }
    public void setVersionNo(int versionNo) {
        this.versionNo = versionNo;
    }
    public long getAcquisitionType() {
        return acquisitionType;
    }
    public void setAcquisitionType(long acquisitionType) {
        this.acquisitionType = acquisitionType;
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
        StringBuffer buffer = new StringBuffer("[");
        buffer.append("FurnitureID=").append(FurnitureID);
        buffer.append("/Type=").append(Type);
        buffer.append("/Name=").append(Name);
        buffer.append("/Acquisition=").append(Acquisition);
        buffer.append("/acquisitionType=").append(acquisitionType);
        buffer.append("/versionNo=").append(versionNo);
        buffer.append("]");
        return buffer.toString();
    }
}
