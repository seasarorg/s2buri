/*
 * �쐬��: 2006/05/16
 *
 */
package org.seasar.buri.dao.util.impl;

import org.seasar.buri.dao.BuriStateUndoLogDao;
import org.seasar.buri.dao.util.BTIDUtil;
import org.seasar.buri.dao.util.BuriUndoLogUtil;

public class BuriUndoLogUtilImpl implements BuriUndoLogUtil {
    private BTIDUtil btidUtil;
    private BuriStateUndoLogDao undoDao;
    
    
    public void addUndoLog(long stateID,long branchID) {
        undoDao.addUndoLog(stateID,0,btidUtil.getCurrentBTID());
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
