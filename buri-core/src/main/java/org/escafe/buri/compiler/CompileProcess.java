/*
 * 作成日: 2006/05/02
 *
 */
package org.escafe.buri.compiler;

import org.escafe.buri.engine.ParticipantProvider;
import org.escafe.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.escafe.buri.util.packages.BuriExePackages;

public interface CompileProcess {
    void compile(BuriExePackages result, BuriWorkflowProcessType process, ParticipantProvider provider,ClassLoader classLoader);
}
