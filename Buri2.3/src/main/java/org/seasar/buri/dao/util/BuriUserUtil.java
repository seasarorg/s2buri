/*
 * 作成日: 2006/06/09
 *
 */
package org.seasar.buri.dao.util;

import java.util.List;

import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.util.packages.BranchWalker;
import org.seasar.coffee.dataaccess.DataAccessFactory;

public interface BuriUserUtil {
    List getTgtRoleInfoList(DataAccessFactory factory,BuriSystemContext sysContext,BranchWalker walker);
    long convertUserID(Long userIDNum,String userIDVal);
    List convUserIDListFromRoleInfos(List roleInfos);
    Object getUserData(DataAccessFactory factory,long userID,Long userIDNum,String userIDStr);
    
}
