/*
 * 作成日: 2006/03/07
 *
 */
package org.seasar.buri.oouo.internal.structure;

public class BuriParticipantType {
    private String name;
    private String id;
    private String type;
    
    public final static String OOUOTHIS = "Participant";
    
    public final static String setId_ATTRI = "Id";
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public final static String setName_ATTRI = "Name";
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public final static String setType_ELEMENT = "ParticipantType";
    public final static String setType_ATTRI = "Type";
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("id=").append(id);
        buff.append("/name=").append(name);
        buff.append("/type=").append(type);
        buff.append("]");
        return buff.toString();
    }
    
}
