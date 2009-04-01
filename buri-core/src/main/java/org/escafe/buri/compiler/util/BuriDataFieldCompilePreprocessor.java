/*
 * 作成日: 2006/07/04
 *
 */
package org.escafe.buri.compiler.util;

import org.escafe.buri.oouo.internal.structure.BuriDataFieldType;

public interface BuriDataFieldCompilePreprocessor {
    BuriDataFieldType preprocess(BuriDataFieldType src);
}
