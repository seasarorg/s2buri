package org.escafe.buri.dto;

import java.util.Date;

import org.seasar.nadejako.annotation.NakoProperties;
import org.seasar.nadejako.annotation.NakoProperty;

@NakoProperties( {
    @NakoProperty(name = "あいでぃー", property = "furnitureId"),
    @NakoProperty(name = "種類", property = "type"),
    @NakoProperty(name = "名前", property = "name"),
    @NakoProperty(name = "取得日", property = "acquisition"),
    @NakoProperty(name = "取得タイプ", property = "acquisitionType") })
public class FurnitureItemDto {
	public static final String TABLE = "FURNITURE_ITEM";

	public static final String furnitureId_ID =
	    "sequence, sequenceName=FURNITURE_ITEM_SEQ";

	private Long furnitureId;

	private String type = "";

	private String name = "";

	private Date acquisition;

	private Integer acquisitionType;

	private Long versionNo;

	public Long getFurnitureId() {
		return furnitureId;
	}

	public void setFurnitureId(Long furnitureId) {
		this.furnitureId = furnitureId;
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

	public void setAcquisitionType(Integer acquisitionType) {
		this.acquisitionType = acquisitionType;
	}

	public Long getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Long versionNo) {
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

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/furnitureId=").append(furnitureId);
		buff.append("/type=").append(type);
		buff.append("/name=").append(name);
		buff.append("/acquisition=").append(acquisition);
		buff.append("/acquisitionType=").append(acquisitionType);
		buff.append("/versionNo=").append(versionNo);
		buff.append("]");
		return buff.toString();
	}
}
