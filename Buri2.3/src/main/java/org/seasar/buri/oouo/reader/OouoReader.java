/*
 * 作成日: 2006/03/06
 *
 */
package org.seasar.buri.oouo.reader;

import java.io.InputStream;

public interface OouoReader {
    Object readInputStream(InputStream workFlowIs);
    Object readResource(String resourceName);
    Object readFile(String fileName);
    
    void addRootClass(Class clazz);
}
