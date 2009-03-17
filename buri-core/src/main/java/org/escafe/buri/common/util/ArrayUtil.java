/*
 * 作成日: 2005/05/15
 *
 */
package org.escafe.buri.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * @author makotan
 *
 */
public class ArrayUtil {

    public static List arrayToList(Object[] datas) {
        ArrayList list = new ArrayList();
        return arrayAppendList(datas, list);
    }

    public static List arrayAppendList(Object[] datas, List toList) {
        if (isEmpty(datas)) {
            return toList;
        }
        for (Object data : datas) {
            toList.add(data);
        }
        return toList;
    }

    public static Object getArrayTopOne(Object[] datas) {
        if (isEmpty(datas)) {
            return null;
        }
        return datas[0];
    }

    public static boolean isEmpty(Object[] datas) {
        if ((datas == null) || (datas.length == 0)) {
            return true;
        }
        return false;
    }

    public static boolean hasData(Object[] datas) {
        return (!isEmpty(datas));
    }

    public static Object procOgnl(Collection datas, Object arg, String proc, String result, String init) {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return procOgnl(datas, arg, proc, result, init, container);
    }

    public static Object procOgnl(Collection datas, Object arg, String proc, String result, String init, S2Container container) {
        ScriptProcessor processor = new ScriptProcessor();
        processor.putInContext("datas", datas);
        processor.putInContext("arg", arg);
        processor.getValue(init, container);
        Iterator ite = datas.iterator();
        while (ite.hasNext()) {
            Object data = ite.next();
            processor.putInContext("data", data);
            processor.getValue(proc, container);
        }
        Object resultObj = processor.getValue(result, container);
        return resultObj;
    }

    public static Collection procOgnl(Collection datas, Object arg, String proc) {
        String result = "#datas";
        return (Collection) procOgnl(datas, arg, proc, result);
    }

    public static Collection procOgnl(Collection datas, String proc) {
        String result = "#datas";
        return (Collection) procOgnl(datas, null, proc, result);
    }

    public static Object procOgnl(Collection datas, String proc, String result) {
        return procOgnl(datas, null, proc, result);
    }

    public static Object procOgnl(Collection datas, Object arg, String proc, String result) {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        Object resultObj = procOgnl(datas, arg, proc, result, "", container);
        return resultObj;
    }

    public static Object procOgnl(Object[] datas, Object arg, String proc, String result, String init) {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return procOgnl(datas, arg, proc, result, init, container);
    }

    public static Object procOgnl(Object[] datas, Object arg, String proc, String result, String init, S2Container container) {
        ScriptProcessor processor = new ScriptProcessor();
        processor.putInContext("datas", datas);
        processor.putInContext("arg", arg);
        processor.getValue(init, container);
        for (Object data : datas) {
            processor.putInContext("data", data);
            processor.getValue(proc, container);
        }
        Object resultObj = processor.getValue(result, container);
        return resultObj;
    }

    public static Object[] procOgnl(Object[] datas, Object arg, String proc) {
        String result = "#datas";
        return (Object[]) procOgnl(datas, arg, proc, result);
    }

    public static Object[] procOgnl(Object[] datas, String proc) {
        String result = "#datas";
        return (Object[]) procOgnl(datas, null, proc, result);
    }

    public static Object procOgnl(Object[] datas, String proc, String result) {
        return procOgnl(datas, null, proc, result);
    }

    public static Object procOgnl(Object[] datas, Object arg, String proc, String result) {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        Object resultObj = procOgnl(datas, arg, proc, result, "", container);
        return resultObj;
    }

}
