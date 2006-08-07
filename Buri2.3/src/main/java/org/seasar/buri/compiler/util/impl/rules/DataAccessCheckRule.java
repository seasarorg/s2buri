/*
 * ì¬“ú: 2006/07/05
 *
 */
package org.seasar.buri.compiler.util.impl.rules;

import java.util.Iterator;

import org.seasar.buri.common.util.ClassDefUtilImpl;
import org.seasar.buri.exception.BuriDataFieldErrorException;
import org.seasar.buri.oouo.internal.structure.BuriDataFieldType;
import org.seasar.framework.util.ClassUtil;

public class DataAccessCheckRule extends AbstractBuriDataFieldProcRule {
    public void finishCheck(BuriDataFieldType src) {
        String id = src.getId();
        if( ! ClassDefUtilImpl.isClassName(id)) {
            throw new BuriDataFieldErrorException(id);
        }
        if(src.getKeys().size() > 0) {
            checkKeyName(src);
        }
    }
    
    protected void checkKeyName(BuriDataFieldType src) {
        String id = src.getId();
        Class tgtClass = ClassUtil.forName(id);
        Iterator ite = src.getKeys().keySet().iterator();
        while(ite.hasNext()) {
            String keyVal = ite.next().toString();
            
            if( ! ClassDefUtilImpl.hasPropertyName(tgtClass,keyVal)) {
                throw new BuriDataFieldErrorException(tgtClass,keyVal);
            }
        }
    }

}
