package org.escafe.buri.dto;

import java.util.Date;

public class BuriBranchEntityDto {
    public static final String TABLE = "BuriBranch";

    public static final String branchID_ID = "sequence, sequenceName=BuriBranchID";
    private long branchID;
    private Long parentBranchID;
    private Long pathID;
    private Long dataID;
    private long BTID;
    private Date processDate = null;
    private int versionNo;

    public BuriBranchEntityDto() {
    }

    public long getBranchID() {
        return branchID;
    }

    public void setBranchID(long branchID) {
        this.branchID = branchID;
    }

    public Long getParentBranchID() {
        return parentBranchID;
    }

    public void setParentBranchID(Long parentBranchID) {
        this.parentBranchID = parentBranchID;
    }

    public Long getPathID() {
        return pathID;
    }

    public void setPathID(Long pathID) {
        this.pathID = pathID;
    }

    public Long getDataID() {
        return dataID;
    }

    public void setDataID(Long dataID) {
        this.dataID = dataID;
    }

    public Date getProcessDate() {
        return processDate;
    }

    public void setProcessDate(Date processDate) {
        this.processDate = processDate;
    }

    public int getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(int versionNo) {
        this.versionNo = versionNo;
    }

    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("/branchID=").append(branchID);
        buff.append("/parentBranchID=").append(parentBranchID);
        buff.append("/pathID=").append(pathID);
        buff.append("/dataID=").append(dataID);
        buff.append("/BTID=").append(BTID);
        buff.append("/processDate=").append(processDate);
        buff.append("/versionNo=").append(versionNo);
        buff.append("]");
        return buff.toString();
    }

    public long getBTID() {
        return BTID;
    }

    public void setBTID(long btid) {
        BTID = btid;
    }

}