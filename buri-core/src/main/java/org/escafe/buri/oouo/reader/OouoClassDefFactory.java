/*
 * 作成日: 2006/03/12
 *
 */
package org.escafe.buri.oouo.reader;

public interface OouoClassDefFactory {

    OouoClassDef create(Class clazz);

    Object getRoot(String eleName);

}
