/*
 * çÏê¨ì˙: 2005/09/13
 *
 */
package org.seasar.buri.common.util.template.impl;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;


import org.seasar.buri.common.util.template.TextTemplate;
import org.seasar.buri.common.util.template.exception.TemplateRuntimeException;
import org.seasar.framework.exception.IORuntimeException;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TextTemplateFreeMakerImpl implements TextTemplate {
    private Configuration configuration = new Configuration();

    public String process(String templateText, Object data) {
        Template template = null;
        try {
            template = new Template("name", new StringReader(templateText),configuration);
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
        StringWriter stringWriter = new StringWriter();
        try {
            template.process(data,stringWriter);
        } catch (TemplateException e) {
            throw new TemplateRuntimeException(e);
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
        stringWriter.flush();
        String result = stringWriter.toString();
        return result;
    }

}
