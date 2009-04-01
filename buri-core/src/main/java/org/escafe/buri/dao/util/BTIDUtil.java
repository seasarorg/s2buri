/*
 * 作成日: 2006/05/13
 *
 */
package org.escafe.buri.dao.util;

public interface BTIDUtil {
	long createBtId();

	long getCurrentBtId();

	void setBtId(long btId);
}
