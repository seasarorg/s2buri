/*
 * çÏê¨ì˙: 2005/11/18
 *
 */
package org.seasar.buri.dao.util.impl;

import org.seasar.buri.dto.BuriStateEntityDto;

public class NoSBuriStateUtilImpl extends BuriStateUtilImpl {
    
    protected void createNewBuriStateID(BuriStateEntityDto startEntityDto) {
        getStateDao().insert(startEntityDto);
    }
    protected void insertStateDto(BuriStateEntityDto entityDto) {
    }

}
