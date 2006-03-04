/*
 * çÏê¨ì˙: 2005/11/09
 *
 */
package org.seasar.buri.xpdl.util.impl;

import java.util.HashMap;
import java.util.Map;

import org.seasar.buri.common.util.ClassDefUtil;
import org.seasar.buri.context.ContextUtil;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.exception.BuriNoDataFieldException;
import org.seasar.buri.xpdl.util.BuriDataMetaData;
import org.seasar.buri.xpdl.util.BuriDataMetaDataUtil;
import org.seasar.buri.xpdl.util.DataFieldUtil;
import org.seasar.buri.xpdl.util.ExtendedAttributeUtil;
import org.wfmc.x2002.xpdl10.DataFieldDocument.DataField;

public class BuriDataMetaDataUtilImpl implements BuriDataMetaDataUtil {
    private DataFieldUtil dataFieldUtil;
    private ExtendedAttributeUtil extendedAttributeUtil;
    private ContextUtil contextUtil;
    private Map metadataCache = new HashMap();
    private ClassDefUtil classDefUtil;

    public BuriDataMetaData getMetaData(BuriPath path, Object data) {
        return getMetaDataFromTypeName(path,classDefUtil.getClassName(data));
    }
    
    
    protected BuriDataMetaData getBuriDataMetaData(DataField dataField) {
        Map dataContext = extendedAttributeUtil.getExtendedAttribute(dataField);
        BuriDataMetaData metaData = new BuriDataMetaData(dataContext,dataField);
        return metaData;
    }
    
    public BuriDataMetaData getMetaDataFromTypeName(BuriPath path,String dataType) {
//        if(metadataCache.containsKey(path+dataType)){
//            return (BuriDataMetaData)metadataCache.get(path+dataType);
//        }
        DataField dataField = dataFieldUtil.getDataField(path,dataType);
        if(dataField==null) {
            throw new BuriNoDataFieldException(new Object[]{path.getWorkflowPackage(),dataType});
        }
        BuriDataMetaData metaData = getBuriDataMetaData(dataField);
//        metadataCache.put(path+dataType,metaData);
        return metaData;
    }

    public DataFieldUtil getDataFieldUtil() {
        return dataFieldUtil;
    }

    public void setDataFieldUtil(DataFieldUtil dataFieldUtil) {
        this.dataFieldUtil = dataFieldUtil;
    }

    public ExtendedAttributeUtil getExtendedAttributeUtil() {
        return extendedAttributeUtil;
    }

    public void setExtendedAttributeUtil(ExtendedAttributeUtil extendedAttributeUtil) {
        this.extendedAttributeUtil = extendedAttributeUtil;
    }

    public ContextUtil getContextUtil() {
        return contextUtil;
    }

    public void setContextUtil(ContextUtil contextUtil) {
        this.contextUtil = contextUtil;
    }


    public ClassDefUtil getClassDefUtil() {
        return classDefUtil;
    }


    public void setClassDefUtil(ClassDefUtil classDefUtil) {
        this.classDefUtil = classDefUtil;
    }

}
