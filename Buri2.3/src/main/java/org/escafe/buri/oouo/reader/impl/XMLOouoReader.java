/*
 * 作成日: 2006/03/09
 *
 */
package org.escafe.buri.oouo.reader.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.escafe.buri.oouo.reader.OouoClassDef;
import org.escafe.buri.oouo.reader.OouoClassDefFactory;
import org.escafe.buri.oouo.reader.OouoReader;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.exception.IORuntimeException;
import org.seasar.framework.exception.ParserConfigurationRuntimeException;
import org.seasar.framework.exception.SAXRuntimeException;
import org.seasar.framework.util.FileInputStreamUtil;
import org.seasar.framework.util.ResourceUtil;
import org.xml.sax.SAXException;

public class XMLOouoReader implements OouoReader {
    private Map rootClass = new HashMap();
    private S2Container container;
    private OouoClassDefFactory oouoClassDefFactory;
    
    public void dispose() {
    	rootClass.clear();
    }
    
    public Object readInputStream(InputStream workFlowIs) {
    	return read(workFlowIs);
    }
    
    public Object readResource(String resourceName) {
        URL resource = ResourceUtil.getResource(resourceName);
        try {
            return read(resource.openStream());
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }

    public Object readFile(String fileName) {
        File file = new File(fileName);
        InputStream is = FileInputStreamUtil.create(file);
        return read(is);
    }
    
    protected Object read(InputStream input) {
        XmlHandler xmlHandler = new XmlHandler();
        xmlHandler.setClassDefFactory(oouoClassDefFactory);
        xmlHandler.setContainer(container);
        SAXParserFactory spfactory = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = spfactory.newSAXParser();
            parser.parse(input,xmlHandler);
        } catch (ParserConfigurationException e) {
            throw new ParserConfigurationRuntimeException(e);
        } catch (SAXException e) {
            throw new SAXRuntimeException(e);
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
        
        return xmlHandler.getRoot();
    }

    public void addRootClass(Class clazz) {
        OouoClassDef classDef = oouoClassDefFactory.create(clazz);
        String rootName = classDef.getElementName();
//        String rootName = classDefUtil.getMethodSignatureValue(clazz,"ROOTELEMENT",null).toString();
        rootClass.put(rootName,clazz);
        
    }

    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }

    public OouoClassDefFactory getOouoClassDefFactory() {
        return oouoClassDefFactory;
    }

    public void setOouoClassDefFactory(OouoClassDefFactory oouoClassDefFactory) {
        this.oouoClassDefFactory = oouoClassDefFactory;
    }

}
