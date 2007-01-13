/*
 * 作成日: 2006/05/29
 *
 */
package org.seasar.buri.engine.selector;

import java.util.List;

import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.util.packages.BuriExePackages;

public interface BuriProcessSelector {
    final static int SELECT_ERROR = -1;
    final static int SELECT_FINAL = 0;
    final static int SELECT_NEXT = 1;
    int select(List procs,BuriSystemContext systemContext,BuriExePackages exePackages);

}
