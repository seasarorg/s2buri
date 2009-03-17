/*
 * 作成日: 2006/05/29
 *
 */
package org.escafe.buri.engine.selector;

import java.util.List;

import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.escafe.buri.util.packages.BuriExePackages;

/**
 * 実行対象のプロセスを選択するためのインターフェースです。
 * 
 * @author $Author: nobeans $
 */
public interface BuriProcessSelector {

    /** 選択に失敗したことを示します。 */
    final static int SELECT_ERROR = -1;

    /** 選択に成功し、続けて他のセレクタを実行する必要がないことを示します。 */
    final static int SELECT_FINAL = 0;

    /** 選択に成功し、続けて他のセレクタを実行することを示します。 */
    final static int SELECT_NEXT = 1;

    /**
     * 実行対象のプロセスを選択します。
     * <p>
     * 本メソッドを実行することにより第1引数のプロセス群が増減します。
     * </p>
     * 
     * @param processes
     * @param systemContext
     * @param exePackages
     * @return
     */
    int select(List<BuriWorkflowProcessType> processes, BuriSystemContext systemContext, BuriExePackages exePackages);

}
