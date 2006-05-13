/*
 * ì¬“ú: 2006/05/02
 *
 */
package org.seasar.buri.compiler;

import org.seasar.buri.oouo.internal.structure.BuriPackageType;
import org.seasar.buri.util.packages.BuriExePackages;

public interface CompilePackage {
    BuriExePackages compile(BuriPackageType buriPackage);
}
