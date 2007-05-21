/*
 * 作成日: 2006/07/14
 *
 */
package org.escafe.buri.oouo.internal.structure;

public class BuriValidToType {
    private String toDate;
    public final static String OOUOTHIS = "ValidTo";

    public String getToDate() {
        return toDate;
    }

    public final static String setToDate_OOUOTEXT = "";
    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
    
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("/toDate=").append(toDate);
        buff.append("]");
        return buff.toString();
    }

}
