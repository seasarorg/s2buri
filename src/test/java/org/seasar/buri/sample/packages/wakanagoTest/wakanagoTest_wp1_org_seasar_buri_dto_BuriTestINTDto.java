/*
 * çÏê¨ì˙: 2006/05/04
 *
 */
package org.seasar.buri.sample.packages.wakanagoTest;

import java.util.List;

import org.seasar.buri.dto.BuriTestINTDto;
import org.seasar.buri.util.packages.abst.AbstDataAccessUtilLongKey;
import org.seasar.coffee.dataaccess.DataAccessUtilLongKey;

public class wakanagoTest_wp1_org_seasar_buri_dto_BuriTestINTDto extends AbstDataAccessUtilLongKey implements DataAccessUtilLongKey {

    public List get(List keyVals) {
        String execScript = "BuriTestINTDao.getBuriTestINTByIds(#data)";
        return getDataList(keyVals,execScript);
    }
    

    public Long getKey(Object key) {
        return getLongPkey(key,"testID");
    }

    public Object getObjectFromKey(Long keyObj) {
        BuriTestINTDto dto = new BuriTestINTDto();
        pkeyExpressionScript.setVal(dto,"testID",keyObj,null);
        return getDataFromDto(dto);
    }

    public Object Store(Object data) {
        String execScript = "";
        if(hasAvailableKey(data)) {
            execScript = "BuriTestINTDao.update(#data)";
        } else {
            execScript = "BuriTestINTDao.insert(#data)";
        }
        runScript(data,execScript);
        return data;
    }
    
    public boolean hasAvailableKey(Object keyVal) {
        Object evalResult = pkeyExpressionScript.eval(keyVal,"testID != 0",null);
        return ((Boolean)evalResult).booleanValue();
    }

    public int delete(Object data) {
        String execScript = "BuriTestINTDao.delete(#data)";
        return deleteData(data,execScript);
    }

    public Object getDataFromDto(Object keyVal) {
        BuriTestINTDto dto = (BuriTestINTDto)keyVal;
        String execScript = "BuriTestINTDao.getBuriTestINT(#data.testID)";
        return runScript(dto,execScript);
    }
    

}
