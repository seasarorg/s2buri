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
package org.escafe.buri.oouo.reader.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.escafe.buri.oouo.reader.OouoClassDef;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.FieldUtil;
import org.seasar.framework.util.MethodUtil;

/**
 * Oouo用クラス定義ユーティリティの実装です。
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/03/12
 */
public class OouoClassDefImpl implements OouoClassDef {
    /**
     * 対象のクラス
     */
    private Class<?> clazz;
    /**
     * Oouoで使用するシグネチャのリスト
     */
    private static List<OouoSig> fieldSigs = new ArrayList<OouoSig>();
    /**
     * 対象のクラスのオブジェクト
     */
    private Object tgtObj;

    /**
     * 対象のクラスのメソッド情報
     * <p>
     * メソッド名をキーに、{@link Method}を紐づけます。
     * </p>
     */
    private Map<String, Method> methodMap = new HashMap<String, Method>();

    /**
     * このオブジェクトの属性名
     */
    private String thisElementName = "";
    /**
     * 終了メソッド名
     */
    private Method finMethod = null;
    /**
     * テキストをセットする為のメソッド名
     */
    private Method textMethod = null;
    /**
     * 子要素を取得する為のセッターメソッド
     */
    private Map<String, Method> childElement = new HashMap<String, Method>();
    /**
     * 要素と属性を紐づけて保持する{@link Map}
     * <p>
     * {@code Map<String, Map<String, Method>>}
     * </p>
     */
    private Map<String, Map<String, Method>> attriElement = new HashMap<String, Map<String, Method>>();
    /**
     * 要素とそれへの参照となるメソッドを紐づけて保持する{@link Map}
     */
    private Map<String, Method> attri = new HashMap<String, Method>();

    /*
     * Static initializer.
     */
    static {
        /*
         * 属性のシグネチャの設定
         */
        OouoSig sig = new OouoSig();
        sig.setAction(OouoSig.ATTRI);
        sig.setSig("_ATTRI");
        fieldSigs.add(sig);
        /*
         * 要素のシグネチャの設定
         */
        sig = new OouoSig();
        sig.setAction(OouoSig.ELEMENT);
        sig.setSig("_ELEMENT");
        fieldSigs.add(sig);
        /*
         * 要素名の設定
         */
        sig = new OouoSig();
        sig.setAction(OouoSig.THIS);
        sig.setSig("OOUOTHIS");
        fieldSigs.add(sig);
        /*
         * 終了メソッドの設定
         */
        sig = new OouoSig();
        sig.setAction(OouoSig.FIN);
        sig.setSig("_OOUOFIN");
        fieldSigs.add(sig);
        /*
         * テキストをセットするメソッドの設定
         */
        sig = new OouoSig();
        sig.setAction(OouoSig.TEXT);
        sig.setSig("_OOUOTEXT");
        fieldSigs.add(sig);
    }

    /**
     * オブジェクトの初期化をします。
     */
    public void dispose() {
        methodMap.clear();
        childElement.clear();
        attriElement.clear();
        attri.clear();
        fieldSigs.clear();
    }

    /*
     * @see org.escafe.buri.oouo.reader.OouoClassDef#setClass(java.lang.Class)
     */
    public void setClass(Class<?> clazz) {
        this.clazz = clazz;
        tgtObj = ClassUtil.newInstance(clazz);
        Map<String, OouoAccessInfo> accessDef = setupAccessDef(clazz);
        setupMethodInfo(accessDef);
    }

    /**
     * メソッド情報をセットアップします。
     * 
     * @param accessDef
     */
    private void setupMethodInfo(Map<String, OouoAccessInfo> accessDef) {
        setupMethodNames();
        for (String key : accessDef.keySet()) {
            OouoAccessInfo accessInfo = accessDef.get(key);
            dispatch(accessInfo);
        }
    }

    /**
     * 対象のクラスからメソッドの一覧を取得して{@code methodMap}に登録します。
     */
    private void setupMethodNames() {
        Method methods[] = clazz.getMethods();
        for (Method method : methods) {
            if (isOouoMethod(method)) {
                methodMap.put(method.getName(), method);
            }
        }
    }

    /**
     * Oouoのアーキテクチャに沿ったメソッドかどうかを判定します。
     * 
     * @param method 判定したい{@link Method}
     * @return Oouoの為のメソッドだった場合は{@code true}、そうでない場合{@code false}
     */
    private boolean isOouoMethod(Method method) {
        int paramCount = method.getParameterTypes().length;
        if (paramCount <= 1) {
            return true;
        }
        return false;
    }

    /**
     * 要素を示すオブジェクトに設定された振る舞いの値を参照して、
     * {@link OouoAccessInfo}オブジェクトに与える操作を振り分けます。
     * 
     * @param accessInfo 要素への参照情報
     */
    private void dispatch(OouoAccessInfo accessInfo) {
        int type = accessInfo.getTypeSum();
        if (type == OouoSig.THIS) {
            // 始点
            setThisEleName(accessInfo);
        } else if (type == OouoSig.ATTRI) {
            // 要素
            attriDef(accessInfo);
        } else if (type == (OouoSig.ELEMENT + OouoSig.ATTRI)) {
            // 要素の属性
            attriEleDef(accessInfo);
        } else if (type == OouoSig.ELEMENT) {
            // 属性
            elementDef(accessInfo);
        } else if (type == OouoSig.FIN) {
            // 終了時
            setFinMethod(accessInfo);
        } else if (type == OouoSig.TEXT) {
            // テキストをセット
            setTextMethod(accessInfo);
        } else if (type == (OouoSig.ELEMENT + OouoSig.TEXT)) {
            // テキストをセット
            setTextMethod(accessInfo);
        }
    }

    /**
     * 要素を示す{@link OouoAccessInfo}からテキストをセットするメソッドを探し出してセットアップを行います。
     * 
     * @param accessInfo 要素を示す{@link OouoAccessInfo}
     */
    private void setTextMethod(OouoAccessInfo accessInfo) {
        textMethod = getSetupMethod(accessInfo);
    }

    /**
     * 要素を示す{@link OouoAccessInfo}から終了メソッドを探し出してセットアップを行います。
     * 
     * @param accessInfo 参照情報を示す{@link OouoAccessInfo}
     */
    private void setFinMethod(OouoAccessInfo accessInfo) {
        Method methods[] = clazz.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(accessInfo.getTgtName())) {
                finMethod = method;
            }
        }
    }

    /**
     * このオブジェクトの要素名を設定します。
     * 
     * @param accessInfo 参照情報を示す{@link OouoAccessInfo}
     */
    private void setThisEleName(OouoAccessInfo accessInfo) {
        OouoSig sig = (OouoSig) accessInfo.getSigSet().toArray()[0];
        thisElementName = (String) FieldUtil.get(sig.getField(), tgtObj);
    }

    /**
     * 要素とそれへの参照となるメソッドを紐づけて保持する{@link Map}への参照情報のセットアップを行います。
     * 
     * @param accessInfo 参照情報を示す{@link OouoAccessInfo}
     */
    private void attriDef(OouoAccessInfo accessInfo) {
        setupAccessInfoToMap(accessInfo, attri);
    }

    /**
     * 要素と属性を紐づけて保持する{@link Map}への参照情報のセットアップを行います。
     * 
     * @param accessInfo 参照情報を示す{@link OouoAccessInfo}
     */
    private void attriEleDef(OouoAccessInfo accessInfo) {
        attriEleImpl(accessInfo, OouoSig.ELEMENT, attriElement);
    }

    /**
     * 指定された{@link OouoAccessInfo}から取得した{@link OouoSig}情報を元に
     * 指定された{@code Map<String, Map<String, Method>>}を更新します。
     * <p>
     * {@code eleType}は2009/03/22時点で{@link OouoSig#ELEMENT}のみで使用されています。
     * </p>
     * 
     * @param accessInfo {@link OouoSig}情報を持つ{@link OouoAccessInfo}
     * @param eleType 要素の振る舞いを示す{@code int}値
     * @param tgtMap
     */
    private void attriEleImpl(OouoAccessInfo accessInfo, int eleType, Map<String, Map<String, Method>> tgtMap) {
        Map<String, Method> attriMap = new HashMap<String, Method>();
        String att = "";
        Method tgtMethod = null;
        for (OouoSig sig : accessInfo.getSigSet()) {
            String val = (String) FieldUtil.get(sig.getField(), tgtObj);
            if (sig.getAction() == eleType) {
                // 指定されたeleTypeとOouoSigの種別が同一の場合
                String ele = val;
                if (!tgtMap.containsKey(ele)) {
                    tgtMap.put(ele, attriMap);
                } else {
                    attriMap = tgtMap.get(ele);
                }
            }
            if (sig.getAction() == OouoSig.ATTRI) {
                // OouoSigの属性に対する処理
                att = val;
                tgtMethod = getSetupMethod(accessInfo);
            }
        }
        attriMap.put(att, tgtMethod);
    }

    /**
     * 要素に対する定義を行います。
     * <p>
     * {@link #setupAccessInfoToMap(OouoAccessInfo, Map)}へ制御を委譲します。
     * </p>
     * 
     * @param accessInfo {@link OouoAccessInfo}
     */
    private void elementDef(OouoAccessInfo accessInfo) {
        setupAccessInfoToMap(accessInfo, childElement);
    }

    /**
     * {@code tgtMap}として指定された{@link Map}に、プロパティ名と対応するメソッドを
     * {@link #getSetupMethod(OouoAccessInfo)}から取得して登録します。
     * 
     * @param accessInfo {@link OouoAccessInfo}
     * @param tgtMap {@code Map<String, Method>}
     */
    private void setupAccessInfoToMap(OouoAccessInfo accessInfo, Map<String, Method> tgtMap) {
        OouoSig sig = (OouoSig) accessInfo.getSigSet().toArray()[0];
        String val = (String) FieldUtil.get(sig.getField(), tgtObj);
        Method tgtMethod = getSetupMethod(accessInfo);
        tgtMap.put(val, tgtMethod);
    }

    /**
     * 指定された{@link OouoAccessInfo}の属性名がメソッド名として登録されているかどうかを判定し、
     * 存在した場合はその対応した{@link Method}を返します。
     * 対応するメソッドがなかった場合は{@code null}を返します。
     * 
     * @param accessInfo メソッドとして取得したい属性の{@link OouoAccessInfo}
     * @return 属性に対応した{@link Method}
     */
    private Method getSetupMethod(OouoAccessInfo accessInfo) {
        String tgtName = accessInfo.getTgtName();
        Method tgtMethod = null;
        if (methodMap.containsKey(tgtName)) {
            tgtMethod = (Method) methodMap.get(tgtName);
        }
        return tgtMethod;
    }

    /**
     * 指定されたクラスのプロパティの全てを{@code Map<String, OouoAccessInfo>}に
     * 変換して返します。
     * 
     * @param clazz 変換したいクラス
     * @return {@code Map<String, OouoAccessInfo>}
     */
    private Map<String, OouoAccessInfo> setupAccessDef(Class<?> clazz) {
        Field field[] = clazz.getFields();
        Map<String, OouoAccessInfo> accessDef = new HashMap<String, OouoAccessInfo>();
        for (Field tgt : field) {
            fieldProcess(tgt, accessDef);
        }
        return accessDef;
    }

    /**
     * 指定された{@link Field}を元に、{@code Map<String, OouoAccessInfo>}の内容を補完します。
     * 
     * @param tgt {@code accessDef}にセットしたい{@link Field}
     * @param accessDef 補完したい{@code Map<String, OouoAccessInfo>}
     */
    private void fieldProcess(Field tgt, Map<String, OouoAccessInfo> accessDef) {
        if (tgt.getType().equals(String.class)) {
            OouoSig sig = findTgtFieldName(tgt);
            if (sig == null) {
                return;
            }
            createOouoAccessInfo(tgt, sig, accessDef);
        }
    }

    /**
     * 指定されたクラスのプロパティを元に、{@code Map<String, OouoAccessInfo>}を生成します。
     * 
     * @param tgt {@link OouoAccessInfo}の元になる{@link Field}
     * @param sig {@link Field}を参照する為の{@link OouoSig}
     * @param accessDef 更新する{@code Map<String, OouoAccessInfo>}
     */
    private void createOouoAccessInfo(Field tgt, OouoSig sig, Map<String, OouoAccessInfo> accessDef) {
        String tgtName = tgt.getName().replaceAll(sig.getSig(), "");
        OouoAccessInfo accessInfo = null;
        if (accessDef.containsKey(tgtName)) {
            // すでに登録済みのプロパティの場合
            accessInfo = (OouoAccessInfo) accessDef.get(tgtName);
        } else {
            // 未登録のプロパティの場合
            accessInfo = new OouoAccessInfo();
            accessInfo.setTgtName(tgtName);
            accessDef.put(tgtName, accessInfo);
        }
        accessInfo.getSigSet().add(sig);
        accessInfo.addType(sig);
    }

    /**
     * 指定された{@link Field}から{@link OouoSig}を探し出して返します。
     * <p>
     * {@link Field}の{@code Name}と同じシグネチャを持つ{@link OouoSig}がなかった場合、{@code null}を返します。
     * </p>
     * 
     * @param tgt {@link OouoSig}にしたい{@link Field}
     * @return {@link OouoSig}
     */
    private OouoSig findTgtFieldName(Field tgt) {
        for (OouoSig sig : fieldSigs) {
            if (tgt.getName().indexOf(sig.getSig()) >= 0) {
                return sig.copy(tgt);
            }
        }
        return null;
    }

    /*
     * @see org.escafe.buri.oouo.reader.OouoClassDef#isChildElement(java.lang.String)
     */
    public boolean isChildElement(String elename) {
        if (childElement.containsKey(elename)) {
            return true;
        }
        return false;
    }

    /*
     * @see org.escafe.buri.oouo.reader.OouoClassDef#getElementName()
     */
    public String getElementName() {
        return thisElementName;
    }

    /*
     * @see org.escafe.buri.oouo.reader.OouoClassDef#getChildObject(java.lang.String)
     */
    public Object getChildObject(String name) {
        Method tgtMethod = getSetterMethod(name);
        if (tgtMethod.getParameterTypes().length == 0) {
            return null;
        }
        Class<?> clazz = tgtMethod.getParameterTypes()[0];
        Object obj = ClassUtil.newInstance(clazz);
        return obj;
    }

    /**
     * セッターメソッドを返します。
     * 
     * @param name 子要素名
     * @return セッターメソッド
     */
    private Method getSetterMethod(String name) {
        Method tgtMethod = null;
        if (childElement.containsKey(name)) {
            tgtMethod = (Method) childElement.get(name);
        }
        return tgtMethod;
    }

    /*
     * @see org.escafe.buri.oouo.reader.OouoClassDef#setChild(java.lang.Object,
     *      java.lang.String, java.lang.Object)
     */
    public void setChild(Object now, String name, Object child) {
        Method tgtMethod = getSetterMethod(name);
        if (child != null) {
            MethodUtil.invoke(tgtMethod, now, new Object[] { child });
        } else {
            MethodUtil.invoke(tgtMethod, now, null);
        }
    }

    /*
     * @see org.escafe.buri.oouo.reader.OouoClassDef#isChildAttribute(java.lang.String, java.lang.String)
     */
    public boolean isChildAttribute(String name, String type) {
        Map<String, Method> types = null;
        if (attriElement.containsKey(name)) {
            types = attriElement.get(name);
        }
        if ((types != null) && types.containsKey(type)) {
            return true;
        }
        return false;
    }

    /*
     * @see org.escafe.buri.oouo.reader.OouoClassDef#setChildAttribute(java.lang.Object, java.lang.String, java.lang.String, java.lang.String)
     */
    public void setChildAttribute(Object now, String name, String type, String value) {
        Method tgtMethod = null;
        Map<String, Method> types = null;
        if (attriElement.containsKey(name)) {
            types = (Map<String, Method>) attriElement.get(name);
        }
        if ((types != null) && types.containsKey(type)) {
            tgtMethod = (Method) types.get(type);
            MethodUtil.invoke(tgtMethod, now, new Object[] { value });
        }

    }

    /*
     * @see org.escafe.buri.oouo.reader.OouoClassDef#isAttribute(java.lang.String)
     */
    public boolean isAttribute(String type) {
        if (attri.containsKey(type)) {
            return true;
        }
        return false;

    }

    /*
     * @see org.escafe.buri.oouo.reader.OouoClassDef#setAttribute(java.lang.Object, java.lang.String, java.lang.String)
     */
    public void setAttribute(Object now, String type, String value) {
        Method method = attri.get(type);
        if (method.getParameterTypes()[0].equals(String.class)) {
            MethodUtil.invoke(method, now, new Object[] { value });
        }
        // TODO 型変換＆セット関係を充実させる
    }

    /*
     * @see org.escafe.buri.oouo.reader.OouoClassDef#getTgtClass()
     */
    public Class<?> getTgtClass() {
        return clazz;
    }

    /*
     * @see org.escafe.buri.oouo.reader.OouoClassDef#isCallFinMethod()
     */
    public boolean isCallFinMethod() {
        if (finMethod == null) {
            return false;
        }
        return true;
    }

    /*
     * @see org.escafe.buri.oouo.reader.OouoClassDef#fin(java.lang.Object, java.lang.Object)
     */
    public void fin(Object now, Object parent) {
        if (finMethod.getParameterTypes().length == 0) {
            MethodUtil.invoke(finMethod, now, null);
        } else {
            MethodUtil.invoke(finMethod, now, new Object[] { parent });
        }
    }

    /*
     * @see org.escafe.buri.oouo.reader.OouoClassDef#isSetTextMethod()
     */
    public boolean isSetTextMethod() {
        if (textMethod == null) {
            return false;
        }
        return true;
    }

    /*
     * @see org.escafe.buri.oouo.reader.OouoClassDef#setText(java.lang.Object, java.lang.String)
     */
    public void setText(Object now, String value) {
        MethodUtil.invoke(textMethod, now, new Object[] { value });
    }

}
