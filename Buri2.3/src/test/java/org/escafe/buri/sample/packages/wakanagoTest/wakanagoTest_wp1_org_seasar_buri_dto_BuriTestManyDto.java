/*
 * 作成日: 2006/05/05
 *
 */
package org.escafe.buri.sample.packages.wakanagoTest;

import java.util.List;

import org.escafe.buri.dto.BuriTestManyDto;
import org.escafe.buri.util.packages.abst.AbstDataAccessUtilManyKey;

public class wakanagoTest_wp1_org_seasar_buri_dto_BuriTestManyDto extends
        AbstDataAccessUtilManyKey {
    private static String params[] = new String[]{"testID01","testID02"};
    private static String condition[] = new String[]{"testID01 != 0","testID02 != 0"};

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

    
    protected BuriTestManyDto convKeyStringToObj(String keyObj) {
        BuriTestManyDto dto = new BuriTestManyDto();
        setStringKeyToObj(dto,keyObj);
        return dto;
    }

    public Object getObjectFromKey(String keyObj) {
        BuriTestManyDto dto = convKeyStringToObj(keyObj);
        return getDataFromDto(dto);
    }

    public Object Store(Object data) {
        String execScript = "";
        if(hasPkey(data,condition)) {
            execScript = "BuriTestManyDao.update(#data)";
        } else {
            execScript = "BuriTestManyDao.insert(#data)";
        }
        runScript(data,execScript);
        return data;
    }

    public int delete(Object data) {
        String execScript = "BuriTestManyDao.delete(#data)";
        return deleteData(data,execScript);
    }

    public Object getDataFromDto(Object keyVal) {
        String execScript = "BuriTestManyDao.getBuriTestMany(#data.testID01 , #data.testID02)";
        return runScript(keyVal,execScript);
    }

}
