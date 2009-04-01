/*
 * 作成日: 2006/05/16
 *
 */
package org.escafe.buri.dao.util.impl;

import org.escafe.buri.dao.util.BTIDUtil;
import org.escafe.buri.dao.util.BuriUndoLogUtil;
import org.escafe.buri.service.BuriStateUndoLogEntityService;

public class BuriUndoLogUtilNoImpl implements BuriUndoLogUtil {
	private BTIDUtil btidUtil;

	private BuriStateUndoLogEntityService buriStateUndoLogEntityService;

	public void addUndoLog(long stateID, long branchID) {
	}

	public BTIDUtil getBtidUtil() {
		return btidUtil;
	}

	public void setBtidUtil(BTIDUtil btidUtil) {
		this.btidUtil = btidUtil;
	}

	public BuriStateUndoLogEntityService getBuriStateUndoLogEntityService() {
		return buriStateUndoLogEntityService;
	}

	public void setBuriStateUndoLogEntityService(
	        BuriStateUndoLogEntityService buriStateUndoLogEntityService) {
		this.buriStateUndoLogEntityService = buriStateUndoLogEntityService;
	}
}
