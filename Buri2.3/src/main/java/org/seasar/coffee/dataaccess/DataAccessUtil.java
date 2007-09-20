/*
 * 作成日: 2006/03/24
 *
 */
package org.seasar.coffee.dataaccess;

import org.seasar.coffee.script.Script;

public interface DataAccessUtil {

    Object Store(Object data);

    int delete(Object data);

    Object getDataFromDto(Object keyVal);

    boolean hasAvailableKey(Object keyVal);

    void setDataAccessScript(Script dataAccessScript);

    void setPkeyExpressionScript(Script pkeyExpressionScript);
    
    String getTableName(Object data);
    
    String getClassName(Object data);
}
