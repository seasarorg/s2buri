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
import java.util.List;

import javax.ejb.TimerService;

import org.escafe.buri.oouo.internal.structure.util.ExtentedAttributeUtil;

/**
 * XPDLの{@code Activity}要素を表すクラスです。
 * <p>
 * この{@code Activity}要素は、フローのプロセスで書かれた状態の1つを表します。
 * </p>
 * <p>
 * 前のアクティビティからトランジションを経る事で遷移をしていきます。
 * そして、次のアクティビティに遷移するまで、そのアクティビティが有効と判断されます。
 * </p>
 * <p>
 * {@code Activity}のスキーマは以下のように定義されています。
 * <pre>{@code <xsd:element name="Activity">
 *     <xsd:annotation>
 *         <xsd:documentation>BPMN extension</xsd:documentation>
 *     </xsd:annotation>
 *     <xsd:complexType>
 *         <xsd:sequence>
 *             <xsd:element ref="xpdl:Description" minOccurs="0"/>
 *             <xsd:element ref="xpdl:Limit" minOccurs="0"/>
 *             <xsd:choice minOccurs="0">
 *                 <xsd:element ref="xpdl:Route"/>
 *                 <xsd:element ref="xpdl:Implementation">
 *                     <xsd:annotation>
 *                         <xsd:documentation>BPMN: corresponds to an activity, which could be a task or subprocess.[Suggest change element to BpmnActivity, since there is an attribute Implementation which means something else entirely.]</xsd:documentation>
 *                     </xsd:annotation>
 *                 </xsd:element>
 *                 <xsd:choice minOccurs="0">
 *                     <xsd:element ref="deprecated:BlockActivity"/>
 *                     <xsd:element ref="xpdl:BlockActivity"/>
 *                 </xsd:choice>
 *                 <xsd:element ref="xpdl:Event">
 *                     <xsd:annotation>
 *                         <xsd:documentation>BPMN: Identifies XPDL activity as a BPMN event.</xsd:documentation>
 *                     </xsd:annotation>
 *                 </xsd:element>
 *             </xsd:choice>
 *             <xsd:element ref="xpdl:Transaction" minOccurs="0"/>
 *             <xsd:element ref="xpdl:Performers" minOccurs="0"/>
 *             <xsd:element ref="xpdl:Performer" minOccurs="0">
 *                 <xsd:annotation>
 *                     <xsd:documentation>Deprecated from XPDL2.0. Must be a child of  Performers</xsd:documentation>
 *                 </xsd:annotation>
 *             </xsd:element>
 *             <xsd:element ref="deprecated:StartMode" minOccurs="0"/>
 *             <xsd:element ref="deprecated:FinishMode" minOccurs="0"/>
 *             <xsd:element ref="xpdl:Priority" minOccurs="0"/>
 *             <xsd:choice minOccurs="0">
 *                 <xsd:element ref="deprecated:Deadline" minOccurs="0" maxOccurs="unbounded"/>
 *                 <xsd:element ref="xpdl:Deadline" minOccurs="0" maxOccurs="unbounded"/>
 *             </xsd:choice>
 *             <xsd:element ref="xpdl:SimulationInformation" minOccurs="0"/>
 *             <xsd:element ref="xpdl:Icon" minOccurs="0"/>
 *             <xsd:element ref="xpdl:Documentation" minOccurs="0"/>
 *             <xsd:element ref="xpdl:TransitionRestrictions" minOccurs="0"/>
 *             <xsd:element ref="xpdl:ExtendedAttributes" minOccurs="0"/>
 *             <xsd:element ref="xpdl:DataFields" minOccurs="0"/>
 *             <xsd:element ref="xpdl:InputSets" minOccurs="0"/>
 *             <xsd:element ref="xpdl:OutputSets" minOccurs="0"/>
 *             <xsd:element ref="xpdl:IORules" minOccurs="0"/>
 *             <xsd:element ref="xpdl:Loop" minOccurs="0"/>
 *             <xsd:element ref="xpdl:Assignments" minOccurs="0"/>
 *             <xsd:element ref="xpdl:Object" minOccurs="0"/>
 *             <xsd:element ref="xpdl:NodeGraphicsInfos" minOccurs="0"/>
 *             <xsd:choice minOccurs="0">
 *                 <xsd:sequence>
 *                     <xsd:element name="Extensions"/>
 *                     <xsd:any namespace="##other" processContents="lax" minOccurs="0" maxOccurs="unbounded"/>
 *                 </xsd:sequence>
 *             </xsd:choice>
 *         </xsd:sequence>
 *         <xsd:attribute name="Id" type="xpdl:Id" use="required">
 *             <xsd:annotation>
 *                 <xsd:documentation>BPMN: unique identifier of the flow object</xsd:documentation>
 *             </xsd:annotation>
 *         </xsd:attribute>
 *         <xsd:attribute name="IsForCompensation" type="xsd:boolean" use="optional"/>
 *         <xsd:attribute name="Name" type="xsd:string" use="optional">
 *             <xsd:annotation>
 *                 <xsd:documentation>BPMN: label of the flow object in the diagram</xsd:documentation>
 *             </xsd:annotation>
 *         </xsd:attribute>
 *         <xsd:attribute name="StartActivity" type="xsd:boolean" use="optional">
 *             <xsd:annotation>
 *                 <xsd:documentation> Designates the first activity to be executed when the process is instantiated. Used when there is no other way to determine this Conflicts with BPMN StartEvent and no process definition should use both.</xsd:documentation>
 *             </xsd:annotation>
 *         </xsd:attribute>
 *         <xsd:attribute name="Status" use="optional" default="None">
 *             <xsd:annotation>
 *                 <xsd:documentation> BPMN: Status values are assigned during execution. Status can be treated as a property and used in expressions local to an Activity. It is unclear that status belongs in the XPDL document.</xsd:documentation>
 *             </xsd:annotation>
 *             <xsd:simpleType>
 *                 <xsd:restriction base="xsd:NMTOKEN">
 *                     <xsd:enumeration value="None"/>
 *                     <xsd:enumeration value="Ready"/>
 *                     <xsd:enumeration value="Active"/>
 *                     <xsd:enumeration value="Cancelled"/>
 *                     <xsd:enumeration value="Aborting"/>
 *                     <xsd:enumeration value="Aborted"/>
 *                     <xsd:enumeration value="Completing"/>
 *                     <xsd:enumeration value="Completed"/>
 *                 </xsd:restriction>
 *             </xsd:simpleType>
 *         </xsd:attribute>
 *         <xsd:attribute name="StartMode">
 *             <xsd:simpleType>
 *                 <xsd:restriction base="xsd:NMTOKEN">
 *                     <xsd:enumeration value="Automatic"/>
 *                     <xsd:enumeration value="Manual"/>
 *                 </xsd:restriction>
 *             </xsd:simpleType>
 *         </xsd:attribute>
 *         <xsd:attribute name="FinishMode">
 *             <xsd:simpleType>
 *                 <xsd:restriction base="xsd:NMTOKEN">
 *                     <xsd:enumeration value="Automatic"/>
 *                     <xsd:enumeration value="Manual"/>
 *                 </xsd:restriction>
 *             </xsd:simpleType>
 *         </xsd:attribute>
 *         <xsd:attribute name="StartQuantity" type="xsd:integer" use="optional" default="1"/>
 *         <xsd:attribute name="CompletionQuantity" type="xsd:integer" use="optional" default="1"/>
 *         <xsd:attribute name="IsATransaction" type="xsd:boolean" use="optional" default="false"/>
 *         <xsd:anyAttribute namespace="##other" processContents="lax"/>
 *     </xsd:complexType>
 * </xsd:element>}</pre>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/03/20
 */
public class BuriActivityType {
	/**
	 * アクティビティのID
	 */
	private String id;
	/**
	 * アクティビティの名前
	 */
	private String name;
	/**
	 * アクティビティの実行者
	 */
	private BuriPerformerType performer;
	/**
	 * アクティビティの{@code StartMode}
	 */
	private BuriStartModeType startMode;
	/**
	 * アクティビティの{@code FinishMode}
	 */
	private BuriFinishModeType finishMode;
	/**
	 * アクティビティに紐づく{@code Tool}の{@link BuriToolType}のリスト
	 */
	private List<BuriToolType> toolList = new ArrayList<BuriToolType>();
	/**
	 * アクティビティに紐づく{@code Split}の{@link BuriSplitType}
	 */
	private BuriSplitType split;
	/**
	 * アクティビティに紐づく{@code Join}の{@link BuriSplitType}
	 */
	private BuriJoinType join;
	/**
	 * アクティビティの{@code Split}が{@code And}か{@code Xor}かを判定するフラグ
	 * <p>
	 * {@code And}の場合は{@code true}、そうでない場合は{@code false}
	 * </p>
	 */
	private Boolean isSplitAnd = null;
	/**
	 * アクティビティの{@code Join}が{@code And}か{@code Xor}かを判定するフラグ
	 * <p>
	 * {@code And}の場合は{@code true}、そうでない場合は{@code false}
	 * </p>
	 */
	private Boolean isJoinAnd = null;
	/**
	 * アクティビティに紐づく{@code ExtendedAttribute}要素の{@link BuriExtendedAttributeType}のリスト
	 */
	private List<BuriExtendedAttributeType> ExtentedAttribute = new ArrayList<BuriExtendedAttributeType>();
	/**
	 * アクティビティが所属するフローのプロセスの{@link BuriWorkflowProcessType}
	 */
	private BuriWorkflowProcessType process;
	/**
	 * アクティビティに設定された{@code ActivitySet}
	 */
	private BuriActivitySetType activitySet;
	/**
	 * アクティビティに設定された{@code Limit}
	 */
	private BuriActivityLimitType limitType;

	/**
	 * 従属するサブフローが存在した場合のサブフローのID
	 */
	private String subFlow = null;
	/**
	 * 従属するサブフローへのエントリポイントとなるアクティビティのID
	 */
	private String blockActivity = null;

	/**
	 * アクティビティの要素の要素名
	 */
	public final static String OOUOTHIS = "Activity";

	/**
	 * アクティビティのIDを返します。
	 * 
	 * @return アクティビティのID
	 */
	public String getId() {
		return id;
	}

	/**
	 * アクティビティのIDの属性名
	 */
	public final static String setId_ATTRI = "Id";

	/**
	 * アクティビティのIDを登録します。
	 * 
	 * @param id アクティビティのID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * アクティビティの名前を返します。
	 * 
	 * @return アクティビティの名前
	 */
	public String getName() {
		return name;
	}

	/**
	 * アクティビティの名前の属性名
	 */
	public final static String setName_ATTRI = "Name";

	/**
	 * アクティビティの名前を登録します。
	 * 
	 * @param name アクティビティの名前
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * アクティビティの実行者を返します。
	 * 
	 * @return アクティビティの実行者
	 */
	public BuriPerformerType getPerformer() {
		return performer;
	}

	/**
	 * アクティビティが所属するステークホルダーの名前を返します。
	 * 
	 * @return ステークホルダーの名前
	 */
	public String getParticipantName() {
		BuriParticipantType participantType = process
				.getParticipantById(performer.getId());
		String participantName = participantType.getName();
		return participantName;
	}

	/**
	 * アクティビティが所属するステークホルダーの種別を返します。
	 * 
	 * @return ステークホルダーの種別
	 */
	public String getParticipantType() {
		BuriParticipantType participantType = process
				.getParticipantById(performer.getId());
		return participantType.getType();
	}

	/**
	 * アクティビティの実行者の要素名
	 */
	public final static String setPerformer_ELEMENT = "Performer";

	/**
	 * アクティビティの実行者を登録します。
	 * 
	 * @param performer アクティビティの実行者
	 */
	public void setPerformer(BuriPerformerType performer) {
		this.performer = performer;
	}

	/**
	 * アクティビティの{@code StartMode}を返します。
	 * 
	 * @return アクティビティの{@code StartMode}
	 */
	public BuriStartModeType getStartMode() {
		return startMode;
	}

	/**
	 * アクティビティの{@code StartMode}の要素名
	 */
	public final static String setStartMode_ELEMENT = "StartMode";

	/**
	 * アクティビティの{@code StartMode}を登録します。
	 * 
	 * @param startMode アクティビティの{@code StartMode}
	 */
	public void setStartMode(BuriStartModeType startMode) {
		this.startMode = startMode;
	}

	/**
	 * アクティビティの{@code StartMode}が{@code ManualMode}かどうかを判定します。
	 * <p>
	 * アクティビティの{@code StartMode}が{@code null}の場合は{@code false}を返します。
	 * </p>
	 * 
	 * @return {@code ManualMode}の場合{@code true}、そうでない場合は{@code false}
	 */
	public boolean isStartModeManual() {
		if (startMode == null) {
			return false;
		}
		return startMode.isManual();
	}

	/**
	 * アクティビティの{@code FinishMode}を返します。
	 * 
	 * @return アクティビティの{@code FinishMode}
	 */
	public BuriFinishModeType getFinishMode() {
		return finishMode;
	}

	/**
	 * アクティビティの{@code FinishMode}の要素名
	 */
	public final static String setFinishMode_ELEMENT = "FinishMode";

	/**
	 * アクティビティの{@code FinishMode}を登録します。
	 * 
	 * @param finishMode アクティビティの{@code FinishMode}
	 */
	public void setFinishMode(BuriFinishModeType finishMode) {
		this.finishMode = finishMode;
	}

	/**
	 * アクティビティの{@code FinishMode}が{@code ManualMode}かどうかを判定します。
	 * <p>
	 * アクティビティの{@code FinishMode}が{@code null}の場合は{@code false}を返します。
	 * </p>
	 * 
	 * @return {@code ManualMode}の場合{@code true}、そうでない場合は{@code false}
	 */
	public boolean isFinishModeManual() {
		if (finishMode == null) {
			return false;
		}
		return finishMode.isManual();
	}

	/**
	 * アクティビティに紐づく{@code Tool}の要素名
	 */
	public final static String addTool_ELEMENT = "Tool";

	/**
	 * アクティビティに紐づく{@code Tool}の{@link BuriToolType}を追加します。
	 * 
	 * @param tool アクティビティに紐づく{@code Tool}の{@link BuriToolType}
	 */
	public void addTool(BuriToolType tool) {
		this.toolList.add(tool);
	}

	/**
	 * アクティビティに紐づく{@code Tool}の{@link BuriToolType}のリストを返します。
	 * 
	 * @return アクティビティに紐づく{@code Tool}の{@link BuriToolType}のリスト
	 */
	public List<BuriToolType> getTools() {
		return toolList;
	}

	/**
	 * アクティビティに紐づく{@code ExtendedAttribute}要素の要素名
	 */
	public static final String addExtendedAttribute_ELEMENT = "ExtendedAttribute";

	/**
	 * アクティビティに紐づく{@code ExtendedAttribute}要素を追加します。
	 * 
	 * @param attri アクティビティに紐づく{@code ExtendedAttribute}要素
	 */
	public void addExtendedAttribute(BuriExtendedAttributeType attri) {
		ExtentedAttribute.add(attri);
	}

	/**
	 * アクティビティに紐づく{@code ExtendedAttribute}要素のオブジェクト化した{@link BuriExtendedAttributeType}のリストを返します。
	 * 
	 * @return アクティビティに紐づく{@code ExtendedAttribute}要素のオブジェクト化した{@link BuriExtendedAttributeType}のリスト
	 */
	public List<BuriExtendedAttributeType> getExtendedAttributeList() {
		return ExtentedAttribute;
	}

	/**
	 * アクティビティに所属する{@link BuriSplitType}を返します。
	 * 
	 * @return アクティビティに所属する{@link BuriSplitType}
	 */
	public BuriSplitType getSplit() {
		return split;
	}

	/**
	 * {@code Split}要素の要素名
	 */
	public static final String setSplit_ELEMENT = "Split";

	/**
	 * アクティビティに所属する{@link BuriSplitType}を登録します。
	 * 
	 * @param split アクティビティに所属する{@link BuriSplitType}
	 */
	public void setSplit(BuriSplitType split) {
		this.split = split;
	}

	/**
	 * アクティビティに所属する{@link BuriSplitType}が{@code AND}の値を持つかどうかを判定します。
	 * 
	 * @return {@code AND-Split}の場合{@code true}、そうでない場合は{@code false}
	 */
	public boolean isSplitAnd() {
		if (isSplitAnd == null) {
			isSplitAnd = Boolean.FALSE;
			if ((split != null) && split.isAnd()) {
				isSplitAnd = Boolean.TRUE;
			}
		}
		return isSplitAnd.booleanValue();
	}

	/**
	 * アクティビティに所属する{@link BuriJoinType}を返します。
	 * 
	 * @return アクティビティに所属する{@link BuriJoinType}
	 */
	public BuriJoinType getJoin() {
		return join;
	}

	/**
	 * アクティビティに所属する{@code Join}要素の要素名
	 */
	public static final String setJoin_ELEMENT = "Join";

	/**
	 * アクティビティに所属する{@code Join}の{@link BuriJoinType}を登録します。
	 * 
	 * @param join アクティビティに所属する{@code Join}の{@link BuriJoinType}
	 */
	public void setJoin(BuriJoinType join) {
		this.join = join;
	}

	/**
	 * アクティビティに所属する{@code Join}の{@link BuriJoinType}が
	 * {@code AND-Join}かどうかを判定します。
	 * 
	 * @return {@code AND-Join}の場合{@code true}、そうでない場合{@code false}
	 */
	public boolean isJoinAnd() {
		if (isJoinAnd == null) {
			isJoinAnd = Boolean.FALSE;
			if ((join != null) && join.isAnd()) {
				isJoinAnd = Boolean.TRUE;
			}
		}
		return isJoinAnd.booleanValue();
	}

	/**
	 * このアクティビティの{@code ExtentedAttribute}に、{@code "NOJOIN"}か
	 * {@code "NOXORJOIN"}が設定されているかどうかを判定します。
	 * 
	 * @return 設定されていた場合{@code true}、そうでない場合は{@code false}
	 */
	public boolean isNoJoin() {
		if (ExtentedAttributeUtil.getExtendedAttribute(ExtentedAttribute,
				"NOJOIN") == null) {
			if (ExtentedAttributeUtil.getExtendedAttribute(ExtentedAttribute,
					"NOXORJOIN") == null) {
				return false;
			}
		}
		return true;
	}

	/**
     * このアクティビティの{@code ExtentedAttribute}に、
     * {@code "NOXORJOIN"}が設定されているかどうかを判定します。
     * 
     * @return 設定されていた場合{@code true}、そうでない場合は{@code false}
	 */
	public boolean isXorJoin() {
		if (ExtentedAttributeUtil.getExtendedAttribute(ExtentedAttribute,
				"XORJOIN") == null) {
			return false;
		}
		return true;
	}

	/**
	 * 従属するサブフローが存在した場合のサブフローのIDを返します。
	 * 
	 * @return サブフローのID
	 */
	public String getSubFlow() {
		return subFlow;
	}

	/**
	 * {@code SubFlow}の要素名
	 */
	public static final String setSubFlow_ELEMENT = "SubFlow";
	/**
	 * {@code SubFlow}のIDの属性名
	 */
	public static final String setSubFlow_ATTRI = "Id";

	/**
	 * サブフローのIDを登録します。
	 * 
	 * @param subFlow サブフローのID
	 */
	public void setSubFlow(String subFlow) {
		this.subFlow = subFlow;
	}

	/**
	 * 従属するサブフローへのエントリポイントとなるアクティビティのIDを返します。
	 * 
	 * @return 従属するサブフローへのエントリポイントとなるアクティビティのID
	 */
	public String getBlockActivity() {
		return blockActivity;
	}

	/**
	 * 従属するサブフローへのエントリポイントとなるアクティビティの要素名
	 */
	public static final String setBlockActivity_ELEMENT = "BlockActivity";
	/**
	 * 従属するサブフローへのエントリポイントとなるアクティビティのIDの属性名
	 */
	public static final String setBlockActivity_ATTRI = "BlockId";

	/**
	 * 従属するサブフローへのエントリポイントとなるアクティビティのIDを登録します。
	 * 
	 * @param blockActivity 従属するサブフローへのエントリポイントとなるアクティビティのID
	 */
	public void setBlockActivity(String blockActivity) {
		this.blockActivity = blockActivity;
	}

	/**
	 * 読み込み終了を表す文字列
	 */
	public static final String setupEnd_OOUOFIN = "";

	/**
	 * 読み込み終了時の処理を行います。
	 * 
	 * @param parent このオブジェクトが所属する親オブジェクト
	 */
	public void setupEnd(Object parent) {
		if (parent instanceof BuriWorkflowProcessType) {
			this.process = (BuriWorkflowProcessType) parent;
		} else if (parent instanceof BuriActivitySetType) {
			this.activitySet = (BuriActivitySetType) parent;
		} else {
			assert false;
		}
	}

	/**
	 * アクティビティが所属するフローのプロセスの{@link BuriWorkflowProcessType}を登録します。
	 * 
	 * @return アクティビティが所属するフローのプロセスの{@link BuriWorkflowProcessType}
	 */
	public BuriWorkflowProcessType getWorkflowProcess() {
		return process;
	}

	/**
	 * アクティビティが所属するフローのプロセスの{@link BuriWorkflowProcessType}を返します。
	 * 
	 * @return アクティビティが所属するフローのプロセスの{@link BuriWorkflowProcessType}
	 */
	public BuriActivitySetType getActivitySet() {
		return activitySet;
	}

	/**
	 * アクティビティに設定された{@code Limit}の値を返します。
	 * <p>
	 * {@code Limit}は{@link TimerService}を使用した際にそのアクティビティを実行する日時を指定する為のパラメータです。
	 * </p>
	 * <p>
	 * 設定されていなかった場合は{@code null}を返します。
	 * </p>
	 * 
	 * @return {@code Limit}
	 */
	public String getLimit() {
		if (limitType == null) {
			return null;
		}
		return limitType.getLimit();
	}

	/**
	 * アクティビティに設定された{@code Limit}を返します。
	 * 
	 * @return 対応する{@link BuriActivityLimitType}
	 */
	public BuriActivityLimitType getLimitType() {
		return limitType;
	}

	/**
	 * アクティビティに設定された{@code Limit}の要素名
	 */
	public static final String setLimitType_ELEMENT = "Limit";

	/**
	 * アクティビティに設定された{@code Limit}を登録します。
	 * 
	 * @param limit アクティビティに設定された{@code Limit}
	 */
	public void setLimitType(BuriActivityLimitType limit) {
		this.limitType = limit;
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer("\n[");
		buff.append("id=").append(id);
		buff.append("/name=").append(name);
		buff.append("/limitType=").append(limitType);
		buff.append("/performer=").append(performer);
		buff.append("/subFlow=").append(subFlow);
		buff.append("/blockActivity=").append(blockActivity);
		buff.append("/").append(startMode);
		buff.append("/").append(finishMode);
		buff.append("/join=").append(join);
		buff.append("\n    /split=").append(split);
		buff.append("\n    /tool=").append(toolList);
		buff.append("\n    /ExtentedAttribute=").append(ExtentedAttribute);
		buff.append("]\n");
		return buff.toString();
	}

}
