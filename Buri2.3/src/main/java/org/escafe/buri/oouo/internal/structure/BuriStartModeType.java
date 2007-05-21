/*
 * 作成日: 2006/03/20
 *
 */
package org.escafe.buri.oouo.internal.structure;

public class BuriStartModeType {
    private boolean isAuto = true;
    public final static String OOUOTHIS = "StartMode";
    
    public final static String setAutomatic_ELEMENT = "Automatic";
    public void setAutomatic() {
        isAuto = true;
    }
    
    public final static String setManual_ELEMENT = "Manual";
    public void setManual() {
        isAuto = false;
    }
    
    public boolean isAutomatic() {
        return isAuto;
    }
    
    public boolean isManual() {
        return ( ! isAuto);
    }
    
    public String toString() {
        if(isAuto) {
            return "StartMode=Automatic";
        } else {
            return "StartMode=Manual";
        }
    }
}
