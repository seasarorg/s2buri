/*
 * çÏê¨ì˙: 2005/11/24
 *
 */
package org.seasar.buri.tablepickup.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import jxl.BooleanCell;
import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.LabelCell;

import org.seasar.buri.common.util.ClassDefUtil;
import org.seasar.buri.common.util.ScriptProcessor;
import org.seasar.buri.context.ContextUtil;
import org.seasar.buri.tablepickup.Pickup;
import org.seasar.framework.log.Logger;

public class PickupImpl implements Pickup {
    private Logger logger = Logger.getLogger(PickupImpl.class);
    private HashMap table = new HashMap();
    private HashMap cacheTable = null;
    private HashMap valueData = new HashMap();
    private String valColName = "Value";
    private String sheetName;
    private HashMap expression = new HashMap();
    private ContextUtil contextUtil;
    private ClassDefUtil classDefUtil;
   
    private Object parseExpression(String strExpression,ScriptProcessor processor) {
        Object ret;
        ret = expression.get(strExpression);
        if(ret == null) {
            ret = processor.parseExpression(strExpression); 
            expression.put(strExpression,ret);
        }
        return ret;
    }

    public void addPickUp(String x,String y,Cell cell) {
        if( !table.containsKey(x) ) {
            table.put(x,new HashMap());
        }
        HashMap tableY = (HashMap)table.get(x);
        tableY.put(y,cell);
        
        if( !valueData.containsKey(x) ) {
            valueData.put(x,new Vector());
        }
        Vector cellData = (Vector)valueData.get(x);
        cellData.add(cell);
    }
    
    public Collection getValues(String title) {
        ScriptProcessor processor = new ScriptProcessor();
        Vector result = new Vector();
        Vector cellData = (Vector)valueData.get(title);
        
        Iterator ite = cellData.iterator();
        while(ite.hasNext()) {
            Cell cell = (Cell)ite.next();
            Object obj = cellToObject(cell,processor);
            result.add(obj);
        }
        
        return result;
    }
    
    public void setValColName(String valColName) {
        this.valColName = valColName;
    }
    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }
    
    /* (îÒ Javadoc)
     * @see jp.starlogic.makotan.buridicon.tablepickup.TablePickup#pickup(java.util.Map)
     */
    public Object pickup(Object data) {
        ScriptProcessor processor = getScriptProcessor();
        String xKey = null;
        String yKey = null;
        Object result = null;

        xKey = getNextKey(processor,data,table);
        if(xKey == null) {
            logger.error(sheetName + " Col pickup Error");
            return null;
        }
        HashMap tableY = (HashMap)table.get(xKey);

        yKey = getNextKey(processor,data,tableY);
        if(yKey == null) {
            logger.error(sheetName + " Row pickup Error");
            return null;
        }
        Cell cell = (Cell)tableY.get(yKey);
        result = cellToObject(cell,processor);

        return result;
    }
    
    private synchronized void createPickupCacheTable(ScriptProcessor processor) {
        if(cacheTable!=null){ return; }
        cacheTable = createValTable(processor,table);
    }
    
    private HashMap createValTable(ScriptProcessor processor,HashMap table) {
        HashMap valTable = new HashMap();
        Iterator xIte = table.keySet().iterator();
        while(xIte.hasNext()) {
            String keyTmp = (String)xIte.next();
            Object obj = processor.getValue(keyTmp,null);
            Object childObj = table.get(keyTmp);
            if(classDefUtil.getClazz(childObj).equals(HashMap.class)){
                childObj = createValTable(processor,(HashMap)childObj);
            }
            valTable.put(obj,childObj);
        }
        
        return valTable;
    }


    /* (îÒ Javadoc)
     * @see jp.starlogic.makotan.buridicon.tablepickup.TablePickup#pickupLite(java.lang.Object, java.lang.Object)
     */
    public Object pickupLite(Object yObject, Object xObject) {
        ScriptProcessor processor = new ScriptProcessor();
        Object result = null;

        createPickupCacheTable(processor);

        HashMap tableY = (HashMap)cacheTable.get(xObject);
        if(tableY==null){
            logger.error(sheetName + " Col pickupLite Error");
            return null;
        }
        Cell cell = (Cell)tableY.get(yObject);
        if(cell == null) {
            logger.error(sheetName + " Row pickupLite Error");
            return null;
        }
        result = cellToObject(cell,processor);
        return result;
        
    }

    public Object lookup(Object data,String valColName) {
        ScriptProcessor processor = getScriptProcessor();
        String yKey = null;
        Object result = null;

        HashMap tableY = (HashMap)table.get(valColName);

        yKey = getNextKey(processor,data,tableY);
        if(yKey == null) {
            logger.error(sheetName + " Row lookup Error");
            return null;
        }
        Cell cell = (Cell)tableY.get(yKey);
        result = cellToObject(cell,processor);

        return result;
    }

    /* (îÒ Javadoc)
     * @see jp.starlogic.makotan.buridicon.tablepickup.TablePickup#lookup(java.util.Map)
     */
    public Object lookup(Object data) {
        return lookup(data,valColName);
    }

    private synchronized void createLookupCacheTable(ScriptProcessor processor) {
        if(cacheTable!=null){ return; }

        HashMap valTable = new HashMap();
        Iterator xIte = table.keySet().iterator();
        while(xIte.hasNext()) {
            String keyTmp = (String)xIte.next();
            Object childObj = table.get(keyTmp);
            if(childObj.getClass().equals(HashMap.class)){
                childObj = createValTable(processor,(HashMap)childObj);
            }
            valTable.put(keyTmp,childObj);
        }
        
        cacheTable = valTable;
    }

    public Object lookupLite(Object obj,String valColName) {
        ScriptProcessor processor = new ScriptProcessor();
        Object result = null;
        createLookupCacheTable(processor);

        HashMap tableY = (HashMap)cacheTable.get(valColName);
        Cell cell = (Cell)tableY.get(obj);
        if(cell == null) {
            logger.error(sheetName + " Row lookupLite Error");
            return null;
        }
        result = cellToObject(cell,processor);
        return result;
    }
    /* (îÒ Javadoc)
     * @see jp.starlogic.makotan.buridicon.tablepickup.TablePickup#lookupLite(java.lang.Object)
     */
    public Object lookupLite(Object obj) {
        return lookupLite(obj,valColName);
    }

    
    protected Object cellToObject(Cell cell,ScriptProcessor processor) {
        if(cell.getType() == CellType.LABEL){
            LabelCell labelCell = (LabelCell)cell;
            return labelCell.getString();
        }else if(cell.getType() == CellType.BOOLEAN){
            BooleanCell boolCell = (BooleanCell)cell;
            return Boolean.valueOf(boolCell.getValue());
        }else if(cell.getType() == CellType.DATE){
            DateCell dateCell = (DateCell)cell;
            return dateCell.getDate();
        }else if(cell.getType() == CellType.NUMBER){
            Object expression = parseExpression(cell.getContents(),processor);
            return processor.getValueExpression(expression,null);
        }
        
        return null;
    }
    
    protected ScriptProcessor getScriptProcessor() {
        ScriptProcessor processor = new ScriptProcessor();
        processor.putAllContext(contextUtil.getContext());
        return processor;
    }
    
    protected String getNextKeyLite(ScriptProcessor processor,Object cmpObject,HashMap table) {
        String retKey = null;
        Iterator xIte = table.keySet().iterator();
        while(xIte.hasNext()) {
            String keyTmp = (String)xIte.next();
            Object expression = parseExpression(keyTmp,processor);
            Object obj = processor.getValueExpression(expression,null);
            if(obj.equals(cmpObject)) {
                retKey = keyTmp;
                break;
            }
        }
        return retKey;
    }
    
    protected String getNextKey(ScriptProcessor processor,Object rootObject,HashMap table) {
        String retKey = null;
        Iterator xIte = table.keySet().iterator();
        while(xIte.hasNext()) {
            String keyTmp = (String)xIte.next();
            Object expression = parseExpression(keyTmp,processor);
            Boolean hantei = (Boolean)processor.getValueExpression(expression,rootObject);
            if(hantei.booleanValue()) {
                retKey = keyTmp;
                break;
            }
        }
        return retKey;
        
    }

    public ContextUtil getContextUtil() {
        return contextUtil;
    }

    public void setContextUtil(ContextUtil contextUtil) {
        this.contextUtil = contextUtil;
    }

    public ClassDefUtil getClassDefUtil() {
        return classDefUtil;
    }

    public void setClassDefUtil(ClassDefUtil classDefUtil) {
        this.classDefUtil = classDefUtil;
    }

}
