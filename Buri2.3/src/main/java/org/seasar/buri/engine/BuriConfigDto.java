/*
 * 作成日: 2006/07/16
 *
 */
package org.seasar.buri.engine;

public class BuriConfigDto {
    private String fileName;
    private String packageName;
    private ParticipantProvider provider;
    
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public ParticipantProvider getProvider() {
        return provider;
    }
    public void setProvider(ParticipantProvider provider) {
        this.provider = provider;
    }
    public String getPackageName() {
        return packageName;
    }
    public void setPackageName(String resourceName) {
        this.packageName = resourceName;
    }
    
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("fileName=").append(fileName);
        buff.append("/packageName=").append(packageName);
        buff.append("/provider=").append(provider);
        buff.append("]");
        return buff.toString();
    }
    
}
