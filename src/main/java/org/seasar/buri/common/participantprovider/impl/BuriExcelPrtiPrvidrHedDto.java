/*
 * çÏê¨ì˙: 2006/07/13
 *
 */
package org.seasar.buri.common.participantprovider.impl;

public class BuriExcelPrtiPrvidrHedDto {
    private String roleName;
    private int idPos;
    private int namePos;
    private int seq;
    
    public int getIdPos() {
        return idPos;
    }
    public void setIdPos(int idPos) {
        this.idPos = idPos;
    }
    public int getNamePos() {
        return namePos;
    }
    public void setNamePos(int namePos) {
        this.namePos = namePos;
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public int getSeq() {
        return seq;
    }
    public void setSeq(int seq) {
        this.seq = seq;
    }
    
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("roleName=").append(roleName);
        buff.append("/idPos=").append(idPos);
        buff.append("/namePos=").append(namePos);
        buff.append("/seq=").append(seq);
        buff.append("]");
        return buff.toString();
    }
}
