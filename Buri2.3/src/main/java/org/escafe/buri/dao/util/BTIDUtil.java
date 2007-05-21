/*
 * 作成日: 2006/05/13
 *
 */
package org.escafe.buri.dao.util;

public interface BTIDUtil {
    long createBTID();

    long getCurrentBTID();

    void setBTID(long btid);
}
