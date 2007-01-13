/*
 * 作成日: 2006/03/28
 *
 */
package org.seasar.buri.component;

import java.util.Set;

import org.seasar.buri.oouo.internal.structure.BuriToolType;

public interface BuriComponent {
    String getBuriExecuteSource(String tgtObjName,BuriToolType tool);
    String convertTextToSource(String text);
    Set appendImportName(BuriToolType tool);
    
    String setupSource();
}
