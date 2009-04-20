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
package org.escafe.buri.engine;

import java.io.InputStream;
import java.util.Map;

import org.escafe.buri.engine.impl.BuriSimpleEngineImpl;
import org.escafe.buri.engine.impl.BuriStandardEngineImpl;
import org.escafe.buri.engine.processor.SimpleBuriProcessor;
import org.escafe.buri.engine.processor.StandardBuriProcessor;
import org.escafe.buri.oouo.internal.structure.BuriPackageType;
import org.escafe.buri.util.packages.BuriExePackages;
import org.escafe.buri.util.packages.BuriExecProcess;

/**
 * ぶりの中核を担うエンジンです。
 * <p>
 * ぶりの全ての機能を集約してWrapしているクラスです。 フローの読み込みと実行を行う為のメソッドを提供しています。
 * Bao、プロセッサの為の基本的な機能はこのクラスで実装されます。
 * </p>
 * <p>
 * このクラスの高位クラスとして{@link BuriSimpleEngineImpl}と {@link BuriStandardEngineImpl}
 * があり、{@link SimpleBuriProcessor}には {@link BuriSimpleEngineImpl}が、
 * {@link StandardBuriProcessor}には {@link BuriStandardEngineImpl}がそれぞれ適用されます。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/04/01
 */
public interface WakanagoEngine {
	/**
	 * フローの実行時に使用する{@link BuriUserContext}を生成して返します。
	 * 
	 * @param data
	 *            ぶりに管理させたいデータのDto
	 * @param userData
	 *            権限による制御を行う場合に使用するユーザ情報のDto
	 * @param action
	 *            トランジションの経路を指定する為の{@code action}
	 * @param context
	 *            その他必要な情報をセットした{@link Map}オブジェクト
	 * @return 各パラメータをセットした{@link BuriUserContext}
	 */
	BuriUserContext createUserContext(Object data, Object userData,
	        Object action, Map<String, Object> context);

	/**
	 * フローの実行時に使用する{@link BuriSystemContext}を生成して返します。
	 * 
	 * @param buriPath
	 *            パッケージ名.プロセス名[.アクティビティー名]
	 * @param userContext
	 *            フローの実行の際に使用する{@link BuriUserContext}
	 * @return 各パラメータをセットした{@link BuriSystemContext}
	 */
	BuriSystemContext createSystemContext(String buriPath,
	        BuriUserContext userContext);

	/**
	 * ぶりのフローに沿った状態の遷移処理を実行します。
	 * 
	 * @param sysContext
	 *            システムのコンテキスト情報をセットした{@link BuriSystemContext}
	 * @param resultScript
	 *            戻り値のOGNL式
	 * @return OGNL式の実行結果
	 */
	Object execute(BuriSystemContext sysContext, String resultScript);

	/**
	 * 指定したパスから実行するフローのプロセスを探し出して返します。
	 * 
	 * @param path
	 *            パッケージ名.プロセス名[.アクティビティー名]
	 * @return コンパイル済みの{@link BuriExecProcess}
	 */
	BuriExecProcess selectDirectProcess(BuriPath path);

	/**
	 * {@link BuriSystemContext}を元に、コンパイル済みのフローの パッケージを返します。
	 * 
	 * @param sysContext
	 *            システムコンテキスト
	 * @return {@link BuriExePackages}
	 */
	BuriExePackages selectPackage(BuriSystemContext sysContext);

	/**
	 * コンパイル済みのフローのパッケージ（{@link BuriExePackages}） からコンパイル済みのフローのプロセスを返します。
	 * 
	 * @param wPackageObj
	 *            コンパイル済みのフローの{@link BuriExePackages}
	 * @param sysContext
	 *            システムコンテキスト
	 * @return {@link BuriExecProcess}
	 */
	BuriExecProcess selectProcess(BuriExePackages wPackageObj,
	        BuriSystemContext sysContext);

	/**
	 * フローを読み込み、コンパイルをしてエンジンに登録します。
	 * <p>
	 * 登録するフローをリソース名で指定します。
	 * </p>
	 * 
	 * @param workFlowName
	 *            フローを示すリソース名
	 * @param resourceName
	 *            エンジンに登録する名称
	 */
	void readWorkFlowFromResource(String workFlowName, String resourceName);

	/**
	 * フローを読み込み、コンパイルをしてエンジンに登録します。
	 * <p>
	 * 登録するフローを{@link InputStream}オブジェクトで指定します。
	 * </p>
	 * 
	 * @param workFlowIs
	 *            フローを示す{@link InputStream}
	 * @param resourceName
	 *            エンジンに登録する名称
	 */
	void readWorkFlowFromInputStream(InputStream workFlowIs, String resourceName);

	/**
	 * フローを読み込み、コンパイルをしてエンジンに登録します。
	 * <p>
	 * 登録するフローを{@link BuriPackageType}オブジェクトで指定します。
	 * </p>
	 * 
	 * @param buriPackage
	 *            フローから取得した{@link BuriPackageType}
	 * @param resourceName
	 *            エンジンに登録する名称
	 */
	void readWorkFlowFromBuriPackageType(BuriPackageType buriPackage,
	        String resourceName);

	/**
	 * 権限による制御が必要なフローを読み込み、コンパイルをしてエンジンに登録します。
	 * 
	 * @param workFlowName
	 *            フローを示すリソース名
	 * @param resourceName
	 *            エンジンに登録する名称
	 * @param provider
	 *            権限管理に使用する{@link ParticipantProvider}
	 */
	void readWorkFlowFromResource(String workFlowName, String resourceName,
	        ParticipantProvider provider);

	/**
	 * 権限による制御が必要なフローを読み込み、コンパイルをしてエンジンに登録します。
	 * <p>
	 * 登録するフローを{@link InputStream}オブジェクトで指定します。
	 * </p>
	 * 
	 * @param workFlowIs
	 *            フローを示す{@link InputStream}
	 * @param resourceName
	 *            エンジンに登録する名称
	 * @param provider
	 *            権限管理に使用する{@link ParticipantProvider}
	 */
	void readWorkFlowFromInputStream(InputStream workFlowIs,
	        String resourceName, ParticipantProvider provider);

	/**
	 * フローを読み込み、コンパイルをしてエンジンに登録します。
	 * <p>
	 * 登録するフローを{@link BuriPackageType}オブジェクトで指定します。
	 * </p>
	 * 
	 * @param buriPackage
	 *            フローから取得した{@link BuriPackageType}
	 * @param resourceName
	 *            エンジンに登録する名称
	 * @param provider
	 *            権限管理に使用する{@link ParticipantProvider}
	 */
	void readWorkFlowFromBuriPackageType(BuriPackageType buriPackage,
	        String resourceName, ParticipantProvider provider);

	/**
	 * 指定したパッケージ名の{@link BuriExePackages}が存在するかどうかを判定します。
	 * 
	 * @param packageName
	 *            パッケージ名.プロセス名[.アクティビティー名]
	 * @return 存在していたら{@code true}、そうでない場合は{@code false}
	 */
	boolean hasPackage(String packageName);
}
