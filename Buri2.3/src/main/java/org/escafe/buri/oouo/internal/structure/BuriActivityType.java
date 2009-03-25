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

import org.escafe.buri.oouo.internal.structure.util.ExtentedAttributeUtil;

/**
 * XPDLの{@code Activitty}要素を表すクラスです。
 * <p>
 * この{@code Activitty}要素は、フローのプロセスで書かれた状態の1つを表します。
 * </p>
 * <p>
 * 前のアクティビティからトランジションを経る事で遷移をしていきます。
 * そして、次のアクティビティに遷移するまで、そのアクティビティが有効と判断されます。
 * </p>
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
	private BuriActivityLimitType limitType;

	private String subFlow = null;
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

	public boolean isXorJoin() {
		if (ExtentedAttributeUtil.getExtendedAttribute(ExtentedAttribute,
				"XORJOIN") == null) {
			return false;
		}
		return true;
	}

	public String getSubFlow() {
		return subFlow;
	}

	public static final String setSubFlow_ELEMENT = "SubFlow";
	public static final String setSubFlow_ATTRI = "Id";

	public void setSubFlow(String subFlow) {
		this.subFlow = subFlow;
	}

	public String getBlockActivity() {
		return blockActivity;
	}

	public static final String setBlockActivity_ELEMENT = "BlockActivity";
	public static final String setBlockActivity_ATTRI = "BlockId";

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

	public String getLimit() {
		if (limitType == null) {
			return null;
		}
		return limitType.getLimit();
	}

	public BuriActivityLimitType getLimitType() {
		return limitType;
	}

	public static final String setLimitType_ELEMENT = "Limit";

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
