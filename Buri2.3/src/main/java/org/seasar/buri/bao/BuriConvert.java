/*
 * çÏê¨ì˙: 2005/12/31
 *
 */
package org.seasar.buri.bao;

public class BuriConvert {
    private Class clazz;
    private String convertOgnl;
    
    public BuriConvert(Class clazz,String convertOgnl) {
        this.clazz = clazz;
        this.convertOgnl = convertOgnl;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getConvertOgnl() {
        return convertOgnl;
    }

    public void setConvertOgnl(String convertOgnl) {
        this.convertOgnl = convertOgnl;
    }
}
