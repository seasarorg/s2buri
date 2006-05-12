/*
 * ì¬“ú: 2006/01/04
 *
 */
package org.seasar.buri.common.util;

import java.util.HashSet;
import java.util.Set;

public class IsNumberUtil {
    private static Set numberTypes;

    static {
        numberTypes = new HashSet();
        numberTypes.add(Number.class);
        numberTypes.add(Long.class);
        numberTypes.add(Long.TYPE);
        numberTypes.add(Integer.class);
        numberTypes.add(Integer.TYPE);
        numberTypes.add(Short.class);
        numberTypes.add(Short.TYPE);
        numberTypes.add(Byte.class);
        numberTypes.add(Byte.TYPE);
    }
    
    public static boolean isNumberType(Class checkClazz) {
        if(numberTypes.contains(checkClazz)) {
            return true;
        }else{
            return false;
        }
    }
    
}
