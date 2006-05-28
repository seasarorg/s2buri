/*
 * çÏê¨ì˙: 2006/05/08
 *
 */
package org.seasar.buri.dao.util;

import java.util.List;

import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.util.packages.BuriExecProcess;

public interface BuriPathUtil {
    List getPathListByDataId(long dataID);
    BuriPath getBuriPathByID(long pathID);
    BuriPath getBuriPathFromRealPath(BuriPath srcPath);
    List getBuriPathFromPathName(BuriPath srcPath);
    long getPathType(BuriExecProcess process);
}
