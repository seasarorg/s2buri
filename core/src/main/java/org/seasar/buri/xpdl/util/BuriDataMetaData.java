/*
 * çÏê¨ì˙: 2005/07/01
 *
 */
package org.seasar.buri.xpdl.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.seasar.buri.common.util.ScriptProcessor;
import org.seasar.buri.common.util.StringUtil;
import org.seasar.buri.exception.BuriNoDataAccessMethod;
import org.seasar.buri.exception.BuriNoPkeyDefine;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.log.Logger;
import org.seasar.framework.message.MessageFormatter;
import org.seasar.framework.util.ClassUtil;
import org.wfmc.x2002.xpdl10.DataFieldDocument.DataField;

public class BuriDataMetaData {
    private static Logger logger = Logger.getLogger(BuriDataMetaData.class);
    private String insertOgnl = null;
    private String updateOgnl = null;
    private String selectOgnl = null;
    private String selectManyOgnl = null;
    private String filterManyOgnl = null;
    private String deleteOgnl = null;
    private String preprocessOgnl = null;
    private String className = null;
    private Class clazz;
    private Map pkeyMap = null;
    private Object pkey = null;
    
    public BuriDataMetaData(Map dataContext,DataField dataField) {
        setFilterManyOgnl( (String)dataContext.get("filterMany"));
        setPreprocessOgnl( (String)dataContext.get("preprocess"));
        if( ! checkDataContext(dataContext,"insert")) {
            throwNoDataAccessMethod("insert",dataField.getId());
        }
        setInsertOgnl( (String)dataContext.get("insert") );
        if( ! checkDataContext(dataContext,"update")) {
            throwNoDataAccessMethod("update",dataField.getId());
        }
        setUpdateOgnl( (String)dataContext.get("update"));
        if( ! checkDataContext(dataContext,"select")) {
            throwNoDataAccessMethod("select",dataField.getId());
        }
        setSelectOgnl( (String)dataContext.get("select"));
        setSelectManyOgnl(  (String)dataContext.get("selectMany"));
        setDeleteOgnl( (String)dataContext.get("delete"));
        if( ! checkDataContext(dataContext,"pkey")) {
            throwNoDataAccessMethod("pkey",dataField.getId());
        }
        setPkeyMap(  getPKey(dataContext));
        setClassName(  dataField.getId());
        clazz = ClassUtil.forName(className);
    }
    
    protected void throwNoDataAccessMethod(String key,String className){
        throw new BuriNoDataAccessMethod(new Object[]{key,className});
    }
    
    protected boolean checkDataContext(Map dataContext,String key) {
        if(filterManyOgnl != null) {
            return true;
        }
        if(preprocessOgnl != null) {
            return true;
        }
        if( ! dataContext.containsKey(key)) {
            return false;
        }
        if(org.seasar.framework.util.StringUtil.isEmpty((String)dataContext.get(key))) {
            return false;
        }
        return true;
    }
    
    protected String pkeyTopPropName() {
        return (String)pkeyMap.keySet().toArray()[0];
    }
        
    public boolean isPkeyNumberType() {
        if(pkeyMap.size() == 1) {
            String propName = pkeyTopPropName();
            BeanDesc beanDesc = BeanDescFactory.getBeanDesc(clazz);
            PropertyDesc propertyDesc = beanDesc.getPropertyDesc(propName);
            Class propType = propertyDesc.getPropertyType();
            if (propType.isPrimitive()) {
                return true;
            } else if (Number.class.isAssignableFrom(propType)) {
                return true;
            }
        }
        return false;
    }
    
    protected Map getPKey(Map dataContext) {
        String pkey = (String)dataContext.get("pkey");
        if(preprocessOgnl != null && pkey == null) {
            return new HashMap();
        }
        String pkeys[] = pkey.split("\n");
        if(pkeys.length==0) {
            throw new BuriNoPkeyDefine(pkey);
        }
        HashMap pkeyMap = new HashMap();
        for(int i=0 ; i< pkeys.length ; i++ ) {
            String onePkey = pkeys[i];
            setOnePkey(onePkey,pkeyMap);
        }
        return pkeyMap;
    }
    
    protected void setOnePkey(String onePkey,Map pkeyMap) {
        String splitStr[] = StringUtil.SplitFastString(onePkey,",");
        String keyName = splitStr[0];
        String keyCheck = keyName + "!=0";
        if(splitStr.length==0) {
            throw new BuriNoPkeyDefine(onePkey);
        } else if(splitStr.length==1) {
            String msg = MessageFormatter.getMessage("IBRI0001",new Object[]{keyName,keyCheck});
            logger.info(msg);
        } else {
            keyCheck = splitStr[1];
        }
        pkeyMap.put(keyName,keyCheck);
    }
    
    public boolean isCorrectPkey(Object data) {
        boolean correctPkey = true;
        ScriptProcessor processor = new ScriptProcessor();
        Iterator ite = pkeyMap.values().iterator();
        while(ite.hasNext()) {
            String condition = (String)ite.next();
            Boolean conditionResult = (Boolean)processor.getValue(condition,data);
            if(conditionResult.booleanValue()==false) {
                correctPkey = false;
                break;
            }
        }
        return correctPkey;
    }
    
    public Object cnvFindObjectToObj(Object findObj) {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(clazz);
        Object resultObj = beanDesc.newInstance(null);
        ScriptProcessor processor = new ScriptProcessor();
        
        Iterator ite = pkeyMap.keySet().iterator();
        while(ite.hasNext()) {
            String key = (String)ite.next();
            Object val = processor.getValue(key,findObj);
            setValueToObject(resultObj,val,key);
        }
        return resultObj;
    }
    
    public Object cnvStorePkeyToObj(Number keyOfNum,String keyOfStr) {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(clazz);
        Object resultObj = beanDesc.newInstance(null);
        if(keyOfNum != null) {
            String prop = pkeyTopPropName();
            setValueToObject(resultObj,keyOfNum,prop);
        } else {
            setValesToObject(resultObj,keyOfStr);
        }
        
        return resultObj;
    }
    
    protected void setValesToObject(Object target,String keyOfStr) {
        String vals[] = keyOfStr.split("\n");
        for(int i=0 ; i < vals.length ; i++) {
            String keyVal[] = StringUtil.SplitFastString(vals[i],"=");
            if(keyVal.length == 1) {
                String prop = pkeyTopPropName();
                setValueToObject(target,keyVal[0],prop);
            } else {
                setValueToObject(target,keyVal[1],keyVal[0]);
            }
        }
    }
    
    protected void setValueToObject(Object target,Object val,String prop) {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(clazz);
        PropertyDesc propertyDesc = beanDesc.getPropertyDesc(prop);
        propertyDesc.setValue(target,val);
    }
        
    public Object getPkeyFromObject(Object data) {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(clazz);
        if(pkeyMap.size()==1) {
            String prop = pkeyTopPropName();
            PropertyDesc propertyDesc = beanDesc.getPropertyDesc(prop);
            Object val = propertyDesc.getValue(data);
            return val;
        }
        return getPkeys(data);
    }
    
    protected Object getPkeys(Object data) {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(clazz);
        StringBuffer buff = new StringBuffer();
        String cr = "";
        Iterator ite = pkeyMap.keySet().iterator();
        while(ite.hasNext()) {
            String prop = (String)ite.next();
            PropertyDesc propertyDesc = beanDesc.getPropertyDesc(prop);
            Object val = propertyDesc.getValue(data);
            buff.append(cr).append(prop).append("=").append(val);
            cr = "\n";
        }
        return buff.toString();
    }

    public String getDeleteOgnl() {
        return deleteOgnl;
    }

    public void setDeleteOgnl(String deleteOgnl) {
        this.deleteOgnl = deleteOgnl;
    }

    public String getInsertOgnl() {
        return insertOgnl;
    }

    public void setInsertOgnl(String insertOgnl) {
        this.insertOgnl = insertOgnl;
    }

    public Object getPkey() {
        return pkey;
    }

    public void setPkey(Object pkey) {
        this.pkey = pkey;
    }

    public Map getPkeyMap() {
        return pkeyMap;
    }

    public void setPkeyMap(Map pkeyMap) {
        this.pkeyMap = pkeyMap;
    }

    public String getSelectOgnl() {
        return selectOgnl;
    }

    public void setSelectOgnl(String selectOgnl) {
        this.selectOgnl = selectOgnl;
    }

    public String getUpdateOgnl() {
        return updateOgnl;
    }

    public void setUpdateOgnl(String updateOgnl) {
        this.updateOgnl = updateOgnl;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSelectManyOgnl() {
        return selectManyOgnl;
    }

    public void setSelectManyOgnl(String selectManyOgnl) {
        this.selectManyOgnl = selectManyOgnl;
    }

    public String getPreprocessOgnl() {
        return preprocessOgnl;
    }

    public void setPreprocessOgnl(String preprocessOgnl) {
        this.preprocessOgnl = preprocessOgnl;
    }

    public String getFilterManyOgnl() {
        return filterManyOgnl;
    }

    public void setFilterManyOgnl(String filterManyOgnl) {
        this.filterManyOgnl = filterManyOgnl;
    }

}
