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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.escafe.buri.common.util.MultiValueMap;

/**
 * XPDLの{@code ActivitySet}要素を表すクラスです。
 * <p>
 * この{@code ActivitySet}には、フローから参照するフローを定義できます。
 * フローのサブルーチンとなるもので、アクティビティやトランジション等も通常のフローと同様に定義されます。
 * </p>
 * <p>
 * {@code ActivitySet}のスキーマは以下のように定義されています。
 * <pre>{@code <xsd:element name="ActivitySet">
 *     <xsd:complexType>
 *         <xsd:sequence>
 *             <xsd:element ref="xpdl:Activities" minOccurs="0"/>
 *             <xsd:element ref="xpdl:Transitions" minOccurs="0"/>
 *             <xsd:element ref="xpdl:Object" minOccurs="0"/>
 *             <xsd:any namespace="##other" processContents="lax" minOccurs="0" maxOccurs="unbounded"/>
 *         </xsd:sequence>
 *         <xsd:attribute name="Id" type="xpdl:Id" use="required"/>
 *         <xsd:attribute name="Name" type="xsd:string" use="optional">
 *             <xsd:annotation>
 *                 <xsd:documentation source="added to XPDL 2.0"/>
 *             </xsd:annotation>
 *         </xsd:attribute>
 *         <xsd:attribute name="AdHoc" type="xsd:boolean" use="optional" default="false">
 *             <xsd:annotation>
 *                 <xsd:documentation>BPMN: for Embedded subprocess</xsd:documentation>
 *             </xsd:annotation>
 *         </xsd:attribute>
 *         <xsd:attribute name="AdHocOrdering" use="optional" default="Parallel">
 *             <xsd:annotation>
 *                 <xsd:documentation>BPMN: for Embedded subprocess</xsd:documentation>
 *             </xsd:annotation>
 *             <xsd:simpleType>
 *                 <xsd:restriction base="xsd:NMTOKEN">
 *                     <xsd:enumeration value="Sequential"/>
 *                     <xsd:enumeration value="Parallel"/>
 *                 </xsd:restriction>
 *             </xsd:simpleType>
 *         </xsd:attribute>
 *         <xsd:attribute name="AdHocCompletionCondition" type="xsd:string" use="optional">
 *             <xsd:annotation>
 *                 <xsd:documentation>BPMN: for Embedded subprocess</xsd:documentation>
 *             </xsd:annotation>
 *         </xsd:attribute>
 *         <xsd:attribute name="DefaultStartActivityId" type="xpdl:IdRef" use="optional"/>
 *         <xsd:anyAttribute namespace="##other" processContents="lax"/>
 *     </xsd:complexType>
 *     <xsd:key name="ActivityIds.ActivitySet">
 *         <xsd:selector xpath="./xpdl:Activities/xpdl:Activity"/>
 *         <xsd:field xpath="@Id"/>
 *     </xsd:key>
 *     <xsd:key name="TransitionIds.ActivitySet">
 *         <xsd:selector xpath="./xpdl:Transitions/xpdl:Transition"/>
 *         <xsd:field xpath="@Id"/>
 *     </xsd:key>
 *     <xsd:keyref name="DefaultStartActivityIdRef.ActivitySet" refer="xpdl:ActivityIds.ActivitySet">
 *         <xsd:selector xpath="."/>
 *         <xsd:field xpath="@DefaultStartActivityId"/>
 *     </xsd:keyref>
 *     <xsd:keyref name="TransitionFromRef.ActivitySet" refer="xpdl:ActivityIds.ActivitySet">
 *         <xsd:selector xpath="./xpdl:Transitions/xpdl:Transition"/>
 *         <xsd:field xpath="@From"/>
 *     </xsd:keyref>
 *     <xsd:keyref name="TransitionToRef.ActivitySet" refer="xpdl:ActivityIds.ActivitySet">
 *         <xsd:selector xpath="./xpdl:Transitions/xpdl:Transition"/>
 *         <xsd:field xpath="@To"/>
 *     </xsd:keyref>
 *     <xsd:keyref name="TransitionRefIdRef.ActivitySet" refer="xpdl:TransitionIds.ActivitySet">
 *         <xsd:selector xpath="./xpdl:Activities/xpdl:Activity/xpdl:TransitionRestrictions/xpdl:TransitionRestriction/xpdl:Split/xpdl:TransitionRefs/xpdl:TransitionRef"/>
 *         <xsd:field xpath="@Id"/>
 *     </xsd:keyref>
 *     <!-- check that the default start activity id exists -->
 *     <!-- check that the from and to specified in a transition exists -->
 *     <!-- check that the id specified in a transitionref exists -->
 * </xsd:element>}</pre>
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/04/03
 *
 */
public class BuriActivitySetType {
    /**
     * {@code ActivitySet}のID
     */
    private String id;
    /**
     * {@code ActivitySet}に所属する{@code Activity}のIDと{@link BuriActivityType}を紐づけて管理する{@link Map}
     */
    private Map<String, BuriActivityType> activityById = new HashMap<String, BuriActivityType>();
    /**
     * {@code ActivitySet}に所属する{@code Activity}の名前と{@link BuriActivityType}を紐づけて管理する{@link MultiValueMap}
     */
    private MultiValueMap<BuriActivityType> activityByName = new MultiValueMap<BuriActivityType>();
    /**
     * {@code ActivitySet}に所属する{@code Transition}のリスト
     */
    private List<BuriTransitionType> transition = new ArrayList<BuriTransitionType>();
    /**
     * 接続元の{@code Transition}のIDと{@link BuriTransitionType}を紐づけて管理する{@link MultiValueMap}
     */
    private MultiValueMap<BuriTransitionType> transitionByFrom = new MultiValueMap<BuriTransitionType>();
    /**
     * 接続先の{@code Transition}のIDの一覧
     */
    private Set<String> transToId = new HashSet<String>();
    /**
     * 接続元の{@link BuriActivityType}のリスト
     */
    private List<BuriActivityType> startActivitys = new ArrayList<BuriActivityType>();

    /**
     * {@code ActivitySet}を持つ{@code WorkflowProcess}
     */
    private BuriWorkflowProcessType processType = null;
    /**
     * {@code ActivitySet}の要素名
     */
    public final static String OOUOTHIS = "ActivitySet";

    /**
     * {@code ActivitySet}のIDの属性名
     */
    public final static String setId_ATTRI = "Id";

    /**
     * {@code ActivitySet}のIDを返します。
     * 
     * @return {@code ActivitySet}のID
     */
    public String getId() {
        return id;
    }

    /**
     * {@code ActivitySet}のIDを登録します。
     * 
     * @param id {@code ActivitySet}のID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * {@code Activity}の要素名
     */
    public static final String addActivity_ELEMENT = "Activity";

    /**
     * {@code Activity}要素の{@link BuriActivityType}を追加します。
     * 
     * @param act {@code Activity}要素の{@link BuriActivityType}
     */
    public void addActivity(BuriActivityType act) {
        activityById.put(act.getId(), act);
        activityByName.put(act.getName(), act);
    }

    /**
     * 指定されたIDに対応する{@link BuriActivityType}を返します。
     * 
     * @param actId {@code Activity}のID
     * @return {@link BuriActivityType}
     */
    public BuriActivityType getActivityById(String actId) {
        return activityById.get(actId);
    }

    /**
     * 指定された名前に対応する{@link BuriActivityType}を返します。
     * <p>
     * 返される{@link BuriActivityType}は複数件存在する可能性があります。
     * </p>
     * 
     * @param actName {@code Activity}の名前
     * @return {@link BuriActivityType}のリスト
     */
    public List<BuriActivityType> getActivityByName(String actName) {
        return activityByName.get(actName);
    }

    /**
     * {@code Transition}の要素名
     */
    public static final String addTransition_ELEMENT = "Transition";

    /**
     * {@code Transition}要素の{@code BuriTransitionType}を追加します。
     * 
     * @param transition {@code Transition}要素の{@code BuriTransitionType}
     */
    public void addTransition(BuriTransitionType transition) {
        this.transition.add(transition);
        transitionByFrom.put(transition.getFrom(), transition);
        transToId.add(transition.getTo());
    }

    /**
     * 指定されたIDから接続元の{@code Transition}の{@link BuriTransitionType}のリストを返します。
     * 
     * @param actId 接続元の{@code Transition}のID
     * @return {@link BuriTransitionType}のリスト
     */
    public List<BuriTransitionType> getRefTransition(String actId) {
        return transitionByFrom.get(actId);
    }

    /**
     * 読み込み終了を表す文字列
     */
    public static String setupEnd_OOUOFIN = "";

    /**
     * 読み込み終了処理を行います。
     * 
     * @param processType この{@code ActivitySet}を含んだ{@link BuriWorkflowProcessType}
     */
    public void setupEnd(BuriWorkflowProcessType processType) {
        this.processType = processType;
        updateStartActivites();
    }

    /**
     * {@code ActivitySet}を持つ{@code WorkflowProcess}を返します。
     * 
     * @return {@code ActivitySet}を持つ{@code WorkflowProcess}
     */
    public BuriWorkflowProcessType getProcessType() {
        return processType;
    }

    /**
     * {@code ActivitySet}を持つ{@code WorkflowProcess}を登録します。
     * 
     * @param processType {@code ActivitySet}を持つ{@code WorkflowProcess}
     */
    public void setProcessType(BuriWorkflowProcessType processType) {
        this.processType = processType;
    }

    /**
     * 接続元の{@link BuriActivityType}のリストを更新します。
     * <p>
     * 保持しているアクティビティのIDのリストを元に、{@code startActivitys}を更新します。
     * </p>
     */
    protected void updateStartActivites() {
        List<String> actIds = new ArrayList<String>(activityById.keySet());
        actIds.removeAll(transToId);
        for (String id : actIds) {
            BuriActivityType act = getActivityById(id);
            startActivitys.add(act);
        }
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer("\n  [");
        buff.append("    id=").append(id);
        buff.append("    -----ACTIVITY----------\n");
        buff.append("    /activity=").append(activityById).append("\n");
        buff.append("    -----ACTIVITY SET transition----------\n");
        buff.append("    /transition=").append(transition).append("\n");
        buff.append("    -----ACTIVITY SET START ACTIVITY----------\n");
        buff.append("    /").append(startActivitys).append("\n");
        buff.append("  ]\n\n");
        return buff.toString();
    }

}
