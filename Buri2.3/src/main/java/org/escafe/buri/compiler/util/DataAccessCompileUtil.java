/*
 * 作成日: 2006/06/05
 *
 */
package org.escafe.buri.compiler.util;

import org.escafe.buri.dataaccess.BuriDataAccessFactory;
import org.escafe.buri.oouo.internal.structure.BuriDataFieldType;
import org.escafe.buri.oouo.internal.structure.BuriPackageType;
import org.escafe.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.seasar.coffee.dataaccess.DataAccessUtil;

public interface DataAccessCompileUtil {
    DataAccessUtil compileDataAccess(BuriDataFieldType fieldType, BuriPackageType buriPackage, BuriWorkflowProcessType process,ClassLoader classLoader);

    void setupDataAccessUtil(BuriDataAccessFactory factory, String className, BuriDataFieldType fieldType, BuriPackageType buriPackage,
            BuriWorkflowProcessType process,ClassLoader classLoader);

    void setupDataAccessUtil(BuriDataAccessFactory factory, String className, BuriDataFieldType fieldType, String packageId, String processId,ClassLoader classLoader);

    boolean isDataAccessUtil(BuriDataFieldType fieldType);
}
