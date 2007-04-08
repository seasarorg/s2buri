/*
 * 作成日: 2006/03/12
 *
 */
package org.seasar.buri.oouo.reader;

public interface OouoClassDefFactory {

    OouoClassDef create(Class clazz);
    Object getRoot(String eleName);
    

}
