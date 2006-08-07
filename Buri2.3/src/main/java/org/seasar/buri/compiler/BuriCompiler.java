/*
 * ì¬“ú: 2006/03/26
 *
 */
package org.seasar.buri.compiler;

import org.seasar.buri.engine.ParticipantProvider;
import org.seasar.buri.util.packages.BuriExePackages;

public interface BuriCompiler {
    BuriExePackages CompileResource(String src,ParticipantProvider provider);
    BuriExePackages CompileFile(String src,ParticipantProvider provider);
}
