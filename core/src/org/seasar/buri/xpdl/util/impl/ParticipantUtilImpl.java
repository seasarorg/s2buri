/*
 * ì¬“ú: 2005/10/02
 *
 */
package org.seasar.buri.xpdl.util.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.xmlbeans.XmlObject;
import org.seasar.buri.common.util.ArrayUtil;
import org.seasar.buri.engine.BuriParticipant;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.ParticipantProvider;
import org.seasar.buri.exception.BuriNoParticipantException;
import org.seasar.buri.xpdl.util.ActivityTagSelect;
import org.seasar.buri.xpdl.util.ParticipantUtil;
import org.seasar.buri.xpdl.util.TagSelect;
import org.seasar.buri.xpdl.util.WorkFlowPackageTagSelect;
import org.seasar.buri.xpdl.util.WorkFlowsUtil;
import org.seasar.buri.xpdl.util.WorkflowProcessTagSelect;
import org.seasar.buri.xpdl.util.XPathUtil;
import org.wfmc.x2002.xpdl10.ParticipantDocument.Participant;
import org.wfmc.x2002.xpdl10.ParticipantTypeDocument.ParticipantType;


public class ParticipantUtilImpl implements ParticipantUtil {
    protected static HashMap convTypeToVal = new HashMap();
    private WorkFlowsUtil flowsUtil;

    static {
        convTypeToVal.put(ParticipantType.Type.HUMAN,new Long(1));
        convTypeToVal.put(ParticipantType.Type.ROLE,new Long(2));
        convTypeToVal.put(ParticipantType.Type.ORGANIZATIONAL_UNIT,new Long(3));
        convTypeToVal.put(ParticipantType.Type.RESOURCE,new Long(4));
        convTypeToVal.put(ParticipantType.Type.RESOURCE_SET,new Long(5));
        convTypeToVal.put(ParticipantType.Type.SYSTEM,new Long(6));
    }
    
    private XPathUtil pathUtil;

    public List getParticipantList(BuriPath buriPath) {
        TagSelect tagSelect = flowsUtil.getWorkFlowPackage(buriPath);
        Participant[] participants = getParticipantArray(tagSelect.getXmlObject(),null);
        return ArrayUtil.arrayToList(participants);
    }
    public Participant getParticipantFromName(BuriPath buriPath,String name) {
        if(buriPath.getWorkflowProcess().length() > 0) {
            WorkflowProcessTagSelect process = flowsUtil.getWorkflowProcess(buriPath);
            Participant[] result = getParticipantArray(process.getXmlObject(),name);
            if(result != null) {
                return (Participant)ArrayUtil.getArrayTopOne(result);
            }
        }
        TagSelect tagSelect = flowsUtil.getWorkFlowPackage(buriPath);
        Participant[] participants = getParticipantArray(tagSelect.getXmlObject(),name);
        return (Participant)ArrayUtil.getArrayTopOne(participants);
    }
    
    public List findParticipant(BuriPath buriPath,List activityList,BuriParticipant participant) {
        WorkFlowPackageTagSelect packageTagSelect = flowsUtil.getWorkFlowPackage(buriPath);
        List result = new ArrayList();
        ParticipantProvider provider = packageTagSelect.getParticipantProvider();
        Iterator ite = activityList.iterator();
        int roleTypeID = 100;
        while(ite.hasNext()) {
            ActivityTagSelect activity = (ActivityTagSelect)ite.next();
            roleTypeID = appendActivity(buriPath,result,activity,participant,provider,roleTypeID);
        }
        return result;
    }
    
    protected int appendActivity(BuriPath buriPath,List result,ActivityTagSelect activity,BuriParticipant participant,ParticipantProvider provider,int roleTypeID) {
        Participant xpdlParticipant = getParticipantFromName(buriPath,activity.getActivity().getPerformer());
        if(xpdlParticipant==null) {
            throw new BuriNoParticipantException(buriPath,participant.getUserData().toString());
        }
        if( canExecuteParticipant(xpdlParticipant,participant,provider) ) {
            int newRoleTypeID = convParticipantTypeToVal(xpdlParticipant.getParticipantType());
            if(roleTypeID > newRoleTypeID) {
                result.clear();
                roleTypeID = newRoleTypeID;
            }
            if(roleTypeID == newRoleTypeID) {
                result.add(activity);
            }
        }
        return roleTypeID;
    }
    
    protected int convParticipantTypeToVal(ParticipantType participantType) {
        Long val = (Long)convTypeToVal.get(participantType.getType());
        return val.intValue();
    }

    protected boolean canExecuteParticipant(Participant xpdlParticipant,BuriParticipant participant,ParticipantProvider provider) {
        String participantName = xpdlParticipant.getName(); 
        return provider.isUserInRole(participant.getUserData(),participantName);
    }
    
    protected String getParticipantXPath() {
        String xpath = "./xpdl:Participants/xpdl:Participant";
        return xpath;
    }
    public Participant getParticipant(BuriPath buriPath,String name) {
        TagSelect tagSelect = flowsUtil.getWorkFlowPackage(buriPath);
        return getParticipant(tagSelect.getXmlObject(),name);
    }
    public Participant getParticipant(XmlObject xmlObj,String name) {
        Participant[] participantArray = getParticipantArray(xmlObj,name);
        if(participantArray!= null && participantArray.length > 0) {
            return participantArray[0];
        }
        return null;
    }

    
    public Participant[] getParticipantArray(BuriPath buriPath,String name) {
        TagSelect tagSelect = flowsUtil.getWorkFlowPackage(buriPath);
        return getParticipantArray(tagSelect.getXmlObject(),name);
    }
    public Participant[] getParticipantArray(XmlObject xmlObj,String name) {
        XmlObject[] xmlObjs = pathUtil.getXmlObjArrayFromXPath(xmlObj,getParticipantXPath(),name,name);
        if(xmlObjs.length==0) {
            return null;
        }
        Participant[] participants = (Participant[])xmlObjs;
        return participants;
    }

    public XPathUtil getPathUtil() {
        return pathUtil;
    }

    public void setPathUtil(XPathUtil pathUtil) {
        this.pathUtil = pathUtil;
    }
    public WorkFlowsUtil getFlowsUtil() {
        return flowsUtil;
    }
    public void setFlowsUtil(WorkFlowsUtil flowsUtil) {
        this.flowsUtil = flowsUtil;
    }

}
