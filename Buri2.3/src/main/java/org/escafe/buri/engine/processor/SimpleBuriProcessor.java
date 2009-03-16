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

import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.engine.impl.BuriSimpleEngineImpl;
import org.seasar.framework.container.S2Container;

/**
 * 基本的な機能を提供するAPIセットです。
 * <p>
 * 権限を伴う制御を必要としない状態管理を実行する際に使用します。
 * 実行には事前にフローがこのクラスに対応するエンジン
 * （このクラスの場合{@link BuriSimpleEngineImpl}）に登録されている必要があります。
 * このフローは、{@code /buri/dicon/buri-user.dicon}の
 * {@code "BuriEngineConfig"}に対してのコンポーネント定義で以下のように
 * 記述する事で登録ができます。
 * <pre>{@code
 * <initMethod name="addResourceConfig">
 *     <arg>"wakanagoxpdl/個人情報管理.xpdl"</arg>
 *     <arg>"個人情報管理"</arg>
 * </initMethod>
 * }</pre>
 * </p>
 * <p>
 * このAPIで提供されている{@code toNextStatus}メソッドの引数の{@code data}
 * ならびに{@code datas}には、ぶりで管理させたいデータベーステーブルと対応したDtoを指定します。
 * API使用時に未登録だったデータが指定された場合は、
 * そのDtoに対応するS2DaoのDaoインターフェイス{@code insert}メソッドが使用されます。
 * 既に登録済みだった場合は、{@code update}が行われます。
 * <br/>
 * 同じく{@code toNextStatus}メソッドの引数の{@code path}には、
 * 最初にデータを登録する場合を除いて常に
 * 「データが現在所属しているアクティビティを表わすパス（パッケージ名.プロセス名[.アクティビティ名]）」
 * を指定します。これは、遷移先のパスがフローに定義された{@code #action}や
 * ONGL式での条件式を評価した上で決定されるので、{@code toNextStatus}の実行時に
 * 予めそれを知る事が出来ない為です。
 * <br/>
 * この際、アクティビティ名を省略する事が可能です。
 * （ただし、テストの際の確認難度を上げるだけなのでお勧めできません。）
 * </p>
 * <p>
 * このクラスは{@link BuriSimpleEngineImpl}のWrapperで、{@code toNextStatus}メソッドは
 * 必要なコンテキストを作成した後に
 * {@link BuriSimpleEngineImpl#execute(org.escafe.buri.engine.BuriSystemContext, String)}
 * を実行します。
 * </p>
 * <p>
 * このプロセッサならびにエンジンへのコンポーネント定義は、
 * {@code /buri/dicon/buriSimple.dicon}です。
 * 拡張、変更が必要な際はこれを参考にして下さい。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/06/14
 */
public interface SimpleBuriProcessor {
    /**
     * 状態を次に進めるメソッドの最小インタフェース版です。
     * <p>
     * {@code data}にはぶりに管理させたいデータを指定して、
     * {@code path}にそのデータが現在存在している状態のパスを指定することで、
     * ぶりの管理下で状態遷移を実行することができます。
     * </p>
     * 
     * @param path パッケージ名.プロセス名[.アクティビティ名]
     * @param data ぶりに管理させたい(させてる)Dto
     */
    void toNextStatus(String path, Object data);

    /**
     * 状態を次に進めるメソッドの戻り値有り版です。
     * <p>
     * {@code data}にはぶりに管理させたいデータを指定して、
     * {@code path}として指定されたデータが現在存在している状態のパスを
     * 参照してぶりの状態遷移処理を実行します。
     * その後に、{@code resultExp}として指定されたONGL式の実行結果が
     * 戻り値として返されます。。
     * </p>
     * 
     * @param path パッケージ名.プロセス名[.アクティビティ名]
     * @param data ぶりに管理させたい(させてる)Dto
     * @param resultExp 戻り値を作るためのOGNL式
     * @return ONGL式の実行結果
     */
    Object toNextStatus(String path, Object data, String resultExp);

    /**
     * 状態を次に進めるメソッドの{@code action}付き版です。
     * <p>
     * {@code action}は、状態から状態への移動時にどのトランジションを
     * 経路として使用するか、を決定する為の条件の一種です。
     * <br/>
     * 例えば、フローに{@code #action == "hoge"}などと定義されたトランジションが
     * 存在していた場合、このメソッド実行時に引数{@code action}に
     * {@code "hoge"}をする事で、データの状態は{@code true}を返した
     * トランジションを経由して次の状態に移ることになります。
     * </p>
     * 
     * @param path パッケージ名.プロセス名[.アクティビティ名]
     * @param data ぶりに管理させたい(させてる)Dto
     * @param action Actionの文字列、ぶりのなかでは{@code #action}として参照可能
     */
    void toNextStatusAction(String path, Object data, String action);

    /**
     * 状態を次に進めるメソッドの高機能版です。
     * <p>
     * {@link BuriProcessorInfo}というDtoに種々のデータを保持させ、
     * それを引数にぶりの状態遷移を実行します。
     * この{@link BuriProcessorInfo}というクラスは、プロセッサからエンジンが
     * 呼ばれる時に適用させるS2コンテナ、{@code action}、戻り値を作る為のOGNL式
     * を保持します。
     * 開発者であるぶりのユーザが独自に設定したこのDtoを引数に指定できる事で、
     * 最も多くの引数が指定できるメソッドです。
     * </p>
     * 
     * @param path パッケージ名.プロセス名[.アクティビティ名]
     * @param data ぶりに管理させたい(させてる)Dto
     * @param info {@link BuriProcessorInfo}
     * @return infoに{@code resultExp}があれば評価結果を返す
     */
    Object toNextStatus(String path, Object data, BuriProcessorInfo info);

    /**
     * 指定した状態に所属するデータのリストを返します。
     * <p>
     * 所属するデータが無い場合、空のリストが返されます。
     * </p>
     * 
     * @param path パッケージ名.プロセス名[.アクティビティ名]
     * @param tgtClass リストにしたいDtoのクラス
     * @return Dtoのリスト
     */
    List<Object> getDataListFromPath(String path, Class<?> tgtClass);

    /**
     * 指定した状態に所属するデータのキーをリストで返します。
     * <p>
     * 所属するデータが無い場合、空のリストが返されます。
     * </p>
     * 
     * @param path パッケージ名.プロセス名[.アクティビティ名]
     * @param tgtClass リストにしたいDtoのクラス
     * @return DtoのIDのリスト
     */
    List<Object> getDataIDFromPath(String path, Class<?> tgtClass);

    /**
     * 指定したデータに所属するアクティビティのパス（{@link BuriPath}）のリストを返します。
     * <p>
     * 指定するパスは、フローのプロセスが識別できるものである必要があります。
     * </p>
     * 
     * @param path パッケージ名.プロセス名[.アクティビティ名]
     * @param data アクティビティのパスが知りたいDto
     * @return {@link BuriPath}のリスト
     */
    List<BuriPath> getPathFromData(String path, Object data);

    /**
     * 指定したデータのリスト内の指定されたパスに所属するデータの件数を返します。
     * 
     * @param path パッケージ名.プロセス名[.アクティビティ名]
     * @param datas データの一覧
     * @return データの件数
     */
    long countByPathAndDatas(String path, List<Object> datas);

    /**
     * 指定した状態に所属するデータのキーをリストで返します。
     * <p>
     * 所属するデータが無い場合、空のリストが返されます。
     * </p>
     * <p>
     * 任意のS2コンテナを使用する事ができます。
     * </p>
     * 
     * @param path パッケージ名.プロセス名[.アクティビティ名]
     * @param tgtClass リストにしたいDtoのクラス
     * @param container 任意の{@link S2Container}
     * @return Dtoのリスト
     */
    List<Object> getDataListFromPath(String path, Class<?> tgtClass, S2Container container);

    /**
     * 指定した状態に所属するデータのキーをリストで返します。
     * <p>
     * 所属するデータが無い場合、空のリストが返されます。
     * </p>
     * <p>
     * 任意のS2コンテナを使用する事ができます。
     * </p>
     * 
     * @param path パッケージ名.プロセス名[.アクティビティ名]
     * @param tgtClass リストにしたいDtoのクラス
     * @param container 任意の{@link S2Container}
     * @return DtoのIDのリスト
     */
    List<Object> getDataIDFromPath(String path, Class<?> tgtClass, S2Container container);

    /**
     * 指定したデータに所属するアクティビティのパス（{@link BuriPath}）のリストを返します。
     * <p>
     * 指定するパスは、フローのプロセスが識別できるものである必要があります。
     * </p>
     * <p>
     * 任意のS2コンテナを使用する事ができます。
     * </p>
     * 
     * @param path パッケージ名.プロセス名[.アクティビティ名]
     * @param data アクティビティのパスが知りたいDto
     * @param container 任意の{@link S2Container}
     * @return {@link BuriPath}のリスト
     */
    List<BuriPath> getPathFromData(String path, Object data, S2Container container);

    /**
     * 指定したデータのリスト内の指定されたパスに所属するデータの件数を返します。
     * <p>
     * 任意のS2コンテナを使用する事ができます。
     * </p>
     * 
     * @param path パッケージ名.プロセス名[.アクティビティ名]
     * @param datas データの一覧
     * @param container 任意の{@link S2Container}
     * @return データの件数
     */
    long countByPathAndDatas(String path, List<Object> datas, S2Container container);

}
