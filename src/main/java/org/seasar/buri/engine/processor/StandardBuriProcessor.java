/*
 * 作成日: 2005/08/26
 *
 */
package org.seasar.buri.engine.processor;

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
    Object toNextStatus(String path, Object data, Object userData,String context);

    void toNextStatusAction(String path, Object data,Object action, Object userData);
    Object toNextStatusAction(String path, Object data,Object action, Object userData,String context);

    void toNextStatus(S2Container container,String path, Object data, Object userData);
    Object toNextStatus(S2Container container,String path, Object data, Object userData,String context);

    void toNextStatusAction(S2Container container,String path, Object data,Object action, Object userData);
    Object toNextStatusAction(S2Container container,String path, Object data,Object action, Object userData,String context);
//
//    void invokeNoUpdate(String path, Object data, Object userData);
//    Object invokeNoUpdate(String path, Object data, Object userData,String context);
//
//    void invokeActionNoUpdate(String path, Object data,Object action, Object userData);
//    Object invokeActionNoUpdate(String path, Object data,Object action, Object userData,String context);
//
//    void invokeNoUpdate(String path, S2Container container,Object data, Object userData);
//    Object invokeNoUpdate(String path, S2Container container,Object data, Object userData,String context);
//
//    void invokeActionNoUpdate(String path, S2Container container,Object data,Object action, Object userData);
//    Object invokeActionNoUpdate(String path, S2Container container,Object data,Object action, Object userData,String context);


//    List findDataList(String path, Object userData,Object dto);
//    List getDataListFromPathAndUser(String path, Object userData);
//    List getDataIDFromPathAndUser(String path, Object userData);
    List getDataListFromPath(String path, Object userData);
    List getDataIDFromPath(String path, Object userData);
    List getPathFromData(String path, Object data, Object userData);

//    List findDataList(String path, Object userData,Object dto, S2Container container);
//    List getDataListFromPathAndUser(String path, Object userData, S2Container container);
//    List getDataIDFromPathAndUser(String path, Object userData, S2Container container);
    List getDataListFromPath(String path, Object userData, S2Container container);
    List getDataIDFromPath(String path, Object userData, S2Container container);
    List getPathFromData(String path, Object data, Object userData, S2Container container);

    List getDataListFromPathAndUserOnly(String path, Object userData);
    List getDataIDFromPathAndUserOnly(String path, Object userData);

    List getDataListFromPathAndUserOnly(String path, Object userData, S2Container container);
    List getDataIDFromPathAndUserOnly(String path, Object userData, S2Container container);
    long countByPathAndDatas(String path, List datas,Object userData);
    long countByPathAndDatas(String path, List datas,Object userData, S2Container container);

}
