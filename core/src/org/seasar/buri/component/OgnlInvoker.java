/*
 * ì¬“ú: 2005/07/01
 *
 */
package org.seasar.buri.component;

import java.util.Map;

import org.seasar.buri.common.util.ScriptProcessor;

public interface OgnlInvoker extends BuriComponent3{
    Object processOgnls(String ognls,ScriptProcessor processor,Map contextData);
}
