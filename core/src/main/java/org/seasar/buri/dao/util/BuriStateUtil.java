/*
 * çÏê¨ì˙: 2005/07/06
 *
 */
package org.seasar.buri.dao.util;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.seasar.buri.dto.BuriStateEntityDto;
import org.seasar.buri.engine.BuriPath;


public interface BuriStateUtil {
    
    BuriStateEntityDto readOnlyStateDto(BuriPath path,long branchID);
    BuriStateEntityDto readStateDto(BuriPath path,long branchID);
    void updateStateDto(BuriStateEntityDto start,Collection savePaths,long branchID);
    BuriStateEntityDto createNewStateDto(BuriPath path,long branchID,Date limitDate);
    
    List getStatusPath(BuriPath tgtPath);
    
    void processedJoinXor(BuriPath path);
    boolean canAndJoin(BuriPath path);
    
    void stopBuriState(BuriStateEntityDto stateEntityDto);
    
}
