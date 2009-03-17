/*
 * 作成日: 2006/03/20
 *
 */
package org.escafe.buri.oouo.internal.structure;

public class BuriPerformerType {
    private String id;

    public final static String OOUOTHIS = "Performer";

    public String getId() {
        return id;
    }

    public final static String setId_OOUOTEXT = "";

    public void setId(String id) {
        String con = this.id;
        if (con == null) {
            con = "";
        }
        this.id = con + id;
    }

    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("id=").append(id);
        buff.append("]");
        return buff.toString();
    }
}
