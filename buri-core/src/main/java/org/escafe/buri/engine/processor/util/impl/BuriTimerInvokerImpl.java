/*
 * 作成日: 2006/06/26
 *
 */
package org.escafe.buri.engine.processor.util.impl;

import org.escafe.buri.dao.util.BuriDataUtil;
import org.escafe.buri.dao.util.BuriUserUtil;
import org.escafe.buri.engine.IdentityInfo;
import org.escafe.buri.engine.processor.BuriAutoSelectProcessor;
import org.escafe.buri.engine.processor.BuriProcessorInfo;
import org.escafe.buri.engine.processor.util.BuriTimerInvoker;
import org.escafe.buri.entity.BuriPathDataEntity;
import org.escafe.buri.entity.BuriPathDataUserEntity;
import org.escafe.buri.service.BuriPathDataUserEntityService;
import org.seasar.coffee.dataaccess.DataAccessFactory;

public class BuriTimerInvokerImpl implements BuriTimerInvoker {
	private BuriAutoSelectProcessor processor;

	private BuriDataUtil dataUtil;

	private BuriUserUtil userUtil;

	private BuriPathDataUserEntityService buriPathDataUserEntityService;

	public void invoke(BuriPathDataEntity callDto) {
		DataAccessFactory accessFactory =
		    processor.getDataAccessFactory(callDto.pathName);
		BuriProcessorInfo info = new BuriProcessorInfo();
		info.put("autoAction", Boolean.TRUE);
		if (processor.isSimpleProcessor(callDto.pathName)) {
			simpleCall(callDto, accessFactory, info);
		}
		if (processor.isStdProcessor(callDto.pathName)) {
			stdCall(callDto, accessFactory, info);
		}
	}

	protected void simpleCall(BuriPathDataEntity callDto,
	        DataAccessFactory accessFactory, BuriProcessorInfo info) {
		Object argDto = getArgDto(callDto, accessFactory);
		processor.toNextStatus(callDto.pathName, argDto, null, info);
	}

	protected Object getArgDto(BuriPathDataEntity callDto,
	        DataAccessFactory accessFactory) {
		Object argDto = dataUtil.getBuriData(callDto.dataId, accessFactory);
		return argDto;
	}

	protected Object getUserData(BuriPathDataEntity callDto,
	        DataAccessFactory accessFactory) {
		BuriPathDataUserEntity buriPathDataUserEntity =
		    buriPathDataUserEntityService.getDto(callDto.stateId);
		IdentityInfo appUserId =
		    new IdentityInfo(
		        buriPathDataUserEntity.userIdNum,
		        buriPathDataUserEntity.userIdVal);
		Object userData =
		    userUtil.getUserData(
		        accessFactory,
		        buriPathDataUserEntity.buriUserId,
		        appUserId);
		return userData;
	}

	protected void stdCall(BuriPathDataEntity callDto,
	        DataAccessFactory accessFactory, BuriProcessorInfo info) {
		Object argDto = getArgDto(callDto, accessFactory);
		Object userData = getUserData(callDto, accessFactory);
		processor.toNextStatus(callDto.pathName, argDto, userData, info);
	}

	public BuriDataUtil getDataUtil() {
		return dataUtil;
	}

	public void setDataUtil(BuriDataUtil dataUtil) {
		this.dataUtil = dataUtil;
	}

	public BuriAutoSelectProcessor getProcessor() {
		return processor;
	}

	public void setProcessor(BuriAutoSelectProcessor processor) {
		this.processor = processor;
	}

	public BuriUserUtil getUserUtil() {
		return userUtil;
	}

	public void setUserUtil(BuriUserUtil userUtil) {
		this.userUtil = userUtil;
	}

	public BuriPathDataUserEntityService getBuriPathDataUserService() {
		return buriPathDataUserEntityService;
	}

	public void setBuriPathDataUserService(
	        BuriPathDataUserEntityService buriPathDataUserEntityService) {
		this.buriPathDataUserEntityService = buriPathDataUserEntityService;
	}
}
