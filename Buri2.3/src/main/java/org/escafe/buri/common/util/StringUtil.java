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

    public static List<String> convertCommaSplitString(String commaData) {
        return convertManySplitString(commaData, ",");
    }

    public static List<String> convertLFSplitString(String commaData) {
        return convertManySplitString(commaData, "\n");
    }

    public static List<String> convertManySplitString(String srcData, String delimiter) {
        List<String> splitData = new ArrayList<String>();
        if (org.seasar.framework.util.StringUtil.isEmpty(srcData)) {
            return splitData;
        }
        String[] datax = srcData.split(delimiter);
        for (String data : datax) {
            splitData.add(data.trim());
        }
        return splitData;
    }

    public static String[] splitFastString(String srcStr, String delimiter) {
        int splitPos = srcStr.indexOf(delimiter);
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
