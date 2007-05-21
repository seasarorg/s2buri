/*
 * 作成日: 2006/06/19
 *
 */
package org.escafe.buri.oouo.internal.structure;

public class BuriActivityLimitType {
    private String limit="";
    public final static String OOUOTHIS = "Limit";
    
    public String getLimit() {
        return limit;
    }
    public final static String setLimit_OOUOTEXT = "";
    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("/limit=").append(limit);
        buff.append("]");
        return buff.toString();
    }

}
