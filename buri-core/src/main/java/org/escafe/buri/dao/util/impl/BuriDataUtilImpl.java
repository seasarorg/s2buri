/*
 * 作成日: 2006/05/08
 *
 */
package org.escafe.buri.dao.util.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.escafe.buri.common.util.ClassDefUtil;
import org.escafe.buri.common.util.ClassDefUtilImpl;
import org.escafe.buri.dao.util.BuriDataUtil;
import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.entity.AbstractBuriPathDataEntity;
import org.escafe.buri.entity.BuriDataEntity;
import org.escafe.buri.entity.BuriPathDataEntity;
import org.escafe.buri.service.BuriDataEntityService;
import org.escafe.buri.service.BuriIdListService;
import org.escafe.buri.service.BuriPathDataEntityService;
import org.seasar.coffee.dataaccess.DataAccessFactory;
import org.seasar.coffee.dataaccess.DataAccessUtil;
import org.seasar.coffee.dataaccess.DataAccessUtilLongKey;
import org.seasar.coffee.dataaccess.DataAccessUtilManyKey;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.tiger.CollectionsUtil;

public class BuriDataUtilImpl implements BuriDataUtil {
	private BuriDataEntityService buriDataEntityService;

	private BuriIdListService buriIdListService;

	private BuriPathDataEntityService buriPathDataEntityService;

	private ClassDefUtil classDefUtil = new ClassDefUtilImpl();

	public void dispose() {
		classDefUtil = new ClassDefUtilImpl();
	}

	public Long countByPathAndDatas(String pathName, List<?> dataList,
	        DataAccessFactory factory, BuriSystemContext sysContext) {
		if ((dataList != null) && (dataList.size() == 0)) {
			return 0L;
		}
		Class<?> tgtClass = dataList.get(0).getClass();
		DataAccessUtil util = factory.getDataAccessUtil(tgtClass);
		if (util instanceof DataAccessUtilLongKey) {
			return countByPathAndDatasPkeyNum(
			    pathName,
			    dataList,
			    (DataAccessUtilLongKey) util,
			    sysContext);
		} else {
			return countByPathAndDatasPkeyVal(
			    pathName,
			    dataList,
			    (DataAccessUtilManyKey) util,
			    sysContext);
		}
	}

	protected Long countByPathAndDatasPkeyNum(String pathName,
	        List<?> dataList, DataAccessUtilLongKey util,
	        BuriSystemContext sysContext) {
		List<Long> longList = new ArrayList<Long>();
		String className = util.getClassName(dataList.get(0));
		Iterator<?> ite = dataList.iterator();
		while (ite.hasNext()) {
			Object data = ite.next();
			Long key = util.getKey(data);
			longList.add(key);
		}
		Long count =
		    countByPathKeys(className, longList, null, pathName, sysContext);
		return count;
	}

	protected Long countByPathKeys(String className, List<Long> longList,
	        List<String> strList, String pathName, BuriSystemContext sysContext) {
		Long count =
		    buriPathDataEntityService.getCountByPathKeys(
		        className,
		        longList,
		        strList,
		        pathName,
		        sysContext.getCallPath().getPathType());
		return count;
	}

	protected Long countByPathAndDatasPkeyVal(String pathName,
	        List<?> dataList, DataAccessUtilManyKey util,
	        BuriSystemContext sysContext) {
		List<String> strList = new ArrayList<String>();
		String className = util.getClassName(dataList.get(0));
		Iterator<?> ite = dataList.iterator();
		while (ite.hasNext()) {
			Object data = ite.next();
			String key = util.getKey(data);
			strList.add(key);
		}
		long count =
		    countByPathKeys(className, null, strList, pathName, sysContext);
		return count;
	}

	public List getBuriPathByDto(Object dto, DataAccessFactory factory,
	        BuriSystemContext sysContext) {
		DataAccessUtil util = factory.getDataAccessUtil(dto.getClass());
		String className = util.getClassName(dto);
		Long pathType = sysContext.getCallPath().getPathType();
		List<BuriPathDataEntity> infoList;
		if (util instanceof DataAccessUtilLongKey) {
			Long key = ((DataAccessUtilLongKey) util).getKey(dto);
			infoList =
			    buriPathDataEntityService.getListByPkeyNum(
			        className,
			        key,
			        pathType);
		} else {
			String key = ((DataAccessUtilManyKey) util).getKey(dto);
			infoList =
			    buriPathDataEntityService.getListByPkeyVal(
			        className,
			        key,
			        pathType);
		}
		List<BuriPath> pathList = convPathDataToBuriPath(infoList);
		return pathList;
	}

	protected List<BuriPath> convPathDataToBuriPath(
	        List<BuriPathDataEntity> infoList) {
		List<BuriPath> result = new ArrayList<BuriPath>();
		Iterator<BuriPathDataEntity> ite = infoList.iterator();
		while (ite.hasNext()) {
			BuriPathDataEntity dto = ite.next();
			BuriPath path =
			    new BuriPath(dto.pathName, dto.realPathName, dto.pathId);
			result.add(path);
		}
		return result;
	}

	public List getIdListByPathName(String pathName, DataAccessFactory factory,
	        BuriSystemContext sysContext) {
		List result;
		Class<?> tgtCalss = sysContext.getTargetDtoClass();
		DataAccessUtil util = factory.getDataAccessUtil(tgtCalss);
		if (util instanceof DataAccessUtilLongKey) {
			result =
			    getDataDtoList(
			        pathName,
			        (DataAccessUtilLongKey) util,
			        sysContext);
		} else {
			result =
			    getManyDataDtoList(
			        pathName,
			        (DataAccessUtilManyKey) util,
			        sysContext);
		}
		return result;
	}

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

	protected List<AbstractBuriPathDataEntity> getDataInfoListFromPathName(
	        String pathName, BuriSystemContext sysContext) {
		String className = null;
		if (sysContext.getTargetDtoClass() != null) {
			className =
			    classDefUtil.getClassName(sysContext.getTargetDtoClass());
		}
		Long pathType = sysContext.getCallPath().getPathType();
		List<BuriPathDataEntity> infoList =
		    buriPathDataEntityService.getListByPathName(
		        className,
		        pathName,
		        pathType);
		List<AbstractBuriPathDataEntity> result =
		    CollectionsUtil.newArrayList();
		result.addAll(infoList);
		return result;
	}

	protected List<?> getManyDataDtoList(String pathName,
	        DataAccessUtilManyKey dataUtil, BuriSystemContext sysContext) {
		List<AbstractBuriPathDataEntity> infoList =
		    getDataInfoListFromPathName(pathName, sysContext);
		Iterator<AbstractBuriPathDataEntity> ite = infoList.iterator();
		List<Object> result = new ArrayList<Object>();
		while (ite.hasNext()) {
			AbstractBuriPathDataEntity dto = ite.next();
			Object data = dataUtil.getObjectFromKey(dto.pkeyVal);
			result.add(data);
		}
		return result;
	}

	public List getDtoListByPathName(String pathName,
	        DataAccessFactory factory, BuriSystemContext sysContext) {
		List result = new ArrayList();
		DataAccessUtil util =
		    factory.getDataAccessUtil(sysContext.getTargetDtoClass());
		List keys = getIdListByPathName(pathName, factory, sysContext);
		if (keys.size() == 0) {
			return result;
		}
		if (util instanceof DataAccessUtilLongKey) {
			result = ((DataAccessUtilLongKey) util).get(keys);
		} else {
			result = ((DataAccessUtilManyKey) util).get(keys);
		}
		return result;
	}

	protected BuriDataEntity getBuriDataDto(Object argDto,
	        DataAccessFactory factory, BuriSystemContext sysContext) {
		BuriDataEntity findDto = new BuriDataEntity();
		setupPkey(findDto, argDto, factory);
		DataAccessUtil util = factory.getDataAccessUtil(argDto.getClass());
		findDto.dataType = util.getClassName(argDto);
		boolean hasDataId = false;
		if (util instanceof DataAccessUtilLongKey) {
			Long dataID = ((DataAccessUtilLongKey) util).getKey(argDto);
			findDto.pkeyNum = dataID;
			hasDataId = dataID == null ? false : true;
		} else {
			String dataID = ((DataAccessUtilManyKey) util).getKey(argDto);
			findDto.pkeyVal = dataID;
			hasDataId = dataID == null ? false : true;
		}
		List datas = new ArrayList();
		if (hasDataId) {
			datas = buriDataEntityService.getBuriDataFromDto(findDto);
		}
		BuriDataEntity dto = null;
		if (datas.size() == 0) {
			findDto.insertUserId = sysContext.getBuriUserId();
			findDto.tableName = util.getTableName(argDto);
			buriDataEntityService.insert(findDto);
			dto = findDto;
		} else {
			dto = (BuriDataEntity) datas.get(0);
		}
		return dto;
	}

	protected void setupPkey(BuriDataEntity dto, Object argDto,
	        DataAccessFactory factory) {
		DataAccessUtil util = factory.getDataAccessUtil(argDto.getClass());
		if (util == null) {
			throw new NullPointerException("DataAccessUtil Not Found TgtClass="
			    + argDto.getClass().toString()
			    + " / XPDL Miss!(Workflow variables)");
		}
		if (util instanceof DataAccessUtilLongKey) {
			Long dataID = ((DataAccessUtilLongKey) util).getKey(argDto);
			dto.pkeyNum = dataID;
		} else {
			String dataID = ((DataAccessUtilManyKey) util).getKey(argDto);
			dto.pkeyVal = dataID;
		}
	}

	public long getBuriDataId(DataAccessFactory factory,
	        BuriSystemContext sysContext) {
		Object argDto = sysContext.getUserContext().getData();
		if (sysContext.getDataId() == null) {
			BuriDataEntity dto = getBuriDataDto(argDto, factory, sysContext);
			assert dto != null;
			sysContext.setDataId(new Long(dto.dataId));
		}
		return sysContext.getDataId().longValue();
	}

	public void storeData(DataAccessFactory factory,
	        BuriSystemContext sysContext) {
		Object argDto = sysContext.getUserContext().getData();
		DataAccessUtil util = factory.getDataAccessUtil(argDto.getClass());
		util.Store(argDto);
	}

	public void updateBuriData(DataAccessFactory factory,
	        BuriSystemContext sysContext) {
		Object argDto = sysContext.getUserContext().getData();
		if (sysContext.getDataId() == null) {
			BuriDataEntity dto = getBuriDataDto(argDto, factory, sysContext);
			sysContext.setDataId(new Long(dto.dataId));
		} else {
			BuriDataEntity dto =
			    buriDataEntityService.getBuriData(sysContext
			        .getDataId()
			        .longValue());
			setupPkey(dto, argDto, factory);
			buriDataEntityService.update(dto);
		}
	}

	public Object getBuriData(long dataId, DataAccessFactory factory) {
		Object result = null;
		BuriDataEntity dto = buriDataEntityService.getBuriData(dataId);
		Class<?> tgtClass = ClassUtil.forName(dto.dataType);
		DataAccessUtil util = factory.getDataAccessUtil(tgtClass);
		if (util instanceof DataAccessUtilLongKey) {
			result =
			    ((DataAccessUtilLongKey) util).getObjectFromKey(dto.pkeyNum);
		} else {
			result =
			    ((DataAccessUtilManyKey) util).getObjectFromKey(dto.pkeyVal);
		}
		return result;
	}

	public BuriDataEntityService getBuriDataService() {
		return buriDataEntityService;
	}

	public void setBuriDataService(BuriDataEntityService buriDataEntityService) {
		this.buriDataEntityService = buriDataEntityService;
	}

	public BuriDataEntityService getBuriDataEntityService() {
		return buriDataEntityService;
	}

	public void setBuriDataEntityService(
	        BuriDataEntityService buriDataEntityService) {
		this.buriDataEntityService = buriDataEntityService;
	}

	public BuriIdListService getBuriIdListService() {
		return buriIdListService;
	}

	public void setBuriIdListService(BuriIdListService buriIdListService) {
		this.buriIdListService = buriIdListService;
	}

	public BuriPathDataEntityService getBuriPathDataEntityService() {
		return buriPathDataEntityService;
	}

	public void setBuriPathDataEntityService(
	        BuriPathDataEntityService buriPathDataEntityService) {
		this.buriPathDataEntityService = buriPathDataEntityService;
	}
}
