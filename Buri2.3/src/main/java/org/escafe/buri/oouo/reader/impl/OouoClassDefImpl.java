/*
 * 作成日: 2006/03/12
 *
 */
package org.escafe.buri.oouo.reader.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.escafe.buri.oouo.reader.OouoClassDef;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.FieldUtil;
import org.seasar.framework.util.MethodUtil;

public class OouoClassDefImpl implements OouoClassDef {
    private Class clazz;
    private static List fieldSigs = new ArrayList();
    private Object tgtObj;
    
    private Map methodMap = new HashMap();
    
    private String thisElementName = "";
    private Method finMethod = null;
    private Method textMethod = null;
    
    private Map childElement = new HashMap();
    private Map attriElement = new HashMap();
    private Map attri = new HashMap();
    
    static {
        OouoSig sig = new OouoSig();
        sig.setAction(OouoSig.ATTRI);
        sig.setSig("_ATTRI");
        fieldSigs.add(sig);
        sig = new OouoSig();
        sig.setAction(OouoSig.ELEMENT);
        sig.setSig("_ELEMENT");
        fieldSigs.add(sig);
        sig = new OouoSig();
        sig.setAction(OouoSig.THIS);
        sig.setSig("OOUOTHIS");
        fieldSigs.add(sig);
        sig = new OouoSig();
        sig.setAction(OouoSig.FIN);
        sig.setSig("_OOUOFIN");
        fieldSigs.add(sig);
        sig = new OouoSig();
        sig.setAction(OouoSig.TEXT);
        sig.setSig("_OOUOTEXT");
        fieldSigs.add(sig);
    }
    
    public void dispose() {
    	methodMap.clear();
    	childElement.clear();
    	attriElement.clear();
    	attri.clear();
    	fieldSigs.clear();
    }
    
    
    public void setClass(Class clazz) {
        this.clazz = clazz;
        tgtObj = ClassUtil.newInstance(clazz);
        Map accessDef = setupAccessDef(clazz);
        setupMethodInfo(accessDef);
    }
    
    private void setupMethodInfo(Map accessDef) {
        setupMethodNames();
        Iterator ite = accessDef.keySet().iterator();
        while(ite.hasNext()) {
            OouoAccessInfo accessInfo = (OouoAccessInfo)accessDef.get(ite.next());
            dispach(accessInfo);
        }
    }
    
    private void setupMethodNames() {
        Method methods[] = clazz.getMethods();
        for(int i=0; i < methods.length; i++) {
            if( isOouoMethod(methods[i]) ) {
                methodMap.put(methods[i].getName(),methods[i]);
            }
        }
    }
    
    private boolean isOouoMethod(Method method) {
        int paramCount = method.getParameterTypes().length;
        if( paramCount <= 1 ) {
            return true;
        }
        return false;
    }
    
    private void dispach(OouoAccessInfo accessInfo) {
        int type = accessInfo.getTypeSum();
        if( type == OouoSig.THIS) {
            setThisEleName(accessInfo);
        } else if(type == OouoSig.ATTRI) {
            attriDef(accessInfo);
        } else if(type == (OouoSig.ELEMENT+OouoSig.ATTRI)) {
            attriEleDef(accessInfo);
        } else if(type == OouoSig.ELEMENT) {
            elementDef(accessInfo);
        } else if(type == OouoSig.FIN) {
            setFinMethod(accessInfo);
        } else if(type == OouoSig.TEXT) {
            setTextMethod(accessInfo);
        } else if(type == (OouoSig.ELEMENT+OouoSig.TEXT)) {
            setTextMethod(accessInfo);
        }
    }
    
    private void setTextMethod(OouoAccessInfo accessInfo) {
        textMethod = getSetupMethod(accessInfo);
        
    }
    
    private void setFinMethod(OouoAccessInfo accessInfo) {
//        OouoSig sig = (OouoSig)accessInfo.getSigSet().toArray()[0];
        Method methods[] = clazz.getMethods();
        for(int i=0; i < methods.length; i++) {
            if( methods[i].getName().equals(accessInfo.getTgtName()) ) {
                finMethod = methods[i];
            }
        }
    }
    
    private void setThisEleName(OouoAccessInfo accessInfo) {
        OouoSig sig = (OouoSig)accessInfo.getSigSet().toArray()[0];
        thisElementName = (String)FieldUtil.get(sig.getField(),tgtObj);
    }
    
    private void attriDef(OouoAccessInfo accessInfo) {
        setupAccessInfoToMap(accessInfo,attri);
    }
    
    private void attriEleDef(OouoAccessInfo accessInfo) {
        attriEleImpl(accessInfo,OouoSig.ELEMENT,attriElement);
    }
    
    private void attriEleImpl(OouoAccessInfo accessInfo,int eleType,Map tgtMap) {
        Iterator ite = accessInfo.getSigSet().iterator();
        Map attriMap = new HashMap();
        OouoSig sig;
        String att = "";
        Method tgtMethod = null;
        while(ite.hasNext()) {
            sig = (OouoSig)ite.next();
            String val = (String)FieldUtil.get(sig.getField(),tgtObj);
            if(sig.getAction() == eleType) {
                String ele = val;
                if( ! tgtMap.containsKey(ele)) {
                    tgtMap.put(ele,attriMap);
                }else{
                    attriMap = (Map)tgtMap.get(ele);
                }
            }
            if(sig.getAction() == OouoSig.ATTRI) {
                att = val;
                tgtMethod = getSetupMethod(accessInfo);
            }
        }
        attriMap.put(att,tgtMethod);
    }
    
    private void elementDef(OouoAccessInfo accessInfo) {
        setupAccessInfoToMap(accessInfo,childElement);
    }
    
    private void setupAccessInfoToMap(OouoAccessInfo accessInfo,Map tgtMap) {
        OouoSig sig = (OouoSig)accessInfo.getSigSet().toArray()[0];
        String val = (String)FieldUtil.get(sig.getField(),tgtObj);
        Method tgtMethod = getSetupMethod(accessInfo);
        tgtMap.put(val,tgtMethod);
    }
    
    private Method getSetupMethod(OouoAccessInfo accessInfo) {
        String tgtName = accessInfo.getTgtName();
        Method tgtMethod = null;
        if(methodMap.containsKey(tgtName)) {
            tgtMethod = (Method)methodMap.get(tgtName);
        }
        return tgtMethod;
    }
    
    private Map setupAccessDef(Class clazz) {
        Field field[] = clazz.getFields();
        Map accessDef = new HashMap();
        for(int i=0; i < field.length; i++) {
            Field tgt = field[i];
            fieldProcess(tgt,accessDef);
        }
        return accessDef;
    }
    
    private void fieldProcess(Field tgt,Map accessDef) {
        if(tgt.getType().equals(String.class)) {
            OouoSig sig = findTgtFieldName(tgt);
            if(sig==null) {
                return;
            }
            createOouoAccessInfo(tgt,sig,accessDef);
        }
    }
    
    private void createOouoAccessInfo(Field tgt,OouoSig sig,Map accessDef) {
        String tgtName = tgt.getName().replaceAll(sig.getSig(),"");
        OouoAccessInfo accessInfo = null;
        if(accessDef.containsKey(tgtName)) {
            accessInfo = (OouoAccessInfo)accessDef.get(tgtName);
        } else {
            accessInfo = new OouoAccessInfo();
            accessInfo.setTgtName(tgtName);
            accessDef.put(tgtName,accessInfo);
        }
        accessInfo.getSigSet().add(sig);
        accessInfo.addType(sig);
    }
    
    private OouoSig findTgtFieldName(Field tgt) {
        Iterator ite = fieldSigs.iterator();
        while(ite.hasNext()) {
            OouoSig sig = (OouoSig)ite.next();
            if(tgt.getName().indexOf(sig.getSig()) >= 0) {
                return sig.copy(tgt);
            }
        }
        return null;
    }

    public boolean isChildElement(String elename) {
        if(childElement.containsKey(elename)) {
            return true;
        }
        return false;
    }

    public String getElementName() {
        return thisElementName;
    }

    public Object getChildObject(String name) {
        Method tgtMethod = getSetterMethod(name);
        if(tgtMethod.getParameterTypes().length==0) {
            return null;
        }
        Class clazz = tgtMethod.getParameterTypes()[0];
        Object obj = ClassUtil.newInstance(clazz);
        return obj;
    }
    
    private Method getSetterMethod(String name) {
        Method tgtMethod = null;
        if(childElement.containsKey(name)) {
            tgtMethod = (Method)childElement.get(name);
        }
        return tgtMethod;
    }

    public void setChild(Object now, String name, Object child) {
        Method tgtMethod = getSetterMethod(name);
        if(child != null) {
            MethodUtil.invoke(tgtMethod,now,new Object[]{child});
        } else {
            MethodUtil.invoke(tgtMethod,now,null);
        }
    }

    public boolean isChildAttribute(String name, String type) {
        Map types = null;
        if(attriElement.containsKey(name)) {
            types = (Map)attriElement.get(name);
        }
        if(types != null && types.containsKey(type)) {
            return true;
        }
        return false;
    }

    public void setChildAttribute(Object now, String name, String type, String value) {
        Method tgtMethod = null;
        Map types = null;
        if(attriElement.containsKey(name)) {
            types = (Map)attriElement.get(name);
        }
        if(types != null && types.containsKey(type)) {
            tgtMethod = (Method)types.get(type);
            MethodUtil.invoke(tgtMethod,now,new Object[]{value});
        }

    }
    public boolean isAttribute(String type) {
        if(attri.containsKey(type)) {
            return true;
        }
        return false;
        
    }
    public void setAttribute(Object now, String type, String value) {
        Method method = (Method)attri.get(type);
        if( method.getParameterTypes()[0].equals(String.class) ) {
            MethodUtil.invoke(method,now,new Object[]{value});
        }
        //TODO 型変換＆セット関係を充実させる
    }

    public Class getTgtClass() {
        return clazz;
    }

    public boolean isCallFinMethod() {
        if(finMethod == null) {
            return false;
        }
        return true;
    }

    public void fin(Object now,Object parent) {
        if(finMethod.getParameterTypes().length==0) {
            MethodUtil.invoke(finMethod,now,null);
        } else {
            MethodUtil.invoke(finMethod,now,new Object[]{parent});
        }
    }

    public boolean isSetTextMethod() {
        if(textMethod == null) {
            return false;
        }
        return true;
    }

    public void setText(Object now, String value) {
        MethodUtil.invoke(textMethod,now,new Object[]{value});
    }

}
