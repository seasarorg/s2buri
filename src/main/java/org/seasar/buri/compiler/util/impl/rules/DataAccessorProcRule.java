/*
 * çÏê¨ì˙: 2006/07/05
 *
 */
package org.seasar.buri.compiler.util.impl.rules;

import java.lang.reflect.Method;

import org.seasar.buri.exception.BuriDataFieldErrorException;
import org.seasar.buri.oouo.internal.structure.BuriDataFieldType;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.MethodUtil;
import org.seasar.framework.util.StringUtil;

public class DataAccessorProcRule extends AbstractBuriDataFieldProcRule {
    private String propName;
    private boolean failCheckPass = false;
    
    protected void setupVal(BuriDataFieldType src,String val) {
        Method mthd = ClassUtil.getMethod(src.getClass(),"set"+propName,new Class[]{String.class});
        MethodUtil.invoke(mthd,src,new Object[]{val});
    }

    public boolean fstCheckProcess(BuriDataFieldType src) {
        if(hasName(src,getKeyName())) {
            setupVal(src,getNameVal(src,getKeyName()));
            return true;
        }
        return true;
    }

    public void finishCheck(BuriDataFieldType src) {
        if(failCheckPass) {
            return;
        }
        Method mthd = ClassUtil.getMethod(src.getClass(),"get"+propName,new Class[]{});
        String val = (String)MethodUtil.invoke(mthd,src,new Object[]{});
        if(StringUtil.isEmpty(val)) {
            throw new BuriDataFieldErrorException(src.getId(),getKeyName());
        }
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
        setKeyName(propName);
    }

    public boolean isFailCheckPass() {
        return failCheckPass;
    }

    public void setFailCheckPass(boolean failCheckPass) {
        this.failCheckPass = failCheckPass;
    }
    
    
}
