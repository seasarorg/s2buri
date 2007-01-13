/*
 * 作成日: 2006/05/16
 *
 */
package org.seasar.buri.dao.util;

public interface BuriUndoLogUtil {
    void addUndoLog(long stateID,long branchID);
}
