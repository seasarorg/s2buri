/*
 * 作成日: 2006/03/12
 *
 */
package org.escafe.buri.oouo.reader;

public interface OouoClassDef {
    void setClass(Class clazz);
    Class getTgtClass();
    boolean isChildElement(String elename);
    
    String getElementName();
    void setChild(Object now, String name, Object child);
    boolean isChildAttribute(String name, String type);
    void setChildAttribute(Object now, String name, String type, String value);
    boolean isAttribute(String type);
    void setAttribute(Object now, String type, String value);
    Object getChildObject(String name);
    
    boolean isCallFinMethod();
    void fin(Object now,Object parent);
    boolean isSetTextMethod();
    void setText(Object now, String value);
    
}
