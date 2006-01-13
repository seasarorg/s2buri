/*
 * ì¬“ú: 2005/11/09
 *
 */
package org.seasar.buri.xpdl.util;

import org.seasar.buri.engine.BuriPath;

public interface BuriDataMetaDataUtil {
    BuriDataMetaData getMetaData(BuriPath path, Object data);
    BuriDataMetaData getMetaDataFromTypeName(BuriPath path,String dataType);

}
