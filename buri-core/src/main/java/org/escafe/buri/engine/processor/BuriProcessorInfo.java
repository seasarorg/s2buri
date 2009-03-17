/*
 * 作成日: 2006/06/14
 *
 */
package org.escafe.buri.engine.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.framework.container.S2Container;

public class BuriProcessorInfo {
	/**
	 * S2のコンテナ(ぶりが設定するので通常設定する必要なし)
	 */
    private S2Container container;
    /**
     * #action に指定したい任意のデータ
     */
    private Object action;
    /**
     * それ以外で渡したい情報
     * ぶりのなかでは#nameとして値を参照可能
     */
    private Map context = new HashMap();
    /**
     * 戻り値のOGNL式
     */
    private String resultExp;
    /**
     * 実行制約用のアクティビティー名のリスト
     * entityの状態がこれ以外だったらエラーになる
     */
    private List actNames;

    public Object getAction() {
        return action;
    }

    public void setAction(Object action) {
        this.action = action;
    }

    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }

    public String getResultExp() {
        return resultExp;
    }

    public void setResultExp(String resultExp) {
        this.resultExp = resultExp;
    }

    public void put(String key, Object val) {
        context.put(key, val);
    }

    public void putAll(Map datas) {
        context.putAll(datas);
    }

    public Object get(String key) {
        return context.get(key);
    }

    public Map getContext() {
        return context;
    }

    public List getActNames() {
        return actNames;
    }

    public void setActNames(List actNames) {
        this.actNames = actNames;
    }

}
