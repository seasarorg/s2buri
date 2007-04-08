/*
 * 作成日: 2006/03/24
 *
 */
package org.seasar.coffee.dataaccess;

import java.util.List;

public interface DataAccessUtilLongKey extends DataAccessUtil{
    List get(List keyVals);
    
    Long getKey(Object key);
    Object getObjectFromKey(Long keyObj);

}
