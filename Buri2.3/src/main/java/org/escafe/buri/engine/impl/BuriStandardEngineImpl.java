/*
 * 作成日: 2006/06/06
 *
 */
package org.escafe.buri.engine.impl;

import org.escafe.buri.compiler.BuriCompiler;
import org.escafe.buri.dao.util.BuriUserUtil;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.IdentityInfo;
import org.escafe.buri.engine.ParticipantProvider;
import org.escafe.buri.util.packages.BuriExePackages;
import org.escafe.buri.util.packages.BuriExecProcess;

public class BuriStandardEngineImpl extends BuriSimpleEngineImpl {

    private BuriUserUtil userUtil;

    @Override
    public void readWorkFlowFromResource(String workFlowName, String resourceName) {
    }

    @Override
    public void readWorkFlowFromResource(String workFlowName, String resourceName, ParticipantProvider provider) {
        readFromResource(workFlowName, resourceName, provider);
    }

    @Override
    public void setupUserID(BuriSystemContext sysContext) {
        BuriExePackages wPackageObj = selectPackage(sysContext);
        BuriExecProcess wp = selectProcessNoDataAccess(wPackageObj, sysContext);
        updateUserInfo(sysContext, wp, wPackageObj);
    }

    @Override
    protected void updateUserInfo(BuriSystemContext sysContext, BuriExecProcess wp, BuriExePackages wPackageObj) {
        super.updateUserInfo(sysContext, wp, wPackageObj);
        IdentityInfo appUserId = sysContext.getAppUserId();
        if ((appUserId.getIdNumber() == null) && (appUserId.getIdString() == null)) {
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
