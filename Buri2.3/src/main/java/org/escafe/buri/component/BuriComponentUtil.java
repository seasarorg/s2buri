/*
 * 作成日: 2006/04/03
 *
 */
package org.escafe.buri.component;

import org.escafe.buri.oouo.internal.structure.BuriActivityType;
import org.escafe.buri.oouo.internal.structure.BuriToolType;

public interface BuriComponentUtil {
    String getJavaProcessCode(String tgtObjName, BuriToolType toolType, BuriActivityType actType);

    String getJavaAfterProcessCode(String tgtObjName, BuriToolType toolType, BuriActivityType actType);

    String convScriptToJavaString(String script);
}
