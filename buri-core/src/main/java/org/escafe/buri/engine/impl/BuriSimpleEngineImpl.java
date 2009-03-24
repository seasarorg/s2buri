/*
 * 作成日: 2006/05/24
 *
 */
package org.escafe.buri.engine.impl;

import org.escafe.buri.common.util.ClassDefUtil;
import org.escafe.buri.compiler.BuriCompiler;
import org.escafe.buri.dataaccess.BuriDataAccessFactory;
import org.escafe.buri.engine.BuriEngine;
import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.BuriUserContext;
import org.escafe.buri.engine.ParticipantProvider;
import org.escafe.buri.entity.BuriPathDataEntity;
import org.escafe.buri.service.BuriPathDataEntityService;
import org.escafe.buri.service.BuriStateEntityService;
import org.escafe.buri.util.packages.BuriExePackages;
import org.escafe.buri.util.packages.BuriExecProcess;
import org.seasar.coffee.dataaccess.DataAccessUtil;
import org.seasar.coffee.dataaccess.DataAccessUtilLongKey;
import org.seasar.coffee.dataaccess.DataAccessUtilManyKey;
import org.seasar.coffee.dataaccess.PreprocessAccessUtil;

public class BuriSimpleEngineImpl extends WakanagoEngineImpl implements
        BuriEngine {
	private BuriPathDataEntityService buriPathDataEntityService;

	private ClassDefUtil classDefUtil;

	private BuriStateEntityService buriStateEntityService;

	public void setupUserID(BuriSystemContext sysContext) {
	}

	@Override
	public void readWorkFlowFromResource(String workFlowName,
	        String resourceName) {
		readFromResource(workFlowName, resourceName, null);
	}

	@Override
	public void readWorkFlowFromResource(String workFlowName,
	        String resourceName, ParticipantProvider provider) {
	}

	public void abortData(BuriSystemContext sysContext) {
		BuriExePackages wPackageObj = selectPackage(sysContext);
		BuriExecProcess wp = selectProcessNoDataAccess(wPackageObj, sysContext);
		Object data = sysContext.getUserContext().getData();
		BuriDataAccessFactory factory = (BuriDataAccessFactory) wp;
		DataAccessUtil accessUtil = factory.getDataAccessUtil(data.getClass());
		String manyKey = getManyKey(accessUtil, data);
		Long longKey = getLongKey(accessUtil, data);
		if ((manyKey == null) && (longKey == null)) {
			return;
		}
		buriStateEntityService.updateAbortByData(longKey, manyKey, accessUtil
		    .getClassName(data));
	}

	@Override
	protected void updateSystemContext(BuriSystemContext sysContext,
	        BuriExecProcess wp, BuriExePackages wPackageObj) {
		super.updateSystemContext(sysContext, wp, wPackageObj);
		BuriDataAccessFactory factory = (BuriDataAccessFactory) wp;
		preprocessData(factory, sysContext.getUserContext());
		Object data = sysContext.getUserContext().getData();
		DataAccessUtil accessUtil = factory.getDataAccessUtil(data.getClass());
		String manyKey = getManyKey(accessUtil, data);
		Long longKey = getLongKey(accessUtil, data);
		if ((manyKey == null) && (longKey == null)) {
			return;
		}
		String pathName = sysContext.getCallPath().getPlainName() + ".%";
		if (sysContext.getCallPath().getActivityName().size() > 0) {
			pathName = sysContext.getCallPath().getPlainName();
		}
		BuriPathDataEntity dto =
		    buriPathDataEntityService.getDtoByPathKey(accessUtil
		        .getClassName(data), longKey, manyKey, pathName, sysContext
		        .getCallPath()
		        .getPathType());
		finalSetup(dto, sysContext);
	}

	protected void finalSetup(BuriPathDataEntity dto,
	        BuriSystemContext sysContext) {
		if (dto != null) {
			sysContext.setDataId(new Long(dto.dataId));
			sysContext.setStatusId(new Long(dto.stateId));
			BuriPath callPath =
			    new BuriPath(
			        dto.pathName,
			        dto.realPathName,
			        dto.pathId,
			        dto.pathType);
			sysContext.setCallPath(callPath);
		}
	}

	protected String getManyKey(DataAccessUtil accessUtil, Object data) {
		if (accessUtil instanceof DataAccessUtilManyKey) {
			return ((DataAccessUtilManyKey) accessUtil).getKey(data);
		}
		return null;
	}

	protected Long getLongKey(DataAccessUtil accessUtil, Object data) {
		if (accessUtil instanceof DataAccessUtilLongKey) {
			return ((DataAccessUtilLongKey) accessUtil).getKey(data);
		}
		return null;
	}

	protected void preprocessData(BuriDataAccessFactory factory,
	        BuriUserContext userContext) {
		PreprocessAccessUtil pre =
		    factory.getPreprocessAccessUtil(userContext.getData().getClass());
		if (pre != null) {
			Object trueData = pre.getTrueData(userContext.getData());
			userContext.setData(trueData);
		}
	}

	@Override
	public BuriCompiler getBuriCompiler() {
		return buriCompiler;
	}

	@Override
	public void setBuriCompiler(BuriCompiler buriCompiler) {
		this.buriCompiler = buriCompiler;
	}

	public ClassDefUtil getClassDefUtil() {
		return classDefUtil;
	}

	public void setClassDefUtil(ClassDefUtil classDefUtil) {
		this.classDefUtil = classDefUtil;
	}

	public BuriPathDataEntityService getBuriPathDataEntityService() {
		return buriPathDataEntityService;
	}

	public void setBuriPathDataEntityService(
	        BuriPathDataEntityService buriPathDataEntityService) {
		this.buriPathDataEntityService = buriPathDataEntityService;
	}

	public BuriStateEntityService getBuriStateEntityService() {
		return buriStateEntityService;
	}

	public void setBuriStateEntityService(
	        BuriStateEntityService buriStateEntityService) {
		this.buriStateEntityService = buriStateEntityService;
	}
}
