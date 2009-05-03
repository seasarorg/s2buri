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

import org.escafe.buri.oouo.internal.structure.BuriActivityType;
import org.escafe.buri.oouo.internal.structure.BuriPackageType;
import org.escafe.buri.oouo.internal.structure.BuriWorkflowProcessType;

/**
 * パッケージ、プロセス、アクティビティのID、名前をそれぞれ保持するDtoです。
 * <p>
 * ぶりで使用するアクティビティのパスは、プロセッサやエンジンに対して指定する
 * 論理パスとぶりがコンパイルするクラス名に使用される物理パスの2種類が存在します。
 * このクラスはこの2つのパスを互いに補完しつつ、両方を管理する際に使用されます。
 * </p>
 * <p>
 * ぶりのパスは以下のようなフォーマットで表現されます。 <div><b>{@code パッケージ名.プロセス名[.アクティビティ名]}</b></div>
 * ぶりはXPDLで記述されたフローを実行するルールエンジンの役割を持っていますが、
 * ぶりはフローを都度読み込んで都度解釈するインタープリタ型に処理するのではなく、
 * 一度フローをJavaのバイトコードにコンパイルしてそのバイトコードを実行しています。 その場合に生成されるクラスは、以下のように表現されます。
 * <div><b>{@code パッケージID=package.プロセスID=ClassName.アクティビティID=MethodName}
 * </b></div>
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @author j5ik2o
 * @since 2005/05/13
 */
public class BuriPath {
	/**
	 * ぶり固有のテーブル{@code BuriPath}で使用されるID
	 * <p>
	 * 初期値は{@code 0}が設定されています。
	 * </p>
	 */
	private Long buriPathId = Long.valueOf(0L);

	/**
	 * プロセッサやエンジンが受け取る論理パス
	 */
	private BuriRealPath thisPath = new BuriRealPath();

	/**
	 * クラス名に使用される物理パス
	 */
	private BuriRealPath realPath = new BuriRealPath();

	/**
	 * XPDLで指定されるPathType
	 * <p>
	 * ぶりではこの値を活用していません。 XPDLの読み込み時に取得した値がそのままこのクラスに設定されます。
	 * </p>
	 */
	private Long pathType;

	/**
	 * パッケージ、プロセス、アクティビティのID、名前をそれぞれ保持するDtoのコンストラクタです。
	 * <p>
	 * 空文字でインスタンスを生成します。
	 * </p>
	 */
	public BuriPath() {
		this("");
	}

	/**
	 * パッケージ、プロセス、アクティビティのID、名前をそれぞれ保持するDtoのコンストラクタです。
	 * <p>
	 * プロセッサやエンジンが受け取るパスでインスタンスを生成します。
	 * </p>
	 * 
	 * @param path プロセッサやエンジンが受け取るパス
	 */
	public BuriPath(String path) {
		thisPath = new BuriRealPath(path);
	}

	/**
	 * パッケージ、プロセス、アクティビティのID、名前をそれぞれ保持するDtoのコンストラクタです。
	 * <p>
	 * プロセッサやエンジンが受け取るパスとクラス名に使用されるパスの両方からインスタンスを生成します。
	 * </p>
	 * 
	 * @param path プロセッサやエンジンが受け取るパス
	 * @param realStrPath クラス名に使用されるパス
	 */
	public BuriPath(String path, String realStrPath) {
		thisPath = new BuriRealPath(path);
		realPath = new BuriRealPath(realStrPath);
	}

	/**
	 * パッケージ、プロセス、アクティビティのID、名前をそれぞれ保持するDtoのコンストラクタです。
	 * <p>
	 * 2種類のパスとぶり固有のテーブル{@code BuriPath}のIDからインスタンスを生成します。
	 * </p>
	 * 
	 * @param path プロセッサやエンジンが受け取るパス
	 * @param realStrPath クラス名に使用されるパス
	 * @param pathId ぶり固有のテーブル{@code BuriPath}のID
	 */
	public BuriPath(String path, String realStrPath, long pathId) {
		thisPath = new BuriRealPath(path);
		realPath = new BuriRealPath(realStrPath);
		buriPathId = pathId;
	}

	/**
	 * パッケージ、プロセス、アクティビティのID、名前をそれぞれ保持するDtoのコンストラクタです。
	 * 
	 * @param path プロセッサやエンジンが受け取るパス
	 * @param realStrPath クラス名に使用されるパス
	 * @param pathId ぶり固有のテーブル{@code BuriPath}のID
	 * @param pathType XPDLで指定されたPathTypeの値
	 */
	public BuriPath(String path, String realStrPath, long pathId, Long pathType) {
		thisPath = new BuriRealPath(path);
		realPath = new BuriRealPath(realStrPath);
		buriPathId = pathId;
		this.pathType = pathType;
	}

	/**
	 * パッケージ、プロセス、アクティビティのID、名前をそれぞれ保持するDtoのコンストラクタです。
	 * 
	 * @param path プロセッサやエンジンが受け取るパス
	 * @param realStrPath クラス名に使用されるパス
	 * @param pathType XPDLで指定されたPathTypeの値
	 */
	public BuriPath(String path, String realStrPath, Long pathType) {
		thisPath = new BuriRealPath(path);
		realPath = new BuriRealPath(realStrPath);
		this.pathType = pathType;
	}

	/**
	 * オブジェクトの複製を作成して返します。
	 * 
	 * @return 同じ内容の{@link BuriPath}オブジェクト
	 */
	public BuriPath copy() {
		BuriPath clonePath = new BuriPath();
		clonePath.thisPath = thisPath.copyRealPath();
		clonePath.realPath = realPath.copyRealPath();
		clonePath.buriPathId = buriPathId;
		clonePath.pathType = pathType;
		return clonePath;
	}

	// これを使わずにいけると思ったので・・・封印！
	// 封印解除はこれを使わないと絶対に実装できないとき public
	// BuriPath
	// changePath(String newPath, int
	// pos) {
	// BuriPath clonePath = copy();
	// clonePath.thisPath =
	// thisPath.changePash(newPath,
	// pos);
	// assert
	// realPath.getActivity().size() ==
	// 0 : "realPathを設定しているのに論理パスだけ設定";
	// return clonePath;
	// }
	//
	// public BuriPath changePath(String
	// newPath, String newRealPath, int
	// pos) {
	// BuriPath clonePath = copy();
	// clonePath.thisPath =
	// thisPath.changePash(newPath,
	// pos);
	// clonePath.realPath =
	// realPath.changePash(newRealPath,
	// pos);
	// return clonePath;
	// }
	//
	// public BuriPath
	// changePath(BuriActivityType
	// newAct, int pos) {
	// BuriPath clonePath = copy();
	// if (newAct == null) {
	// return clonePath;
	// }
	// clonePath.thisPath =
	// thisPath.changePash(newAct.getName(),
	// pos);
	// clonePath.realPath =
	// realPath.changePash(newAct.getId(),
	// pos);
	// return clonePath;
	// }
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BuriPath) {
			BuriPath tgtPath = (BuriPath) obj;
			if ((tgtPath.getBuriPathId() != 0) && (this.getBuriPathId() != 0)) {
				if (tgtPath.getBuriPathId().equals(this.getBuriPathId())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * アクティビティIDのリストを返します。
	 * 
	 * @return アクティビティIDのリスト
	 */
	public List<String> getActivityId() {
		return new ArrayList<String>(realPath.getActivity());
	}

	/**
	 * アクティビティ名のリストを返します。
	 * 
	 * @return アクティビティ名のリスト
	 */
	public List<String> getActivityName() {
		return new ArrayList<String>(thisPath.getActivity());
	}

	/**
	 * ぶり固有のテーブル{@code BuriPath}で使用されるIDを返します。
	 * 
	 * @return ぶり固有のテーブル{@code BuriPath}で使用されるID
	 */
	public Long getBuriPathId() {
		return buriPathId;
	}

	/**
	 * PathTypeを返します。
	 * 
	 * @return PathType
	 */
	public Long getPathType() {
		return pathType;
	}

	/**
	 * 名称で構成された実行対象のパスを返します。
	 * 
	 * @return 実行対象のパス
	 */
	public String getPlainName() {
		return thisPath.getPlainName();
	}

	/**
	 * IDで構成された実行対象のパスを返します。
	 * 
	 * @return 実行対象のパス
	 */
	public BuriRealPath getRealPath() {
		return realPath;
	}

	/**
	 * フローのパッケージ名を返します。
	 * 
	 * @return フローのパッケージ名
	 */
	public String getWorkflowPackage() {
		return thisPath.getWorkflowPackage();
	}

	/**
	 * フローのプロセス名を返します。
	 * 
	 * @return フローのプロセス名
	 */
	public String getWorkflowProcess() {
		return thisPath.getWorkflowProcess();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hashCode = 1;
		hashCode = 31 * hashCode + (int) (+buriPathId ^ (buriPathId >>> 32));
		hashCode = 31 * hashCode + (thisPath == null ? 0 : thisPath.hashCode());
		hashCode = 31 * hashCode + (realPath == null ? 0 : realPath.hashCode());
		return hashCode;
	}

	/**
	 * 対象のパスがぶりの管理下にあるかどうかを判定します。
	 * <p>
	 * ぶり固有のテーブル{@code BuriPath}で使用されるIDが{@code 0}以外の場合{@code true}を返します。
	 * </p>
	 * <p>
	 * IDで構成された実行対象のパスに空の構成要素がない場合、{@code true}を返します。
	 * </p>
	 * 
	 * @return 管理下にあった場合{@code true}、そうでない場合は{@code false}
	 */
	public boolean isCorrect() {
		if (getBuriPathId() != 0) {
			return true;
		}
		if (getRealPath().isCorrect()) {
			return true;
		}
		return false;
	}

	/**
	 * {@link BuriActivityType}から新しいアクティビティを追加して移動された実行対象のパスを返します。
	 * 
	 * @param newAct 追加したい{@link BuriActivityType}
	 * @return 移動後の{@link BuriPath}
	 */
	public BuriPath moveChildPath(BuriActivityType newAct) {
		BuriPath clonePath = moveChildPath(newAct.getName(), newAct.getId());
		return clonePath;
	}

	/**
	 * 新しいアクティビティのIDと名前を指定して実行対象パスを移動させます。
	 * 
	 * @param newPath 移動させたいパスの名前
	 * @param newRealPath 移動させたいパスのID
	 * @return 移動後の{@link BuriPath}
	 */
	public BuriPath moveChildPath(String newPath, String newRealPath) {
		BuriPath clonePath = copy();
		clonePath = clonePath.setBuriPathId(0);
		clonePath.thisPath.moveChildPath(newPath);
		clonePath.realPath.moveChildPath(newRealPath);
		return clonePath;
	}

	/**
	 * 積み上げたアクティビティを減らして実行対象のパスを移動させます。
	 * 
	 * @return 移動後の{@link BuriPath}
	 */
	public BuriPath moveUpPath() {
		BuriPath clonePath = copy();
		clonePath = clonePath.setBuriPathId(0);
		clonePath.thisPath.moveUpPath();
		clonePath.realPath.moveUpPath();
		return clonePath;
	}

	/**
	 * ぶり固有のテーブル{@code BuriPath}で使用されるIDから得られる{@link BuriPath}を返します。
	 * <p>
	 * その他の値は今のオブジェクトのものが引き継がれます。
	 * </p>
	 * 
	 * @param buriPathId ぶり固有のテーブル{@code BuriPath}で使用されるID
	 * @return 対応した{@link BuriPath}
	 */
	public BuriPath setBuriPathId(long buriPathId) {
		BuriPath clonePath = copy();
		clonePath.buriPathId = buriPathId;
		return clonePath;
	}

	/**
	 * {@code PathType}から得られる{@link BuriPath}を返します。
	 * <p>
	 * {@code PathType}はXPDLで指定される値がセットされますが、ぶりではその値を活用しません。
	 * </p>
	 * 
	 * @param pathType {@code PathType}
	 * @return 対応した{@link BuriPath}
	 */
	public BuriPath setPathType(Long pathType) {
		BuriPath clonePath = copy();
		clonePath.pathType = pathType;
		return clonePath;
	}

	/**
	 * {@link BuriPackageType}を指定して得られる{@link BuriPath}を返します。
	 * <p>
	 * その他の値は今のオブジェクトのものが引き継がれます。
	 * </p>
	 * 
	 * @param packageDoc {@link BuriPackageType}
	 * @return 対応した{@link BuriPath}
	 */
	public BuriPath setWorkflowPackage(BuriPackageType packageDoc) {
		BuriPath clonePath = copy();
		clonePath.thisPath.setWorkflowPackage(packageDoc.getName());
		clonePath.realPath.setWorkflowPackage(packageDoc.getId());
		return clonePath;
	}

	/**
	 * フローのパッケージ名を指定することで得られる{@link BuriPath}を返します。
	 * <p>
	 * その他の値は今のオブジェクトのものが引き継がれます。
	 * </p>
	 * 
	 * @param workflow フローのパッケージ名前
	 * @return 対応した{@link BuriPath}
	 */
	public BuriPath setWorkflowPackage(String workflow) {
		BuriPath clonePath = copy();
		clonePath.thisPath.setWorkflowPackage(workflow);
		assert realPath.getWorkflowPackage().length() == 0 : "realPathを設定しているのに論理パスだけ設定";
		return clonePath;
	}

	/**
	 * フローのパッケージ名とパッケージIDを指定して得られる{@link BuriPath}を返します。
	 * <p>
	 * その他の値は今のオブジェクトのものが引き継がれます。
	 * </p>
	 * 
	 * @param workflow フローのパッケージ名
	 * @param realWorkflow フローのパッケージID
	 * @return 対応した{@link BuriPath}
	 */
	public BuriPath setWorkflowPackage(String workflow, String realWorkflow) {
		BuriPath clonePath = copy();
		clonePath.thisPath.setWorkflowPackage(workflow);
		clonePath.realPath.setWorkflowPackage(realWorkflow);
		return clonePath;
	}

	/**
	 * {@link BuriWorkflowProcessType}を指定して得られる{@link BuriPath}を返します。
	 * <p>
	 * その他の値は今のオブジェクトのものが引き継がれます。
	 * </p>
	 * 
	 * @param wkfProcess 実行対象の{@link BuriWorkflowProcessType}
	 * @return 対応する{@link BuriPath}
	 */
	public BuriPath setWorkflowProcess(BuriWorkflowProcessType wkfProcess) {
		BuriPath clonePath = copy();
		clonePath.thisPath.setWorkflowProcess(wkfProcess);
		clonePath.realPath.setWorkflowProcess(wkfProcess.getId());
		return clonePath;
	}

	/**
	 * フローのプロセス名を指定して得られる{@link BuriPath}を返します。
	 * <p>
	 * その他の値は今のオブジェクトのものが引き継がれます。
	 * </p>
	 * 
	 * @param process フローのプロセス名
	 * @return 対応した{@link BuriPath}
	 */
	public BuriPath setWorkflowProcess(String process) {
		BuriPath clonePath = copy();
		clonePath.thisPath.setWorkflowProcess(process);
		assert realPath.getWorkflowProcess().length() == 0 : "realPathを設定しているのに論理パスだけ設定";
		return clonePath;
	}

	/**
	 * フローのプロセス名とプロセスIDを指定して得られる{@link BuriPath}を返します。
	 * <p>
	 * その他の値は今のオブジェクトのものが引き継がれます。
	 * </p>
	 * 
	 * @param process フローのプロセス名
	 * @param realProcess フローのプロセスID
	 * @return 対応した{@link BuriPath}
	 */
	public BuriPath setWorkflowProcess(String process, String realProcess) {
		BuriPath clonePath = copy();
		clonePath.thisPath.setWorkflowProcess(process);
		clonePath.realPath.setWorkflowProcess(realProcess);
		return clonePath;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		String str = thisPath.getPlainName();
		str = str + "[" + realPath.getPlainName() + "]";
		return str;
	}
}
