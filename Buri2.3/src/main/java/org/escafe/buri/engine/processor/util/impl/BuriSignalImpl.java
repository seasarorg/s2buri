package org.escafe.buri.engine.processor.util.impl;

import java.util.List;

import org.escafe.buri.dao.BuriDataDao;
import org.escafe.buri.dao.BuriPathDao;
import org.escafe.buri.dao.BuriPathDataDao;
import org.escafe.buri.dao.BuriPathDataUserDao;
import org.escafe.buri.dao.BuriUserDao;
import org.escafe.buri.dao.util.BuriDataUtil;
import org.escafe.buri.dao.util.BuriUserUtil;
import org.escafe.buri.dto.BuriPathDataEntityDto;
import org.escafe.buri.dto.BuriPathDataUserEntityDto;
import org.escafe.buri.dto.BuriPathEntityDto;
import org.escafe.buri.engine.IdentityInfo;
import org.escafe.buri.engine.processor.BuriAutoSelectProcessor;
import org.escafe.buri.engine.processor.BuriProcessorInfo;
import org.escafe.buri.engine.processor.util.BuriSignal;
import org.seasar.coffee.dataaccess.DataAccessFactory;
import org.seasar.coffee.dataaccess.DataAccessUtil;
import org.seasar.coffee.dataaccess.DataAccessUtilLongKey;
import org.seasar.coffee.dataaccess.DataAccessUtilManyKey;

/**
 * 
 * @author a-conv
 * 
 */
public class BuriSignalImpl implements BuriSignal {

	private BuriAutoSelectProcessor processor;

	private BuriPathDao pathDao;

	private BuriPathDataDao pathDataDao;

	private BuriDataUtil dataUtil;

	private BuriDataDao dataDao;

	private BuriUserUtil userUtil;

	private BuriUserDao userDao;

	private BuriPathDataUserDao pathDataUserDao;

	private Long getLongKey(Object data, DataAccessFactory accessFactory) {
		Long longKey = null;
		DataAccessUtil accessUtil = accessFactory.getDataAccessUtil(data.getClass());
		if (accessUtil instanceof DataAccessUtilLongKey) {
			DataAccessUtilLongKey longKeyUtil = (DataAccessUtilLongKey) accessUtil;
			longKey = longKeyUtil.getKey(data);
		}
		return longKey;
	}

	private String getManyKey(Object data, DataAccessFactory accessFactory) {
		String manyKey = null;
		DataAccessUtil accessUtil = accessFactory.getDataAccessUtil(data.getClass());
		if (accessUtil instanceof DataAccessUtilManyKey) {
			DataAccessUtilManyKey manyKeyUtil = (DataAccessUtilManyKey) accessUtil;
			manyKey = manyKeyUtil.getKey(data);
		}
		return manyKey;
	}

	private BuriPathDataEntityDto getBuriPathData(String callPath, Object data, DataAccessFactory accessFactory) {
		BuriPathEntityDto pathEntityDto = getBuriPath(callPath);
		String className = data.getClass().getName();
		String pathName = pathEntityDto.getPathName();
		Long pathType = pathEntityDto.getPathType();
		Long longKey = getLongKey(data, accessFactory);
		String manyKey = getManyKey(data, accessFactory);
		return pathDataDao.getDtoByPathKey(className, longKey, manyKey, pathName, pathType);
	}

	private BuriPathEntityDto getBuriPath(String callPath) {
		List<BuriPathEntityDto> paths = pathDao.getAllBuriPath();
		for (BuriPathEntityDto path : paths) {
			if (path.getPathName().equals(callPath)) {
				return path;
			}
		}
		return null;
	}

	private Object getUserData(BuriPathDataEntityDto pathDataDto, DataAccessFactory accessFactory) {
		BuriPathDataUserEntityDto pathDataUserDto = pathDataUserDao.getDto(pathDataDto.getStateID());
		IdentityInfo appUserId = new IdentityInfo(pathDataUserDto.getUserIDNum(), pathDataUserDto.getUserIDVal());
		return userUtil.getUserData(accessFactory, pathDataUserDto.getBuriUserID(), appUserId);
	}

	private void simpleCall(String callPath, Object data, DataAccessFactory accessFactory, BuriProcessorInfo info, Object action) {
		if (action != null) {
			processor.toNextStatusAction(callPath, data, null, action);
		} else {
			processor.toNextStatus(callPath, data, null, info);
		}
	}

	private void standardCall(String callPath, Object data, DataAccessFactory accessFactory, BuriProcessorInfo info, Object action) {
		BuriPathDataEntityDto pathDataDto = getBuriPathData(callPath, data, accessFactory);
		Object userData = getUserData(pathDataDto, accessFactory);
		if (action != null) {
			processor.toNextStatusAction(callPath, data, userData, action);
		} else {
			processor.toNextStatus(callPath, data, userData, info);
		}
	}

	public void signal(String callPath, Object data) {
		DataAccessFactory accessFactory = processor.getDataAccessFactory(callPath);
		BuriProcessorInfo info = new BuriProcessorInfo();
		info.put("signalAction", Boolean.TRUE);
		if (processor.isSimpleProcessor(callPath)) {
			simpleCall(callPath, data, accessFactory, info, null);
		}
		if (processor.isStdProcessor(callPath)) {
			standardCall(callPath, data, accessFactory, info, null);
		}
	}

	public void signal(String callPath, Object data, String action) {
		DataAccessFactory accessFactory = processor.getDataAccessFactory(callPath);
		BuriProcessorInfo info = new BuriProcessorInfo();
		info.put("signalAction", Boolean.TRUE);
		info.put("action", action);
		if (processor.isSimpleProcessor(callPath)) {
			simpleCall(callPath, data, accessFactory, info, action);
		}
		if (processor.isStdProcessor(callPath)) {
			standardCall(callPath, data, accessFactory, info, action);
		}
	}

	public void signal(String callPath, List datas) {
		for (Object data : datas) {
			signal(callPath, data);
		}
	}

	public void signal(String callPath, List datas, String action) {
		for (Object data : datas) {
			signal(callPath, data, action);
		}
	}

	public BuriAutoSelectProcessor getProcessor() {
		return processor;
	}

	public void setProcessor(BuriAutoSelectProcessor processor) {
		this.processor = processor;
	}

	public BuriPathDao getPathDao() {
		return pathDao;
	}

	public void setPathDao(BuriPathDao pathDao) {
		this.pathDao = pathDao;
	}

	public BuriPathDataDao getPathDataDao() {
		return pathDataDao;
	}

	public void setPathDataDao(BuriPathDataDao pathDataDao) {
		this.pathDataDao = pathDataDao;
	}

	public BuriDataUtil getDataUtil() {
		return dataUtil;
	}

	public void setDataUtil(BuriDataUtil dataUtil) {
		this.dataUtil = dataUtil;
	}

	public BuriDataDao getDataDao() {
		return dataDao;
	}

	public void setDataDao(BuriDataDao dataDao) {
		this.dataDao = dataDao;
	}

	public BuriUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(BuriUserDao userDao) {
		this.userDao = userDao;
	}

	public BuriUserUtil getUserUtil() {
		return userUtil;
	}

	public void setUserUtil(BuriUserUtil userUtil) {
		this.userUtil = userUtil;
	}

	public BuriPathDataUserDao getPathDataUserDao() {
		return pathDataUserDao;
	}

	public void setPathDataUserDao(BuriPathDataUserDao pathDataUserDao) {
		this.pathDataUserDao = pathDataUserDao;
	}

}
