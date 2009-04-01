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
package org.escafe.buri.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.escafe.buri.oouo.internal.structure.BuriActivityType;
import org.escafe.buri.oouo.internal.structure.BuriPackageType;
import org.escafe.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.seasar.framework.util.StringUtil;

/**
 * 実行可能なパスとしてパッケージ、プロセス、アクティビティを管理するDtoです。
 * <p>
 * このクラスは1つのプロセスに対して1つのインスタンスを使用します。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2005/06/04
 */
public class BuriRealPath {
	/**
	 * フローのパッケージID/名
	 */
	private String workflowPackage = "";

	/**
	 * フローのプロセスID/名
	 */
	private String workflowProcess = "";

	/**
	 * フローのプロセスに所属するアクティビティID/名のリスト
	 */
	private List<String> activity = new ArrayList<String>();

	/**
	 * 実行対象パス
	 * <p>
	 * パッケージID/名.プロセスID/名[.アクティビティID/名]
	 * </p>
	 */
	private String pathStr = "";

	/**
	 * 実行可能なパスとしてパッケージ、プロセス、アクティビティを管理するDtoのコンストラクタです。
	 * <p>
	 * 空文字でインスタンスを生成します。
	 * </p>
	 */
	public BuriRealPath() {
		this("");
	}

	/**
	 * 実行可能なパスとしてパッケージ、プロセス、アクティビティを管理するDtoのコンストラクタです。
	 * 
	 * @param path
	 *            パッケージID/名.プロセスID/名[.アクティビティID/名]
	 */
	public BuriRealPath(String path) {
		if (StringUtil.isEmpty(path)) {
			return;
		}
		StringTokenizer st = new StringTokenizer(path, ".");
		setPathFromToken(st);
		pathStr = path;
	}

	/**
	 * アクティビティID/名を変更します。
	 * <p>
	 * {@code pos}のアクティビティID/名が存在しない場合は、新たにアクティビティID/名のリストに追加します。
	 * </p>
	 * 
	 * @param newPath
	 *            新しいアクティビティID/名
	 * @param pos
	 *            アクティビティID/名が登録されている{@code pos}
	 */
	public void changePath(String newPath, int pos) {
		if (StringUtil.isEmpty(newPath)) {
			newPath = "";
		}
		if (activity.size() == 0) {
			/*
			 * アクティビティID/名が登録されていない場合
			 */
			getActivity().add(newPath);
		} else {
			/*
			 * アクティビティID/名が登録されている場合
			 */
			assert pos < activity.size();
			activity.set(pos, newPath);
		}
		setupPlainName();
	}

	/**
	 * {@link BuriActivityType}からアクティビティID/名を変更します。
	 * <p>
	 * {@code pos}のアクティビティID/名が存在しない場合は、新たにアクティビティID/名のリストに追加します。
	 * </p>
	 * 
	 * @param newAct
	 *            新しいアクティビティ
	 * @param pos
	 *            アクティビティID/名が登録されている{@code pos}
	 */
	public void changePath(BuriActivityType newAct, int pos) {
		changePath(newAct.getName(), pos);
	}

	/**
	 * オブジェクトの複製を作成して返します。
	 * 
	 * @return 同じ内容の{@link BuriRealPath}オブジェクト
	 */
	public BuriRealPath copyRealPath() {
		BuriRealPath clonePath = new BuriRealPath();
		clonePath.workflowPackage = workflowPackage;
		clonePath.workflowProcess = workflowProcess;
		clonePath.activity.addAll(activity);
		clonePath.pathStr = pathStr;
		return clonePath;
	}

	/**
	 * アクティビティID/名のリストを返します。
	 * 
	 * @return アクティビティID/名のリスト
	 */
	public List<String> getActivity() {
		return new ArrayList<String>(activity);
	}

	/**
	 * 実行対象パスを返します。
	 * 
	 * @return 実行対象パス
	 */
	public String getPlainName() {
		return pathStr;
	}

	/**
	 * フローのパッケージID/名を返します。
	 * 
	 * @return フローのパッケージID/名
	 */
	public String getWorkflowPackage() {
		return workflowPackage;
	}

	/**
	 * フローのプロセスID/名を返します。
	 * 
	 * @return フローのプロセスID/名
	 */
	public String getWorkflowProcess() {
		return workflowProcess;
	}

	/*
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hashCode = 1;
		hashCode =
		    31
		        * hashCode
		        + (workflowPackage == null ? 0 : workflowPackage.hashCode());
		hashCode =
		    31
		        * hashCode
		        + (workflowProcess == null ? 0 : workflowProcess.hashCode());
		hashCode = 31 * hashCode + (activity == null ? 0 : activity.hashCode());
		hashCode = 31 * hashCode + (pathStr == null ? 0 : pathStr.hashCode());
		return hashCode;
	}

	/**
	 * {@link BuriActivityType}から新しいアクティビティを追加します。
	 * 
	 * @param newAct
	 *            新しいアクティビティの{@link BuriActivityType}
	 */
	public void moveChildPath(BuriActivityType newAct) {
		moveChildPath(newAct.getName());
		setupPlainName();
	}

	/**
	 * 新しいアクティビティID/名を追加します。
	 * 
	 * @param childPath
	 *            新しいアクティビティID/名
	 */
	public void moveChildPath(String childPath) {
		activity.add(childPath);
		setupPlainName();
	}

	/**
	 * 積み上げたアクティビティを減らして実行対象のパスを移動させます。
	 */
	public void moveUpPath() {
		if (activity.size() == 0) {
			return;
		} else {
			activity.remove(activity.size() - 1);
		}
		setupPlainName();
	}

	/**
	 * アクティビティID/名のリストを登録します。
	 * 
	 * @param activity
	 *            アクティビティID/名のリスト
	 */
	public void setActivity(List<String> activity) {
		activity.clear();
		activity.addAll(activity);
		setupPlainName();
	}

	/**
	 * {@link BuriPackageType}からフローのパッケージID/名を登録します。
	 * 
	 * @param packageDoc
	 *            フローの{@link BuriPackageType}
	 */
	public void setWorkflowPackage(BuriPackageType packageDoc) {
		setWorkflowPackage(packageDoc.getName());
		setupPlainName();
	}

	/**
	 * フローのパッケージID/名を登録します。
	 * 
	 * @param workflow
	 *            フローのパッケージID/名
	 */
	public void setWorkflowPackage(String workflow) {
		if (StringUtil.isEmpty(workflow)) {
			workflowPackage = "";
		} else {
			workflowPackage = workflow;
		}
		setupPlainName();
	}

	/**
	 * {@link BuriWorkflowProcessType}からフローのプロセスID/名を登録します。
	 * 
	 * @param wkfProcess
	 *            フローの{@link BuriWorkflowProcessType}
	 */
	public void setWorkflowProcess(BuriWorkflowProcessType wkfProcess) {
		setWorkflowProcess(wkfProcess.getName());
		setupPlainName();
	}

	/**
	 * フローのプロセスID/名を登録します。
	 * 
	 * @param process
	 *            フローのプロセスID/名
	 */
	public void setWorkflowProcess(String process) {
		if (StringUtil.isEmpty(process)) {
			workflowProcess = "";
		} else {
			workflowProcess = process;
		}
		setupPlainName();
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return pathStr;
	}

	/**
	 * 指定されたパスの構成要素が空ではないかどうかを判定します。
	 * 
	 * @param pathName
	 *            パスの構成要素（パッケージID/名、プロセスID/名、アクティビティID/名）
	 * @return パスの構成要素が空ではない場合{@code true}、そうでない場合は{code false}
	 */
	protected boolean hasPathName(String pathName) {
		return pathName.length() > 0;
	}

	/**
	 * インスタンスのパスに空の構成要素がないかどうかを判定します。
	 * 
	 * @return パッケージID/名、プロセスID/名、アクティビティID/名のいずれかに空の要素があった場合{@code false}
	 *         、そうでない場合は{@code true}
	 */
	protected boolean isCorrect() {
		if (hasPathName(getWorkflowPackage())) {
			if (hasPathName(getWorkflowProcess())) {
				return isCorrectActivitys(getActivity());
			}
		}
		return false;
	}

	/**
	 * 空のアクティビティが指定されているかどうかを判定します。
	 * 
	 * @param activitis
	 *            アクティビティID/名のリスト
	 * @return 空のアクティビティがない場合は{@code true}、そうでない場合は{@code false}
	 */
	protected boolean isCorrectActivitys(List<String> activitis) {
		for (String pathName : activitis) {
			if (!hasPathName(pathName)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * パスとして渡された文字列をパッケージID/名、プロセスID/名とアクティビティID/名のリストに分解してセットします。
	 * 
	 * @param st
	 *            区切り文字を与えた{@link StringTokenizer}
	 */
	protected void setPathFromToken(StringTokenizer st) {
		if (st.hasMoreTokens()) {
			workflowPackage = st.nextToken();
		}
		if (st.hasMoreTokens()) {
			workflowProcess = st.nextToken();
		}
		List<String> activity = new ArrayList<String>();
		while (st.hasMoreTokens()) {
			activity.add(st.nextToken());
		}
		this.activity = activity;
		setupPlainName();
	}

	/**
	 * 実行対象パスを最新の状態に更新します。
	 */
	protected void setupPlainName() {
		String path = workflowPackage + "." + workflowProcess;
		String act = "";
		String koron = "";
		for (String _act : activity) {
			act = act + koron + _act;
			koron = ".";
		}
		pathStr = path + koron + act;
	}
}
