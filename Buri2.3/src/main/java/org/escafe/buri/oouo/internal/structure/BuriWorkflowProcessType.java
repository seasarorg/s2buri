/*
 * 作成日: 2006/03/07
 *
 */
package org.escafe.buri.oouo.internal.structure;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.starlogic.util.datetime.DateUtil;

import org.escafe.buri.common.util.MultiValueMap;

public class BuriWorkflowProcessType {

    private static final String SEP = System.getProperty("line.separator");

    private String id;
    private String name;
    private Map participantsById = new HashMap();
    private MultiValueMap participantsByName = new MultiValueMap();
    private Map datasById = new HashMap();
    private Map applicationsById = new HashMap();
    private MultiValueMap applicationsByName = new MultiValueMap();
    private Map activitiesById = new HashMap();
    private MultiValueMap activitiesByName = new MultiValueMap();
    private List transitions = new ArrayList();
    private MultiValueMap transitionsByFrom = new MultiValueMap();
    private MultiValueMap transitionsByTo = new MultiValueMap();
    private List startActivities = new ArrayList();
    private List extentedAttributes = new ArrayList();
    private Map activitySetsById = new HashMap();
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

    public static final String addParticipant_ELEMENT = "Participant";

    public void addParticipant(BuriParticipantType participant) {
        participantsById.put(participant.getId(), participant);
        participantsByName.put(participant.getName(), participant);
    }

    public List getParticipantsByName(String participantName) {
        return participantsByName.get(participantName);
    }

    public BuriParticipantType getParticipantById(String participantId) {
        if (participantsById.containsKey(participantId)) {
            return (BuriParticipantType) participantsById.get(participantId);
        }
        return packageType.getParticipantById(participantId);
    }

    public static final String addApplication_ELEMENT = "Application";

    public void addApplication(BuriApplicationType app) {
        applicationsById.put(app.getId(), app);
        applicationsByName.put(app.getName(), app);
    }

    public BuriApplicationType getApplicationById(String appId) {
        if (applicationsById.containsKey(appId)) {
            return (BuriApplicationType) applicationsById.get(appId);
        }
        return packageType.getApplicationById(appId);
    }

    public List getApplicationByName(String appName) {
        return applicationsByName.get(appName);
    }

    public static final String addDataField_ELEMENT = "DataField";

    public void addDataField(BuriDataFieldType dataField) {
        datasById.put(dataField.getId(), dataField);
    }

    public BuriDataFieldType getDataFieldById(String dataId) {
        if (datasById.containsKey(dataId)) {
            return (BuriDataFieldType) datasById.get(dataId);
        }
        return packageType.getDataFieldById(dataId);
    }

    public Map getDataField() {
        return datasById;
    }

    public static final String addExtendedAttribute_ELEMENT = "ExtendedAttribute";

    public void addExtendedAttribute(BuriExtendedAttributeType attri) {
        extentedAttributes.add(attri);
    }

    public List getExtentedAttribute() {
        return extentedAttributes;
    }

    public static final String addActivity_ELEMENT = "Activity";

    public void addActivity(BuriActivityType act) {
        activitiesById.put(act.getId(), act);
        activitiesByName.put(act.getName(), act);
    }

    public BuriActivityType getActivityById(String actId) {
        return (BuriActivityType) activitiesById.get(actId);
    }

    public List getActivityByName(String actName) {
        return activitiesByName.get(actName);
    }

    public Map getActivityById() {
        return activitiesById;
    }

    public static final String addTransition_ELEMENT = "Transition";

    public void addTransition(BuriTransitionType transition) {
        this.transitions.add(transition);
        transitionsByFrom.put(transition.getFrom(), transition);
        transitionsByTo.put(transition.getTo(), transition);
    }

    public List getRefToTransition(String actId) {
        if (transitionsByTo.containsKey(actId)) {
            return transitionsByTo.get(actId);
        }
        return new ArrayList();
    }

    public List getRefFromTransition(String actId) {
        if (transitionsByFrom.containsKey(actId)) {
            return transitionsByFrom.get(actId);
        }
        return new ArrayList();
    }

    public static final String addActivitySet_ELEMENT = "ActivitySet";

    public void addActivitySet(BuriActivitySetType activitySet) {
        activitySetsById.put(activitySet.getId(), activitySet);
    }

    public BuriActivitySetType getActivitySet(String actId) {
        return (BuriActivitySetType) activitySetsById.get(actId);
    }

    public static String setupEnd_OOUOFIN = "";

    public void setupEnd(BuriPackageType packageType) {
        this.packageType = packageType;
        updateStartActivites();
        if (fromType != null) {
            validFrom = DateUtil.parse(fromType.getFromDate());
        }
        if (toType != null) {
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
        List actIds = new ArrayList(activitiesById.keySet());
        actIds.removeAll(transitionsByTo.keySet());
        Iterator ite = actIds.iterator();
        while (ite.hasNext()) {
            String id = (String) ite.next();
            BuriActivityType act = getActivityById(id);
            startActivities.add(act);
        }
    }

    public List getStartActivitys() {
        return startActivities;
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

    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("  id=").append(id);
        buff.append("/name=").append(name);
        buff.append("/pathType=").append(pathType).append(SEP);
        buff.append("  -----PARTICIPANT----------").append(SEP);
        buff.append("  /participants=").append(participantsById).append(SEP);
        buff.append("  -----DATA----------").append(SEP);
        buff.append("  /datas=").append(datasById).append(SEP);
        buff.append("  -----APPLICATION----------").append(SEP);
        buff.append("  /applications=").append(applicationsById).append(SEP);
        buff.append("  -----ACTIVITY----------").append(SEP);
        buff.append("  /activities=").append(activitiesById).append(SEP);
        buff.append("  -----ACTIVITY SET----------").append(SEP);
        buff.append("  /activitySets=").append(activitySetsById).append(SEP);
        buff.append("  -----TRANSITION----------").append(SEP);
        buff.append("  /transitions=").append(transitions).append(SEP);
        buff.append("  -----EXTENDED ATTRIBUTE----------").append(SEP);
        buff.append("  /extentedAttributes=").append(extentedAttributes).append(SEP);
        buff.append("  -----START ACTIVITY----------").append(SEP);
        buff.append("  /startActivities=").append(startActivities).append(SEP);
        buff.append("]");
        return buff.toString();
    }

}
