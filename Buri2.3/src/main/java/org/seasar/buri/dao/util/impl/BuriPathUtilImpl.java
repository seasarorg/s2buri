/*
 * 作成日: 2006/05/08
 *
 */
package org.seasar.buri.dao.util.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.seasar.buri.dao.BuriPathDao;
import org.seasar.buri.dao.util.BuriPathUtil;
import org.seasar.buri.dto.BuriPathEntityDto;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.util.packages.BuriExecProcess;

public class BuriPathUtilImpl implements BuriPathUtil {
    private BuriPathDao pathDao;

    public List getPathListByDataId(long dataID) {
        List entityList = pathDao.getBuriPathByDataID(dataID);
        List result = convEntytyToBuriPath(entityList);
        return result;
    }

    public BuriPath getBuriPathByID(long pathID) {
        BuriPathEntityDto dto = pathDao.getBuriPath(pathID);
        BuriPath path = new BuriPath(dto.getPathName(), dto.getRealPathName(), dto.getPathID(), dto
            .getPathType());
        return path;
    }

    public BuriPath getBuriPathFromRealPath(BuriPath srcPath) {
        assert srcPath != null;
        if (srcPath.getBuriPathId() != 0) {
            return srcPath;
        }
        assert srcPath.getRealPath().getActivity().size() > 0;
        String realPath = srcPath.getRealPath().getPlainName();
        BuriPathEntityDto dto = pathDao.getBuriPathFromRealPath(realPath);
        if (dto == null) {
            dto = new BuriPathEntityDto();
            dto.setPathName(srcPath.getPlainName());
            dto.setRealPathName(srcPath.getRealPath().getPlainName());
            dto.setPathType(srcPath.getPathType());
            pathDao.insert(dto);
        }
        BuriPath result = srcPath.setBuriPathId(dto.getPathID());
        return result;
    }

    public List getBuriPathFromPathName(BuriPath srcPath) {
        List entityList = pathDao
            .getBuriPathFromPath(srcPath.getPlainName(), srcPath.getPathType());
        List result = convEntytyToBuriPath(entityList);
        return result;
    }

    protected List convEntytyToBuriPath(List entityList) {
        List result = new ArrayList();
        Iterator ite = entityList.iterator();
        while (ite.hasNext()) {
            BuriPathEntityDto dto = (BuriPathEntityDto) ite.next();
            BuriPath path = new BuriPath(dto.getPathName(), dto.getRealPathName(), dto.getPathID(),
                dto.getPathType());
            result.add(path);
        }
        return result;
    }

    public long getPathType(BuriExecProcess process) {
        return process.getBuriWorkflowProcessType().getPathType();
    }

    public BuriPathDao getPathDao() {
        return pathDao;
    }

    public void setPathDao(BuriPathDao pathDao) {
        this.pathDao = pathDao;
    }

}
