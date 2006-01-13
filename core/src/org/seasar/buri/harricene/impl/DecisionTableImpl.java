/*
 * çÏê¨ì˙: 2005/11/23
 *
 */
package org.seasar.buri.harricene.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.seasar.buri.common.util.ScriptProcessor;
import org.seasar.buri.harricene.DecisionTable;

public class DecisionTableImpl implements DecisionTable {
    private HashMap decisionData = new HashMap();
    private HashMap resultCheck = new HashMap();
    private List keySet = new ArrayList();

    public String decision(Object rootObj) {
        return decision(rootObj, null);
    }

    public String decision(Object rootObj, Map context) {
        Set baseResult = decisionMulti(rootObj, context);
        if(baseResult.size()==1){
            return (String)baseResult.toArray()[0];
        }
        return "";
    }
    
    protected Set decisionMulti(Object rootObj, Map context) {
        Set baseResult = ((HashMap)resultCheck.clone()).keySet();
        ScriptProcessor processor = new ScriptProcessor();
        if(context != null) {
            processor.putAllContext(context);
        }
        
        Iterator checkIte = keySet.iterator();
        while(checkIte.hasNext()) {
            String checkStr = (String)checkIte.next();
            HashMap valData = (HashMap)decisionData.get(checkStr);

            decisionColCheck(valData,processor,checkStr,rootObj,baseResult);
        }
        return baseResult;
    }
    
    protected void decisionColCheck(HashMap valData,ScriptProcessor processor,String checkStr,Object rootObj,Set baseResult) {
        Set valSet = valData.keySet();
        Iterator valIte = valSet.iterator();
        while(valIte.hasNext()){
            String valStr = (String)valIte.next();
            if(valStr.length() == 0) {
                continue;
            }
            Vector resultData = (Vector)valData.get(valStr);
            Object checkObj = processor.getValue(checkStr,rootObj); 
            Object valObj = processor.getValue(valStr,rootObj); 
            boolean check = checkObj.equals(valObj);
            if(check==false) {
                baseResult.removeAll(resultData);
            }
        }
    }

    
    public void addDecision(String check,String val,String result) {
        if( ! decisionData.containsKey(check) ) {
            decisionData.put(check,new HashMap());
            keySet.add(check);
        }
        HashMap valData = (HashMap)decisionData.get(check);
        if( ! valData.containsKey(val) ) {
            valData.put(val,new Vector());
        }
        Vector resultData = (Vector)valData.get(val);
        resultData.add(result);
        resultCheck.put(result,null);
    }
    
}
