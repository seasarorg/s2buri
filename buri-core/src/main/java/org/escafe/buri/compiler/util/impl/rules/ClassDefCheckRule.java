/*
 * 作成日: 2006/07/05
 *
 */
package org.escafe.buri.compiler.util.impl.rules;

import java.util.Iterator;
import java.util.List;

import org.escafe.buri.common.util.BuriConfiguration;
import org.escafe.buri.common.util.ClassDefUtilImpl;
import org.escafe.buri.exception.BuriDataFieldErrorException;
import org.escafe.buri.oouo.internal.structure.BuriDataFieldType;

public class ClassDefCheckRule extends AbstractBuriDataFieldProcRule {
    private BuriConfiguration configuration;

    @Override
    public boolean process(BuriDataFieldType src) {
        String id = src.getId();
        if (ClassDefUtilImpl.isClassName(id)) {
            return false;
        }
        List packageNames = configuration.getValList("DtoPackageName");
        Iterator ite = packageNames.iterator();
        while (ite.hasNext()) {
            String packageName = ite.next().toString();
            String fullClassName = packageName + "." + id;
            if (ClassDefUtilImpl.isClassName(fullClassName)) {
                src.setId(fullClassName);
                return false;
            }
        }
        throw new BuriDataFieldErrorException(id);
    }

    public BuriConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(BuriConfiguration configuration) {
        this.configuration = configuration;
    }

}
