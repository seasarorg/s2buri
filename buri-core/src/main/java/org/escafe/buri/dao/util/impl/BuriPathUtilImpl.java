/*
 * 作成日: 2006/05/08
 *
 */
package org.escafe.buri.dao.util.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.escafe.buri.dao.util.BuriPathUtil;
import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.entity.BuriPathEntity;
import org.escafe.buri.service.BuriPathEntityService;
import org.escafe.buri.util.packages.BuriExecProcess;

public class BuriPathUtilImpl implements BuriPathUtil {
	private BuriPathEntityService buriPathEntityService;

	public List<BuriPath> getPathListByDataId(Long dataId) {
		List<BuriPathEntity> entityList =
		    buriPathEntityService.getBuriPathByDataId(dataId);
		List<BuriPath> result = convEntytyToBuriPath(entityList);
		return result;
	}

	public BuriPath getBuriPathById(Long pathId) {
		BuriPathEntity dto = buriPathEntityService.getBuriPath(pathId);
		BuriPath path =
		    new BuriPath(
		        dto.pathName,
		        dto.realPathName,
		        dto.pathId,
		        dto.pathType);
		return path;
	}

	public BuriPath getBuriPathFromRealPath(BuriPath srcPath) {
		assert srcPath != null;
		if (srcPath.getBuriPathId() != 0) {
			return srcPath;
		}
		assert srcPath.getRealPath().getActivity().size() > 0;
		String realPath = srcPath.getRealPath().getPlainName();
		BuriPathEntity dto =
		    buriPathEntityService.getBuriPathFromRealPath(realPath);
		if (dto == null) {
			dto = new BuriPathEntity();
			dto.pathName = srcPath.getPlainName();
			dto.realPathName = srcPath.getRealPath().getPlainName();
			dto.pathType = srcPath.getPathType();
			buriPathEntityService.insert(dto);
		}
		BuriPath result = srcPath.setBuriPathId(dto.pathId);
		return result;
	}

	public List<BuriPath> getBuriPathFromPathName(BuriPath srcPath) {
		List<BuriPathEntity> entityList =
		    buriPathEntityService.getBuriPathFromPath(
		        srcPath.getPlainName(),
		        srcPath.getPathType());
		List<BuriPath> result = convEntytyToBuriPath(entityList);
		return result;
	}

	protected List<BuriPath> convEntytyToBuriPath(
	        List<BuriPathEntity> entityList) {
		List<BuriPath> result = new ArrayList<BuriPath>();
		Iterator<BuriPathEntity> ite = entityList.iterator();
		while (ite.hasNext()) {
			BuriPathEntity dto = ite.next();
			BuriPath path =
			    new BuriPath(
			        dto.pathName,
			        dto.realPathName,
			        dto.pathId,
			        dto.pathType);
			result.add(path);
		}
		return result;
	}

	public long getPathType(BuriExecProcess process) {
		return process.getBuriWorkflowProcessType().getPathType();
	}

	public BuriPathEntityService getBuriPathEntityService() {
		return buriPathEntityService;
	}

	public void setBuriPathEntityService(
	        BuriPathEntityService buriPathEntityService) {
		this.buriPathEntityService = buriPathEntityService;
	}
}
