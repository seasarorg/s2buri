/*
 * çÏê¨ì˙: 2005/07/01
 *
 */
package org.seasar.buri.dao.datautil;

import org.seasar.buri.dto.BuriDataEntityDto;
import org.seasar.buri.engine.BuriPath;


public interface BuriDataDaoUtil {
    BuriDataEntityDto getBuriDataEntityDto(BuriPath path);
    
    long readBuriDataID(BuriPath path);
    long readOnlyBuriDataID(BuriPath path,Object data);
    long readOnlyBuriDataID(BuriPath path);
    Object getDtoFromDataKey(BuriPath path);
    
    long createOnlyBuriDataID(BuriPath path);
    void storeBuriData(BuriPath path);
    
    void setupBuriParticipant(BuriPath path);
}
