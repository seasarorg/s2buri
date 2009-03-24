package org.escafe.buri.engine.interceptor;

import org.aopalliance.intercept.MethodInvocation;
import org.escafe.buri.aop.impl.BuriMethodInvocation;
import org.escafe.buri.dao.util.BuriPathUtil;
import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.entity.BuriDataPathHistoryEntity;
import org.escafe.buri.service.BuriDataPathHistoryEntityService;
import org.escafe.buri.util.packages.BranchWalker;
import org.escafe.buri.util.packages.BuriExecProcess;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;

public class BuriRecorderInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;

	private BuriDataPathHistoryEntityService buriDataPathHistoryEntityService;

	private BuriPathUtil pathUtil;

	public Object invoke(MethodInvocation invocation) throws Throwable {
		if (!(invocation instanceof BuriMethodInvocation)) {
			return invocation.proceed();
		}
		BuriMethodInvocation bInvo = (BuriMethodInvocation) invocation;
		if (bInvo.getThisObject() instanceof BuriExecProcess) {
			if (bInvo.getCallMethod().getName().indexOf("_start") > 0) {
				saveLog(bInvo);
			}
		}
		return invocation.proceed();
	}

	protected void saveLog(BuriMethodInvocation bInvo) {
		BuriSystemContext sysContext =
		    (BuriSystemContext) bInvo.getCallArguments()[0];
		BranchWalker walker = (BranchWalker) bInvo.getCallArguments()[1];
		BuriDataPathHistoryEntity dto = new BuriDataPathHistoryEntity();
		dto.action = sysContext.getUserContext().getAction();
		dto.buriUserId = sysContext.getBuriUserId();
		dto.dataId = sysContext.getDataId();
		if (walker.getNowPath() != null) {
			BuriPath realPath =
			    pathUtil.getBuriPathFromRealPath(walker.getNowPath());
			long pathID = realPath.getBuriPathId();
			dto.pathId = new Long(pathID);
			dto.pathName = realPath.toString();
		} else {
			dto.pathName =
			    sysContext.getCallPath().toString()
			        + "/"
			        + bInvo.getCallMethod().getName();
		}
		buriDataPathHistoryEntityService.insert(dto);
	}

	public BuriPathUtil getPathUtil() {
		return pathUtil;
	}

	public void setPathUtil(BuriPathUtil pathUtil) {
		this.pathUtil = pathUtil;
	}

	public BuriDataPathHistoryEntityService getBuriDataPathHistoryService() {
		return buriDataPathHistoryEntityService;
	}

	public void setBuriDataPathHistoryService(
	        BuriDataPathHistoryEntityService buriDataPathHistoryEntityService) {
		this.buriDataPathHistoryEntityService =
		    buriDataPathHistoryEntityService;
	}
}
