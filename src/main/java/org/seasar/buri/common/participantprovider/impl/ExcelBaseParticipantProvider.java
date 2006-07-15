/*
 * çÏê¨ì˙: 2006/07/12
 *
 */
package org.seasar.buri.common.participantprovider.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.starlogic.util.datetime.DateUtil;

import org.seasar.buri.common.participantprovider.ExcelPrtiPrvidrParser;
import org.seasar.buri.common.util.ScriptProcessor;
import org.seasar.buri.engine.ParticipantContext;
import org.seasar.buri.engine.ParticipantProvider;
import org.seasar.buri.engine.RoleInfo;
import org.seasar.framework.container.S2Container;

public class ExcelBaseParticipantProvider implements ParticipantProvider {
    private List ppList = new ArrayList();
    private ExcelPrtiPrvidrParser parser;
    private S2Container container;
    
    public void loadFromResource(String fileName) {
        List pps = parser.loadFromResource(fileName);
        ppList.addAll(pps);
    }
    
    public void loadFromFile(String fileName) {
        List pps = parser.loadFromFile(fileName);
        ppList.addAll(pps);
    }
    
    public BuriExcelPrtiPrvidrRootDto getRootDto() {
        Iterator ite = ppList.iterator();
        while(ite.hasNext()) {
            BuriExcelPrtiPrvidrRootDto dto = (BuriExcelPrtiPrvidrRootDto)ite.next();
            Date now = new Date();
            if(DateUtil.compare(dto.getFromDate(),now) <= 0 && DateUtil.compare(now,dto.getToDate()) < 0 ) {
                return dto;
            }
        }
        return null;
    }

    public boolean isUserInRole(Object userData, String participantName, String participantType) {
        BuriExcelPrtiPrvidrRootDto rootDto = getRootDto();
        BuriExcelPrtiPrvidrItemDto itemDto = new BuriExcelPrtiPrvidrItemDto();
        itemDto.setId(getUserIDString(userData, participantType));
        itemDto.setName(getUserIDString(userData, participantType));
        itemDto = (BuriExcelPrtiPrvidrItemDto)rootDto.getHierarchy().get(itemDto.getItemKey());
        if(itemDto == null) {
            return false;
        }
        if(itemDto.getRoles().contains(participantName)) {
            return true;
        }
        return false;
    }

    public String getUserIDString(Object userData, String participantType) {
        BuriExcelPrtiPrvidrRootDto dto = getRootDto();
        Map context = new HashMap();
        context.put("userData",userData);
        ScriptProcessor processor = new ScriptProcessor();
        Object obj = processor.getValue(dto.getConvName(),container,context);
        return (String)obj;
    }

    public Long getUserIDNum(Object userData, String participantType) {
        BuriExcelPrtiPrvidrRootDto dto = getRootDto();
        Map context = new HashMap();
        context.put("userData",userData);
        ScriptProcessor processor = new ScriptProcessor();
        Object obj = processor.getValue(dto.getConvId(),container,context);
        return (Long)obj;
    }

    public Object getUserData(Long userIDNum, String userIDString) {
        BuriExcelPrtiPrvidrRootDto dto = getRootDto();
        Map context = new HashMap();
        context.put("userIDNum",userIDNum);
        context.put("userIDVal",userIDString);
        ScriptProcessor processor = new ScriptProcessor();
        Object obj = processor.getValue(dto.getConvObj(),container,context);
        return obj;
    }

    public boolean hasRoleUser(ParticipantContext context) {
        List result = findParticipantName(context);
        if(result.size() == 0) {
            return false;
        }
        return true;
    }
    
    protected List findParticipantName(ParticipantContext context) {
        List result = new ArrayList();
        BuriExcelPrtiPrvidrRootDto rootDto = getRootDto();
        BuriExcelPrtiPrvidrItemDto itemDto = new BuriExcelPrtiPrvidrItemDto();
        itemDto.setId(context.getActionUserIdNum().toString());
        itemDto.setName(context.getActionUserIdVal());
        itemDto = (BuriExcelPrtiPrvidrItemDto)rootDto.getHierarchy().get(itemDto.getItemKey());
        if(itemDto == null) {
            return result;
        }
        result = findLeft(itemDto,context.getParticipantName());
        result.addAll( findRight(itemDto,context.getParticipantName()) );
        return result;
    }
    
    protected List findLeft(BuriExcelPrtiPrvidrItemDto itemDto,String participantName) {
        Iterator ite = itemDto.getLefts().iterator();
        List result = new ArrayList();
        while(ite.hasNext()) {
            BuriExcelPrtiPrvidrItemDto leftDto = (BuriExcelPrtiPrvidrItemDto)ite.next();
            if(leftDto.getRoles().contains(participantName)) {
                result.add(leftDto);
            }
            result.addAll(findLeft(leftDto,participantName));
        }
        return result;
    }
    
    protected List findRight(BuriExcelPrtiPrvidrItemDto itemDto,String participantName) {
        Iterator ite = itemDto.getRights().iterator();
        List result = new ArrayList();
        while(ite.hasNext()) {
            BuriExcelPrtiPrvidrItemDto rightDto = (BuriExcelPrtiPrvidrItemDto)ite.next();
            if(rightDto.getRoles().contains(participantName)) {
                result.add(rightDto);
            }
            result.addAll(findRight(rightDto,participantName));
        }
        return result;
    }

    public RoleInfo getSingleUser(ParticipantContext context) {
        List result = getUser(context);
        if(result.size() == 0) {
            return null;
        }
        return (RoleInfo)result.get(0);
    }

    public List getUser(ParticipantContext context) {
        List partiList = findParticipantName(context);
        List result = new ArrayList();
        Iterator ite = partiList.iterator();
        while(ite.hasNext()) {
            BuriExcelPrtiPrvidrItemDto itemDto = (BuriExcelPrtiPrvidrItemDto)ite.next();
            RoleInfo info = new RoleInfo();
            info.setIdNum(new Long(itemDto.getId()));
            info.setIdVar(itemDto.getName());
            result.add(info);
        }
        return result;
    }

    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }

    public ExcelPrtiPrvidrParser getParser() {
        return parser;
    }

    public void setParser(ExcelPrtiPrvidrParser parser) {
        this.parser = parser;
    }

    
    
}
