/*
 * ì¬“ú: 2005/06/09
 *
 */
package org.seasar.buri.rule;

import java.util.Map;



/**
 * @author makotan
 *
 */
public interface ConditionProcessingRule {
    boolean  judgesCondition(String condition,Map context);
}
