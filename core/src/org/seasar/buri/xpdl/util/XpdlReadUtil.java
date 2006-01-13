/*
 * çÏê¨ì˙: 2005/05/15
 *
 */
package org.seasar.buri.xpdl.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlRuntimeException;
import org.seasar.framework.exception.IORuntimeException;
import org.seasar.framework.util.ResourceUtil;
import org.wfmc.x2002.xpdl10.PackageDocument;

/**
 * @author makotan
 *
 */
public class XpdlReadUtil {
    public static PackageDocument getFileNameToPackageDocument(String fileName) {
        InputStream xpdlStream = ResourceUtil.getResourceAsStream(fileName);
        return getFileToPackageDocument(xpdlStream);
    }
    
    public static PackageDocument getFileToPackageDocument(InputStream xpdlStream) {
        PackageDocument packageDoc = null;
        try {
            packageDoc = PackageDocument.Factory.parse(xpdlStream);
        } catch (XmlException e) {
            throw new XmlRuntimeException(e);
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
        return packageDoc;
    }

}
