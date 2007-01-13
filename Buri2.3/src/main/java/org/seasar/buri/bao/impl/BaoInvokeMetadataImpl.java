/*
 * 作成日: 2005/12/31
 *
 */
package org.seasar.buri.bao.impl;

import org.seasar.buri.bao.BaoInvokeMetadata;
import org.seasar.buri.bao.BuriConvert;

public class BaoInvokeMetadataImpl extends AbstractBaoMetadata implements
        BaoInvokeMetadata {
    
    private String action = null;
    private BuriConvert buriConvert = null;
    private String result = null;

    public String getAction() {
        return action;
    }


    public void setAction(String action) {
        this.action = action;
    }


    public BuriConvert getBuriConvert() {
        return buriConvert;
    }


    public void setBuriConvert(BuriConvert buriConvert) {
        this.buriConvert = buriConvert;
    }


    public String getResult() {
        return result;
    }


    public void setResult(String result) {
        this.result = result;
    }
    

    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append(super.toString());
        buff.append("/action=").append(action);
        buff.append("/buriConvert=").append(buriConvert);
        buff.append("/result=").append(result);
        buff.append("]");
        return buff.toString();
    }


}
