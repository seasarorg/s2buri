/*
 * 作成日: 2005/07/02
 *
 */
package org.escafe.buri.dto;

public class BuriTestManyDto {
    public static final String TABLE = "BuriTestMany";

//    public static final String testID01_ID = "sequence, sequenceName=BuriTestID";
    private long testID01;
    private long testID02;
    private String value;
    private int versionNo;

    public BuriTestManyDto() {
    }

    public long getTestID01() {
        return testID01;
    }

    public void setTestID01(long testID01) {
        this.testID01 = testID01;
    }

    public long getTestID02() {
        return testID02;
    }

    public void setTestID02(long testID02) {
        this.testID02 = testID02;
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
