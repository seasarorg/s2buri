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
package org.escafe.buri.engine.processor;

import java.util.List;

import org.escafe.buri.common.participantprovider.impl.ExcelBaseParticipantProvider;
import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.engine.ParticipantProvider;
import org.escafe.buri.engine.impl.BuriStandardEngineImpl;
import org.seasar.framework.container.S2Container;

/**
 * ぶりの標準的な実行とデータの取得に関係するAPIのセットです。
 * <p>
 * 権限を伴う制御を必要としない状態管理を実行する際に使用します。 実行には事前にフローがこのクラスに対応するエンジン （このクラスの場合
 * {@link BuriStandardEngineImpl}）に登録されている必要があります。 このフローは、{@code
 * /buri/dicon/buri-user.dicon}の {@code "BuriEngineConfig"}に対してのコンポーネント定義で以下のように
 * 記述する事で登録ができます。 （以下の例では、権限主体に{@link ExcelBaseParticipantProvider}を指定しています。）
 * 
 * <pre>
 * &#064;code
 * &lt;initMethod name=&quot;addResourceConfig&quot;&gt;
 *     &lt;arg&gt;&quot;wakanagoxpdl/個人情報管理.xpdl&quot;&lt;/arg&gt;
 *     &lt;arg&gt;&quot;個人情報管理&quot;&lt;/arg&gt;
 *     &lt;arg&gt;ExcelBaseParticipantProvider&lt;/arg&gt;
 * &lt;/initMethod&gt;
 * }
 * </pre>
 * 
 * </p>
 * <p>
 * このプロセッサならびにエンジンへのコンポーネント定義は、 {@code /buri/dicon/buriStandard.dicon}です。
 * 拡張、変更が必要な際はこれを参考にして下さい。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2005/08/26
 */
public interface StandardBuriProcessor {
	/**
	 * 状態を先に進めます。
	 * <p>
	 * {@code data}にはぶりに管理させたいデータを指定して、 {@code path}にそのデータが現在存在している状態のパスを指定、 さらに
	 * {@code userData}でこのデータを処理するユーザの情報を指定することで、 ユーザの権限も踏まえた状態遷移を実行することができます。
	 * </p>
	 * <p>
	 * ユーザ情報を表すDtoを指定することで、フローを指定する際に一緒に指定した {@link ParticipantProvider}
	 * がぶりで権限管理を行えるよう、 内部で適切に処理をする事が可能となります。
	 * </p>
	 * 
	 * @param path
	 *            パッケージ名.プロセス名[.アクティビティ名]
	 * @param data
	 *            ぶりに管理させたい(させてる)Dto
	 * @param userData
	 *            権限を伴うフローに対応したユーザ情報を表わすDto
	 */
	void toNextStatus(String path, Object data, Object userData);

	/**
	 * 状態を先に進めます。
	 * <p>
	 * 戻り値は、指定したONGL式によって決定されます。
	 * </p>
	 * <p>
	 * {@code data}にはぶりに管理させたいデータを指定して、 {@code path}にそのデータが現在存在している状態のパスを指定、 さらに
	 * {@code userData}でこのデータを処理するユーザの情報を指定することで、 ユーザの権限も踏まえた状態遷移を実行することができます。
	 * </p>
	 * 
	 * @param path
	 *            パッケージ名.プロセス名[.アクティビティ名]
	 * @param data
	 *            ぶりに管理させたい(させてる)Dto
	 * @param userData
	 *            権限を伴うフローに対応したユーザ情報を表わすDto
	 * @param resultExp
	 *            戻り値を作るためのOGNL式
	 * @return {@code resultExp}の実行結果
	 */
	Object toNextStatus(String path, Object data, Object userData,
	        String resultExp);

	/**
	 * 状態を先に進めます。
	 * <p>
	 * {@code action}にはトランジションの経路を決定する為の文字列を指定します。
	 * </p>
	 * <p>
	 * {@code data}にはぶりに管理させたいデータを指定して、 {@code path}にそのデータが現在存在している状態のパスを指定、 さらに
	 * {@code userData}でこのデータを処理するユーザの情報を指定することで、 ユーザの権限も踏まえた状態遷移を実行することができます。
	 * </p>
	 * 
	 * @param path
	 *            パッケージ名.プロセス名[.アクティビティ名]
	 * @param data
	 *            ぶりに管理させたい(させてる)Dto
	 * @param userData
	 *            権限を伴うフローに対応したユーザ情報を表わすDto
	 * @param action
	 *            Actionの文字列、ぶりのなかでは#actionとして参照可能
	 */
	void toNextStatusAction(String path, Object data, Object userData,
	        Object action);

	/**
	 * 状態を先に進めます。
	 * <p>
	 * {@code data}にはぶりに管理させたいデータを指定して、 {@code path}にそのデータが現在存在している状態のパスを指定、 さらに
	 * {@code userData}でこのデータを処理するユーザの情報を指定することで、 ユーザの権限も踏まえた状態遷移を実行することができます。
	 * </p>
	 * <p>
	 * {@link BuriProcessorInfo}というDtoに種々のデータを保持させ、 それを引数にぶりの状態遷移を実行します。 この
	 * {@link BuriProcessorInfo}というクラスは、プロセッサからエンジンが 呼ばれる時に適用させるS2コンテナ、{@code
	 * action}、戻り値を作る為のOGNL式 を保持します。 開発者であるぶりのユーザが独自に設定したこのDtoを引数に指定できる事で、
	 * 最も多くの引数が指定できるメソッドです。
	 * </p>
	 * 
	 * @param path
	 *            パッケージ名.プロセス名[.アクティビティ名]
	 * @param data
	 *            ぶりに管理させたい(させてる)Dto
	 * @param userData
	 *            権限を伴うフローに対応したユーザ情報を表わすDto
	 * @param info
	 *            {@link BuriProcessorInfo}
	 * @return {@code info}に含まれるONGL式の実行結果
	 */
	Object toNextStatus(String path, Object data, Object userData,
	        BuriProcessorInfo info);

	/**
	 * 指定した状態に所属するデータのリストを返します。
	 * <p>
	 * 所属するデータが無い場合、空のリストが返されます。
	 * </p>
	 * 
	 * @param path
	 *            パッケージ名.プロセス名[.アクティビティ名]
	 * @param userData
	 *            権限を伴うフローに対応したユーザ情報を表わすDto
	 * @param tgtClass
	 *            リストにしたいDtoのクラス
	 * @return Dtoのリスト
	 */
	List<Object> getDataListFromPath(String path, Object userData,
	        Class<?> tgtClass);

	/**
	 * 指定した状態に所属するデータのキーをリストで返します。
	 * <p>
	 * 所属するデータが無い場合、空のリストが返されます。
	 * </p>
	 * <p>
	 * 任意のS2コンテナを使用する事ができます。
	 * </p>
	 * 
	 * @param path
	 *            パッケージ名.プロセス名[.アクティビティ名]
	 * @param userData
	 *            権限を伴うフローに対応したユーザ情報を表わすDto
	 * @param tgtClass
	 *            リストにしたいDtoのクラス
	 * @return Dtoのリスト
	 */
	List<Object> getDataIDFromPath(String path, Object userData,
	        Class<?> tgtClass);

	/**
	 * 指定したデータからアクティビティのパスを表わす{@link BuriPath}を取得して返します。
	 * <p>
	 * 対象のデータが複数のアクティビティに所属していた場合、複数件のリストとして返されます。
	 * </p>
	 * 
	 * @param path
	 *            パッケージ名.プロセス名[.アクティビティ名]
	 * @param data
	 *            ぶりに管理させたい(させてる)Dto
	 * @param userData
	 *            権限を伴うフローに対応したユーザ情報を表わすDto
	 * @param tgtClass
	 *            リストにしたいDtoのクラス
	 * @return {@link BuriPath}のリスト
	 */
	List<BuriPath> getPathFromData(String path, Object data, Object userData,
	        Class<?> tgtClass);

	/**
	 * 指定したデータの中の{@code path}で指定した状態に所属するデータを抜き出して返します。
	 * 
	 * @param path
	 *            パッケージ名.プロセス名[.アクティビティ名]
	 * @param datas
	 *            ぶりに管理させたい(させてる)Dtoのリスト
	 * @param userData
	 *            権限を伴うフローに対応したユーザ情報を表わすDto
	 * @return Dtoのリスト
	 */
	long countByPathAndDatas(String path, List<Object> datas, Object userData);

	/**
	 * 任意の{@link S2Container}を指定して、指定した状態に所属するデータの一覧を返します。
	 * 
	 * @param path
	 *            パッケージ名.プロセス名[.アクティビティ名]
	 * @param userData
	 *            権限を伴うフローに対応したユーザ情報を表わすDto
	 * @param tgtClass
	 *            Dtoのクラス
	 * @param container
	 *            任意の{@link S2Container}
	 * @return Dtoのリスト
	 */
	List<Object> getDataListFromPath(String path, Object userData,
	        Class<?> tgtClass, S2Container container);

	/**
	 * 任意の{@link S2Container}を指定して、指定した状態に所属するデータの IDのリストを返します。
	 * 
	 * @param path
	 *            パッケージ名.プロセス名[.アクティビティ名]
	 * @param userData
	 *            権限を伴うフローに対応したユーザ情報を表わすDto
	 * @param tgtClass
	 *            Dtoのクラス
	 * @param container
	 *            任意の{@link S2Container}
	 * @return DtoのIDのリスト
	 */
	List<Object> getDataIDFromPath(String path, Object userData,
	        Class<?> tgtClass, S2Container container);

	/**
	 * 指定したデータから所属するアクティビティの{@link BuriPath}のリストを探し出して返します。
	 * 
	 * @param path
	 *            パッケージ名.プロセス名[.アクティビティ名]
	 * @param data
	 *            ぶりに管理させたい(させてる)Dto
	 * @param userData
	 *            権限を伴うフローに対応したユーザ情報を表わすDto
	 * @param tgtClass
	 *            Dtoのクラス
	 * @param container
	 *            container 任意の{@link S2Container}
	 * @return {@link BuriPath}のリスト
	 */
	List<BuriPath> getPathFromData(String path, Object data, Object userData,
	        Class<?> tgtClass, S2Container container);

	/**
	 * 指定したデータの中の{@code path}で指定した状態に所属するデータを抜き出して返します。
	 * 
	 * @param path
	 *            パッケージ名.プロセス名[.アクティビティ名]
	 * @param datas
	 *            ぶりに管理させたい(させてる)Dtoのリスト
	 * @param userData
	 *            権限を伴うフローに対応したユーザ情報を表わすDto
	 * @param container
	 *            container 任意の{@link S2Container}
	 * @return Dtoのリスト
	 */
	long countByPathAndDatas(String path, List<Object> datas, Object userData,
	        S2Container container);
}
