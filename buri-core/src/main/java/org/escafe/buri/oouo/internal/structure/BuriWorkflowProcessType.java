/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.escafe.buri.oouo.internal.structure;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.starlogic.util.datetime.DateUtil;

import org.escafe.buri.common.util.MultiValueMap;

/**
 * XPDLの{@code WorkflowProcess}要素を表すクラスです。
 * <p>
 * XPDLの{@code WorkflowProcess}は、フローのパッケージに対して複数紐づきます。
 * </p>
 * <p>
 * {@code WorkflowProcess}のスキーマは以下のように定義されています。
 * <pre>{@code <xsd:element name="WorkflowProcess" type="xpdl:ProcessType">
 *     <xsd:key name="ActivitySetIds.WorkflowProcess">
 *         <xsd:selector xpath="./xpdl:ActivitySets/xpdl:ActivitySet"/>
 *         <xsd:field xpath="@Id"/>
 *     </xsd:key>
 *     <xsd:key name="ActivityIds.WorkflowProcess">
 *         <xsd:selector xpath="./xpdl:Activities/xpdl:Activity | ./xpdl:ActivitySets/xpdl:ActivitySet/xpdl:Activities/xpdl:Activity"/>
 *         <xsd:field xpath="@Id"/>
 *     </xsd:key>
 *     <xsd:key name="ActivityIdsTopLevel.WorkflowProcess">
 *         <xsd:selector xpath="./xpdl:Activities/xpdl:Activity"/>
 *         <xsd:field xpath="@Id"/>
 *     </xsd:key>
 *     <xsd:key name="TransitionIdsTopLevel.WorkflowProcess">
 *         <xsd:selector xpath="./xpdl:Transitions/xpdl:Transition"/>
 *         <xsd:field xpath="@Id"/>
 *     </xsd:key>
 *     <xsd:keyref name="DefaultStartActivitySetIdRef.WorkflowProcess" refer="xpdl:ActivitySetIds.WorkflowProcess">
 *         <xsd:selector xpath="."/>
 *         <xsd:field xpath="@DefaultStartActivitySetId"/>
 *     </xsd:keyref>
 *     <xsd:keyref name="DefaultStartActivityIdRef.WorkflowProcess" refer="xpdl:ActivityIds.WorkflowProcess">
 *         <xsd:selector xpath="."/>
 *         <xsd:field xpath="@DefaultStartActivityId"/>
 *     </xsd:keyref>
 *     <xsd:keyref name="BlockActivityActivitySetIdRef.WorkflowProcess" refer="xpdl:ActivitySetIds.WorkflowProcess">
 *         <xsd:selector xpath=".//xpdl:BlockActivity"/>
 *         <xsd:field xpath="@ActivitySetId"/>
 *     </xsd:keyref>
 *     <xsd:keyref name="BlockActivityStartActivityIdRef.WorkflowProcess" refer="xpdl:ActivityIds.WorkflowProcess">
 *         <xsd:selector xpath=".//xpdl:BlockActivity"/>
 *         <xsd:field xpath="@StartActivityId"/>
 *     </xsd:keyref>
 *     <xsd:keyref name="TransitionFromRef.WorkflowProcess" refer="xpdl:ActivityIdsTopLevel.WorkflowProcess">
 *         <xsd:selector xpath="./xpdl:Transitions/xpdl:Transition"/>
 *         <xsd:field xpath="@From"/>
 *     </xsd:keyref>
 *     <xsd:keyref name="TransitionToRef.WorkflowProcess" refer="xpdl:ActivityIdsTopLevel.WorkflowProcess">
 *         <xsd:selector xpath="./xpdl:Transitions/xpdl:Transition"/>
 *         <xsd:field xpath="@To"/>
 *     </xsd:keyref>
 *     <xsd:keyref name="TransitionRefIdRef.WorkflowProcess" refer="xpdl:TransitionIdsTopLevel.WorkflowProcess">
 *         <xsd:selector xpath="./xpdl:Activities/xpdl:Activity/xpdl:TransitionRestrictions/xpdl:TransitionRestriction/xpdl:Split/xpdl:TransitionRefs/xpdl:TransitionRef"/>
 *         <xsd:field xpath="@Id"/>
 *     </xsd:keyref>
 *     <!-- constrain to only activities in the top-level, not activitysets -->
 *     <!-- constrain to only transitions in the top-level, not activitysets -->
 *     <!-- check that specified default start activityset exists -->
 *     <!-- check that specified default start activity exists (note: incomplete test, does not constrain to optional activtyset specified by DefaultStartActivitySetId) -->
 *     <!-- check that the activityset specified in a blockactivity exists -->
 *     <!-- check that the start activity specified in a blockactivity exists (note: incomplete test, does not constrain to activtyset specified by ActivitySetId) -->
 *     <!-- check that the from and to specified in a transition exists -->
 *     <!-- check that the id specified in a transitionref exists -->
 * </xsd:element>}</pre>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/03/07
 */
public class BuriWorkflowProcessType {

    /**
     * セパレータ
     */
    private static final String SEP = System.getProperty("line.separator");

    /**
     * フローのプロセスID
     */
    private String id;

    /**
     * フローのプロセス名
     */
    private String name;

    /**
     * {@code Participant}のIDと{@link BuriParticipantType}を紐づけて管理する{@link Map}
     */
    private Map<String, BuriParticipantType> participantsById = new HashMap<String, BuriParticipantType>();

    /**
     * {@code Participant}の名前と{@link BuriParticipantType}を紐づけて管理する{@link MultiValueMap}
     */
    private MultiValueMap<BuriParticipantType> participantsByName = new MultiValueMap<BuriParticipantType>();

    /**
     * {@code DataField}のIDと{@link BuriDataFieldType}を紐づけて管理する{@link Map}
     */
    private Map<String, BuriDataFieldType> datasById = new HashMap<String, BuriDataFieldType>();

    /**
     * {@code Application}のIDと{@link BuriApplicationType}を紐づけて管理する{@link Map}
     */
    private Map<String, BuriApplicationType> applicationsById = new HashMap<String, BuriApplicationType>();

    /**
     * {@code Application}の名前と{@link BuriApplicationType}を紐づけて管理する{@link MultiValueMap}
     */
    private MultiValueMap<BuriApplicationType> applicationsByName = new MultiValueMap<BuriApplicationType>();

    /**
     * {@code Activity}のIDと{@link BuriActivityType}を紐づけて管理する{@link Map}
     */
    private Map<String, BuriActivityType> activitiesById = new HashMap<String, BuriActivityType>();

    /**
     * {@code Activity}の名前と{@link BuriActivityType}を紐づけて管理する{@link MultiValueMap}
     */
    private MultiValueMap<BuriActivityType> activitiesByName = new MultiValueMap<BuriActivityType>();

    /**
     * {@code Transition}のリスト
     */
    private List<BuriTransitionType> transitions = new ArrayList<BuriTransitionType>();

    /**
     * 遷移元の{@code Activity}のIDと{@link BuriTransitionType}を紐づけて管理する{@link MultiValueMap}
     */
    private MultiValueMap<BuriTransitionType> transitionsByFrom = new MultiValueMap<BuriTransitionType>();

    /**
     * 遷移先の{@code Transition}のIDと{@link BuriTransitionType}を紐づけて管理する{@link MultiValueMap}
     */
    private MultiValueMap<BuriTransitionType> transitionsByTo = new MultiValueMap<BuriTransitionType>();

    /**
     * 始点になるアクティビティのリスト
     */
    private List<BuriActivityType> startActivities = new ArrayList<BuriActivityType>();

    /**
     * {@code WorkflowProcess}に紐づく{@code ExtendedAttribute}のリスト
     */
    private List<BuriExtendedAttributeType> extentedAttributes = new ArrayList<BuriExtendedAttributeType>();

    /**
     * {@code WorkflowProcess}に紐づく{@code ActivitySet}のIDと{@link BuriActivitySetType}を紐づけて管理する{@link Map}
     */
    private Map<String, BuriActivitySetType> activitySetsById = new HashMap<String, BuriActivitySetType>();

    /**
     * {@code WorkflowProcess}の発効日時の{@link BuriValidFromType}
     */
    private BuriValidFromType fromType;

    /**
     * {@code WorkflowProcess}の失効日時の{@llink BuriValidToType}
     */
    private BuriValidToType toType;

    /**
     * {@code WorkflowProcess}の発効日時
     */
    private Date validFrom;

    /**
     * {@code WorkflowProcess}の失効日時
     */
    private Date validTo;

    /**
     * この{@code WorkflowProcess}が所属する{@code Package}
     */
    private BuriPackageType packageType;

    /**
     * {@code WorkflowProcess}の{@code PathType}
     */
    private long pathType = 0;

    /**
     * {@code WorkflowProcess}の要素名
     */
    public final static String OOUOTHIS = "WorkflowProcess";

    /**
     * {@code WorkflowProcess}のIDの属性名
     */
    public final static String setId_ATTRI = "Id";

    /**
     * {@code WorkflowProcess}のIDを返します。
     * 
     * @return {@code WorkflowProcess}のID
     */
    public String getId() {
        return id;
    }

    /**
     * {@code WorkflowProcess}のIDを登録します。
     * 
     * @param id {@code WorkflowProcess}のID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * {@code WorkflowProcess}の名前の属性名
     */
    public final static String setName_ATTRI = "Name";

    /**
     * {@code WorkflowProcess}の名前を返します。
     * 
     * @return {@code WorkflowProcess}の名前
     */
    public String getName() {
        return name;
    }

    /**
     * {@code WorkflowProcess}の名前を登録します。
     * 
     * @param name {@code WorkflowProcess}の名前
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@code WorkflowProcess}のステークホルダーの要素名
     */
    public static final String addParticipant_ELEMENT = "Participant";

    /**
     * {@code WorkflowProcess}のステークホルダーを追加します。
     * 
     * @param participant {@code WorkflowProcess}のステークホルダー
     */
    public void addParticipant(BuriParticipantType participant) {
        participantsById.put(participant.getId(), participant);
        participantsByName.put(participant.getName(), participant);
    }

    /**
     * 指定されたステークホルダーの名前から、{@code WorkflowProcess}のステークホルダーのリストを返します。
     * @param participantName ステークホルダーの名前
     * @return {@code WorkflowProcess}のステークホルダーのリスト
     */
    public List<BuriParticipantType> getParticipantsByName(String participantName) {
        return participantsByName.get(participantName);
    }

    /**
     * 指定されたステークホルダーのIDから、対応する{@link BuriParticipantType}を返します。
     * 
     * @param participantId ステークホルダーのID
     * @return 対応する{@link BuriParticipantType}
     */
    public BuriParticipantType getParticipantById(String participantId) {
        if (participantsById.containsKey(participantId)) {
            return (BuriParticipantType) participantsById.get(participantId);
        }
        return packageType.getParticipantById(participantId);
    }

    /**
     * {@code Application}の要素名
     */
    public static final String addApplication_ELEMENT = "Application";

    /**
     * {@code Application}を追加します。
     * 
     * @param app {@code Application}
     */
    public void addApplication(BuriApplicationType app) {
        applicationsById.put(app.getId(), app);
        applicationsByName.put(app.getName(), app);
    }

    /**
     * 指定された{@code Application}のIDから、対応する{@link BuriApplicationType}を返します。
     * 
     * @param appId {@code Application}のID
     * @return 対応する{@link BuriApplicationType}
     */
    public BuriApplicationType getApplicationById(String appId) {
        if (applicationsById.containsKey(appId)) {
            return (BuriApplicationType) applicationsById.get(appId);
        }
        return packageType.getApplicationById(appId);
    }

    /**
     * 指定された{@code Application}の名前から、対応する{@link BuriApplicationType}のリストを返します。
     * 
     * @param appName {@code Application}の名前
     * @return 対応する{@link BuriApplicationType}のリスト
     */
    public List<BuriApplicationType> getApplicationByName(String appName) {
        return applicationsByName.get(appName);
    }

    /**
     * {@code DataField}の要素名
     */
    public static final String addDataField_ELEMENT = "DataField";

    /**
     * {@code DataField}を追加します。
     * 
     * @param dataField {@code DataField}
     */
    public void addDataField(BuriDataFieldType dataField) {
        datasById.put(dataField.getId(), dataField);
    }

    /**
     * 指定された{@code DataField}のIDから、対応する{@link BuriDataFieldType}を返します。
     * 
     * @param dataId {@code DataField}のID
     * @return 対応する{@link BuriDataFieldType}
     */
    public BuriDataFieldType getDataFieldById(String dataId) {
        if (datasById.containsKey(dataId)) {
            return (BuriDataFieldType) datasById.get(dataId);
        }
        return packageType.getDataFieldById(dataId);
    }

    /**
     * {@code DataField}のIDと{@link BuriDataFieldType}を紐づけて管理する{@link Map}を返します。
     * 
     * @return {@code DataField}のIDと{@link BuriDataFieldType}を紐づけて管理する{@link Map}
     */
    public Map<String, BuriDataFieldType> getDataField() {
        return datasById;
    }

    /**
     * {@code WorkflowProcess}に紐づく{@code ExtendedAttribute}の要素名
     */
    public static final String addExtendedAttribute_ELEMENT = "ExtendedAttribute";

    /**
     * {@code WorkflowProcess}に紐づく{@code ExtendedAttribute}を追加します。
     * 
     * @param attri {@code WorkflowProcess}に紐づく{@code ExtendedAttribute}
     */
    public void addExtendedAttribute(BuriExtendedAttributeType attri) {
        extentedAttributes.add(attri);
    }

    /**
     * {@code WorkflowProcess}に紐づく{@code ExtendedAttribute}のリストを返します。
     * 
     * @return {@code WorkflowProcess}に紐づく{@code ExtendedAttribute}のリスト
     */
    public List<BuriExtendedAttributeType> getExtentedAttribute() {
        return extentedAttributes;
    }

    /**
     * {@code Activity}の要素名
     */
    public static final String addActivity_ELEMENT = "Activity";

    /**
     * {@code Activity}を追加します。
     * <p>
     * {@code Activity}のIDと{@link BuriActivityType}を紐づけて管理する{@link Map}と
     * {@code Activity}の名前と{@link BuriActivityType}を紐づけて管理する{@link MultiValueMap}の
     * いずれにも追加します。
     * </p>
     * 
     * @param act {@code Activity}の{@link BuriActivityType} 
     */
    public void addActivity(BuriActivityType act) {
        activitiesById.put(act.getId(), act);
        activitiesByName.put(act.getName(), act);
    }

    /**
     * 指定された{@code Activity}のIDから、対応する{@link BuriActivityType}を返します。
     * 
     * @param actId {@code Activity}のID
     * @return 対応する{@link BuriActivityType}
     */
    public BuriActivityType getActivityById(String actId) {
        return activitiesById.get(actId);
    }

    /**
     * 指定された{@code Activity}の名前から、対応する{@link BuriActivityType}のリストを返します。
     * 
     * @param actName {@code Activity}の名前
     * @return 対応する{@link BuriActivityType}のリスト
     */
    public List<BuriActivityType> getActivityByName(String actName) {
        return activitiesByName.get(actName);
    }

    /**
     * {@code Activity}のIDと{@link BuriActivityType}を紐づけて管理する{@link Map}を返します。
     * 
     * @return {@code Activity}のIDと{@link BuriActivityType}を紐づけて管理する{@link Map}
     */
    public Map<String, BuriActivityType> getActivityById() {
        return activitiesById;
    }

    /**
     * {@code Transition}の要素名
     */
    public static final String addTransition_ELEMENT = "Transition";

    /**
     * {@code Transition}を追加します。
     * <p>
     * {@code Transition}のリスト、
     * 遷移元の{@code Transition}のIDと{@link BuriTransitionType}を紐づけて管理する{@link MultiValueMap}、
     * 遷移先の{@code Transition}のIDと{@link BuriTransitionType}を紐づけて管理する{@link MultiValueMap}の
     * いずれにも追加します。
     * </p>
     * 
     * @param transition {@code Transition}
     */
    public void addTransition(BuriTransitionType transition) {
        this.transitions.add(transition);
        transitionsByFrom.put(transition.getFrom(), transition);
        transitionsByTo.put(transition.getTo(), transition);
    }

    /**
     * 指定された遷移元のアクティビティのIDから、遷移先のトランジションのリストを返します。
     * 
     * @param actId アクティビティのID
     * @return 遷移先のトランジションのリスト
     */
    public List<BuriTransitionType> getRefToTransition(String actId) {
        if (transitionsByTo.containsKey(actId)) {
            return transitionsByTo.get(actId);
        }
        return new ArrayList<BuriTransitionType>();
    }

    /**
     * 指定された遷移元のアクティビティのIDから、トランジションのリストを返します。
     * 
     * @param actId 遷移元のアクティビティのID
     * @return トランジションのリスト
     */
    public List<BuriTransitionType> getRefFromTransition(String actId) {
        if (transitionsByFrom.containsKey(actId)) {
            return transitionsByFrom.get(actId);
        }
        return new ArrayList<BuriTransitionType>();
    }

    /**
     * {@code ActivitySet}の要素名
     */
    public static final String addActivitySet_ELEMENT = "ActivitySet";

    /**
     * {@code ActivitySet}を追加します。
     * 
     * @param activitySet {@code ActivitySet}
     */
    public void addActivitySet(BuriActivitySetType activitySet) {
        activitySetsById.put(activitySet.getId(), activitySet);
    }

    /**
     * 指定された{@code ActivitySet}のIDから、対応する{@link BuriActivityType}を返します。
     * 
     * @param actId {@code ActivitySet}のID
     * @return 対応する{@link BuriActivityType}
     */
    public BuriActivitySetType getActivitySet(String actId) {
        return (BuriActivitySetType) activitySetsById.get(actId);
    }

    /**
     * 読み込み終了を表す文字列
     */
    public static String setupEnd_OOUOFIN = "";

    /**
     * 読み込み終了処理を行います。
     * 
     * @param packageType この{@code WorkflowProcess}が所属する{@link BuriPackageType}
     */
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

    /**
     * この{@code WorkflowProcess}が所属する{@code Package}を返します。
     * 
     * @return この{@code WorkflowProcess}が所属する{@code Package}
     */
    public BuriPackageType getPackageType() {
        return packageType;
    }

    /**
     * この{@code WorkflowProcess}が所属する{@code Package}を登録します。
     * 
     * @param packageType この{@code WorkflowProcess}が所属する{@code Package}
     */
    public void setPackageType(BuriPackageType packageType) {
        this.packageType = packageType;
        updateStartActivites();
    }

    /**
     * 始点となるアクティビティのリストを更新します。
     */
    protected void updateStartActivites() {
        List<String> actIds = new ArrayList<String>(activitiesById.keySet());
        actIds.removeAll(transitionsByTo.keySet());
        for (String id : actIds) {
            BuriActivityType act = getActivityById(id);
            startActivities.add(act);
        }
    }

    /**
     * 始点となるアクティビティのリストを返します。
     * 
     * @return 始点となるアクティビティのリスト
     */
    public List<BuriActivityType> getStartActivitys() {
        return startActivities;
    }

    /**
     * {@code WorkflowProcess}の{@code PathType}を返します。
     * 
     * @return {@code WorkflowProcess}の{@code PathType}
     */
    public long getPathType() {
        return pathType;
    }

    /**
     * {@code WorkflowProcess}の{@code PathType}を登録します。
     * 
     * @param pathType {@code WorkflowProcess}の{@code PathType}
     */
    public void setPathType(long pathType) {
        this.pathType = pathType;
    }

    /**
     * {@code WorkflowProcess}の発効日時の{@link BuriValidFromType}を返します。
     * 
     * @return {@code WorkflowProcess}の発効日時の{@link BuriValidFromType}
     */
    public BuriValidFromType getFromType() {
        return fromType;
    }

    /**
     * {@code WorkflowProcess}の発効日時の{@link BuriValidFromType}の要素名
     */
    public static final String setFromType_ELEMENT = "ValidFrom";

    /**
     * {@code WorkflowProcess}の発効日時の{@link BuriValidFromType}を登録します。
     * 
     * @param fromType {@code WorkflowProcess}の発効日時の{@link BuriValidFromType}
     */
    public void setFromType(BuriValidFromType fromType) {
        this.fromType = fromType;
    }

    /**
     * {@code WorkflowProcess}の失効日時の{@llink BuriValidToType}を返します。
     * 
     * @return {@code WorkflowProcess}の失効日時の{@llink BuriValidToType}
     */
    public BuriValidToType getToType() {
        return toType;
    }

    /**
     * {@code WorkflowProcess}の失効日時の{@llink BuriValidToType}の要素名
     */
    public static final String setToType_ELEMENT = "ValidTo";

    /**
     * {@code WorkflowProcess}の失効日時の{@llink BuriValidToType}を登録します。
     * 
     * @param toType {@code WorkflowProcess}の失効日時の{@llink BuriValidToType}
     */
    public void setToType(BuriValidToType toType) {
        this.toType = toType;
    }

    /**
     * {@code WorkflowProcess}の発効日時を返します。
     * 
     * @return {@code WorkflowProcess}の発効日時
     */
    public Date getValidFrom() {
        return validFrom;
    }

    /**
     * {@code WorkflowProcess}の発効日時を登録します。
     * 
     * @param validFrom {@code WorkflowProcess}の発効日時
     */
    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    /**
     * {@code WorkflowProcess}の失効日時を返します。
     * 
     * @return {@code WorkflowProcess}の失効日時
     */
    public Date getValidTo() {
        return validTo;
    }

    /**
     * {@code WorkflowProcess}の失効日時を登録します。
     * 
     * @param validTo {@code WorkflowProcess}の失効日時
     */
    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    /*
     * @see java.lang.Object#toString()
     */
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
