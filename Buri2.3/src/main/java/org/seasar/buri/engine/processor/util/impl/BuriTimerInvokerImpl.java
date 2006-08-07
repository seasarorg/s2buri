/*
 * çÏê¨ì˙: 2006/06/26
 *
 */
package org.seasar.buri.engine.processor.util.impl;

import org.seasar.buri.dao.BuriPathDataUserDao;
import org.seasar.buri.dao.util.BuriDataUtil;
import org.seasar.buri.dao.util.BuriUserUtil;
import org.seasar.buri.dto.BuriPathDataEntityDto;
import org.seasar.buri.dto.BuriPathDataUserEntityDto;
import org.seasar.buri.engine.processor.BuriAutoSelectProcessor;
import org.seasar.buri.engine.processor.BuriProcessorInfo;
import org.seasar.buri.engine.processor.util.BuriTimerInvoker;
import org.seasar.coffee.dataaccess.DataAccessFactory;

public class BuriTimerInvokerImpl implements BuriTimerInvoker {
    private BuriAutoSelectProcessor processor;
    private BuriDataUtil dataUtil;
    private BuriUserUtil userUtil;
    private BuriPathDataUserDao dataUserDao;
    
    public void invoke(BuriPathDataEntityDto callDto) {
        DataAccessFactory accessFactory = processor.getDataAccessFactory(callDto.getPathName());
        BuriProcessorInfo info = new BuriProcessorInfo();
        info.put("autoAction",Boolean.TRUE);
        if(processor.isSimpleProcessor(callDto.getPathName())) {
            simpleCall(callDto,accessFactory,info);
        }
        if(processor.isStdProcessor(callDto.getPathName())) {
            stdCall(callDto,accessFactory,info);
        }
    }
    
    protected void simpleCall(BuriPathDataEntityDto callDto,DataAccessFactory accessFactory,BuriProcessorInfo info) {
        Object argDto = getArgDto(callDto,accessFactory);
        processor.toNextStatus(callDto.getPathName(),argDto,null,info);
    }
    
    protected Object getArgDto(BuriPathDataEntityDto callDto,DataAccessFactory accessFactory) {
        Object argDto = dataUtil.getBuriData(callDto.getDataID(),accessFactory);
        return argDto;
    }
    
    protected Object getUserData(BuriPathDataEntityDto callDto,DataAccessFactory accessFactory) {
        BuriPathDataUserEntityDto dto = dataUserDao.getDto(callDto.getStateID());
        Object userData = userUtil.getUserData(accessFactory,dto.getBuriUserID(),dto.getUserIDNum(),dto.getUserIDVal());
        return userData;
    }
    
    protected void stdCall(BuriPathDataEntityDto callDto,DataAccessFactory accessFactory,BuriProcessorInfo info) {
        Object argDto = getArgDto(callDto,accessFactory);
        Object userData = getUserData(callDto,accessFactory);
        processor.toNextStatus(callDto.getPathName(),argDto,userData,info);
    }

    public BuriPathDataUserDao getDataUserDao() {
        return dataUserDao;
    }

    public void setDataUserDao(BuriPathDataUserDao dataUserDao) {
        this.dataUserDao = dataUserDao;
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
    
    
    
}
