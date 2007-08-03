package org.escafe.buri.engine.processor.util.impl;

import java.util.List;

import org.escafe.buri.dao.BuriUserDao;
import org.escafe.buri.dao.util.BuriUserUtil;
import org.escafe.buri.dto.BuriUserEntityDto;
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

	private BuriUserUtil userUtil;

	private BuriUserDao userDao;

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
	
	private void toNextStatus(String callPath, Object data, Object userData,Object action) {
		BuriProcessorInfo info = new BuriProcessorInfo();
		info.put("signalAction", Boolean.TRUE);
		
		processor.toNextStatusAction(callPath, data, userData, action);
	}

	@SuppressWarnings("unchecked")
	private void processSignal(String callPath, Object data, String action) {
		DataAccessFactory accessFactory = processor.getDataAccessFactory(callPath);
		Long longKey = getLongKey(data, accessFactory);
		String manyKey = getManyKey(data, accessFactory);
		List<BuriUserEntityDto> buriUsers = userDao.getBuriUserFromPathAndPkey(callPath, longKey, manyKey);
		if(buriUsers != null && buriUsers.size() > 0) {
			for (BuriUserEntityDto userEntity : buriUsers) {
				IdentityInfo appUserId = new IdentityInfo(userEntity.getUserIDNum(), userEntity.getUserIDVal());
				Object userData = userUtil.getUserData(accessFactory, userEntity.getBuriUserID(), appUserId);
				
				toNextStatus(callPath, data, userData,action);
			}
		} else {
			toNextStatus(callPath, data, null,action);
		}
	}
	
	public void signal(String callPath, Object data) {
		processSignal(callPath, data, null);
	}

	public void signal(String callPath, Object data, String action) {
		processSignal(callPath, data, action);
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

}
