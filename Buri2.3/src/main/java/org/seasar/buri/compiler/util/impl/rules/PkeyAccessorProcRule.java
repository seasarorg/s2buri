/*
 * 作成日: 2006/07/05
 *
 */
package org.seasar.buri.compiler.util.impl.rules;

import org.seasar.buri.common.util.StringUtil;
import org.seasar.buri.exception.BuriDataFieldErrorException;
import org.seasar.buri.exception.BuriNoPkeyDefine;
import org.seasar.buri.oouo.internal.structure.BuriDataFieldType;
import org.seasar.framework.log.Logger;
import org.seasar.framework.message.MessageFormatter;

public class PkeyAccessorProcRule extends DataAccessorProcRule {
    private static Logger logger = Logger.getLogger(PkeyAccessorProcRule.class);
    protected void setupVal(BuriDataFieldType src,String val) {
        setupPkey(src,val);
    }

    protected void setupPkey(BuriDataFieldType src,String pkey) {
        if(org.seasar.framework.util.StringUtil.isEmpty(pkey)) {
            throw new BuriNoPkeyDefine(src.getId());
        }
        String pkeys[] = pkey.split("\n");
        if(pkeys.length==0) {
            throw new BuriNoPkeyDefine(src.getId());
        }
        
        for(int i=0 ; i< pkeys.length ; i++ ) {
            String onePkey = pkeys[i];
            setOnePkey(src,onePkey);
        }
    }
    protected void setOnePkey(BuriDataFieldType src,String onePkey) {
        String splitStr[] = StringUtil.SplitFastString(onePkey,",");
        String keyName = splitStr[0];
        String keyCheck = keyName + "!=0";
        if(splitStr.length==0) {
            throw new BuriNoPkeyDefine(src.getId());
        } else if(splitStr.length==1) {
            String msg = MessageFormatter.getMessage("IBRI0001",new Object[]{keyName,keyCheck});
            logger.info(msg);
        } else {
            keyCheck = splitStr[1];
        }
        src.getKeys().put(keyName,keyCheck);
    }

    public void finishCheck(BuriDataFieldType src) {
        if(src.getKeys().size() == 0) {
            throw new BuriDataFieldErrorException(src.getId(),getKeyName());
        }
    }

}
