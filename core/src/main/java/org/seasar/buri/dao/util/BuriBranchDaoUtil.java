/*
 * çÏê¨ì˙: 2005/08/13
 *
 */
package org.seasar.buri.dao.util;

import java.util.Collection;
import java.util.List;

import org.seasar.buri.dto.BuriBranchEntityDto;
import org.seasar.buri.dto.BuriStateEntityDto;
import org.seasar.buri.engine.BuriPath;


public interface BuriBranchDaoUtil {
    BuriBranchEntityDto setupBranchID(BuriStateEntityDto stateEntityDto,BuriPath path);
    void updateBranchPath(BuriBranchEntityDto branchEntityDto,BuriPath path);
    List setupBranchID(Collection paths,BuriBranchEntityDto parentBranchEntityDto);
    void saveBranchEntityDto(BuriBranchEntityDto branchEntityDto);
    
}
