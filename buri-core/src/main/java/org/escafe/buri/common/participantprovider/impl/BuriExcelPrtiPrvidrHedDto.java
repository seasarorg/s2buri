/*
 * 作成日: 2006/07/13
 *
 */
package org.escafe.buri.common.participantprovider.impl;

public class BuriExcelPrtiPrvidrHedDto {
    private String appUserIdName;
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
        return appUserIdName;
    }

    public void setRoleName(String appUserIdName) {
        this.appUserIdName = appUserIdName;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("appUserIdName=").append(appUserIdName);
        buff.append("/idPos=").append(idPos);
        buff.append("/namePos=").append(namePos);
        buff.append("/seq=").append(seq);
        buff.append("]");
        return buff.toString();
    }
}
