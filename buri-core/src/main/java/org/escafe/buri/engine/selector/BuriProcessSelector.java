/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.escafe.buri.engine.selector;

import java.util.List;

import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.escafe.buri.util.packages.BuriExePackages;

/**
 * 実行対象のプロセスを選択するためのインターフェースです。
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/05/29
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
     * @param processes フローのプロセスの{@link BuriWorkflowProcessType}
     * @param systemContext 実行用コンテキスト
     * @param exePackages 実行対象のフローのパッケージ
     * @return 選択処理の結果ステータス
     */
    int select(List<BuriWorkflowProcessType> processes, BuriSystemContext systemContext, BuriExePackages exePackages);

}
