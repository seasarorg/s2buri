/*
 * çÏê¨ì˙: 2005/11/18
 *
 */
package org.seasar.buri.dao.util.impl;

import org.seasar.buri.dto.BuriBranchEntityDto;

public class NoSBuriBranchDaoUtilImpl extends BuriBranchDaoUtilImpl {
    
    protected void setupBranchID(BuriBranchEntityDto entityDto) {
        getBranchDao().insert(entityDto);
    }

}
