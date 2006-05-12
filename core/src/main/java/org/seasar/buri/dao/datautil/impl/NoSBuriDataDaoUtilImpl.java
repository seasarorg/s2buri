/*
 * çÏê¨ì˙: 2005/11/18
 *
 */
package org.seasar.buri.dao.datautil.impl;

import org.seasar.buri.dto.BuriDataEntityDto;

public class NoSBuriDataDaoUtilImpl extends BuriDataDaoUtilImpl {
    protected long getNewBuriDataID(BuriDataEntityDto dataEntityDto) {
        getBuriDataDao().insert(dataEntityDto);
        return dataEntityDto.getDataID();
    }

    protected void storeBuriData(BuriDataEntityDto entityDto) {
        getBuriDataDao().update(entityDto);
    }

}
