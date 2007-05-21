/*
 * 作成日: 2005/09/13
 *
 */
package org.escafe.buri.common.util.template.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;

import org.escafe.buri.common.util.template.TextTemplate;
import org.escafe.buri.common.util.template.exception.TemplateRuntimeException;
import org.seasar.framework.exception.IORuntimeException;
import org.seasar.framework.util.FileInputStreamUtil;
import org.seasar.framework.util.InputStreamReaderUtil;
import org.seasar.framework.util.ReaderUtil;
import org.seasar.framework.util.ResourceUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TextTemplateFreeMakerImpl implements TextTemplate {
    private Configuration configuration = new Configuration();
    
    public TextTemplateFreeMakerImpl() {
//        configuration.setObjectWrapper(ObjectWrapper.BEANS_WRAPPER);  
    }
    
    public String processResource(String resourceName, Object data) {
        URL resource = ResourceUtil.getResource(resourceName);
        try {
            return processInputStream(resource.openStream(), data);
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }

    public String processFile(String fileName, Object data) {
        File file = new File(fileName);
        InputStream is = FileInputStreamUtil.create(file);
        return processInputStream(is, data);
    }
    
    private String processInputStream(InputStream is, Object data) {
        InputStreamReader reader = InputStreamReaderUtil.create(is);
        String templateText = ReaderUtil.readText(reader);
        return process(templateText, data);
    }

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

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

}
