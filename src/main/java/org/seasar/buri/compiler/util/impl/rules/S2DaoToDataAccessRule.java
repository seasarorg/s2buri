/*
 * �쐬��: 2006/07/06
 *
 */
package org.seasar.buri.compiler.util.impl.rules;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import org.seasar.buri.common.util.BuriConfiguration;
import org.seasar.buri.common.util.ClassDefUtil;
import org.seasar.buri.common.util.ClassDefUtilImpl;
import org.seasar.buri.oouo.internal.structure.BuriDataFieldType;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.StringUtil;

public class S2DaoToDataAccessRule extends AbstractBuriDataFieldProcRule {
    protected String daoKeyName = "s2dao";
    public final String DAOKEY = "dao";
    
    private BuriConfiguration configuration;
    private S2Container container;
    private ClassDefUtil classDefUtil;
    
    public boolean getRequiredRule(BuriDataFieldType src) {
        if(hasName(src,"preprocess")) {
            return false;
        }
        return true;
    }
    
    public boolean isRequiredNegotiate(BuriDataFieldType src) {
        if(src.getKeys().size() == 0 ) {
            return true;
        }
        if( ! hasName(src,"select")) {
            return true;
        }
        if( ! hasName(src,"insert")) {
            return true;
        }
        if( ! hasName(src,"update")) {
            return true;
        }
//TODO ���̂����S���ɗL�������� �Ԃ�ɂ͕K�{����Ȃ��̂ŃR�����g�A�E�g���E�E�E
        if( ! hasName(src,"delete")) {
            return true;
        }
        return false;
    }
    
    public boolean fstCheckProcess(BuriDataFieldType src) {
        if( ! isRequiredNegotiate(src)) {
            return false;
        }
        if(src.getCache().containsKey(daoKeyName)) {
            
        } else if(hasName(src,DAOKEY)) {
            src.getCache().put(daoKeyName,getNameVal(src,DAOKEY));
        }
        return true;
    }
    
    public boolean process(BuriDataFieldType src) {
//        if( ! src.getCache().containsKey(daoKeyName) ) {
//            return false;
//        }
        if( src.getCache().containsKey(daoKeyName + "_end") ) {
            return false;
        }
        negotiateDao(src);
        
        src.getCache().put(daoKeyName + "_end",Boolean.TRUE);
        return false;
    }

    protected void negotiateDao(BuriDataFieldType src) {
        String dtoClassName = src.getId();
        String daoName = getDaoName(src,dtoClassName);
        if(daoName != null) {
            src.getCache().put(daoKeyName,daoName);
            Class daoClass = container.getRoot().getComponentDef(daoName).getComponentClass();
            pkeySetup(src);
            
            if(src.getKeys().size()==1) {
                String keyName = src.getKeys().keySet().toArray()[0].toString();
                BeanDesc beanDesc = BeanDescFactory.getBeanDesc(ClassUtil.forName(dtoClassName));
                Class tgtClass = beanDesc.getPropertyDesc(keyName).getPropertyType();
                src.getCache().put(daoKeyName + "_KeyType",tgtClass);
                src.getCache().put(daoKeyName + "_KeyName",keyName);
                findAndSetupAllMethod(src,beanDesc,daoClass);
            }
        }
        
    }
    
    protected void findAndSetupAllMethod(BuriDataFieldType src,BeanDesc beanDesc,Class daoClass) {
        Method methods[] = daoClass.getMethods();
        for(int i=0; i < methods.length ; i++) {
            Method method = methods[i];
            String methodName = method.getName();
            selectSetup(src,method,methodName,beanDesc);
            updateSetup(src,method,methodName,beanDesc);
            deleteSetup(src,method,methodName,beanDesc);
            selectManySetup(src,method,methodName,beanDesc);
            insertSetup(src,method,methodName,beanDesc);
        }
    }
    
    protected void selectManySetup(BuriDataFieldType src,Method method,String methodName,BeanDesc beanDesc) {
        if( ! StringUtil.isEmpty(src.getSelectMany())) {
            return;
        }
        
        if(isSelectManyMethod(src,method,methodName)) {
            String daoName = src.getCache().get(daoKeyName).toString();
            src.setSelectMany(daoName+"."+methodName+"(#data)");
        }
    }
    
    protected boolean isSelectManyMethod(BuriDataFieldType src,Method method,String methodName) {
        if(methodName.startsWith("get") || methodName.startsWith("select")) {
            if(method.getParameterTypes().length == 1) {
                if(method.getParameterTypes()[0].isAssignableFrom(List.class)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    protected void deleteSetup(BuriDataFieldType src,Method method,String methodName,BeanDesc beanDesc) {
        if( ! StringUtil.isEmpty(src.getDelete())) {
            return;
        }
        
        if(isDeleteMethod(src,method,methodName)) {
            String daoName = src.getCache().get(daoKeyName).toString();
            src.setDelete(daoName+"."+methodName+"(#data)");
        }
    }
    
    protected boolean isDeleteMethod(BuriDataFieldType src,Method method,String methodName) {
        if(methodName.startsWith("del")) {
            if(method.getParameterTypes().length == 1) {
                if(method.getParameterTypes()[0].getName().equals(src.getId())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    protected void updateSetup(BuriDataFieldType src,Method method,String methodName,BeanDesc beanDesc) {
        if( ! StringUtil.isEmpty(src.getUpdate())) {
            return;
        }
        
        if(isUpdateMethod(src,method,methodName)) {
            String daoName = src.getCache().get(daoKeyName).toString();
            src.setUpdate(daoName+"."+methodName+"(#data)");
        }
    }
    
    protected boolean isUpdateMethod(BuriDataFieldType src,Method method,String methodName) {
        if(methodName.startsWith("update")) {
            if(method.getParameterTypes().length == 1) {
                if(method.getParameterTypes()[0].getName().equals(src.getId())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    protected void insertSetup(BuriDataFieldType src,Method method,String methodName,BeanDesc beanDesc) {
        if( ! StringUtil.isEmpty(src.getInsert())) {
            return;
        }
        
        if(isInsertMethod(src,method,methodName)) {
            String daoName = src.getCache().get(daoKeyName).toString();
            src.setInsert(daoName+"."+methodName+"(#data)");
        }
    }
    
    protected boolean isInsertMethod(BuriDataFieldType src,Method method,String methodName) {
        if(methodName.startsWith("insert")) {
            if(method.getParameterTypes().length == 1) {
                if(method.getParameterTypes()[0].getName().equals(src.getId())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    protected void selectSetup(BuriDataFieldType src,Method method,String methodName,BeanDesc beanDesc) {
        if( ! StringUtil.isEmpty(src.getSelect()) ) {
            return;
        }
        
        if(isSelectMethod(src,method,methodName)) {
            String keyName = src.getCache().get(daoKeyName + "_KeyName").toString();
            String daoName = src.getCache().get(daoKeyName).toString();
            src.setSelect(daoName+"."+methodName+"(#data."+keyName+")");
        }
    }
    
    protected boolean isSelectMethod(BuriDataFieldType src,Method method,String methodName) {
        if(methodName.startsWith("get") || methodName.startsWith("select")) {
            if(method.getParameterTypes().length == 1) {
                Class tgtClass = (Class)src.getCache().get(daoKeyName + "_KeyType");
                if(method.getParameterTypes()[0].equals(tgtClass)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    protected void pkeySetup(BuriDataFieldType src) {
        if(src.getKeys().size() > 0 || hasName(src,"pkey") ) {
            return;
        }
        Class tgt = ClassUtil.forName(src.getId());
        BeanDesc bd = BeanDescFactory.getBeanDesc(tgt);
        int propLen = bd.getPropertyDescSize();
        for(int i=0; i < propLen ; i++ ) {
            PropertyDesc pd = bd.getPropertyDesc(i);
            Object sig = classDefUtil.getMethodSignatureValue(tgt,"_ID",pd.getPropertyName());
            if(sig != null) {
                String condition = createPkeyCondition(pd);
                src.getKeys().put(pd.getPropertyName(),condition);
            }
        }
    }
    
    protected String createPkeyCondition(PropertyDesc pd) {
        String condition = null;
        String pkeyName = pd.getPropertyName();
        Class propType = pd.getPropertyType();
        if(propType.equals(Long.TYPE)) {
            condition = pkeyName + " != 0";
        } else if(propType.isAssignableFrom(Number.class)) {
            condition = pkeyName + " != 0";
        } else {
            condition = pkeyName + " != null";
        }
        return condition;
    }
    
    
    protected String getDaoName(BuriDataFieldType src,String dtoClassName) {
        String dao = null;
        String shtName = createDaoName(src,dtoClassName);
        if(container.getRoot().hasComponentDef(shtName)) {
            dao = shtName;
        } else {
            dao = findDaoClass(shtName);
        }
        return dao;
    }
    
    protected String findDaoClass(String shtName) {
        String dao = null;
        List daoPackageName = configuration.getValList("DaoPackageName");
        Iterator ite = daoPackageName.iterator();
        while(ite.hasNext()) {
            String pacName = ite.next().toString();
            String fullName = pacName + "." +shtName;
            dao = classNameToDao(fullName);
            if(dao != null) {
                break;
            }
        }
        return dao;
    }
    
    protected String classNameToDao(String fullName) {
        String dao = null;
        if(ClassDefUtilImpl.isClassName(fullName)) {
            Class daoClass = ClassUtil.forName(fullName);
            if(container.getRoot().hasComponentDef(daoClass)) {
                dao = container.getRoot().getComponentDef(daoClass).getComponentName();
            }
        }
        return dao;
    }
    
    protected String createDaoName(BuriDataFieldType src,String dtoClassName) {
        if(hasName(src,DAOKEY)) {
            return getNameVal(src,DAOKEY);
        }
        Class tgtClass = ClassUtil.forName(dtoClassName);
        String shtName = ClassUtil.getShortClassName(tgtClass);
        shtName = shtName.replaceAll("Dto","");
        shtName = shtName.replaceAll("Entity","");
        shtName = shtName + "Dao";
        return shtName;
    }

    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }

    public BuriConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(BuriConfiguration configuration) {
        this.configuration = configuration;
    }

    public ClassDefUtil getClassDefUtil() {
        return classDefUtil;
    }

    public void setClassDefUtil(ClassDefUtil classDefUtil) {
        this.classDefUtil = classDefUtil;
    }
    
}