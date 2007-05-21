/*
 * 作成日: 2006/05/08
 *
 */
package org.escafe.buri.dao.util;

import java.util.List;

import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.util.packages.BuriExecProcess;

public interface BuriPathUtil {
    List getPathListByDataId(long dataID);
    BuriPath getBuriPathByID(long pathID);
    BuriPath getBuriPathFromRealPath(BuriPath srcPath);
    List getBuriPathFromPathName(BuriPath srcPath);
    long getPathType(BuriExecProcess process);
}
