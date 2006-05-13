/*
 * çÏê¨ì˙: 2006/04/03
 *
 */
package org.seasar.buri.component;

import org.seasar.buri.oouo.internal.structure.BuriActivityType;
import org.seasar.buri.oouo.internal.structure.BuriToolType;

public interface BuriComponentUtil {
    String getJavaProcessCode(BuriToolType toolType,BuriActivityType actType);
    String convScriptToJavaString(String script);
}
