/*
 * 作成日: 2006/06/06
 *
 */
package org.seasar.buri.engine.impl;

import org.seasar.buri.compiler.BuriCompiler;
import org.seasar.buri.dao.util.BuriUserUtil;
import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.engine.IdentityInfo;
import org.seasar.buri.engine.ParticipantProvider;
import org.seasar.buri.util.packages.BuriExePackages;
import org.seasar.buri.util.packages.BuriExecProcess;

public class BuriStandardEngineImpl extends BuriSimpleEngineImpl {

    private BuriUserUtil userUtil;

    @Override
    public void readWorkFlowFromResource(String workFlowName, String resourceName) {
    }

    @Override
    public void readWorkFlowFromResource(String workFlowName, String resourceName,
            ParticipantProvider provider) {
        readFromResource(workFlowName, resourceName, provider);
    }

    @Override
    public void setupUserID(BuriSystemContext sysContext) {
        BuriExePackages wPackageObj = (BuriExePackages) selectPackage(sysContext);
        BuriExecProcess wp = selectProcessNoDataAccess(wPackageObj, sysContext);
        updateUserInfo(sysContext, wp, wPackageObj);
    }

    @Override
    protected void updateUserInfo(BuriSystemContext sysContext, BuriExecProcess wp,
            BuriExePackages wPackageObj) {
        super.updateUserInfo(sysContext, wp, wPackageObj);
        IdentityInfo appUserId = sysContext.getAppUserId();
        if (appUserId.getIdNumber() == null && appUserId.getIdString() == null) {
            return;
        }
        long userID = userUtil.convertBuriUserId(appUserId);
        sysContext.setBuriUserID(new Long(userID));
    }

    public BuriUserUtil getUserUtil() {
        return userUtil;
    }

    public void setUserUtil(BuriUserUtil userUtil) {
        this.userUtil = userUtil;
    }

    @Override
    public BuriCompiler getBuriCompiler() {
        return buriCompiler;
    }

    @Override
    public void setBuriCompiler(BuriCompiler buriCompiler) {
        this.buriCompiler = buriCompiler;
    }

}
