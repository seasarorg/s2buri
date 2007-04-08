/*
 * 作成日: 2006/03/15
 *
 */
package org.seasar.buri.oouo.reader.impl;

import java.lang.reflect.Field;

public class OouoSig {
    private String sig;
    private int action;
    private Field field;
    
    public static final int THIS = 64;
    public static final int FIN = 128;

    public static final int NESTELEMENT = 3;
    public static final int ELEMENT = 2;
    public static final int ATTRI = 4;
    public static final int TEXT = 8;
    
    public OouoSig copy(Field tgt) {
        OouoSig sig = new OouoSig();
        sig.sig = this.sig;
        sig.action = this.action;
        sig.field = tgt;
        return sig;
    }
    
    public int getAction() {
        return action;
    }
    public void setAction(int action) {
        this.action = action;
    }
    public String getSig() {
        return sig;
    }
    public void setSig(String sig) {
        this.sig = sig;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
    
}
