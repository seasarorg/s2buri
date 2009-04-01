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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.escafe.buri.oouo.reader.OouoClassDef;
import org.escafe.buri.oouo.reader.OouoClassDefFactory;
import org.escafe.buri.oouo.reader.OouoReader;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.exception.IORuntimeException;
import org.seasar.framework.exception.ParserConfigurationRuntimeException;
import org.seasar.framework.exception.SAXRuntimeException;
import org.seasar.framework.util.FileInputStreamUtil;
import org.seasar.framework.util.ResourceUtil;
import org.xml.sax.SAXException;

/**
 * ワークフローを読み込む為のリーダの実装です。
 * <p>
 * XMLフォーマットで記述されたワークフローに対応したリーダです。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/03/09
 */
public class XMLOouoReader implements OouoReader {
    /**
     * ルートクラスを保持する{@link Map}
     * <p>
     * ツリー構造の頂点となるクラスを、頂点のノードに指定された{@code name}に紐付けて登録します。
     * </p>
     */
    private Map<String, Class<?>> rootClass = new HashMap<String, Class<?>>();
    /**
     * S2コンテナ
     */
    private S2Container container;
    /**
     * Oouoのクラス定義ファクトリ
     */
    private OouoClassDefFactory oouoClassDefFactory;

    /**
     * オブジェクトの初期化をします。
     */
    public void dispose() {
        rootClass.clear();
    }

    /*
     * @see org.escafe.buri.oouo.reader.OouoReader#readInputStream(java.io.InputStream)
     */
    public Object readInputStream(InputStream workFlowIs) {
        return read(workFlowIs);
    }

    /*
     * @see org.escafe.buri.oouo.reader.OouoReader#readResource(java.lang.String)
     */
    public Object readResource(String resourceName) {
        URL resource = ResourceUtil.getResource(resourceName);
        try {
            return read(resource.openStream());
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }

    /*
     * @see org.escafe.buri.oouo.reader.OouoReader#readFile(java.lang.String)
     */
    public Object readFile(String fileName) {
        File file = new File(fileName);
        InputStream is = FileInputStreamUtil.create(file);
        return read(is);
    }

    /**
     * {@link InputStream}で指定されたワークフローを読み込みをして、 ツリー構造のオブジェクト群を作成します。
     * 
     * @param input ワークフローの{@link InputStream}
     * @return {@link BuriPackageType}を頂点としたツリー構造のオブジェクト（ワークフローがXPDLの場合）
     */
    protected Object read(InputStream input) {
        XmlHandler xmlHandler = new XmlHandler();
        xmlHandler.setClassDefFactory(oouoClassDefFactory);
        xmlHandler.setContainer(container);
        SAXParserFactory spfactory = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = spfactory.newSAXParser();
            parser.parse(input, xmlHandler);
        } catch (ParserConfigurationException e) {
            throw new ParserConfigurationRuntimeException(e);
        } catch (SAXException e) {
            throw new SAXRuntimeException(e);
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }

        return xmlHandler.getRoot();
    }

    /*
     * @see org.escafe.buri.oouo.reader.OouoReader#addRootClass(java.lang.Class)
     */
    public void addRootClass(Class<?> clazz) {
        OouoClassDef classDef = oouoClassDefFactory.create(clazz);
        String rootName = classDef.getElementName();
        // String rootName =
        // classDefUtil.getMethodSignatureValue(clazz,"ROOTELEMENT",null).toString();
        rootClass.put(rootName, clazz);

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
    public OouoClassDefFactory getOouoClassDefFactory() {
        return oouoClassDefFactory;
    }

    /**
     * Oouo用のクラス定義ファクトリを登録します。
     * <p>
     * {@link S2Container}で自動的にバインドさせる為のメソッドです。
     * </p>
     * 
     * @param oouoClassDefFactory Oouo用のクラス定義ファクトリ
     */
    public void setOouoClassDefFactory(OouoClassDefFactory oouoClassDefFactory) {
        this.oouoClassDefFactory = oouoClassDefFactory;
    }

}
