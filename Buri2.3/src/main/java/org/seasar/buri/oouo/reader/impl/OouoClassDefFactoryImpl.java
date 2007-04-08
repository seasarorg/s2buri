/*
 * 作成日: 2006/03/12
 *
 */
package org.seasar.buri.oouo.reader.impl;

import java.util.HashMap;
import java.util.Map;

import org.seasar.buri.oouo.reader.OouoClassDef;
import org.seasar.buri.oouo.reader.OouoClassDefFactory;
import org.seasar.framework.util.ClassUtil;

public class OouoClassDefFactoryImpl implements OouoClassDefFactory {
    private Map defMap = new HashMap();
    private Map nameClassMap = new HashMap();
    
    public void dispose() {
    	nameClassMap.clear();
    	defMap.clear();
    }
    
    public OouoClassDef create(Class clazz) {
        if(defMap.containsKey(clazz)) {
            return (OouoClassDef)defMap.get(clazz);
        }
        OouoClassDefImpl defImpl = new OouoClassDefImpl();
        defImpl.setClass(clazz);
        defMap.put(clazz,defImpl);
        nameClassMap.put(defImpl.getElementName(),defImpl);
        return defImpl;
    }

    public Object getRoot(String eleName) {
        OouoClassDef defImpl = (OouoClassDef)nameClassMap.get(eleName);
        return ClassUtil.newInstance(defImpl.getTgtClass());
    }

}
