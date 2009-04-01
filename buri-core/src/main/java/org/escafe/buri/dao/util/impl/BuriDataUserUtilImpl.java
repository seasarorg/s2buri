/*
 * 作成日: 2006/06/06
 *
 */
package org.escafe.buri.dao.util.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.escafe.buri.common.util.ClassDefUtil;
import org.escafe.buri.dao.util.BuriDataUtil;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.entity.AbstractBuriPathDataEntity;
import org.escafe.buri.entity.BuriPathDataUserEntity;
import org.escafe.buri.service.BuriPathDataUserEntityService;
import org.seasar.coffee.dataaccess.DataAccessUtilLongKey;
import org.seasar.framework.util.tiger.CollectionsUtil;

public class BuriDataUserUtilImpl extends BuriDataUtilImpl implements
        BuriDataUtil {
	private BuriPathDataUserEntityService buriPathDataUserEntityService;

	private ClassDefUtil classDefUtil;

	@Override
	protected Long countByPathKeys(String className, List<Long> longList,
	        List<String> strList, String pathName, BuriSystemContext sysContext) {
		long count =
		    buriPathDataUserEntityService.getCountByPathKeysAndUser(
		        className,
		        longList,
		        strList,
		        pathName,
		        sysContext.getCallPath().getPathType(),
		        sysContext.getBuriUserId());
		return count;
	}

	@Override
	protected List<AbstractBuriPathDataEntity> getDataInfoListFromPathName(
	        String pathName, BuriSystemContext sysContext) {
		String className = null;
		if (sysContext.getTargetDtoClass() != null) {
			className =
			    classDefUtil.getClassName(sysContext.getTargetDtoClass());
		}
		Long pathType = sysContext.getCallPath().getPathType();
		List<BuriPathDataUserEntity> infoList =
		    buriPathDataUserEntityService.getListByPathNameAndUser(
		        className,
		        pathName,
		        pathType,
		        sysContext.getBuriUserId());
		List<AbstractBuriPathDataEntity> result =
		    CollectionsUtil.newArrayList();
		result.addAll(infoList);
		return result;
	}

	@Override
	protected List<Long> getDataDtoList(String pathName,
	        DataAccessUtilLongKey dataUtil, BuriSystemContext sysContext) {
		List<AbstractBuriPathDataEntity> infoList =
		    getDataInfoListFromPathName(pathName, sysContext);
		Iterator<AbstractBuriPathDataEntity> ite = infoList.iterator();
		List<Long> result = new ArrayList<Long>();
		while (ite.hasNext()) {
			AbstractBuriPathDataEntity dto = ite.next();
			result.add(dto.pkeyNum);
		}
		return result;
	}

	public ClassDefUtil getClassDefUtil() {
		return classDefUtil;
	}

	public void setClassDefUtil(ClassDefUtil classDefUtil) {
		this.classDefUtil = classDefUtil;
	}

	public BuriPathDataUserEntityService getBuriPathDataUserEntityService() {
		return buriPathDataUserEntityService;
	}

	public void setBuriPathDataUserEntityService(
	        BuriPathDataUserEntityService buriPathDataUserEntityService) {
		this.buriPathDataUserEntityService = buriPathDataUserEntityService;
	}
}
