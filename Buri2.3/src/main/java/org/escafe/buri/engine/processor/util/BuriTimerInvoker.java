/*
 * 作成日: 2006/06/26
 *
 */
package org.escafe.buri.engine.processor.util;

import org.escafe.buri.dto.BuriPathDataEntityDto;

public interface BuriTimerInvoker {
    void invoke(BuriPathDataEntityDto callDto);
}
