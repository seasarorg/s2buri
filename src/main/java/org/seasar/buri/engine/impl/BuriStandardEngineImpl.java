/*
 * çÏê¨ì˙: 2006/06/06
 *
 */
package org.seasar.buri.engine.impl;

import org.seasar.buri.dao.util.BuriUserUtil;
import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.util.packages.BuriExePackages;
import org.seasar.buri.util.packages.BuriExecProcess;

public class BuriStandardEngineImpl extends BuriSimpleEngineImpl {
    private BuriUserUtil userUtil;
    
    protected void updateUserInfo(BuriSystemContext sysContext,BuriExecProcess wp,BuriExePackages wPackageObj) {
        super.updateUserInfo(sysContext,wp,wPackageObj);
        if(sysContext.getUserPkeyNum() == null && sysContext.getUserPkeyVal() == null) {
            return;
        }
        long userID = userUtil.convertUserID(sysContext.getUserPkeyNum(),sysContext.getUserPkeyVal());
        sysContext.setUserID(new Long(userID));
    }
    
    public BuriUserUtil getUserUtil() {
        return userUtil;
    }
    public void setUserUtil(BuriUserUtil userUtil) {
        this.userUtil = userUtil;
    }

}
