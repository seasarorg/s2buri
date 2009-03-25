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
package org.escafe.buri.oouo.internal.structure.util;

import java.util.List;

import org.escafe.buri.oouo.internal.structure.BuriExtendedAttributeType;

/**
 * XPDLフォーマットの{@code ExtentedAttribute}ノードの為のユーティリティです。
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/03/26
 */
public class ExtentedAttributeUtil {

    /**
     * 指定された要素のリストから、{@code key}として指定した名前を持つ要素を探し出し、
     * そこに設定されている値を取得して返します。
     * <p>
     * 要素が見つからなかった場合、{@code null}を返します。
     * </p>
     * 
     * @param attriList {@link BuriExtendedAttributeType}のリスト
     * @param key 探したい{@code key}値
     * @return 対応する{@link BuriExtendedAttributeType}の値
     */
    public static String getAttributeVal(List<BuriExtendedAttributeType> attriList, String key) {
        BuriExtendedAttributeType attri = getExtendedAttribute(attriList, key);
        if (attri != null) {
            return attri.getValue();
        }
        return null;
    }

    /**
     * 指定された要素のリストから、{@code key}として指定した名前を持つ要素を探し出して返します。
     * <p>
     * 要素が見つからなかった場合、{@code null}を返します。
     * </p>
     * 
     * @param attriList {@link BuriExtendedAttributeType}のリスト
     * @param key 探したい{@code key}値
     * @return 対応する{@link BuriExtendedAttributeType}
     */
    public static BuriExtendedAttributeType getExtendedAttribute(List<BuriExtendedAttributeType> attriList, String key) {
        for (BuriExtendedAttributeType attri : attriList) {
            if (attri.getName().compareToIgnoreCase(key) == 0) {
                return attri;
            }
        }
        return null;
    }
}
