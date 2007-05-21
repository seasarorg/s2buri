/*
 * 作成日: 2006/07/14
 *
 */
package org.escafe.buri.oouo.internal.structure;

public class BuriValidFromType {
    private String fromDate;
    public final static String OOUOTHIS = "ValidFrom";

    public String getFromDate() {
        return fromDate;
    }

    public final static String setFromDate_OOUOTEXT = "";

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("/fromDate=").append(fromDate);
        buff.append("]");
        return buff.toString();
    }

}
