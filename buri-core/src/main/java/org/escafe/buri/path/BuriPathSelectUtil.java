/*
 * 作成日: 2006/04/02
 *
 */
package org.escafe.buri.path;

import java.util.List;

import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.oouo.internal.structure.BuriPackageType;

public interface BuriPathSelectUtil {
    List selectBuriPath(BuriPackageType packagesType, BuriPath path, BuriSystemContext sysContext);
}
