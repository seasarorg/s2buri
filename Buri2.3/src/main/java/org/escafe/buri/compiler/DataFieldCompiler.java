/*
 * 作成日: 2006/05/04
 *
 */
package org.escafe.buri.compiler;

import org.escafe.buri.dataaccess.BuriDataAccessFactory;
import org.escafe.buri.oouo.internal.structure.BuriDataFieldType;
import org.escafe.buri.oouo.internal.structure.BuriPackageType;
import org.escafe.buri.oouo.internal.structure.BuriWorkflowProcessType;

public interface DataFieldCompiler {
    void compileAndSettingOne(BuriDataFieldType fieldType, BuriDataAccessFactory factory, BuriPackageType buriPackage, BuriWorkflowProcessType process);

    void compileAndSetting(BuriDataAccessFactory factory, BuriPackageType buriPackage, BuriWorkflowProcessType process);
}
