/*
 * 作成日: 2006/07/13
 *
 */
package org.seasar.buri.common.participantprovider;

import java.util.List;

import org.seasar.buri.common.participantprovider.impl.BuriExcelPrtiPrvidrRootDto;

public interface ExcelPrtiPrvidrParser {

    List<BuriExcelPrtiPrvidrRootDto> loadFromResource(String fileName);

    List<BuriExcelPrtiPrvidrRootDto> loadFromFile(String fileName);

}
