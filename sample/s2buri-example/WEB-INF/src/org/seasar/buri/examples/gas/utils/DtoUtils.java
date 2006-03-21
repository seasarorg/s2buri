package org.seasar.buri.examples.gas.utils;

import org.apache.commons.beanutils.BeanUtils;

public final class DtoUtils {

    public static void copy(Object src, Object dest) {
        try {
            BeanUtils.copyProperties(dest, src);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
