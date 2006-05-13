/*
 * ì¬“ú: 2006/03/24
 *
 */
package org.seasar.buri.engine;

public class BuriRole {
    private String idVar;
    private String idNum;
    public String getIdNum() {
        return idNum;
    }
    public void setIdNum(String idnum) {
        this.idNum = idnum;
    }
    public String getIdVar() {
        return idVar;
    }
    public void setIdVar(String idVar) {
        this.idVar = idVar;
    }
    public String toString() {
        return "[idVar="+idVar+"/idNum="+idNum+"]";
    }
}
