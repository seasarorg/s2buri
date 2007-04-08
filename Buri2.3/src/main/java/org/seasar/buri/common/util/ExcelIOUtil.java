/*
 * 作成日: 2005/11/23
 *
 */
package org.seasar.buri.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.seasar.framework.exception.IORuntimeException;
import org.seasar.framework.log.Logger;
import org.seasar.framework.util.FileInputStreamUtil;
import org.seasar.framework.util.ResourceUtil;

public class ExcelIOUtil {
    private static Logger logger = Logger.getLogger(ExcelIOUtil.class);

    public static Workbook getWorkbookNoResource(String fileName) {
        File file = new File(fileName);
        InputStream is = FileInputStreamUtil.create(file);
        Workbook workbook = getWorkbook(is);
        return workbook;
    }

    public static Workbook getWorkbook(String fileName) {
        URL resource = ResourceUtil.getResource(fileName);
        Workbook workbook = null;
        try {
            workbook = getWorkbook(resource.openStream());
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
        return workbook;
    }

    private static Workbook getWorkbook(InputStream is) {
        Workbook workbook = null;
        try {
            workbook = Workbook.getWorkbook(is);
        } catch (BiffException e) {
            logger.error(e);
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
        return workbook;
    }
    
    public static Sheet getSheet(Workbook workbook,Object sheetInfo) {
        if(sheetInfo instanceof Number) {
            return getSheet(workbook,((Number)sheetInfo).intValue());
        } else {
            return getSheet(workbook,sheetInfo.toString());
        }
    }
    
    public static Sheet getSheet(Workbook workbook,int sheetNo) {
        return workbook.getSheet(sheetNo);
    }
    
    public static Sheet getSheet(Workbook workbook,String sheetName) {
        return workbook.getSheet(sheetName);
    }
    
}
