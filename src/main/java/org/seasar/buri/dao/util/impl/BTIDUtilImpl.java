/*
 * çÏê¨ì˙: 2006/05/13
 *
 */
package org.seasar.buri.dao.util.impl;

import java.util.Date;

import org.seasar.buri.dao.BuriTransactionDao;
import org.seasar.buri.dao.util.BTIDUtil;
import org.seasar.buri.dto.BuriTransactionEntityDto;

public class BTIDUtilImpl implements BTIDUtil {
    private BuriTransactionDao btDao;
    private ThreadLocal btidLocal = new ThreadLocal();
    
    public long createBTID() {
        BuriTransactionEntityDto dto = new BuriTransactionEntityDto();
        dto.setInsertDate(new Date());
        btDao.insert(dto);
        return dto.getBTID();
    }
    
    public long getCurrentBTID() {
        if(btidLocal.get() == null) {
            setBTID(0);
        }
        long result = ((Long)btidLocal.get()).longValue();
        return result;
    }
    
    public void setBTID(long btid) {
        Long val = new Long(btid);
        btidLocal.set(val);
    }
}
