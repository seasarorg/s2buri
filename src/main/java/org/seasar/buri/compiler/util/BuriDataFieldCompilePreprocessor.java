/*
 * ì¬“ú: 2006/07/04
 *
 */
package org.seasar.buri.compiler.util;

import org.seasar.buri.oouo.internal.structure.BuriDataFieldType;

public interface BuriDataFieldCompilePreprocessor {
    BuriDataFieldType preprocess(BuriDataFieldType src);
}
