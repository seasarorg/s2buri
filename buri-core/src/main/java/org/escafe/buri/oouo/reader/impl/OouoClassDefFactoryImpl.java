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

import java.util.HashMap;
import java.util.Map;

import org.escafe.buri.oouo.reader.OouoClassDef;
import org.escafe.buri.oouo.reader.OouoClassDefFactory;
import org.seasar.framework.util.ClassUtil;

/**
 * Oouoで使用するクラス定義用クラスのファクトリの実装です。
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/03/12
 */
public class OouoClassDefFactoryImpl implements OouoClassDefFactory {
    /**
     * クラスとそれに対応する{@link OouoClassDef}を管理する{@link Map}
     */
    private Map<Class<?>, OouoClassDef> defMap = new HashMap<Class<?>, OouoClassDef>();
    /**
     * 属性名と{@link OouoClassDef}を管理する{@link Map}
     */
    private Map<String, OouoClassDef> nameClassMap = new HashMap<String, OouoClassDef>();

    /**
     * オブジェクトの初期化をします。
     */
    public void dispose() {
        nameClassMap.clear();
        defMap.clear();
    }

    /*
     * @see org.escafe.buri.oouo.reader.OouoClassDefFactory#create(java.lang.Class)
     */
    public OouoClassDef create(Class<?> clazz) {
        if (defMap.containsKey(clazz)) {
            return (OouoClassDef) defMap.get(clazz);
        }
        OouoClassDefImpl defImpl = new OouoClassDefImpl();
        defImpl.setClass(clazz);
        defMap.put(clazz, defImpl);
        nameClassMap.put(defImpl.getElementName(), defImpl);
        return defImpl;
    }

    /*
     * @see org.escafe.buri.oouo.reader.OouoClassDefFactory#getRoot(java.lang.String)
     */
    public Object getRoot(String eleName) {
        OouoClassDef defImpl = (OouoClassDef) nameClassMap.get(eleName);
        return ClassUtil.newInstance(defImpl.getTgtClass());
    }

}
