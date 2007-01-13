/*
 * 作成日: 2006/05/23
 *
 */
package org.seasar.buri.engine.selector;

import java.util.Set;

import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.util.packages.BuriExecProcess;

public interface BuriActivitySelector {
    final static int SELECT_ERROR = -1;
    final static int SELECT_FINAL = 0;
    final static int SELECT_NEXT = 1;
    int select(Set activitys,BuriSystemContext systemContext,BuriExecProcess execProcess);
}
