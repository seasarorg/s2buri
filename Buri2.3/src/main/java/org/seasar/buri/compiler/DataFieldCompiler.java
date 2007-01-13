/*
 * 作成日: 2006/05/04
 *
 */
package org.seasar.buri.compiler;

import org.seasar.buri.dataaccess.BuriDataAccessFactory;
import org.seasar.buri.oouo.internal.structure.BuriDataFieldType;
import org.seasar.buri.oouo.internal.structure.BuriPackageType;
import org.seasar.buri.oouo.internal.structure.BuriWorkflowProcessType;

public interface DataFieldCompiler {
    void compileAndSettingOne(BuriDataFieldType fieldType,BuriDataAccessFactory factory,BuriPackageType buriPackage,BuriWorkflowProcessType process);
    void compileAndSetting(BuriDataAccessFactory factory,BuriPackageType buriPackage,BuriWorkflowProcessType process);
}
