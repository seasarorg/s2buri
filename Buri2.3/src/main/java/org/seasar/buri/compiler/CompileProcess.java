/*
 * çÏê¨ì˙: 2006/05/02
 *
 */
package org.seasar.buri.compiler;

import org.seasar.buri.engine.ParticipantProvider;
import org.seasar.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.seasar.buri.util.packages.BuriExePackages;

public interface CompileProcess {
    void compile(BuriExePackages result,BuriWorkflowProcessType process,ParticipantProvider provider);
}
