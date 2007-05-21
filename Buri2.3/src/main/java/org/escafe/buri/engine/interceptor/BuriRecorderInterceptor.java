package org.escafe.buri.engine.interceptor;

import org.aopalliance.intercept.MethodInvocation;
import org.escafe.buri.aop.impl.BuriMethodInvocation;
import org.escafe.buri.dao.BuriDataPathHistoryDao;
import org.escafe.buri.dao.util.BuriPathUtil;
import org.escafe.buri.dto.BuriDataPathHistoryEntityDto;
import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.util.packages.BranchWalker;
import org.escafe.buri.util.packages.BuriExecProcess;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;

public class BuriRecorderInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = 1L;
    private BuriDataPathHistoryDao historyDao;
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
        BuriSystemContext sysContext = (BuriSystemContext) bInvo.getCallArguments()[0];
        BranchWalker walker = (BranchWalker) bInvo.getCallArguments()[1];
        BuriDataPathHistoryEntityDto dto = new BuriDataPathHistoryEntityDto();
        dto.setAction(sysContext.getUserContext().getAction());
        dto.setBuriUserID(sysContext.getBuriUserID());
        dto.setDataID(sysContext.getDataID());
        if (walker.getNowPath() != null) {
            BuriPath realPath = pathUtil.getBuriPathFromRealPath(walker.getNowPath());
            long pathID = realPath.getBuriPathId();
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
