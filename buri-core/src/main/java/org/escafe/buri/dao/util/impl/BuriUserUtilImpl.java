/*
 * 作成日: 2006/06/09
 *
 */
package org.escafe.buri.dao.util.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.escafe.buri.dao.util.BuriUserUtil;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.IdentityInfo;
import org.escafe.buri.engine.ParticipantContext;
import org.escafe.buri.engine.ParticipantProvider;
import org.escafe.buri.entity.BuriDataEntity;
import org.escafe.buri.entity.BuriUserEntity;
import org.escafe.buri.oouo.internal.structure.BuriActivityType;
import org.escafe.buri.service.BuriDataEntityService;
import org.escafe.buri.service.BuriUserEntityService;
import org.escafe.buri.util.packages.BranchWalker;
import org.escafe.buri.util.packages.BuriExePackages;
import org.escafe.buri.util.packages.BuriExecProcess;
import org.seasar.coffee.dataaccess.DataAccessFactory;

/**
 * ユーザ情報に関するユーティリティの実装クラスです。
 * 
 * @author $Author: nobeans $
 * @version $Revision: 230 $
 */
public class BuriUserUtilImpl implements BuriUserUtil {
	private BuriDataEntityService buriDataEntityService;

	private BuriUserEntityService buriUserEntityService;

	private Map<String, Long> buriUserIDCache = new HashMap<String, Long>();

	public void dispose() {
		buriUserIDCache.clear();
	}

	public long convertBuriUserId(IdentityInfo appUserId) {
		String userIDKey =
		    appUserId.getIdNumber() + "/" + appUserId.getIdString();
		if (buriUserIDCache.containsKey(userIDKey)) {
			Long buriUserID = buriUserIDCache.get(userIDKey);
			return buriUserID.longValue();
		}
		BuriUserEntity dto =
		    convertBuriUserEntity(appUserId.getIdNumber(), appUserId
		        .getIdString());
		buriUserIDCache.put(userIDKey, new Long(dto.buriUserId));
		return dto.buriUserId;
	}

	private BuriUserEntity convertBuriUserEntity(Long appUserIDNumber,
	        String appUserIDString) {
		BuriUserEntity dto =
		    buriUserEntityService.getBuriUserFromIds(
		        appUserIDNumber,
		        appUserIDString);
		if (dto == null) {
			dto = new BuriUserEntity();
			dto.userIdNum = appUserIDNumber;
			dto.userIdVal = appUserIDString;
			buriUserEntityService.insert(dto);
		}
		return dto;
	}

	public List<Long> convertBuriUserIds(List<IdentityInfo> appUserIds) {
		List<Long> resutlt = new ArrayList<Long>();
		for (IdentityInfo appUserId : appUserIds) {
			long buriUserID = convertBuriUserId(appUserId);
			resutlt.add(new Long(buriUserID));
		}
		return resutlt;
	}

	public Object getUserData(DataAccessFactory factory, long buriUserId,
	        IdentityInfo appUserId) {
		BuriExePackages packages = getBuriExePackages(factory);
		ParticipantProvider provider = packages.getParticipantProvider();
		if ((appUserId.getIdNumber() == null)
		    && (appUserId.getIdString() == null)) {
			appUserId = convertAppUserId(buriUserId);
		}
		return provider.getUserData(appUserId);
	}

	private IdentityInfo convertAppUserId(long buriUserId) {
		BuriUserEntity buriUserEntity =
		    buriUserEntityService.getBuriUser(buriUserId);
		IdentityInfo appUserId = new IdentityInfo();
		appUserId.setIdNumber(buriUserEntity.userIdNum);
		appUserId.setIdString(buriUserEntity.userIdVal);
		return appUserId;
	}

	public List<IdentityInfo> getUserIds(DataAccessFactory factory,
	        BuriSystemContext sysContext, BranchWalker walker) {
		BuriExePackages packages = getBuriExePackages(factory);
		ParticipantProvider provider = packages.getParticipantProvider();
		ParticipantContext pc = new ParticipantContext();
		pc.setInsertUserId(getInsertUserId(sysContext));
		Object userData = sysContext.getUserContext().getUserData();
		pc.setUserData(userData);
		pc.setUserId(provider.getUserId(userData));
		pc.setStartParticipantName(sysContext.getStartParticipantName());
		pc.setData(sysContext.getUserContext().getData());
		pc.setProcess((BuriExecProcess) factory);
		pc.setUserContext(sysContext.getUserContext());
		updateParticipantInfo(pc, walker, (BuriExecProcess) factory); // TODO:キャスト関係が複雑...
		List<IdentityInfo> users = provider.getAuthorizedUserIds(pc);
		return users;
	}

	private BuriExePackages getBuriExePackages(DataAccessFactory factory) {
		BuriExecProcess process = (BuriExecProcess) factory;
		BuriExePackages packages = process.getBuriExePackages();
		return packages;
	}

	private void updateParticipantInfo(ParticipantContext pc,
	        BranchWalker walker, BuriExecProcess process) {
		String actId = walker.getNowPath().getActivityId().get(0);
		BuriActivityType actType =
		    process.getBuriWorkflowProcessType().getActivityById(actId);
		pc.setParticipantName(actType.getParticipantName());
		pc.setParticipantType(actType.getParticipantType());
	}

	public IdentityInfo getInsertUserId(BuriSystemContext sysContext) {
		BuriDataEntity dataEntity =
		    buriDataEntityService.getBuriData(sysContext
		        .getDataID()
		        .longValue());
		Long insertBuriUserId = dataEntity.insertUserId;
		if (insertBuriUserId == null) {
			return null;
		}
		return convertAppUserId(insertBuriUserId.longValue());
	}

	public Map<String, Long> getBuriUserIDCache() {
		return buriUserIDCache;
	}

	public BuriDataEntityService getBuriDataEntityService() {
		return buriDataEntityService;
	}

	public void setBuriDataEntityService(
	        BuriDataEntityService buriDataEntityService) {
		this.buriDataEntityService = buriDataEntityService;
	}

	public BuriUserEntityService getBuriUserEntityService() {
		return buriUserEntityService;
	}

	public void setBuriUserEntityService(
	        BuriUserEntityService buriUserEntityService) {
		this.buriUserEntityService = buriUserEntityService;
	}
}
