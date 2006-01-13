/*
 * ì¬“ú: 2005/10/08
 *
 */
package org.seasar.buri.context;

import java.util.Map;

public interface BuriInnerContext extends Map {
    Object getData();
    void setData(Object data);
    Object getUserData();
    void setUserData(Object data);
}
