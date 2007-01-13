/*
 * 作成日: 2006/05/13
 *
 */
package org.seasar.buri.dao.util;

public interface BTIDUtil {
    long createBTID();
    long getCurrentBTID();
    void setBTID(long btid);
}
