/*
 * 作成日: 2006/03/26
 *
 */
package org.seasar.buri.compiler;

import java.io.InputStream;

import org.seasar.buri.engine.ParticipantProvider;
import org.seasar.buri.oouo.internal.structure.BuriPackageType;
import org.seasar.buri.util.packages.BuriExePackages;

public interface BuriCompiler {
    BuriExePackages CompileInputStream(InputStream workFlowIs,ParticipantProvider provider);
    BuriExePackages CompileResource(String src,ParticipantProvider provider);
    BuriExePackages CompileFile(String src,ParticipantProvider provider);
    BuriExePackages CompileObject(BuriPackageType buriPackage,ParticipantProvider provider);
}
