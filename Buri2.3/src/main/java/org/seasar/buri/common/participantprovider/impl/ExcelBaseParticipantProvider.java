/*
 * 作成日: 2006/07/12
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
import org.seasar.buri.engine.IdentityInfo;
import org.seasar.buri.engine.ParticipantContext;
import org.seasar.buri.engine.ParticipantProvider;
import org.seasar.framework.container.S2Container;

/**
 * Excel上で権限主体に関する設定を一元管理する権限主体プロバイダです。
 * 
 * @author $Author$
 */
public class ExcelBaseParticipantProvider implements ParticipantProvider {

    private List<BuriExcelPrtiPrvidrRootDto> ppList = new ArrayList<BuriExcelPrtiPrvidrRootDto>();
    private ExcelPrtiPrvidrParser parser;
    private S2Container container;

    public void loadFromResource(String fileName) {
        List<BuriExcelPrtiPrvidrRootDto> pps = parser.loadFromResource(fileName);
        ppList.addAll(pps);
    }

    public void loadFromFile(String fileName) {
        List<BuriExcelPrtiPrvidrRootDto> pps = parser.loadFromFile(fileName);
        ppList.addAll(pps);
    }

    private BuriExcelPrtiPrvidrRootDto getRootDto() {
        Iterator<BuriExcelPrtiPrvidrRootDto> ite = ppList.iterator();
        while (ite.hasNext()) {
            BuriExcelPrtiPrvidrRootDto dto = ite.next();
            Date now = new Date();
            if (DateUtil.compare(dto.getFromDate(), now) <= 0
                    && DateUtil.compare(now, dto.getToDate()) < 0) {
                return dto;
            }
        }
        return null;
    }

    public IdentityInfo getUserId(Object userData) {
        IdentityInfo userId = new IdentityInfo();
        BuriExcelPrtiPrvidrRootDto dto = getRootDto();
        Map<String, Object> context = new HashMap<String, Object>();
        context.put("userData", userData);
        ScriptProcessor processor = new ScriptProcessor();
        if (dto.getConvId() != null) {
            Object idObj = processor.getValue(dto.getConvId(), container, context);
            userId.setIdNumber((Long) idObj);
        }
        if (dto.getConvName() != null) {
            Object nameObj = processor.getValue(dto.getConvName(), container, context);
            userId.setIdString((String) nameObj);
        }
        return userId;
    }

    public boolean hasAuthority(ParticipantContext context) {
        BuriExcelPrtiPrvidrRootDto rootDto = getRootDto();
        BuriExcelPrtiPrvidrItemDto itemDto = rootDto.getHierarchy().get(getItemKey(context));
        if (itemDto == null) {
            return false;
        }
        if (itemDto.getRoleNames().contains(context.getParticipantName())) {
            return true;
        }
        return false;
    }

    public Object getUserData(IdentityInfo appUserId) {
        BuriExcelPrtiPrvidrRootDto dto = getRootDto();
        Map<String, Object> context = new HashMap<String, Object>();
        context.put("userIDNum", appUserId.getIdNumber()); // FIXME:deprecated
        context.put("userIDVal", appUserId.getIdString()); // FIXME:deprecated
        context.put("userIDNumber", appUserId.getIdNumber()); // FIXME:deprecated
        context.put("userIDString", appUserId.getIdString()); // FIXME:deprecated
        context.put("userIdNumber", appUserId.getIdNumber());
        context.put("userIdString", appUserId.getIdString());
        ScriptProcessor processor = new ScriptProcessor();
        Object obj = processor.getValue(dto.getConvObj(), container, context);
        return obj;
    }

    private List<BuriExcelPrtiPrvidrItemDto> findParticipantName(ParticipantContext context) {
        BuriExcelPrtiPrvidrRootDto rootDto = getRootDto();
        BuriExcelPrtiPrvidrItemDto itemDto = rootDto.getHierarchy().get(getItemKey(context));
        if (itemDto == null) {
            return new ArrayList<BuriExcelPrtiPrvidrItemDto>(0);
        }
        List<BuriExcelPrtiPrvidrItemDto> result = findLeft(itemDto, context.getParticipantName());
        result.addAll(findRight(itemDto, context.getParticipantName()));
        return result;
    }

    private String getItemKey(ParticipantContext context) {
        return getItemDto(context).getItemKey();
    }

    private BuriExcelPrtiPrvidrItemDto getItemDto(ParticipantContext context) {
        IdentityInfo appUserId = context.getUserId();
        BuriExcelPrtiPrvidrItemDto itemDto = new BuriExcelPrtiPrvidrItemDto();
        itemDto.setId(appUserId.getIdNumber());
        itemDto.setName(appUserId.getIdString());
        return itemDto;
    }

    private List<BuriExcelPrtiPrvidrItemDto> findLeft(BuriExcelPrtiPrvidrItemDto itemDto,
            String participantName) {
        List<BuriExcelPrtiPrvidrItemDto> result = new ArrayList<BuriExcelPrtiPrvidrItemDto>();
        for (BuriExcelPrtiPrvidrItemDto leftDto : itemDto.getLefts()) {
            if (leftDto.getRoleNames().contains(participantName)) {
                result.add(leftDto);
            }
            result.addAll(findLeft(leftDto, participantName));
        }
        return result;
    }

    private List<BuriExcelPrtiPrvidrItemDto> findRight(BuriExcelPrtiPrvidrItemDto itemDto,
            String participantName) {
        List<BuriExcelPrtiPrvidrItemDto> result = new ArrayList<BuriExcelPrtiPrvidrItemDto>();
        for (BuriExcelPrtiPrvidrItemDto rightDto : itemDto.getRights()) {
            if (rightDto.getRoleNames().contains(participantName)) {
                result.add(rightDto);
            }
            result.addAll(findRight(rightDto, participantName));
        }
        return result;
    }

    public List<IdentityInfo> getAuthorizedUserIds(ParticipantContext context) {
        List<BuriExcelPrtiPrvidrItemDto> itemDtos = findParticipantName(context);
        if (hasAuthority(context)) { // 実行ユーザも適切な権限を持つのであれば追加する
            itemDtos.add(getItemDto(context));
        }
        List<IdentityInfo> result = new ArrayList<IdentityInfo>();
        for (BuriExcelPrtiPrvidrItemDto itemDto : itemDtos) {
            IdentityInfo info = new IdentityInfo();
            info.setIdNumber(itemDto.getId());
            if (itemDto.getName() != null && !itemDto.getName().equals("")) { // nameなし対応
                info.setIdString(itemDto.getName());
            }
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
