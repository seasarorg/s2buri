/*
 * 作成日: 2006/03/24
 *
 */
package org.seasar.coffee.dataaccess;

import java.util.List;

public interface DataAccessUtilManyKey extends DataAccessUtil{
    List get(List keyStr);

    String getKey(Object key);
    Object getObjectFromKey(String keyObj);

}
