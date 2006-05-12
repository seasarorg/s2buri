/*
 * çÏê¨ì˙: 2005/05/15
 *
 */
package org.seasar.buri.impl;

import java.util.HashMap;
import java.util.Map;

import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.ParticipantProvider;
import org.seasar.buri.engine.WakanagoWorkFlows;
import org.seasar.buri.exception.select.BuriNotSelecteedPackageException;
import org.seasar.buri.xpdl.util.WorkFlowPackageTagSelect;
import org.seasar.buri.xpdl.util.XpdlReadUtil;
import org.seasar.framework.container.S2Container;
import org.wfmc.x2002.xpdl10.PackageDocument;

/**
 * @author makotan
 *
 */
public class WakanagoFlowsImpl implements WakanagoWorkFlows {
    private static Map flow = new HashMap();
    private static Map fileInfo = new HashMap();
    private static Map cache = new HashMap(50);
    private S2Container container;
    
    public void readWorkFlowFromResource(String workFlowName,String resourceName) {
        readWorkFlowFromResource(workFlowName,resourceName,null);
    }
    
    public synchronized  void readWorkFlowFromResource(String workFlowName,String resourceName,ParticipantProvider provider) {
//        if(fileInfo.containsKey(workFlowName)) {
//            return;
//        }
        PackageDocument packageDoc = XpdlReadUtil.getFileNameToPackageDocument(resourceName);
        WorkFlowPackageTagSelect tagSelect = (WorkFlowPackageTagSelect)getContainer().getComponent(WorkFlowPackageTagSelect.class);
        tagSelect.setPackageDocument(packageDoc.getPackage());
        tagSelect.setParticipantProvider(provider);
        flow.put(workFlowName,tagSelect);
        fileInfo.put(workFlowName,resourceName);
    }
    
    public Map getCache() {
        return cache;
    }

    public WorkFlowPackageTagSelect getPackageTagSelect(BuriPath path) {
        WorkFlowPackageTagSelect packageTagSelect = (WorkFlowPackageTagSelect)flow.get(path.getWorkflowPackage());
        if(packageTagSelect==null) {
            throw new BuriNotSelecteedPackageException(path);
        }
        return packageTagSelect;
    }
    public S2Container getContainer() {
        return container;
    }
    public void setContainer(S2Container container) {
        this.container = container;
    }

}
