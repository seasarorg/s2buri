/*
 * 作成日: 2005/09/13
 *
 */
package org.seasar.buri.common.util.template;

public interface TextTemplate {
    String process(String templateName,Object data);
    String processResource(String templateName,Object data);
    String processFile(String templateName,Object data);
}
