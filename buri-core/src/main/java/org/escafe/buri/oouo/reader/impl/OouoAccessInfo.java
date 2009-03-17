/*
 * 作成日: 2006/03/15
 *
 */
package org.escafe.buri.oouo.reader.impl;

import java.util.HashSet;
import java.util.Set;

public class OouoAccessInfo {
    private String tgtName;
    private Set sigSet = new HashSet();
    private int typeSum = 0;

    public Set getSigSet() {
        return sigSet;
    }

    public void setSigSet(Set sigSet) {
        this.sigSet = sigSet;
    }

    public String getTgtName() {
        return tgtName;
    }

    public void setTgtName(String tgtName) {
        this.tgtName = tgtName;
    }

    public int getTypeSum() {
        return typeSum;
    }

    public void setTypeSum(int typeSum) {
        this.typeSum = typeSum;
    }

    public void addType(OouoSig sig) {
        typeSum = typeSum + sig.getAction();
    }

}
