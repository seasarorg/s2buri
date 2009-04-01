/*
 * 作成日: 2006/05/12
 *
 */
package org.escafe.buri.dto;

import java.util.Date;

public class BuriTransactionEntityDto {
    public static final String TABLE = "BuriTransaction";

    //  public static final String stateID_ID = "assigned";;
    public static final String BTID_ID = "sequence, sequenceName=BuriTransactionID";
    private long BTID;
    private Date insertDate;
    private long versionNo;

    public long getBTID() {
        return BTID;
    }

    public void setBTID(long btid) {
        BTID = btid;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public long getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(long versionNo) {
        this.versionNo = versionNo;
    }

    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("/BTID=").append(BTID);
        buff.append("/insertDate=").append(insertDate);
        buff.append("/versionNo=").append(versionNo);
        buff.append("]");
        return buff.toString();
    }
}
