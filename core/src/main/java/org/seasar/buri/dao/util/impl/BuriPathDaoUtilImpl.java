/*
 * çÏê¨ì˙: 2005/06/30
 *
 */
package org.seasar.buri.dao.util.impl;

import java.util.HashMap;
import java.util.Map;

import org.seasar.buri.context.ContextUtil;
import org.seasar.buri.dao.BuriPathDao;
import org.seasar.buri.dao.util.BuriPathDaoUtil;
import org.seasar.buri.dto.BuriPathEntityDto;
import org.seasar.buri.engine.BuriParticipant;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.exception.BuriPathNotCorrectedException;
import org.seasar.buri.xpdl.util.WorkFlowsUtil;


public class BuriPathDaoUtilImpl implements BuriPathDaoUtil {
    
    private BuriPathDao buriPathDao;
    private ContextUtil contextUtil;
    private WorkFlowsUtil flowsUtil;
    private Map pathCache = new HashMap();
    private Map pathIDCache = new HashMap();
    
    public void setup(BuriPath path) {
        if(path != null) {
            if( path.getBuriPathID() != 0) {
                return;
            }
            BuriParticipant participant = contextUtil.getLocalContext().getBuriParticipant();
            flowsUtil.getActivityTagSelectFromPathAndParticipant(path,participant);
            if( path.getBuriPathID() != 0) {
                return;
            }
            if(path.isCorrect()) {
                getBuriPath(path);
            }
        }
    }

    public BuriPath getBuriPath(BuriPath path) {
        if( ! path.isCorrect()) {
            throw new BuriPathNotCorrectedException(path);
        }
        BuriPath resultPath = null;
        if(path.getBuriPathID()==0) {
            resultPath = createBuriPath(path);
        } else {
            resultPath = getBuriPath(path.getBuriPathID());
        }
        return resultPath;
    }
    
    private BuriPath createBuriPath(BuriPath path) {
        BuriPath resultPath = null;
        BuriParticipant participant = contextUtil.getLocalContext().getBuriParticipant();
        flowsUtil.getActivityTagSelectFromPathAndParticipant(path,participant);
        if( path.getBuriPathID() != 0) {
            return path;
        }
        String realPathName = path.getRealPath().getPlainName();
        if(pathCache.containsKey(realPathName)) {
            resultPath = (BuriPath)pathCache.get(realPathName);
            path.setBuriPathID(resultPath.getBuriPathID());
            BuriPath result = new BuriPath(path.getPlainName(),path.getRealPath().getPlainName());
            result.setBuriPathID(path.getBuriPathID());
            return result;
        }
        BuriPathEntityDto pathDto = buriPathDao.getBuriPathFromRealPath(realPathName);
        if(pathDto == null) {
//            String pathName = path.getPlainName();
//            pathDto = buriPathDao.getBuriPathFromPath(pathName);
//            if(pathDto == null) {
                pathDto = createBuriPathEntityDto(path);
//            }
        }
        resultPath = convertBuriPathEntityDtoToBuriPath(pathDto);
        path.setBuriPathID(resultPath.getBuriPathID());
        if(path.getBuriPathID()!=0) {
            pathCache.put(realPathName,resultPath);
            pathIDCache.put(new Long(resultPath.getBuriPathID()),resultPath);
        }
        return resultPath;
    }

    private BuriPathEntityDto createBuriPathEntityDto(BuriPath path) {
        BuriPathEntityDto pathDto = new BuriPathEntityDto();
        pathDto.setPathName(path.getPlainName());
        pathDto.setRealPathName(path.getRealPath().getPlainName());
        buriPathDao.insert(pathDto);
        return pathDto;
    }

    private BuriPath convertBuriPathEntityDtoToBuriPath(BuriPathEntityDto pathDto) {
        BuriPath path = new BuriPath(pathDto.getPathName(),pathDto.getRealPathName());
        path.setBuriPathID(pathDto.getPathID());
        return path;
    }

    public BuriPath getBuriPath(long buriPathID) {
        BuriPathEntityDto pathDto = buriPathDao.getBuriPath(buriPathID);
        BuriPath path = convertBuriPathEntityDtoToBuriPath(pathDto);
        return path;
    }

    public BuriPathDao getBuriPathDao() {
        return buriPathDao;
    }

    public void setBuriPathDao(BuriPathDao buriPathDao) {
        this.buriPathDao = buriPathDao;
    }

    public ContextUtil getContextUtil() {
        return contextUtil;
    }

    public void setContextUtil(ContextUtil contextUtil) {
        this.contextUtil = contextUtil;
    }

    public WorkFlowsUtil getFlowsUtil() {
        return flowsUtil;
    }

    public void setFlowsUtil(WorkFlowsUtil flowsUtil) {
        this.flowsUtil = flowsUtil;
    }

}
