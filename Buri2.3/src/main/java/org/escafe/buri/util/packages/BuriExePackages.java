/*
 * 作成日: 2006/03/21
 *
 */
package org.escafe.buri.util.packages;

import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.engine.ParticipantProvider;
import org.escafe.buri.oouo.internal.structure.BuriPackageType;

public interface BuriExePackages {
    void setup(BuriPackageType buriPackage);

    BuriExecProcess getProcess(BuriPath path);

    void setProcess(String procID, BuriExecProcess execProcess);

    BuriPackageType getBuriPackageType();

    void setParticipantProvider(ParticipantProvider provider);

    ParticipantProvider getParticipantProvider();

    String getConditionExpressionType();

    String getDataAccessScriptType();

    String getDefaultExpressionType();

    String getPkeyExpressionType();

    String getPreprocessScriptType();

    String getTimeLimitExpressionType();

}
