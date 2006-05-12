/*
 * ì¬“ú: 2005/11/23
 *
 */
package org.seasar.buri.harricene;

import java.util.Map;


public interface DecisionTable {
    void addDecision(String check,String val,String result);
    String decision(Object rootObj);
    String decision(Object rootObj,Map context);
}
