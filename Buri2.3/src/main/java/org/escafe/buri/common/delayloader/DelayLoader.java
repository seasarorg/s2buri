/*
 * 作成日: 2006/07/10
 *
 */
package org.escafe.buri.common.delayloader;

public interface DelayLoader {
    void addResourceLoader(Object obj, DelayLoaderInfo loaderInfo) throws Throwable;

    void addFileLoader(Object obj, DelayLoaderInfo loaderInfo) throws Throwable;

    void read(Object obj, String key) throws Throwable;
}
