/*
 * �쐬��: 2005/07/02
 *
 */
package org.seasar.buri.dto;


public class BuriTestINTDto {
    public static final String TABLE = "BuriTestINT";

    public static final String testID_ID = "sequence, sequenceName=BuriTestID";
    private long testID;
    private String value;
    private int versionNo;

    public BuriTestINTDto() {
    }

    public long getTestID() {
        return testID;
    }

    public void setTestID(long testID) {
        this.testID = testID;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(int versionNo) {
        this.versionNo = versionNo;
    }
    
    public String toString() {
        StringBuffer buff = new StringBuffer();
        buff.append("testID=").append(testID).append("\n");
        buff.append("value=").append(value).append("\n");
        buff.append("versionNo=").append(versionNo).append("\n");
        return buff.toString();
    }
    
}
