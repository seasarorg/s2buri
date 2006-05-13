/*
 * ì¬“ú: 2006/05/08
 *
 */
package org.seasar.buri.dao.util;

import java.util.List;

import org.seasar.buri.engine.BuriPath;

public interface BuriPathUtil {
    BuriPath getBuriPathFromRealPath(BuriPath srcPath);
    List getBuriPathFromPathName(BuriPath srcPath);
}
