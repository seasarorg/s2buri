/*
 * Copyright 2006 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.codegen.impl;

import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.seasar.codegen.element.DataType;
import org.seasar.codegen.element.Field;
import org.seasar.codegen.element.LinkTable;
import org.seasar.codegen.element.PrimaryKey;
import org.seasar.codegen.element.Table;
import org.seasar.extension.unit.S2TestCase;

/**
 * ExcelImportCodeDataImpl2 の単体テスト。
 * 
 * @author glad
 */
public class ExcelImportCodeDataImpl2Test extends S2TestCase {

    private ExcelImportCodeDataImpl2 importCodeData;
    
    protected void setUp() throws Exception {
        include("ExcelImportCodeDataImpl2Test.dicon");
    }

    public void testReadCodeData() {
        File src = new File("test/CodeGen-Example2.xls");
        Map actual = importCodeData.readCodeData(src);
        assertEquals(5, actual.size());
        assertEquals(getExpectedTableNames(), actual.keySet());
        System.out.println(actual);
        assertEquals(getExpectedDataPathHistory().toString(),
                actual.get("BuriDataPathHistory").toString());
    }

    protected Set getExpectedTableNames() {
        Set set = new HashSet();
        set.add("BuriBranch");
        set.add("BuriData");
        set.add("BuriDataPathHistory");
        set.add("BuriPath");
        set.add("BuriState");
        return set;
    }
    
    protected Table getExpectedDataPathHistory() {
        Table history = new Table();
        history.setTableName("BuriDataPathHistory");
        
        Field historyId = new Field();
        historyId.setFieldName("historyID");
        historyId.setDataType(getDataType("BIGINT", "NOT NULL", ""));
        history.addTableField(historyId);
        
        PrimaryKey primaryKey = new PrimaryKey();
        primaryKey.setField(historyId);
        historyId.setSequence(historyId.getFieldName());
        history.addPrimaryKey(primaryKey);
        
        Field pathId = new Field();
        pathId.setFieldName("PathID");
        pathId.setDataType(getDataType("BIGINT", "NULL", ""));
        history.addTableField(pathId);
        
        LinkTable toPath = new LinkTable();
        toPath.setTableName("BuriPath");
        toPath.setParentFieldName("PathID");
        toPath.setChildFieldName("PathID");
        history.addLinkTable(toPath);
        
        Field dataId = new Field();
        dataId.setFieldName("DataID");
        dataId.setDataType(getDataType("BIGINT", "NULL", ""));
        history.addTableField(dataId);
        
        LinkTable toData = new LinkTable();
        toData.setTableName("BuriData");
        toData.setParentFieldName("DataID");
        toData.setChildFieldName("DataID");
        history.addLinkTable(toData);
        
        Field userIdVal = new Field();
        userIdVal.setFieldName("UserIDVal");
        userIdVal.setDataType(getDataType("VARCHAR(20)", "NULL", ""));
        history.addTableField(userIdVal);
        
        Field userIdNum = new Field();
        userIdNum.setFieldName("UserIDNum");
        userIdNum.setDataType(getDataType("INTEGER", "NULL", ""));
        history.addTableField(userIdNum);
        
        Field insertDate = new Field();
        insertDate.setFieldName("insertDate");
        insertDate.setDataType(getDataType("DATE", "NOT NULL", ""));
        history.addTableField(insertDate);
        
        return history;
    }
    
    protected DataType getDataType(
            String dataType, String notNull, String defaultValue) {
        return importCodeData.getDataType(dataType, notNull, defaultValue);
    }
    
}
