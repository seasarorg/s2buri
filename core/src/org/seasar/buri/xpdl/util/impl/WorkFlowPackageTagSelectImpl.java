/*
 * çÏê¨ì˙: 2005/10/02
 *
 */
package org.seasar.buri.xpdl.util.impl;


import org.apache.xmlbeans.XmlObject;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.ParticipantProvider;
import org.seasar.buri.xpdl.util.WorkFlowPackageTagSelect;
import org.wfmc.x2002.xpdl10.PackageDocument.Package;

public class WorkFlowPackageTagSelectImpl extends AbstractTagSelect implements
        WorkFlowPackageTagSelect {
    
    private Package packageData;
    private BuriPath thisPath;
    private ParticipantProvider provider;

    public Package getPackageDocument() {
        return packageData;
    }

    public void setPackageDocument(Package packageData) {
        this.packageData = packageData;
    }

    public void setBuriPath(BuriPath thisPath) {
        this.thisPath = thisPath;
    }

    public BuriPath getBuriPath() {
        return thisPath;
    }

    public XmlObject getCurrentXmlObject() {
        return getPackageDocument();
    }

    public XmlObject getXmlObject() {
        return getPackageDocument();
    }

    public void setParticipantProvider(ParticipantProvider provider) {
        this.provider = provider;
    }

    public ParticipantProvider getParticipantProvider() {
        return provider;
    }

}
