/*
 * çÏê¨ì˙: 2006/07/05
 *
 */
package org.seasar.buri.compiler.util.impl.rules;

import org.seasar.buri.common.util.ClassDefUtilImpl;
import org.seasar.buri.exception.BuriDataFieldErrorException;
import org.seasar.buri.oouo.internal.structure.BuriDataFieldType;

public class ClassDefCheckRule extends AbstractBuriDataFieldProcRule {
    public void finishCheck(BuriDataFieldType src) {
        String id = src.getId();
        if( ! ClassDefUtilImpl.isClassName(id)) {
            throw new BuriDataFieldErrorException(id);
        }
    }

}
