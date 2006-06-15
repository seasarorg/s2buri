/*
 * çÏê¨ì˙: 2006/06/08
 *
 */
package org.seasar.buri.engine;

import org.seasar.buri.util.packages.BranchWalker;
import org.seasar.buri.util.packages.BuriExecProcess;

public class BuriParticipantContext extends ParticipantContext {
    private BuriExecProcess process;
    private BuriSystemContext systemContext;
    private BranchWalker walker;
    private BuriUserContext userContext;
    private String startRoleName;
    
    public BuriExecProcess getProcess() {
        return process;
    }
    public void setProcess(BuriExecProcess process) {
        this.process = process;
    }
    public BuriSystemContext getSystemContext() {
        return systemContext;
    }
    public void setSystemContext(BuriSystemContext systemContext) {
        this.systemContext = systemContext;
    }
    public BranchWalker getWalker() {
        return walker;
    }
    public void setWalker(BranchWalker walker) {
        this.walker = walker;
    }
    public String getStartRoleName() {
        return startRoleName;
    }
    public void setStartRoleName(String startRoleName) {
        this.startRoleName = startRoleName;
    }
    public BuriUserContext getUserContext() {
        return userContext;
    }
    public void setUserContext(BuriUserContext userContext) {
        this.userContext = userContext;
    }
    
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append(super.toString());
        buff.append("/startRoleName=").append(startRoleName);
        buff.append("/userContext=").append(userContext);
        buff.append("/process=").append(process);
        buff.append("/systemContext=").append(systemContext);
        buff.append("/walker=").append(walker);
        buff.append("]");
        return buff.toString();
    }
}
