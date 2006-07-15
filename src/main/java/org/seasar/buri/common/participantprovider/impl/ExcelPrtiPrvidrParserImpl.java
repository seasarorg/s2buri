/*
 * çÏê¨ì˙: 2006/07/13
 *
 */
package org.seasar.buri.common.participantprovider.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jxl.Cell;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

import org.seasar.buri.common.participantprovider.ExcelPrtiPrvidrParser;
import org.seasar.buri.common.util.ExcelIOUtil;
import org.seasar.framework.util.StringUtil;

public class ExcelPrtiPrvidrParserImpl implements ExcelPrtiPrvidrParser {
    
    public List loadFromResource(String fileName) {
        Workbook workbook =  ExcelIOUtil.getWorkbook(fileName);
        return loadFromWorkbool(workbook);
    }
    
    public List loadFromFile(String fileName) {
        Workbook workbook =  ExcelIOUtil.getWorkbookNoResource(fileName);
        return loadFromWorkbool(workbook);
    }
    
    protected List loadFromWorkbool(Workbook workbook) {
        List ppList = new ArrayList();
        Sheet sheets[] = workbook.getSheets();
        for(int i=0;i < sheets.length ; i++) {
            BuriExcelPrtiPrvidrRootDto dto = loadFromSheet(sheets[i]);
            if(dto != null) {
                ppList.add(dto);
            }
        }
        return ppList;
    }
    
    protected BuriExcelPrtiPrvidrRootDto loadFromSheet(Sheet sheet) {
        int mode = 0;
        BuriExcelPrtiPrvidrRootDto dto = new BuriExcelPrtiPrvidrRootDto();
        for(int i=0 ; i < sheet.getRows() ; i++) {
            Cell cell = sheet.getCell(0,i);
            String contents = cell.getContents();
            mode = checkMode(contents,mode);
            switch (mode) {
            case 1:
                parseLimit(dto,sheet,i);
                break;
            case 2:
                parseConvert(dto,sheet,i);
                break;
            case 3:
                parseContent(dto,sheet,i);
                break;

            default:
                break;
            }
        }
        if(mode==0) {
            return null;
        }
        return dto;
    }
    
    protected void parseContent(BuriExcelPrtiPrvidrRootDto dto,Sheet sheet,int row) {
        if(dto.getHierarchyHed().size() == 0) {
            parseContentHed(dto,sheet,row);
        } else {
            Iterator ite = dto.getHierarchyHed().iterator();
            while(ite.hasNext()) {
                BuriExcelPrtiPrvidrHedDto hedDto = (BuriExcelPrtiPrvidrHedDto)ite.next();
                BuriExcelPrtiPrvidrItemDto item = getBuriExcelPrtiPrvidrItemDto(dto,hedDto,sheet,row);
                if(item!=null) {
                    setupLeftItem(dto,hedDto,item);
                    dto.getHierList().add(hedDto.getSeq(),item);
                    for( int i = dto.getHierList().size()-1 ; hedDto.getSeq() < i  ; i--) {
                        dto.getHierList().remove(i);
                    }
                    dto.getHierarchy().put(item.getItemKey(),item);
                }
            }
        }
    }
    
    protected void setupLeftItem(BuriExcelPrtiPrvidrRootDto dto,BuriExcelPrtiPrvidrHedDto hedDto,BuriExcelPrtiPrvidrItemDto item) {
        if( hedDto.getSeq() > 0) {
            BuriExcelPrtiPrvidrItemDto left = (BuriExcelPrtiPrvidrItemDto)dto.getHierList().get(hedDto.getSeq()-1);
            left.getRights().add(item);
            item.getLefts().add(left);
        }
    }
    
    protected BuriExcelPrtiPrvidrItemDto getBuriExcelPrtiPrvidrItemDto(BuriExcelPrtiPrvidrRootDto dto,BuriExcelPrtiPrvidrHedDto hedDto,Sheet sheet,int row) {
        int idCol = hedDto.getIdPos();
        Cell idCell = sheet.getCell(idCol,row);
        if(idCell.getContents().equalsIgnoreCase("id")) {
            return null;
        }
        int nameCol = hedDto.getNamePos();
        Cell nameCell = sheet.getCell(nameCol,row);
        String id = idCell.getContents();
        String name = nameCell.getContents();
        if((id+name).length() == 0) {
            return null;
        }
        BuriExcelPrtiPrvidrItemDto item = new BuriExcelPrtiPrvidrItemDto();
        item.setId(id);
        item.setName(name);
        if(dto.getHierarchy().containsKey(item.getItemKey())) {
            item = (BuriExcelPrtiPrvidrItemDto)dto.getHierarchy().get(item.getItemKey());
        }
        item.getRoles().add(hedDto.getRoleName());
        return item;
    }
    
    protected void parseContentHed(BuriExcelPrtiPrvidrRootDto dto,Sheet sheet,int row) {
        String title = "";
        int seq = 0;
        BuriExcelPrtiPrvidrHedDto hedDto = null;
        for(int i =1 ; i < sheet.getColumns() ; i++) {
            Cell nameCell = sheet.getCell(i,row);
            if( ! StringUtil.isEmpty(nameCell.getContents())) {
                title = nameCell.getContents();
                hedDto = new BuriExcelPrtiPrvidrHedDto();
                hedDto.setRoleName(title);
                hedDto.setSeq(seq);
                dto.getHierarchyHed().add(hedDto);
                seq = seq + 1;
            }
            Cell typeCell = sheet.getCell(i,row+1);
            if("id".equalsIgnoreCase(typeCell.getContents())) {
                hedDto.setIdPos(i);
            } else if("name".equalsIgnoreCase(typeCell.getContents())) {
                hedDto.setNamePos(i);
            }
        }
    }
    
    protected void parseConvert(BuriExcelPrtiPrvidrRootDto dto,Sheet sheet,int row) {
        Cell keyCell = sheet.getCell(1,row);
        Cell valCell = sheet.getCell(2,row);
        if(StringUtil.isEmpty(keyCell.getContents())) {
            return;
        }
        if(StringUtil.isEmpty(valCell.getContents())) {
            return;
        }
        if(keyCell.getContents().equalsIgnoreCase("id")) {
            dto.setConvId(valCell.getContents());
        }
        if(keyCell.getContents().equalsIgnoreCase("name")) {
            dto.setConvName(valCell.getContents());
        }
        if(keyCell.getContents().equalsIgnoreCase("obj")) {
            dto.setConvObj(valCell.getContents());
        }
    }
    
    protected void parseLimit(BuriExcelPrtiPrvidrRootDto dto,Sheet sheet,int row) {
        Cell cell = sheet.getCell(2,row);
        if(StringUtil.isEmpty(cell.getContents())) {
            return;
        }
        Cell keyCell = sheet.getCell(1,row);
        DateCell dateCell = (DateCell)cell;
        if(keyCell.getContents().equalsIgnoreCase("from")) {
            dto.setFromDate(dateCell.getDate());
        } else if(keyCell.getContents().equalsIgnoreCase("to")) {
            dto.setToDate(dateCell.getDate());
        }
    }
    
    protected int checkMode(String contents,int nowMode) {
        int mode = nowMode;
        if(contents.equalsIgnoreCase("limit")) {
            mode = 1;
        } else if(contents.equalsIgnoreCase("convert")) {
            mode = 2;
        } else if(contents.equalsIgnoreCase("type")) {
            mode = 3;
        }
        return mode;
    }

}
