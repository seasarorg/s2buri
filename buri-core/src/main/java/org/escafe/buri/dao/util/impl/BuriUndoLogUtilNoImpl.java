/*
 * 作成日: 2006/05/16
 *
 */
package org.escafe.buri.dao.util.impl;

import org.escafe.buri.dao.BuriStateUndoLogDao;
import org.escafe.buri.dao.util.BTIDUtil;
import org.escafe.buri.dao.util.BuriUndoLogUtil;

public class BuriUndoLogUtilNoImpl implements BuriUndoLogUtil {
    private BTIDUtil btidUtil;
    private BuriStateUndoLogDao undoDao;

    public void addUndoLog(long stateID, long branchID) {
    }

    public BTIDUtil getBtidUtil() {
        return btidUtil;
    }

    public void setBtidUtil(BTIDUtil btidUtil) {
        this.btidUtil = btidUtil;
    }

    public BuriStateUndoLogDao getUndoDao() {
        return undoDao;
    }

    public void setUndoDao(BuriStateUndoLogDao undoDao) {
        this.undoDao = undoDao;
    }

}
