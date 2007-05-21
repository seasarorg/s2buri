/*
 * 作成日: 2005/05/15
 *
 */
package org.escafe.buri.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author makotan
 *
 */
public class StringUtil {
    public static List convertKanmaSplitString(String kanmaData) {
        return convertManySplitString(kanmaData, ",");
    }

    public static List convertLFSplitString(String kanmaData) {
        return convertManySplitString(kanmaData, "\n");
    }

    public static List convertManySplitString(String srcData, String kugiri) {
        ArrayList splitData = new ArrayList();
        if (org.seasar.framework.util.StringUtil.isEmpty(srcData)) {
            return splitData;
        }
        String[] datax = srcData.split(kugiri);
        for (String element : datax) {
            splitData.add(element.trim());
        }
        return splitData;
    }

    public static String[] SplitFastString(String srcStr, String kugiri) {
        int splitPos = srcStr.indexOf(kugiri);
        String result[];
        if (splitPos == -1) {
            result = new String[] { srcStr };
        } else {
            String key = srcStr.substring(0, splitPos);
            String val = srcStr.substring(splitPos + 1);
            result = new String[] { key, val };
        }
        return result;
    }
}
