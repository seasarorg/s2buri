/*
 * 作成日: 2006/07/10
 *
 */
package org.escafe.buri.common.delayloader;

public interface FileReader {
    void loadFromFile(String fileName,String fileKey);
    String getFile(String fileKey);
}
