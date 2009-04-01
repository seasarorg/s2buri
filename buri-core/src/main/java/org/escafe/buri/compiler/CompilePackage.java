/*
 * 作成日: 2006/05/02
 *
 */
package org.escafe.buri.compiler;

import org.escafe.buri.oouo.internal.structure.BuriPackageType;
import org.escafe.buri.util.packages.BuriExePackages;

public interface CompilePackage {
    BuriExePackages compile(BuriPackageType buriPackage);
}
