/*
 * çÏê¨ì˙: 2005/07/02
 *
 */
package org.seasar.buri.dto;

public class BuriTestCHARDto {
    public static final String TABLE = "BuriTestCHAR";

    public static final String testID_ID = "assigned";
    private String testID;
    private String value;
    private int versionNo;

    public BuriTestCHARDto() {
    }

    public String getTestID() {
        return testID;
    }

    public void setTestID(String testID) {
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

}
