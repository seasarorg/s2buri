/*
 * çÏê¨ì˙: 2005/12/31
 *
 */
package org.seasar.buri.bao.impl;

import org.seasar.buri.bao.BaoStatusMetadata;

public class BaoStatusMetadataImpl extends AbstractBaoMetadata implements BaoStatusMetadata {
    private String buriPathName;
    
    public String getBuriPathName() {
        return buriPathName;
    }

    public void setBuriPathName(String buriPathName) {
        this.buriPathName = buriPathName;
    }

    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append(super.toString());
        buff.append("/buriPathName=").append(buriPathName);
        buff.append("]");
        return buff.toString();
    }



}
