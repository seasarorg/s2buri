/*
 * 作成日: 2006/07/13
 *
 */
package org.escafe.buri.common.participantprovider;

import java.util.List;

import org.escafe.buri.common.participantprovider.impl.BuriExcelPrtiPrvidrRootDto;

public interface ExcelPrtiPrvidrParser {

    List<BuriExcelPrtiPrvidrRootDto> loadFromResource(String fileName);

    List<BuriExcelPrtiPrvidrRootDto> loadFromFile(String fileName);

}
