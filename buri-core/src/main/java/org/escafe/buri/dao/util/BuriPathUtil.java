/*
 * 作成日: 2006/05/08
 *
 */
package org.escafe.buri.dao.util;

import java.util.List;

import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.util.packages.BuriExecProcess;

public interface BuriPathUtil {
	List<BuriPath> getPathListByDataId(Long dataId);

	BuriPath getBuriPathById(Long pathId);

	BuriPath getBuriPathFromRealPath(BuriPath srcPath);

	List<BuriPath> getBuriPathFromPathName(BuriPath srcPath);

	long getPathType(BuriExecProcess process);
}
