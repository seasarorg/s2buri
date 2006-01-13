/*
 * çÏê¨ì˙: 2005/10/02
 *
 */
package org.seasar.buri.xpdl.util;


import org.seasar.buri.engine.BuriPath;
import org.wfmc.x2002.xpdl10.ApplicationDocument.Application;

public interface ApplicationUutil {
    Application[] getApplicationArray(BuriPath buriPath,String name);
    Application getApplication(BuriPath buriPath,String name);
}
