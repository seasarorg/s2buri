/*
 * 作成日: 2006/06/26
 *
 */
package org.seasar.buri.engine.processor.util;

import org.seasar.buri.dto.BuriPathDataEntityDto;

public interface BuriTimerInvoker {
    void invoke(BuriPathDataEntityDto callDto);
}
