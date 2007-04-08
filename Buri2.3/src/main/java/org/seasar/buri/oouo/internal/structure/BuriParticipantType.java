/*
 * 作成日: 2006/03/07
 *
 */
package org.seasar.buri.oouo.internal.structure;

public class BuriParticipantType {
    private String name;
    private String id;
    private String role;
    
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
    public final static String setRole_ELEMENT = "ParticipantType";
    public final static String setRole_ATTRI = "Type";
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("id=").append(id);
        buff.append("/name=").append(name);
        buff.append("/role=").append(role);
        buff.append("]");
        return buff.toString();
    }
    
}
