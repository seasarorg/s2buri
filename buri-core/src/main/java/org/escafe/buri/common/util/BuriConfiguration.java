/*
 * 作成日: 2006/07/07
 *
 */
package org.escafe.buri.common.util;

import java.util.List;

public interface BuriConfiguration {
    void addValue(String name, Object val);

    List getValList(String name);

    Object getVal(String name);
}
