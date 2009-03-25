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

import java.util.Stack;

import org.escafe.buri.oouo.reader.OouoClassDef;
import org.escafe.buri.oouo.reader.OouoClassDefFactory;
import org.seasar.framework.container.S2Container;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * XMLを解析するためのイベントハンドラです。
 * <p>
 * XPDLフォーマットに対応しています。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/03/06
 */
public class XmlHandler extends DefaultHandler {
    /**
     * S2コンテナ
     */
    private S2Container container;
    /**
     * Oouo用のクラス定義ファクトリ
     */
    private OouoClassDefFactory classDefFactory;
    /**
     * ルートノードのオブジェクト
     */
    private Object root = null;
    /**
     * 現在位置
     */
    private Object now = null;
    /**
     * Oouo用のクラス定義ユーティリティ
     */
    private OouoClassDef classDef;
    /**
     * 解析後のオブジェクト
     */
    private Stack<Object> objStack = new Stack<Object>();
    /**
     * 解析後のエレメント
     */
    private Stack<Object> eleStack = new Stack<Object>();
    /**
     * 上位ノードのエレメント名
     */
    private String parentQName;
    /**
     * 現在ノードのエレメント名
     */
    private String nowQName;

    /*
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
     *      java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (root == null) {
            /*
             * ルートが未設定の場合
             */
            root = classDefFactory.getRoot(qName);
            now = root;
            classDef = classDefFactory.create(now.getClass());

            objStack.push(now);
            eleStack.push(parentQName);
            parentQName = qName;
        } else {
            /*
             * ルートを設定した後の処理
             */
            if (classDef.isChildElement(qName)) {
                objStack.push(now);
                eleStack.push(parentQName);
                parentQName = qName;

                Object child = classDef.getChildObject(qName);
                now = child;
                if (now != null) {
                    classDef = classDefFactory.create(now.getClass());
                }
            }
        }
        if (classDef.getElementName().endsWith(qName)) {
            for (int i = 0; i < attributes.getLength(); i++) {
                String tgtName = attributes.getQName(i);
                if (classDef.isAttribute(tgtName)) {
                    classDef.setAttribute(now, tgtName, attributes.getValue(i));
                }
            }
        } else {
            for (int i = 0; i < attributes.getLength(); i++) {
                String tgtName = attributes.getQName(i);
                if (classDef.isChildAttribute(qName, tgtName)) {
                    classDef.setChildAttribute(now, qName, tgtName, attributes.getValue(i));
                }
            }
        }
        nowQName = qName;
    }

    /*
     * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    @Override
    public void endElement(String uri, String localName, String qName) {
        if (parentQName.equalsIgnoreCase(qName)) {
            Object childObj = now;
            parentQName = (String) eleStack.pop();
            now = objStack.pop();
            if (classDef.isCallFinMethod()) {
                classDef.fin(childObj, now);
            }
            classDef = classDefFactory.create(now.getClass());
            if (classDef.isChildElement(qName)) {
                classDef.setChild(now, qName, childObj);
            }
            nowQName = classDef.getElementName();
        }
    }

    /*
     * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
     */
    @Override
    public void characters(char[] ch, int start, int length) {
        if (nowQName.equals(classDef.getElementName())) {
            String text = new String(ch, start, length);
            if (classDef.isSetTextMethod()) {
                classDef.setText(now, text);
            }
        }
    }

    /**
     * S2コンテナを返します。
     * 
     * @return S2コンテナ
     */
    public S2Container getContainer() {
        return container;
    }

    /**
     * S2コンテナを登録します。
     * <p>
     * {@link S2Container}で自動的にバインドさせる為のメソッドです。
     * </p>
     * 
     * @param container S2コンテナ
     */
    public void setContainer(S2Container container) {
        this.container = container;
    }

    /**
     * Oouo用のクラス定義ファクトリを返します。
     * 
     * @return Oouo用のクラス定義ファクトリ
     */
    public OouoClassDefFactory getClassDefFactory() {
        return classDefFactory;
    }

    /**
     * Oouo用のクラス定義ファクトリを登録します。
     * 
     * @param classDefFactory Oouo用のクラス定義ファクトリ
     */
    public void setClassDefFactory(OouoClassDefFactory classDefFactory) {
        this.classDefFactory = classDefFactory;
    }

    /**
     * ルートノードのオブジェクトを返します。
     * 
     * @return ルートノードのオブジェクト
     */
    public Object getRoot() {
        return root;
    }

    /**
     * ルートノードのオブジェクトを登録します。
     * 
     * @param root ルートノードのオブジェクト
     */
    public void setRoot(Object root) {
        this.root = root;
    }
}
