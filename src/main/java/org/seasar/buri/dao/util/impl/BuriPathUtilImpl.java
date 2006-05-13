/*
 * ì¬“ú: 2006/05/08
 *
 */
package org.seasar.buri.dao.util.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.seasar.buri.dao.util.BuriPathUtil;
import org.seasar.buri.dao.BuriPathDao;
import org.seasar.buri.dto.BuriPathEntityDto;
import org.seasar.buri.engine.BuriPath;

public class BuriPathUtilImpl implements BuriPathUtil {
    private BuriPathDao pathDao;
    
    
    public BuriPath getBuriPathFromRealPath(BuriPath srcPath) {
        assert srcPath != null;
        if(srcPath.getBuriPathID() != 0 ) {
            return srcPath;
        }
        assert srcPath.getRealPath().getActivity().size() > 0;
        BuriPathEntityDto dto = pathDao.getBuriPathFromRealPath(srcPath.getRealPath().getPlainName());
        if(dto==null) {
            dto = new BuriPathEntityDto();
            dto.setPathName(srcPath.getPlainName());
            dto.setRealPathName(srcPath.getRealPath().getPlainName());
            pathDao.insert(dto);
        }
        BuriPath result = srcPath.setBuriPathID(dto.getPathID());
        return result;
    }
    
    public List getBuriPathFromPathName(BuriPath srcPath) {
        List result = new ArrayList();
        List entityList = pathDao.getBuriPathFromPath(srcPath.getPlainName());
        Iterator ite = entityList.iterator();
        while(ite.hasNext()) {
            BuriPathEntityDto dto = (BuriPathEntityDto)ite.next();
            BuriPath path = new BuriPath(dto.getPathName(),dto.getRealPathName(),dto.getPathID());
            result.add(path);
        }
        return result;
    }

    public BuriPathDao getPathDao() {
        return pathDao;
    }

    public void setPathDao(BuriPathDao pathDao) {
        this.pathDao = pathDao;
    }
    
}
