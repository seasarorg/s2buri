/*
 * 作成日: 2006/03/07
 *
 */
package org.seasar.buri.oouo.internal.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuriDataFieldType {
    private String id;
    private List ExtentedAttribute = new ArrayList();
    
    private String insert;
    private String update;
    private String select;
    private String delete;
    private String selectMany;
    private String preprocess;
    
    private Map keys = new HashMap();
    private Map cache = new HashMap();
    
    public final static String OOUOTHIS = "DataField";
    
    public final static String setId_ATTRI = "Id";
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public static final String addExtendedAttribute_ELEMENT = "ExtendedAttribute";
    public void addExtendedAttribute(BuriExtendedAttributeType attri) {
        ExtentedAttribute.add(attri);
    }
    
    public List getExtentedAttribute() {
        return ExtentedAttribute;
    }
    public void setExtentedAttribute(List extentedAttribute) {
        ExtentedAttribute = extentedAttribute;
    }
    
    public String getInsert() {
        return insert;
    }
    public void setInsert(String insert) {
        this.insert = insert;
    }
    public String getPreprocess() {
        return preprocess;
    }
    public void setPreprocess(String preprocess) {
        this.preprocess = preprocess;
    }
    public String getSelect() {
        return select;
    }
    public void setSelect(String select) {
        this.select = select;
    }
    public String getSelectMany() {
        return selectMany;
    }
    public void setSelectMany(String selectMany) {
        this.selectMany = selectMany;
    }
    public String getUpdate() {
        return update;
    }
    public void setUpdate(String update) {
        this.update = update;
    }
    public String getDelete() {
        return delete;
    }
    public void setDelete(String delete) {
        this.delete = delete;
    }
    public Map getCache() {
        return cache;
    }
    public void setCache(Map cache) {
        this.cache = cache;
    }
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("id=").append(id);
        buff.append("cache=").append(cache);
        buff.append("/ExtentedAttribute=").append(ExtentedAttribute);
        buff.append("]");
        return buff.toString();
    }
    public Map getKeys() {
        return keys;
    }
    public void setKeys(Map keys) {
        this.keys = keys;
    }

}
