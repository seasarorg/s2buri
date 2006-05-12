/*
 * çÏê¨ì˙: 2005/11/24
 *
 */
package org.seasar.buri.tablepickup.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.seasar.buri.common.util.ClassDefUtil;
import org.seasar.buri.common.util.ExcelIOUtil;
import org.seasar.buri.context.ContextUtil;
import org.seasar.buri.tablepickup.Pickup;
import org.seasar.buri.tablepickup.TablePickup;
import org.seasar.framework.container.S2Container;

public class TablePickupImpl extends HashMap implements TablePickup {

    private static final long serialVersionUID = 2514983174892683050L;
    private String defaltValColName = "Value";
    private HashMap valColNames = new HashMap();
    private S2Container container;

    public void readFile(String filename) {
        List sheets = new ArrayList();
        sheets.add(new Integer(0));
        readFile(filename, sheets);
    }

    public void readFile(String filename, List sheets) {
        Workbook book = ExcelIOUtil.getWorkbook(filename);
        Iterator ite = sheets.iterator();
        while(ite.hasNext()) {
            Object tgt = ite.next();
            Sheet sheet = ExcelIOUtil.getSheet(book,tgt);
            Pickup pickup = createTablePickup(sheet,defaltValColName);
            put(tgt,pickup);
            put(sheet.getName(),pickup);
        }
    }

    protected Pickup createTablePickup(Sheet sheet,String valColName) {
        PickupImpl pickup = new PickupImpl();
        pickup.setContextUtil((ContextUtil)container.getComponent(ContextUtil.class));
        pickup.setClassDefUtil((ClassDefUtil)container.getComponent(ClassDefUtil.class));
        pickup.setValColName(valColName);
        pickup.setSheetName(sheet.getName());
        for(int y = 1; y < sheet.getRows(); y++) {
            String yStr = sheet.getCell(0,y).getContents();
            
            for(int x = 1; x < sheet.getColumns(); x++) { 
                String xStr = sheet.getCell(x,0).getContents();
                Cell valCell = sheet.getCell(x,y);
                pickup.addPickUp(xStr,yStr,valCell);
            }
        }
        
        return pickup;
    }

    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }


}
