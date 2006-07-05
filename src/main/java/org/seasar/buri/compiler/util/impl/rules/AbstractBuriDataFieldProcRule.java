/*
 * çÏê¨ì˙: 2006/07/04
 *
 */
package org.seasar.buri.compiler.util.impl.rules;

import org.seasar.buri.compiler.util.BuriDataFieldProcRule;
import org.seasar.buri.oouo.internal.structure.BuriDataFieldType;
import org.seasar.buri.oouo.internal.structure.BuriExtendedAttributeType;
import org.seasar.buri.oouo.internal.structure.util.ExtentedAttributeUtil;

public abstract class AbstractBuriDataFieldProcRule implements BuriDataFieldProcRule {
    private String keyName;
    
    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public boolean fstCheckProcess(BuriDataFieldType src) {
        return true;
    }

    public boolean process(BuriDataFieldType src) {
        return false;
    }

    public boolean afterCheck(BuriDataFieldType src, boolean totalResult) {
        return false;
    }

    public void finishCheck(BuriDataFieldType src) {
    }
    
    protected boolean hasName(BuriDataFieldType src,String name) {
        BuriExtendedAttributeType attri = getExAttri(src,name);
        if(attri==null) {
            return false;
        }
        return true;
    }
    
    protected String getNameVal(BuriDataFieldType src,String name) {
        BuriExtendedAttributeType attri = getExAttri(src,name);
        return attri.getValue();
    }
    
    protected BuriExtendedAttributeType getExAttri(BuriDataFieldType src,String name) {
        if(src.getCache().containsKey(name)) {
            return (BuriExtendedAttributeType)src.getCache().get(name);
        }
        BuriExtendedAttributeType attri = ExtentedAttributeUtil.getExtendedAttribute(src.getExtentedAttribute(),name);
        src.getCache().put(name,attri);
        return attri;
    }
    
    protected boolean hasPreprocess(BuriDataFieldType src) {
        boolean result = hasName(src,"preprocess");
        return result;
    }

}
