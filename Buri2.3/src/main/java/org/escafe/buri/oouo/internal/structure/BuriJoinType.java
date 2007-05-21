/*
 * 作成日: 2006/03/20
 *
 */
package org.escafe.buri.oouo.internal.structure;

public class BuriJoinType {
    private String type;
    private boolean isXOR = true;

    public static final String OOUOTHIS = "Join";


    public String getType() {
        return type;
    }

    public static final String setType_ATTRI = "Type";
    public void setType(String type) {
        this.type = type;
    }
    
    public static final String setupEnd_OOUOFIN = "";
    public void setupEnd() {
        if(type.endsWith("AND")) {
            isXOR = false;
        }
    }
    
    public boolean isXor() {
        return isXOR;
    }
    
    public boolean isAnd() {
        return (! isXOR);
    }

    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("/type=").append(type);
        buff.append("]");
        return buff.toString();
    }
    
}
