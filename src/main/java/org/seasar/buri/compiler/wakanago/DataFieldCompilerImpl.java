/*
 * çÏê¨ì˙: 2006/05/04
 *
 */
package org.seasar.buri.compiler.wakanago;

import java.util.Iterator;
import java.util.Map;

import jp.starlogic.common.janino.util.BasicCompileInfo;
import jp.starlogic.common.janino.util.BasicCompler;

import org.seasar.buri.compiler.DataFieldCompiler;
import org.seasar.buri.dataaccess.BuriDataAccessFactory;
import org.seasar.buri.oouo.internal.structure.BuriDataFieldType;
import org.seasar.buri.oouo.internal.structure.BuriPackageType;
import org.seasar.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.seasar.buri.util.packages.abst.AbstDataAccessUtilLongKey;
import org.seasar.buri.util.packages.abst.AbstDataAccessUtilManyKey;
import org.seasar.coffee.dataaccess.DataAccessUtil;
import org.seasar.coffee.dataaccess.DataAccessUtilLongKey;
import org.seasar.coffee.dataaccess.DataAccessUtilManyKey;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.util.ClassUtil;

public class DataFieldCompilerImpl implements DataFieldCompiler {
    
    private S2Container container;
    private BasicCompler compler;
    private String longKeyTemplateName ="ftl/DataAccessUtilLongKey.ftl";
    private String manyKeyTemplateName ="ftl/DataAccessUtilManyKey.ftl";

    
    public void compileAndSetting(BuriDataAccessFactory factory,BuriPackageType buriPackage,BuriWorkflowProcessType process) {
        Map dataField;
        if(process==null) {
            dataField = buriPackage.getDataField();
        } else {
            dataField = process.getDataField();
        }
        Iterator ite = dataField.keySet().iterator();
        while(ite.hasNext()) {
            String className = (String)ite.next();
            Class clazz = ClassUtil.forName(className);
            BuriDataFieldType fieldType = (BuriDataFieldType)dataField.get(className);
            DataAccessUtil accessUtil = compileDataAccess(fieldType,buriPackage,process);
            if(accessUtil == null) {
                continue;
            }
            if(accessUtil instanceof DataAccessUtilLongKey) {
                factory.setDataAccessUtil(clazz,(DataAccessUtilLongKey)accessUtil);
            } else {
                factory.setDataAccessUtil(clazz,(DataAccessUtilManyKey)accessUtil);
            }
        }
    }
    
    public DataAccessUtil compileDataAccess(BuriDataFieldType fieldType,BuriPackageType buriPackage,BuriWorkflowProcessType process) {
        if(fieldType.getKeys().size() == 0) {
            return null;
        }
        String createClassName = getDataAccessClassName(fieldType,process,buriPackage);
        if(isUseLongKeyType(fieldType)) {
            DataAccessUtilLongKey dataAccessUtil = comileDataAccessUtilLongKey(fieldType,createClassName);
            return dataAccessUtil;
        } else {
            DataAccessUtilManyKey dataAccessUtil = comileDataAccessUtilManyKey(fieldType,createClassName);
            return dataAccessUtil;
        }
    }
    
    private String getDataAccessClassName(BuriDataFieldType fieldType,BuriWorkflowProcessType process,BuriPackageType buriPackage) {
        String baseClassName = "" + buriPackage.getId() ;
        String lastClassName = fieldType.getId().replaceAll("\\.","_")+ "_DataAccessUtil";
        if(process!=null) {
            return  baseClassName + "." + process.getId() + "_" + lastClassName;
        }
        return baseClassName + "." + buriPackage.getId() + "_" + lastClassName;
    }
    
    private DataAccessUtilLongKey comileDataAccessUtilLongKey(BuriDataFieldType fieldType,String createClassName) {
        BasicCompileInfo info = new BasicCompileInfo();
        info.setOutputClassName(createClassName);
        info.setBaseClass(AbstDataAccessUtilLongKey.class);
        info.setInterfaceClass(new Class[]{});
        info.setBaseObjectName("fieldType");
        info.setBaseObject(fieldType);
        info.setTemplateFileName(longKeyTemplateName);
        Object result = compler.Compile(info);
        DataAccessUtilLongKey dataAccessUtil = (DataAccessUtilLongKey)result;
        container.injectDependency(dataAccessUtil,AbstDataAccessUtilLongKey.class);
        return dataAccessUtil;
    }
    
    private DataAccessUtilManyKey comileDataAccessUtilManyKey(BuriDataFieldType fieldType,String createClassName) {
        BasicCompileInfo info = new BasicCompileInfo();
        info.setOutputClassName(createClassName);
        info.setBaseClass(AbstDataAccessUtilManyKey.class);
        info.setInterfaceClass(new Class[]{});
        info.setBaseObjectName("fieldType");
        info.setBaseObject(fieldType);
        info.setTemplateFileName(manyKeyTemplateName);
        Object result = compler.Compile(info);
        DataAccessUtilManyKey dataAccessUtil = (DataAccessUtilManyKey)result;
        container.injectDependency(dataAccessUtil,AbstDataAccessUtilManyKey.class);
        return dataAccessUtil;
    }
    
    private boolean isUseLongKeyType(BuriDataFieldType fieldType) {
        if(fieldType.getKeys().size() == 1) {
            String prop = (String)fieldType.getKeys().keySet().toArray()[0];
            Class tgtClass = ClassUtil.forName(fieldType.getId());
            BeanDesc beanDesc = BeanDescFactory.getBeanDesc(tgtClass);
            PropertyDesc pkeyPropertyDesc = beanDesc.getPropertyDesc(prop);
            if(pkeyPropertyDesc.getPropertyType().isAssignableFrom(Long.TYPE)) {
                return true;
            }
        }
        return false;
    }

    public BasicCompler getCompler() {
        return compler;
    }

    public void setCompler(BasicCompler compler) {
        this.compler = compler;
    }

    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }

    public String getLongKeyTemplateName() {
        return longKeyTemplateName;
    }

    public void setLongKeyTemplateName(String longKeyTemplateName) {
        this.longKeyTemplateName = longKeyTemplateName;
    }

    public String getManyKeyTemplateName() {
        return manyKeyTemplateName;
    }

    public void setManyKeyTemplateName(String manyKeyTemplateName) {
        this.manyKeyTemplateName = manyKeyTemplateName;
    }
}
