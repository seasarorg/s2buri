/*
 * 作成日: 2006/05/10
 *
 */
package org.escafe.buri.dao.util.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import jp.starlogic.util.datetime.DateUtil;

import org.escafe.buri.dao.util.BTIDUtil;
import org.escafe.buri.dao.util.BuriDataUtil;
import org.escafe.buri.dao.util.BuriPathUtil;
import org.escafe.buri.dao.util.BuriStateUtil;
import org.escafe.buri.dao.util.BuriUndoLogUtil;
import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.engine.BuriRealPath;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.entity.BuriBranchEntity;
import org.escafe.buri.entity.BuriStateEntity;
import org.escafe.buri.event.state.caller.BuriStatusEventCaller;
import org.escafe.buri.exception.IllegalArgumentRuntimeException;
import org.escafe.buri.oouo.internal.structure.BuriActivityType;
import org.escafe.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.escafe.buri.service.BuriBranchEntityService;
import org.escafe.buri.service.BuriStateEntityService;
import org.escafe.buri.util.packages.BranchWalker;
import org.escafe.buri.util.packages.BuriExePackages;
import org.escafe.buri.util.packages.BuriExecProcess;
import org.seasar.coffee.dataaccess.DataAccessFactory;
import org.seasar.coffee.script.Script;
import org.seasar.coffee.script.ScriptFactory;
import org.seasar.framework.util.StringUtil;

public class BuriStateUtilImpl implements BuriStateUtil {
	private BuriDataUtil dataUtil;

	private BuriPathUtil pathUtil;

	private BuriStateEntityService buriStateEntityService;

	private BuriBranchEntityService buriBranchEntityService;

	private BuriUndoLogUtil undoLogUtil;

	private BTIDUtil btidUtil;

	private ScriptFactory scriptFactory;

	protected BuriStatusEventCaller buriStatusEventCaller;

	public BranchWalker createBranchWalker(BuriSystemContext sysContext) {
		BranchWalker walker = new BranchWalker();
		walker.setBranchId(0);
		walker.setParentBranchId(0);
		walker.setParentPath(sysContext.getCallPath().moveUpPath());
		walker.setNowPath(null);
		return walker;
	}

	public BranchWalker loadBranchWalker(BuriSystemContext sysContext) {
		if ((sysContext.getStatusId() == null)
		    || (sysContext.getStatusId().longValue() == 0)) {
			return createBranchWalker(sysContext);
		}
		BuriStateEntity stateDto =
		    buriStateEntityService.getBuriState(sysContext
		        .getStatusId()
		        .longValue());
		if ((stateDto == null) || (stateDto.branchId == null)) {
			return createBranchWalker(sysContext);
		}
		return loadBranchWalker(sysContext, stateDto);
	}

	private BranchWalker loadBranchWalker(BuriSystemContext sysContext,
	        BuriStateEntity stateDto) {
		BuriBranchEntity branchDto =
		    buriBranchEntityService.select(stateDto.branchId.longValue());
		if (branchDto == null) {
			return createBranchWalker(sysContext);
		} else {
			return loadBranchWalker(sysContext, stateDto, branchDto);
		}
	}

	private BranchWalker loadBranchWalker(BuriSystemContext sysContext,
	        BuriStateEntity buriStateEntity, BuriBranchEntity buriBranchEntity) {
		BranchWalker walker = new BranchWalker();
		walker.setBranchId(buriBranchEntity.branchId);
		walker.setParentBranchId(buriBranchEntity.parentBranchId.longValue());
		walker.setParentPath(sysContext.getCallPath().moveUpPath());
		BuriPath branchPath =
		    pathUtil.getBuriPathFromRealPath(pathUtil
		        .getBuriPathById(buriStateEntity.pathId.longValue()));
		walker.setNowPath(branchPath);
		return walker;
	}

	public void saveBranch(BranchWalker walker, DataAccessFactory factory,
	        BuriSystemContext sysContext) {
		if (walker.getBranchId() != 0) {
			return;
		}
		BuriBranchEntity branch = new BuriBranchEntity();
		branch.btId = btidUtil.getCurrentBtId();
		long dataId = dataUtil.getBuriDataId(factory, sysContext);
		branch.dataId = Long.valueOf(dataId);
		branch.parentBranchId = Long.valueOf(walker.getParentBranchId());
		buriBranchEntityService.insert(branch);
		walker.setBranchId(branch.branchId);
	}

	public BranchWalker branchChild(BranchWalker parentWalker,
	        DataAccessFactory factory, BuriSystemContext sysContext) {
		BranchWalker walker = new BranchWalker();
		walker.setParentBranchId(parentWalker.getBranchId());
		walker.setParentPath(parentWalker.getParentPath());
		return walker;
	}

	public BranchWalker getNowPathBranchWalker(DataAccessFactory factory,
	        BuriSystemContext sysContext, BuriPath callPath) {
		long dataId = dataUtil.getBuriDataId(factory, sysContext);
		BuriPath path = pathUtil.getBuriPathFromRealPath(callPath);
		BuriStateEntity stateDto =
		    buriStateEntityService.getBuriStateByPathAndData(path
		        .getBuriPathId(), dataId);
		if (stateDto == null) {
			return null;
		}
		return getNowPathBranchWalker(path, stateDto, sysContext);
	}

	private BranchWalker getNowPathBranchWalker(BuriPath path,
	        BuriStateEntity buriStateEntity, BuriSystemContext sysContext) {
		BranchWalker walker = new BranchWalker();
		walker.setBranchId(buriStateEntity.branchId.longValue());
		walker.setNowPath(path);
		walker.setParentPath(sysContext.getCallPath());
		BuriBranchEntity buriBranchEntity =
		    buriBranchEntityService
		        .select(buriStateEntity.branchId.longValue());
		if (buriBranchEntity != null) { // 本当は状態と一緒に保存しているはずなので不要なハズ
			assert buriBranchEntity != null;
			assert buriBranchEntity.parentBranchId != null;
			walker.setParentBranchId(buriBranchEntity.parentBranchId
			    .longValue());
		}
		return walker;
	}

	public BranchWalker createBranch(BranchWalker nowWalker,
	        BuriPath parentPath, BuriSystemContext sysContext, String pathName,
	        String PathId) {
		BuriPath tgtPath = parentPath.moveChildPath(pathName, PathId);
		BuriPath path = pathUtil.getBuriPathFromRealPath(tgtPath);
		BranchWalker walker = new BranchWalker();
		walker.setParentPath(parentPath);
		walker.setParentBranchId(nowWalker.getBranchId());
		BuriBranchEntity dto = new BuriBranchEntity();
		dto.dataId = sysContext.getDataId();
		dto.parentBranchId = Long.valueOf(nowWalker.getBranchId());
		dto.processDate = new Date();
		dto.pathId = Long.valueOf(path.getBuriPathId());
		buriBranchEntityService.insert(dto);
		walker.setBranchId(dto.branchId);
		walker.setNowPath(tgtPath);
		buriStatusEventCaller.createBranch(sysContext, walker);
		return walker;
	}

	public Long loadStatus(DataAccessFactory factory,
	        BuriSystemContext sysContext, BranchWalker walker) {
		BuriPath path = pathUtil.getBuriPathFromRealPath(walker.getNowPath());
		long dataID = dataUtil.getBuriDataId(factory, sysContext);
		if (dataID == 0) {
			return 0L;
		}
		BuriStateEntity dto =
		    buriStateEntityService.getBuriStateByPathAndData(path
		        .getBuriPathId(), dataID);
		if (dto != null) {
			return dto.stateId;
		}
		return 0L;
	}

	public Long saveStatus(DataAccessFactory factory,
	        BuriSystemContext sysContext, BranchWalker walker) {
		dataUtil.storeData(factory, sysContext);
		dataUtil.updateBuriData(factory, sysContext);
		BuriStateEntity stateDto = createStateDto(factory, sysContext, walker);
		buriStateEntityService.insert(stateDto);
		buriStatusEventCaller.saveState(factory, sysContext, walker);
		return stateDto.stateId;
	}

	protected BuriStateEntity createStateDto(DataAccessFactory factory,
	        BuriSystemContext sysContext, BranchWalker walker) {
		long dataId = dataUtil.getBuriDataId(factory, sysContext);
		BuriPath path = pathUtil.getBuriPathFromRealPath(walker.getNowPath());
		BuriStateEntity stateDto = new BuriStateEntity();
		stateDto.dataId = Long.valueOf(dataId);
		stateDto.pathId = Long.valueOf(path.getBuriPathId());
		stateDto.branchId = Long.valueOf(walker.getBranchId());
		stateDto.insertDate = new Date();
		if (factory instanceof BuriExecProcess) {
			BuriExecProcess process = (BuriExecProcess) factory;
			BuriActivityType act =
			    process.getBuriWorkflowProcessType().getActivityById(
			        path.getActivityId().get(0));
			stateDto.participantName = (act.getParticipantName());
			stateDto.participantType = (act.getParticipantType());
		}
		stateDto.abortDate = DateUtil.getSQLMaxDate();
		stateDto.processDate = DateUtil.getSQLMaxDate();
		setupAutoRunTime(stateDto, factory, sysContext, walker);
		return stateDto;
	}

	protected void setupAutoRunTime(BuriStateEntity stateDto,
	        DataAccessFactory factory, BuriSystemContext sysContext,
	        BranchWalker walker) {
		Date setupDate = DateUtil.getSQLMaxDate();
		BuriExePackages exePackages = getBuriExePackages(factory);
		if (exePackages != null) {
			Script exScript =
			    scriptFactory.getScript(exePackages
			        .getTimeLimitExpressionType());
			String limit = getLimit(exePackages, walker);
			setupDate = getLimitDate(limit, exScript, sysContext, walker);
			buriStatusEventCaller.setAutorun(
			    factory,
			    sysContext,
			    walker,
			    setupDate);
		}
		stateDto.autoRunTime = setupDate;
	}

	protected Date getLimitDate(String limit, Script exScript,
	        BuriSystemContext sysContext, BranchWalker walker) {
		if (!StringUtil.isEmpty(limit)) {
			Object obj =
			    exScript.eval(null, limit, sysContext.getUserContext());
			if (obj == null) {
				return DateUtil.getSQLMaxDate();
			}
			if (obj instanceof Date) {
				return (Date) obj;
			} else if (obj instanceof Calendar) {
				return ((Calendar) obj).getTime();
			} else if (obj instanceof Timestamp) {
				return new Date(((Timestamp) obj).getTime());
			} else {
				throw new IllegalArgumentRuntimeException(
				    "EBRI0007",
				    new Object[] { walker.getNowPath(), obj });
			}
		}
		return DateUtil.getSQLMaxDate();
	}

	protected BuriExePackages getBuriExePackages(DataAccessFactory factory) {
		BuriExePackages exePackages = null;
		if (factory instanceof BuriExecProcess) {
			exePackages = ((BuriExecProcess) factory).getBuriExePackages();
		} else if (factory instanceof BuriExePackages) {
			exePackages = (BuriExePackages) factory;
		}
		return exePackages;
	}

	protected String getLimit(BuriExePackages exePackages, BranchWalker walker) {
		BuriRealPath nowRealPath = walker.getNowPath().getRealPath();
		BuriWorkflowProcessType processType =
		    exePackages.getBuriPackageType().getProcessById(
		        nowRealPath.getWorkflowProcess());
		BuriActivityType actType =
		    processType.getActivityById(nowRealPath
		        .getActivity()
		        .get(0)
		        .toString());
		String limit = actType.getLimit();
		return limit;
	}

	public void processed(DataAccessFactory factory,
	        BuriSystemContext sysContext, BranchWalker walker) {
		assert sysContext.getStatusId() != null;
		long stateID = sysContext.getStatusId().longValue();
		buriStatusEventCaller.processed(factory, sysContext, walker);
		undoLogUtil.addUndoLog(stateID, 0);
		buriStateEntityService.updateProceesByStateId(stateID);
	}

	public void abortStatus(DataAccessFactory factory,
	        BuriSystemContext sysContext, BranchWalker walker) {
		assert sysContext.getStatusId() != null;
		long stateID = sysContext.getStatusId().longValue();
		assert walker.getBranchId() != 0;
		buriStatusEventCaller.abortState(factory, sysContext, walker);
		undoLogUtil.addUndoLog(stateID, 0);
		buriStateEntityService.updateAbortByStateId(stateID);
	}

	public void abortBranch(DataAccessFactory factory,
	        BuriSystemContext sysContext, BranchWalker walker) {
		buriStatusEventCaller.abortBranch(factory, sysContext, walker);
		abortParentBranchID(walker.getParentBranchId(), factory, sysContext);
		BuriBranchEntity dto =
		    buriBranchEntityService.select(walker.getParentBranchId());
		walker.setBranchId(dto.branchId);
		walker.setParentBranchId(dto.parentBranchId);
	}

	protected void abortParentBranchID(long parentBranchId,
	        DataAccessFactory factory, BuriSystemContext sysContext) {
		if (parentBranchId == 0) {
			return;
		}
		List<BuriBranchEntity> childs =
		    buriBranchEntityService.getBranchByParentId(parentBranchId);
		Iterator<BuriBranchEntity> ite = childs.iterator();
		while (ite.hasNext()) {
			BuriBranchEntity child = ite.next();
			abortParentBranchID(child.branchId, factory, sysContext);
		}
		undoLogUtil.addUndoLog(0, parentBranchId);
		buriStateEntityService.updateAbortByBranchId(parentBranchId);
	}

	public Long countNoProcessedSiblingStatus(DataAccessFactory factory,
	        BuriSystemContext sysContext, BranchWalker walker) {
		assert walker.getParentBranchId() != 0;
		long count =
		    buriStateEntityService.countByBranchIdAndNotProcessed(walker
		        .getParentBranchId());
		return count;
	}

	public BuriDataUtil getDataUtil() {
		return dataUtil;
	}

	public void setDataUtil(BuriDataUtil dataUtil) {
		this.dataUtil = dataUtil;
	}

	public BuriPathUtil getPathUtil() {
		return pathUtil;
	}

	public void setPathUtil(BuriPathUtil pathUtil) {
		this.pathUtil = pathUtil;
	}

	public BuriBranchEntityService getBuriBranchEntityService() {
		return buriBranchEntityService;
	}

	public void setBuriBranchEntityService(
	        BuriBranchEntityService buriBranchEntityService) {
		this.buriBranchEntityService = buriBranchEntityService;
	}

	public BuriStateEntityService getBuriStateEntityService() {
		return buriStateEntityService;
	}

	public void setBuriStateEntityService(
	        BuriStateEntityService buriStateEntityService) {
		this.buriStateEntityService = buriStateEntityService;
	}

	public BuriUndoLogUtil getUndoLogUtil() {
		return undoLogUtil;
	}

	public void setUndoLogUtil(BuriUndoLogUtil undoLogUtil) {
		this.undoLogUtil = undoLogUtil;
	}

	public BTIDUtil getBtidUtil() {
		return btidUtil;
	}

	public void setBtidUtil(BTIDUtil btidUtil) {
		this.btidUtil = btidUtil;
	}

	public ScriptFactory getScriptFactory() {
		return scriptFactory;
	}

	public void setScriptFactory(ScriptFactory scriptFactory) {
		this.scriptFactory = scriptFactory;
	}

	public BuriStatusEventCaller getBuriStatusEventCaller() {
		return buriStatusEventCaller;
	}

	public void setBuriStatusEventCaller(
	        BuriStatusEventCaller buriStatusEventCaller) {
		this.buriStatusEventCaller = buriStatusEventCaller;
	}
}
