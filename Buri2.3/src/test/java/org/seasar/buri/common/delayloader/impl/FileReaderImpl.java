/*
 * 作成日: 2006/07/10
 *
 */
package org.seasar.buri.common.delayloader.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.seasar.buri.common.delayloader.FileReader;
import org.seasar.framework.util.FileUtil;

public class FileReaderImpl implements FileReader {
    private Map fileData = new HashMap();

    public void loadFromFile(String fileName, String fileKey) {
        System.out.println("now loadFromFile fileName=" + fileName + "  fileKey="+fileKey);
        File file = new File(fileName);
        byte bytes[] = FileUtil.getBytes(file);
        String str = new String(bytes);
        fileData.put(fileKey,str);
    }

    public String getFile(String fileKey) {
        System.out.println("now getFile fileKey="+fileKey);
        return fileData.get(fileKey).toString();
    }

}
