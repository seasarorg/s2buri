/*
 * 作成日: 2006/07/10
 *
 */
package org.escafe.buri.common.delayloader;

import org.aopalliance.intercept.MethodInvocation;

public class DelayLoaderInfo {
    private String tgtKey;
    private String name;
    private long lastModified;
    private MethodInvocation invoke;

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTgtKey() {
        return tgtKey;
    }

    public void setTgtKey(String tgtKey) {
        this.tgtKey = tgtKey;
    }

    public MethodInvocation getInvoke() {
        return invoke;
    }

    public void setInvoke(MethodInvocation invoke) {
        this.invoke = invoke;
    }

    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("tgtKey=").append(tgtKey);
        buff.append("/name=").append(name);
        buff.append("/lastModified=").append(lastModified);
        buff.append("/invoke=").append(invoke);
        buff.append("]");
        return buff.toString();
    }
}
