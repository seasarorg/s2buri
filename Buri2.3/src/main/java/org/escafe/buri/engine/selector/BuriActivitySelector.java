/*
 * 作成日: 2006/05/23
 *
 */
package org.escafe.buri.engine.selector;

import java.util.Set;

import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.oouo.internal.structure.BuriActivityType;
import org.escafe.buri.util.packages.BuriExecProcess;

/**
 * 実行対象のアクティビティを選択するためのインターフェースです。
 * 
 * @author $Author$
 */
public interface BuriActivitySelector {

    /** 選択に失敗したことを示します。 */
    final static int SELECT_ERROR = -1;

    /** 選択に成功し、続けて他のセレクタを実行する必要がないことを示します。 */
    final static int SELECT_FINAL = 0;

    /** 選択に成功し、続けて他のセレクタを実行することを示します。 */
    final static int SELECT_NEXT = 1;

    /**
     * 実行対象のアクティビティを選択します。
     * <p>
     * 本メソッドを実行することにより第1引数のアクティビティ群が増減します。
     * </p>
     * 
     * @param activitys
     * @param systemContext
     * @param execProcess
     * @return
     */
    int select(Set<BuriActivityType> activitys, BuriSystemContext systemContext,
            BuriExecProcess execProcess);
}
