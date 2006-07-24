package org.seasar.buri.engine.interceptor;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.buri.aop.impl.BuriMethodInvocation;
import org.seasar.buri.dao.BuriDataPathHistoryDao;
import org.seasar.buri.dao.util.BuriPathUtil;
import org.seasar.buri.dto.BuriDataPathHistoryEntityDto;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.util.packages.BranchWalker;
import org.seasar.buri.util.packages.BuriExecProcess;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;

public class BuriRecorderInterceptor extends AbstractInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BuriDataPathHistoryDao historyDao;
	private BuriPathUtil pathUtil;

	public Object invoke(MethodInvocation invocation) throws Throwable {
		if( ! (invocation instanceof BuriMethodInvocation)) {
			return invocation.proceed();
		}
		BuriMethodInvocation bInvo = (BuriMethodInvocation)invocation;
		if(bInvo.getThisObject() instanceof BuriExecProcess) {
			if(bInvo.getCallMethod().getName().indexOf("_start") > 0) {
				saveLog(bInvo);
			}
		}
		return invocation.proceed();
	}
	
	protected void saveLog(BuriMethodInvocation bInvo) {
		BuriSystemContext sysContext = (BuriSystemContext)bInvo.getCallArguments()[0];
		BranchWalker walker = (BranchWalker)bInvo.getCallArguments()[1];
		BuriDataPathHistoryEntityDto dto = new BuriDataPathHistoryEntityDto();
		dto.setAction(sysContext.getUserContext().getAction());
		if(sysContext.getBTID() != null) {
			dto.setBTID(sysContext.getBTID().longValue());
		}
		dto.setBuriUserID(sysContext.getUserID());
		dto.setDataID(sysContext.getDataID());
		if(walker.getNowPath() != null) {
			BuriPath realPath = pathUtil.getBuriPathFromRealPath(walker.getNowPath());
			long pathID = realPath.getBuriPathID();
			dto.setPathID(new Long(pathID));
			dto.setPathName(realPath.toString());
		} else {
			dto.setPathName(sysContext.getCallPath().toString() + "/" + bInvo.getCallMethod().getName());
		}
		historyDao.insert(dto);
	}

	public BuriDataPathHistoryDao getHistoryDao() {
		return historyDao;
	}

	public void setHistoryDao(BuriDataPathHistoryDao historyDao) {
		this.historyDao = historyDao;
	}

	public BuriPathUtil getPathUtil() {
		return pathUtil;
	}

	public void setPathUtil(BuriPathUtil pathUtil) {
		this.pathUtil = pathUtil;
	}
	

}
