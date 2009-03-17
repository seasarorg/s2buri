/*
 * 作成日: 2005/08/18
 *
 */
package org.escafe.buri.engine.processor;

import java.util.List;

import org.seasar.framework.container.S2Container;

public interface SimpleBuriProcessor {
	/**
	 * 状態を次に進めるメソッドの最小インタフェース版
	 * @param path パッケージ名.プロセス名[.アクティビティー名]を指定する
	 * @param data ぶりに管理させたい(させてる)entity
	 */
    void toNextStatus(String path, Object data);

    /**
     * 状態を次に進めるメソッドの戻り値有り版
     * @param path パッケージ名.プロセス名[.アクティビティー名]を指定する
     * @param data ぶりに管理させたい(させてる)entity
     * @param resultExp 戻り値を作るためのOGNL式
     * @return
     */
    Object toNextStatus(String path, Object data, String resultExp);

    /**
     * 状態を次に進めるメソッドのaction付き版
     * @param path パッケージ名.プロセス名[.アクティビティー名]を指定する
     * @param data ぶりに管理させたい(させてる)entity
     * @param action Actionの文字列、ぶりのなかでは#actionとして参照可能
     */
    void toNextStatusAction(String path, Object data, String action);

    /**
     * 状態を次に進めるメソッドの高機能版
     * @param path パッケージ名.プロセス名[.アクティビティー名]を指定する
     * @param data ぶりに管理させたい(させてる)entity
     * @param info BuriProcessorInfoを渡す
     * @return infoにresultExpがあれば評価結果を返す
     */
    Object toNextStatus(String path, Object data, BuriProcessorInfo info);

    List getDataListFromPath(String path, Class tgtClass);

    List getDataIDFromPath(String path, Class tgtClass);

    List getPathFromData(String path, Object data);

    long countByPathAndDatas(String path, List datas);

    List getDataListFromPath(String path, Class tgtClass, S2Container container);

    List getDataIDFromPath(String path, Class tgtClass, S2Container container);

    List getPathFromData(String path, Object data, S2Container container);

    long countByPathAndDatas(String path, List datas, S2Container container);

}
