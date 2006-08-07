/*
 * ì¬“ú: 2006/04/02
 *
 */
package org.seasar.buri.path;

import java.util.List;

import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.oouo.internal.structure.BuriPackageType;

public interface BuriPathSelectUtil {
    List selectBuriPath(BuriPackageType packagesType,BuriPath path,BuriSystemContext sysContext);
}
