/*
 * çÏê¨ì˙: 2006/04/03
 *
 */
package org.seasar.buri.component;

import org.seasar.buri.oouo.internal.structure.BuriActivityType;
import org.seasar.buri.oouo.internal.structure.BuriToolType;

public interface BuriComponentUtil {
    String getJavaProcessCode(String tgtObjName,BuriToolType toolType,BuriActivityType actType);
    String getJavaAfterProcessCode(String tgtObjName,BuriToolType toolType,BuriActivityType actType);
    String convScriptToJavaString(String script);
}
