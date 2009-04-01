/*
 * 作成日: 2006/07/12
 *
 */
package org.escafe.buri.common.participantprovider.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.starlogic.util.datetime.DateUtil;

public class BuriExcelPrtiPrvidrRootDto {
    private Date fromDate = DateUtil.getSQLMinDate();
    private Date toDate = DateUtil.getSQLMaxDate();
    private String convId;
    private String convName;
    private String convObj;
    private List<BuriExcelPrtiPrvidrHedDto> hierarchyHed = new ArrayList<BuriExcelPrtiPrvidrHedDto>();
    private Map<String, BuriExcelPrtiPrvidrItemDto> hierarchy = new HashMap<String, BuriExcelPrtiPrvidrItemDto>();
    private List<BuriExcelPrtiPrvidrItemDto> hierList = new ArrayList<BuriExcelPrtiPrvidrItemDto>();

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getConvId() {
        return convId;
    }

    public void setConvId(String convId) {
        this.convId = convId;
    }

    public String getConvName() {
        return convName;
    }

    public void setConvName(String convName) {
        this.convName = convName;
    }

    public Map<String, BuriExcelPrtiPrvidrItemDto> getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(Map<String, BuriExcelPrtiPrvidrItemDto> hierarchy) {
        this.hierarchy = hierarchy;
    }

    public List<BuriExcelPrtiPrvidrItemDto> getHierList() {
        return hierList;
    }

    public void setHierList(List<BuriExcelPrtiPrvidrItemDto> hierList) {
        this.hierList = hierList;
    }

    public List<BuriExcelPrtiPrvidrHedDto> getHierarchyHed() {
        return hierarchyHed;
    }

    public void setHierarchyHed(List<BuriExcelPrtiPrvidrHedDto> hierarchyHed) {
        this.hierarchyHed = hierarchyHed;
    }

    public String getConvObj() {
        return convObj;
    }

    public void setConvObj(String convObj) {
        this.convObj = convObj;
    }

    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("fromDate=").append(fromDate);
        buff.append("/toDate=").append(toDate);
        buff.append("/convId=").append(convId);
        buff.append("/convName=").append(convName);
        buff.append("/convObj=").append(convObj);
        buff.append("/hierarchyHed=").append(hierarchyHed);
        buff.append("/hierarchy=").append(hierarchy);
        buff.append("/hierList=").append(hierList);
        buff.append("]");
        return buff.toString();
    }
}
