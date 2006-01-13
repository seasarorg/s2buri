/*
 * ì¬“ú: 2005/05/15
 *
 */
package org.seasar.buri.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author makotan
 *
 */
public class ArrayUtil {
    public static List arrayToList(Object[] datas) {
        ArrayList list = new ArrayList();
        return arrayAppendList(datas,list);
    }
    
    public static List arrayAppendList(Object[] datas,List toList) {
        if(isEmpty(datas)) {
            return toList;
        }
        for(int i = 0 ; i < datas.length ; i++ ) {
            toList.add(datas[i]);
        }
        return toList;
    }
    public static Object getArrayTopOne(Object[] datas) {
        if(isEmpty(datas)) {
            return null;
        }
        return datas[0];
    }
    public static boolean isEmpty(Object[] datas) {
        if(datas==null || datas.length == 0) {
            return true;
        }
        return false;
    }
    public static boolean hasData(Object[] datas) {
        return (! isEmpty(datas));
    }
}
