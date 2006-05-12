/*
 * ì¬“ú: 2005/10/02
 *
 */
package org.seasar.buri.xpdl.util;


import org.seasar.buri.engine.BuriPath;
import org.wfmc.x2002.xpdl10.DataFieldDocument.DataField;

public interface DataFieldUtil {
    DataField getDataField(BuriPath buriPath,String className);
}
