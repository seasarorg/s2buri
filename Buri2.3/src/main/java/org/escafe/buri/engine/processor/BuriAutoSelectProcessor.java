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

import org.escafe.buri.engine.BuriEngineConfig;
import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.engine.impl.BuriSimpleEngineImpl;
import org.escafe.buri.engine.impl.BuriStandardEngineImpl;
import org.escafe.buri.engine.processor.impl.BuriAutoSelectAutoSignalProcessorImpl;
import org.escafe.buri.engine.processor.impl.BuriAutoSelectProcessorImpl;
import org.seasar.coffee.dataaccess.DataAccessFactory;
import org.seasar.framework.container.S2Container;

/**
 * ぶりのプロセッサとして最もレイヤの高いAPIセットです。
 * <p>
 * ぶりには既に{@link SimpleBuriProcessor}と{@link StandardBuriProcessor}という高レイヤの
 * APIセットが提供されていますが、このクラスはその両方のプロセッサをサポートする為のAPIを提供しています。
 * このクラスより高いレイヤのAPIは{@code Bao}と派生した拡張APIとして
 * {@link BuriAutoSelectAutoSignalProcessorImpl}があります。
 * </p>
 * <p>
 * 具体的には、このクラス（の実装クラスである{@link BuriAutoSelectProcessorImpl}）は
 * {@link BuriSimpleEngineImpl}と{@link BuriStandardEngineImpl}の
 * 両方のエンジンを保有し、かつ{@link SimpleBuriProcessor}と{@link StandardBuriProcessor}
 * の両方のプロセッサも保有します。
 * このクラスでは実行するフローによって適宜判別して実行します。
 * 以下は、プロセッサとエンジンの関係です。
 * <ul>
 * <li>{@link SimpleBuriProcessor}={@link BuriSimpleEngineImpl}</li>
 * <li>{@link StandardBuriProcessor}={@link BuriStandardEngineImpl}</li>
* </ul>
 * </p>
 * <p>
 * フローそれぞれに適用させるプロセッサを判別させる基準は、
 * そのフローがどちらのプロセッサに登録されているか、を参照して判断します。
 * フローはコンパイルが無事完了した後ぶりはコンパイルを担当したエンジンに
 * コンパイル後のフローを保有しますが、このクラスは、保有する両方のエンジンを参照する事で
 * フローがいずれのプロセッサのものかを判定しています。
 * このフローの登録は、{@code buri/dicon/buri-user.dicon} の
 * {@code BuriEngineConfig}（{@link BuriEngineConfig}）で以下のように設定します。
 * 
 * <pre>{@code
 * <initMethod name="addResourceConfig">
 *     <arg>"wakanagoxpdl/個人情報管理.xpdl"</arg>
 *     <arg>"個人情報管理"</arg>
 *     <arg>ExcelBaseParticipantProvider</arg>
 * </initMethod>
 * }</pre>
 * 
 * </p>
 * <p>
 * なお、このインターフェイスへのDI定義は、
 * {@code src/main/resources/buri/dicon/buriBase.dicon}に定義されています。
 * <br/>拡張や変更が必要な場合は、このdiconファイルを参考にしてください。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/06/14
 */
public interface BuriAutoSelectProcessor {

    /**
     * 指定されたパスが{@link BuriStandardEngineImpl}に登録されているパスかどうかを判別します。
     * <p>
     * パスが表すフローのプロセスは、ぶりによってクラスとしてコンパイルされ、
     * エンジンの属性として保持されます。
     * その為、指定されたパスがどちらのエンジンに保持されているか、
     * を参照することでパスから実行させたいエンジンを特定できるという仕組みになっています。
     * </p>
     * 
     * @param buriPath パッケージ名.プロセス名[.アクティビティー名]
     * @return 登録されていた場合は{@code true}、そうでない場合は{@code false}
     */
    boolean isStdProcessor(String buriPath);

    /**
     * 指定したパスが{@link BuriSimpleEngineImpl}に登録されているパスかどうかを判別します。
     * <p>
     * パスが表すフローのプロセスは、ぶりによってクラスとしてコンパイルされ、
     * エンジンの属性として保持されます。
     * その為、指定されたパスがどちらのエンジンに保持されているか、
     * を参照することでパスから実行させたいエンジンを特定できるという仕組みになっています。
     * </p>
     * 
     * @param buriPath パッケージ名.プロセス名[.アクティビティー名]
     * @return 登録されていた場合は{@code true}、そうでない場合は{@code false}
     */
    boolean isSimpleProcessor(String buriPath);

    /**
     * 指定したパスの実行可能なフロープロセスを{@link BuriSimpleEngineImpl}
     * または{@link BuriStandardEngineImpl}から取得して、
     * {@link DataAccessFactory}のオブジェクトとして返します。
     * 
     * @param buriPath パッケージ名.プロセス名[.アクティビティー名]
     * @return 実行対象のパスに対応した{@link DataAccessFactory}
     */
    DataAccessFactory getDataAccessFactory(String buriPath);

    /**
     * 状態を次に進めます。
     * <p>
     * 権限管理を行わないフローを実行する場合は、{@code userData}に{@code null}を指定します。
     * </p>
     * <p>
     * 状態を進めた結果は、トランジションに設定された条件やactionを評価した結果で決定されます。
     * <br/>{@code path}には、現在Entityが存在する{@code path}を指定します。
     * </p>
     * 
     * @param path パッケージ名.プロセス名[.アクティビティー名]
     * @param data ぶりに管理させたい(させてる)Dto
     * @param userData 権限を伴うフローに対応したユーザ情報を表わすDto
     */
    void toNextStatus(String path, Object data, Object userData);

    /**
     * 状態を次に進めます。
     * <p>
     * 権限管理を行わないフローを実行する場合は、
     * {@code userData}に{@code null}を指定します。
     * </p>
     * <p>
     * 状態を進めた結果は、トランジションに設定された条件やactionを評価した結果で決定されます。
     * <br/>{@code path}には、現在Entityが存在する{@code path}を指定します。
     * </p>
     * 
     * @param path パッケージ名.プロセス名[.アクティビティー名]
     * @param data ぶりに管理させたい(させてる)Dto
     * @param userData 権限を伴うフローに対応したユーザ情報を表わすDto
     * @param resultExp 戻り値を作るためのOGNL式
     * @return {@code resultExp}の実行結果
     */
    Object toNextStatus(String path, Object data, Object userData, String resultExp);

    /**
     * 状態を次に進めます。
     * その際に、{@code action}を指定してトランジションの経路を指定する事が可能です。
     * <p>
     * 権限管理を行わないフローを実行する場合は、 {@code userData}に{@code null}を指定します。
     * </p>
     * <p>
     * 状態を進めた結果は、トランジションに設定された条件やactionを評価した結果で決定されます。
     * <br/>{@code path}には、現在Entityが存在する{@code path}を指定します。
     * </p>
     * 
     * @param path パッケージ名.プロセス名[.アクティビティー名]
     * @param data ぶりに管理させたい(させてる)Dto
     * @param userData 権限を伴うフローに対応したユーザ情報を表わすDto
     * @param action Actionの文字列、ぶりのなかでは{@code #action}として参照可能
     */
    void toNextStatusAction(String path, Object data, Object userData, Object action);

    /**
     * 状態を次に進めます。
     * <p>
     * 権限管理を行わないフローを実行する場合は、
     * {@code userData}に{@code null}を指定します。
     * </p>
     * <p>
     * 状態を進めた結果は、トランジションに設定された条件やactionを評価した結果で決定されます。
     * <br/>{@code path}には、現在Entityが存在する{@code path}を指定します。
     * </p>
     * 
     * @param path パッケージ名.プロセス名[.アクティビティー名]
     * @param data ぶりに管理させたい(させてる)Dto
     * @param userData 権限を伴うフローに対応したユーザ情報を表わすDto
     * @param info {@link BuriProcessorInfo}
     * @return {@code info}に含まれる戻り値を作るONGL式の実行結果
     */
    Object toNextStatus(String path, Object data, Object userData, BuriProcessorInfo info);

    /**
     * 指定した状態に所属するデータのリストを返します。
     * <p>
     * 所属するデータが無い場合、空のリストが返されます。
     * </p>
     * 
     * @param path パッケージ名.プロセス名[.アクティビティー名]
     * @param userData 権限を伴うフローに対応したユーザ情報を表わすDto
     * @param tgtClass リストにしたいデータのクラス
     * @return Dtoのリスト
     */
    List<Object> getDataListFromPath(String path, Object userData, Class<?> tgtClass);

    /**
     * 指定した状態に所属するデータのキーをリストで返します。
     * <p>
     * 所属するデータが無い場合、空のリストが返されます。
     * </p>
     * 
     * @param path パッケージ名.プロセス名[.アクティビティー名]
     * @param userData 権限を伴うフローに対応したユーザ情報を表わすDto
     * @param tgtClass リストにしたいデータのクラス
     * @return Dtoのキーのリスト
     */
    List<Object> getDataIDFromPath(String path, Object userData, Class<?> tgtClass);

    /**
     * 指定したデータに所属するアクティビティのパス（{@link BuriPath}）のリストを返します。
     * <p>
     * 指定するパスは、フローのプロセスが識別できるものである必要があります。
     * </p>
     * <p>
     * ぶりで管理するデータが持つ状態は通常1つです。
     * ただし、AND-Splitで分岐するフローの場合、1つのデータが複数の条件に
     * 所属する事があります。
     * </p>
     * 
     * @param path パッケージ名.プロセス名[.アクティビティ名]
     * @param data ぶりに管理させたい(させてる)Dto
     * @param userData 権限を伴うフローに対応したユーザ情報を表わすDto
     * @param tgtClass リストにしたいデータのクラス
     * @return {@link BuriPath}のリスト
     */
    List<BuriPath> getPathFromData(String path, Object data, Object userData, Class<?> tgtClass);

    /**
     * 指定したデータのリスト内の指定されたパスに所属するデータの件数を返します。
     * <p>
     * 指定するパスは、フローのプロセスが識別できるものである必要があります。
     * </p>
     * 
     * @param path パッケージ名.プロセス名[.アクティビティー名]
     * @param datas ぶりに管理させたい(させてる)Dtoのリスト
     * @param userData 権限を伴うフローに対応したユーザ情報を表わすDto
     * @return データの件数
     */
    long countByPathAndDatas(String path, List<Object> datas, Object userData);

    /**
     * 指定した状態に所属するデータのリストを返します。
     * <p>
     * 所属するデータが無い場合、空のリストが返されます。
     * </p>
     * 
     * @param path パッケージ名.プロセス名[.アクティビティー名]
     * @param userData 権限を伴うフローに対応したユーザ情報を表わすDto
     * @param tgtClass リストにしたいデータのクラス
     * @param container 任意の{@link S2Container}
     * @return Dtoのリスト
     */
    List<Object> getDataListFromPath(String path, Object userData, Class<?> tgtClass, S2Container container);

    /**
     * 指定した状態に所属するデータのキーをリストで返します。
     * <p>
     * 所属するデータが無い場合、空のリストが返されます。
     * </p>
     * 
     * @param path パッケージ名.プロセス名[.アクティビティー名]
     * @param userData 権限を伴うフローに対応したユーザ情報を表わすDto
     * @param tgtClass リストにしたいデータのクラス
     * @param container 任意の{@link S2Container}
     * @return Dtoのキーのリスト
     */
    List<Object> getDataIDFromPath(String path, Object userData, Class<?> tgtClass, S2Container container);

    /**
     * 指定したデータに所属するアクティビティのパス（{@link BuriPath}）のリストを返します。
     * <p>
     * 指定するパスは、フローのプロセスが識別できるものである必要があります。
     * </p>
     * <p>
     * ぶりで管理するデータが持つ状態は通常1つです。
     * ただし、AND-Splitで分岐するフローの場合、1つのデータが複数の条件に
     * 所属する事があります。
     * </p>
     * 
     * @param path パッケージ名.プロセス名[.アクティビティ名]
     * @param data ぶりに管理させたい(させてる)Dto
     * @param userData 権限を伴うフローに対応したユーザ情報を表わすDto
     * @param tgtClass リストにしたいデータのクラス
     * @param container 任意の{@link S2Container}
     * @return {@link BuriPath}のリスト
     */
    List<BuriPath> getPathFromData(String path, Object data, Object userData, Class<?> tgtClass, S2Container container);

    /**
     * 指定したデータのリスト内の指定されたパスに所属するデータの件数を返します。
     * 
     * @param path パッケージ名.プロセス名[.アクティビティー名]
     * @param datas ぶりに管理させたい(させてる)Dtoのリスト
     * @param userData 権限を伴うフローに対応したユーザ情報を表わすDto
     * @param container 任意の{@link S2Container}
     * @return データの件数
     */
    long countByPathAndDatas(String path, List<Object> datas, Object userData, S2Container container);
}
