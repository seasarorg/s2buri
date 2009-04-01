/*
 * 作成日: 2006/06/06
 *
 */
package org.escafe.buri.dao.util.impl;

import java.util.ArrayList;
import java.util.List;

import org.escafe.buri.dao.util.BuriStateUtil;
import org.escafe.buri.dao.util.BuriUserUtil;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.IdentityInfo;
import org.escafe.buri.entity.BuriStateUserEntity;
import org.escafe.buri.service.BuriStateUserEntityService;
import org.escafe.buri.util.packages.BranchWalker;
import org.seasar.coffee.dataaccess.DataAccessFactory;

public class BuriStateUserUtilImpl extends BuriStateUtilImpl implements
        BuriStateUtil {
	private BuriUserUtil userUtil;

	private BuriStateUserEntityService buriStateUserEntityService;

	@Override
	public Long saveStatus(DataAccessFactory factory,
	        BuriSystemContext sysContext, BranchWalker walker) {
		long stateID = super.saveStatus(factory, sysContext, walker);
		List<IdentityInfo> users =
		    userUtil.getUserIds(factory, sysContext, walker);
		List<Long> userIds = userUtil.convertBuriUserIds(users);
		List<BuriStateUserEntity> entities =
		    getEntityList(Long.valueOf(stateID), userIds);
		for (BuriStateUserEntity dto : entities) {
			buriStateUserEntityService.insert(dto);
		}
		return stateID;
	}

	protected List<BuriStateUserEntity> getEntityList(Long stateID,
	        List<Long> userIds) {
		List<BuriStateUserEntity> entities =
		    new ArrayList<BuriStateUserEntity>();
		for (Long userID : userIds) {
			BuriStateUserEntity dto = new BuriStateUserEntity();
			dto.buriUserId = userID;
			dto.stateId = stateID;
			entities.add(dto);
		}
		return entities;
	}

	public BuriUserUtil getUserUtil() {
		return userUtil;
	}

	public void setUserUtil(BuriUserUtil userUtil) {
		this.userUtil = userUtil;
	}

	public BuriStateUserEntityService getBuriStateUserEntityService() {
		return buriStateUserEntityService;
	}

	public void setBuriStateUserEntityService(
	        BuriStateUserEntityService buriStateUserEntityService) {
		this.buriStateUserEntityService = buriStateUserEntityService;
	}
}
