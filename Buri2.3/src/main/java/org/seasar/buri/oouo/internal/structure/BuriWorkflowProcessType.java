/*
 * 作成日: 2006/03/07
 *
 */
package org.seasar.buri.oouo.internal.structure;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.starlogic.util.datetime.DateUtil;

import org.seasar.buri.common.util.MultiValueMap;

public class BuriWorkflowProcessType {
    private String id;
    private String name;
    private Map rolesId = new HashMap();
    private MultiValueMap rolesName = new MultiValueMap();
    private Map data = new HashMap();
    private Map applicationId = new HashMap();
    private MultiValueMap applicationName = new MultiValueMap();
    private Map activityById = new HashMap();
    private MultiValueMap activityByName = new MultiValueMap();
    private List transition = new ArrayList();
    private MultiValueMap transitionByFrom = new MultiValueMap();
    private MultiValueMap transitionByTo = new MultiValueMap();
    private List startActivitys = new ArrayList();
    private List ExtentedAttribute = new ArrayList();
    private Map activitySetId = new HashMap();
    private BuriValidFromType fromType;
    private BuriValidToType toType;
    private Date validFrom;
    private Date validTo;
    
    private BuriPackageType packageType;
    private long pathType = 0;
    
    public final static String OOUOTHIS = "WorkflowProcess";
    
    
    public final static String setId_ATTRI = "Id";
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public final static String setName_ATTRI = "Name";
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public static final String addRoles_ELEMENT = "Participant";
    public void addRoles(BuriParticipantType participant) {
        rolesId.put(participant.getId(),participant);
        rolesName.put(participant.getName(),participant);
    }
    
    public List getRolesByName(String roleName) {
        return rolesName.get(roleName);
    }
    
    public BuriParticipantType getRolesById(String roleId) {
        if(rolesId.containsKey(roleId)) {
            return (BuriParticipantType)rolesId.get(roleId);
        }
        return packageType.getRolesById(roleId);
    }
    
    public static final String addApplication_ELEMENT = "Application";
    public void addApplication(BuriApplicationType app) {
        applicationId.put(app.getId(),app);
        applicationName.put(app.getName(),app);
    }
    
    public BuriApplicationType getApplicationById(String appId) {
        if(applicationId.containsKey(appId)) {
            return (BuriApplicationType)applicationId.get(appId);
        }
        return packageType.getApplicationById(appId);
    }
    
    public List getApplicationByName(String appName) {
        return applicationName.get(appName);
    }
    
    public static final String addDataField_ELEMENT = "DataField";
    public void addDataField(BuriDataFieldType dataField) {
        data.put(dataField.getId(),dataField);
    }
    
    public BuriDataFieldType getDataFieldById(String dataId) {
        if(data.containsKey(dataId)) {
            return (BuriDataFieldType)data.get(dataId);
        }
        return packageType.getDataFieldById(dataId);
    }
    
    public Map getDataField() {
        return data;
    }
    
    public static final String addExtendedAttribute_ELEMENT = "ExtendedAttribute";
    public void addExtendedAttribute(BuriExtendedAttributeType attri) {
        ExtentedAttribute.add(attri);
    }
    
    public List getExtentedAttribute() {
        return ExtentedAttribute;
    }
    
    public static final String addActivity_ELEMENT = "Activity";
    public void addActivity(BuriActivityType act) {
        activityById.put(act.getId(),act);
        activityByName.put(act.getName(),act);
    }
    
    public BuriActivityType getActivityById(String actId) {
        return (BuriActivityType)activityById.get(actId);
    }
    
    public List getActivityByName(String actName) {
        return activityByName.get(actName);
    }
    
    public Map getActivityById() {
        return activityById;
    }

    public static final String addTransition_ELEMENT = "Transition";
    public void addTransition(BuriTransitionType transition) {
        this.transition.add(transition);
        transitionByFrom.put(transition.getFrom(),transition);
        transitionByTo.put(transition.getTo(),transition);
    }
    
    public List getRefToTransition(String actId) {
        if(transitionByTo.containsKey(actId)) {
            return transitionByTo.get(actId);
        }
        return new ArrayList();
    }
    
    public List getRefFromTransition(String actId) {
        if(transitionByFrom.containsKey(actId)) {
            return transitionByFrom.get(actId);
        }
        return new ArrayList();
    }

    public static final String addActivitySet_ELEMENT = "ActivitySet";
    public void addActivitySet(BuriActivitySetType activitySet) {
        activitySetId.put(activitySet.getId(),activitySet);
    }
    
    public BuriActivitySetType getActivitySet(String actId) {
        return (BuriActivitySetType)activitySetId.get(actId);
    }
    
    public static String setupEnd_OOUOFIN = "";
    public void setupEnd(BuriPackageType packageType) {
        this.packageType = packageType;
        updateStartActivites();
        if(fromType != null) {
            validFrom = DateUtil.parse(fromType.getFromDate());
        }
        if(toType != null) {
            validTo = DateUtil.parse(toType.getToDate());
        }
    }
    
    
    public BuriPackageType getPackageType() {
        return packageType;
    }
    public void setPackageType(BuriPackageType packageType) {
        this.packageType = packageType;
        updateStartActivites();
    }
    
    protected void updateStartActivites() {
        List actIds = new ArrayList(activityById.keySet());
        actIds.removeAll(transitionByTo.keySet());
        Iterator ite = actIds.iterator();
        while(ite.hasNext()) {
            String id = (String)ite.next();
            BuriActivityType act = getActivityById(id);
            startActivitys.add(act);
        }
    }
    
    public List getStartActivitys() {
        return startActivitys;
    }

    
    public long getPathType() {
        return pathType;
    }
    public void setPathType(long pathType) {
        this.pathType = pathType;
    }
    
    public BuriValidFromType getFromType() {
        return fromType;
    }
    public static final String setFromType_ELEMENT = "ValidFrom";
    public void setFromType(BuriValidFromType fromType) {
        this.fromType = fromType;
    }
    public BuriValidToType getToType() {
        return toType;
    }
    public static final String setToType_ELEMENT = "ValidTo";
    public void setToType(BuriValidToType toType) {
        this.toType = toType;
    }
    public Date getValidFrom() {
        return validFrom;
    }
    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }
    public Date getValidTo() {
        return validTo;
    }
    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("  id=").append(id);
        buff.append("/name=").append(name);
        buff.append("/pathType=").append(pathType).append("\n");
        buff.append("  -----ROLE ID----------\n");
        buff.append("  /roles=").append(rolesId).append("\n");
        buff.append("  -----DATA----------\n");
        buff.append("  /data=").append(data).append("\n");
        buff.append("  -----APPLICATION----------\n");
        buff.append("  /application=").append(applicationId).append("\n");
        buff.append("  -----ACTIVITY----------\n");
        buff.append("  /activity=").append(activityById).append("\n");
        buff.append("  -----ACTIVITY SET----------\n");
        buff.append("  /activitySetId=").append(activitySetId).append("\n");
        buff.append("  -----transition----------\n");
        buff.append("  /transition=").append(transition).append("\n");
        buff.append("  -----ExtentedAttribute----------\n");
        buff.append("  /ExtentedAttribute=").append(ExtentedAttribute).append("\n");
        buff.append("  -----START ACTIVITY----------\n");
        buff.append("  /").append(startActivitys).append("\n");
        buff.append("]\n\n");
        return buff.toString();
    }

}
