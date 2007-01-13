/*
 * 作成日: 2006/07/13
 *
 */
package org.seasar.buri.common.participantprovider;

import java.util.List;

public interface ExcelPrtiPrvidrParser {
    List loadFromResource(String fileName);
    List loadFromFile(String fileName);
    
}
