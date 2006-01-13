/*
 * çÏê¨ì˙: 2005/11/23
 *
 */
package org.seasar.buri.harricene.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.seasar.buri.common.util.ExcelIOUtil;
import org.seasar.buri.harricene.DecisionTable;
import org.seasar.buri.harricene.Harricene;

public class HarriceneImpl extends HashMap implements Harricene {
    private static final long serialVersionUID = -7880764545835947975L;

    public void readFile(String filename) {
        List sheetNo = new ArrayList();
        sheetNo.add(new Integer(0));
        readFile(filename,sheetNo);
    }

    public void readFile(String filename, List sheets) {
        Workbook hari = ExcelIOUtil.getWorkbook(filename);
        Iterator ite = sheets.iterator();
        while(ite.hasNext()) {
            Object sheet = ite.next();
            Sheet tgt = ExcelIOUtil.getSheet(hari,sheet);
            DecisionTable dt = getDecisionTable(tgt);
            put(sheet.toString(),dt);
            put(tgt.getName(),dt);
        }
    }

    public DecisionTable getDecisionTable(Sheet decisionSheet) {
        DecisionTable decision = new DecisionTableImpl();
        String check,val,result;
        for(int row=1;row<decisionSheet.getRows();row++) {
            Cell resultCell = decisionSheet.getCell(0,row);
            result = resultCell.getContents();
            if(result.length()!=0){
                for(int col=1;col<decisionSheet.getColumns();col++) {
                    Cell checkCell = decisionSheet.getCell(col,0);
                    check = checkCell.getContents();
                    Cell valCell = decisionSheet.getCell(col,row);
                    val = valCell.getContents();
                    if(check.length()!=0&&val.length()!=0) {
                        decision.addDecision(check,val,result);
                    }
                }
            }
        }
        
        return decision;
    }
    

    
}
