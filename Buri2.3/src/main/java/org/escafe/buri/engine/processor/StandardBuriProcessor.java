/*
 * 作成日: 2005/08/26
 *
 */
package org.escafe.buri.engine.processor;

import java.util.List;

import org.seasar.framework.container.S2Container;

/**
 * ぶりの標準的な実行とデータの取得に関係するAPIのセット
 * ユーザの権限によって動作を変更する場合はこのAPIを使う
 * 
 * invoke 通常のInvoke
 * context 実行の結果として値を取得したい場合のためのOGNL式
 * これに加えてメソッドの後ろで動作が変化する
 * 
 * Action action付きのInvoke
 * NoUpdate dataをUpdateしないInvoke
 * 
 * dataとactionはXPDLでは#を付けて#data,#actionみたいに扱う
 * あとは通常のOGNL式を書く
 * 
 * 
 * 
 * @author makotan
 *
 */
public interface StandardBuriProcessor {
    void toNextStatus(String path, Object data, Object userData);

    Object toNextStatus(String path, Object data, Object userData, String resultExp);

    void toNextStatusAction(String path, Object data, Object userData, Object action);

    Object toNextStatus(String path, Object data, Object userData, BuriProcessorInfo info);

    List getDataListFromPath(String path, Object userData, Class tgtClass);

    List getDataIDFromPath(String path, Object userData, Class tgtClass);

    List getPathFromData(String path, Object data, Object userData, Class tgtClass);

    long countByPathAndDatas(String path, List datas, Object userData);

    List getDataListFromPath(String path, Object userData, Class tgtClass, S2Container container);

    List getDataIDFromPath(String path, Object userData, Class tgtClass, S2Container container);

    List getPathFromData(String path, Object data, Object userData, Class tgtClass, S2Container container);

    long countByPathAndDatas(String path, List datas, Object userData, S2Container container);
}
