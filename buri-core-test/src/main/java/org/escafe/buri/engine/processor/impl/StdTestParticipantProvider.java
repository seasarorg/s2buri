/*
 * 作成日: 2006/06/14
 *
 */
package org.escafe.buri.engine.processor.impl;

import java.util.ArrayList;
import java.util.List;

import org.escafe.buri.engine.IdentityInfo;
import org.escafe.buri.engine.ParticipantContext;
import org.escafe.buri.engine.ParticipantProvider;
import org.escafe.buri.entity.BuriTestUser;
import org.escafe.buri.service.BuriTestUserService;

public class StdTestParticipantProvider implements ParticipantProvider {
	private BuriTestUserService buriTestUserService;

	public BuriTestUser getUserData(IdentityInfo appUserId) {
		if ((appUserId == null) || (appUserId.getIdNumber() == null)) {
			return null;
		}
		BuriTestUser dto =
		    buriTestUserService.getBuriTestUser(appUserId.getIdNumber());
		return dto;
	}

	public IdentityInfo getUserId(Object userData) {
		if (userData == null) {
			return new IdentityInfo(null, "無関係");
		}
		BuriTestUser dto = (BuriTestUser) userData;
		IdentityInfo appUserId = new IdentityInfo();
		appUserId.setIdNumber(dto.userId);
		appUserId.setIdString(dto.userName);
		return appUserId;
	}

	public boolean hasAuthority(ParticipantContext context) {
		BuriTestUser dto = getUserData(context.getUserId());
		if (dto == null) {
			return context.getParticipantName().equalsIgnoreCase("無関係");
		}
		if (dto.roleName.indexOf(context.getParticipantName()) >= 0) {
			return true;
		}
		return false;
	}

	public List<IdentityInfo> getAuthorizedUserIds(ParticipantContext context) {
		Long currentUserIdNumber = context.getUserId().getIdNumber();
		String roleName = context.getParticipantName();
		String startRoleName = context.getStartParticipantName();
		// 上の方を探す
		List<BuriTestUser> roleList =
		    buriTestUserService.getUserListByParentUserId(
		        currentUserIdNumber,
		        roleName);
		if (!roleName.equals("無関係")) {
			if (roleList.isEmpty()) {
				// 一段下を探す
				roleList =
				    buriTestUserService.getUserListByUserId(
				        currentUserIdNumber,
				        roleName);
			}
			if ((roleList.size() == 0)
			    && roleName.equals("下っ端")
			    && startRoleName.equals("一番上")) {
				// 一番上と下っ端が対象の場合もう一段下を探す
				List<BuriTestUser> mannaka =
				    buriTestUserService.getUserListByParentUserId(
				        currentUserIdNumber,
				        "真ん中");
				for (BuriTestUser dto : mannaka) {
					List<BuriTestUser> tmpList =
					    buriTestUserService.getUserListByParentUserId(new Long(
					        dto.userId), roleName);
					roleList.addAll(tmpList);
				}
			}
			if ((roleList.size() == 0)
			    && roleName.equals("真ん中")
			    && startRoleName.equals("下っ端")) {
				List<BuriTestUser> selfData =
				    buriTestUserService.getUserListByUserId(
				        currentUserIdNumber,
				        "下っ端");
				for (BuriTestUser dto : selfData) {
					List<BuriTestUser> tmpList =
					    buriTestUserService.getUserListByUserId(
					        dto.parentUserId,
					        roleName);
					roleList.addAll(tmpList);
				}
			}
			if ((roleList.size() == 0)
			    && roleName.equals("一番上")
			    && startRoleName.equals("真ん中")) {
				List<BuriTestUser> selfData =
				    buriTestUserService.getUserListByUserId(
				        currentUserIdNumber,
				        "真ん中");
				for (BuriTestUser dto : selfData) {
					List<BuriTestUser> tmpList =
					    buriTestUserService.getUserListByUserId(new Long(
					        dto.parentUserId), roleName);
					roleList.addAll(tmpList);
				}
			}
		}
		List<IdentityInfo> result =
		    new ArrayList<IdentityInfo>(roleList.size());
		for (BuriTestUser dto : roleList) {
			result.add(getUserId(dto));
		}
		if (result.isEmpty()) {
			result.add(new IdentityInfo(null, "無関係"));
		}
		return result;
	}

	public BuriTestUserService getBuriTestUserService() {
		return buriTestUserService;
	}

	public void setBuriTestUserService(BuriTestUserService buriTestUserService) {
		this.buriTestUserService = buriTestUserService;
	}
}
