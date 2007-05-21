/*
 * 作成日: 2006/05/04
 *
 */
package org.escafe.buri.sample.packages.wakanagoTest;

import java.util.List;

import org.escafe.buri.dto.BuriTestCHARDto;
import org.escafe.buri.util.packages.abst.AbstDataAccessUtilManyKey;
import org.seasar.coffee.dataaccess.DataAccessUtilManyKey;

public class wakanagoTest_wp1_org_seasar_buri_dto_BuriTestCHARDto extends AbstDataAccessUtilManyKey implements DataAccessUtilManyKey {
    private static String params[] = new String[]{"testID"};
    private static String condition[] = new String[]{"testID != \"\""};

    protected String[] getConditions() {
        return condition;
    }

    public List get(List keyStr) {
        String execScript = "";
        return getDataList(keyStr,execScript);
    }

    public String getKey(Object key) {
        return getKey(key,params);
    }
    
    protected BuriTestCHARDto convKeyStringToObj(String keyObj) {
        BuriTestCHARDto dto = new BuriTestCHARDto();
        setStringKeyToObj(dto,keyObj);
        return dto;
    }

    public Object getObjectFromKey(String keyObj) {
        BuriTestCHARDto dto = convKeyStringToObj(keyObj);
        return getDataFromDto(dto);
    }
    
    public Object Store(Object data) {
        String execScript = "";
        if(hasPkey(data,condition)) {
            execScript = "BuriTestCHARDao.update(#data)";
        } else {
            execScript = "BuriTestCHARDao.insert(#data)";
        }
        runScript(data,execScript);
        return data;
    }

    public int delete(Object data) {
        String execScript = "BuriTestCHARDao.delete(#data)";
        return deleteData(data,execScript);
    }

    public Object getDataFromDto(Object keyVal) {
        String execScript = "BuriTestCHARDao.getBuriTestCHAR(#data.testID)";
        return runScript(keyVal,execScript);
    }

}
