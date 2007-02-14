/*
 * 作成日: 2006/07/13
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
import jxl.biff.EmptyCell;

import org.seasar.buri.common.participantprovider.ExcelPrtiPrvidrParser;
import org.seasar.buri.common.util.ExcelIOUtil;
import org.seasar.framework.util.StringUtil;

public class ExcelPrtiPrvidrParserImpl implements ExcelPrtiPrvidrParser {

    public List<BuriExcelPrtiPrvidrRootDto> loadFromResource(String fileName) {
        Workbook workbook = ExcelIOUtil.getWorkbook(fileName);
        return loadFromWorkbool(workbook);
    }

    public List<BuriExcelPrtiPrvidrRootDto> loadFromFile(String fileName) {
        Workbook workbook = ExcelIOUtil.getWorkbookNoResource(fileName);
        return loadFromWorkbool(workbook);
    }

    protected List<BuriExcelPrtiPrvidrRootDto> loadFromWorkbool(Workbook workbook) {
        List<BuriExcelPrtiPrvidrRootDto> ppList = new ArrayList<BuriExcelPrtiPrvidrRootDto>();
        Sheet sheets[] = workbook.getSheets();
        for (int i = 0; i < sheets.length; i++) {
            BuriExcelPrtiPrvidrRootDto dto = loadFromSheet(sheets[i]);
            if (dto != null) {
                ppList.add(dto);
            }
        }
        return ppList;
    }

    protected BuriExcelPrtiPrvidrRootDto loadFromSheet(Sheet sheet) {
        int mode = 0;
        BuriExcelPrtiPrvidrRootDto dto = new BuriExcelPrtiPrvidrRootDto();
        for (int i = 0; i < sheet.getRows(); i++) {
            Cell cell = sheet.getCell(0, i);
            String contents = cell.getContents();
            mode = checkMode(contents, mode);
            switch (mode) {
                case 1:
                    parseValid(dto, sheet, i);
                    break;
                case 2:
                    parseConvert(dto, sheet, i);
                    break;
                case 3:
                    parseContent(dto, sheet, i);
                    break;

                default:
                    break;
            }
        }
        if (mode == 0) {
            return null;
        }
        return dto;
    }

    protected void parseContent(BuriExcelPrtiPrvidrRootDto dto, Sheet sheet, int row) {
        if (dto.getHierarchyHed().size() == 0) {
            parseContentHed(dto, sheet, row);
        } else {
            Iterator<BuriExcelPrtiPrvidrHedDto> ite = dto.getHierarchyHed().iterator();
            while (ite.hasNext()) {
                BuriExcelPrtiPrvidrHedDto hedDto = ite.next();
                BuriExcelPrtiPrvidrItemDto item = getBuriExcelPrtiPrvidrItemDto(dto, hedDto, sheet,
                    row);
                if (item != null) {
                    setupLeftItem(dto, hedDto, item);
                    dto.getHierList().add(hedDto.getSeq(), item);
                    for (int i = dto.getHierList().size() - 1; hedDto.getSeq() < i; i--) {
                        dto.getHierList().remove(i);
                    }
                    dto.getHierarchy().put(item.getItemKey(), item);
                }
            }
        }
    }

    protected void setupLeftItem(BuriExcelPrtiPrvidrRootDto dto, BuriExcelPrtiPrvidrHedDto hedDto,
            BuriExcelPrtiPrvidrItemDto item) {
        if (hedDto.getSeq() > 0) {
            BuriExcelPrtiPrvidrItemDto left;
            if (hedDto.getSeq() > dto.getHierList().size()) {
                left = dto.getHierList().get(dto.getHierList().size() - 1);
                for (int i = dto.getHierList().size() - 1; i < hedDto.getSeq() - 1; i++) {
                    dto.getHierList().add(left);
                }
            } else {
                left = dto.getHierList().get(hedDto.getSeq() - 1);
            }
            left.getRights().add(item);
            item.getLefts().add(left);
        }
    }

    protected BuriExcelPrtiPrvidrItemDto getBuriExcelPrtiPrvidrItemDto(
            BuriExcelPrtiPrvidrRootDto dto, BuriExcelPrtiPrvidrHedDto hedDto, Sheet sheet, int row) {
        BuriExcelPrtiPrvidrItemDto item = new BuriExcelPrtiPrvidrItemDto();

        Long itemID = getIdColItemDto(hedDto, sheet, row);
        item.setId(itemID);
        String name = getNameColItemDto(hedDto, sheet, row);
        item.setName(name);
        if (itemID == null && StringUtil.isEmpty(name)) {
            return null;
        }
        if (dto.getHierarchy().containsKey(item.getItemKey())) {
            item = dto.getHierarchy().get(item.getItemKey());
        }
        item.getRoleNames().add(hedDto.getRoleName());
        return item;
    }

    protected String getNameColItemDto(BuriExcelPrtiPrvidrHedDto hedDto, Sheet sheet, int row) {
        int nameCol = hedDto.getNamePos();
        Cell nameCell = sheet.getCell(nameCol, row);
        String name = null;
        if (!(nameCell instanceof EmptyCell)) {
            name = nameCell.getContents();
        }
        return name;
    }

    protected Long getIdColItemDto(BuriExcelPrtiPrvidrHedDto hedDto, Sheet sheet, int row) {
        int idCol = hedDto.getIdPos();
        Cell idCell = sheet.getCell(idCol, row);
        String id = null;
        if (!(idCell instanceof EmptyCell)) {
            id = idCell.getContents();
            if (StringUtil.isEmpty(id)) {
                return null;
            }
            if (id.equalsIgnoreCase("id")) {
                return null;
            }
            return new Long(id);
        }
        return null;
    }

    protected void parseContentHed(BuriExcelPrtiPrvidrRootDto dto, Sheet sheet, int row) {
        String title = "";
        int seq = 0;
        BuriExcelPrtiPrvidrHedDto hedDto = null;
        for (int i = 1; i < sheet.getColumns(); i++) {
            Cell nameCell = sheet.getCell(i, row);
            if (!StringUtil.isEmpty(nameCell.getContents())) {
                title = nameCell.getContents();
                hedDto = new BuriExcelPrtiPrvidrHedDto();
                hedDto.setRoleName(title);
                hedDto.setSeq(seq);
                dto.getHierarchyHed().add(hedDto);
                seq = seq + 1;
            }
            Cell typeCell = sheet.getCell(i, row + 1);
            if ("id".equalsIgnoreCase(typeCell.getContents())) {
                hedDto.setIdPos(i);
            } else if ("name".equalsIgnoreCase(typeCell.getContents())) {
                hedDto.setNamePos(i);
            }
        }
    }

    protected void parseConvert(BuriExcelPrtiPrvidrRootDto dto, Sheet sheet, int row) {
        Cell keyCell = sheet.getCell(1, row);
        Cell valCell = sheet.getCell(2, row);
        if (StringUtil.isEmpty(keyCell.getContents())) {
            return;
        }
        if (StringUtil.isEmpty(valCell.getContents())) {
            return;
        }
        if (keyCell.getContents().equalsIgnoreCase("id")) {
            dto.setConvId(valCell.getContents());
        }
        if (keyCell.getContents().equalsIgnoreCase("name")) {
            dto.setConvName(valCell.getContents());
        }
        if (keyCell.getContents().equalsIgnoreCase("obj")) {
            dto.setConvObj(valCell.getContents());
        }
    }

    protected void parseValid(BuriExcelPrtiPrvidrRootDto dto, Sheet sheet, int row) {
        Cell cell = sheet.getCell(2, row);
        if (StringUtil.isEmpty(cell.getContents())) {
            return;
        }
        Cell keyCell = sheet.getCell(1, row);
        DateCell dateCell = (DateCell) cell;
        if (keyCell.getContents().equalsIgnoreCase("from")) {
            dto.setFromDate(dateCell.getDate());
        } else if (keyCell.getContents().equalsIgnoreCase("to")) {
            dto.setToDate(dateCell.getDate());
        }
    }

    protected int checkMode(String contents, int nowMode) {
        int mode = nowMode;
        if (contents.equalsIgnoreCase("valid")) {
            mode = 1;
        } else if (contents.equalsIgnoreCase("convert")) {
            mode = 2;
        } else if (contents.equalsIgnoreCase("type")) {
            mode = 3;
        }
        return mode;
    }

}
