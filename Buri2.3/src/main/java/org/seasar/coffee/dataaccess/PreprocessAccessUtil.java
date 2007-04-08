/*
 * 作成日: 2006/03/24
 *
 */
package org.seasar.coffee.dataaccess;

import org.seasar.coffee.script.Script;

public interface PreprocessAccessUtil {
    Object getTrueData(Object orgData);
    void setPreprocessScript(Script preprocessScript);

}
