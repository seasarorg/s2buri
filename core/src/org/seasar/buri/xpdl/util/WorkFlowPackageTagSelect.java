/*
 * çÏê¨ì˙: 2005/10/02
 *
 */
package org.seasar.buri.xpdl.util;


import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.ParticipantProvider;
import org.wfmc.x2002.xpdl10.PackageDocument.Package;

public interface WorkFlowPackageTagSelect extends TagSelect {

    Package getPackageDocument();
    void setPackageDocument(Package packageData);
    
    void setBuriPath(BuriPath thisPath);
    BuriPath getBuriPath();
    void setParticipantProvider(ParticipantProvider provider);
    ParticipantProvider getParticipantProvider();
    
}
