/*
 * çÏê¨ì˙: 2006/06/05
 *
 */
package org.seasar.buri.compiler.util;

import org.seasar.buri.dataaccess.BuriDataAccessFactory;
import org.seasar.buri.oouo.internal.structure.BuriDataFieldType;
import org.seasar.buri.oouo.internal.structure.BuriPackageType;
import org.seasar.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.seasar.coffee.dataaccess.DataAccessUtil;

public interface DataAccessCompileUtil {
    DataAccessUtil compileDataAccess(BuriDataFieldType fieldType,BuriPackageType buriPackage,BuriWorkflowProcessType process);
    void setupDataAccessUtil(BuriDataAccessFactory factory,String className,BuriDataFieldType fieldType,BuriPackageType buriPackage,BuriWorkflowProcessType process);
    void setupDataAccessUtil(BuriDataAccessFactory factory,String className,BuriDataFieldType fieldType,String packageId,String processId);
    boolean isDataAccessUtil(BuriDataFieldType fieldType);
}
