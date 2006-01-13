/*
 * ì¬“ú: 2005/07/06
 *
 */
package org.seasar.buri.dao.util;

import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.xpdl.util.ActivityTagSelect;


public interface BuriHistoryUtil {
    void addHistory(ActivityTagSelect actTagSelect , BuriPath path);
}
