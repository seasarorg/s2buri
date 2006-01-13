/*
 * çÏê¨ì˙: 2005/11/23
 *
 */
package org.seasar.buri.common.util;

import java.io.IOException;
import java.net.URL;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.seasar.framework.log.Logger;
import org.seasar.framework.util.ResourceUtil;

public class ExcelIOUtil {
    private static Logger logger = Logger.getLogger(ExcelIOUtil.class);

    public static Workbook getWorkbook(String fileName) {
        URL resource = ResourceUtil.getResource(fileName);
        Workbook workbook = null;
        try {
            workbook = Workbook.getWorkbook(resource.openStream());
        } catch (BiffException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
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
