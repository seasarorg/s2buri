/*
 * ì¬“ú: 2006/03/21
 *
 */
package org.seasar.buri.util.packages;

import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.oouo.internal.structure.BuriPackageType;

public interface BuriExePackages {
    void setup(BuriPackageType buriPackage);
    BuriExecProcess getProcess(BuriPath path);
}
